package com.example.controller;

import com.example.common.AutoLogHeating_engineer;
import com.example.common.Result;
import com.example.entity.Accessories;
import com.example.entity.Maintain;
import com.example.entity.Notice;
import com.example.entity.Params;
import com.example.service.MaintainService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/maintain")
public class MaintainController {
    @Resource
    private MaintainService maintainService;
    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Maintain> info = maintainService.findBySearch(params);
        return Result.success(info);
    }
    @GetMapping("/searchU")
    public Result findBySearchU(Params params) {
        PageInfo<Maintain> info = maintainService.findBySearchU(params);
        return Result.success(info);
    }
    @GetMapping("/searchH")
    public Result findBySearchH(Params params) {
        PageInfo<Maintain> info = maintainService.findBySearchH(params);
        return Result.success(info);
    }



    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        maintainService.delete(id);
        return Result.success();
    }
    @PostMapping
    public Result save(@RequestBody Maintain maintain) {
        maintainService.add(maintain);
        return Result.success();
    }
    @PostMapping("/update")
    @AutoLogHeating_engineer("供暖师傅维修")
    public Result update(@RequestBody Maintain maintain) {
        maintainService.update(maintain);
        return Result.success();
    }

}
