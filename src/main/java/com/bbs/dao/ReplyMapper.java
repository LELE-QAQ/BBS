package com.bbs.dao;

import com.bbs.pojo.PageBean;
import com.bbs.pojo.Reply;
import com.bbs.pojo.ReplyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReplyMapper {
    long countByExample(ReplyExample example);

    int deleteByExample(ReplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Reply record);

    int insertSelective(Reply record);

    List<Reply> selectByExample(ReplyExample example);

    Reply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Reply record, @Param("example") ReplyExample example);

    int updateByExample(@Param("record") Reply record, @Param("example") ReplyExample example);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKey(Reply record);

    int findPageCount(PageBean<Map<String,Object>> page);

    List<Map<String,Object>> findPage(PageBean<Map<String,Object>> page);

    void deleteByPostId(int id);
}