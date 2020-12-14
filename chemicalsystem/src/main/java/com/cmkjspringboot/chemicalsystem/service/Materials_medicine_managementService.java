package com.cmkjspringboot.chemicalsystem.service;

import com.cmkjspringboot.chemicalsystem.entity.Materials_medicine_management;

import java.util.List;

public interface Materials_medicine_managementService {

    // 双条件查询 根据药品名称和药品药柜 共同确定是否存在药品
    public List<Materials_medicine_management> findByCondition(String medicine_name, String medicine_box);
}
