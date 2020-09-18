package com.cmkjspringboot.chemicalsystem.service;

import com.cmkjspringboot.chemicalsystem.entity.User;

public interface UserService {
    //用户登录
    public User login(User user);
}
