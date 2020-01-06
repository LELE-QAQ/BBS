package com.bbs.controller.Admin;


import com.bbs.pojo.PageBean;
import com.bbs.pojo.User;
import com.bbs.pojo.UserRole;
import com.bbs.service.UserRoleService;
import com.bbs.service.UserService;
import com.bbs.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA
 * Description:管理用户
 * Created By KL
 * Date: 2019/8/18
 * Time: 15:55
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 跳转用户管理界面
     * @param request
     * @param model
     * @param user
     * @param pageBean
     * @return
     */
    @RequestMapping("index.do")
    public String index(HttpServletRequest request, Model model, User user, PageBean<User> pageBean){
        pageBean = userService.findPage(user,pageBean);
        //System.out.println(pageBean);
        model.addAttribute("page", pageBean);
        model.addAttribute("queryUser", user);
        return "jsp/admin/user/index";
    }


    /**
     * 删除用户
     * @param request
     * @param model
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("del.do")
    public String delete(HttpServletRequest request,Model model,User user){
        return userService.delete(user.getId());
    }

    /**
     * 重置密码
     * @param request
     * @param model
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("resetpwd.do")
    public String resetPwd(HttpServletRequest request,Model model,User user){
        String pwd  = "123456";
        user.setPassword(Tool.MD5(pwd));
        try {
            userService.update(user);
        }catch (Exception e){
            return "0";
        }
        return "1";
    }


    /**
     * 设置版主
     * @param request
     * @param model
     * @param user
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping("updateadmin.do")
    public String updateAdmin(HttpServletRequest request,Model model,User user,int roleId){
        //user.setRoleId(roleId);
        UserRole userRole = new UserRole();
        userRole.setUserid(user.getId());
        userRole.setRoleid(roleId);
        boolean b = userRoleService.updateAdmin(userRole);
        if(b){
            return "1";
        }else{
            return "0";
        }
    }

    /**
     * 设置flag
     * @param request
     * @param model
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("updateflag.do")
    public String updateFlag(HttpServletRequest request,Model model,User user,int flag){
        user.setFlag(flag);
        boolean b = userService.updateFlag(user);
        if(b){
            return "1";
        }else{
            return "0";
        }
    }


}
