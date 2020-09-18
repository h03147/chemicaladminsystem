package com.cmkjspringboot.chemicalsystem.repository;

import com.cmkjspringboot.chemicalsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
