package secondkill.cache;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.*;

/**
 * @author sunxin08
 * @date 2017/6/9
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:/spring/spring-*.xml")
public class JedisServiceTest {

    @Autowired
    private JedisService jedisService;

    @Test
    public void testJedis() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("sunxin", "222");
        Assert.assertEquals("222", jedis.get("sunxin"));
    }

    @Test
    public void testSetString() throws Exception {
        jedisService.setString("sunxin", "111");
    }

    @Test
    public void testSetString1() throws Exception {
        jedisService.setString("second", "1", 3);
    }

    @Test
    public void testGetString() throws Exception {
        System.out.println(jedisService.getString("sunxin"));
    }
}