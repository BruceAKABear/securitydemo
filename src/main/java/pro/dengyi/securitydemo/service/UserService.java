package pro.dengyi.securitydemo.service;

import pro.dengyi.securitydemo.model.User;

/**
 * @author 邓艺
 * @version v1.0
 * @Title
 * @Description
 * @date 2019/11/13 12:49
 **/
public interface UserService {
    User findByUserName(String username);
}
