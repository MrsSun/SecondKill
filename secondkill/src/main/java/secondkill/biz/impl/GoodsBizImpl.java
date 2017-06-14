package secondkill.biz.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import secondkill.biz.GoodsBiz;
import secondkill.biz.dto.GoodsDetail;
import secondkill.biz.dto.GoodsReduceTask;
import secondkill.biz.dto.SecondKillBizRequest;
import secondkill.cache.queue.CacheQueue;
import secondkill.cache.queue.GoodsCacheQueue;
import secondkill.cache.service.GoodsCacheService;
import secondkill.dao.GoodsDAO;
import secondkill.dao.RecordDAO;
import secondkill.dao.UserDAO;
import secondkill.entity.Goods;
import secondkill.entity.Record;
import secondkill.entity.User;
import secondkill.util.GoodsStatusEnum;
import secondkill.util.UuidUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author chaoge
 * @date 2017/6/9
 */
@Service
public class GoodsBizImpl implements GoodsBiz, InitializingBean {

    private Logger logger = Logger.getLogger(GoodsBizImpl.class);

    @Autowired
    private GoodsDAO goodsDAO;

    @Autowired
    private RecordDAO recordDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private GoodsCacheService goodsCacheService;

    private final int SCHEDULED_THREAD_SIZE = 5;

    private final long SCHEDULED_TIME_INTERVAL = 500;

    private final int ORDER_SERVICE_THREAD_POOL_SIZE = 50;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

    // 订单线程池
    private final ExecutorService orderService = Executors.newFixedThreadPool(ORDER_SERVICE_THREAD_POOL_SIZE);

    @Autowired
    private GoodsCacheQueue goodsCacheQueue;

    public List<Goods> queryAllGoods() {
        return goodsDAO.queryAllGoods();
    }

    public List<Goods> queryAllActiveGoods() {
        return goodsDAO.queryAllActiveGoods(new Date());
    }

    public GoodsDetail queryGoodsDetails(Long goodsId) {
        GoodsDetail goodsDetail = queryGoodsDetailsInternal(goodsId);
        if (Objects.isNull(goodsDetail)) {
            goodsDetail = queryGoodsDetailsInternal(goodsId);
        }
        return goodsDetail;
    }

    @Transactional
    public Boolean secondKill(SecondKillBizRequest request) {
        Goods goods = getGoods(request.getGoodsId());
        if (Objects.isNull(goods)) {
            logger.error("secondKill error: goods is null");
            return false;
        }
        if (false == StringUtils.equals(request.getUuid(), goods.getUuid())) {
            return false;
        }
        GoodsReduceTask task = new GoodsReduceTask();
        task.setGoodsId(request.getGoodsId());
        task.setUserId(request.getUserId());
        task.setNumber(request.getNumber());
        return goodsCacheQueue.push(task);
    }

    public Goods getGoods(Long goodsId) {
        Goods goods = goodsCacheService.getCacheBeanById(goodsId);
        if (Objects.isNull(goods)) {
            goods = goodsDAO.queryGoodsById(goodsId);
            updateGoodsCache(goods);
        }
        return goods;
    }

    /**
     *
     * @param goodsId
     * @return
     */
    private GoodsDetail queryGoodsDetailsInternal(Long goodsId) {
        if (Objects.isNull(goodsId)) {
            logger.error("queryGoodsDetails error: goodsId is null");
        }
        Goods goods = goodsCacheService.getCacheBeanById(goodsId);
        if (Objects.isNull(goods)) {
            goods = goodsDAO.queryGoodsById(goodsId);
            updateGoodsCache(goods);
        }
        updateStatusAndCache(goods);
        return new GoodsDetail(goods);
    }

    private void updateGoodsCache(Goods goods) {
        if (Objects.nonNull(goods)) {
            goodsCacheService.addCacheBean(goods);
        }
    }

    /**
     * 更新状态、uuid，及缓存
     * 使用乐观锁，更新失败返回false
     * @param goods
     * @return
     */
    private boolean updateStatusAndCache(Goods goods) {
        long now = new Date().getTime();
        GoodsStatusEnum status = null;
        if (now < goods.getStartTime().getTime()) {
            status = GoodsStatusEnum.NOT_START;
        } else if (now > goods.getEndTime().getTime()) {
            status = GoodsStatusEnum.FINISHED;
        } else {
            status = GoodsStatusEnum.ONGOING;
        }
        if (false == goods.getStatus().equals(status.getStatus())) {
            // 从未开始变为开始，需要考虑同步问题，防止uuid被多次修改
            int lines = 1;
            if (goods.getStatus().equals(GoodsStatusEnum.NOT_START.getStatus()) && status == GoodsStatusEnum.ONGOING) {
                goods.setUuid(UuidUtils.generateUuid());
                goods.setStatus(status.getStatus());
                lines = goodsDAO.updateWithUuid(goods);
            } else {
                goods.setStatus(status.getStatus());
                lines = goodsDAO.updateExceptUuid(goods);
            }
            updateGoodsCache(goods);
            return lines != 0;
        }
        return true;
    }

    @Transactional
    private boolean processTask(GoodsReduceTask task) {
        // 减库存
        int res = goodsDAO.reduceCAS(task.getGoodsId(), task.getNumber());
        if (res <= 0) {
            logger.error("processTask error: " + task);
            return false;
        }
        // 加记录
        User user = userDAO.queryById(task.getUserId());
        Goods goods = goodsDAO.queryGoodsById(task.getGoodsId());
        Record record = new Record();
        record.setUser(user);
        record.setGoods(goods);
        record.setNumber(task.getNumber());
        record.setCreateTime(new Date());
        res = recordDAO.addRecord(record);
        return res > 0;
    }

    /**
     * 初始化调度器和线程池
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        scheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                if (!goodsCacheQueue.isEmpty()) {
                    final GoodsReduceTask task = goodsCacheQueue.pop();
                    orderService.execute(() -> processTask(task));
                }
            }
        }, 0, SCHEDULED_TIME_INTERVAL, TimeUnit.MILLISECONDS);
        logger.info("afterPropertiesSet finished");
    }
}
