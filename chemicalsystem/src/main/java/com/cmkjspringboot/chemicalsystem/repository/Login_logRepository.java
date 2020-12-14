package com.cmkjspringboot.chemicalsystem.repository;

import com.cmkjspringboot.chemicalsystem.entity.Login_log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface Login_logRepository extends JpaRepository<Login_log, Integer>, JpaSpecificationExecutor {
}
