package secondkill.cache;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import secondkill.entity.User;

/**
 * @author sunxin08
 * @date 2017/6/9
 */
@Service("userCacheService")
public class UserCacheService extends AbstractRedisCacheService<User> {


    @Override
    protected String getCacheKeyById(Long id) {
        return getBeanClass() + "@" + id;
    }

    @Override
    protected String getCacheKey(User user) {
        Assert.notNull(user, "getCacheKey error: user is null");
        Assert.notNull(user.getUserId(), "getCacheKey error: userId is null");
        return getCacheKeyById(user.getUserId());
    }

    @Override
    protected Class<User> getBeanClass() {
        return User.class;
    }
}
