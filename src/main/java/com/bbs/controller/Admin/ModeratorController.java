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
 * Description:版主操作
 * Created By KL
 * Date: 2019/8/14
 * Time: 19:02
 */
@Controller
@RequestMapping("/moderator")
public class ModeratorController {

    @Autowired
    private PostService postService;



    /**
     * 设置精华
     *
     * @param request
     * @param id
     * @param excellent
     * @return
     */
    @ResponseBody
    @RequestMapping("best.do")
    public Map<String, String> best(HttpServletRequest request, int id, String excellent) {
        HashMap<String, String> map = new HashMap<>();
        Post post = new Post();
        post.setId(id);
        post.setExcellent(excellent);
        User user = (User) request.getSession().getAttribute(com.bbs.tool.PublicStatic.USER);

        post.setLastupdate("" + user.getName() + "\"" + user.getLoginname() + "\"于" + Tool.getyyyyMMddHHmmss() + "将帖子设置精华" + excellent + "</br>");
        postService.update(post);
        map.put("flag", "1");
        return map;
    }


    /**
     * 取消精华
     *
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("disbest.do")
    public Map<String, String> disBest(HttpServletRequest request, int id) {
        Map<String, String> map = new HashMap<String, String>();
        Post post = new Post();
        post.setId(id);
        post.setExcellent("");
        User user = (User) request.getSession().getAttribute(com.bbs.tool.PublicStatic.USER);
        post.setLastupdate("" + user.getName() + "\"" + user.getLoginname() + "\"于" + Tool.getyyyyMMddHHmmss() + "将帖子取消精华" + "</br>");
        postService.update(post);
        map.put("flag", "1");
        return map;
    }


    /**
     * 版块置顶
     *
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("top.do")
    public Map<String, String> top(HttpServletRequest request, int id) {
        Map<String, String> map = new HashMap<String, String>();
        Post post = new Post();
        post.setId(id);
        post.setTop(com.bbs.tool.PublicStatic.BANKUAIZHIDING);
        User user = (User) request.getSession().getAttribute(com.bbs.tool.PublicStatic.USER);
        post.setLastupdate("" + user.getName() + "\"" + user.getLoginname() + "\"于" + Tool.getyyyyMMddHHmmss() + "将帖子板块置顶" + "</br>");
        postService.update(post);
        map.put("flag", "1");
        return map;
    }

    /**
     * 取消版块置顶
     *
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("distop.do")
    public Map<String, String> disTop(HttpServletRequest request, int id) {
        Map<String, String> map = new HashMap<String, String>();
        Post post = new Post();
        post.setId(id);
        post.setTop("");
        User user = (User) request.getSession().getAttribute(com.bbs.tool.PublicStatic.USER);
        post.setLastupdate("" + user.getName() + "\"" + user.getLoginname() + "\"于" + Tool.getyyyyMMddHHmmss() + "将帖子取消板块置顶" + "</br>");
        postService.update(post);
        map.put("flag", "1");
        return map;
    }

}
