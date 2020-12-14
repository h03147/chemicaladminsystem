package com.cmkjspringboot.chemicalsystem.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Export_chemical_medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String export_time;
    private String medicine_name;
    private String medicine_type;
    private Integer medicine_number;
    private String measurement;
    private String medicine_box;
    private String expired_time;
    private String export_user;
    private String export_approver;

}
