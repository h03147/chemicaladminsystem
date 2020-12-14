package com.cmkjspringboot.chemicalsystem.controller;

import com.cmkjspringboot.chemicalsystem.entity.Notification;
import com.cmkjspringboot.chemicalsystem.repository.NotificationRepository;
import com.cmkjspringboot.chemicalsystem.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 消息通知的控制层
@RestController
@RequestMapping("/Notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationRepository notificationRepository;

    @PostMapping("/savenotice")
    public Map<String, Object> saveNotice(@RequestBody ArrayList<Object> list1) {
        System.out.println("前端传来了添加通知列表：" + list1);
        Map<String, Object> map = new HashMap<>();
        map.put("status", "ok");
        return map;
    }

    // 查询消息通知
    @GetMapping("/findAllNotices")
    public Map<String, Object> findAllNotices() {
        Map<String, Object> map = new HashMap<>();
        // select * from tables where notice_status in (0,1) and expired_number >=0
        // select * from tables where notice_status in (0,1) and medicine_number >=0
        // 需要查询in里面的值用list装着传参
        List<Integer> Notice_statusIn = new ArrayList<>();
        Notice_statusIn.add(0);
        Notice_statusIn.add(1);
        List<Notification> findNotices_ListResult1 =notificationService.findByNotice_status(Notice_statusIn, 0, null);
        List<Notification> findNotices_ListResult2 =notificationService.findByNotice_status(Notice_statusIn, null, 0);
        //判断消息是否全部一已读
        boolean All_read1 = false;
        boolean All_read2 = false;
        for(int noti_x1 = 0; noti_x1 < findNotices_ListResult1.size(); noti_x1++ )
        {
            if(findNotices_ListResult1.get(noti_x1).getNotice_status() == 0)
            {
                All_read1 = true;
            }
        }
        for(int noti_x2 = 0; noti_x2 < findNotices_ListResult2.size(); noti_x2++ )
        {
            if(findNotices_ListResult2.get(noti_x2).getNotice_status() == 0)
            {
                All_read2 = true;
            }
        }
        map.put("All_read1", All_read1);
        map.put("All_read2", All_read2);
        map.put("findNotices_ListResult1", findNotices_ListResult1);
        map.put("findNotices_ListResult2", findNotices_ListResult2);
        return map;
    }

    // 删除消息
    @PostMapping("deleteNotice")
    public Map<String, Object> deleteNotice(@RequestBody Notification notification) {
        System.out.println("前端传来删除通知的消息为：" + notification);
        Map<String, Object> map = new HashMap<>();
        if(notification != null)
        {
            try{
                notification.setNotice_status(2);
                Notification delete_Result1 = notificationRepository.save(notification);
//                System.out.println("删除操作结果：" + delete_Result1);
                if(delete_Result1 != null)
                {
                    map.put("delete_notice_status", "ok");
                }
            } catch (Exception e)
            {
                map.put("delete_notice_status", "throw");
            }
        }else
        {
            map.put("delete_notice_status", "null");
        }
        return map;
    }

    // 消息已读
    @PostMapping("isreadNotice")
    public Map<String, Object> isreadNotice(@RequestBody Notification notification) {
        System.out.println("前端传来已读的通知消息为：" + notification);
        Map<String, Object> map = new HashMap<>();
        if(notification != null)
        {
            try{
                notification.setNotice_status(1);
                Notification delete_Result1 = notificationRepository.save(notification);
//                System.out.println("删除操作结果：" + delete_Result1);
                if(delete_Result1 != null)
                {
                    map.put("read_notice_status", "ok");
                }
            } catch (Exception e)
            {
                map.put("read_notice_status", "throw");
            }
        }else
        {
            map.put("read_notice_status", "null");
        }
        return map;
    }

    // 登录到首页后的小铃铛上面查询未读总消息数
    @GetMapping("/findAllUnreadNotice")
    public Map<String, Integer> findAllUnreadNotice() {
        Map<String, Integer> map = new HashMap<>();
        try {
            // 需要查询in里面的值用list装着传参
            // select * from tables where notice_status in (0)
            List<Integer> Notice_statusIn_All_zero = new ArrayList<>();
            Notice_statusIn_All_zero.add(0);
            List<Notification> findAll_Unread_Notices_ListResult1 = notificationService.findByNotice_status(Notice_statusIn_All_zero, null, null);
//            int i = 100 / 0;
            if(findAll_Unread_Notices_ListResult1.size() != 0)
            {
                map.put("AllUnreadNotice_Result", findAll_Unread_Notices_ListResult1.size());
            }else
            {
                map.put("AllUnreadNotice_Result", null);
            }

        } catch (Exception e)
        {
            map.put("AllUnreadNotice_Result", -2);
            map.put("throw Exception", -1);
        } finally {
            map.put("Status_Code", 200);
        }
        return map;
    }
}
