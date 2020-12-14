package com.cmkjspringboot.chemicalsystem.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class System_setting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer medicine_expired_warning;
    private Integer medicine_number_warning;
    private Integer equipment_number_warning;
}
