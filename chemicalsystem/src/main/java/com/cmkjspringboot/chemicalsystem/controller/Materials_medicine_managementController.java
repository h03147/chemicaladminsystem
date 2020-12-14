package com.cmkjspringboot.chemicalsystem.controller;

import com.cmkjspringboot.chemicalsystem.entity.Addnewmedicine;
import com.cmkjspringboot.chemicalsystem.entity.Export_chemical_medicine;
import com.cmkjspringboot.chemicalsystem.entity.Materials_medicine_management;
import com.cmkjspringboot.chemicalsystem.entity.Notification;
import com.cmkjspringboot.chemicalsystem.repository.*;
import com.cmkjspringboot.chemicalsystem.service.AddnewmedicineService;
import com.cmkjspringboot.chemicalsystem.service.Export_chemical_medicineService;
import com.cmkjspringboot.chemicalsystem.service.NotificationService;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.data.domain.Sort.by;

/***
 * describe: 实验设备入库管理的接口类
 * author: arthurye
 */

@RestController
@RequestMapping("/Materialsmedicinemanagement")
public class Materials_medicine_managementController {

    @Autowired
    private Materials_medicine_managementRepository materials_medicine_managementRepository;

    @Autowired
    private AddnewmedicineService addnewmedicineService;

    @Autowired
    private Export_chemical_medicineService export_chemical_medicineService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private System_settingRepository system_settingRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @PersistenceContext
    private EntityManager entityManager;



//    // 分页查询物资管理的药品表信息并且绑定id字段来进行正序逆序排序
//    @GetMapping("/findmaterialsmedicineandsortPageable/{page}/{size}/{sortType}/{sortableFields}")
//    public Map<String, Object> findAllpageableandsort(
//            @PathVariable("page") Integer page, // 第几页
//            @PathVariable("size") Integer size, // 显示多少条
//            @PathVariable("sortType") String sortType, // 正序还是逆序
//            @PathVariable("sortableFields") String sortableFields //需要按照哪一个字段域来排序
//    ) {
//        System.out.println("前端传来的配置sort"+page+size+sortType+sortableFields);
//        Map<String, Object> map = new HashMap<>();
//
////        try{
//            //判断排序类型及排序字段
//            Sort sort = "ascending".equals(sortType) ? by(Sort.Direction.ASC, sortableFields) : by(Sort.Direction.DESC, sortableFields);
//            //获取pageable
//            Pageable pageable = PageRequest.of(page-1,size,sort);
////            System.out.println("输出查询的分页" + materials_medicine_managementRepository.findAll(pageable).getContent().get(0).getMedicine_number());
////            System.out.println("输出查询的分页" + materials_medicine_managementRepository.findAll(pageable).getContent().get(1).getMedicine_number());
//            // 获取当前分页数组数据的长度
//            //分割线-------------同步数量的数据到物资管理表-----------------------------
//
//
//        int totaladdnumber1 = 0;
//            for(int i = 0;i < materials_medicine_managementRepository.findAll(pageable).getContent().size(); i++)
//            {
//                System.out.println("同步数量的数据到物资管理表log1");
//                // 根据materials_medicine_management表每行的药品名称和药品柜信息两个条件去addnewmedicine表
//                // 查询出对应的药品的入库总数量，再用查出来的addnewmedicine表对应药品名称和药柜名称匹配对应药品行数据的过
//                // 期日期来做第三个条件，用这三个条件去出库表查出对应药品的出库总数量，入库减去出库就是实际剩余数量，赋值给
//                // materials_medicine_management表对应数量字段即可
//
//                // 单行数据在Addnewmedicine的总入库数量
//                int Every_SingleLine_Total_Add_Medicine_Number1 = 0;
//
//                List<Addnewmedicine> ListAddnewmedicine_Result1 = addnewmedicineService.findByCondition(
//                        materials_medicine_managementRepository.findAll(pageable).getContent().get(i).getMedicine_name(),
//                        materials_medicine_managementRepository.findAll(pageable).getContent().get(i).getMedicine_box()
//                );
//                for(int i1 =0; i1 < ListAddnewmedicine_Result1.size(); i1++)
//                {
//                    System.out.println("同步数量的数据到物资管理表log1ListAddnewmedicine_Result1.get(i).getMedicinenumber():" + ListAddnewmedicine_Result1.get(i).getMedicinenumber());
//                    Every_SingleLine_Total_Add_Medicine_Number1 += ListAddnewmedicine_Result1.get(i).getMedicinenumber();
//
//                    // 单行数据在Export_chemical_medicine的总出库数量
//                    System.out.println("同步数量的数据到物资管理表log1ListAddnewmedicine_Result1.size():" + ListAddnewmedicine_Result1.size());
//                    int Every_SingleLine_Total_Export_Medicine_Number1 = 0;
//                    int Every_SingleLine_Total_Export_Medicine_Number2 = 0;
//
//                    System.out.println("同步数量的数据到物资管理表log2");
//                    List<Export_chemical_medicine> ListExport_chemical_medicine_Result1 = export_chemical_medicineService.findByCondition(
//                            ListAddnewmedicine_Result1.get(i1).getMedicinename(),
//                            ListAddnewmedicine_Result1.get(i1).getMedicinebox(),
//                            ListAddnewmedicine_Result1.get(i1).getExpiredtime());
//                    System.out.println("同步数量的数据到物资管理表log2ListExport_chemical_medicine_Result1条件一:" + ListAddnewmedicine_Result1.get(i1).getMedicinename());
//                    System.out.println("同步数量的数据到物资管理表log2ListExport_chemical_medicine_Result1条件二:" + ListAddnewmedicine_Result1.get(i1).getMedicinebox());
//                    System.out.println("同步数量的数据到物资管理表log2ListExport_chemical_medicine_Result1条件三:" + ListAddnewmedicine_Result1.get(i1).getExpiredtime());
//                    System.out.println("同步数量的数据到物资管理表log2ListExport_chemical_medicine_Result1:" + ListExport_chemical_medicine_Result1);
//                    System.out.println("同步数量的数据到物资管理表log2ListExport_chemical_medicine_Result1.size():" + ListExport_chemical_medicine_Result1.size());
//
//                    for(int j = 0; j < ListAddnewmedicine_Result1.size()-1; j++)
//                    {
//                        if(ListExport_chemical_medicine_Result1.size() != 0)
//                        {
//                            System.out.println("Every_SingleLine_Total_Export_Medicine_Number1累加计算" + ListExport_chemical_medicine_Result1.get(j).getMedicine_number());
//                            Every_SingleLine_Total_Export_Medicine_Number1 += ListExport_chemical_medicine_Result1.get(j).getMedicine_number();
//                            System.out.println("叶佳累减：" +
//                                    Every_SingleLine_Total_Add_Medicine_Number1+ "-" +
//                                    Every_SingleLine_Total_Export_Medicine_Number1+
//                                    "=" + (Every_SingleLine_Total_Add_Medicine_Number1 - Every_SingleLine_Total_Export_Medicine_Number1));
//                        }else
//                        {
//                            System.out.println("Every_SingleLine_Total_Export_Medicine_Number1赋值零");
//                            Every_SingleLine_Total_Export_Medicine_Number2 = 0;
//                            System.out.println("叶佳计算赋零：" +
//                                    Every_SingleLine_Total_Add_Medicine_Number1+ "-" +
//                                    Every_SingleLine_Total_Export_Medicine_Number2+
//                                    "=" + (Every_SingleLine_Total_Add_Medicine_Number1 - Every_SingleLine_Total_Export_Medicine_Number2));
//                        }
//                    }
//                    System.out.println("同步数量的数据到物资管理表log3");
//                    Session session = entityManager.unwrap(org.hibernate.Session.class);
//                    System.out.println("同步数量的数据到物资管理表log3-1");
//                    System.out.println("同步数量的数据到物资管理表log3继续之前的之前:" + ListAddnewmedicine_Result1.get(i1).getMedicinenumber());
//                    ListAddnewmedicine_Result1.get(i1).setMedicinenumber(Every_SingleLine_Total_Add_Medicine_Number1 - Every_SingleLine_Total_Export_Medicine_Number1 - Every_SingleLine_Total_Export_Medicine_Number2);
//                    totaladdnumber1 = ListAddnewmedicine_Result1.get(i1).getMedicinenumber();
//                    System.out.println("同步数量的数据到物资管理表log3继续之前：" +
//                            Every_SingleLine_Total_Add_Medicine_Number1+ "-" +
//                            Every_SingleLine_Total_Export_Medicine_Number1+
//                            "=" + ListAddnewmedicine_Result1.get(i1).getMedicinenumber());
//                    System.out.println("同步数量的数据到物资管理表log3继续" + ListAddnewmedicine_Result1.get(i1).getMedicinenumber());
//                    for (int s1 = 0; s1 < ListAddnewmedicine_Result1.size(); s1++)
//                    {
//                        session.evict(ListAddnewmedicine_Result1.get(s1));
//                    }
//                }
//                totaladdnumber1 += totaladdnumber1;
//            }
//
////
////            //分割线-------------同步数量的数据到物资管理表-----------------------------
//
//        try{
//            int length = materials_medicine_managementRepository.findAll(pageable).getContent().size();
//            System.out.println("输出查询的长度" + length);
//            if(length == 0)
//            {
//                map.put("info", "没有什么需要返回的");
//                map.put("SelectDataResult", materials_medicine_managementRepository.findAll(pageable));
//            }else
//            {
//
//                for (int i = 0; i < length; i++) {
//                    System.out.println("i的长度" + i);
//                    // 判断库存
//                    if(materials_medicine_managementRepository.findAll(pageable).getContent().get(i).getMedicine_number() > 20)
//                    {
//                        materials_medicine_managementRepository.findAll(pageable).getContent().get(i).setStorage_number_status("库存正常");
//                    }else if(materials_medicine_managementRepository.findAll(pageable).getContent().get(i).getMedicine_number() > 0)
//                    {
//                        materials_medicine_managementRepository.findAll(pageable).getContent().get(i).setStorage_number_status("库存不足");
//                    }else
//                    {
//                        materials_medicine_managementRepository.findAll(pageable).getContent().get(i).setStorage_number_status("库存为空");
//                    }
//
//                    // 毫秒值加减 时间戳
//                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//                    // 当前行的药品名称
//                    String cache_medicinename = materials_medicine_managementRepository.findAll(pageable).getContent().get(i).getMedicine_name();
//                    System.out.println("当前行的药品名称的结果：" + cache_medicinename);
//                    // 当前行对应的药瓶柜位置
//                    String cache_medicinebox = materials_medicine_managementRepository.findAll(pageable).getContent().get(i).getMedicine_box();
//                    System.out.println("当前行对应的药瓶柜位置的结果：" + cache_medicinebox);
//                    // 比较入库药品的过期时间 找出离过期最近的时间 赋值给物资管理里面的过期时间字段
//                    List<Addnewmedicine> findByConditiona_Result = addnewmedicineService.findByCondition(cache_medicinename, cache_medicinebox);
//                    if(findByConditiona_Result.isEmpty())
//                    {
//                        map.put("info", "j没有什么需要返回的");
//                    }
//                    else
//                    {
//                        // 定义最快过期的时间的时间戳 赋予初值为第0个元素的值
//                        long Shortest_time = df.parse(findByConditiona_Result.get(0).getExpiredtime()).getTime();
//                        for(int j = 0; j < findByConditiona_Result.size(); j++) {
//                            System.out.println("addnewmedicinefindByConditiona_Result的结果：" + findByConditiona_Result);
//                            System.out.println("addnewmedicinefindByConditiona_Result的长度：" + findByConditiona_Result.size());
//                            if(df.parse(findByConditiona_Result.get(j).getExpiredtime()).getTime() < Shortest_time)
//                            {
//                                Shortest_time = df.parse(findByConditiona_Result.get(j).getExpiredtime()).getTime();
//                            }
//                        }
//
//                        // 判断是否过期
//                        // 获取当前系统时间
//                        Date date = new Date();
//                        System.out.println("当前时间的时间戳" + date.getTime());
////                        System.out.println("药品过期时间的时间戳" + df.parse(materials_medicine_managementRepository.findAll(pageable).getContent().get(i).getExpired_time()).getTime());
//                        System.out.println("药品过期时间的时间戳" + Shortest_time);
//
//                        materials_medicine_managementRepository.findAll(pageable).getContent().get(i).setExpired_time(df.format(Shortest_time));
//                        materials_medicine_managementRepository.findAll(pageable).getContent().get(i).setMedicine_number(totaladdnumber1);
//
//                        // 时间戳相减的值
////                        long substuction_time = df.parse(materials_medicine_managementRepository.findAll(pageable).getContent().get(i).getExpired_time()).getTime() - date.getTime();
//                        long substuction_time = Shortest_time - date.getTime();
//                        if(substuction_time >= 0)
//                        {
//                            // 详见后转换为天数
//                            System.out.println("相减后的天数：" + substuction_time / 1000 / 3600 / 24);
//                            if((substuction_time / 1000 / 3600 / 24) > 30)
//                            {
//                                materials_medicine_managementRepository.findAll(pageable).getContent().get(i).setExpired_time_status("日期正常");
//                            }else
//                            {
//                                materials_medicine_managementRepository.findAll(pageable).getContent().get(i).setExpired_time_status("预过期");
//                            }
//                        }else
//                        {
//                            System.out.println("已过期");
//                            materials_medicine_managementRepository.findAll(pageable).getContent().get(i).setExpired_time_status("已过期");
//                        }
//
//                    }
//
//                    map.put("SelectDataResult", materials_medicine_managementRepository.findAll(pageable));
//                }
//
//            }
////            return materials_medicine_managementRepository.findAll(pageable);
//        } catch (Exception e) {
//            map.put("error", "糟糕 出错了！");
//        }
//        return map;
//
//    }

//    // 根据多条件返回dialog框需要的多行数据
//    @GetMapping("/findmaterialsmedicinebycondition/{medicinename}/{medicinebox}")
//    public Map<String, Object> dialogfindByCondition(@PathVariable("medicinename") String medicinename,
//                                                     @PathVariable("medicinebox") String medicinebox) {
//        System.out.println("前端传来了要详细查看的药柜信息：" + medicinename + "$$$" + medicinebox);
//        Map<String, Object> map = new HashMap<>();
//        try{
//            List<Addnewmedicine> findByCondition_ListResult1 = addnewmedicineService.findByCondition(medicinename, medicinebox);
//            map.put("findByCondition_ListResult1", findByCondition_ListResult1);
//            map.put("status", true);
//        }catch (Exception e) {
//            map.put("status", false);
//        }
//        return map;
//    }



    // 分页查询物资管理的药品表信息并且绑定id字段来进行正序逆序排序
    @GetMapping("/findmaterialsmedicineandsortPageable/{page}/{size}/{sortType}/{sortableFields}")
    public Map<String, Object> findAllpageableandsort(
            @PathVariable("page") Integer page, // 第几页
            @PathVariable("size") Integer size, // 显示多少条
            @PathVariable("sortType") String sortType, // 正序还是逆序
            @PathVariable("sortableFields") String sortableFields //需要按照哪一个字段域来排序
    ) {
        System.out.println("前端传来的配置sort"+page+size+sortType+sortableFields);
        Map<String, Object> map = new HashMap<>();

//        try{
        //判断排序类型及排序字段
        Sort sort = "ascending".equals(sortType) ? by(Sort.Direction.ASC, sortableFields) : by(Sort.Direction.DESC, sortableFields);
        //获取pageable
        Pageable pageable = PageRequest.of(page-1,size,sort);
//            System.out.println("输出查询的分页" + materials_medicine_managementRepository.findAll(pageable).getContent().get(0).getMedicine_number());
//            System.out.println("输出查询的分页" + materials_medicine_managementRepository.findAll(pageable).getContent().get(1).getMedicine_number());
        // 获取当前分页数组数据的长度
        //分割线start-------------同步数量的数据到物资管理表-----------------------------
        // 当前页数据行数
        int materials_length = materials_medicine_managementRepository.findAll(pageable).getContent().size();
        if(materials_length != 0)
        {
            Page<Materials_medicine_management> materials_medicine_management_finallResult1 = null;
            int[] materials_medicine_management_aftersub_array1 = new int[materials_length];
            for(int i = 0; i < materials_length; i++)
            {
                List<Addnewmedicine> AddnewmedicineList_Result1 = addnewmedicineService.findByCondition(
                        materials_medicine_managementRepository.findAll(pageable).getContent().get(i).getMedicine_name(),
                        materials_medicine_managementRepository.findAll(pageable).getContent().get(i).getMedicine_box());
//                int materials_medicine_management_aftersub_number1 = 0;
                System.out.println("打点计时器---------------------：" + AddnewmedicineList_Result1);
                if(AddnewmedicineList_Result1.size() != 0)
                {
                    //？？？ 当前行数据统一药品名称，统一药柜的药品减去出库后剩余数量总数
                    int linestorage_number_total1 = 0;
                    // 当前药品名，药柜号的
                    for(int j = 0; j < AddnewmedicineList_Result1.size(); j++)
                    {
                        List<Export_chemical_medicine> Export_chemical_medicineList_Result1 = export_chemical_medicineService.findByCondition(
                                AddnewmedicineList_Result1.get(j).getMedicinename(),
                                AddnewmedicineList_Result1.get(j).getMedicinebox(),
                                AddnewmedicineList_Result1.get(j).getExpiredtime()
                        );

                        if(Export_chemical_medicineList_Result1.size() != 0)
                        {
                            // 当前药品名称，药品柜以及药品过期时间的出库数量总和
                            int Export_chemical_medicine_totalnumber1 = 0;
                            for(int k = 0; k < Export_chemical_medicineList_Result1.size(); k++)
                            {
                                if(Export_chemical_medicineList_Result1.size() != 0)
                                {
                                    Export_chemical_medicine_totalnumber1 += Export_chemical_medicineList_Result1.get(k).getMedicine_number();
                                }
                            }

                            int storage_number1 = AddnewmedicineList_Result1.get(j).getMedicinenumber();
                            storage_number1 -= Export_chemical_medicine_totalnumber1;
                            System.out.println("log1--storage_number1:" + storage_number1);

                            Session session1 = entityManager.unwrap(org.hibernate.Session.class);
                            AddnewmedicineList_Result1.get(j).setMedicinenumber(storage_number1);

                            linestorage_number_total1 +=  AddnewmedicineList_Result1.get(j).getMedicinenumber();
                            for(int m = 0; m < AddnewmedicineList_Result1.size(); m++)
                            {
                                session1.evict(AddnewmedicineList_Result1.get(m));
                            }
                        }else
                        {
                            linestorage_number_total1 +=  AddnewmedicineList_Result1.get(j).getMedicinenumber();
                        }


                    }
                    System.out.println("log2--linestorage_number_total1:" + linestorage_number_total1);

                    Session session2 = entityManager.unwrap(org.hibernate.Session.class);
                    materials_medicine_managementRepository.findAll(pageable).getContent().get(i).setMedicine_number(linestorage_number_total1);
                    materials_medicine_management_aftersub_array1[i] = materials_medicine_managementRepository.findAll(pageable).getContent().get(i).getMedicine_number();
                    session2.evict(materials_medicine_managementRepository.findAll(pageable).getContent().get(i));
//                    materials_medicine_management_finallResult1 = materials_medicine_managementRepository.findAll(pageable);
//                    for(int n = 0; n < materials_medicine_managementRepository.findAll(pageable).getContent().size(); n++)
//                    {
//                        System.out.println("log3--materials_medicine_management_finallResult1:" + materials_medicine_management_finallResult1.getContent().get(n));
//                        session2.evict(materials_medicine_managementRepository.findAll(pageable).getContent().get(n));
//                    }

                }

//                System.out.println("log3--materials_medicine_management_finallResult1.getContent().get(i):" + materials_medicine_management_finallResult1.getContent().get(i));
            }

            // 需要临时存储的最近的库存数量字段数组
            int[] last_number_array1 = new int[materials_length];
            // 需要临时存储的最近的过期时间字段数组
            String[] last_expired_time_array1 = new String[materials_length];
            // 需要临时存储的最近的库存状态字段数组
            String[] last_number_status_array1 = new String[materials_length];
            // 需要临时存储的最近的过期状态字段数组
            String[] last_expired_status_array1 = new String[materials_length];

            // 回传给前端前做实际药品数量同步操作，仅前端变化
            for(int n = 0; n < materials_length; n++)
            {
//                Session session3 = entityManager.unwrap(org.hibernate.Session.class);
//                materials_medicine_managementRepository.findAll(pageable).getContent().get(n).setMedicine_number(
//                        materials_medicine_management_aftersub_array1[n]);
//                last_number_array1[n] = materials_medicine_managementRepository.findAll(pageable).getContent().get(n).getMedicine_number()
//                session3.evict(materials_medicine_managementRepository.findAll(pageable).getContent().get(n));
                last_number_array1[n] = materials_medicine_management_aftersub_array1[n];
            }


            // 回传前, 做库存状态判断
            for (int y = 0; y < materials_length; y++) {
//                System.out.println("y的长度" + y);
                // 判断库存
                if (materials_medicine_management_aftersub_array1[y] > system_settingRepository.findById(1202526328).get().getMedicine_number_warning()) {
//                    materials_medicine_managementRepository.findAll(pageable).getContent().get(y).setStorage_number_status("库存正常");
                    last_number_status_array1[y] = "库存正常";
                } else if (materials_medicine_management_aftersub_array1[y] > 0) {
//                    materials_medicine_managementRepository.findAll(pageable).getContent().get(y).setStorage_number_status("库存不足");
                    last_number_status_array1[y] = "库存不足";
                } else {
//                    materials_medicine_managementRepository.findAll(pageable).getContent().get(y).setStorage_number_status("库存为空");
                    last_number_status_array1[y] = "库存为空";
                }
            }

            // 回传判断最近快要过期的药品时间信息
            for(int z = 0; z < materials_length; z++)
            {
                // 毫秒值加减 时间戳
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                // 当前行的药品名称
                String cache_medicinename = materials_medicine_managementRepository.findAll(pageable).getContent().get(z).getMedicine_name();
//                System.out.println("当前行的药品名称的结果：" + cache_medicinename);
                // 当前行对应的药瓶柜位置
                String cache_medicinebox = materials_medicine_managementRepository.findAll(pageable).getContent().get(z).getMedicine_box();
//                System.out.println("当前行对应的药瓶柜位置的结果：" + cache_medicinebox);
                // 比较入库药品的过期时间 找出离过期最近的时间 赋值给物资管理里面的过期时间字段
                List<Addnewmedicine> findByConditiona_Result = addnewmedicineService.findByCondition(cache_medicinename, cache_medicinebox);
                System.out.println("詹姆斯log条件1~~~~:" + cache_medicinename);
                System.out.println("詹姆斯log条件2~~~~:" + cache_medicinebox);
                System.out.println("异常断电findByConditiona_Result：~~~~:" + findByConditiona_Result.size());
                if(findByConditiona_Result.isEmpty())
                {
                    map.put("info", "j没有什么需要返回的");
                }else
                {
                    long Shortest_time = 0;
                    // 当条件查询只有一行数据时
                    try {

                        if(findByConditiona_Result.size() == 1)
                        {
                            Shortest_time = df.parse(findByConditiona_Result.get(0).getExpiredtime()).getTime();
                        }
                        else
                        {

//202010142056start-------------------------------------------------------------------------------------------------------------------------
                            // 记录所有已经用完了的药(相减后数量小于等于零)的下标
//                            List<Integer> less_than_zero_list1 = new ArrayList<>();
                            // 判断当前药品数量是否已使用完，如果已经使用完了就不参与最短时间计算了
                            for(int i = 0; i < findByConditiona_Result.size(); i++)
                            {
                                System.out.println("叶佳202011021519log条件1~~~~:" + findByConditiona_Result.get(i).getMedicinename());
                                System.out.println("叶佳202011021519log条件2~~~~:" + findByConditiona_Result.get(i).getMedicinebox());
                                System.out.println("叶佳202011021519log条件3~~~~:" + findByConditiona_Result.get(i).getExpiredtime());
//                                System.out.println("詹姆斯logsize~~~~:" + findByConditiona_Result.size() + "-" +
//                                        findByConditiona_Result.get(i).getMedicinename() + "-" +
//                                        findByConditiona_Result.get(i).getMedicinebox() + "-" +
//                                        findByConditiona_Result.get(i).getExpiredtime());
                                List<Export_chemical_medicine> find_medicine_number_exist_Result = export_chemical_medicineService.findByCondition(
                                        findByConditiona_Result.get(i).getMedicinename(),
                                        findByConditiona_Result.get(i).getMedicinebox(),
                                        findByConditiona_Result.get(i).getExpiredtime()
                                );

                                // 当前药品的所库数量之和
                                int export_totalnumber1 = 0;


                                if(!find_medicine_number_exist_Result.isEmpty())
                                {
                                    for(int s = 0; s < find_medicine_number_exist_Result.size(); s++)
                                    {
                                        export_totalnumber1 += find_medicine_number_exist_Result.get(s).getMedicine_number();
                                    }
                                    System.out.println("詹姆斯export_totalnumber1:" + export_totalnumber1);
                                }

                                if(findByConditiona_Result.get(i).getMedicinenumber() - export_totalnumber1 <= 0)
                                {

//                                    less_than_zero_list1.add(i);
                                    System.out.println("詹姆斯小小小----要清零的药品名字为:" + findByConditiona_Result.get(i).getMedicinename() + "$$$" +
                                            "过期日期为：" + findByConditiona_Result.get(i).getExpiredtime() + "$$$" +
                                            "药柜号为：" + findByConditiona_Result.get(i).getMedicinebox() + "$$$" +
                                            "实际剩余数量为:" + (findByConditiona_Result.get(i).getMedicinenumber() - export_totalnumber1));
                                    List<Addnewmedicine> find_zero_number_status_ListResult1 = addnewmedicineService.findByConditionthree(
                                            findByConditiona_Result.get(i).getMedicinename(),
                                            findByConditiona_Result.get(i).getMedicinebox(),
                                            findByConditiona_Result.get(i).getExpiredtime()
                                    );
                                    if(find_zero_number_status_ListResult1.size() == 1)
                                    {
                                        // Jpa的自动事务会去数据库修改对应行的Medicinenumber_status状态值为0，表示该药品已用完
                                        find_zero_number_status_ListResult1.get(0).setMedicinenumber_status(0);
                                    }
                                }

                            }


//202010142056end-------------------------------------------------------------------------------------------------------------------------


























                            // 定义最快过期的时间的时间戳 赋予初值为第0个元素的值

//                            Shortest_time = df.parse(findByConditiona_Result.get(0).getExpiredtime()).getTime();
                            // 设初始值为Long类型默认范围的最大值（这里假设为无穷大）
                            Shortest_time = Long.MAX_VALUE;
//                            System.out.println("叶佳打点计时器log： 药品：" + findByConditiona_Result.get(z).getMedicinename() + "当前共有记录数" + findByConditiona_Result.size());
                            for (int p = 0; p < findByConditiona_Result.size(); p++) {
//                            System.out.println("addnewmedicinefindByConditiona_Result的结果：" + findByConditiona_Result);
//                            System.out.println("addnewmedicinefindByConditiona_Result的长度：" + findByConditiona_Result.size());
//                                System.out.println("詹姆斯log~~~~：" + findByConditiona_Result.get(p).getMedicinenumber());
                                if(findByConditiona_Result.get(p).getMedicinenumber_status() != 0)
                                {
                                    if (df.parse(findByConditiona_Result.get(p).getExpiredtime()).getTime() < Shortest_time) {
                                        Shortest_time = df.parse(findByConditiona_Result.get(p).getExpiredtime()).getTime();
                                    }
                                }

                            }



//                            System.out.println("叶佳打点计时器log： 药品：" + findByConditiona_Result.get(z).getMedicinename() + "的最短日期是" + df.format(Shortest_time));
                        }
                    } catch (Exception e)
                    {
                        map.put("info", "bad messsage1.");
                    }
                    // 判断是否过期
                    // 获取当前系统时间
                    Date date = new Date();
//                        System.out.println("当前时间的时间戳" + date.getTime());
//                        System.out.println("药品过期时间的时间戳" + df.parse(materials_medicine_managementRepository.findAll(pageable).getContent().get(i).getExpired_time()).getTime());
//                        System.out.println("药品过期时间的时间戳" + Shortest_time);

//                    materials_medicine_managementRepository.findAll(pageable).getContent().get(z).setExpired_time(df.format(Shortest_time));
                    last_expired_time_array1[z] = df.format(Shortest_time);
//                    materials_medicine_managementRepository.findAll(pageable).getContent().get(z).setMedicine_number(totaladdnumber1);

                    // 时间戳相减的值
//                        long substuction_time = df.parse(materials_medicine_managementRepository.findAll(pageable).getContent().get(i).getExpired_time()).getTime() - date.getTime();
                    long substuction_time = Shortest_time - date.getTime();
                    if (substuction_time >= 0) {
                        // 相减后转换为天数
//                        System.out.println("相减后的天数：" + substuction_time / 1000 / 3600 / 24);
                        if ((substuction_time / 1000 / 3600 / 24) <= system_settingRepository.findById(1202526328).get().getMedicine_expired_warning()) {
//                            materials_medicine_managementRepository.findAll(pageable).getContent().get(z).setExpired_time_status("预过期");
                            last_expired_status_array1[z] = "预过期";
                        } else {
//                            materials_medicine_managementRepository.findAll(pageable).getContent().get(z).setExpired_time_status("日期正常");
                            last_expired_status_array1[z] = "日期正常";
                        }
                    } else {
//                        System.out.println("已过期");
//                        materials_medicine_managementRepository.findAll(pageable).getContent().get(z).setExpired_time_status("已过期");
                        last_expired_status_array1[z] = "已过期";
                    }

                }
            }

            System.out.print("log4--last_number_array1[]=[");
            for(int x = 0; x < materials_length; x++)
            {
                System.out.print(last_number_array1[x] + ",");
            }
            System.out.print("]");
            System.out.print("log4--last_expired_time_array1[]=[");
            for(int x = 0; x < materials_length; x++)
            {
                System.out.print(last_expired_time_array1[x] + ",");
            }
            System.out.print("]");
            System.out.print("log4--last_number_status_array1[]=[");
            for(int x = 0; x < materials_length; x++)
            {
                System.out.print(last_number_status_array1[x] + ",");
            }
            System.out.print("]");
            System.out.print("log4--last_expired_status_array1[]=[");
            for(int x = 0; x < materials_length; x++)
            {
                System.out.print(last_expired_status_array1[x] + ",");
            }
            System.out.print("]");


            Session session3 = entityManager.unwrap(org.hibernate.Session.class);
            for(int q = 0; q < materials_length; q++)
            {
                materials_medicine_managementRepository.findAll(pageable).getContent().get(q).setMedicine_number(
                        last_number_array1[q]
                );
                materials_medicine_managementRepository.findAll(pageable).getContent().get(q).setExpired_time(
                        last_expired_time_array1[q]
                );
                materials_medicine_managementRepository.findAll(pageable).getContent().get(q).setStorage_number_status(
                        last_number_status_array1[q]
                );
                materials_medicine_managementRepository.findAll(pageable).getContent().get(q).setExpired_time_status(
                        last_expired_status_array1[q]
                );
            }
            map.put("SelectDataResult", materials_medicine_managementRepository.findAll(pageable));
            for(int r = 0; r < materials_length; r++)
            {
                session3.evict(materials_medicine_managementRepository.findAll(pageable).getContent().get(r));
            }
        }else
        {
            map.put("info", "no messsage.");
            map.put("SelectDataResult", materials_medicine_managementRepository.findAll(pageable));
        }


        //分割线end-------------同步数量的数据到物资管理表-----------------------------

//        if(materials_medicine_managementRepository.findAll(pageable).getContent().size() == 0)
//        {
//            map.put("info", "no messsage.");
//            map.put("SelectDataResult", materials_medicine_managementRepository.findAll(pageable));
//        }else
//        {
//            map.put("SelectDataResult", materials_medicine_managementRepository.findAll(pageable));
//        }
        return map;
    }



    // 根据多条件返回dialog框需要的多行数据
    @GetMapping("/findmaterialsmedicinebycondition/{medicinename}/{medicinebox}")
    public Map<String, Object> findByConditionexport123(@PathVariable("medicinename") String medicinename,
                                                        @PathVariable("medicinebox") String medicinebox) {
        System.out.println("前端传来了要详细查看的药柜信息：" + medicinename + "$$$" + medicinebox);
        Map<String, Object> map = new HashMap<>();
        try{
            List<Addnewmedicine> findByConditiona_Resultexport123 = addnewmedicineService.findByCondition(medicinename, medicinebox);
            // 根据该药品柜的名称和药品名称查出对应的药品过期时间 再根据查出来的药品对应的过期时间 加前端传来的药品名和药柜名三个条件一起去出库表插对应的出库数量 做减法
//            System.out.println("i&&&&&&&&&&&&&的循环次数" + findByConditiona_Resultexport123.size());
            for(int i = 0; i < findByConditiona_Resultexport123.size(); i++)
            {
//                System.out.println("药品数量打点1@@@@@@@@" + findByConditiona_Resultexport123.get(i).getMedicinenumber());
                List<Export_chemical_medicine> findByCondition_Export_chemical_medicine_ListResult = export_chemical_medicineService.findByCondition(findByConditiona_Resultexport123.get(i).getMedicinename(),
                        findByConditiona_Resultexport123.get(i).getMedicinebox(), findByConditiona_Resultexport123.get(i).getExpiredtime());
                // 如果对应药品有出库记录 则查询出来出库数量相减
                if(!findByCondition_Export_chemical_medicine_ListResult.isEmpty())
                {
//                    System.out.println("药品数量打点2@@@@@@@@" + findByConditiona_Resultexport123.get(i).getMedicinenumber());
                    // 总出库数量
                    int total_export_number = 0;
//                    System.out.println("j&&&&&&&&&&&的循环次数" + findByCondition_Export_chemical_medicine_ListResult.size());
                    for(int j = 0; j < findByCondition_Export_chemical_medicine_ListResult.size(); j++)
                    {
                        total_export_number += findByCondition_Export_chemical_medicine_ListResult.get(j).getMedicine_number();
                    }
                    // 库存的数量
                    int storage_number = findByConditiona_Resultexport123.get(i).getMedicinenumber();
                    // 相减后得到实际剩余数量
                    storage_number -= total_export_number;
//                    System.out.println("这是storage_number最终的值：" + storage_number);
//                    System.out.println("这是total_export_number最终的值：" + total_export_number);
//                    System.out.println("当前需要设置的出库行原始查询数量为：" + findByConditiona_Resultexport123.get(i).getMedicinenumber());
                    Session session = entityManager.unwrap(org.hibernate.Session.class);
//                    System.out.println("叶佳打点1");
                    findByConditiona_Resultexport123.get(i).setMedicinenumber(storage_number);
//                    System.out.println("叶佳打点2");
                    for (int k = 0; k < findByConditiona_Resultexport123.size(); k++)
                    {
                        session.evict(findByConditiona_Resultexport123.get(k));
                    }
//                    session.evict(findByConditiona_Resultexport123);
//                    System.out.println("当前需要设置的出库行原始查询数量相减赋值后为：" + findByConditiona_Resultexport123.get(i).getMedicinenumber());
//                    System.out.println("药品数量打点3@@@@@@@@" + findByConditiona_Resultexport123.get(i).getMedicinenumber());
//                    System.out.println("药柜" +findByConditiona_Resultexport123.get(i).getMedicinebox()+ "中日期为：" +findByConditiona_Resultexport123.get(i).getExpiredtime()+ "的药品" +findByConditiona_Resultexport123.get(i).getMedicinename()+ "有出柜记录，共出柜了："
//                            +total_export_number+ "瓶，库存实际还剩余：" +storage_number+ "瓶");

                }else
                {
//                    System.out.println("药品数量打点4@@@@@@@@" + findByConditiona_Resultexport123.get(i).getMedicinenumber());
//                    System.out.println("药柜" +findByConditiona_Resultexport123.get(i).getMedicinebox()+ "中日期为：" +findByConditiona_Resultexport123.get(i).getExpiredtime()+ "的药品：" +findByConditiona_Resultexport123.get(i).getMedicinename()+ "没有出柜记录");
//                    System.out.println("药柜" +findByConditiona_Resultexport123.get(i).getMedicinebox()+ "中日期为：" +findByConditiona_Resultexport123.get(i).getExpiredtime()+ "的药品：" +findByConditiona_Resultexport123.get(i).getMedicinename()+ "有：" +findByConditiona_Resultexport123.get(i).getMedicinenumber()+ "瓶");

                }
            }


            if(findByConditiona_Resultexport123.isEmpty())
            {
                map.put("msg", "查询结果为空");
            }else
            {
                map.put("findByConditiona_Resultexport123", findByConditiona_Resultexport123);
            }
        } catch (Exception e) {
            map.put("msg", "error");
        }
        return map;

    }






    //----------------------------------------------202010151741给通知表的数据
    //----------------------------------------------202010151741给通知表的数据
    //----------------------------------------------202010151741给通知表的数据
    //----------------------------------------------202010151741给通知表的数据
    //----------------------------------------------202010151741给通知表的数据
    //----------------------------------------------202010151741给通知表的数据
    //----------------------------------------------202010151741给通知表的数据
    //----------------------------------------------202010151741给通知表的数据
    //----------------------------------------------202010151741给通知表的数据
    //----------------------------------------------202010151741给通知表的数据
    // 前端手动调用一次启动通知表的数据刷新
    @GetMapping("/findmaterialsmedicinetonotification/{page}/{size}/{sortType}/{sortableFields}")
    public Map<String, Object> findAlltonotification(
            @PathVariable("page") Integer page, // 第几页
            @PathVariable("size") Integer size, // 显示多少条
            @PathVariable("sortType") String sortType, // 正序还是逆序
            @PathVariable("sortableFields") String sortableFields //需要按照哪一个字段域来排序
    ) {
        System.out.println("前端手动调用一次启动通知表的数据刷新"+page+size+sortType+sortableFields);
        Map<String, Object> map = new HashMap<>();

//        try{
        //判断排序类型及排序字段
        Sort sort = "ascending".equals(sortType) ? by(Sort.Direction.ASC, sortableFields) : by(Sort.Direction.DESC, sortableFields);
        //获取pageable
        Pageable pageable = PageRequest.of(page-1,size,sort);
//            System.out.println("输出查询的分页" + materials_medicine_managementRepository.findAll(pageable).getContent().get(0).getMedicine_number());
//            System.out.println("输出查询的分页" + materials_medicine_managementRepository.findAll(pageable).getContent().get(1).getMedicine_number());
        // 获取当前分页数组数据的长度
        //分割线start-------------同步数量的数据到物资管理表-----------------------------
        // 当前页数据行数
        int materials_length = materials_medicine_managementRepository.findAll(pageable).getContent().size();
        if(materials_length != 0)
        {
            Page<Materials_medicine_management> materials_medicine_management_finallResult1 = null;
            int[] materials_medicine_management_aftersub_array1 = new int[materials_length];
            List<Materials_medicine_management> get_notification_dataList1 = new ArrayList<>();
            for(int i = 0; i < materials_length; i++)
            {
                List<Addnewmedicine> AddnewmedicineList_Result1 = addnewmedicineService.findByCondition(
                        materials_medicine_managementRepository.findAll(pageable).getContent().get(i).getMedicine_name(),
                        materials_medicine_managementRepository.findAll(pageable).getContent().get(i).getMedicine_box());
//                int materials_medicine_management_aftersub_number1 = 0;
//                System.out.println("打点计时器---------------------：" + AddnewmedicineList_Result1);
                if(AddnewmedicineList_Result1.size() != 0)
                {
                    //？？？ 当前行数据统一药品名称，统一药柜的药品减去出库后剩余数量总数
                    int linestorage_number_total1 = 0;
                    // 当前药品名，药柜号的
                    for(int j = 0; j < AddnewmedicineList_Result1.size(); j++)
                    {
                        List<Export_chemical_medicine> Export_chemical_medicineList_Result1 = export_chemical_medicineService.findByCondition(
                                AddnewmedicineList_Result1.get(j).getMedicinename(),
                                AddnewmedicineList_Result1.get(j).getMedicinebox(),
                                AddnewmedicineList_Result1.get(j).getExpiredtime()
                        );

                        if(Export_chemical_medicineList_Result1.size() != 0)
                        {
                            // 当前药品名称，药品柜以及药品过期时间的出库数量总和
                            int Export_chemical_medicine_totalnumber1 = 0;
                            for(int k = 0; k < Export_chemical_medicineList_Result1.size(); k++)
                            {
                                if(Export_chemical_medicineList_Result1.size() != 0)
                                {
                                    Export_chemical_medicine_totalnumber1 += Export_chemical_medicineList_Result1.get(k).getMedicine_number();
                                }
                            }

                            int storage_number1 = AddnewmedicineList_Result1.get(j).getMedicinenumber();
                            storage_number1 -= Export_chemical_medicine_totalnumber1;
//                            System.out.println("log1--storage_number1:" + storage_number1);

                            Session session1 = entityManager.unwrap(org.hibernate.Session.class);
                            AddnewmedicineList_Result1.get(j).setMedicinenumber(storage_number1);

                            linestorage_number_total1 +=  AddnewmedicineList_Result1.get(j).getMedicinenumber();
                            for(int m = 0; m < AddnewmedicineList_Result1.size(); m++)
                            {
                                session1.evict(AddnewmedicineList_Result1.get(m));
                            }
                        }else
                        {
                            linestorage_number_total1 +=  AddnewmedicineList_Result1.get(j).getMedicinenumber();
                        }


                    }
//                    System.out.println("log2--linestorage_number_total1:" + linestorage_number_total1);

                    Session session2 = entityManager.unwrap(org.hibernate.Session.class);
                    materials_medicine_managementRepository.findAll(pageable).getContent().get(i).setMedicine_number(linestorage_number_total1);
                    materials_medicine_management_aftersub_array1[i] = materials_medicine_managementRepository.findAll(pageable).getContent().get(i).getMedicine_number();
                    session2.evict(materials_medicine_managementRepository.findAll(pageable).getContent().get(i));

                }

//                System.out.println("log3--materials_medicine_management_finallResult1.getContent().get(i):" + materials_medicine_management_finallResult1.getContent().get(i));
            }

            // 数据统计页需要其当前最新库存总量数据
            Integer Total_materials_medicine_number = 0;

            // 数据统计页需要其当前最新库存不足的总量数据
            Integer Total_Inadequate_Numbers = 0;

            // 数据统计页需要其当前最新预过期库存的总量数据
            Integer Total_Pre_expired_number = 0;

            // 需要临时存储的最近的库存数量字段数组
            int[] last_number_array1 = new int[materials_length];
            // 需要临时存储的最近的过期时间字段数组
            String[] last_expired_time_array1 = new String[materials_length];
            // 需要临时存储的最近的库存状态字段数组
            String[] last_number_status_array1 = new String[materials_length];
            // 需要临时存储的最近的过期状态字段数组
            String[] last_expired_status_array1 = new String[materials_length];

            // 回传给前端前做实际药品数量同步操作，仅前端变化
            for(int n = 0; n < materials_length; n++)
            {
                last_number_array1[n] = materials_medicine_management_aftersub_array1[n];
            }


            // 回传前, 做库存状态判断
            for (int y = 0; y < materials_length; y++) {
//                System.out.println("y的长度" + y);
                // 判断库存
                if (materials_medicine_management_aftersub_array1[y] > system_settingRepository.findById(1202526328).get().getMedicine_number_warning()) {
//                    materials_medicine_managementRepository.findAll(pageable).getContent().get(y).setStorage_number_status("库存正常");
                    last_number_status_array1[y] = "库存正常";
                } else if (materials_medicine_management_aftersub_array1[y] > 0) {
//                    materials_medicine_managementRepository.findAll(pageable).getContent().get(y).setStorage_number_status("库存不足");
                    last_number_status_array1[y] = "库存不足";
                } else {
//                    materials_medicine_managementRepository.findAll(pageable).getContent().get(y).setStorage_number_status("库存为空");
                    last_number_status_array1[y] = "库存为空";
                }
            }

            // 回传判断最近快要过期的药品时间信息
            for(int z = 0; z < materials_length; z++)
            {
                // 毫秒值加减 时间戳
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                // 当前行的药品名称
                String cache_medicinename = materials_medicine_managementRepository.findAll(pageable).getContent().get(z).getMedicine_name();
//                System.out.println("当前行的药品名称的结果：" + cache_medicinename);
                // 当前行对应的药瓶柜位置
                String cache_medicinebox = materials_medicine_managementRepository.findAll(pageable).getContent().get(z).getMedicine_box();
//                System.out.println("当前行对应的药瓶柜位置的结果：" + cache_medicinebox);
                // 比较入库药品的过期时间 找出离过期最近的时间 赋值给物资管理里面的过期时间字段
                List<Addnewmedicine> findByConditiona_Result = addnewmedicineService.findByCondition(cache_medicinename, cache_medicinebox);
//                System.out.println("詹姆斯log条件1~~~~:" + cache_medicinename);
//                System.out.println("詹姆斯log条件2~~~~:" + cache_medicinebox);
                if(findByConditiona_Result.isEmpty())
                {
                    map.put("info", "j没有什么需要返回的");
                }else
                {
                    long Shortest_time = 0;
                    // 当条件查询只有一行数据时
                    try {

                        if(findByConditiona_Result.size() == 1)
                        {
                            Shortest_time = df.parse(findByConditiona_Result.get(0).getExpiredtime()).getTime();
                        }
                        else
                        {
//202010142056start-------------------------------------------------------------------------------------------------------------------------

//                            System.out.println("詹姆斯log条件1~~~~:" + findByConditiona_Result.get(z).getMedicinename());
//                            System.out.println("詹姆斯log条件2~~~~:" + findByConditiona_Result.get(z).getMedicinebox());
//                            System.out.println("詹姆斯log条件3~~~~:" + findByConditiona_Result.get(z).getExpiredtime());
                            // 记录所有已经用完了的药(相减后数量小于等于零)的下标
//                            List<Integer> less_than_zero_list1 = new ArrayList<>();
                            // 判断当前药品数量是否已使用完，如果已经使用完了就不参与最短时间计算了
                            for(int i = 0; i < findByConditiona_Result.size(); i++)
                            {
//                                System.out.println("詹姆斯logsize~~~~:" + findByConditiona_Result.size() + "-" +
//                                        findByConditiona_Result.get(i).getMedicinename() + "-" +
//                                        findByConditiona_Result.get(i).getMedicinebox() + "-" +
//                                        findByConditiona_Result.get(i).getExpiredtime());
                                List<Export_chemical_medicine> find_medicine_number_exist_Result = export_chemical_medicineService.findByCondition(
                                        findByConditiona_Result.get(i).getMedicinename(),
                                        findByConditiona_Result.get(i).getMedicinebox(),
                                        findByConditiona_Result.get(i).getExpiredtime()
                                );

                                // 当前药品的所库数量之和
                                int export_totalnumber1 = 0;


                                if(!find_medicine_number_exist_Result.isEmpty())
                                {
                                    for(int s = 0; s < find_medicine_number_exist_Result.size(); s++)
                                    {
                                        export_totalnumber1 += find_medicine_number_exist_Result.get(s).getMedicine_number();
                                    }
//                                    System.out.println("詹姆斯export_totalnumber1:" + export_totalnumber1);
                                }

                                if(findByConditiona_Result.get(i).getMedicinenumber() - export_totalnumber1 <= 0)
                                {

//                                    less_than_zero_list1.add(i);
//                                    System.out.println("詹姆斯小小小----要清零的药品名字为:" + findByConditiona_Result.get(i).getMedicinename() + "$$$" +
//                                            "过期日期为：" + findByConditiona_Result.get(i).getExpiredtime() + "$$$" +
//                                            "药柜号为：" + findByConditiona_Result.get(i).getMedicinebox() + "$$$" +
//                                            "实际剩余数量为:" + (findByConditiona_Result.get(i).getMedicinenumber() - export_totalnumber1));
                                    List<Addnewmedicine> find_zero_number_status_ListResult1 = addnewmedicineService.findByConditionthree(
                                            findByConditiona_Result.get(i).getMedicinename(),
                                            findByConditiona_Result.get(i).getMedicinebox(),
                                            findByConditiona_Result.get(i).getExpiredtime()
                                    );
                                    if(find_zero_number_status_ListResult1.size() == 1)
                                    {
                                        // Jpa的自动事务会去数据库修改对应行的Medicinenumber_status状态值为0，表示该药品已用完
                                        find_zero_number_status_ListResult1.get(0).setMedicinenumber_status(0);
                                    }
                                }

                            }


//202010142056end-----------------------------------------------------------------------------------------------------------------------
                            // 定义最快过期的时间的时间戳 赋予初值为第0个元素的值

//                            Shortest_time = df.parse(findByConditiona_Result.get(0).getExpiredtime()).getTime();
                            // 设初始值为Long类型默认范围的最大值（这里假设为无穷大）
                            Shortest_time = Long.MAX_VALUE;
//                            System.out.println("叶佳打点计时器log： 药品：" + findByConditiona_Result.get(z).getMedicinename() + "当前共有记录数" + findByConditiona_Result.size());
                            for (int p = 0; p < findByConditiona_Result.size(); p++) {
//                            System.out.println("addnewmedicinefindByConditiona_Result的结果：" + findByConditiona_Result);
//                            System.out.println("addnewmedicinefindByConditiona_Result的长度：" + findByConditiona_Result.size());
//                                System.out.println("詹姆斯log~~~~：" + findByConditiona_Result.get(p).getMedicinenumber());
                                if(findByConditiona_Result.get(p).getMedicinenumber_status() != 0)
                                {
                                    if (df.parse(findByConditiona_Result.get(p).getExpiredtime()).getTime() < Shortest_time) {
                                        Shortest_time = df.parse(findByConditiona_Result.get(p).getExpiredtime()).getTime();
                                    }
                                }

                            }

//                            System.out.println("叶佳打点计时器log： 药品：" + findByConditiona_Result.get(z).getMedicinename() + "的最短日期是" + df.format(Shortest_time));
                        }
                    } catch (Exception e)
                    {
                        map.put("info", "bad messsage.");
                    }
                    // 判断是否过期
                    // 获取当前系统时间
                    Date date = new Date();
//                        System.out.println("当前时间的时间戳" + date.getTime());
//                        System.out.println("药品过期时间的时间戳" + df.parse(materials_medicine_managementRepository.findAll(pageable).getContent().get(i).getExpired_time()).getTime());
//                        System.out.println("药品过期时间的时间戳" + Shortest_time);

//                    materials_medicine_managementRepository.findAll(pageable).getContent().get(z).setExpired_time(df.format(Shortest_time));
                    last_expired_time_array1[z] = df.format(Shortest_time);
//                    materials_medicine_managementRepository.findAll(pageable).getContent().get(z).setMedicine_number(totaladdnumber1);

                    // 时间戳相减的值
//                        long substuction_time = df.parse(materials_medicine_managementRepository.findAll(pageable).getContent().get(i).getExpired_time()).getTime() - date.getTime();
                    long substuction_time = Shortest_time - date.getTime();
                    if (substuction_time >= 0) {
                        // 相减后转换为天数
//                        System.out.println("相减后的天数：" + substuction_time / 1000 / 3600 / 24);
                        if ((substuction_time / 1000 / 3600 / 24) <= system_settingRepository.findById(1202526328).get().getMedicine_expired_warning()) {
//                            materials_medicine_managementRepository.findAll(pageable).getContent().get(z).setExpired_time_status("预过期");
                            last_expired_status_array1[z] = "预过期";
                        } else {
//                            materials_medicine_managementRepository.findAll(pageable).getContent().get(z).setExpired_time_status("日期正常");
                            last_expired_status_array1[z] = "日期正常";
                        }
                    } else {
//                        System.out.println("已过期");
//                        materials_medicine_managementRepository.findAll(pageable).getContent().get(z).setExpired_time_status("已过期");
                        last_expired_status_array1[z] = "已过期";
                    }

                }
            }

            System.out.print("log4--last_number_array1[]=[");
            for(int x = 0; x < materials_length; x++)
            {
                System.out.print(last_number_array1[x] + ",");
                Total_materials_medicine_number += last_number_array1[x];
            }
            System.out.print("]");
            System.out.print("log4--last_expired_time_array1[]=[");
            for(int x = 0; x < materials_length; x++)
            {
                System.out.print(last_expired_time_array1[x] + ",");
            }
            System.out.print("]");
            System.out.print("log4--last_number_status_array1[]=[");
            for(int x = 0; x < materials_length; x++)
            {
                System.out.print(last_number_status_array1[x] + ",");
            }
            System.out.print("]");
            System.out.print("log4--last_expired_status_array1[]=[");
            for(int x = 0; x < materials_length; x++)
            {
                System.out.print(last_expired_status_array1[x] + ",");
            }
            System.out.print("]");
            System.out.println("-*/!@#$%^&*()_+-*/!@#$%^&*()_+-*/!@#$%^&*()_+-*/!@#$%^&*()_+");

            Session session3 = entityManager.unwrap(org.hibernate.Session.class);
            for(int q = 0; q < materials_length; q++)
            {
                materials_medicine_managementRepository.findAll(pageable).getContent().get(q).setMedicine_number(
                        last_number_array1[q]
                );
                materials_medicine_managementRepository.findAll(pageable).getContent().get(q).setExpired_time(
                        last_expired_time_array1[q]
                );
                materials_medicine_managementRepository.findAll(pageable).getContent().get(q).setStorage_number_status(
                        last_number_status_array1[q]
                );
                materials_medicine_managementRepository.findAll(pageable).getContent().get(q).setExpired_time_status(
                        last_expired_status_array1[q]
                );
            }
            map.put("SelectDataResult", materials_medicine_managementRepository.findAll(pageable));
            map.put("Total_materials_medicine_number", Total_materials_medicine_number);
            // 防止自动事务刷数据库，后面还要用到materials_medicine_managementRepository.findAll(pageable).getContent()的内容，所以请空前要存储一下
            get_notification_dataList1 = materials_medicine_managementRepository.findAll(pageable).getContent();
            for(int r = 0; r < materials_length; r++)
            {
                session3.evict(materials_medicine_managementRepository.findAll(pageable).getContent().get(r));
            }
            System.out.println("来了 宝贝：" + get_notification_dataList1);

            // 202010191139 算出当前库存不足商品总量和预过期商品库存总量的操作 ----------------------start----------------------------------------------

            for(int totalnotenoughx1 = 0; totalnotenoughx1 < get_notification_dataList1.size(); totalnotenoughx1++)
            {
                System.out.println("叶佳再次打了一个点log1：" + get_notification_dataList1.get(totalnotenoughx1).getStorage_number_status());
                System.out.println("叶佳再次打了一个点log2：" + get_notification_dataList1.get(totalnotenoughx1).getMedicine_number());
                if(get_notification_dataList1.get(totalnotenoughx1).getStorage_number_status().equals("库存不足"))
                {
                    Total_Inadequate_Numbers += get_notification_dataList1.get(totalnotenoughx1).getMedicine_number();
                }else if(get_notification_dataList1.get(totalnotenoughx1).getExpired_time_status().equals("预过期"))
                {
                    Total_Pre_expired_number += get_notification_dataList1.get(totalnotenoughx1).getMedicine_number();
                }
            }
            System.out.println("叶佳再次打了一个点log3：" + Total_Inadequate_Numbers);
            System.out.println("叶佳再次打了一个点log4：" + Total_Pre_expired_number);
            map.put("Total_Inadequate_Numbers", Total_Inadequate_Numbers);
            map.put("Total_Pre_expired_number", Total_Pre_expired_number);

            // 202010191139 算出当前库存不足商品总量和预过期商品库存总量的操作 ---------------------- end ----------------------------------------------

            // 202010161013 更新通知消息表的操作 ----------------------start----------------------------------------------


            System.out.println("notification table**********:" + get_notification_dataList1 + "长度" + get_notification_dataList1.size());
            int notice_length = 0;
            notice_length = get_notification_dataList1.size();
            for(int noti_x1 = 0; noti_x1 < notice_length; noti_x1++)
            {
                // 检查每天可能需要添加的新通知消息是否已经通知过了
                List<Notification> exist_notice_ListResult1 = notificationService.findByNotificationCondition(
                        get_notification_dataList1.get(noti_x1).getMedicine_name(),
                        get_notification_dataList1.get(noti_x1).getMedicine_box(),
                        get_notification_dataList1.get(noti_x1).getExpired_time()
                );

                if(exist_notice_ListResult1.isEmpty())
                {
                    if(get_notification_dataList1.get(noti_x1).getExpired_time_status() == "预过期")
                    {
                        try {
                            Date todaydate = new Date();
//                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            long this_medicine_expired_time = sdf.parse(get_notification_dataList1.get(noti_x1).getExpired_time()).getTime();
                            // 预过期产品计算出来还有多少天过期的日期天数
                            int calculated_day = 0;
                            calculated_day = ( (int)((this_medicine_expired_time - todaydate.getTime()) / 1000 / 3600 / 24) );

                            Notification notification1 = new Notification();
                            notification1.setMedicine_name(get_notification_dataList1.get(noti_x1).getMedicine_name());
                            notification1.setMedicine_box(get_notification_dataList1.get(noti_x1).getMedicine_box());
                            notification1.setExpired_time(get_notification_dataList1.get(noti_x1).getExpired_time());
                            notification1.setExpired_number(calculated_day);
                            notification1.setNotice_time(sdf1.format(todaydate));
                            notification1.setNotice_status(0);
                            notificationRepository.save(notification1);

                            if(get_notification_dataList1.get(noti_x1).getStorage_number_status() == "库存不足")
                            {
                                Notification notification2 = new Notification();
                                notification2.setMedicine_name(get_notification_dataList1.get(noti_x1).getMedicine_name());
                                notification2.setMedicine_box(get_notification_dataList1.get(noti_x1).getMedicine_box());
                                notification2.setExpired_time(get_notification_dataList1.get(noti_x1).getExpired_time());
                                notification2.setMedicine_number(get_notification_dataList1.get(noti_x1).getMedicine_number());
                                notification2.setNotice_time(sdf1.format(todaydate));
                                notification2.setNotice_status(0);
                                notificationRepository.save(notification2);
                                System.out.println("当前预过期物品库存也不足了，附加更新了库存不足的通知信息成功！");
                            }
                            System.out.println("更新预过期通知信息成功！");
                        } catch (Exception e) {
                            System.out.println("更新预过期通知信息失败！");
                        }
                    }else if(get_notification_dataList1.get(noti_x1).getStorage_number_status() == "库存不足")
                    {
                        Date todaydate1 = new Date();
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Notification notification3 = new Notification();
                        notification3.setMedicine_name(get_notification_dataList1.get(noti_x1).getMedicine_name());
                        notification3.setMedicine_box(get_notification_dataList1.get(noti_x1).getMedicine_box());
                        notification3.setExpired_time(get_notification_dataList1.get(noti_x1).getExpired_time());
                        notification3.setMedicine_number(get_notification_dataList1.get(noti_x1).getMedicine_number());
                        notification3.setNotice_time(sdf2.format(todaydate1));
                        notification3.setNotice_status(0);
                        notificationRepository.save(notification3);
                        System.out.println("更新库存不足通知信息成功！");
                    }
                }else
                {
                    System.out.println("本次刷新没有需要新添加的消息通知！");
                }

            }

            // 202010161013 更新通知消息表的操作 ----------------------end------------------------------------------------

        }else
        {
            map.put("info", "no messsage.");
            map.put("SelectDataResult", materials_medicine_managementRepository.findAll(pageable));
            map.put("Total_materials_medicine_number", 0);
            map.put("Total_Inadequate_Numbers", 0);
            map.put("Total_Pre_expired_number", 0);
        }


        //分割线end-------------同步数量的数据到物资管理表-----------------------------

        // 计算出来的数据，什么返回值都不需要就解开这行注释
        // map.clear();

        // 这里我需要Total_materials_medicine_number，但是不需要返回SelectDataResult，那就把它从键值对中清除掉
        // (其实前面我不进行put SelectDataResult操作，这里根本就不需要用迭代器去查找删除SelectDataResult键值对)
        // 但是就单纯的想试试Iterator的功能好使不~~~~~ hahahahah ~~~~~~
        // map中key（键）的迭代器对象
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){// 循环取键值进行判断
            String key = iterator.next();// 键
            if(key.startsWith("SelectDataResult")){
                iterator.remove();// 移除map中以SelectDataResult字符开头的键对应的键值对
            }
        }
        map.put("result", "update notification success");

        return map;
    }

}
