package com.zhujun.controller;


import com.zhujun.entity.User;
import com.zhujun.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/addUser")
    public String addUser(@RequestBody User user){
        for(int i=0;i<10000;i++){
            user.setNo(user.getNo()+1);
            user.setAge(user.getAge()+1);
            userRepository.save(user);
        }
        return "add user success";
    }


    @RequestMapping("/findUser")
    public User findUser(String username){
       return userRepository.findUserByName(username);
    }

    @RequestMapping("/test")
    public String test(){
        return "hello jpa";
    }

}
