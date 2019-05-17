package com.zhujun.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * className: HelloController
 * create by: zhujun
 * description: 简单测试Controller
 * create time: 2019/5/17 14:56
 */

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String sayHello(@RequestParam("name")String name){
        return "hello"+name;
    }
}
