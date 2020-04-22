package com.zhujun.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * Redis 实现的延时队列
 * 基于zset数据结构   value 为消息对象的序列化Json  Score为当前时间加上延时的时间
 * 使用zrem 确保消息只被一个消费者消费
 * zrangebyscore 取一条来尝试获取消息
 *
 * 待优化 zrange、zrem可能会使多个线程获取到消息 却在zrem时失败
 * 可以使用lua脚本 讲zrange、zrem在服务端作为原子性操作
 */

public class RedisDelayQueue<T> {
    static class Message<T> implements Serializable {
        private String id;
        private T msg;
        @Override
        public String toString() {
            return "Message{" +
                    "id='" + id + '\'' +
                    ", msg=" + msg +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public T getMsg() {
            return msg;
        }

        public void setMsg(T msg) {
            this.msg = msg;
        }
    }

    private RedisTemplate redisTemplate;//redis 连接
    private String queueKey;//消息队列key

    public RedisDelayQueue(RedisTemplate redisTemplate, String queueKey) {
        this.redisTemplate = redisTemplate;
        this.queueKey = queueKey;
    }

    public void delay(T msg){
        Message<T> message = new Message<>();
        message.id = UUID.randomUUID().toString();
        message.msg = msg;
        String messageString = JSON.toJSONString(message);
        System.out.println("message:"+messageString);
        this.redisTemplate.opsForZSet().add(queueKey,messageString,System.currentTimeMillis()+5000);
    }


    public void loop(){
        //线程被中断
        while(!Thread.interrupted()){
            //只取一条
            Set<DefaultTypedTuple> set = this.redisTemplate.opsForZSet().rangeByScoreWithScores(queueKey,0,System.currentTimeMillis(),0,1);
            if(set.isEmpty()){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            String value = (String)(set.iterator().next().getValue());
            if(this.redisTemplate.opsForZSet().remove(queueKey,value)>0){
                //获得到了消息
                Message<T> message = JSON.parseObject(value, new TypeReference<Message>() {});
                System.out.println("getMessage:"+message);
                return;
            }
        }
    }
}
