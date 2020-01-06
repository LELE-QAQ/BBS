package com.bbs.service;

import com.bbs.pojo.PageBean;
import com.bbs.pojo.Reply;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/17
 * Time: 21:51
 */
public interface ReplyService {
    PageBean<Map<String,Object>> findPage(HashMap<String, Object> qmap, PageBean<Map<String,Object>> page);

    int delReply(Integer id);

    Integer maxCount(int id);

    void insert(Reply reply);
}
