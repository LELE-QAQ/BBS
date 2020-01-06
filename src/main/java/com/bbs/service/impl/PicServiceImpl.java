package com.bbs.service.impl;

import com.bbs.dao.PicMapper;
import com.bbs.pojo.Pic;
import com.bbs.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/17
 * Time: 21:49
 */
@Service("picService")
public class PicServiceImpl implements PicService {

    @Autowired
    private PicMapper picMapper;


    @Override
    public void insert(Pic pic) {
        picMapper.insertSelective(pic);
    }

    @Override
    public List<Pic> indexPic() {
        return picMapper.indexPic();
    }
}
