package com.zhujun.entity;

import java.io.Serializable;

/**
 * (SysUser)实体类
 *
 * @author makejava
 * @since 2020-02-24 20:57:34
 */
public class SysUser implements Serializable {
    private static final long serialVersionUID = 423532791880595993L;
    
    private Integer no;
    
    private String age;
    
    private String gender;
    
    private String name;


    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "no=" + no +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}