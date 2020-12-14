package com.cmkjspringboot.chemicalsystem.service.Impl;

import com.cmkjspringboot.chemicalsystem.entity.Login_log;
import com.cmkjspringboot.chemicalsystem.repository.Login_logRepository;
import com.cmkjspringboot.chemicalsystem.service.Login_logService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class Login_logServiceImpl implements Login_logService {

    @Autowired
    private Login_logRepository login_logRepository;

    @Override
    public List<Login_log> findByToday_time(String login_date) {
        return login_logRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            // 如果传入日期条件非空
            if(!StringUtils.isEmpty(login_date))
            {
                predicates.add(criteriaBuilder.like(root.get("login_date"), login_date + "%"));
            }

            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        });
    }

    @Override
    public List<Login_log> SortBylogin_date(List<Login_log> list1) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 用双自指针法排序
        int point1 = 0;
        int point2 = 0;
        Login_log login_log1 = new Login_log();
        Login_log login_log2 = new Login_log();
        for (int i = 0; i < list1.size(); i++)
        {
            login_log1 = list1.get(i);
            point1 = i;
            for(int j = i + 1; j < list1.size(); j++)
            {
                login_log2 = list1.get(j);
                try {
                    if(sdf1.parse(login_log1.getLogin_date()).getTime() < sdf1.parse(login_log2.getLogin_date()).getTime())
                    {
                        login_log1 = login_log2;
                        point2 = j;
                    }

                } catch (Exception e) {
                    e.getMessage();
                }
            }
            list1.set(point2, list1.get(point1));
            list1.set(point1, login_log1);
        }
        return list1;
    }
}
