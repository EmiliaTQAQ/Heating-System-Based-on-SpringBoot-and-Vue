package com.example.controller;

import com.example.common.Result;
import com.example.entity.Notice;
import com.example.entity.Params;
import com.example.entity.Pay_the_fees;
import com.example.entity.Temperature;
import com.example.service.TemperatureService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/temperature")
public class TemperatureController {
    @Resource
    private TemperatureService temperatureService;
    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Temperature> info = temperatureService.findBySearch(params);
        return Result.success(info);
    }
    @PostMapping
    public Result save(@RequestBody Temperature temperature) {
        temperatureService.update(temperature);
        return Result.success();
    }
    @PostMapping("/in")
    public Result save1() {
        temperatureService.save1();
        return Result.success();
    }
    @PostMapping("/del")
    public Result del() {
        temperatureService.del();
        return Result.success();
    }
}
