package com.cmkjspringboot.chemicalsystem.controller;

import com.cmkjspringboot.chemicalsystem.entity.Export_equipment;
import com.cmkjspringboot.chemicalsystem.repository.Export_equipmentRepository;
import com.cmkjspringboot.chemicalsystem.service.Export_equipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.domain.Sort.by;

@RestController
@RequestMapping("Exportequipment")
public class Export_equipmentController {

    @Autowired
    private Export_equipmentRepository export_equipmentRepository;

    @Autowired
    private Export_equipmentService export_equipmentService;


    // 查询出库设备信息分页加排序
    @GetMapping("/findexportequipmentandsortPageable/{page}/{size}/{sortType}/{sortableFields}")
    public Map<String, Object> findAllexportequipmentpageableandsort(
            @PathVariable("page") Integer page, // 第几页
            @PathVariable("size") Integer size, // 显示多少条
            @PathVariable("sortType") String sortType, // 正序还是逆序
            @PathVariable("sortableFields") String sortableFields //需要按照哪一个字段域来排序
    ) {
        System.out.println("前端传来了分页查询出库设备列表的请求：" + page + size + sortType + sortableFields);
        Map<String, Object> map = new HashMap<>();
        try {
            Sort sort  = "ascending".equals(sortType) ? by(Sort.Direction.ASC, sortableFields) : by(Sort.Direction.DESC, sortableFields);
            Pageable pageable = PageRequest.of(page - 1, size, sort);
            map.put("SelectDataResult", export_equipmentRepository.findAll(pageable));
            map.put("status", true);
        } catch (Exception e) {
            map.put("status", false);
        }
        return map;
    }

    // 批量接收excel的出借设备信息
    @PostMapping("/import")
    public boolean batchAddExportEquipment(@RequestParam("file") MultipartFile file) {
        System.out.println("前端传来了出借设备的表格信息：" + file);
        boolean status = false;
        String fileName = file.getOriginalFilename();
        try {
            status = export_equipmentService.batchExport_equipment_Import(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // 删除操作
    @GetMapping("/deleteByEquipId/{id}")
    public Map<String, Object> deleteByEquipId(@PathVariable("id") Integer id) {
        System.out.println("前端传来删除出设备信息的id：" + id);
        Map<String, Object> map = new HashMap<>();
        try {
            Export_equipment export_equipmentfindResult1 = export_equipmentRepository.findById(id).get();
            if(export_equipmentfindResult1 != null)
            {
                export_equipmentRepository.deleteById(id);
                map.put("delete_status", true);
            }else
            {
                map.put("delete_status", false);
            }
        } catch (Exception e) {
            map.put("delete_status", false);
            map.put("error", "throw Exception");
        }
        return map;
    }

    // 归还设备信息
    @PostMapping("/returnEquipByEquip")
    public String returnEquipByEquip(@RequestBody Export_equipment export_equipment) {
        System.out.println("前端传来了归还设备的请求,归还设备信息如下：" + export_equipment);
        try {
            export_equipment.setExport_equipment_status("在库中");
            export_equipmentRepository.save(export_equipment);
            return "success";
        } catch (Exception e) {
            return "throw Exception";
        }
    }

    // 归还设备信息表有条件的查询出库设备信息分页加排序
    @GetMapping("/findAllexportequipmentnotreturnedpageableandsort/{page}/{size}/{sortType}/{sortableFields}")
    public Map<String, Object> findAllexportequipmentnotreturnedpageableandsort(
            @PathVariable("page") Integer page, // 第几页
            @PathVariable("size") Integer size, // 显示多少条
            @PathVariable("sortType") String sortType, // 正序还是逆序
            @PathVariable("sortableFields") String sortableFields //需要按照哪一个字段域来排序
    ) {
        System.out.println("前端传来了分页查询出库设备列表的请求：" + page + size + sortType + sortableFields);
        Map<String, Object> map = new HashMap<>();
        try {
            Sort sort  = "ascending".equals(sortType) ? by(Sort.Direction.ASC, sortableFields) : by(Sort.Direction.DESC, sortableFields);
            Pageable pageable = PageRequest.of(page - 1, size, sort);
            // 去设备外界表查询过滤已借出的信息行并分页返回结果
//            map.put("SelectDataResult", export_equipmentRepository.findAll(pageable));
            map.put("SelectDataResult", export_equipmentService.findByExport_Equip_status("已借出", pageable));
            map.put("status", true);
        } catch (Exception e) {
            map.put("status", false);
        }
        return map;
    }
}
