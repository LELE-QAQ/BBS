package com.bbs.controller.index;


import com.bbs.pojo.PageBean;
import com.bbs.service.PostService;
import com.bbs.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/8/12
 * Time: 16:35
 */
@Controller
@RequestMapping("/pt")
public class IndexPostController {



    @Autowired
    private PostService postService;

    @Autowired
    private ReplyService replyService;

    /**
     * 跳转帖子详情页面
     * @param request
     * @param model
     * @param id
     * @param orderby
     * @param page
     * @return
     */
    @RequestMapping("index.do")
    public String index(HttpServletRequest request, Model model,String id,String orderby,PageBean<Map<String, Object>> page){
        Map<String,Object> map =  postService.findById(id);
        HashMap<String, Object> qmap = new HashMap<>();
        qmap.put("id",id);
        if("0".equals(orderby)){
            qmap.put("orderby"," desc");
        }else {
            qmap.put("orderby"," asc");
        }

        PageBean<Map<String, Object>> replyMap = replyService.findPage(qmap, page);

        model.addAttribute("replymap", replyMap);
        model.addAttribute("flag",1);
        model.addAttribute("map",map);
        model.addAttribute("orderby", orderby);
        return "jsp/index/content";
    }

}
