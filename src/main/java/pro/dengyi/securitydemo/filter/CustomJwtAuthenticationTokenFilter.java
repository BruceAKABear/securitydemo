package pro.dengyi.securitydemo.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import pro.dengyi.securitydemo.service.UserService;
import pro.dengyi.securitydemo.utils.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 邓艺
 * @version v1.0
 * @Title jwt过滤器
 * @Description
 * @date 2019/11/13 14:14
 **/
@Component
public class CustomJwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取请求的头是否包含我们规定的
        String headerBody = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(headerBody)) {
            //包含规定头信息
            String jwtMessageBody = headerBody.replace("Bearer ", "");
            if (JwtUtil.isExpiration(jwtMessageBody)) {

            }
        }

        //执行
        filterChain.doFilter(request, response);

    }
}
