package com.bbs.service;

import com.bbs.pojo.Info;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/17
 * Time: 21:49
 */
public interface InfoService {
    Info find();

    void update(Info info);
}
