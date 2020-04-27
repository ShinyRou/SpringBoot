package com.zhujun.controller;


import com.zhujun.dao.UserDao;
import com.zhujun.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping("/add")
    public User add(User user){
        User respUser = userDao.save(user);
        System.out.println(respUser);
        return  respUser;
    }

    @RequestMapping("/query")
    public User query(Integer id)
    {
        Optional<User> user = userDao.findById(id);
        return  user.orElse(new User());
    }
}
