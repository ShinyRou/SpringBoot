package com.zhujun.controller;

import com.zhujun.RabbitMQApp;
import com.zhujun.entity.Order;
import com.zhujun.sender.MsgSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * className: TestController
 * create by: zhujun
 * description: TODO
 * create time: 2019/5/21 16:28
 */
@RestController
@PropertySource("classpath:zhujun.yml")
public class TestController {

    @Autowired
    private MsgSender sender;

    @Value("${name}")
    private String name;


    @Value("${age}")
    private String age;

    private final Logger log = LoggerFactory.getLogger(RabbitMQApp.class);

    @RequestMapping("/test")
    public String test(){
        for(int i=0;i<3;i++){
            /*sender.sendMsg("queue","msg"+i);
            sender.sendMsg("TOPIC_EXCHANGE","topic.msg","msg"+i);
            //因为fanout 交换器不处理路由键，只是简单的将队列绑定到交换器上，
            // 每个发送到交换器的消息都会被转发到与该交换器绑定的所有队列上
            sender.sendMsg("FANOUT_EXCHANGE","","msg"+i);*/
            Order order = new Order(String.valueOf(i),"进行中","等待付款");
            sender.sendMsg(order);
        }
        log.info("信息发送完成");
        return "hello "+name+age;
    }
}
