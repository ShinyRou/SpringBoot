package com.zhujun.model;


import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.lang.annotation.Documented;


@Data
@Document(indexName = "users", type = "_doc")
public class User {
    private int id;
    private String username;
    private String password;
    private int age;

    /** getter and setter */
}