package secondkill.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import secondkill.entity.Goods;

import java.util.Date;
import java.util.List;

/**
 * @author chaoge
 * @date 2017/6/9
 */
public interface GoodsDAO {

    List<Goods> queryAllGoods();

    List<Goods> queryAllActiveGoods(@Param("now") Date now);

    Goods queryGoodsById(Long id);

    /**
     * 减库存
     * 余量大于减少量时执行
     * @param id
     * @param number
     * @return
     */
    int reduceCAS(@Param("goodsId") Long id, @Param("number") Integer number);

    /**
     * 修改包含Uuid的所有属性
     * 使用乐观锁，保证uuid只会被改一次
     * @param goods
     * @return
     */
    int updateWithUuid(Goods goods);

    /**
     * 修改除了Uuid之外的属性
     * @param goods
     * @return
     */
    int updateExceptUuid(Goods goods);

}
