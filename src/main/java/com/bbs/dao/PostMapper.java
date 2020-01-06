package com.bbs.dao;

import com.bbs.pojo.PageBean;
import com.bbs.pojo.Post;
import com.bbs.pojo.PostExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PostMapper {
    long countByExample(PostExample example);

    int deleteByExample(PostExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Post record);

    int insertSelective(Post record);

    List<Post> selectByExample(PostExample example);

    Post selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Post record, @Param("example") PostExample example);

    int updateByExample(@Param("record") Post record, @Param("example") PostExample example);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);

    int findPageCount(PageBean<Post> page);

    List<Post> findPage(PageBean<Post> page);

    int findCount(Post post);

    Map<String,Object> findById(Post post);

    Post findByPost(Post post);

    List<Post> findTopList(Integer sectionId);
}