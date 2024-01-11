package cn.coloray.website.config;


import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
//在使用Validation框架检查请求参数格式时，可以将其配置为“快速失败”，即：当检查到第1个错误时，就不再继续检查请求参数的格式了，而是直接反馈错误结果！
@Configuration
public class ValidationConfiguration {

    @Bean
    public javax.validation.Validator validator() {
        return Validation.byProvider(HibernateValidator.class)
                .configure() // 开始配置
                .failFast(true) // 配置快速失败
                .buildValidatorFactory() // 构建Validator工厂
                .getValidator(); // 从Validator工厂中获取Validator对象
    }

}
