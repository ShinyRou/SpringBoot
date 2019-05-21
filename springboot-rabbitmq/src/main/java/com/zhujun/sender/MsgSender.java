package com.zhujun.sender;

import com.zhujun.entity.Order;
import com.zhujun.receiver.MsgReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * className: MsgSender
 * create by: zhujun
 * description: 消息发送
 * create time: 2019/5/20 15:23
 */
@Component
public class MsgSender {

    private final Logger log = LoggerFactory.getLogger(MsgSender.class);

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

    public void sendMsg(Order order){
        log.info("【订单生成时间】" + new Date().toString() +"【1分钟后检查订单是否已经支付】" + order.toString() );
        this.amqpTemplate.convertAndSend("cache_exchange", "cache_msg", order, message -> {
            // 如果配置了 params.put("x-message-ttl", 5 * 1000); 那么这一句也可以省略,具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送自己控制时间
            message.getMessageProperties().setExpiration(1 * 1000 * 10 + "");
            return message;
        });
    }



}
