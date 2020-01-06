package com.bbs.service;

import com.bbs.pojo.PageBean;
import com.bbs.pojo.Section;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/17
 * Time: 21:48
 */
public interface SectionService {
    PageBean<Section> findPage(Section section, PageBean<Section> pageBean);

    Section findById(Section section);

    List<Section> findByParentId();


    void update(Section section);

    void insert(Section section);

    int del(int id);

    List<Section> findAll();


}
