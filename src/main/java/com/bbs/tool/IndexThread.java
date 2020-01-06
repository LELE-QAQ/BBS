package com.bbs.tool;


import com.bbs.service.PostService;
import com.bbs.service.SectionService;
import com.bbs.service.UserService;

public class IndexThread extends Thread{
	//private static Logger log = Logger.getLogger(IndexThread.class);



	SectionService sectionService=(SectionService) ToolSpring.getBean("sectionService");
	PostService postService = (PostService)ToolSpring.getBean("postService");
	UserService userService = (UserService)ToolSpring.getBean("userService");
	@Override
	public void run() {
			//缓存数据和更新数据-暂时调用位置有 新增帖子，删除帖子 新增回复  删除回复
			long start=System.currentTimeMillis();
			//缓存板块信息
			sectionService.findAll();
			//postService.newPost();
			//postService.updateLastReplyId();
			//userService.indexMath();
			//log.info("执行耗时:"+(System.currentTimeMillis()-start));
	}
}
