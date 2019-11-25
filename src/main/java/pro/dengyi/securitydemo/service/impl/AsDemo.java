package pro.dengyi.securitydemo.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author 邓艺
 * @version v1.0
 * @Title
 * @Description
 * @date 2019/11/25 17:44
 **/
@Component
public class AsDemo {
    @Async
    public void say() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("hai");
    }
}
