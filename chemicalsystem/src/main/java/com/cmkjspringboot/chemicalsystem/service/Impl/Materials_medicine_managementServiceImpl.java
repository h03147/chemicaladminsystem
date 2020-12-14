package com.cmkjspringboot.chemicalsystem.service.Impl;

import com.cmkjspringboot.chemicalsystem.entity.Materials_medicine_management;
import com.cmkjspringboot.chemicalsystem.repository.Materials_medicine_managementRepository;
import com.cmkjspringboot.chemicalsystem.service.Materials_medicine_managementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class Materials_medicine_managementServiceImpl implements Materials_medicine_managementService {

    @Autowired
    private Materials_medicine_managementRepository materials_medicine_managementRepository;

    // 根据药瓶名称和药品柜查询是否存在实现类
    @Override
    public List<Materials_medicine_management> findByCondition(String medicine_name, String medicine_box) {
        return materials_medicine_managementRepository.findAll((root,  criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();
//            System.out.println("多条件传来了药品名称和药品柜位置：" + medicine_name + "$$$$$" + medicine_box);

            // 药品名称是否存在
            if(!StringUtils.isEmpty(medicine_name)) {
                predicates.add(criteriaBuilder.equal(root.get("medicine_name"), medicine_name));
            }

            // 药品柜是否存在
            if(!StringUtils.isEmpty(medicine_box)) {
                predicates.add(criteriaBuilder.equal(root.get("medicine_box"), medicine_box));
            }

//            System.out.println("根据药瓶名称和药品柜查询的结果：" + criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction());
            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();

        });
    }
}
