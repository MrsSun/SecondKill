package secondkill.biz.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import secondkill.biz.GoodsBiz;
import secondkill.biz.dto.GoodsDetail;
import secondkill.biz.dto.SecondKillBizRequest;

import static org.junit.Assert.*;

/**
 * @author chaoge
 * @date 2017/6/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:/spring/spring-*.xml")
public class GoodsBizImplTest {

    @Autowired
    private GoodsBiz goodsBiz;

    @Test
    public void testQueryAllGoods() throws Exception {

    }

    @Test
    public void testQueryAllActiveGoods() throws Exception {

    }

    @Test
    public void testQueryGoodsDetails() throws Exception {

    }

    @Test
    public void testSecondKill() throws Exception {
        SecondKillBizRequest request = new SecondKillBizRequest(1000L, 1000L, "1b0559a9e09b4182b4db9c3ebe6b000a", 1);
        boolean result = goodsBiz.secondKill(request);
        System.out.println(request);
        assertTrue(result);
        Thread.sleep(500);
    }

    @Test
    public void testGetGoods() throws Exception {
        GoodsDetail goodsDetail = goodsBiz.queryGoodsDetails(1000L);
        System.out.println(goodsDetail);
    }
}