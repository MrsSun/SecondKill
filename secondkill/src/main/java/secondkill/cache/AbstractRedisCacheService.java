package secondkill.cache;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sunxin08
 * @date 2017/6/9
 */
public abstract class AbstractRedisCacheService<T extends Cacheable> implements CacheService<T>, InitializingBean {

    @Autowired
    protected JedisService jedisService;

    private final int DEFAULT_CACHE_TIMEOUT= 3600;

    public T getCacheBeanById(Long id) {
        String key = getCacheKeyById(id);
        String val = jedisService.getString(key);
        return JSON.parseObject(val, getBeanClass());
    }

    public T getCacheBean(T bean) {
        String key = getCacheKey(bean);
        String val = jedisService.getString(key);
        return JSON.parseObject(val, getBeanClass());
    }

    public void addCacheBean(T bean) {
        String key = getCacheKey(bean);
        String val = JSON.toJSONString(bean);
        jedisService.setString(key, val, getTimeout());
    }

    protected abstract String getCacheKeyById(Long id);

    protected abstract String getCacheKey(T bean);

    protected abstract Class<T> getBeanClass();

    protected int getTimeout() {
        return DEFAULT_CACHE_TIMEOUT;
    }

    public void afterPropertiesSet() throws Exception {

    }
}
