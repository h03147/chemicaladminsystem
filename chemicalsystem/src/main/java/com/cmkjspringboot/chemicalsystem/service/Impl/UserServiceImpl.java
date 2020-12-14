package com.cmkjspringboot.chemicalsystem.service.Impl;

import com.cmkjspringboot.chemicalsystem.dao.UserDao;
import com.cmkjspringboot.chemicalsystem.entity.User;
import com.cmkjspringboot.chemicalsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
                if(userByUsernumber.getUserrole().equals(user.getUserrole()))
                {
                    return userByUsernumber;
                }else {
                    // throw new RuntimeException("用户对应权限不匹配！");
                    throw new RuntimeException("账号或密码或权限不匹配！");
                }
            }else {
//                throw new RuntimeException("密码不正确！");
                throw new RuntimeException("账号或密码或权限不匹配！");
            }
        }else {
//            throw new RuntimeException("账号不存在！");
            throw new RuntimeException("账号或密码或权限不匹配！");
        }
    }


    // 根据用户登录账号查询后回传对应的用户名和用户权限
    @Override
    public List<User> Loginandfindone(User user) {
        List<User> findByUserNumber_ListResult1 = userDao.findByLoginUsernumber(user.getUsernumber());
        return findByUserNumber_ListResult1;
    }


    // 登录token生成算法
    @Override
    public String createLoginToken(User user) {

        // 生成一个随机uuid
        UUID uuidOne = UUID.randomUUID();
        String getUuidOne = String.valueOf(uuidOne);
        // 获取当前系统时间的时间戳
        Date Timestamp = new Date();
        String getTimestamp = String.valueOf(Timestamp.getTime());
        // 获取当前账号
        String getUsernumber = user.getUsernumber();
        // 获取当前账号的权限
        String getUserrole = user.getUserrole();
        // 获取一个16位随机英文字符串
        String getRandomEnglish16 = CreateRandomEnglish();
        // 拼接后的token
        String origin_token = getUuidOne + getTimestamp + getUsernumber + getUserrole + getRandomEnglish16;
        System.out.println("拼接的字符串为:" + getUuidOne + "拼接" +  getTimestamp + "拼接"  + getUsernumber + "拼接"  + getUserrole + "拼接"  + getRandomEnglish16);
        String after_BCrypt = bCryptPasswordEncoder.encode(origin_token);
        System.out.println("BCrypt加密后字符串位:" + after_BCrypt);

        return after_BCrypt;
    }

    // 产生16个随机英文字母
    private String CreateRandomEnglish() {
        String string = "";

        for(int i = 0; i < 16; i++)
        {
            char c = (char) ((Math.random() * 26) + 97);
            string += (c + "");
        }
        return string;
    }
}
