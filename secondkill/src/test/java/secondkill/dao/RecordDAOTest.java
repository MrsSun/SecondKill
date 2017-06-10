package secondkill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import secondkill.entity.Goods;
import secondkill.entity.Record;
import secondkill.entity.User;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author sunxin08
 * @date 2017/6/9
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:/spring/spring-*.xml")
public class RecordDAOTest {

    @Autowired
    private RecordDAO recordDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private GoodsDAO goodsDAO;

    @Test
    public void testGetUserAllRecord() throws Exception {
        List<Record> records = recordDAO.getUserAllRecord(1000L);
        assertNotNull(records);
        for (Record record : records) {
            System.out.println(record);
            assertNotNull(record);
            assertNotNull(record.getUser());
            assertNotNull(record.getGoods());
        }
    }

    @Test
    public void testAddRecord() throws Exception {
        User user = userDAO.queryById(1001L);
        Goods goods = goodsDAO.queryGoodsById(1000L);
        Record record = new Record();
        record.setNumber(1);
        record.setUser(user);
        record.setGoods(goods);
        int res = recordDAO.addRecord(record);
        assertTrue(res > 0);
    }
}