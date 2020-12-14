package com.cmkjspringboot.chemicalsystem.controller;

import com.cmkjspringboot.chemicalsystem.entity.Laboratory_equipment_management;
import com.cmkjspringboot.chemicalsystem.entity.Materials_equipment_management;
import com.cmkjspringboot.chemicalsystem.repository.Laboratory_equipment_managementRepository;
import com.cmkjspringboot.chemicalsystem.repository.Materials_equipment_managementRepository;
import com.cmkjspringboot.chemicalsystem.service.Materials_equipment_managementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.domain.Sort.by;


/***
 * describe: 实验设备入库管理的接口类
 * author: arthurye
 */

@RestController
@RequestMapping("/Laboratoryequipmentmanagement")
public class Laboratory_equipment_managementController {

    @Autowired
    private Laboratory_equipment_managementRepository laboratory_equipment_managementRepository;

    @Autowired
    private Materials_equipment_managementRepository materials_equipment_managementRepository;

    @Autowired
    private Materials_equipment_managementService materials_equipment_managementService;

    // 分页查询实验设备表信息并且绑定id字段来进行正序逆序排序
    @GetMapping("/findlaboratoryequipmentmanagementandsortPageable/{page}/{size}/{sortType}/{sortableFields}")
    public Page<Laboratory_equipment_management> findAllpageableandsort(
            @PathVariable("page") Integer page, // 第几页
            @PathVariable("size") Integer size, // 显示多少条
            @PathVariable("sortType") String sortType, // 正序还是逆序
            @PathVariable("sortableFields") String sortableFields //需要按照哪一个字段域来排序
    ) {
        System.out.println("前端传来的配置sort"+page+size+sortType+sortableFields);
        //判断排序类型及排序字段
        Sort sort = "ascending".equals(sortType) ? by(Sort.Direction.ASC, sortableFields) : by(Sort.Direction.DESC, sortableFields);
        //获取pageable
        Pageable pageable = PageRequest.of(page-1,size,sort);
        return laboratory_equipment_managementRepository.findAll(pageable);
    }

    // 入库新设备
    @PostMapping("/savenewlineequipment")
    public String addNewEquipment(@RequestBody Laboratory_equipment_management laboratory_equipment_management) {
        System.out.println("前端请求入库新设备的信息："+ laboratory_equipment_management);
        try {

            Laboratory_equipment_management addNewEquipmentResult = laboratory_equipment_managementRepository.save(laboratory_equipment_management);
            List<Materials_equipment_management> findByCondition_ListResult1 = materials_equipment_managementService.findByEquipment_name_and_Equipment_position(
                    laboratory_equipment_management.getEquipment_name(),
                    laboratory_equipment_management.getEquipment_position()
            );
            if(findByCondition_ListResult1.isEmpty())
            {
                Materials_equipment_management materials_equipment_management1 = new Materials_equipment_management();
                materials_equipment_management1.setEquipment_name(laboratory_equipment_management.getEquipment_name());
                materials_equipment_management1.setEquipment_type(laboratory_equipment_management.getEquipment_type());
                materials_equipment_management1.setEquipment_number(laboratory_equipment_management.getEquipment_number());
                materials_equipment_management1.setEquipment_position(laboratory_equipment_management.getEquipment_position());
                materials_equipment_managementRepository.save(materials_equipment_management1);
                System.out.println("对应设备位置未记录，新添成功！");
            }else
            {
                // 对原有设备进行追加
                int origin_number = findByCondition_ListResult1.get(0).getEquipment_number();
                int add_number = laboratory_equipment_management.getEquipment_number();
                findByCondition_ListResult1.get(0).setEquipment_number(origin_number + add_number);
                materials_equipment_managementRepository.save(findByCondition_ListResult1.get(0));
                System.out.println("设备位置已存在，追加成功！");
            }
            if(addNewEquipmentResult != null)
            {
                return "success";
            }else
            {
                return "failed";
            }
        } catch (Exception e) {
            return "throw Exception";
        }

    }

//    // 根据id查询设备信息
//    @GetMapping("/findById/{id}")
//    public Map<String, Object> findById(@PathVariable("id") Integer id) {
//        System.out.println("前端请求修改设备信息的id："+ id);
//        Map<String, Object> map = new HashMap<>();
//        try{
//            Laboratory_equipment_management laborequipselelctByIdResult = laboratory_equipment_managementRepository.findById(id).get();
//            if(laborequipselelctByIdResult != null)
//            {
//                map.put("laborequipResult", laborequipselelctByIdResult);
//            }else
//            {
//                map.put("error", "糟糕！ 获取失败了，可能的原因：无法描述的非法操作,请核对你的操作是否有误！");
//            }
//        } catch (Exception e) {
//            map.put("error", "糟糕！ 获取失败了，可能的原因：无法描述的非法操作,请核对你的操作是否有误！");
//        }
//        return map;
//    }
//
//    // 修改设备信息
//    @PostMapping("/updateequipment")
//    public String updateEquipment(@RequestBody Laboratory_equipment_management laboratory_equipment_management) {
//        System.out.println("前端传来了修改设备的请求数据：" + laboratory_equipment_management);
//        try{
//            Laboratory_equipment_management laborequipupdateResult = laboratory_equipment_managementRepository.save(laboratory_equipment_management);
//            return laborequipupdateResult != null ? "修改设备信息成功" : "糟糕！ 修改失败了呀，可能的原因：无法描述的非法操作,请核对你的操作是否有误！";
//        } catch (Exception e) {
//            return "糟糕！ 修改失败了，可能的原因：无法描述的非法操作,请核对你的操作是否有误！";
//        }
//    }

    // 根据id删除设备信息
    @GetMapping("/deleteEquipmentById/{id}")
    public String deleteEquipmentById (@PathVariable("id") Integer id) {
        System.out.println("前端传来删除设备的id：" + id);
        try{
            // 添加错误的设备删除前，先去设备资源管理表清掉错误信息和数量
            Laboratory_equipment_management lab_equip_find_Result1 = laboratory_equipment_managementRepository.findById(id).get();
            List<Materials_equipment_management> mater_equip_findByCondition_ListResult1 = materials_equipment_managementService
                    .findByEquipment_name_and_Equipment_position(lab_equip_find_Result1.getEquipment_name(),
                            lab_equip_find_Result1.getEquipment_position());
            int number1 = lab_equip_find_Result1.getEquipment_number();
            int number2 = mater_equip_findByCondition_ListResult1.get(0).getEquipment_number();
            if(number2 - number1 > 0)
            {
                mater_equip_findByCondition_ListResult1.get(0).setEquipment_number(number2 -number1);
                materials_equipment_managementRepository.save(mater_equip_findByCondition_ListResult1.get(0));
                laboratory_equipment_managementRepository.deleteById(id);
                return "delete success";
            }else if(number2 - number1 == 0)
            {
                materials_equipment_managementRepository.deleteById(mater_equip_findByCondition_ListResult1.get(0).getId());
                laboratory_equipment_managementRepository.deleteById(id);
                return "delete success";
            }else
            {
                return "delete failed";
            }
        } catch (Exception e) {
            return "throw Exception";
        }
    }
}
