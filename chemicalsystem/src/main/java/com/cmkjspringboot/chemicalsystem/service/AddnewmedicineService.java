package com.cmkjspringboot.chemicalsystem.service;

import com.cmkjspringboot.chemicalsystem.entity.Addnewmedicine;

import java.util.List;

public interface AddnewmedicineService {

    // 双条件查询 根据药品名称和药品药柜 查询某个柜子存在的药品详情
    public List<Addnewmedicine> findByCondition(String medicinename, String medicinebox);

    // 三条件查询 根据药品名称和药品药柜以及过期日期 查询某个柜子药品某日期的药是否用完了
    public List<Addnewmedicine> findByConditionthree(String medicinename, String medicinebox, String expiredtime);

    // 单条件模糊查询入库日期为某一天的所有入库记录
    public List<Addnewmedicine> findByStorageTime(String storage_time);
}
