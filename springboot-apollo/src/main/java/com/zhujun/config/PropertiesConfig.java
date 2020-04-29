package com.zhujun.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * 通过ConfigurationProperties注解 将配置注入到bean中
 * 注入PropertiesConfig 来获取配置
 * 但是不会自动同步配置 需要通过listener
 */
@Data
@ConfigurationProperties(prefix = "test")
@Component
public class PropertiesConfig {

    @Value("${age:10}")
    private Integer age;
}
