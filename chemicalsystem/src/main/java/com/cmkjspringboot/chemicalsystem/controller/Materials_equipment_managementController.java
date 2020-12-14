package com.cmkjspringboot.chemicalsystem.controller;

import com.cmkjspringboot.chemicalsystem.entity.Export_equipment;
import com.cmkjspringboot.chemicalsystem.entity.Laboratory_equipment_management;
import com.cmkjspringboot.chemicalsystem.repository.Materials_equipment_managementRepository;
import com.cmkjspringboot.chemicalsystem.service.Export_equipmentService;
import com.cmkjspringboot.chemicalsystem.service.Laboratory_equipment_managementService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.domain.Sort.by;

@RestController
@RequestMapping("/Materialsequipmentmanagement")
public class Materials_equipment_managementController {

    @Autowired
    private Materials_equipment_managementRepository materials_equipment_managementRepository;

    @Autowired
    private Export_equipmentService export_equipmentService;

    @PersistenceContext
    private EntityManager entityManager;

    // 分页查询物资管理的设备表信息并且绑定id字段来进行正序逆序排序
    @GetMapping("/findmaterialsequipmentandsortPageable/{page}/{size}/{sortType}/{sortableFields}")
    public Map<String, Object> findAllpageableandsort(
            @PathVariable("page") Integer page, // 第几页
            @PathVariable("size") Integer size, // 显示多少条
            @PathVariable("sortType") String sortType, // 正序还是逆序
            @PathVariable("sortableFields") String sortableFields //需要按照哪一个字段域来排序
    ) {
        System.out.println("前端传来的配置sort" + page + size + sortType + sortableFields);
        Map<String, Object> map = new HashMap<>();
        //判断排序类型及排序字段
        Sort sort = "ascending".equals(sortType) ? by(Sort.Direction.ASC, sortableFields) : by(Sort.Direction.DESC, sortableFields);
        //获取pageable
        Pageable pageable = PageRequest.of(page-1,size,sort);
        // 当前页行数
        int materials_equipment_length = materials_equipment_managementRepository.findAll(pageable).getContent().size();
        if(materials_equipment_length != 0)
        {
            int[] materials_equipment_management_aftersub_array1 = new int[materials_equipment_length];
            for(int i = 0; i < materials_equipment_length; i++)
            {
                // 当前行设备的原始库存总数
                int lineLabora_storage_number_total1 = 0;
                lineLabora_storage_number_total1 = materials_equipment_managementRepository.findAll(pageable).getContent().get(i).getEquipment_number();
                List<Export_equipment> Export_equip_findByCondition_ListResult1 = export_equipmentService
                        .findByExport_Equip_name_and_Equip_position_and_Export_Equip_status(
                                materials_equipment_managementRepository.findAll(pageable).getContent().get(i).getEquipment_name(),
                                materials_equipment_managementRepository.findAll(pageable).getContent().get(i).getEquipment_position(),
                                "已借出"
                        );
                if(Export_equip_findByCondition_ListResult1.size() !=0)
                {
                    // 当前设备名称，设备柜已借出的设备数量总和
                    int Export_equipment_totalnumber1 = 0;
                    for(int j = 0; j < Export_equip_findByCondition_ListResult1.size(); j++)
                    {
                        Export_equipment_totalnumber1 += Export_equip_findByCondition_ListResult1.get(j).getEquipment_number();
                    }
                    lineLabora_storage_number_total1 -= Export_equipment_totalnumber1;
                    materials_equipment_management_aftersub_array1[i] = lineLabora_storage_number_total1;
                }else
                {
                    materials_equipment_management_aftersub_array1[i] = lineLabora_storage_number_total1;
                }
            }
            System.out.print("现在的实际库存量为：materials_equipment_management_aftersub_array1[] = [");
            for(int item : materials_equipment_management_aftersub_array1)
            {
//                System.out.print("叶佳log1：" + item);
                System.out.print(item + ",");
            }
            System.out.print("]");

            Session session1 = entityManager.unwrap(org.hibernate.Session.class);
            for(int k = 0; k < materials_equipment_length; k++)
            {
                materials_equipment_managementRepository.findAll(pageable).getContent().get(k).setEquipment_number(
                        materials_equipment_management_aftersub_array1[k]
                );
            }
            map.put("status", "ok");
            map.put("SelectDataResult", materials_equipment_managementRepository.findAll(pageable));
            for(int w = 0; w < materials_equipment_length; w++)
            {
                session1.evict(materials_equipment_managementRepository.findAll(pageable).getContent().get(w));
            }
        }else
        {
            map.put("status", "empty");
            map.put("SelectDataResult", materials_equipment_managementRepository.findAll(pageable));
        }
        return map;
    }

    // 获取当前设备库存实际总量
    @GetMapping("/findmaterialsequipmentTotalNumber/{page}/{size}/{sortType}/{sortableFields}")
    public Map<String, Object> findmaterialsequipmentTotalNumber(
            @PathVariable("page") Integer page, // 第几页
            @PathVariable("size") Integer size, // 显示多少条
            @PathVariable("sortType") String sortType, // 正序还是逆序
            @PathVariable("sortableFields") String sortableFields //需要按照哪一个字段域来排序
    ) {
        System.out.println("前端传来的配置sort" + page + size + sortType + sortableFields);
        Map<String, Object> map = new HashMap<>();
        //判断排序类型及排序字段
        Sort sort = "ascending".equals(sortType) ? by(Sort.Direction.ASC, sortableFields) : by(Sort.Direction.DESC, sortableFields);
        //获取pageable
        Pageable pageable = PageRequest.of(page-1,size,sort);
        int Total_Equip_Number = 0;
        // 当前页行数
        int materials_equipment_length = materials_equipment_managementRepository.findAll(pageable).getContent().size();
        if(materials_equipment_length != 0)
        {
            int[] materials_equipment_management_aftersub_array1 = new int[materials_equipment_length];
            for(int i = 0; i < materials_equipment_length; i++)
            {
                // 当前行设备的原始库存总数
                int lineLabora_storage_number_total1 = 0;
                lineLabora_storage_number_total1 = materials_equipment_managementRepository.findAll(pageable).getContent().get(i).getEquipment_number();
                List<Export_equipment> Export_equip_findByCondition_ListResult1 = export_equipmentService
                        .findByExport_Equip_name_and_Equip_position_and_Export_Equip_status(
                                materials_equipment_managementRepository.findAll(pageable).getContent().get(i).getEquipment_name(),
                                materials_equipment_managementRepository.findAll(pageable).getContent().get(i).getEquipment_position(),
                                "已借出"
                        );
                if(Export_equip_findByCondition_ListResult1.size() !=0)
                {
                    // 当前设备名称，设备柜已借出的设备数量总和
                    int Export_equipment_totalnumber1 = 0;
                    for(int j = 0; j < Export_equip_findByCondition_ListResult1.size(); j++)
                    {
                        Export_equipment_totalnumber1 += Export_equip_findByCondition_ListResult1.get(j).getEquipment_number();
                    }
                    lineLabora_storage_number_total1 -= Export_equipment_totalnumber1;
                    materials_equipment_management_aftersub_array1[i] = lineLabora_storage_number_total1;
                }else
                {
                    materials_equipment_management_aftersub_array1[i] = lineLabora_storage_number_total1;
                }
            }
            System.out.print("现在的实际库存量为：materials_equipment_management_aftersub_array1[] = [");
            for(int item : materials_equipment_management_aftersub_array1)
            {
//                System.out.print("叶佳log1：" + item);
                System.out.print(item + ",");
                Total_Equip_Number += item;
            }
            System.out.print("]");
            map.put("status", "ok");
            map.put("Total_Equip_Number",Total_Equip_Number);
        }else
        {
            map.put("status", "empty");
            map.put("Total_Equip_Number", Total_Equip_Number);
        }
        return map;
    }
}
