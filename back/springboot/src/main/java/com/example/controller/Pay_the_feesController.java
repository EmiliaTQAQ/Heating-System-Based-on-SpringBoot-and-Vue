package com.example.controller;

import com.example.common.AutoLogUser;
import com.example.common.Result;
import com.example.entity.Notice;
import com.example.entity.Params;
import com.example.entity.Pay_the_fees;
import com.example.entity.User;
import com.example.service.Pay_the_feesService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/pay_the_fees")
public class Pay_the_feesController {
    @Resource
    private Pay_the_feesService pay_the_feesService;
    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Pay_the_fees> info = pay_the_feesService.findBySearch(params);
        return Result.success(info);
    }

    @PostMapping
    @AutoLogUser("用户缴费")
    public Result save(@RequestBody Pay_the_fees pay_the_fees){
            pay_the_feesService.add(pay_the_fees);
        return Result.success();
    }

    @GetMapping("/find/{account}")
    public Result findY(@PathVariable String account) {
        return Result.success(pay_the_feesService.find(account));
    }
    @DeleteMapping("")
    public Result delete(){
        pay_the_feesService.delete();
        return Result.success();
    }

}
