package com.cmkjspringboot.chemicalsystem.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Materials_medicine_management {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String medicine_name;
    private String medicine_type;
    private Integer medicine_number;
    private String measurement;
    private String medicine_box;
    private String expired_time;
    private String storage_number_status; // 库存状态
    private String expired_time_status; // 保质状态
}
