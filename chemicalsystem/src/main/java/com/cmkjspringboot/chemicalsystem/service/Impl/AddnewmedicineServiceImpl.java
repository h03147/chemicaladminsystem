package com.cmkjspringboot.chemicalsystem.service.Impl;

import com.cmkjspringboot.chemicalsystem.entity.Addnewmedicine;
import com.cmkjspringboot.chemicalsystem.repository.AddnewmedicineRepository;
import com.cmkjspringboot.chemicalsystem.service.AddnewmedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AddnewmedicineServiceImpl implements AddnewmedicineService {

    @Autowired
    private AddnewmedicineRepository addnewmedicineRepository;

    @Override
    public List<Addnewmedicine> findByCondition(String medicinename, String medicinebox) {
        return addnewmedicineRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();
//            System.out.println("多条件传来了详情药柜的药品名称和药品柜查询：" + medicinename + "$$$$$" + medicinebox);

            // 药品名称是否存在
            if(!StringUtils.isEmpty(medicinename)) {
                predicates.add(criteriaBuilder.equal(root.get("medicinename"), medicinename));
            }

            // 药品柜是否存在
            if(!StringUtils.isEmpty(medicinebox)) {
                predicates.add(criteriaBuilder.equal(root.get("medicinebox"), medicinebox));
            }

//            System.out.println("根据药瓶名称和药品柜查询的结果：" + criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction());
            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();

        });
    }

    @Override
    public List<Addnewmedicine> findByConditionthree(String medicinename, String medicinebox, String expiredtime) {
        return addnewmedicineRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();
//            System.out.println("多条件findByConditionthree传来了详情药柜的药品名称和药品柜查询：" + medicinename + "$$$$$" + medicinebox + "$$$$$" + expiredtime);

            // 药品名称是否存在
            if(!StringUtils.isEmpty(medicinename)) {
                predicates.add(criteriaBuilder.equal(root.get("medicinename"), medicinename));
            }

            // 药品柜是否存在
            if(!StringUtils.isEmpty(medicinebox)) {
                predicates.add(criteriaBuilder.equal(root.get("medicinebox"), medicinebox));
            }

            // 药品过期日期是否存在
            if(!StringUtils.isEmpty(expiredtime)) {
                predicates.add(criteriaBuilder.equal(root.get("expiredtime"), expiredtime));
            }

//            System.out.println("根据药瓶名称和药品柜查询的结果：" + criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction());
            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();

        });
    }

    @Override
    public List<Addnewmedicine> findByStorageTime(String storage_time) {
        return addnewmedicineRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            // 判断存储时间是否存在
            if(!StringUtils.isEmpty(storage_time))
            {
                predicates.add(criteriaBuilder.like(root.get("storagetime"), storage_time + "%"));
            }

            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();

        });
    }
}
