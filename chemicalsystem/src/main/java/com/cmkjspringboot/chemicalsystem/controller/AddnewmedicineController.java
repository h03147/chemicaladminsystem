package com.cmkjspringboot.chemicalsystem.controller;

import com.cmkjspringboot.chemicalsystem.entity.Addnewmedicine;
import com.cmkjspringboot.chemicalsystem.entity.Materials_medicine_management;
import com.cmkjspringboot.chemicalsystem.repository.AddnewmedicineRepository;
import com.cmkjspringboot.chemicalsystem.repository.Materials_medicine_managementRepository;
import com.cmkjspringboot.chemicalsystem.service.Materials_medicine_managementService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.domain.Sort.by;

/***
 * describe: 药品入库管理的接口类
 * author: arthurye
 */

@RestController
@RequestMapping("/Addnewmedicine")
public class AddnewmedicineController {

    @Autowired
    private AddnewmedicineRepository addnewmedicineRepository;

    @Autowired
    private Materials_medicine_managementRepository materials_medicine_managementRepository;

    @Autowired
    private Materials_medicine_managementService materials_medicine_managementService;

    /**
     * 手动接管EntityManager事务的注解 Jpa有自动托管事务的方法，在你改变Session中对象的值时
     * 会自动update数据库对应数据 这里与业务上冲突了（并不想改变数据库的对应数据行），所以接管事务清除这个时候自己
     * set的值，清除后事务检测不到变化就不会自动update数据库
     */

//    @PersistenceContext
//    private EntityManager entityManager;

    // 分页查询新药表信息并且绑定id字段来进行正序逆序排序
    @GetMapping("/findnewmedicineandsortPageable/{page}/{size}/{sortType}/{sortableFields}")
    public Page<Addnewmedicine> testPageable(
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
        return addnewmedicineRepository.findAll(pageable);

    }

    // 入库新药
    @PostMapping("/savenewlinemedicine")
    public String addNewMedicine(@RequestBody Addnewmedicine addnewmedicine) {
        System.out.println("前端请求入库新药信息："+ addnewmedicine);
        addnewmedicine.setMedicinenumber_status(1);
        Addnewmedicine addResult = addnewmedicineRepository.save(addnewmedicine);
        try {
            List<Materials_medicine_management> findByConditionResult = materials_medicine_managementService.findByCondition(addnewmedicine.getMedicinename(),
                    addnewmedicine.getMedicinebox());
            // 如果存在就在原有数据基础上增加数量，不能存在就新建一条数据存储
            System.out.println("返回的findByConditionResult:" + findByConditionResult.isEmpty());
            if(findByConditionResult.isEmpty())
            {
                Materials_medicine_management materials_medicine_managementadd1 = new Materials_medicine_management();
                materials_medicine_managementadd1.setMedicine_name(addnewmedicine.getMedicinename());
                materials_medicine_managementadd1.setMedicine_type(addnewmedicine.getMedicinetype());
                materials_medicine_managementadd1.setMedicine_number(addnewmedicine.getMedicinenumber());
                materials_medicine_managementadd1.setMeasurement(addnewmedicine.getMeasurement());
                materials_medicine_managementadd1.setMedicine_box(addnewmedicine.getMedicinebox());
//                materials_medicine_managementadd1.setExpired_time(addnewmedicine.getExpiredtime());
                materials_medicine_managementRepository.save(materials_medicine_managementadd1);
                System.out.println("药品药柜未记录，新添成功！");
            }else
            {
                // 对原有药品数量进行追加
                int find_number = findByConditionResult.get(0).getMedicine_number();
                int add_number = addnewmedicine.getMedicinenumber();
                findByConditionResult.get(0).setMedicine_number(find_number + add_number);
                materials_medicine_managementRepository.save(findByConditionResult.get(0));
                System.out.println("药品药柜已存在，追加成功！");
            }

            if(addResult != null)
            {
                return "新药添加成功";
            }else
            {
                return "糟糕！ 未知的错误";
            }
        } catch (Exception e) {
            return "糟糕！ 未知的错误";
        }

    }

//    // 根据id查询药品信息
//    @GetMapping("/findById/{id}")
//    public Map<String, Object> findById(@PathVariable("id") Integer id) {
//        System.out.println("前端传来了单条药品查询请求的id：" + id);
//        Map<String, Object> map = new HashMap<>();
//        try{
//            Addnewmedicine selectByIdResult = addnewmedicineRepository.findById(id).get();
//            if(selectByIdResult != null)
//            {
//                map.put("selectByIdResult", selectByIdResult);
//            }else
//            {
//                map.put("error", "糟糕，获取失败了，可能的原因：无法描述的非法操作,请核对你的操作是否有误！");
//            }
//        } catch (Exception e) {
//            map.put("error", "糟糕，获取失败了，可能的原因：无法描述的非法操作,请核对你的操作是否有误！");
//        }
//
//        return map;
//    }

//    // 修改药品信息
//    @PostMapping("/updatemedicine")
//    public String updatemedicine(@RequestBody Addnewmedicine addnewmedicine) {
//        System.out.println("前端传来了修改药品的请求数据：" + addnewmedicine);
//        try {
//            Addnewmedicine updateResult = addnewmedicineRepository.save(addnewmedicine);
//            if(updateResult != null)
//            {
//                return "修改药品信息成功";
//            }else
//            {
//                return "糟糕，修改失败了，可能的原因：无法描述的非法操作,请核对你的操作是否有误！";
//            }
//        } catch (Exception e) {
//            return "糟糕，修改失败了，可能的原因：无法描述的非法操作,请核对你的操作是否有误！";
//        }
//    }

    // 根据id删除药品信息
    @GetMapping("/deleteById/{id}")
    public String DeleteById(@PathVariable("id") Integer id) {
        System.out.println("前端传来了删除的请求id：" + id);
        try{
            // 删除前要把物资管理那里减去删除的
            Addnewmedicine find_Result = addnewmedicineRepository.findById(id).get();
            List<Materials_medicine_management> findList_result =  materials_medicine_managementService.findByCondition(find_Result.getMedicinename(), find_Result.getMedicinebox());
            int number1 = find_Result.getMedicinenumber();
            int number2 = findList_result.get(0).getMedicine_number();
            if(number2 - number1 > 0)
            {
//                Session session = entityManager.unwrap(org.hibernate.Session.class);
                findList_result.get(0).setMedicine_number(number2 - number1);
//                session.evict(findList_result); // 在Session中擦去手动更新的内容
                System.out.println("手动分割线1-------------------------------");
                materials_medicine_managementRepository.save(findList_result.get(0));
                System.out.println("手动分割线2-------------------------------");
                addnewmedicineRepository.deleteById(id);
                return "药品删除成功";
            }else if(number2 - number1 == 0)
            {
                materials_medicine_managementRepository.deleteById(findList_result.get(0).getId());
                addnewmedicineRepository.deleteById(id);
                return "药品删除成功";
            }else
            {
                return "糟糕！删除失败了，请找到原因后重试。";
            }
        } catch (Exception e) {
            return "糟糕！删除失败了，请找到原因后重试。";
        }
    }

}
