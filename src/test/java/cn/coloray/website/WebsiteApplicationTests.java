package cn.coloray.website;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class WebsiteApplicationTests {
    @Autowired
    DataSource dataSource;

    @Test
    void getConnection() throws SQLException {
        dataSource.getConnection();
    }

    @Test
    void contextLoads() {
    }



}
