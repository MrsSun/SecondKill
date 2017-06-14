package secondkill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import secondkill.entity.Goods;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author chaoge
 * @date 2017/6/9
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:/spring/spring-*.xml")
public class GoodsDAOTest {

    @Autowired
    private GoodsDAO goodsDAO;

    @Test
    public void testGetAllGoods() throws Exception {
        List<Goods> goodses = goodsDAO.queryAllGoods();
        for (Goods goods : goodses) {
            System.out.println(goods);
        }
        assertNotNull(goodses);
    }

    @Test
    public void testGetGoodsById() throws Exception {
        Goods goods = goodsDAO.queryGoodsById(1000L);
        assertNotNull(goods);
        System.out.println(goods);
    }

    @Test
    public void testReduceCAS() throws Exception {
        int res = goodsDAO.reduceCAS(1000L, 1);
        assertTrue(res > 0);
        System.out.println(res);
        res = goodsDAO.reduceCAS(1000L, 100000);
        System.out.println(res);
        assertFalse(res > 0);
    }
}