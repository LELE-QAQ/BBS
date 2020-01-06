package com.bbs.controller.Admin;

import com.bbs.pojo.Info;
import com.bbs.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA
 * Description:网站信息
 * Created By KL
 * Date: 2019/8/10
 * Time: 13:45
 */
@Controller
@RequestMapping("/admin/info")
public class AdminInfoController {

    @Autowired
    private InfoService infoService;


    /**
     * 网站信息页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("index.do")
    public String index(HttpServletRequest request,Model model){
        Info info =  infoService.find();
        model.addAttribute("info",info);
        return "jsp/admin/info";
    }

    @RequestMapping("update.do")
    private String update(HttpServletRequest request,Info info){
        info.setId(1);
        infoService.update(info);
        info = infoService.find();
        request.getSession().getServletContext().setAttribute("bbsinfo", info);
        return "redirect:/admin/info/index.do ";
    }

}
