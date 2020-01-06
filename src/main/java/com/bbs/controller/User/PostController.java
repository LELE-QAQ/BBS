package com.bbs.controller.User;

import com.bbs.pojo.*;
import com.bbs.service.*;
import com.bbs.tool.Tool;
import jdk.nashorn.internal.ir.Flags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA
 * Description:帖子模块
 * Created By KL
 * Date: 2019/8/10
 * Time: 15:20
 */
@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private UserService userService;
    @Autowired
    private ThemeService themeService;
    @Autowired
    private PicService picService;

    /**
     * 跳转发帖界面
     *
     * @param request
     * @param response
     * @param model
     * @param id
     * @param pageBean
     * @return
     */
    @RequestMapping("topost.do")
    public String toPost(HttpServletRequest request, HttpServletResponse response, Model model, String id, PageBean<Post> pageBean) {
        //System.out.println(id);
        Section section = new Section();
        section.setId(Integer.parseInt(id));
        section = sectionService.findById(section);
        User sessionUser = (User) request.getSession().getAttribute(com.bbs.tool.PublicStatic.USER);
        //发帖权限

        List<Theme> themeList = themeService.find(Integer.parseInt(id));
        Post post = new Post();
        post.setSectionid(Integer.parseInt(id));
        pageBean = postService.findPageById(post, pageBean);
        int count = postService.findCount(id);
        model.addAttribute("themeList", themeList);
        model.addAttribute("sectionId", id);
        model.addAttribute("section", section);
        model.addAttribute("page", pageBean);
        model.addAttribute("nowcount", count);
        return "jsp/index/user/post";

    }

    /**
     * 发帖
     *
     * @param request
     * @param response
     * @param model
     * @param post
     * @return
     */
    @RequestMapping("post.do")
    public String post(HttpServletRequest request, HttpServletResponse response, Model model, Post post
    ) {
        Section section = new Section();
        User sessionUser = (User) request.getSession().getAttribute(com.bbs.tool.PublicStatic.USER);
        section.setId(post.getSectionid());
        post.setCreatetime(Tool.getyyyyMMddHHmmss());
        post.setCreateuserid(sessionUser.getId());
        post.setLastupdate("");

        post.setName(Tool.replaceHtml(post.getName()));
        post.setContenthtml(Tool.replaceHtml(post.getContenthtml()));

        postService.insert(post);
        post =  postService.findByPost(post);
        //新增图片记录
        if(post.getContenthtml().contains("/file/image/")){
            String[] split = post.getContenthtml().split("<img");
            for (int i = 0; i < split.length; i++) {
                String html=split[i];
                if(html.contains("/file/image/")){
                    int statrt =html.indexOf("/file/image/")+5;
                    String tmpcontent=html.substring(statrt);
                    int end=tmpcontent.indexOf("\"");
                    tmpcontent=tmpcontent.substring(1, end);
                    String picpath="/file/"+tmpcontent;
                    com.bbs.tool.PublicStatic.FILE_PATH = "D:\\IDEA\\BBS\\target\\bbs\\file\\";
                    tmpcontent= com.bbs.tool.PublicStatic.FILE_PATH+tmpcontent;
                    //System.out.println(tmpcontent);
                    BufferedImage bufferedImage;
                    try {
                        bufferedImage = javax.imageio.ImageIO.read(new File(tmpcontent));
                        int width = bufferedImage.getWidth();
                        int height = bufferedImage.getHeight();
                        //System.out.println(width);
                        //System.out.println(height);
                        Pic pic=new Pic();
                        pic.setSectionid(post.getSectionid());
                        pic.setHeight(height);
                        pic.setWidth(width);
                        pic.setPath(picpath);
                        pic.setIsdel(false);
                        pic.setPictype("0");
                        pic.setCreateuserid(sessionUser.getId());
                        pic.setPostid(post.getId());
                        picService.insert(pic);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        //启动缓存线程
        new com.bbs.tool.IndexThread().start();
        return "redirect:/bk/index.do?sectionId=" + post.getSectionid();
    }

    /**
     * 跳转编辑帖子页面
     *
     * @param request
     * @param response
     * @param model
     * @param pageBean
     * @param postId
     * @return
     */
    @RequestMapping("toupdate.do")
    public String toupdate(HttpServletRequest request, HttpServletResponse response, Model model, PageBean<Post> pageBean, String postId) {
        Post post = new Post();
        Section section = new Section();
        post.setId(Integer.parseInt(postId));
        post = postService.findByPost(post);
        Integer sectionid = post.getSectionid();
        section.setId(sectionid);
        section = sectionService.findById(section);
        Theme theme = new Theme();
        theme.setSectionid(sectionid);
        List<Theme> list = themeService.find(sectionid);
        pageBean = postService.findPageById(post, pageBean);
        int count = postService.findCount(sectionid + "");
        model.addAttribute("themeList", list);
        model.addAttribute("sectionId", sectionid);
        model.addAttribute("section", section);
        model.addAttribute("page", pageBean);
        model.addAttribute("nowcount", count);
        model.addAttribute("post", post);
        return "jsp/index/user/updatePost";

    }

    /*
    *//**
     * 编辑帖子
     * @param request
     * @param response
     * @param model
     * @param post
     * @return
     *//*
    @RequestMapping("update.do")
    public String update(HttpServletRequest request,HttpServletResponse response,Model model,Post post
    ){
        Tiezi post = postService.findByPostWhere(tiezi);
        User sessionUser =  (User)request.getSession().getAttribute(com.bbs.tool.PublicStatic.USER);
        if(Tool.isAdmin(sessionUser)){
            if(sessionUser.getId()!=post.getCreateuserid()){
                return "redirect:/topage/permission.do";
            }
        }

        //更新帖子信息，保留操作日志
        Tiezi postUpdate = new Tiezi();
        postUpdate.setId(tiezi.getId());
        postUpdate.setZhutiid(tiezi.getZhutiid());
        postUpdate.setName(tiezi.getName());
        postUpdate.setContenthtml(tiezi.getContenthtml());
        postUpdate.setContenttxt(tiezi.getContenttxt());
        postUpdate.setLastupdate("您的帖子在"+Tool.getyyyyMMddHHmmss()+"被"+sessionUser.getLoginname()+"编辑<br/>");
        postUpdate.setName(Tool.replaceHtml(tiezi.getName()));
        postUpdate.setContenthtml(Tool.replaceHtml(tiezi.getContenthtml()));
        postService.update(postUpdate);
        return "redirect:/bk/index.do?bankuaiid="+tiezi.getBankuaiId();
    }*/

    //删除帖子
    @ResponseBody
    @RequestMapping("del.do")
    public Map<String, String> delete(HttpServletRequest request, int id) {
        Map<String, String> map=new HashMap<String, String>();
        postService.delete(id);
        //huifuService.deletebytiezi(id);
        map.put("flag", "1");
        //启动缓存线程
        new com.bbs.tool.IndexThread().start();
        return map;
    }

    /**
     * 添加回复信息
     * @param request
     * @param response
     * @param model
     * @param id
     * @param editortxt
     * @param editorhtml
     * @param replyId
     * @return
     */
    @ResponseBody
    @RequestMapping("addReply.do")
    public String addReply(HttpServletRequest request,HttpServletResponse response,Model model,int id,String editortxt,String editorhtml,Integer replyId){
        User user = (User)request.getSession().getAttribute(com.bbs.tool.PublicStatic.USER);
        Map<String, Object> byId = postService.findById(id + "");
        Reply reply = new Reply();
        reply.setContenthtml(editorhtml);
        reply.setContenttxt(editortxt);
        reply.setCreateuserid(user.getId());
        reply.setPostid(id);
        reply.setCreatetime(Tool.getyyyyMMddHHmmss());
        reply.setIsdel(false);
        reply.setReplyid(replyId);
        //System.out.println(replyId);
        reply.setOrderby(replyService.maxCount(id)+1);
        replyService.insert(reply);
        return "1";
    }


    /**
     * 更新浏览次数
     * @param id
     * @return
     *//*
    @ResponseBody
    @RequestMapping("updatefindcount.do")
    public String updatefindcount(int id){
        postService.updateFindCount(id);
        return "1";
    }




    @RequestMapping("toReply.do")
    public String toReply(HttpServletRequest request,HttpServletResponse response,Model model,int id,int huifuid){
        model.addAttribute("id", id);
        model.addAttribute("huifuid", huifuid);
        return "jsp/index/user/addReply";
    }*/

}
