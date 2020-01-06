package com.bbs.service;

import com.bbs.pojo.PageBean;
import com.bbs.pojo.Post;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/17
 * Time: 21:50
 */
public interface PostService {
    void update(Post post);

    PageBean<Post> findPageById(Post post, PageBean<Post> page);

    int findCount(String sectionId);

    List<Post> findTop(String sectionId);

    Map<String,Object> findById(String id);

    void insert(Post post);

    Post findByPost(Post post);

    void delete(int id);
}
