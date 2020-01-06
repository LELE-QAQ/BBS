package com.bbs.controller.index;


import com.bbs.pojo.PageBean;
import com.bbs.pojo.Pic;
import com.bbs.pojo.Section;
import com.bbs.service.InfoService;
import com.bbs.service.PicService;
import com.bbs.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:首页的查询
 * Created By KL
 * Date: 2019/8/1
 * Time: 17:57
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private SectionService sectionService;
    @Autowired
    private PicService picService;
    @Autowired
    private InfoService infoService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询首页信息
     * @param request
     * @param model
     * @param section
     * @param pageBean
     * @return
     */
    @RequestMapping("index.do")
    public String findAll(HttpServletRequest request, Model model, Section section, PageBean<Section> pageBean) {

        //查询板块
        List<Section> sectionList = sectionService.findAll();
        for (Section sec : sectionList) {
            String postName = sec.getPostName();
            if (postName != null && postName.length() > 10) {
                postName = postName.substring(0, 10) + "...";
                sec.setPostName(postName);
            }
        }
        model.addAttribute("sectionList", sectionList);
        //System.out.println(PublicStatic.nPost);


        List<Pic> picList =  picService.indexPic();

        model.addAttribute("picList",picList);


        //com.bbs.tool.PublicStatic.nPost = (List<Map<String, Object>>)redisTemplate.boundHashOps(Post.class.getSimpleName()).get("newpost");
        //查询最新帖子
       // model.addAttribute("ztiezi", com.bbs.tool.PublicStatic.nPost);

        //System.out.println(PublicStatic.nPost);
       // model.addAttribute("indexmap", com.bbs.tool.PublicStatic.indexmap);


        //new com.bbs.tool.IndexThread().run();
        return "jsp/index/index";
    }


}
