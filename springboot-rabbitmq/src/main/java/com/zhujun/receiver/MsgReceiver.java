package com.zhujun.receiver;

import com.zhujun.RabbitMQApp;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * className: MsgReceiver
 * create by: zhujun
 * description: TODO
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
}
