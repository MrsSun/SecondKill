package secondkill.cache.service;

import secondkill.cache.Cacheable;

/**
 * @author chaoge
 * @date 2017/6/9
 */
public interface CacheService <T extends Cacheable> {

    T getCacheBeanById(Long id);

    T getCacheBean(T bean);

    void addCacheBean(T bean);

}
