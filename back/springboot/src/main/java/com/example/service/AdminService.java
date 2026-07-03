package com.example.service;

import com.example.common.Jwt;
import com.example.dao.AdminDao;
import com.example.entity.Admin;
import com.example.exception.CustomException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminService {
    @Resource
    private AdminDao adminDao;

    public Admin login(Admin admin) {
        if (admin.getAccount() == null || "".equals(admin.getAccount())) {
            throw new CustomException("用户名不能为空");
        }
        if (admin.getPassword() == null || "".equals(admin.getPassword())) {
            throw new CustomException("密码不能为空");
        }
        Admin user = adminDao.findByNameAndPassword(admin.getAccount(), admin.getPassword(),admin.getRole());
        if (user == null){
            throw new CustomException("信息输入错误");
        }
        String token = Jwt.generateJwt(user.getRole(),user.getAccount(),user.getPassword());
        System.out.println(token);
        //生成该登录用户对应的token，然后跟着user一起返回到前台
        user.setToken(token);
        return user;

    }

    public Admin findByA_B(String account,String password,String role) {
        return adminDao.findByNameAndPassword(account,password,role);
    }

    public Admin findById(Integer valueOf) {
        return adminDao.findById(valueOf);
    }
}
