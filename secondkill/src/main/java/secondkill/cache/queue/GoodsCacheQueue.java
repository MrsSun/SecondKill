package secondkill.cache.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import secondkill.biz.dto.GoodsReduceTask;
import secondkill.cache.service.GoodsCacheService;
import secondkill.dao.GoodsDAO;
import secondkill.entity.Goods;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chaoge
 * @date 2017/6/13
 */
@Service
public class GoodsCacheQueue extends AbstractCacheQueue<GoodsReduceTask> {

    @Autowired
    private GoodsCacheService goodsCacheService;

    @Autowired
    private GoodsDAO goodsDAO;

    private Map<Long, Integer> countMap = new ConcurrentHashMap<Long, Integer>();


    /**
     * 判断是否能加进队列
     * @param task
     * @return
     */
    @Override
    protected boolean canPush(GoodsReduceTask task) {
        Integer count = countMap.getOrDefault(task.getGoodsId(), 0);
        return count + task.getNumber() <= getTotal(task.getGoodsId());
    }

    @Override
    public boolean push(GoodsReduceTask task) {
        if (super.push(task)) {
            // 不要分开写，用ConcurrentHashMap的同步机制保证线程安全，否则自己加锁保证
            countMap.put(task.getGoodsId(), task.getNumber() + countMap.getOrDefault(task.getGoodsId(), 0));
            return true;
        }
        return false;
    }

    @Override
    protected String getKey() {
        return "" + GoodsReduceTask.class;
    }

    @Override
    protected Class<GoodsReduceTask> getTaskClass() {
        return GoodsReduceTask.class;
    }

    private Integer getTotal(Long goodsId) {
        Goods goods = goodsCacheService.getCacheBeanById(goodsId);
        if (Objects.isNull(goods)) {
            goods = goodsDAO.queryGoodsById(goodsId);
        }
        Assert.notNull(goodsId, "can not query Goods by goodsId[" + goodsId + "]");
        return goods.getTotal();
    }
}
