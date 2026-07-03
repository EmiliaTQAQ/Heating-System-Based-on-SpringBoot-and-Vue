package com.example.controller;

import com.example.common.AutoLog;
import com.example.common.Result;
import com.example.entity.Admin;
import com.example.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    @PostMapping("/login")

    public Result login(@RequestBody Admin admin){
        Admin loginuser =  adminService.login(admin);
        return Result.success(loginuser);
    }
}
