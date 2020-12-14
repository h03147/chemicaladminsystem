package com.cmkjspringboot.chemicalsystem.repository;

import com.cmkjspringboot.chemicalsystem.entity.Addnewmedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AddnewmedicineRepository extends JpaRepository<Addnewmedicine, Integer>, JpaSpecificationExecutor {

}
