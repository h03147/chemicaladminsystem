package com.cmkjspringboot.chemicalsystem.repository;

import com.cmkjspringboot.chemicalsystem.entity.Materials_equipment_management;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface Materials_equipment_managementRepository extends JpaRepository<Materials_equipment_management, Integer>, JpaSpecificationExecutor {
}
