package com.bbs.controller.index;


import com.bbs.pojo.User;
import com.bbs.pojo.UserExample;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Description:登陆
 * Created By KL
 * Date: 2019/8/2
 * Time: 19:53
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;


    @RequestMapping("index.do")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response, String pathlocation) {
        model.addAttribute("pathlocation", pathlocation);
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if ("bbs".equals(name)) {
                String value = cookie.getValue();
                UserExample userExample = new UserExample();
                userExample.createCriteria().andCookieidEqualTo(value);
                User isUser = userService.isUser(userExample);
                if (isUser != null) {
                    request.getSession().setAttribute(com.bbs.tool.PublicStatic.USER, isUser);
                   /* Cookie c = new Cookie("login", isUser.getCookieid());
                    c.setMaxAge(60 * 60 * 24 * 14);
                    c.setPath("/");
                    response.addCookie(cookie);*/
                    if (pathlocation != null && !pathlocation.isEmpty()) {
                        try {
                            response.sendRedirect(pathlocation);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        return "redirect:/";
                    }
                }

            }
        }
        return "jsp/index/login";
    }

    /**
     * 登录
     * @param request
     * @param response
     * @param user
     * @param pathlocation
     * @return
     */
    @ResponseBody
    @RequestMapping("login.do")
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response, User user, String pathlocation) {
        HashMap<String, Object> map = new HashMap<>();
        int flag = userService.login(user, request);
        if (pathlocation != null && !pathlocation.isEmpty()) {
            map.put("pathlocation", pathlocation);
        } else {
            map.put("pathlocation", request.getContextPath() + "/user/index.do");
        }
        User userSession = (User) request.getSession().getAttribute(com.bbs.tool.PublicStatic.USER);

        //cookie
        /*if (user.isIsremember() == true) {
            Cookie cookie = new Cookie("bbs", userSession.getCookieid());
            cookie.setMaxAge(60 * 60 * 24 * 14);
            cookie.setPath("/");
            response.addCookie(cookie);l
        }*/
        /*System.out.println("*************************");
        System.out.println(cookie.getValue());
        System.out.println(userSession.getLoginname());
        System.out.println(pathlocation);
        System.out.println(flag);
        System.out.println(userSession);
        System.out.println(userSession.getGroupid());
        System.out.println("*************************");*/
        //map.put("flag", flag);
        map.put("flag",flag);
        System.out.println(pathlocation);
        return map;
    }
}
