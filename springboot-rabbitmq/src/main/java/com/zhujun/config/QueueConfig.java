package com.zhujun.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Map;

/**
 * className: QueueConfig
 * create by: zhujun
 * description: TODO
 * create time: 2019/5/20 15:14
 */

@Configuration
public class QueueConfig {

    /**
     *Direct
     */
    @Bean
    public Queue getQueue(){
        return new Queue("queue");
    }


    /**
     *Topic
     */
    @Bean
    public TopicExchange getExchange(){
        return new TopicExchange("TOPIC_EXCHANGE") ;
    }

    @Bean
    public Queue getTopicQueue(){
        return new Queue("topic_queue");
    }

    @Bean
    public Binding getBinding(){
        return BindingBuilder.bind(getTopicQueue()).to(getExchange()).with("topic.#");
    }


    /**
     * FanOut 广播模式
     */
    @Bean
    public FanoutExchange getFanoutExchange(){
        return new FanoutExchange("FANOUT_EXCHANGE") ;
    }

    @Bean
    public Queue getFanoutQueue1(){
        return new Queue("fanout_queue1");
    }

    @Bean
    public Queue getFanoutQueue2(){
        return new Queue("fanout_queue2");
    }
    @Bean
    public Binding getFanoutBinding1(){
        return BindingBuilder.bind(getFanoutQueue1()).to(getFanoutExchange());
    }

    @Bean
    public Binding getFanoutBinding2(){
        return BindingBuilder.bind(getFanoutQueue2()).to(getFanoutExchange());
    }
}
