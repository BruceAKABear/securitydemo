package pro.dengyi.securitydemo.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pro.dengyi.securitydemo.model.CustomUserDetails;
import pro.dengyi.securitydemo.model.User;
import pro.dengyi.securitydemo.utils.JwtUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功调用
 *
 * @author 邓艺
 * @version v1.0
 * @Title 认证成功处理器
 * @Description
 * @date 2019/11/13 13:48
 **/
@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //简单的说就是获取当前用户，拿到用户名或者userId，创建token，返回
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("123l");

        User user = new User();
        user.setId(customUserDetails.getId());
        user.setUserName(customUserDetails.getUserName());
        String s = JwtUtil.generateJsonWebToken(user);
        response.setHeader("Authorization", "Bearer " + s);
    }
}
