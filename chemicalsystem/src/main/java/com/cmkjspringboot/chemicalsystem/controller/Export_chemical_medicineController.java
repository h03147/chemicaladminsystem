package com.cmkjspringboot.chemicalsystem.controller;

import com.cmkjspringboot.chemicalsystem.entity.Addnewmedicine;
import com.cmkjspringboot.chemicalsystem.entity.Export_chemical_medicine;
import com.cmkjspringboot.chemicalsystem.repository.AddnewmedicineRepository;
import com.cmkjspringboot.chemicalsystem.repository.Export_chemical_medicineRepository;
import com.cmkjspringboot.chemicalsystem.service.AddnewmedicineService;
import com.cmkjspringboot.chemicalsystem.service.Export_chemical_medicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("/Exportchemicalmedicine")
public class Export_chemical_medicineController {

    @Autowired
    private Export_chemical_medicineRepository export_chemical_medicineRepository;

    @Autowired
    private Export_chemical_medicineService export_chemical_medicineService;

    @Autowired
    private AddnewmedicineService addnewmedicineService;

    @Autowired
    private AddnewmedicineRepository addnewmedicineRepository;
    // 查询出库药品信息分页加排序
    @GetMapping("/findexportmedicineandsortPageable/{page}/{size}/{sortType}/{sortableFields}")
    public Map<String, Object> findAllexportpageableandsort(
            @PathVariable("page") Integer page, // 第几页
            @PathVariable("size") Integer size, // 显示多少条
            @PathVariable("sortType") String sortType, // 正序还是逆序
            @PathVariable("sortableFields") String sortableFields //需要按照哪一个字段域来排序
    ) {
        System.out.println("前端传来的配置sort"+page+size+sortType+sortableFields);
        Map<String, Object> map = new HashMap<>();
        try{
            //判断排序类型及排序字段
            Sort sort = "ascending".equals(sortType) ? by(Sort.Direction.ASC, sortableFields) : by(Sort.Direction.DESC, sortableFields);
            //获取pageable
            Pageable pageable = PageRequest.of(page-1,size,sort);

            map.put("SelectDataResult", export_chemical_medicineRepository.findAll(pageable));
        }catch (Exception e) {
            map.put("error", "查询失败，请检查原因");
        }
        return map;
    }

    // 接受excel表格里的新增出库信息
    @PostMapping("/import")
    public boolean addExportchemicalmedicine(@RequestParam("file") MultipartFile file) {
        boolean state = false;
        String fileName = file.getOriginalFilename();
        try {
            state = export_chemical_medicineService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state;
    }

    // 根据id删除操作
    @GetMapping("/lover/{id}")
    public Map<String, Object> deleteexport_chemical_medicineById(@PathVariable("id") Integer id) {
        System.out.println("前端传来的删除出库行记录请求的id号为：" + id);
        Map<String, Object> map = new HashMap<>();
        try {
            Export_chemical_medicine exportById_Result1 = export_chemical_medicineRepository.findById(id).get();
            if(exportById_Result1 != null)
            {
                List<Addnewmedicine> addnewmedicinefindById_List1 = addnewmedicineService.findByConditionthree(
                        exportById_Result1.getMedicine_name(),
                        exportById_Result1.getMedicine_box(),
                        exportById_Result1.getExpired_time()
                );

                if(addnewmedicinefindById_List1.size() != 0)
                {
                    // 出柜错了，就把新药入库对应行数据的剩余数量状态还原成1
                    Addnewmedicine addnewmedicine1 = addnewmedicinefindById_List1.get(0);
                    addnewmedicine1.setMedicinenumber_status(1);
                    addnewmedicineRepository.save(addnewmedicine1);
                    export_chemical_medicineRepository.deleteById(id);
                    map.put("delete_status", "ok");
                }else
                {
                    // 出柜了费数据，在入库表不存在的数据删除
                    export_chemical_medicineRepository.deleteById(id);
                    map.put("delete_status", "reset");
                }
            }
        } catch (Exception e) {
            map.put("delete_status", "error");
        }
        return map;
    }

}
