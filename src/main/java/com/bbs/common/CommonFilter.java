package com.bbs.common;


import com.bbs.pojo.Info;
import com.bbs.service.InfoService;
import com.bbs.tool.ConfigPropertiesUtil;
import com.bbs.tool.IndexThread;
import com.bbs.tool.PublicStatic;
import com.bbs.tool.ToolSpring;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拦截处理器
 */
public class CommonFilter implements Filter {


	public void destroy() {

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) arg0;
		HttpServletResponse response=(HttpServletResponse) arg1;

		String urlString=request.getRequestURI();
		int lastIndexOf = urlString.lastIndexOf(".")+1;
		//静态化转换
		if(PublicStatic.HTML.equals(urlString.substring(lastIndexOf, urlString.length()))){
			htmlfilter(request, response, chain, urlString);
		}else{
			String path=request.getServletPath();
			Integer id=isSection(path);
			//板块映射成二级目录
			if(id!=null){
				String url="/bk/index.do?sectionId="+id+"&pagestart=1&themeId=0&orderby=0";
				request.getRequestDispatcher(url).forward(request, response);
				return;
			}else{
				chain.doFilter(request, response);
			}
		}
	}

	//静态化页面，转化controller访问
	public void htmlfilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain, String urlString)throws IOException, ServletException {
		String url="";
		if(!"".equals(request.getContextPath())){
			urlString=urlString.replaceAll(request.getContextPath(), "");
		}
		urlString=urlString.substring(1, urlString.length());
		String[] urlsplit = urlString.split("/", -1);
		if(urlsplit.length==1){
			//首页父栏目拦截  /29.html
			String[] split=urlsplit[0].split("\\.", -1);
			if(split[1].equalsIgnoreCase(PublicStatic.HTML)){
				if(split[0].equals("baidu_verify_VHapi6TZSQ")){
					url="/baidu_verify_VHapi6TZSQ.html";
				}else{
					url="/index.do?parentId="+split[0];
				}
			}
		}else if(urlsplit.length==2){
			//板块 /bk/29_1_0_0.html
			if("bk".equals(urlsplit[0])){
				String[] split=urlsplit[1].split("\\.", -1);
				if(split[1].equalsIgnoreCase(PublicStatic.HTML)){
					split=split[0].split("_", -1);
					url="/bk/index.do?sectionId="+split[0]+"&pagestart="+split[1]+"&themeId="+split[2]+"&orderby="+split[3]+"";
				}
			}else if("post".equals(urlsplit[0])){
				//内容  /tiezi/2_1.html
				String[] split=urlsplit[1].split("\\.", -1);
				if(split[1].equalsIgnoreCase(PublicStatic.HTML)){
					split=split[0].split("_", -1);
					url="/pt/index.do?id="+split[0]+"&pagestart="+split[1]+"&orderby="+split[2];
				}
			}else if("member".equals(urlsplit[0])){
				//个人资料  /member/2_1.html
				String[] split=urlsplit[1].split("\\.", -1);
				if(split[1].equalsIgnoreCase(PublicStatic.HTML)){
					split=split[0].split("_", -1);
					if(split.length==2){
						url="/user/data.do?id="+split[0]+"&flag="+split[1];
					}else{
						url="/user/data.do?id="+split[0]+"&flag="+split[1]+"&pagestart="+split[2];
					}
					
				}
			}else if("file".equals(urlsplit[0])){
				System.out.println(urlsplit[0]);
			}else{
				String path=request.getServletPath();
				Integer id=isSection(path);
				if(id!=null){
					String[] split=urlsplit[1].split("\\.", -1);
					if(split[1].equalsIgnoreCase(PublicStatic.HTML)){
						split=split[0].split("_", -1);
						url="/bk/index.do?sectionId="+id+"&pagestart="+split[0]+"&themeId="+split[1]+"&orderby="+split[2]+"";
					}
				}
			}
		}
		
		if(!"".equals(url)){
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}
	
	public Integer isSection(String path){
		//System.out.println(path);
		String[] split = path.split("/");
		Integer id=null;
		if(PublicStatic.sectionMap!=null&&split.length>=2){
			id = PublicStatic.sectionMap.get(split[1]);
		}
		return id;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		//获取上传文件路径
		String path = ConfigPropertiesUtil.getUpalodDir();
		if (path.equals("javaee")||"".equals(path)) {
			path = filterConfig.getServletContext().getRealPath("/");
		}
		path += "/file/upload/";
		PublicStatic.FILE_PATH=path;
		InfoService infoService=(InfoService) ToolSpring.getBean("infoService");
		Info info = infoService.find();
		filterConfig.getServletContext().setAttribute("bbsinfo", info);
        new IndexThread().start();
	}
	
	
}
