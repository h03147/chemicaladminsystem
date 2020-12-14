package com.cmkjspringboot.chemicalsystem.repository;

import com.cmkjspringboot.chemicalsystem.entity.Export_chemical_medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface Export_chemical_medicineRepository extends JpaRepository<Export_chemical_medicine, Integer>, JpaSpecificationExecutor {
}
