package pro.dengyi.securitydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pro.dengyi.securitydemo.filter.CustomJwtAuthenticationTokenFilter;
import pro.dengyi.securitydemo.handler.*;
import pro.dengyi.securitydemo.service.CustomUserDetailService;

/**
 * @author 邓艺
 * @version v1.0
 * @Title security配置类
 * @Description
 * @date 2019/11/13 13:13
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)//开启方法权限控制
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 将我们自定义的处理器等注入给配置类
     * 注意一点是：加密方式spring推荐bcrypt方式加密，已经一处了md5加密，淡然你可以自定义加密类，去实现PasswordEncoder重写加密方法，个人不建议自定义实现
     * 自定义实现留在此处，方便参考
     */
    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomJwtAuthenticationTokenFilter customJwtAuthenticationTokenFilter;
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;
    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    /**
     * 这个方法定义用户认证信息获取的来源，类似于shiro中relam
     * <br/>
     *
     * @param auth
     * @return void
     * @author dengyi
     * @date 2019/11/13 14:16
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //如果需要改变认证的用户信息来源，我们可以实现UserDetailsService
        auth.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder);
    }

    /**
     * 这个方法主要配置规则
     * <br/>
     *
     * @param http
     * @return void
     * @author dengyi
     * @date 2019/11/13 14:24
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 由于使用的是JWT，我们这里不需要csrf
        http.csrf().disable();
        // 禁用缓存
        http.headers().cacheControl();
        // 基于token，所以不需要session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 配置没有访问权限处理类和匿名访问没有权限处理类
        http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
                .authenticationEntryPoint(customAuthenticationEntryPoint);
        //在验证用户名和密码前添加jwt处理
        http.addFilterBefore(customJwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http
                .authorizeRequests()
                //这里表示"/any"和"/ignore"不需要权限校验
                .antMatchers("/ignore/**", "/login", "/**/register/**").permitAll()
                .anyRequest().authenticated()
                // 这里表示任何请求都需要校验认证(上面配置的放行)
                .and()
                //配置登录,检测到用户未登录时跳转的url地址,登录放行
                .formLogin()
                //需要跟前端表单的action地址一致
                .loginProcessingUrl("/login")
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .permitAll();
        //配置登出,登出放行
        http.logout()
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .permitAll();


    }


}
