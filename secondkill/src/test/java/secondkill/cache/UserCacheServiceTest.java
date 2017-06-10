package secondkill.cache;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import secondkill.entity.User;

/**
 * @author sunxin08
 * @date 2017/6/9
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:/spring/spring-*.xml")
public class UserCacheServiceTest {

    @Autowired
    private UserCacheService userCacheService;

    @Test
    public void testAddCacheBean() {
        User user = new User("sunxin", "sunxin");
        user.setUserId(1000L);
        userCacheService.addCacheBean(user);
    }

    @Test
    public void testGetCacheBean() {
        User user = new User("sunxin", "sunxin");
        user.setUserId(1000L);
        User u = userCacheService.getCacheBean(user);
        Assert.assertNotNull(u);
    }

    @Test
    public void testGetCacheBeanById() {
        User u = userCacheService.getCacheBeanById(1000L);
        Assert.assertNotNull(u);
    }

}