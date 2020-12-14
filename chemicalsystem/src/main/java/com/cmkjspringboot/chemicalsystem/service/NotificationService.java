package com.cmkjspringboot.chemicalsystem.service;

import com.cmkjspringboot.chemicalsystem.entity.Notification;

import java.util.List;

public interface NotificationService {

    // 再插入新通知时检查该通知是否已经通知过了
    public List<Notification> findByNotificationCondition(String medicine_name, String medicine_box, String expired_time);

    // 根据消息状态值返回消息类型 where notice-status in(val1,val2,.....) and expired_number = ......复杂多条件查询
    public List<Notification> findByNotice_status(List<Integer> notice_status_List, Integer expired_number, Integer medicine_number);
}
