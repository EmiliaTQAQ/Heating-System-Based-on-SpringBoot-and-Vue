package com.example.common;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Admin;
import com.example.entity.Heating_engineer;
import com.example.entity.Log;
import com.example.entity.User;
import com.example.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class LogAspect {
    @Resource
    private  LogService logService;
    @Around("@annotation(autoLog)")
    public Object doAround(ProceedingJoinPoint joinPoint,AutoLog autoLog) throws Throwable {

        //操作内容，我们在注解里已经定义了value(),然后在需要切入的接口上面去写上对应的操作内容即可
        String name = autoLog.value();
        //操作时间(当前时间)
        String time = DateUtil.now();
        //操作人
        String account = Jwt.getCurrentAdmin();
        if (ObjectUtil.isNotNull(account)) {

            System.out.println("3");
        }
        //操作人ip
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        System.out.println(ip);
        System.out.println(request.getHeader("token"));
        //执行具体的端口
        Result result = (Result) joinPoint.proceed();

        //再去往日志表李写一条日志记录
        Log log = new Log(null,name,account,time,ip);
        logService.add(log);

        return result;
    }
    @Around("@annotation(autoLogUser)")
    public Object doAroundUser(ProceedingJoinPoint joinPoint,AutoLogUser autoLogUser) throws Throwable {

        //操作内容，我们在注解里已经定义了value(),然后在需要切入的接口上面去写上对应的操作内容即可
        String name = autoLogUser.value();
        //操作时间(当前时间)
        String time = DateUtil.now();
        //操作人
        String account  = Jwt.getCurrentUser();
        if (ObjectUtil.isNotNull(account)) {

        }
        //操作人ip
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();

        //执行具体的端口
        Result result = (Result) joinPoint.proceed();

        //再去往日志表李写一条日志记录
        Log log = new Log(null,name,account,time,ip);
        logService.add(log);

        return result;
    }
    @Around("@annotation(autoLogHeating_engineer)")
    public Object doAroundHeating_engineer(ProceedingJoinPoint joinPoint,AutoLogHeating_engineer autoLogHeating_engineer) throws Throwable {

        //操作内容，我们在注解里已经定义了value(),然后在需要切入的接口上面去写上对应的操作内容即可
        String name = autoLogHeating_engineer.value();
        //操作时间(当前时间)
        String time = DateUtil.now();
        //操作人
        String account = Jwt.getCurrentHeating_engineer();
        if (ObjectUtil.isNotNull(account)) {

        }
        //操作人ip
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();

        //执行具体的端口
        Result result = (Result) joinPoint.proceed();

        //再去往日志表李写一条日志记录
        Log log = new Log(null,name,account,time,ip);
        logService.add(log);

        return result;
    }
}
