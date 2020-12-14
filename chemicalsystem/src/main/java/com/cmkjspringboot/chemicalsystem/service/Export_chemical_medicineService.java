package com.cmkjspringboot.chemicalsystem.service;

import com.cmkjspringboot.chemicalsystem.entity.Export_chemical_medicine;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Export_chemical_medicineService {

    // 批量导入excel出库信息方法
    boolean batchImport(String fileName, MultipartFile file) throws Exception;

    // 三条件查询 根据药品名称和药品药柜药瓶过期时间 共同确定当前要出柜的药品
    public List<Export_chemical_medicine> findByCondition(String medicine_name, String medicine_box, String expired_time);

    // 单条件模糊查询出库如期为某一天的所有出库记录
    public List<Export_chemical_medicine> findByExport_time(String export_time);
}
