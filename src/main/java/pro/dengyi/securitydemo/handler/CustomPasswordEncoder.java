package pro.dengyi.securitydemo.handler;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 自定义密码处理类
 *
 * @author 邓艺
 * @version v1.0
 * @Title 自定义密码处理类
 * @Description
 * @date 2019/11/13 14:22
 **/
@Component
public class CustomPasswordEncoder implements PasswordEncoder {
    /**
     * 这个方法是加密方法，可以使用自己的加密方式实现
     * <br/>
     *
     * @param rawPassword 原始的密码
     * @return java.lang.String
     * @author dengyi
     * @date 2019/11/13 14:42
     */
    @Override
    public String encode(CharSequence rawPassword) {
        //自定义使用md5加密
        byte[] bytes = rawPassword.toString().getBytes();
        return MD5Encoder.encode(bytes);
    }

    /**
     * 这个方法在做密码的对比
     * <br/>
     *
     * @param rawPassword     原始密码
     * @param encodedPassword 加密后的密码
     * @return boolean
     * @author dengyi
     * @date 2019/11/13 14:45
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        byte[] bytes = rawPassword.toString().getBytes();
        return encodedPassword.equals(MD5Encoder.encode(bytes));
    }
}
