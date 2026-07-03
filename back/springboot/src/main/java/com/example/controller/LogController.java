package com.example.controller;


import com.example.common.AutoLog;
import com.example.common.Result;
import com.example.entity.Log;

import com.example.entity.Params;
import com.example.entity.User;
import com.example.service.LogService;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/log")
public class LogController {
    @Resource
    private LogService logService;

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Log> info = logService.findBySearch(params);
        return Result.success(info);
    }
    @PostMapping
    public Result save(@RequestBody Log log) {
        logService.add(log);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        logService.delete(id);
        return Result.success();
    }
}
