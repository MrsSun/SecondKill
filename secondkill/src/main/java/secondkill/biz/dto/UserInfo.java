package secondkill.biz.dto;

/**
 * @author sunxin08
 * @date 2017/6/9
 */
public class UserInfo {

    private Long userId;

    private String name;

    public UserInfo(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
