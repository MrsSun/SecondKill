package secondkill.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import secondkill.entity.User;
import secondkill.service.UserService;

/**
 * @author sunxin08
 * @date 2017/6/8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:WEB-INF/applicationContext.xml")
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

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
        System.out.println(userService.validate(user));
    }

    @Test
    public void testLogin() throws Exception {

    }

    @Test
    public void testRegister() throws Exception {

    }
}