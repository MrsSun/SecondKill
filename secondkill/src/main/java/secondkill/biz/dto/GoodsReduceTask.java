package secondkill.biz.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author chaoge
 * @date 2017/6/13
 */
public class GoodsReduceTask implements Serializable {

    private Long userId;

    private Long goodsId;

    private Integer number;



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(userId)
                .append(goodsId)
                .append(number)
                .toString();
    }
}
