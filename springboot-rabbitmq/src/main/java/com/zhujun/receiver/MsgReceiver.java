package com.zhujun.receiver;


import com.rabbitmq.client.Channel;
import com.zhujun.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * className: MsgReceiver
 * create by: zhujun
 * description: 消息接收
 * create time: 2019/5/20 15:24
 */

@Component

public class MsgReceiver {
    private final Logger log = LoggerFactory.getLogger(MsgReceiver.class);
   @RabbitListener(queues ="queue")
   @RabbitHandler
   public void process(String msg){
       log.info("queue: queue----------receive msg:"+msg);
   }

    @RabbitListener(queues ="topic_queue")
    @RabbitHandler
    public void topicMsg(String msg){
        log.info("queue: topic_queue----------receive msg:"+msg);
    }


    @RabbitListener(queues ="fanout_queue1")
    @RabbitHandler
    public void fanoutMsg1(String msg){
        log.info("queue: fanout_queue1----------receive msg:"+msg);
    }


    @RabbitListener(queues ="fanout_queue2")
    @RabbitHandler
    public void fanoutMsg2(String msg){
        log.info("queue: fanout_queue2----------receive msg:"+msg);
    }

    //输出后消息视为已处理，不会超时到死信队列
    /*@RabbitListener(queues ="cache_queue")
    @RabbitHandler
    public void cacheMsg(Order order){
        order.setOrderStatus("执行中");
        order.setPayStatus("等待支付");
        log.info("【订单进入缓存队列】cache_queue" + new Date().toString() +"【1分钟内未支付即为过期】" + order.toString() );
    }*/

    @RabbitListener(queues ="dead_queue")
    @RabbitHandler
    public void deadMsg(Order order, Channel channel, Message message) throws IOException {
       order.setOrderStatus("已过期");
       order.setPayStatus("未支付");
       log.info("【订单进入死信队列】dead_queue" + new Date().toString() +"【已过期】" + order.toString() );
       channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }


}
