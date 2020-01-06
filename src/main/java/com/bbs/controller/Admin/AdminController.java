package com.bbs.controller.Admin;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA
 * Description:管理员操作
 * Created By KL
 * Date: 2019/8/8
 * Time: 16:03
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * 跳转后台
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/index.do")
    public String index(HttpServletRequest request, Model model){
        return "jsp/admin/index";
    }


}
