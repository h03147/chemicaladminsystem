package com.cmkjspringboot.chemicalsystem.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Export_equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String export_time;
    private String equipment_name;
    private String equipment_type;
    private Integer equipment_number;
    private String equipment_position;
    private String export_user;
    private String export_approver;
    private String export_equipment_status;

}
