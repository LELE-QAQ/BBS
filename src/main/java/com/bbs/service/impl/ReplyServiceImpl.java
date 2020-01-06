package com.bbs.service.impl;

import com.bbs.dao.ReplyMapper;
import com.bbs.pojo.PageBean;
import com.bbs.pojo.Reply;
import com.bbs.pojo.ReplyExample;
import com.bbs.service.ReplyService;
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
 * Time: 21:51
 */
@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public PageBean<Map<String, Object>> findPage(HashMap<String, Object> qmap, PageBean<Map<String, Object>> page) {
        page.setBean(qmap);
        int count =  replyMapper.findPageCount(page);
        page.setTotalRecords(count);
        List<Map<String, Object>> list = replyMapper.findPage(page);
        for (Map<String, Object> stringObjectMap : list) {
            //如果回复被删除，替换回复内容
            String isdel=stringObjectMap.get("isdel")+"";
            if("1".equals(isdel)){
                stringObjectMap.put("contenthtml", "<div class=\"pct\"><div class=\"pcb\"><div class=\"locked\">提示: <em>该作者的回复内容被删除屏蔽</em></div></div></div>");
                stringObjectMap.put("contenttxt", "作者被禁止或删除 内容自动屏蔽");
            }
            String pisdel=stringObjectMap.get("pisdel")+"";
            if("1".equals(pisdel)){
                stringObjectMap.put("pcontenthtml", "<div class=\"pct\"><div class=\"pcb\"><div class=\"locked\">提示: <em>该作者的回复内容被删除屏蔽</em></div></div></div>");
                stringObjectMap.put("pcontenttxt", "作者被禁止或删除 内容自动屏蔽");
            }

        }
        page.setList(list);
        return  page;
    }

    /**
     * 删除回复
     * @param id
     * @return
     */
    @Override
    public int delReply(Integer id) {
        int flag = replyMapper.deleteByPrimaryKey(id);
        return flag;
    }

    @Override
    public Integer maxCount(int id) {
        ReplyExample replyExample = new ReplyExample();
        replyExample.createCriteria().andPostidEqualTo(id);
        return (int)replyMapper.countByExample(replyExample);

    }

    @Override
    public void insert(Reply reply) {
        replyMapper.insertSelective(reply);
    }
}
