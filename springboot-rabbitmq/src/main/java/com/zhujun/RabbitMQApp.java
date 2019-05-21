package com.zhujun;


import com.zhujun.entity.Order;
import com.zhujun.sender.MsgSender;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.PropertySource;

/**
 * className: RabbitMQApp
 * create by: zhujun
 * description: 启动类 启动时运行方法
 * 1. implements ApplicationRunner
 * 2. SpringApplicationBuilder 构造器模式
 * 3. 重写run方法
 * create time: 2019/5/20 15:28
 */

@SpringBootApplication
public class RabbitMQApp{
    public static void main(String[] args) {
        SpringApplication.run(RabbitMQApp.class,args);
    }
}
