package com.bbs.service;

import com.bbs.pojo.PageBean;
import com.bbs.pojo.User;
import com.bbs.pojo.UserExample;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/17
 * Time: 21:49
 */
public interface UserService {
    PageBean<User> findPage(User user, PageBean<User> pageBean);

    String delete(Integer id);

    void update(User user);

    boolean updateFlag(User user);

    User isUser(UserExample userExample);

    int login(User user, HttpServletRequest request);

    void insert(User user);

    int isUsername(String loginname);
}
