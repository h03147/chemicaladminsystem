package com.cmkjspringboot.chemicalsystem.dao;

import com.cmkjspringboot.chemicalsystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    // 用户通过账号登录可复用该方法
    public User findByUsernumber(String usernumber);

    // 根据登录账号查出登录名和登录权限
    public List<User> findByLoginUsernumber(String usernumber);

}
