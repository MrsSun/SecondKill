package secondkill.cache.queue;

/**
 * @author chaoge
 * @date 2017/6/13
 */
public interface CacheQueue<T> {

    boolean push(T task);

    T pop();

    boolean isEmpty();

}
