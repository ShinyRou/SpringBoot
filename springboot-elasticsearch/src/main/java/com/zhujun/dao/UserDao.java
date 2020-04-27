package com.zhujun.dao;

import com.zhujun.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface UserDao extends ElasticsearchRepository<User, Integer> {
}
