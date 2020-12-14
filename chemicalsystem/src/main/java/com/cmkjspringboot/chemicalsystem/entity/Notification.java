package com.cmkjspringboot.chemicalsystem.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String medicine_name;
    private String medicine_box;
    private String expired_time;
    private Integer expired_number;
    private Integer medicine_number;
    private String notice_time;
    private Integer notice_status;
}
