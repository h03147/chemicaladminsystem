package com.cmkjspringboot.chemicalsystem.repository;

import com.cmkjspringboot.chemicalsystem.entity.Export_equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface Export_equipmentRepository extends JpaRepository<Export_equipment, Integer>, JpaSpecificationExecutor {
}
