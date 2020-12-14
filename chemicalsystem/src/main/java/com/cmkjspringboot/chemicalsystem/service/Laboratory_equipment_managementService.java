package com.cmkjspringboot.chemicalsystem.service;

import com.cmkjspringboot.chemicalsystem.entity.Laboratory_equipment_management;

import java.util.List;

public interface Laboratory_equipment_managementService {

    // 根据设备名称和设备柜位置去设备入库表查询对行的数量总和
    public List<Laboratory_equipment_management> findByLaboratoryEquip_name_and_Equip_position(String equipment_name, String equipment_position);
}
