package com.bbs.service.impl;

import com.bbs.dao.PicMapper;
import com.bbs.dao.PostMapper;
import com.bbs.dao.ReplyMapper;
import com.bbs.pojo.PageBean;
import com.bbs.pojo.Post;
import com.bbs.service.PostService;
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
 * Time: 21:50
 */
@Service("postService")
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private PicMapper picMapper;

    /**
     * 更新帖子信息
     *
     * @param post
     */
    @Override
    public void update(Post post) {
        postMapper.updateByPrimaryKeySelective(post);
    }

    /**
     *
     * @param post
     * @param page
     * @return
     */
    @Override
    public PageBean<Post> findPageById(Post post, PageBean<Post> page) {
        String orderby = post.getOrderby();
        if ("0".equals(orderby)) {
            orderby = " t1.id  ";
        } else if ("1".equals(orderby)) {
            orderby = " t5.createtime ";
        } else if ("2".equals(orderby)) {
            orderby = " t1.excellent ";
        } else {
            orderby = " t1.id ";
        }
        post.setOrderby(orderby);
        page.setBean(post);
        int count = postMapper.findPageCount(page);
        page.setTotalRecords(count);
        List<Post> list = postMapper.findPage(page);
        page.setList(list);
        return page;
    }

    /**
     * 当日发帖总数
     *
     * @param sectionId
     * @return
     */
    @Override
    public int findCount(String sectionId) {
        Post post = new Post();
        post.setSectionid(Integer.parseInt(sectionId));
        post.setCreatetime(Tool.getyyyy_MM_dd());
        return postMapper.findCount(post);
    }

    @Override
    public List<Post> findTop(String sectionId) {
        /*PostExample example = new PostExample();
        example.createCriteria().andSectionidEqualTo(Integer.parseInt(sectionId))
                .andTopBetween("1", "2");
        List<Post> postList = postMapper.selectByExample(example);*/
        List<Post> postList = postMapper.findTopList(Integer.parseInt(sectionId));
        return postList;
    }

    @Override
    public Map<String, Object> findById(String id) {
        HashMap<String, Object> map = new HashMap<>();
        Post post = new Post();
        post.setId(Integer.parseInt(id));

        Map<String,Object> postMap = postMapper.findById(post);
        String contenttxt = postMap.get("contenttxt")+"";
        if(contenttxt != null && contenttxt.length()>200){
            //对页面描述(<meta name="description" content="${map.tiezi.contenttxt}" /> )的修改，截取并替换<>
            contenttxt=contenttxt.substring(0, 190)+"...";
            contenttxt=contenttxt.replaceAll("<", "");
            contenttxt=contenttxt.replaceAll(">", "");
            postMap.put("contenttxt", contenttxt);
        }
        String contenthtml =  postMap.get("contenthtml")+"";
        /*if(contenthtml != null && contenthtml.length()>0){
            List<InnerLink> innerLinks =  PublicStatic.innerLink;
            for (InnerLink innerLink : innerLinks) {
                String name = innerLink.getName();
                String innerLinkurl=innerLink.getInnerlink();
                contenthtml=contenthtml.replaceAll(name, "<a href=\""+innerLinkurl+"\" title=\""+name+"\" target=\"_blank\">"+name+"</a>");
            }
            postMap.put("contenthtml",contenthtml);
        }*/
        map.put("post", postMap);
        //map.put("findgroup", postMap.get("findgroup"));
        //map.put("fatiegroup", postMap.get("fatiegroup"));
        //map.put("huifugroup", postMap.get("huifugroup"));
        map.put("createuserid", postMap.get("createuserid"));
        map.put("contenttxt", postMap.get("contenttxt"));
        return map;
    }

    @Override
    public void insert(Post post) {
        postMapper.insertSelective(post);
    }

    @Override
    public Post findByPost(Post post) {
        return postMapper.findByPost(post);
    }

    @Override
    public void delete(int id) {
        postMapper.deleteByPrimaryKey(id);
        replyMapper.deleteByPostId(id);
        picMapper.deleteByPostId(id);
    }
}
