package pro.dengyi.securitydemo.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 响应工具类
 *
 * @author 邓艺
 * @version v1.0
 * @Title 响应工具类
 * @Description
 * @date 2019/11/13 14:49
 **/
@Slf4j
public class ResponseUtil {

    public static void out(Object content, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        String s = JSON.toJSONString(content);
        try {
            response.getWriter().write(s);
        } catch (IOException e) {
            log.error("response响应数据失败:{}", e.getMessage());
        }
    }
}
