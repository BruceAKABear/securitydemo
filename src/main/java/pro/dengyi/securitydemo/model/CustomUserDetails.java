package pro.dengyi.securitydemo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author 邓艺
 * @version v1.0
 * @Title 自定义用户信息类
 * @Description
 * @date 2019/11/13 16:28
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomUserDetails extends User implements UserDetails {

    //权限集合
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 构造器
     * <br/>
     *
     * @param user 自定义用户对象
     * @author dengyi
     * @date 2019/11/13 16:30
     */
    public CustomUserDetails(User user) {
        this.setId(user.getId());
        this.setUserName(user.getUserName());
        this.setPassword(user.getPassword());
//        this.setRoles(user.getRoles());
//        this.setStatus(user.getStatus());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否锁
     * <br/>
     *
     * @param
     * @return boolean
     * @author dengyi
     * @date 2019/11/13 16:44
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
