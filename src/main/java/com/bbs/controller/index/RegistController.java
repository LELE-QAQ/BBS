package com.bbs.controller.index;


import com.bbs.pojo.User;
import com.bbs.service.UserRoleService;
import com.bbs.service.UserService;
import com.bbs.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Description:用户注册
 * Created By KL
 * Date: 2019/8/3
 * Time: 18:53
 */

@Controller
@RequestMapping("/regist")
public class RegistController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 跳转注册而页面
     */
    @RequestMapping("toRegist.do")
    public String toRegist(HttpServletRequest request, HttpServletResponse response) {
        return "jsp/index/regist";
    }

    /**
     * 验证登录名是否存在
     */
    @ResponseBody
    @RequestMapping("isUsername.do")
    public int isUsername(HttpServletRequest request, User user) {
        int flag = userService.isUsername(user.getLoginname());
        return flag;
    }

    /**
     * 用户注册
     */
    @ResponseBody
    @RequestMapping("regist.do")
    public Map<String, String> regist(HttpServletRequest request, HttpServletResponse response, User user) {

        HashMap<String, String> map = new HashMap<>();

        String flag = "1";
        String msg = "注册成功!";

        user.setRegistIp(Tool.getIp(request));
        user.setCreatetime(Tool.getyyyyMMddHHmmss());
        user.setLastlogintime(Tool.getyyyyMMddHHmmss());
        user.setPassword(Tool.MD5(user.getPassword()));
        user.setFlag(1);
        //user.setJifen(0);
        user.setAvatar("touxiang.jpg");
        userService.insert(user);
        //int userid = Integer.valueOf(id).intValue();
        int id = user.getId();
        //System.out.println(id);
        userRoleService.insert(id, com.bbs.tool.PublicStatic.GROUPVIP);
        map.put("flag", flag);
        map.put("msg", msg);
        return map;

    }
}
