package pro.dengyi.securitydemo.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 被拒绝访问时调用
 *
 * @author 邓艺
 * @version v1.0
 * @Title 权限拒绝处理器
 * @Description
 * @date 2019/11/13 13:48
 **/
@Component
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        System.out.println(123);
    }

}
