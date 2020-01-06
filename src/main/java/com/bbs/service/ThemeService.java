package com.bbs.service;

import com.bbs.pojo.Theme;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/17
 * Time: 21:50
 */
public interface ThemeService {

    List<Theme> find(Integer sectionId);

    Theme findById(Theme theme);

    void update(Theme theme);

    void insert(Theme theme);

    String delete(int id);

}
