package secondkill.web;

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
 * @date 2017/6/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:/spring/spring-*.xml")
public class GoodsControllerTest {

    @Autowired
    private GoodsController goodsController;

    @Test
    public void testGetAllGoods() throws Exception {
        List<Goods> goodses = goodsController.getAllGoods();
        System.out.println(goodses);
        assertNotNull(goodses);
    }

    @Test
    public void testGetGoods() throws Exception {

    }
}