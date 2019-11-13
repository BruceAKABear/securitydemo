package pro.dengyi.securitydemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pro.dengyi.securitydemo.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 邓艺
 * @version v1.0
 * @Title 登录控制器
 * @Description
 * @date 2019/11/13 12:47
 **/
@RestController
@RequestMapping
public class LoginController {
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(String userName, String password) {
        HashMap<String, Object> hashMap = new HashMap<>();
//        userService.login(userName,password)
        hashMap.put("name", "zhangsan");
        hashMap.put("age", 23);
        return hashMap;

    }
}
