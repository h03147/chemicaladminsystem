package com.cmkjspringboot.chemicalsystem.service;

import com.cmkjspringboot.chemicalsystem.entity.Materials_equipment_management;

import java.util.List;

public interface Materials_equipment_managementService {

    // 双条件查询 根据设备名称和设备位置确定当前商品是否存在于物资管理表当中
    public List<Materials_equipment_management> findByEquipment_name_and_Equipment_position(String equipment_name, String equipment_position);
}
