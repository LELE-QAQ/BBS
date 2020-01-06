package com.bbs.controller.Admin;

import com.bbs.pojo.Post;
import com.bbs.pojo.User;
import com.bbs.service.PostService;
import com.bbs.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Description:帖子管理
 * Created By KL
 * Date: 2019/8/14
 * Time: 16:45
 */
@Controller
@RequestMapping("/admin/common")
public class AdminPostController {


    @Autowired
    private PostService postService;

    /**
     * 全局置顶帖子
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("Gtop.do")
    public Map<String ,String> top(HttpServletRequest request , int id){
        HashMap<String, String> map = new HashMap<>();
        Post post = new Post();
        post.setId(id);
        post.setTop(com.bbs.tool.PublicStatic.ALLTOP);
        User user=(User) request.getSession().getAttribute(com.bbs.tool.PublicStatic.USER);
        post.setLastupdate(""+user.getName()+"\""+user.getLoginname()+"\"于"+Tool.getyyyyMMddHHmmss()+"将帖子全局置顶"+"</br>");
        postService.update(post);
        map.put("flag", "1");
        return map;
    }


    /**
     * 取消置顶
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("disGtop.do")
    public Map<String, String> disTop(HttpServletRequest request,int id) {
        Map<String, String> map=new HashMap<String, String>();
        Post post=new Post();
        post.setId(id);
        post.setTop("");
        User user=(User) request.getSession().getAttribute(com.bbs.tool.PublicStatic.USER);
        post.setLastupdate(""+user.getName()+"\""+user.getLoginname()+"\"于"+Tool.getyyyyMMddHHmmss()+"将帖子取消全局置顶"+"</br>");
        postService.update(post);
        map.put("flag", "1");
        return map;
    }
}
