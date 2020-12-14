package com.cmkjspringboot.chemicalsystem.service.Impl;

import com.cmkjspringboot.chemicalsystem.entity.Laboratory_equipment_management;
import com.cmkjspringboot.chemicalsystem.repository.Laboratory_equipment_managementRepository;
import com.cmkjspringboot.chemicalsystem.service.Laboratory_equipment_managementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class Laboratory_equipment_managementServiceImpl implements Laboratory_equipment_managementService {

    @Autowired
    private Laboratory_equipment_managementRepository laboratory_equipment_managementRepository;

    @Override
    public List<Laboratory_equipment_management> findByLaboratoryEquip_name_and_Equip_position(String equipment_name, String equipment_position) {
        return laboratory_equipment_managementRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            // 设备名称是否存在
            if(!StringUtils.isEmpty(equipment_name))
            {
                predicates.add(criteriaBuilder.equal(root.get("equipment_name"), equipment_name));
            }

            // 设备柜位置是否存在
            if(!StringUtils.isEmpty(equipment_position))
            {
                predicates.add(criteriaBuilder.equal(root.get("equipment_position"), equipment_position));
            }

            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        });
    }
}
