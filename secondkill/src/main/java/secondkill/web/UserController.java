package secondkill.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import secondkill.util.UserUtils;
import secondkill.biz.UserBiz;
import secondkill.entity.User;
import secondkill.biz.dto.UserInfo;
import secondkill.web.protocol.CommonWebResponse;
import secondkill.web.protocol.ResponseCode;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author chaoge
 * @date 2017/6/7
 */
@Controller
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserBiz userBiz;

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

        User user = userBiz.login(name, password);
        if (Objects.nonNull(user)){
            session.setAttribute(UserUtils.USER_AUTH_KEY, user);
            logger.info("login success: " + user);
            response.setData(true);
            response.setStatus(ResponseCode.WEB_STATUS_OK);
        } else {
            logger.info("login faild: [name:" + name + "], [password:" + password + "]");
            response.setStatus(ResponseCode.WEB_STATUS_FAILED);
            response.setData(false);
        }
        return response;
    }

    @RequestMapping(value = "/register", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonWebResponse<Boolean> register(String name, String password, HttpSession session) {
        logger.info("register: [name:" + name + "], [password:" + password + "]");
        CommonWebResponse<Boolean> response = new CommonWebResponse<Boolean>();

        User user = userBiz.register(name, password);
        if (Objects.nonNull(user)){
            session.setAttribute(UserUtils.USER_AUTH_KEY, user);
            logger.info("register success: [userId:" + user.getUserId() + "], [name:" + name + "], " +
                    "[password:" + password + "]");
            response.setStatus(ResponseCode.WEB_STATUS_OK);
            response.setData(true);
        } else {
            logger.info("register faild: [name:" + name + "], [password:" + password + "]");
            response.setStatus(ResponseCode.WEB_STATUS_FAILED);
            response.setData(false);
        }
        return response;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    @ResponseBody
    public UserInfo getUser(@PathVariable("id") Long id) {
        return userBiz.getUserInfo(id);
    }

}
