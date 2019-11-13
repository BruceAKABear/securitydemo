package pro.dengyi.securitydemo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 邓艺
 * @version v1.0
 * @Title 用户实体类
 * @Description
 * @date 2019/11/13 12:50
 **/
@Data
@TableName("t_user")
public class User {

    private Long id;
    private String userName;
    private String password;
}
