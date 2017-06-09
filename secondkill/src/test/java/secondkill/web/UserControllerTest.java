package secondkill.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import secondkill.UserUtils;
import secondkill.entity.User;

import javax.servlet.http.HttpSession;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author sunxin08
 * @date 2017/6/8
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:WEB-INF/applicationContext.xml")
public class UserControllerTest {


    @Autowired
    private UserController userController;

    @Test
    public void testLogin() throws Exception {
//        HttpSession session = new MockHttpSession();
//        userController.login("sunxin", "sunxin", session);
//        User user = (User) session.getAttribute(UserUtils.USER_AUTH_KEY);
//        System.out.println(user);
//        Assert.notNull(session.getAttribute(UserUtils.USER_AUTH_KEY), "login error");
    }

    @Test
    public void testRegister() throws Exception {
        HttpSession session = new MockHttpSession();
        userController.register("sunxin", "sunxin", session);
        Assert.notNull(session.getAttribute(UserUtils.USER_AUTH_KEY), "login error");
    }

    @Test
    public void testGetAllUser() throws Exception {
        List<User> users = userController.getAllUser();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testGetUser() throws Exception {
        User user = userController.getUser(1000L);
        System.out.println(user);
    }
}