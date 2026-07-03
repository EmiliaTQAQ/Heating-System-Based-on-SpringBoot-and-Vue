package com.example.controller;

import com.example.common.AutoLogUser;
import com.example.common.Result;
import com.example.entity.Advance_fee;
import com.example.entity.Params;
import com.example.entity.Pay_the_fees;
import com.example.service.Advance_feeService;
import com.example.service.Pay_the_feesService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/advance_fee")
public class Advance_feeController {
    @Resource
    private Advance_feeService advance_feeService;
    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Advance_fee> info = advance_feeService.findBySearch(params);
        return Result.success(info);
    }

    @PostMapping
    public Result save(@RequestBody Advance_fee advance_fee){
        advance_feeService.add(advance_fee);
        return Result.success();
    }
    @PostMapping("/transfer")
    public Result transfer(){
        advance_feeService.transfer();
        return Result.success();
    }

    @GetMapping("/find/{account}")
    public Result findY(@PathVariable String account) {
        return Result.success(advance_feeService.find(account));
    }
    @DeleteMapping("")
    public Result delete(){
        advance_feeService.delete();
        return Result.success();
    }
}
