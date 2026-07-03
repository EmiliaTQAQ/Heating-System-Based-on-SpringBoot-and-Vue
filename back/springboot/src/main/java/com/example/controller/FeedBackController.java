package com.example.controller;


import com.example.common.Result;
import com.example.entity.FeedBack;
import com.example.entity.Params;
import com.example.service.FeedBackService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/feedback")
public class FeedBackController {
    @Resource
    private FeedBackService feedBackService;
    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<FeedBack> info = feedBackService.findBySearch(params);
        return Result.success(info);
    }
    @GetMapping("/searchF")
    public Result findBySearchF(Params params) {
        PageInfo<FeedBack> info = feedBackService.findBySearchF(params);
        return Result.success(info);
    }

    @PostMapping
    public Result save(@RequestBody FeedBack feedBack){
        feedBackService.add(feedBack);
        return Result.success();
    }
    @PostMapping("/anonymity")
    public Result add(@RequestBody FeedBack feedBack){
        feedBackService.add(feedBack);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody FeedBack feedBack) {
        feedBackService.update(feedBack);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        feedBackService.delete(id);
        return Result.success();
    }
}
