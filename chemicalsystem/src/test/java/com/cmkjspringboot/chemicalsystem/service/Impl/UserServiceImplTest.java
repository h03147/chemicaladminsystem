package com.cmkjspringboot.chemicalsystem.service.Impl;

import com.cmkjspringboot.chemicalsystem.entity.User;
import com.cmkjspringboot.chemicalsystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void login() {
        User user = new User();
        user.setUsernumber("abc001");
        user.setPassword("123456");
        System.out.println(userService.login(user));
    }

    @Test
    void testbCryptPasswordEncoder() {
        System.out.println(bCryptPasswordEncoder.encode("yejia888"));
    }


}