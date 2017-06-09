package secondkill.service;

import secondkill.entity.User;

import java.util.List;

/**
 * @author sunxin08
 * @date 2017/6/7
 */
public interface UserService {

    List<User> getAllUser();

    User getUser(Long id);

    boolean validate(User user);

    User login(String name, String password);

    User register(String name, String password);

}
