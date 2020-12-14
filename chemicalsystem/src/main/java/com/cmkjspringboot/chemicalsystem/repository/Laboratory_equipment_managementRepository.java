package com.cmkjspringboot.chemicalsystem.repository;

import com.cmkjspringboot.chemicalsystem.entity.Laboratory_equipment_management;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface Laboratory_equipment_managementRepository extends JpaRepository<Laboratory_equipment_management, Integer>, JpaSpecificationExecutor {
}
