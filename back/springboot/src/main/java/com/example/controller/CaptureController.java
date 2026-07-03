package com.example.controller;

import com.example.common.CaptureConfig;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping
public class CaptureController {
    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/captcha")
    public void captcha(@RequestParam String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SpecCaptcha captcha = new SpecCaptcha(135,33,5);
        captcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        CaptureConfig.CAPTURE_MAP.put(key,captcha.text());
        CaptchaUtil.out(captcha,request,response);
    }
}
