package cn.coloray.website.mapper;

import cn.coloray.website.pojo.vo.AdminLogInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class AdminMapperTests {
    @Autowired
    AdminMapper adminMapper;

    @Test
    void getUserByUsernameTest(){
        String username = "admin";
        AdminLogInfoVO admin = adminMapper.getUserByName(username);
        log.info("查询到的用户信息为：user{}",admin);

    }
}
