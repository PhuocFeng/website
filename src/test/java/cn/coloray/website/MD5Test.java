package cn.coloray.website;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@Slf4j
@SpringBootTest
public class MD5Test {
    @Test
    void md5Test(){
        String password = "123456";
        String md5PWD = DigestUtils.md5DigestAsHex(password.getBytes());
        log.info("原密码为{}",password);
        log.info("MD5加密后为{}",md5PWD);
        log.info(password+md5PWD);
    }
}
