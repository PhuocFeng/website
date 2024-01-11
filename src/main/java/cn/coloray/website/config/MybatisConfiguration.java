package cn.coloray.website.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.coloray.website.mapper")
public class MybatisConfiguration {
}
