package com.bbs.controller.Admin;


import com.bbs.pojo.PageBean;
import com.bbs.pojo.Section;
import com.bbs.pojo.User;
import com.bbs.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:管理员版块操作
 * Created By KL
 * Date: 2019/8/8
 * Time: 16:07
 */
@Controller
@RequestMapping("/admin/section")
public class AdminSectionController {


    @Autowired
    private SectionService sectionService;


    /**
     * 版块页面查询
     *
     * @param request
     * @param model
     * @param section
     * @param pageBean
     * @return
     */
    @RequestMapping("index.do")
    public String index(HttpServletRequest request, Model model, Section section, PageBean<Section> pageBean) {
        pageBean = sectionService.findPage(section, pageBean);
        model.addAttribute("page", pageBean);
        //System.out.println(pageBean);
        model.addAttribute("querySec",section);
        return "jsp/admin/section/index";
    }


    /**
     * 跳转添加或修改界面
     *
     * @param section
     * @param model
     * @return
     */
    @RequestMapping("updateoradd.do")
    public String updateOrAdd(HttpServletRequest request, Section section, Model model) {
        //System.out.println(section.getId());
        if (section.getId() != null) {
            section = sectionService.findById(section);
        }
        model.addAttribute("section", section);
        List<Section> sectionList = sectionService.findByParentId();
       /* for (Section sec : sectionList) {
            System.out.println(sec);
        }*/
        /*如果是父版块，删除自己*/
        for (int i = 0; i < sectionList.size(); i++) {
            Section sec = sectionList.get(i);
            if (sec.getId() == section.getId()) {
                sectionList.remove(sec);
            }
        }
        model.addAttribute("parentList", sectionList);
        return "jsp/admin/section/updateoradd";
    }


    /**
     * 更新信息
     * @param request
     * @param section
     * @return
     */
    @RequestMapping("update.do")
    public String update(HttpServletRequest request, Section section) {
        User user = (User) request.getSession().getAttribute(com.bbs.tool.PublicStatic.USER);
        section.setCreateuserid(user.getId());
        String img = section.getImg();
        if (img != null && img.length() == 0) {
            section.setImg("bankuai.jpg");
        }
        if (section.getId() != null) {
            sectionService.update(section);
        } else {
            sectionService.insert(section);
        }

        return "redirect:index.do ";
    }


    /**
     * 获取拼音
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping("getpinyin.do")
    public String getPinyin(String name) {
        String pinYinHeadChar = cn.bbs.tool.PinYin4jUtil.getPinYinHeadChar(name);
        //System.out.println(pinYinHeadChar);
        return pinYinHeadChar;
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("del.do")
    public int del(int id) {
        int result = sectionService.del(id);
        //System.out.println(result);
        return result;
    }


}
