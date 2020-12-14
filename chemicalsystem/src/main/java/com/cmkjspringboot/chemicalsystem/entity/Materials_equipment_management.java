package com.cmkjspringboot.chemicalsystem.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Materials_equipment_management {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String equipment_name;
    private String equipment_type;
    private Integer equipment_number;
    private String equipment_position;
}
