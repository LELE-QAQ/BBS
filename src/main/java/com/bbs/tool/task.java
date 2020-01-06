package com.bbs.tool;


import com.bbs.dao.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/10/14
 * Time: 15:52
 */
@Component
public class task {

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     *查询最新帖子
     */
    public void newPost(){
        String i = "newpost";
        HashMap<String, Object> map = new HashMap<>();
        map.put("zdate",Tool.getq_zy_yyyy_MM_dd());
        //System.out.println(Tool.getq_zy_yyyy_MM_dd());
        //PublicStatic.nPost = tieziMapper.findNewPost(map);
        //List<Map<String, Object>> newPost = postMapper.findNewPost(map);
        //redisTemplate.boundHashOps(Post.class.getSimpleName()).put(i,newPost);
    }
}
