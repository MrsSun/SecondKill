package secondkill.biz;

import secondkill.entity.User;
import secondkill.biz.dto.UserInfo;

/**
 * @author chaoge
 * @date 2017/6/7
 */
public interface UserBiz {

    UserInfo getUserInfo(Long id);

    boolean validate(User user);

    User login(String name, String password);

    User register(String name, String password);

}
