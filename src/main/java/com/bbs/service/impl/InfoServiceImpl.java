package com.bbs.service.impl;

import com.bbs.dao.InfoMapper;
import com.bbs.pojo.Info;
import com.bbs.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/17
 * Time: 21:51
 */
@Service("infoService")
public class InfoServiceImpl implements InfoService {
    @Autowired
    private InfoMapper infoMapper;

    /**
     * 查询网站信息
     *
     * @return
     */
    @Override
    public Info find() {
        int id = 1;
        return infoMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新网站信息
     *
     * @param info
     */
    @Override
    public void update(Info info) {
        infoMapper.updateByPrimaryKeySelective(info);
    }
}
