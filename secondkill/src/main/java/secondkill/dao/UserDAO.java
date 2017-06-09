package secondkill.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import secondkill.entity.User;

import java.util.List;

/**
 * @author sunxin08
 * @date 2017/6/7
 */
@Repository
public interface UserDAO {

    int insertUser(User user);

    int update(@Param("id") long id, @Param("name") String name);

    User queryById(Long id);

    List<User> queryAll();

    int deleteById(long id);

    User login(@Param("name") String name, @Param("password") String password);

}
