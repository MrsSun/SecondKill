package secondkill.dao;

import org.apache.ibatis.annotations.Param;
import secondkill.entity.Goods;

import java.util.Date;
import java.util.List;

/**
 * @author sunxin08
 * @date 2017/6/9
 */
public interface GoodsDAO {

    List<Goods> queryAllGoods();

    List<Goods> queryAllActiveGoods(@Param("now") Date now);

    Goods queryGoodsById(Long id);

    int reduceCAS(@Param("goodsId") Long id, @Param("number") Integer number);

}
