package com.example.controller;

import com.example.common.AutoLogHeating_engineer;
import com.example.common.CaptureConfig;
import com.example.common.Result;
import com.example.entity.Heating_engineer;
import com.example.entity.Params;
import com.example.entity.User;
import com.example.service.Heating_engineerService;
import com.github.pagehelper.PageInfo;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/heating_engineer")
public class Heating_engineerController {
    @Resource
    private Heating_engineerService heatingEngineerService;
    @PostMapping("/login")

    public Result login(@RequestBody Heating_engineer heating_engineer,@RequestParam String key, HttpServletRequest request){

        if (!heating_engineer.getVerCode().toLowerCase().equals(CaptureConfig.CAPTURE_MAP.get(key))) {
            CaptchaUtil.clear(request);
            return Result.error("验证码不正确");
        }
        Heating_engineer loginuser =  heatingEngineerService.login(heating_engineer);
        CaptureConfig.CAPTURE_MAP.remove(key);
        return Result.success(loginuser);
    }
    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Heating_engineer> info = heatingEngineerService.findBySearch(params);
        return Result.success(info);
    }
    @PostMapping
    public Result save(@RequestBody Heating_engineer heating_engineer){
        if (heating_engineer.getId() == null){
            heatingEngineerService.add(heating_engineer);
        } else {
            heatingEngineerService.update(heating_engineer);
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        heatingEngineerService.delete(id);
        return Result.success();
    }
    @GetMapping()
    public Result findByHeating(){
        return Result.success( heatingEngineerService.findByHeating());
    }
    @GetMapping("/findName/{value}")
    public Result findName(@PathVariable String value) {
        return Result.success(heatingEngineerService.findName(value));
    }

    @GetMapping("/message/{account}")
    public Result save(@PathVariable String account){
        Heating_engineer heating_engineer = heatingEngineerService.findByAccount(account);
        return Result.success(heating_engineer);
    }

    @PostMapping("/upMessage")
    public Result saveTwo(@RequestBody Heating_engineer heating_engineer){
        heatingEngineerService.upMessage(heating_engineer);
        return Result.success(heating_engineer);
    }

}
