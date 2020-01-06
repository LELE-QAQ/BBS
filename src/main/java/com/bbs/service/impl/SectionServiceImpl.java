package com.bbs.service.impl;

import com.bbs.dao.PostMapper;
import com.bbs.dao.SectionMapper;
import com.bbs.pojo.*;
import com.bbs.service.SectionService;
import com.bbs.tool.PublicStatic;
import com.bbs.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/17
 * Time: 21:48
 */
@Service("sectionService")
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionMapper sectionMapper;

    @Autowired
    private PostMapper postMapper;

    /**
     * 版块分页
     *
     * @param section
     * @param pageBean
     * @return
     */
    @Override
    public PageBean<Section> findPage(Section section, PageBean<Section> pageBean) {
        pageBean.setBean(section);
        int count = sectionMapper.findPageCount(pageBean);
        pageBean.setTotalRecords(count);
        List<Section> list = sectionMapper.findPage(pageBean);
        pageBean.setList(list);
        return pageBean;
    }

    /**
     * 根据ID查找版块
     *
     * @param section
     * @return
     */
    @Override
    public Section findById(Section section) {
        SectionExample example = new SectionExample();
        example.createCriteria().andIdEqualTo(section.getId());
        List<Section> sectionList = sectionMapper.selectByExample(example);
        if (sectionList != null && sectionList.size() > 0) {
            section = sectionList.get(0);
        }
        return section;
    }

    /**
     * 查询所有父版块
     *
     * @return
     */
    @Override
    public List<Section> findByParentId() {
        SectionExample example = new SectionExample();
        example.createCriteria().andParentidEqualTo(0);
        return sectionMapper.selectByExample(example);
    }

    /**
     * 更新板块信息
     *
     * @param section
     */
    @Override
    public void update(Section section) {
        sectionMapper.updateByPrimaryKeySelective(section);
    }

    /**
     * 添加版块
     *
     * @param section
     */
    @Override
    public void insert(Section section) {
        if (section.getOrderby() == null) {
            int maxOrder = sectionMapper.findMaxOrder(section.getParentid());
            section.setOrderby(maxOrder + 1);
        }
        section.setCreatetime(Tool.getyyyyMMddHHmmss());
        sectionMapper.insertSelective(section);
    }

    /**
     * 删除版块
     *
     * @param id
     * @return
     */
    @Override
    public int del(int id) {
        int result = 9;
        SectionExample sectionExample = new SectionExample();
        sectionExample.createCriteria().andParentidEqualTo(id);
        List<Section> sectionList = sectionMapper.selectByExample(sectionExample);

        PostExample postExample = new PostExample();
        postExample.createCriteria().andSectionidEqualTo(id);
        List<Post> postList = postMapper.selectByExample(postExample);
        if (sectionList.size() > 0) {
            result = 0;
            return result;
        }
        if(postList.size() > 0){
            result = 1;
            return result;
        }
        sectionMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 查询全部版块
     *
     * @return
     */
    @Override
    public List<Section> findAll() {
        List<Section> sectionList = sectionMapper.findAll();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (Section section : sectionList) {
            String shorthand = section.getShorthand();
            Integer id = section.getId();
            map.put(shorthand, id);
        }
        PublicStatic.sectionMap = map;
        return sectionList;
    }


}
