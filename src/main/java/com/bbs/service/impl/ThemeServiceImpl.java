package com.bbs.service.impl;

import com.bbs.dao.PostMapper;
import com.bbs.dao.ThemeMapper;
import com.bbs.pojo.Post;
import com.bbs.pojo.PostExample;
import com.bbs.pojo.Theme;
import com.bbs.pojo.ThemeExample;
import com.bbs.service.ThemeService;
import com.bbs.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/17
 * Time: 21:50
 */
@Service("themeService")
public class
ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeMapper themeMapper;
    @Autowired
    private PostMapper postMapper;

    /**
     * 查询版块下所有主题
     *
     * @param sectionId
     * @return
     */
    @Override
    public List<Theme> find(Integer sectionId) {
        ThemeExample themeExample = new ThemeExample();
        themeExample.createCriteria().andSectionidEqualTo(sectionId);
        List<Theme> themeList = themeMapper.selectByExample(themeExample);
        return themeList;
    }

    /**
     * 根据ID查询主题
     *
     * @param theme
     * @return
     */
    @Override
    public Theme findById(Theme theme) {
        return themeMapper.selectByPrimaryKey(theme.getId());
    }

    /**
     * 更新主题
     *
     * @param theme
     */
    @Override
    public void update(Theme theme) {
        themeMapper.updateByPrimaryKeySelective(theme);
    }

    /**
     * 添加主题
     *
     * @param theme
     */
    @Override
    public void insert(Theme theme) {

        theme.setCreatetime(Tool.getyyyyMMddHHmmss());
        if (theme.getOrderby() == null) {
            int count = themeMapper.findMaxOrder(theme.getSectionid());
            theme.setOrderby(count + 1);
        }
        themeMapper.insertSelective(theme);
    }

    /**
     * 删除主题
     *
     * @param id
     * @return
     */
    @Override
    public String delete(int id) {
        String result = "1";
        PostExample example = new PostExample();
        example.createCriteria().andThemeidEqualTo(id);
        List<Post> postList = postMapper.selectByExample(example);
        if (postList.size() > 0) {
            result = "0";
        } else {
            themeMapper.deleteByPrimaryKey(id);
        }
        return result;
    }
}
