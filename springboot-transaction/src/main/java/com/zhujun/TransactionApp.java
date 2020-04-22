package com.zhujun;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhujun.dao")
public class TransactionApp
{
    public static void main(String[] args) {
        SpringApplication.run(TransactionApp.class,args);
    }
}
