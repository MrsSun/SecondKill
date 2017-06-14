package secondkill.biz.dto;

/**
 * @author chaoge
 * @date 2017/6/9
 */
public class Order {

    private Long userId;

    private Long goodsId;

    private Long number;

    public Order(Long userId, Long goodsId, Long number) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.number = number;
    }

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

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
