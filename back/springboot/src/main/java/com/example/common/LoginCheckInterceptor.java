package com.example.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.entity.Admin;
import com.example.entity.Heating_engineer;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.service.AdminService;
import com.example.service.Heating_engineerService;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginCheckInterceptor  implements HandlerInterceptor {
    @Resource
    private AdminService adminService;

    @Resource
    private Heating_engineerService heating_engineerService;
    @Resource
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.从http请求的header获取token
        String token = request.getHeader("token");
        String Role = request.getHeader("role");
        log.info(token + "1");
//        System.out.println(token);
        if (StrUtil.isBlank(token)) {
            //如果没拿到，我再去参数里面拿一波试试
            token = request.getParameter("token");

        }
        //2.开始执行认证
        if (StrUtil.isBlank(token)) {
            throw new CustomException("无token，请重新登录");
        }
        //获取 token 中的userId
        String userRole;
        String userAccount;
        String userPassword;
        Heating_engineer heating_engineer;
        Admin admin;
        User user;
        try {
            //解码
            userRole = JWT.decode(token).getAudience().get(2);
            userAccount = JWT.decode(token).getAudience().get(0);
            userPassword = JWT.decode(token).getAudience().get(1);
            //根据token中的userId查询数据库
            admin = adminService.findByA_B(userAccount,userPassword,userRole);
            heating_engineer = heating_engineerService.findByA_B(userAccount,userPassword,userRole);
            user = userService.findByA_B(userAccount,userPassword,userRole);
        } catch (Exception e) {
            String errMSg = "token验证失败,请重新登录";
            log.error(errMSg + ", token=" + token,e);
            throw new CustomException(errMSg);
        }
        if (admin == null && heating_engineer == null && user == null) {
            throw new CustomException("用户不存在，请重新登录1");
        }
        if (Role.equals(userRole)) {
            if (admin != null) {
                try {
//                System.out.println("admin123");
                    //用户密码加签证码 token
                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(admin.getRole())).build();
//                System.out.println("admin345");
                    jwtVerifier.verify(token);//验证token
//                System.out.println("admin123");
                } catch (JWTVerificationException e) {
                    throw new CustomException("token验证失败，请重新登录3");
                }
            } else if (heating_engineer != null) {
                try {
                    //用户密码加签证码 token
                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(heating_engineer.getRole())).build();
                    jwtVerifier.verify(token);//验证token
                } catch (JWTVerificationException e) {
                    throw new CustomException("token验证失败，请重新登录4");
                }
            } else if (user != null) {
                try {
                    //用户密码加签证码 token
                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getRole())).build();
                    jwtVerifier.verify(token);//验证token
                } catch (JWTVerificationException e) {
                    throw new CustomException("token验证失败，请重新登录2");
                }
            }
        } else {
            throw new CustomException("身份信息验证失败，请重新登录");
        }
        log.info("token验证成功，允许放行");
        return true;
    }
}
