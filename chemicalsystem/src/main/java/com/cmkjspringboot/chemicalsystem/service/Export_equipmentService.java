package com.cmkjspringboot.chemicalsystem.service;

import com.cmkjspringboot.chemicalsystem.entity.Export_equipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Export_equipmentService {

    // 批量导出设备信息，成功后写入数据库
    boolean batchExport_equipment_Import(String fileName, MultipartFile file) throws Exception;

    // 根据设备名称,设备柜的位置以及设备使用状态为已借出三个条件，找出该设备的所有的外借记录数
    public List<Export_equipment> findByExport_Equip_name_and_Equip_position_and_Export_Equip_status(String equipment_name, String equipment_position, String export_equipment_status);

    // 根据设备状态,查询出仅已借出设备, 并且带分页条件返回结果
    public Page<Export_equipment> findByExport_Equip_status(String export_equipment_status, Pageable pageable);
}
