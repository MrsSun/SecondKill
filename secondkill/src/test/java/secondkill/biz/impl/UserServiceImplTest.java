package secondkill.biz.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import secondkill.biz.UserBiz;
import secondkill.entity.User;

/**
 * @author chaoge
 * @date 2017/6/8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:/spring/spring-*.xml")
public class UserServiceImplTest {

    @Autowired
    private UserBiz userBiz;

    @Test
    public void testGetAllUser() throws Exception {

    }

    @Test
    public void testGetUser() throws Exception {

    }

    @Test
    public void testValidate() throws Exception {
        User user = new User("sunxin", "sunxin");
        user.setUserId(1000L);
        System.out.println(userBiz.validate(user));
    }

    @Test
    public void testLogin() throws Exception {
        userBiz.login("sunxin", "sunxin");
    }

    @Test
    public void testRegister() throws Exception {

    }
}