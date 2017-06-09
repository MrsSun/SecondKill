package secondkill.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secondkill.UserUtils;
import secondkill.entity.User;
import secondkill.service.UserService;
import secondkill.web.protocol.CommonWebResponse;
import secondkill.web.protocol.ResponseCode;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * @author sunxin08
 * @date 2017/6/7
 */
@Controller
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/")
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonWebResponse<Boolean> login(String name, String password, HttpSession session) {
        logger.info("login: [name:" + name + "], [password:" + password + "]");
        CommonWebResponse<Boolean> response = new CommonWebResponse<Boolean>();
        User user = userService.login(name, password);
        if (Objects.nonNull(user)){
            session.setAttribute(UserUtils.USER_AUTH_KEY, user);
            logger.info("login success: " + user);
            response.setData(true);
            response.setStatus(ResponseCode.WEB_STATUS_OK);
            return response;
        } else {
            logger.info("login faild: [name:" + name + "], [password:" + password + "]");
            response.setStatus(ResponseCode.WEB_STATUS_FAILED);
            response.setData(false);
            return response;
        }
    }

    @RequestMapping(value = "/register", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public boolean register(@RequestAttribute("name") String name,
                            @RequestAttribute("password") String password,
                            HttpSession session) {
        logger.info("register: [name:" + name + "], [password:" + password + "]");
        User user = userService.register(name, password);
        if (Objects.nonNull(user)){
            session.setAttribute(UserUtils.USER_AUTH_KEY, user);
            logger.info("register success: [userId:" + user.getUserId() + "], [name:" + name + "], " +
                    "[password:" + password + "]");
            return true;
        } else {
            logger.info("register faild: [name:" + name + "], [password:" + password + "]");
            return false;
        }
    }

    @RequestMapping(value = "/user")
    @ResponseBody
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @RequestMapping(value = "/user/{id}")
    @ResponseBody
    public User getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

}
