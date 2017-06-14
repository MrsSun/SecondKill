package secondkill.biz.dto;

/**
 * @author chaoge
 * @date 2017/6/13
 */
public class SecondKillBizRequest {

    private Long userId;

    private Long goodsId;

    private String uuid;

    private Integer number;

    public SecondKillBizRequest(Long userId, Long goodsId, String uuid, Integer number) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.uuid = uuid;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
