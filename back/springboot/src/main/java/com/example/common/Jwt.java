package com.example.common;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.entity.Admin;
import com.example.entity.Heating_engineer;
import com.example.entity.User;
import com.example.service.AdminService;
import com.example.service.Heating_engineerService;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@Component
public class Jwt {
    private static String signKey = "itheima";
    private static Long expire = 43200000L;
    private static AdminService staticAdminService;
    private static UserService staticUserService;
    private static Heating_engineerService staticHeating_engineerService;
    @Resource
    private  AdminService adminService;
    @Resource
    private  UserService userService;
    @Resource
    private  Heating_engineerService heating_engineerService;
    @PostConstruct
    public void setUserService() {
        staticAdminService = adminService;
        staticUserService = userService;
        staticHeating_engineerService = heating_engineerService;
    }



    /**
     * 生成JWT令牌
     * @param
     * @return
     */
    public static String generateJwt(String userRole,String account,String password){
        return JWT.create() //将userRole 保存到token 里面，作为荷载
                .withAudience(account,password,userRole)
                .withExpiresAt(DateUtil.offsetHour(new Date(),2))//2小时后token过期
                .sign(Algorithm.HMAC256(userRole));//以 userRole 作为 token 的密码
    }


    /**
     * 获取当前登录的管理员信息
     */
    public static String getCurrentAdmin() {
        String token = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = request.getHeader("Token");
            System.out.println("token" +token);
            if (StrUtil.isBlank(token)) {
                token = request.getParameter("token");
            }
            if (StrUtil.isBlank(token)) {
                log.error("获取当前登录的token失败，token：{}",token);
                return null;
            }
            System.out.println(JWT.decode(token).getAudience());
            //解析token
            return JWT.decode(token).getAudience().get(0);
        } catch (Exception e) {
            log.error("获取当前登录的管理员信息失败，token={}",token,e);
            return null;
        }
    }

    /**
     * 获取当前登录的用户信息
     */
    public static String getCurrentUser() {
        String token = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = request.getHeader("Token");

            if (StrUtil.isBlank(token)) {
                token = request.getParameter("token");
            }
            if (StrUtil.isBlank(token)) {
                log.error("获取当前登录的token失败，token：{}",token);
                return null;
            }
            //解析token

            return   JWT.decode(token).getAudience().get(0);
        } catch (Exception e) {
            log.error("获取当前登录的管理员信息失败，token={}",token,e);
            return null;
        }
    }

    /**
     * 获取当前登录的供暖师傅信息
     */
    public static String getCurrentHeating_engineer() {
        String token = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = request.getHeader("Token");
            System.out.println("token" +token);
            if (StrUtil.isBlank(token)) {
                token = request.getParameter("token");
            }
            if (StrUtil.isBlank(token)) {
                log.error("获取当前登录的token失败，token：{}",token);
                return null;
            }
            //解析token
            return  JWT.decode(token).getAudience().get(0);
        } catch (Exception e) {
            log.error("获取当前登录的管理员信息失败，token={}",token,e);
            return null;
        }
    }
}
