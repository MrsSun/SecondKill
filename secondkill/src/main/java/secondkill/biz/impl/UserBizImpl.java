package secondkill.biz.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import secondkill.cache.service.UserCacheService;
import secondkill.dao.UserDAO;
import secondkill.entity.User;
import secondkill.biz.UserBiz;
import secondkill.biz.dto.UserInfo;

import java.util.Objects;

/**
 * @author chaoge
 * @date 2017/6/7
 */
@Service
public class UserBizImpl implements UserBiz {

    private static final Logger logger = Logger.getLogger(UserBizImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserCacheService userCacheService;

    public UserInfo getUserInfo(Long id) {
        if (Objects.isNull(id)) {
            logger.error("getUser error: id is null");
            return null;
        }
        User user = userCacheService.getCacheBeanById(id);
        if (Objects.isNull(user)) {
            user = userDAO.queryById(id);
            updateUserCache(user);
        }
        return Objects.isNull(user) ? null : new UserInfo(user.getUserId(), user.getName());
    }

    public boolean validate(User user) {
        if (Objects.isNull(user)) {
            return false;
        }
        Long userId = user.getUserId();
        User dbUser = null;
        // 1. 缓存验证
        dbUser = userCacheService.getCacheBeanById(userId);
        // 2. db验证
        if (Objects.isNull(dbUser)) {
            dbUser = userDAO.queryById(userId);
        }
        return Objects.nonNull(dbUser) && dbUser.equals(user);
    }

    public User login(String name, String password) {
        try {
            User user = userDAO.login(name, password);
            updateUserCache(user);
            return user;
        } catch (Exception ex) {
            logger.error("login error: [name:" + name + "], [password:" + password + "]", ex);
            return null;
        }
    }

    public User register(String name, String password) {
        User user = new User(name, password);
        try {
            userDAO.insertUser(user);
            updateUserCache(user);
            return user;
        } catch (Exception ex) {
            logger.error("register error: [name:" + name + "], [password:" + password + "]", ex);
            return null;
        }
    }

    private void updateUserCache(User user) {
        if (Objects.nonNull(user)) {
            userCacheService.addCacheBean(user);
        }
    }
}
