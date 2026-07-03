package com.example.controller;

import com.example.common.Result;
import com.example.entity.Notice;
import com.example.entity.Params;
import com.example.service.NoticeService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Resource
    private NoticeService noticeService;

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Notice> info = noticeService.findBySearch(params);
        return Result.success(info);
    }

    @PostMapping
    public Result save(@RequestBody Notice notice) {
        noticeService.add(notice);
        return Result.success();
    }
    @PostMapping("/update")
    public Result update(@RequestBody Notice notice) {
        noticeService.update(notice);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        noticeService.delete(id);
        return Result.success();
    }

    @GetMapping("/findState/{state}")
    public Result find(@PathVariable String state) {
        List<Notice> list = noticeService.findAll(state);
        return Result.success(list);
    }
}
