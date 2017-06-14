package secondkill.cache.queue;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import secondkill.cache.service.JedisService;

import java.util.Objects;

/**
 * @author chaoge
 * @date 2017/6/13
 */
public abstract class AbstractCacheQueue<T> implements CacheQueue<T> {

    @Autowired
    private JedisService jedisService;

    /**
     * 添加任务
     * @param task
     * @return
     */
    public boolean push(T task) {
        if (Objects.isNull(task)) {
            return false;
        }
        return pushInternal(task);
    }

    /**
     * 同步关键字加在这，防止task == null的时候，push出现空锁
     * @param task
     * @return
     */
    protected synchronized boolean pushInternal(T task) {
        if (canPush(task)) {
            Jedis jedis = jedisService.getJedis();
            long res = jedis.lpush(getKey(), JSON.toJSONString(task));
            return res > 0;
        }
        return false;
    }

    public T pop() {
        Jedis jedis = jedisService.getJedis();
        String json = jedis.lpop(getKey());
        T task = JSON.parseObject(json, getTaskClass());
        return task;
    }

    public boolean isEmpty() {
        Jedis jedis = jedisService.getJedis();
        return 0 == jedis.llen(getKey());
    }

    protected abstract boolean canPush(T task);

    protected abstract String getKey();

    protected abstract Class<T> getTaskClass();
}
