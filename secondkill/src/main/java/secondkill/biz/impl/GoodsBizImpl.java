package secondkill.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import secondkill.biz.GoodsBiz;
import secondkill.cache.GoodsCacheService;
import secondkill.dao.GoodsDAO;
import secondkill.entity.Goods;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author sunxin08
 * @date 2017/6/9
 */
@Service
public class GoodsBizImpl implements GoodsBiz {

    @Autowired
    private GoodsDAO goodsDAO;

    @Autowired
    private GoodsCacheService goodsCacheService;

    public List<Goods> queryAllGoods() {
        return goodsDAO.queryAllGoods();
    }

    public List<Goods> queryAllActiveGoods() {
        return goodsDAO.queryAllActiveGoods(new Date());
    }

    public Goods queryGoodsDetails(Long goodsId) {
        Goods goods = goodsCacheService.getCacheBeanById(goodsId);
        if (Objects.nonNull(goods)) {
            return goods;
        }
        goods = goodsDAO.queryGoodsById(goodsId);
        updateGoodsCache(goods);
        return goods;
    }

    private void updateGoodsCache(Goods goods) {
        if (Objects.nonNull(goods)) {
            goodsCacheService.addCacheBean(goods);
        }
    }

}
