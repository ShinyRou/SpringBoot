package com.zhujun.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * className: MsgSender
 * create by: zhujun
 * description: TODO
 * create time: 2019/5/20 15:23
 */
@Component
public class MsgSender {
    @Autowired
    public AmqpTemplate amqpTemplate;

    // 第一个参数：Queue名字
    // 第二个参数：要发送的内容
    public void sendMsg(String queueName,String msg){
        amqpTemplate.convertAndSend(queueName,msg);
    }


    // 第一个参数：TopicExchange名字
    // 第二个参数：Route-Key
    // 第三个参数：要发送的内容
    public void sendMsg(String exchangeName,String routingKey,String msg){
        amqpTemplate.convertAndSend(exchangeName,routingKey,msg);
    }
}
