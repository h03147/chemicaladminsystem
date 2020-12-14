package com.cmkjspringboot.chemicalsystem.service.Impl;

import com.cmkjspringboot.chemicalsystem.entity.Notification;
import com.cmkjspringboot.chemicalsystem.repository.NotificationRepository;
import com.cmkjspringboot.chemicalsystem.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<Notification> findByNotificationCondition(String medicine_name, String medicine_box, String expired_time) {
        return notificationRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            // 药品名称是否存在
            if(!StringUtils.isEmpty(medicine_name)) {
                predicates.add(criteriaBuilder.equal(root.get("medicine_name"), medicine_name));
            }
            // 药柜名称是否存在
            if(!StringUtils.isEmpty(medicine_box)) {
                predicates.add(criteriaBuilder.equal(root.get("medicine_box"), medicine_box));
            }
            // 过期日期是否存在
            if(!StringUtils.isEmpty(expired_time)) {
                predicates.add(criteriaBuilder.equal(root.get("expired_time"), expired_time));
            }

            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        });
    }

    @Override
    public List<Notification> findByNotice_status(List<Integer> notice_status_List, Integer expired_number, Integer medicine_number) {
        return notificationRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();
            CriteriaBuilder.In<Integer> in = criteriaBuilder.in(root.get("notice_status")); // 数据库对应字段名
            if(null != notice_status_List && notice_status_List.size() > 0)
            {
                for(int notice_status : notice_status_List)
                {
                   in.value(notice_status); // 循环把notice_status_List所有的值都传进去
                }
            }
            predicates.add(in); // 拼接这个附加查询条件

            // 过期天数是否存在
            if(!StringUtils.isEmpty(expired_number)) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("expired_number"), expired_number));
            }

            // 库存数量是否存在
            if(!StringUtils.isEmpty(medicine_number)) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("medicine_number"), medicine_number));
            }

            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        });
    }
}
