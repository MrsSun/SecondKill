package secondkill.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import secondkill.cache.Cacheable;

import java.util.Date;

/**
 * @author sunxin08
 * @date 2017/6/9
 */
public class Goods implements Cacheable {

    private Long goodsId;

    private String name;

    // 总量
    private Integer total;

    // 余量
    private Integer surplus;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSurplus() {
        return surplus;
    }

    public void setSurplus(Integer surplus) {
        this.surplus = surplus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(goodsId)
                .append(name)
                .append(total)
                .append(surplus)
                .append(startTime)
                .append(endTime)
                .append(createTime)
                .toString();
    }
}
