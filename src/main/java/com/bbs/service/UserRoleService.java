package com.bbs.service;

import com.bbs.pojo.UserRole;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/17
 * Time: 21:49
 */
public interface UserRoleService {

    boolean updateAdmin(UserRole userRole);

    void insert(int id, int groupvip);
}
