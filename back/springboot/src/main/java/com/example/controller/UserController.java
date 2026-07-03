package com.example.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.AutoLog;
import com.example.common.AutoLogUser;
import com.example.common.CaptureConfig;
import com.example.common.Result;
import com.example.entity.Heating_engineer;
import com.example.entity.Params;
import com.example.entity.Room;
import com.example.entity.User;
import com.example.service.UserService;
import com.github.pagehelper.PageInfo;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @PostMapping("/login")
    public Result login(@RequestBody User user,@RequestParam String key, HttpServletRequest request){
        if (!user.getVerCode().toLowerCase().equals(CaptureConfig.CAPTURE_MAP.get(key))) {
            CaptchaUtil.clear(request);
            return Result.error("验证码不正确");
        }
        User loginuser =  userService.login(user);
        CaptureConfig.CAPTURE_MAP.remove(key);
        return Result.success(loginuser);
    }
    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<User> info = userService.findBySearch(params);
        return Result.success(info);
    }
    @PostMapping
    public Result save(@RequestBody User user){
        if (user.getId() == null){
            userService.add(user);
        } else {
            userService.update(user);
        }
        return Result.success();
    }


    @GetMapping("/upBalance/{account}/{balance}")
    public Result findupB(@PathVariable String account,@PathVariable Double balance) {
        userService.upB(account,balance);
        return Result.success();
    }
    @GetMapping("/findBalance/{id}")
    public Result findBalance(@PathVariable Integer id) {
        Integer x = userService.findBalance(id);
        return Result.success(x);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        userService.delete(id);
        return Result.success();
    }

    @GetMapping("/echarts/bie")
    public Result bie() {
        List<User> list = userService.find();
        Map<String,Long> collect = list.stream().
                filter(x -> ObjectUtil.isNotEmpty(x.getCell()))
                .collect(Collectors.groupingBy(User::getCell,Collectors.counting()));
        List<Map<String,Object>> mapList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(collect)) {
            for (String key : collect.keySet()) {
                Map<String,Object> map = new HashMap<>();
                map.put("name",key);
                map.put("value",collect.get(key));
                mapList.add(map);
            }
        }
        return Result.success(mapList);
    }

//    @GetMapping("/echarts/bie1")
//    public Result bie1() {
//        List<User> list = userService.find();
//        Map<String,Long> collect = list.stream().
//                filter(x -> ObjectUtil.isNotEmpty(x.getSex()))
//                .collect(Collectors.groupingBy(User::getSex,Collectors.counting()));
//        List<Map<String,Object>> mapList = new ArrayList<>();
//        if (CollectionUtil.isNotEmpty(collect)) {
//            for (String key : collect.keySet()) {
//                Map<String,Object> map = new HashMap<>();
//                map.put("name",key);
//                map.put("value",collect.get(key));
//                mapList.add(map);
//            }
//        }
//        return Result.success(mapList);
//    }


    @GetMapping("/message/{account}")
    public Result save(@PathVariable String account) {
        User user = userService.findByAccount(account);
        return Result.success(user);
    }
    @PostMapping("/upMessage")
    @AutoLogUser("用户修改个人信息")
    public Result saveTwo(@RequestBody User user){
        userService.upMessage(user);
        return Result.success(user);
    }
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        user.setRole("user");
        user.setBalance((double)0);
        user.setLiving_space((double)50);
        userService.add(user);
        return Result.success();
    }

    @GetMapping("/tz/{account}")
    public Result tz(@PathVariable String account) {

        String x = userService.tz(account);
        return Result.success(x);
    }
}
