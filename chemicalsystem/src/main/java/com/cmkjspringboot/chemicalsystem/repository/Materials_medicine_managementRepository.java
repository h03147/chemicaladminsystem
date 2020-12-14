package com.cmkjspringboot.chemicalsystem.repository;

import com.cmkjspringboot.chemicalsystem.entity.Materials_medicine_management;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.util.List;

public interface Materials_medicine_managementRepository extends JpaRepository<Materials_medicine_management, Integer>, JpaSpecificationExecutor {

}
