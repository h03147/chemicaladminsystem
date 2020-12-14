package com.cmkjspringboot.chemicalsystem.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Addnewmedicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String storagetime;
    private String medicinename;
    private String medicinetype;
    private Integer medicinenumber;
    private String measurement;
    private String medicinebox;
    private String expiredtime;
    private String storageuser;
    private String approver;
    private Integer medicinenumber_status;
}
