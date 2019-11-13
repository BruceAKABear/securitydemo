package pro.dengyi.securitydemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pro.dengyi.securitydemo.model.CustomUserDetails;
import pro.dengyi.securitydemo.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 邓艺
 * @version v1.0
 * @Title 用户信息处理service
 * @Description
 * @date 2019/11/13 14:00
 **/
@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
//        if (CollectionUtils.isNotEmpty(user.getRoles())) {
//            user.getRoles().forEach(r -> authorities.add(new SimpleGrantedAuthority("ROLE_" + r.getRoleName())));
//        }
        customUserDetails.setAuthorities(authorities);
        log.info("authorities:用户信息");
        return customUserDetails;
    }
}
