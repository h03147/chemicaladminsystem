package com.cmkjspringboot.chemicalsystem.controller;

import com.cmkjspringboot.chemicalsystem.entity.Login_log;
import com.cmkjspringboot.chemicalsystem.entity.User;
import com.cmkjspringboot.chemicalsystem.repository.Login_logRepository;
import com.cmkjspringboot.chemicalsystem.service.Login_logService;
import com.cmkjspringboot.chemicalsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.data.domain.Sort.by;

@RestController
@RequestMapping("Loginlog")
public class Login_logController {

    @Autowired
    private UserService userService;

    @Autowired
    private Login_logService login_logService;

    @Autowired
    private Login_logRepository login_logRepository;

    @PostMapping("/saveOneHistory")
    public Map<String, Object> saveOneHistory(@RequestBody User user) throws ParseException {
        System.out.println("捕获一条需要记录的登录日志：" + user);
        Map<String, Object> map = new HashMap<>();
        try {
            if(!user.getUsernumber().isEmpty())
            {
                List<User> findByusernumber_ResultList1 = userService.Loginandfindone(user);
                System.out.println("查询结果为：" + findByusernumber_ResultList1);
                String id = null;
                String login_number = null;
                String login_name = null;
                String login_date = null;
                String user_role = null;
                UUID uuidOne = UUID.randomUUID();
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date nowdate = new Date();
                id = String.valueOf(uuidOne);
                login_number = user.getUsernumber();
                login_date = sdf1.format(nowdate);
                login_name = findByusernumber_ResultList1.get(0).getUsername();
                user_role = findByusernumber_ResultList1.get(0).getUserrole();
                Login_log login_log1 = new Login_log();
                login_log1.setId(id);
                login_log1.setLogin_number(login_number);
                login_log1.setLogin_name(login_name);
                login_log1.setLogin_date(login_date);
                login_log1.setUser_role(user_role);
                login_logRepository.save(login_log1);
                //            System.out.println("随机生成的id：" + id);
//            System.out.println("当前时间为：" + login_date);
//            System.out.println("用户名为：" + login_name + "权限为：" + user_role);
                map.put("record", "success");
                map.put("log status", true);
            }
        } catch (Exception e) {
            map.put("record", "failed");
            map.put("log status", "throw");
        }
        return map;
    }

    // 根据当天的日期返回这一天的所有登录日志
    @GetMapping("/findTodayLoginlog")
    public Map<String, Object> findTodayLoginlog() {
        System.out.println("前端的登录日志查询请求！");
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String todayDate = sdf1.format(today);
        List<Login_log> TodayLogin_log_ListResult1 = login_logService.findByToday_time(todayDate);
        System.out.println("今天的登录记录未排序时如下：" + TodayLogin_log_ListResult1);
        List<Login_log> TodayLogin_log_ListResult2 = login_logService.SortBylogin_date(TodayLogin_log_ListResult1);
        System.out.println("今天的登录记录按登录日期排序后如下：" + TodayLogin_log_ListResult2);
        map.put("TodayLogin_log_ListResult1", TodayLogin_log_ListResult1);
        map.put("TodayLogin_log_ListResult2", TodayLogin_log_ListResult2);
        return map;
    }
}
