package com.zhujun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * className: AopDemoApp
 * create by: zhujun
 * description: TODO
 * create time: 2019/5/17 15:19
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class AopDemoApp {
    public static void main(String[] args) {
        SpringApplication.run(AopDemoApp.class,args);
    }
}
