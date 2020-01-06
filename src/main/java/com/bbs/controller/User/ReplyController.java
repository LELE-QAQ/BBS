package com.bbs.controller.User;

import com.bbs.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/26
 * Time: 22:56
 */
@Controller
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @ResponseBody
    @RequestMapping("/delReply.do")
    public int delReply(Model model,Integer id){
        int flag = replyService.delReply(id);
        return flag;
    }
}
