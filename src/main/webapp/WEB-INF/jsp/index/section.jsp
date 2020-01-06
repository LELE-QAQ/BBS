<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
  <title>${section.name}-${applicationScope.bbsinfo.name} </title> 
  <meta name="keywords" content="${section.name}" /> 
  <meta name="description" content="${section.descs}" /> 
  <link rel="stylesheet" type="text/css" href="${path}/css/css/style_14_common.css" />
  <link rel="stylesheet" type="text/css" href="${path}/css/css/style_14_forum_forumdisplay.css" /> 
  <script src="${path}/public/js/jquery-1.8.2.min.js" type="text/javascript"></script>
</head>
 <body id="nv_forum" class="pg_index">
 <%@ include file="/WEB-INF/inc/top.jsp"%>
   <div id="wp" class="wp"></div> 
  <div class="qing_forum_thumb cl" style="background:url(${path}/css/img/banner/common_2_banner.jpg) no-repeat center top;"> 
   <div class="wp cl"> 
    <div id="pt" class="cl"> 
     <div class="z"> 
      <a href="${path}/" class="nvhm" title="首页">首页</a> 
      	<em>›</em> 
     	<a href="${path}/${section.parentid}.html">${section.fatherName}</a>
      	<em>›</em> 
     	<a href="${path}/${section.shorthand}/">${section.name}</a>
      <span class="qing_ptpip">今日: ${nowcount} , 总数: ${page.totalRecords}</span> 
     </div> 
    </div> 
    <div class="qing_forum_second cl">
        <c:if  test="${!empty sessionScope.indexuser}">
            <a href="${path}/post/topost.do?id=${sectionId}" class="pn pnc poster z">发新帖</a>
        </c:if>
     <ul id="thread_types" class="qttp y cl"> 
      <li <c:if test="${themeId==0}">class="xw1 a"</c:if>  ><a href="${path}/${section.shorthand}/1_0_${orderby}.html">全部<span></span></a></li>
      <c:forEach items="${themeList}" var="theme">
	      <li <c:if test="${themeId==theme.id}">class="xw1 a"</c:if> ><a href="${path}/${section.shorthand}/1_${theme.id}_${orderby}.html">${theme.name}<span></span></a></li>
      </c:forEach>
     </ul> 
    </div> 
   </div> 
  </div> 
  <div id="wp" class="wp"> 
   <div class="boardnav"> 
    <div id="ct" class="wp cl"> 
     <div class="mn"> 
      <div id="threadlist" class="tl qing_threadlist bm"> 
       <div class="th q_th"> 
        <table cellspacing="0" cellpadding="0"> 
         <tbody>
          <tr> 
           <th colspan="2"> 
            <div class="tf"> 
             <div class="z"> 
              <a href="${path}/${section.shorthand}/1_${themeId}_0.html" class=""><i class="newthead"></i>最新</a>&nbsp;
              <a href="${path}/${section.shorthand}/1_${themeId}_1.html" class=""><i class="lastthead"></i>最后发表</a>&nbsp;
              <a href="${path}/${section.shorthand}/1_${themeId}_2.html" class=""><i class="verythead"></i>精华</a>&nbsp;
             </div> 
            </div> </th> 
          </tr> 
         </tbody>
        </table> 
       </div> 
       <div class="cl qing_simplelist"> 
        <div id="forumnew" style="display:none"></div> 
        <form method="post"  name="moderate" id="moderate" action=""> 
         <table summary="forum_2" cellspacing="0" cellpadding="0" id="threadlisttableid"> 
          <tbody > 
          <c:forEach items="${topList}" var="top">
	           <tr> 
	            <td class="icn"> <a href="" class="avatar"><img src="${path}/file/${top.postUserImage}" /></a> </td>
	            <th class="common"> 
	             <div class="q_f_title1"> 
	              <div class="z qing_fenlei"> 
	              </div> 
	              <a href="${path}/post/${top.id}_1_1.html"  class="s xst">${top.name}</a>
	              <c:if test="${not empty top.excellent}">
	               <img src="${path}/css/img/icon/j${top.excellent}.png" alt="digest" title="精华 ${top.excellent}" />
              	  </c:if>
	             </div> <p></p> 
	             <div class="q_f_title2"> 
	              <div class="z"> 
	               <a href="${path}/member/${top.postUserId}_1.html" style="color: #CC3333;">admin</a> 发表于
	               <span> ${top.createtime}</span>
	               <span style="margin-left:10px;">
	               <c:if test="${top.top==2}">
	               	全局置顶
	               </c:if>
	               <c:if test="${top.top==1}">
	               	板块置顶
	               </c:if>
	               </span> 
	              </div> 
	             </div></th> 
	            <td class="num"><a>${top.view}</a><em>查看</em></td>
	            <td class="num"><a href="" class="xi2">${top.replyCount}</a><em>回复</em></td>
	            <td class="by"> 
	             <div class="byleft"> 
	              <cite><a href="${path}/member/${top.replyUserId}_1.html">${top.replyUserName}</a> <em>${top.replyCreateTime}</em></cite>
	             </div> </td> 
	           </tr> 
          
          </c:forEach>
          </tbody> 
          <tbody id="separatorline"> 
           <tr class="ts"> 
            <th colspan="5">
             <div class="separatorlinediv cl">
              <a href="javascript:;" title="查看更新" class="forumrefresh">版块主题</a>
             </div></th> 
           </tr> 
          </tbody> 
          <tbody id="normalthread_14"> 
          <c:forEach items="${page.list}" var="post">
           <tr> 
            <td class="icn"> <a href="${path}/member/${post.postUserId}_1.html" class="avatar"><img src="${path}/file/${post.postUserImage}" /></a> </td>
            <th class="common"> 
             <div class="q_f_title1"> 
              <div class="z qing_fenlei">
               <em>[<a href="${path}/post/${post.id}_1_1.html">${post.themeName}</a>]</em>
              </div> 
              <a href="${path}/post/${post.id}_1_1.html"  class="s xst">${post.name}</a>
              <c:if test="${not empty post.excellent}">
	               <img src="${path}/css/img/icon/j${post.excellent}.png" alt="digest" title="精华 ${post.excellent}" /> 
              </c:if>
             </div> <p></p> 
             <div class="q_f_title2"> 
              <div class="z"> 
               <a href="${path}/member/${post.postUserId}_1.html">${post.postUserName}</a> 发表于
               <span> ${post.createtime}</span> 
              </div> 
             </div></th> 
            <td class="num"><a>${post.view}</a><em>查看</em></td> 
            <td class="num"><a href="" class="xi2">${post.replyCount}</a><em>回复</em></td>
            <td class="by"> 
             <div class="byleft"> 
              <cite><a href="${path}/member/${post.replyUserId}_1.html">${post.replyUserName}</a> <em>${post.replyCreateTime}</em></cite>
             </div> </td> 
           </tr> 
           </c:forEach>
          </tbody> 
         </table>
        </form> 
       </div> 
      </div> 
      <div class="bm bw0 pgs cl"> 
        <div class="pg">
       <span>当前第${page.pagestart}页/总${page.totalPages}页</span>
        <c:if test="${page.pagestart!=1}">
        <a href="${path}/${section.shorthand}/1_${themeId}_${orderby}.html" >首页</a>
        <a href="${path}/${section.shorthand}/${page.pagestart-1}_${themeId}_${orderby}.html" >上一页</a>
        </c:if>
	         <strong>${page.pagestart}</strong>
         <c:if test="${page.pagestart!=page.totalPages}">
         <a href="${path}/${section.shorthand}/${page.pagestart+1}_${themeId}_${orderby}.html" >下一页</a>
        <a href="${path}/${section.shorthand}/${page.totalPages}_${themeId}_${orderby}.html" >末页</a>
         </c:if>
        </div>
      </div>
     </div> 
    </div> 
   </div> 
  </div> 
  <div class="qing_lk cl"> 
   <div id="category_lk" class="wp ptm"> 
    <ul class="x cl"> 
    <c:forEach items="${linklist}" var="link">
     <li><a href="${link.link}" target="_blank" title="${link.name}">${link.name}</a></li>
    </c:forEach>
    </ul> 
   </div> 
  </div> 
<%@ include file="/WEB-INF/inc/foot.jsp"%>

 </body>
</html>