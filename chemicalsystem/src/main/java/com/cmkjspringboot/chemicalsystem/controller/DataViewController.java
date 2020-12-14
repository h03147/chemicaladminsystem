package com.cmkjspringboot.chemicalsystem.controller;


import com.cmkjspringboot.chemicalsystem.entity.Addnewmedicine;
import com.cmkjspringboot.chemicalsystem.entity.Export_chemical_medicine;
import com.cmkjspringboot.chemicalsystem.service.AddnewmedicineService;
import com.cmkjspringboot.chemicalsystem.service.Export_chemical_medicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 数据统计的控制层类
 */
@RestController
@RequestMapping("/DataView")
public class DataViewController {

    @Autowired
    private Export_chemical_medicineService export_chemical_medicineService;

    @Autowired
    private AddnewmedicineService addnewmedicineService;

    //根据当天出库日期精确到日，模糊查询当天全部出库药品记录
    @GetMapping("/findByExportTime/{export_time}")
    public Map<String, Object> findByExportTime(@PathVariable("export_time") String export_time) {
        System.out.println("前端传来了查询请求的出库日期：" + export_time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> map = new HashMap<>();
        List<String> Export_Medicine_Name_List1 = new ArrayList<String>();
        List<Integer> Export_Medicine_Number_List1 = new ArrayList<Integer>();

        try {
            String after_sdf_export_time = sdf.format(sdf.parse(export_time));
            System.out.println("日期格式转换后的日期为：" + after_sdf_export_time);
            List<Export_chemical_medicine> findByExportTime_ListResult1 = export_chemical_medicineService.findByExport_time(after_sdf_export_time);

            System.out.println("查询结果为：" + findByExportTime_ListResult1);
            if(!findByExportTime_ListResult1.isEmpty())
            {
                for(int export_x1 = 0; export_x1 < findByExportTime_ListResult1.size(); export_x1++)
                {
                    Export_Medicine_Name_List1.add(findByExportTime_ListResult1.get(export_x1).getMedicine_name());
                    Export_Medicine_Number_List1.add(findByExportTime_ListResult1.get(export_x1).getMedicine_number());
                }
                map.put("Export_Medicine_Name_List1", Export_Medicine_Name_List1);
                map.put("Export_Medicine_Number_List1", Export_Medicine_Number_List1);
                map.put("status", 200);
                System.out.println("查询结果Export_Medicine_Name_List1为：" + Export_Medicine_Name_List1);
            }else
            {
                map.put("Export_Medicine_Name_List1", Export_Medicine_Name_List1);
                map.put("Export_Medicine_Number_List1", Export_Medicine_Number_List1);
                map.put("status", 404);
            }


        } catch (Exception e) {
            map.put("Export_Medicine_Name_List1", Export_Medicine_Name_List1);
            map.put("Export_Medicine_Number_List1", Export_Medicine_Number_List1);
            map.put("status", "throw Exception");
        } finally {
            return map;
        }
    }

    //根据当天入库日期精确到日，模糊查询当天全部入库药品记录
    @GetMapping("/findByImportTime/{storage_time}")
    public Map<String, Object> findByImportTime(@PathVariable("storage_time") String storage_time) {
        System.out.println("前端传来了查询请求的入库日期：" + storage_time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> map = new HashMap<>();
        List<String> Storage_Medicine_Name_List1 = new ArrayList<String>();
        List<Integer> Storage_Medicine_Number_List1 = new ArrayList<Integer>();

        try {
            String after_sdf_storage_time = sdf.format(sdf.parse(storage_time));
            System.out.println("日期格式转换后的日期为：" + after_sdf_storage_time);
            List<Addnewmedicine> findByStorageTime_ListResult1 = addnewmedicineService.findByStorageTime(after_sdf_storage_time);

            System.out.println("查询结果为：" + findByStorageTime_ListResult1);
            if(!findByStorageTime_ListResult1.isEmpty())
            {
                for(int import_x1 = 0; import_x1 < findByStorageTime_ListResult1.size(); import_x1++)
                {
                    Storage_Medicine_Name_List1.add(findByStorageTime_ListResult1.get(import_x1).getMedicinename());
                    Storage_Medicine_Number_List1.add(findByStorageTime_ListResult1.get(import_x1).getMedicinenumber());
                }
                map.put("Storage_Medicine_Name_List1", Storage_Medicine_Name_List1);
                map.put("Storage_Medicine_Number_List1", Storage_Medicine_Number_List1);
                map.put("status", 200);
                System.out.println("查询结果Storage_Medicine_Name_List1为：" + Storage_Medicine_Name_List1);
            }else
            {
                map.put("Storage_Medicine_Name_List1", Storage_Medicine_Name_List1);
                map.put("Storage_Medicine_Number_List1", Storage_Medicine_Number_List1);
                map.put("status", 404);
            }


        } catch (Exception e) {
            map.put("Storage_Medicine_Name_List1", Storage_Medicine_Name_List1);
            map.put("Storage_Medicine_Number_List1", Storage_Medicine_Number_List1);
            map.put("status", "throw Exception");
        } finally {
            return map;
        }
    }

}
