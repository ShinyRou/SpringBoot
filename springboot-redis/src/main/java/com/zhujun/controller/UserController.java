package com.zhujun.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhujun.entity.User;
import com.zhujun.util.RedisDelayQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController{

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @RequestMapping("/login")
    public String login(User user){
        String userString = JSON.toJSONString(user);
        System.out.println(userString);
        redisTemplate.opsForValue().set("session"+user.getName(), userString);
        return  redisTemplate.opsForValue().get("session"+user.getName());
    }

    @RequestMapping("/delayQueue")
    public String delayQueue(User user){
        RedisDelayQueue<User> delayQueue = new RedisDelayQueue<User>(this.redisTemplate,"user");
        delayQueue.delay(user);
        delayQueue.loop();
        return "ok";
    }
}
