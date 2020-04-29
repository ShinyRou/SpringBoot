package com.zhujun.controller;


import com.zhujun.config.PropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 通过controller @value获取apollo配置
 * 并且验证实时刷新配置
 *
 * 通过控制台输出验证 Bean形式 是否 自动同步
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @Value("${test.username:zhayujie}")
    private String username;

    @Value("${test.age:25}")
    private Integer age;

    @Autowired
    private PropertiesConfig config;

    @GetMapping("/getConfig")
    public String getConfig(){
        System.out.println("@ConfigurationProperties:"+config.getAge());
        System.out.println("@Value:"+username+age);
        return username+"-----"+age;
    }
}
