package com.bbs.service;

import com.bbs.pojo.Pic;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/24
 * Time: 17:39
 */
public interface PicService {
    void insert(Pic pic);

    List<Pic> indexPic();

}
