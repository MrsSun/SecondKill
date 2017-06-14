package secondkill.util;

/**
 * @author chaoge
 * @date 2017/6/12
 */
public enum GoodsStatusEnum {

    NOT_START(0), ONGOING(1), FINISHED(2);

    private int status;

    GoodsStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
