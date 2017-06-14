package secondkill.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import secondkill.util.UserUtils;
import secondkill.biz.dto.UserInfo;

import javax.servlet.http.HttpSession;

/**
 * @author chaoge
 * @date 2017/6/8
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:/spring/spring-*.xml")
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
    public void testGetUser() throws Exception {
        UserInfo user = userController.getUser(1000L);
        System.out.println(user);
    }
}