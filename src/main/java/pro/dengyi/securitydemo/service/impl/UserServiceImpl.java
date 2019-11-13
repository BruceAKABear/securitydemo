package pro.dengyi.securitydemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.dengyi.securitydemo.dao.UserDao;
import pro.dengyi.securitydemo.model.User;
import pro.dengyi.securitydemo.service.UserService;

/**
 * @author 邓艺
 * @version v1.0
 * @Title
 * @Description
 * @date 2019/11/13 12:49
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByUserName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        return userDao.selectOne(queryWrapper);
    }
}
