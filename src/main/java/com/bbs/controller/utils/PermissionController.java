package com.bbs.controller.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA
 * Description:权限
 * Created By KL
 * Date: 2019/8/13
 * Time: 16:58
 */
@Controller
@RequestMapping("topage")
public class PermissionController {

    @RequestMapping("permission.do")
    public String permission(){
        return "inc/quanxian";
    }


    @RequestMapping("ipPermission.do")
    public String ipPermission(){
        return "inc/ipquanxian";
    }
}
