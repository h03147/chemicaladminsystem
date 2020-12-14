package com.cmkjspringboot.chemicalsystem.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

// 登录日志表
@Entity
@Data
public class Login_log {

    @Id
    private String id;
    private String login_number;
    private String login_name;
    private String login_date;
    private String user_role;


}
