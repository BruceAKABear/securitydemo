package pro.dengyi.securitydemo.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未登录情况下，访问需要登录以后的资源调用
 *
 * @author 邓艺
 * @version v1.0
 * @Title 未登录处理器
 * @Description
 * @date 2019/11/13 13:48
 **/
@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //设置response状态码，返回错误信息等

        System.out.println(2222);
    }
}