package com.zhujun.repository;

import com.zhujun.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.HashMap;

/**
 * @Description
 * @auther ZhuJun
 * @create 2019-05-19 23:17
 */


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
