package secondkill.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import secondkill.dao.UserDAO;
import secondkill.entity.User;
import secondkill.service.UserService;

import java.util.List;
import java.util.Objects;

/**
 * @author sunxin08
 * @date 2017/6/7
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    public List<User> getAllUser() {
        return userDAO.queryAll();
    }

    public User getUser(Long id) {
        if (Objects.isNull(id)) {
            logger.error("getUser error: id is null");
            return null;
        }
        return userDAO.queryById(id);
    }

    public boolean validate(User user) {
        if (Objects.isNull(user)) {
            return false;
        }
        Long userId = user.getUserId();
        // 1. 缓存验证

        // 2. db验证
        User dbUser = userDAO.queryById(userId);
        return Objects.nonNull(dbUser) && dbUser.equals(user);
    }

    public User login(String name, String password) {
        try {
            return userDAO.login(name, password);
        } catch (Exception ex) {
            logger.error("login error: [name:" + name + "], [password:" + password + "]", ex);
            return null;
        }
    }

    public User register(String name, String password) {
        User user = new User(name, password);
        try {
            userDAO.insertUser(user);
            return user;
        } catch (Exception ex) {
            logger.error("register error: [name:" + name + "], [password:" + password + "]", ex);
            return null;
        }
    }
}
