package com.bbs.service.impl;

import com.bbs.dao.UserMapper;
import com.bbs.dao.UserRoleMapper;
import com.bbs.pojo.*;
import com.bbs.service.UserService;
import com.bbs.tool.PublicStatic;
import com.bbs.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/17
 * Time: 21:49
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 分页查询用户
     *
     * @param user
     * @param pageBean
     * @return
     */
    @Override
    public PageBean<User> findPage(User user, PageBean<User> pageBean) {
        pageBean.setBean(user);
        int count = userMapper.findPageCount(pageBean);
        pageBean.setTotalRecords(count);
        List<User> list = userMapper.findPage(pageBean);
        pageBean.setList(list);
        return pageBean;
    }

    /**
     * 用户删除
     *
     * @param id
     * @return
     */
    @Override
    public String delete(Integer id) {
        String result = "1";
        try {
            userMapper.deleteByPrimaryKey(id);

        } catch (Exception e) {
            return "0";
        }
        return result;
    }

    /**
     * 更新用户信息
     *
     * @param user
     */
    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public boolean updateFlag(User user) {
        boolean b = true;
        try {
            userMapper.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
        }
        return b;
    }

    @Override
    public User isUser(UserExample userExample) {
        List<User> userList = userMapper.selectByExample(userExample);
        User user = userList.get(0);
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUseridEqualTo(user.getId());
        List<UserRole> userRoleList = userRoleMapper.selectByExample(example);
        UserRole userRole = userRoleList.get(0);
        user.setRoleId(userRole.getRoleid());
        return user;
    }

    /**
     * 用户登录
     * @param user
     * @param request
     * @return
     */
    @Override
    public int login(User user, HttpServletRequest request) {
        int flag;
        UserExample userExample = new UserExample();
        UserRoleExample example = new UserRoleExample();
        userExample.createCriteria().andLoginnameEqualTo(user.getLoginname());
        List<User> userList = userMapper.selectByExample(userExample);
        User isUser = userList.get(0);
        example.createCriteria().andUseridEqualTo(isUser.getId());
        List<UserRole> userRoleList = userRoleMapper.selectByExample(example);
        UserRole userRole = userRoleList.get(0);
        isUser.setRoleId(userRole.getRoleid());
        if (isUser != null) {
            String pwd = user.getPassword();
            pwd = Tool.MD5(pwd);
            if (isUser.getPassword().equals(pwd)) {
                flag = isUser.getFlag();
                if (flag == 1) {
                    User userSession = isUser;
                    userSession.setLastlogintime(Tool.getyyyyMMddHHmmss());
                    userSession.setLoginIp(Tool.getIp(request));
                    userSession.setCookieid(Tool.getUUID());
                    userMapper.updateByPrimaryKeySelective(userSession);
                    request.getSession().setAttribute(PublicStatic.USER, userSession);
                    return flag;
                } else if (flag == 0) {
                    flag = 66;
                    return flag;
                }
            } else {
                flag = 88;
                return flag;
            }
        } else {
            flag = 99;
            return flag;
        }
        return flag;
    }

    /**
     * 添加用户
     * @param user
     */
    @Override
    public void insert(User user) {
        userMapper.insertSelective(user);
    }

    /**
     * 用户名是否存在
     * @param loginname
     * @return
     */
    @Override
    public int isUsername(String loginname) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andLoginnameEqualTo(loginname);
        List<User> userList = userMapper.selectByExample(userExample);
        if(userList != null && userList.size() > 0){
            return 0;
        }
        return 1;
    }


}
