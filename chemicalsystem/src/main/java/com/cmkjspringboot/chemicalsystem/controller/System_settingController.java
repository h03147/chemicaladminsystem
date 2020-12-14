package com.cmkjspringboot.chemicalsystem.controller;

import com.cmkjspringboot.chemicalsystem.entity.System_setting;
import com.cmkjspringboot.chemicalsystem.repository.System_settingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/systemsetting")
public class System_settingController {

    @Autowired
    private System_settingRepository system_settingRepository;

    @GetMapping("/lovestory")
    public System_setting findSystemsetting() {
        return system_settingRepository.findById(1202526328).get();
    }

    @PostMapping("/cardigan")
    public Map<String, Object> updateSystemsetting(@RequestBody System_setting system_setting) {
        System.out.println("前端传来的设置修改要求：" + system_setting);
        Map<String, Object> map = new HashMap<>();
        try{
            if(system_setting.getId() == 1202526328)
            {
                System_setting save_Result1 = system_settingRepository.save(system_setting);
                if(save_Result1 != null)
                {
                    map.put("state", "ok");
                }else
                {
                    map.put("state", "failed");
                }
            }else
            {
                map.put("state", "bad message");
            }
        } catch (Exception e) {
            map.put("state", "throw bad message");
        }
        return map;
    }
}
