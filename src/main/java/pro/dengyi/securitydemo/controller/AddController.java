package pro.dengyi.securitydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 邓艺
 * @version v1.0
 * @Title
 * @Description
 * @date 2019/11/13 17:06
 **/
@RestController
@RequestMapping
public class AddController {

    @GetMapping("add")
    public Map<String, Object> add() {
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("name", "23");
        return objectObjectHashMap;
    }
}
