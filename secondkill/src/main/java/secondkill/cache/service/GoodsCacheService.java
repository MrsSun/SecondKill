package secondkill.cache.service;

import org.springframework.stereotype.Service;
import secondkill.entity.Goods;

/**
 * @author chaoge
 * @date 2017/6/9
 */
@Service("goodsCacheService")
public class GoodsCacheService extends AbstractRedisCacheService<Goods> {

    private final int GOODS_CACHE_TIMEOUT = 1;

    @Override
    protected String getCacheKeyById(Long id) {
        return getBeanClass() + "@" + id;
    }

    @Override
    protected String getCacheKey(Goods bean) {
        return getCacheKeyById(bean.getGoodsId());
    }

    @Override
    protected Class<Goods> getBeanClass() {
        return Goods.class;
    }

    @Override
    protected int getTimeout() {
        return GOODS_CACHE_TIMEOUT;
    }
}
