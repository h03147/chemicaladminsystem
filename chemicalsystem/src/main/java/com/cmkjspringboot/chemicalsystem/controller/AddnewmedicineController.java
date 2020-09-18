package com.cmkjspringboot.chemicalsystem.controller;

import com.cmkjspringboot.chemicalsystem.entity.Addnewmedicine;
import com.cmkjspringboot.chemicalsystem.entity.User;
import com.cmkjspringboot.chemicalsystem.repository.AddnewmedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.domain.Sort.by;

@RestController
@RequestMapping("/Addnewmedicine")
public class AddnewmedicineController {

    @Autowired
    private AddnewmedicineRepository addnewmedicineRepository;

    //分页查询新药表信息并且绑定id字段来进行正序逆序排序
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
        Addnewmedicine addResult = addnewmedicineRepository.save(addnewmedicine);
        if(addResult != null)
        {
            return "新药添加成功";
        }else
        {
            return "糟糕！ 未知的错误";
        }
    }
}
