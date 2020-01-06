package com.bbs.controller.index;

import com.bbs.pojo.PageBean;
import com.bbs.pojo.Post;
import com.bbs.pojo.Section;
import com.bbs.pojo.Theme;
import com.bbs.service.PostService;
import com.bbs.service.SectionService;
import com.bbs.service.ThemeService;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 首页板块查询
 */
@Controller
@RequestMapping("/bk")
public class IndexSectionController {
    @Autowired
    SectionService sectionService;

    @Autowired
    PostService postService;

    @Autowired
    ThemeService themeService;

    @Autowired
    UserService userService;


    /**
     * 版块信息查询
     *
     * @param request
     * @param model
     * @param sectionId
     * @param themeId
     * @param page
     * @param orderby
     * @return
     */
    @RequestMapping("index.do")
    public String findAllWhere(HttpServletRequest request, Model model, String sectionId, String themeId, PageBean<Post> page, String orderby) {
        Section section = new Section();
        section.setId(Integer.parseInt(sectionId));
        section = sectionService.findById(section);
       /* Theme theme = new Theme();
        theme.setSectionId(Integer.parseInt(sectionId));*/
        List<Theme> list = themeService.find(Integer.parseInt(sectionId));
        Post post = new Post();
        post.setSectionid(Integer.parseInt(sectionId));
        post.setOrderby(orderby);
        if (themeId != null && !themeId.equals("0")) {
            post.setThemeid(Integer.parseInt(themeId));
        } else {
            themeId = "0";
        }
        PageBean<Post> pageById = postService.findPageById(post, page);
        int count = postService.findCount(sectionId);


        //查询置顶
        List<Post> topList = postService.findTop(sectionId);

        model.addAttribute("page", page);
        model.addAttribute("sectionId", sectionId);
        model.addAttribute("themeId", themeId);
        model.addAttribute("orderby", orderby);
        model.addAttribute("section", section);
        model.addAttribute("themeList", list);
        model.addAttribute("nowcount", count);
        model.addAttribute("topList", topList);
        return "jsp/index/section";
    }

}
