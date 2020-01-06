package com.bbs.controller.Admin;


import com.bbs.pojo.Theme;
import com.bbs.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:主题管理
 * Created By KL
 * Date: 2019/8/10
 * Time: 13:46
 */
@Controller
@RequestMapping("/admin/theme")
public class AdminThemeController {

    @Autowired
    private ThemeService themeService;

    /**
     * 主题界面
     * @param request
     * @param model
     * @param sectionId
     * @return
     */
    @RequestMapping("index.do")
    private String findAll(HttpServletRequest request, Model model,Integer sectionId){

        List<Theme> list =  themeService.find(sectionId);
        model.addAttribute("list",list);
        model.addAttribute("sectionId",sectionId);
        return "jsp/admin/theme/index";
    }


    /**
     * 跳转修改页面
     * @param model
     * @param theme
     * @return
     */
    @RequestMapping("updateoradd.do")
    private String updateOrAdd(Model model , Theme theme){
        if(theme.getId() != null){
            theme =  themeService.findById(theme);
        }
        model.addAttribute("theme" ,theme);
        return "jsp/admin/theme/updateoradd";
    }


    /**
     * 添加或修改
     * @param theme
     * @return
     */
    @RequestMapping("update.do")
    public String update(Theme theme){
        if(theme.getId()!= null){
            themeService.update(theme);
        }else {
            themeService.insert(theme);
        }
        return "redirect:index.do?sectionId="+theme.getSectionid();
    }

    /**
     * 删除主题
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("del.do")
    public String del(int id){
        String result = themeService.delete(id);
        return result;
    }


}
