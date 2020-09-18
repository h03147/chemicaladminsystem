package com.cmkjspringboot.chemicalsystem.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id字段自增数据类型和数据库属性绑定
    private Integer id;
    private String username;
    private String usernumber;
    private String password;
    private String userrole;
}
