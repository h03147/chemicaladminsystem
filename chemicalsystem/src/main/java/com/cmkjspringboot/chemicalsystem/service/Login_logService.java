package com.cmkjspringboot.chemicalsystem.service;

import com.cmkjspringboot.chemicalsystem.entity.Login_log;

import java.util.List;


public interface Login_logService {
    // 单条件模糊查询出库如期为某一天的所有出库记录并按日期倒序排列
    public List<Login_log> findByToday_time(String login_date);

    // 根据日期早晚逆序排序
    public List<Login_log> SortBylogin_date(List<Login_log> list1);
}
