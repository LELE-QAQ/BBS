package com.bbs.service.impl;

import com.bbs.dao.UserRoleMapper;
import com.bbs.pojo.UserRole;
import com.bbs.pojo.UserRoleExample;
import com.bbs.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/17
 * Time: 21:49
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    public boolean updateAdmin(UserRole userRole) {
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUseridEqualTo(userRole.getUserid());
        boolean b = true;
        try {
            userRoleMapper.updateByExampleSelective(userRole, example);
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
        }
        return b;
    }

    /**
     * 添加用户与角色权限
     * @param id
     * @param groupvip
     */
    @Override
    public void insert(int id, int groupvip) {
        UserRole userRole = new UserRole();
        userRole.setUserid(id);
        userRole.setRoleid(groupvip);
        userRoleMapper.insertSelective(userRole);
    }
}
