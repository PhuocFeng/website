package cn.coloray.website;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
@Slf4j
public class BCryptTest {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void PasswordEncoderTest() {
        String password = "123456";
        for (int i = 0; i < 10; i++) {
            String encodePassword = passwordEncoder.encode(password);
            log.info(encodePassword);
        }
    }

    @Test
    void matches() {
        String rawPassword = "123456";
        String encodedPassword = "$2a$10$osWYVZT3aBnG9h9ACiS8Mup3Hu4gjx1Lf46OIrjmseICX1cEPXZO6";
        boolean result = passwordEncoder.matches(rawPassword, encodedPassword);
        System.out.println("原文：" + rawPassword);
        System.out.println("密文：" + encodedPassword);
        System.out.println("匹配结果：" + result);
    }
}
