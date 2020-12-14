package com.cmkjspringboot.chemicalsystem.service.Impl;

import com.cmkjspringboot.chemicalsystem.entity.Materials_equipment_management;
import com.cmkjspringboot.chemicalsystem.repository.Materials_equipment_managementRepository;
import com.cmkjspringboot.chemicalsystem.service.Materials_equipment_managementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class Materials_equipment_managementServiceImpl implements Materials_equipment_managementService {

    @Autowired
    private Materials_equipment_managementRepository materials_equipment_managementRepository;

    @Override
    public List<Materials_equipment_management> findByEquipment_name_and_Equipment_position(String equipment_name, String equipment_position) {
        return materials_equipment_managementRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            //装备名称不为空
            if(!StringUtils.isEmpty(equipment_name))
            {
                predicates.add(criteriaBuilder.equal(root.get("equipment_name"), equipment_name));
            }

            //设备柜位置不为空
            if(!StringUtils.isEmpty(equipment_position))
            {
                predicates.add(criteriaBuilder.equal(root.get("equipment_position"), equipment_position));
            }

            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        });
    }
}
