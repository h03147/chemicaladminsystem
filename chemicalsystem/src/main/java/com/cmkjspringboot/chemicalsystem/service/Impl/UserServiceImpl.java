package com.cmkjspringboot.chemicalsystem.service.Impl;

import com.cmkjspringboot.chemicalsystem.dao.UserDao;
import com.cmkjspringboot.chemicalsystem.entity.User;
import com.cmkjspringboot.chemicalsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User login(User user) {
        //1.根据前端输入的学生学号，判断用户是否已存在
        User userByUsernumber = userDao.findByUsernumber(user.getUsernumber());
        //或 if (!ObjectUtils.isEmpty(userByName))  对象不为空
        if (userByUsernumber!=null){
            //用户存在，比对密码
            // matches方法中第一个参数是前端传过来的密码 第二个参数是数据库查询的对应密码，进行比对返回Boolean类型
//            if (userByUsernumber.getPassword().equals(user.getPassword())){
            if(bCryptPasswordEncoder.matches(user.getPassword(), userByUsernumber.getPassword())) {
                return userByUsernumber;
            }else {
                throw new RuntimeException("密码不正确！");
            }
        }else {
            throw new RuntimeException("账号不存在！");
        }
    }
}
