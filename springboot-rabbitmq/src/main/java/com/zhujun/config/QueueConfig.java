package com.zhujun.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * className: QueueConfig
 * create by: zhujun
 * description: 队列、交换机、绑定关系配置类
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

    /*--------------------延时队列----------------------------------------------------------------------------------------------------------*/
    //缓存交换机与缓存队列  缓存队列 指定消息过期转发的死信交换机、与消息的Routing Key
    /**
     * Cache Queue
     */
    @Bean
    public Queue getCacheQueue(){
        String DEAD_ORDER_EXCHANGE_NAME = "dead_exchange";
        String DEAD_ORDER_ROUTING_KEY = "dead_order";
        //参数列表
        Map<String, Object> params = new HashMap<>();
        // x-dead-letter-exchange 声明了队列里的死信转发到的Exhchange名称，
        params.put("x-dead-letter-exchange", DEAD_ORDER_EXCHANGE_NAME);
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        params.put("x-dead-letter-routing-key", DEAD_ORDER_ROUTING_KEY);
        return new Queue("cache_queue",true, false, false, params);
    }
    /**
     * Cache Direct Exchange
     */
    @Bean
    public DirectExchange getCacheExchange(){
        return  new DirectExchange("cache_exchange");
    }

    /**
     * Cache Queue、Exchange Bind
     */
    @Bean
    public  Binding cacheBind(){
        return BindingBuilder.bind(getCacheQueue()).to(getCacheExchange()).with("cache_msg");
    }


    //死信队列与死信交换机 绑定时指定的Binding key为缓存队列转发来的消息的Routing Key
    /**
     * Dead Queue
     */
    @Bean
    public Queue getDeadQueue(){
        return new Queue("dead_queue");
    }
    /**
     * Dead Direct Exchange
     */
    @Bean
    public DirectExchange getDeadExchange(){
        return  new DirectExchange("dead_exchange");
    }
    /**
     * Dead Queue、Exchange Bind
     */
    @Bean
    public  Binding deadBind(){
        return BindingBuilder.bind(getDeadQueue()).to(getDeadExchange()).with("dead_order");
    }

}
