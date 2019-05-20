package com.zhujun;


import com.zhujun.sender.MsgSender;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

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
public class RabbitMQApp  implements ApplicationRunner {

    @Autowired
    private MsgSender sender;

    private final Logger log = LoggerFactory.getLogger(RabbitMQApp.class);

    public static void main(String[] args) {
       /* SpringApplication.run(RabbitMQApp.class,args);*/
        new SpringApplicationBuilder()
                .sources(RabbitMQApp.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for(int i=0;i<10;i++){
            sender.sendMsg("queue","msg"+i);
            sender.sendMsg("TOPIC_EXCHANGE","topic.msg","msg"+i);
            //因为fanout 交换器不处理路由键，只是简单的将队列绑定到交换器上，
            // 每个发送到交换器的消息都会被转发到与该交换器绑定的所有队列上
            sender.sendMsg("FANOUT_EXCHANGE","","msg"+i);

        }
        log.info("信息发送完成");
    }

}
