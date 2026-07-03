package com.example.controller;

import com.example.common.Result;
import com.example.entity.*;
import com.example.service.AccessoriesService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/accessories")
public class AccessoriesController {
    @Resource
    private AccessoriesService accessoriesService;
    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Accessories> info = accessoriesService.findBySearch(params);
        return Result.success(info);
    }

    @PostMapping
    public Result save(@RequestBody Accessories accessories){
        if (accessories.getId() == null){
            accessoriesService.add(accessories);
        } else {
            accessoriesService.update(accessories);
        }
        return Result.success();
    }



    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        accessoriesService.delete(id);
        return Result.success();
    }

}
