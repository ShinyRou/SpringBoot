package com.zhujun.controller;

import com.zhujun.entity.User;
import com.zhujun.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @auther ZhuJun
 * @create 2019-05-19 23:23
 */
@Controller
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/getAlluser")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
}
