package secondkill.biz.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.BeanUtils;
import secondkill.entity.Goods;

import java.util.Date;

/**
 * @author chaoge
 * @date 2017/6/12
 */
public class GoodsDetail {

    private Long goodsId;

    private String uuid;

    private String name;

    // 总量
    private Integer total;

    // 余量
    private Integer surplus;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private boolean ongoing;

    private Date nowTime;

    public GoodsDetail(Goods goods) {
        BeanUtils.copyProperties(goods, this);
        this.nowTime = new Date();
        if (nowTime.getTime() <= this.endTime.getTime() && nowTime.getTime() >= startTime.getTime()) {
            this.ongoing = true;
        } else {
            this.ongoing = false;
        }
    }

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

    public Date getNowTime() {
        return nowTime;
    }

    public void setNowTime(Date nowTime) {
        this.nowTime = nowTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isOngoing() {
        return ongoing;
    }

    public void setOngoing(boolean ongoing) {
        this.ongoing = ongoing;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(goodsId)
                .append(uuid)
                .append(name)
                .append(total)
                .append(surplus)
                .append(startTime)
                .append(endTime)
                .append(createTime)
                .append(ongoing)
                .append(nowTime)
                .toString();

    }
}
