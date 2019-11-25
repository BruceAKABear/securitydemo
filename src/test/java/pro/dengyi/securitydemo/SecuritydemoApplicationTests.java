package pro.dengyi.securitydemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pro.dengyi.securitydemo.service.impl.AsDemo;

@SpringBootTest
class SecuritydemoApplicationTests {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AsDemo asDemo;

    @Test
    void contextLoads() {
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
        asDemo.say();
        System.out.println("in main");

    }


}
