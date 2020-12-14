package com.cmkjspringboot.chemicalsystem.service;

import com.cmkjspringboot.chemicalsystem.entity.User;

import java.util.List;

public interface UserService {
    //用户登录
    public User login(User user);

    // 根据用户名查找需要的对应用户信息
    public List<User> Loginandfindone(User user);

    // 创造登录的token验证
    public String createLoginToken(User user);
}
