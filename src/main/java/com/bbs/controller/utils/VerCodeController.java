package com.bbs.controller.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA
 * Description:验证码
 * Created By KL
 * Date: 2019/8/4
 * Time: 17:27
 */
@Controller
@RequestMapping("/vercode")
public class VerCodeController {

    @RequestMapping("setCode.do")
    public void setCode(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //生成随机字符串
        String code = com.bbs.tool.VerifyCodeUtils.generateVerifyCode(4);

        //存入session
        HttpSession session = request.getSession(true);

        //删除之前的
        session.removeAttribute("code");
        //更新code
        session.setAttribute("code", code.toLowerCase());
        //生成图片
        int w = 150, h = 50;
        try {
            com.bbs.tool.VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), code);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ResponseBody
    @RequestMapping("isVercode.do")
    public int isVercode(HttpServletRequest request, String yanzhengma) {
        HttpSession session = request.getSession(true);
        String vercode = (String) session.getAttribute("code");
        int flag = 0;
        if (vercode != null && vercode.equalsIgnoreCase(yanzhengma)) {
            flag = 1;
        }
        return flag;
    }
}
