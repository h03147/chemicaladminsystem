package com.cmkjspringboot.chemicalsystem.controller;

import com.cmkjspringboot.chemicalsystem.dao.UserDao;
import com.cmkjspringboot.chemicalsystem.entity.User;
import com.cmkjspringboot.chemicalsystem.repository.UserRepository;
import com.cmkjspringboot.chemicalsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.data.domain.Sort.by;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDao userDao;

    //BCrypt密码加密的注入 Bcrypt是单向Hash加密算法，类似Pbkdf2算法 不可反向破解生成明文。
//    Bcrypt有四个变量：
//
//    saltRounds: 正数，代表hash杂凑次数，数值越高越安全，默认10次。
//    myPassword: 明文密码字符串。
//    salt: 盐，一个128bits随机字符串，22字符
//    myHash: 经过明文密码password和盐salt进行hash，个人的理解是默认10次下 ，循环加盐hash10次，得到myHash
//    每次明文字符串myPassword过来，就通过10次循环加盐salt加密后得到myHash, 然后拼接BCrypt版本号+salt盐+myHash等到最终的bcrypt密码 ，存入数据库中。
//
//    作者：martin6699
//    链接：https://www.jianshu.com/p/2b131bfc2f10
//    来源：简书
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    //用户登录
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {
        System.out.println("化学品前端传来的user：" + user);
        Map<String, Object> map = new HashMap<>();
        try {
            User userDB = userService.login(user);
            map.put("state", true);
            map.put("msg", "登录成功！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    //查询全部用户并分页返回
    @GetMapping("/userfindAll/{page}/{size}")
    public Page<User> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        // 使用Jpa封装好的page方法page-1是因为数组从0开始的前端传过来第1页实际上是数组的第0页
        Pageable pageable = PageRequest.of(page - 1, size);
        return userRepository.findAll(pageable);
    }

    //查询用户分页并且绑定id字段来进行正序逆序排序
    @GetMapping("/findandsortPageable/{page}/{size}/{sortType}/{sortableFields}")
    public Page<User> testPageable(
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
        return userRepository.findAll(pageable);

    }

    //添加人员信息
    @PostMapping("/addsystemuser")
    public String addsystemuser(@RequestBody User user) {
        System.out.println("前端传过来的东西在这呢！"+ user);
        //新增前先查看账号是否已存在，如果已存在就驳回插入操作！
        User findByusernumberResult = userDao.findByUsernumber(user.getUsernumber());
        if(findByusernumberResult == null)
        {
            System.out.println("1----原始密码"+user.getPassword());
            String abc = user.getPassword();
            System.out.println("2----密码加密后"+bCryptPasswordEncoder.encode(abc));
            user.setPassword(bCryptPasswordEncoder.encode(abc)); //密码加密后插入数据库
            User userResult = userRepository.save(user);
            if(userResult != null)
            {
                return "添加人员信息成功";
            }else
            {
                return "非常抱歉的通知您，添加人员失败! 请重试";
            }
        }else
        {
            return "怎么办~0q0~,该人员账号已经存在了,换一个试试~";
        }


    }

    // 根据id查询对应行人员信息
    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") Integer id){
        return userRepository.findById(id).get();
    }

    // 根据id修改对应行人员信息
    @PutMapping("/page2update")
    public String update(@RequestBody User user){
        // 前端传过来的修改留言请求
        System.out.println("前段传来的请求user"+user);

        // 先判断修改的账号是否已存在 存在则驳回修改
        User getResultByusernumber = userDao.findByUsernumber(user.getUsernumber());
        User selectByidResult = userRepository.findById(user.getId()).get(); // 根据id去数据库查出对应行的数据并返回
        if(getResultByusernumber == null || getResultByusernumber.getUsernumber().equals(selectByidResult.getUsernumber()))
        {


            if(selectByidResult.getPassword().equals(user.getPassword()))
            {
                User result1 = userRepository.save(user);
                if(result1 != null)
                {
                    return "success";
                }
                else
                {
                    return "error";
                }
            }
            else
            {
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); //对修改后的密码继续进行强hash加密
                User result2 = userRepository.save(user);
                if(result2 != null)
                {
                    return "success";
                }
                else
                {
                    return "error";
                }
            }
        }else
        {
            return "抱歉修改后的账号已经有人使用了，请更换后重试";
        }
    }

    // 根据id删除留言
    @DeleteMapping("/deleteByUserId/{id}")
    public void DeleteById(@PathVariable("id") Integer id){
        //        前端传过来的修改留言请求
        System.out.println("前段传来的请求user"+id);
        userRepository.deleteById(id);
    }
}
