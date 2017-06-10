package secondkill.biz;

import secondkill.entity.Goods;

import java.util.List;

/**
 * @author sunxin08
 * @date 2017/6/9
 */
public interface GoodsBiz {

    /**
     * 查询所有物品
     * 后续考虑加分页
     * @return
     */
    List<Goods> queryAllGoods();

    /**
     * 查询所有活跃物品，即能秒杀的物品
     * @return
     */
    List<Goods> queryAllActiveGoods();

    /**
     * 查询物品明细
     * @param goodsId
     * @return
     */
    Goods queryGoodsDetails(Long goodsId);

}
