package com.cmkjspringboot.chemicalsystem.dao;

import com.cmkjspringboot.chemicalsystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    // 用户通过账号登录可复用该方法
    public User findByUsernumber(String usernumber);

}
