package secondkill.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import secondkill.cache.Cacheable;

import java.io.Serializable;

/**
 * 用户Entity
 * @author chaoge
 * @date 2017/6/7
 */
public class User implements Serializable, Cacheable {

    private Long userId;

    private String name;

    private String password;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(userId).append(name).append(password).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof User)) return false;
        User rhs = (User) obj;
        return new EqualsBuilder()
                .append(this.getUserId(), rhs.getUserId())
                .append(this.getName(), rhs.getName())
                .append(this.getPassword(), rhs.getPassword())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.getUserId())
                .append(this.getName())
                .append(this.getPassword())
                .hashCode();
    }
}
