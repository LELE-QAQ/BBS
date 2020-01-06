package com.bbs.controller.User;


import com.bbs.pojo.PageBean;
import com.bbs.pojo.Post;
import com.bbs.pojo.User;
import com.bbs.pojo.UserExample;
import com.bbs.service.PostService;
import com.bbs.service.UserService;
import com.bbs.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA
 * Description:用户操作
 * Created By KL
 * Date: 2019/8/1
 * Time: 18:21
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;


    /**
     * 用户个人资料
     *
     * @return
     */
    @RequestMapping("index.do")
    public String index(HttpServletRequest request) {
        return "jsp/index/user/user-data";
    }

    /**
     * 更新用户信息
     *
     * @param request
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("update.do")
    public String update(HttpServletRequest request, Model model, User user) {
        User sessionUser = (User) request.getSession().getAttribute(com.bbs.tool.PublicStatic.USER);
        user.setId(sessionUser.getId());
        userService.update(user);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(sessionUser.getId());
        User isUSer = userService.isUser(userExample);
        request.getSession().setAttribute(com.bbs.tool.PublicStatic.USER, isUSer);
        return "redirect:/user/index.do";
    }


    /**
     * 注销
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("logout.do")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return "redirect:/";
    }

    /**
     * 跳转修改密码界面
     *
     * @param request
     * @return
     */
    @RequestMapping("/toRepwd.do")
    public String toRepwd(HttpServletRequest request) {
        return "jsp/index/user/user-pwd";
    }

    /**
     * 修改密码
     *
     * @param request
     * @param pwd
     * @param newpwd
     * @return
     */
    @ResponseBody
    @RequestMapping("/repwd.do")
    public String rePwd(HttpServletRequest request, String pwd, String newpwd) {
        User sessionUser = (User) request.getSession().getAttribute(com.bbs.tool.PublicStatic.USER);
        String userPwd = sessionUser.getPassword();
        pwd = Tool.MD5(pwd);
        if (userPwd.equals(pwd)) {
            sessionUser.setPassword(Tool.MD5(newpwd));
            userService.update(sessionUser);
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * 查询用户信息
     * @param request
     * @param model
     * @param id
     * @param flag
     * @param pageBean
     * @return
     */
    @RequestMapping("data.do")
    public String data(HttpServletRequest request, Model model, int id, String flag, PageBean<Post> pageBean){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(id);
        User user = userService.isUser(userExample);
        model.addAttribute("user",user);
        return "jsp/index/member/xinxi-ziliao";
        /*if("1".equals(flag)){
            Map<String, Object> data = userService.findData(user);
            model.addAttribute("ziliao", data);
            //查询个人资料
            return "jsp/index/member/xinxi-ziliao";
        }else if("2".equals(flag)){
            //查询查询主题
            Tiezi tiezi=new Tiezi();
            tiezi.setCreateuserid(user.getId());
            pageBean.setPageSize(10);
            PageBean<Tiezi> postPage = postService.findPageById(tiezi, pageBean);
            model.addAttribute("tiezipage", postPage);
            return "jsp/index/member/xinxi-zhuti";
        }*//*else if("3".equals(flag)){
            //查询留言

            List<Map<String, Object>> = userLiuyanService.find(id);
            model.addAttribute("liuyanlist", liuyanlist);
            return "jsp/index/member/xinxi-liuyan";
        }*//*else{
            //查询个人资料
            return "jsp/index/member/xinxi-ziliao";
        }*/
    }


}
