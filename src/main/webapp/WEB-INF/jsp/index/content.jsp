<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${map.post.name}-${applicationScope.bbsinfo.name}</title>
<meta name="keywords" content="${map.post.name}" />
<meta name="description" content="${map.post.contenttxt}" />
<link rel="stylesheet" type="text/css" href="${path}/css/css/style_14_common.css" />
<link rel="stylesheet" type="text/css" href="${path}/public/css/tubiao.css" />
<link rel="stylesheet" type="text/css" href="${path}/css/css/style_14_forum_viewthread.css" />
<script type="text/javascript" src="${path}/public/js/jquery-1.8.2.min.js" ></script>
<script type="text/javascript" src="${path}/public/js/javaee.js" ></script>
 <script type="text/javascript" charset="utf-8" src="${path}/public/baiduueditor/ueditor.config.huifu.js"></script>
 <script type="text/javascript" charset="utf-8" src="${path}/public/baiduueditor/ueditor.all.js"></script>
 <script type="text/javascript" src="${path}/public/baiduueditor/lang/zh-cn/zh-cn.js"></script>
 <script type="text/javascript" src="${path}/public/layer/layer.js"></script>
 <script type="text/javascript" src="${path}/public/ckplayer/ckplayer/ckplayer.js"></script>
 <script type="text/javascript" src="${path}/public/ckplayer/ckplayer/baiduckplayer.js"></script>
<script type="text/javascript">
$(function(){
	  UE.getEditor('myEditor');
	  window.setTimeout(updatefindcount, 2000);
 });

function geteditorhtml(){
	 var editorhtml=UE.getEditor('myEditor').getContent();
	 var editortxt=UE.getEditor('myEditor').getContentTxt();
	 if(editorhtml==""){
		 alert("请输入内容");
		 UE.getEditor('myEditor').focus();
		 return;
	 }
	 var param={};
	 param.editorhtml=editorhtml;
	 param.editortxt=editortxt;
	 param.id=${map.post.id};
	 $.ajax({
		   type: "POST",
		   url: "${path}/post/addReply.do",
		   data: param,
		   dataType: "json",
		   async: false,
		   success: function(data){
			   if(data==1){
				   window.location.href="${path}/post/${map.post.id}_1_1.html";
			   }else if(data==0){
				   alert("该板块不允许发表回复");
			   }else{
				   alert("系统错误");
			   }
		   }
	 });
}

function gotologin(){
	window.location.href="${path}/login/index.do?pathlocation=${path}/post/${map.post.id}_1_1.html";
}


function updatefindcount(){
	var param={};
	 param.id=${map.post.id};
	 $.ajax({
		   type: "POST",
		   url: "${path}/post/updatefindcount.do",
		   data: param,
		   dataType: "json",
		   async: false,
		   success: function(data){
		   }
	 });
}



function tzczshoworhide(id){
	getClickPosition();
    $("#"+id).css("left",clickX);
    $("#"+id).css("top",clickY+15);
	$("#"+id).slideToggle('fast');
}
//设置精华
function best(postid,excellent){
	var param={};
	 param.id=postid;
	 param.excellent=excellent;
	 $.ajax({
		   type: "POST",
		   url: "${path}/moderator/best.do",
		   data: param,
		   dataType: "json",
		   async: false,
		   success: function(data){
			   window.location.reload();
		   }
	 });
}
//取消精华
function disBest(postid){
	var param={};
	 param.id=postid;
	 $.ajax({
		   type: "POST",
		   url: "${path}/moderator/disbest.do",
		   data: param,
		   dataType: "json",
		   async: false,
		   success: function(data){
			   window.location.reload();
		   }
	 });
}

//全局置顶
function Gtop(postid){
	var param={};
	 param.id=postid;
	 $.ajax({
		   type: "POST",
		   url: "${path}/admin/common/Gtop.do",
		   data: param,
		   dataType: "json",
		   async: false,
		   success: function(data){
			   window.location.reload();
		   }
	 });
}
//取消置顶
function disGtop(postid){
	var param={};
	 param.id=postid;
	 $.ajax({
		   type: "POST",
		   url: "${path}/admin/common/disGtop.do",
		   data: param,
		   dataType: "json",
		   async: false,
		   success: function(data){
			   window.location.reload();
		   }
	 });
}

function btop(postid){
	var param={};
	 param.id=postid;
	 $.ajax({
		   type: "POST",
		   url: "${path}/moderator/top.do",
		   data: param,
		   dataType: "json",
		   async: false,
		   success: function(data){
			   window.location.reload();
		   }
	 });
}
function disTop(postid){
	var param={};
	 param.id=postid;
	 $.ajax({
		   type: "POST",
		   url: "${path}/moderator/distop.do",
		   data: param,
		   dataType: "json",
		   async: false,
		   success: function(data){
			   window.location.reload();
		   }
	 });
}

function deletePost(postId){
	if(confirm("确认删除此帖子吗?删除此贴将导致此贴所有回复消失")){
		var param={};
		 param.id=postId;
		 $.ajax({
			   type: "POST",
			   url: "${path}/post/del.do",
			   data: param,
			   dataType: "json",
			   async: false,
			   success: function(data){
				   window.location.href='${path}/${map.post.subShorthand}/1_0_0.html';
			   }
		 });
	}
}
function updatePost(postId){
		window.location.href='${path}/post/toupdate.do?postId='+postId;
}
function delReply(replyId){
	if(confirm("确认删除此回复吗?")){
		var param={};
		 param.id=replyId;
		 $.ajax({
			   type: "POST",
			   url: "${path}/reply/delReply.do",
			   data: param,
			   dataType: "json",
			   success: function(data){
			       if(data.flag != 0){
                       window.location.reload();
			       }else {
			           window.alert("删除失败");
                   }

			   }
		 });
	}
}

function addReply(replyid){
	var id='${map.post.id}';
	layer.open({
		  type: 2,
		  title: '发表回复',
		  shadeClose: true,
		  shade: 0.8,
		  area: ['903px', '358px'],
		  content: '${path}/post/addReply?id='+ id+'&huifuid='+replyid
		});
}

/*function yidongztree(){
	var id='${map.post.id}';
	layer.open({
		  type: 2,
		  title: '移动板块',
		  shadeClose: true,
		  shade: 0.8,
		  area: ['303px', '358px'],
		  content: '${path}/moderator/common/ztreeyidong.do?id='+id
		});
	//有的浏览器高度设置有问题 所以特此定义一下
	$(".layui-layer-iframe").css('top',100);
}*/
</script>
</head>
 <body id="nv_forum" class="pg_index">

 <%@ include file="/WEB-INF/inc/top.jsp"%>

   <div id="wp" class="wp">
   <div id="diynavtop" class="area"></div>
   <div id="pt" class="bm cl">
    <div class="z">
     <a href="${path}/" class="nvhm" title="首页">首页</a>
     <em>›</em>
     <a href="${path}/${map.post.fatherSectionId}.html">${map.post.fatherSectionName}</a>
     <em>›</em>
     <a href="${path}/${map.post.subShorthand}/">${map.post.subSectionName}</a>
     <em>›</em>
     <a href="${path}/post/${map.post.id}_1_1.html">${map.post.name}</a>
    </div>
    <div class="y">
     <c:if  test="${!empty sessionScope.indexuser}">
     <a href="${path}/post/topost.do?id=${map.post.subSectionId}" id="newspecial" title="发新帖" class="qing_f_post">发新帖</a>
     <a id="post_reply" onclick="" href="#fastposteditor" title="回复" class="qing_f_reply">回复</a>
     </c:if>
    </div>
   </div>
   <div class="wp">
    <div id="diy1" class="area"></div>
   </div>
   <div id="ct" class="wp cl">
    <div id="postlist" class="pl">
     <table cellspacing="0" cellpadding="0" class="ad">
      <tbody>
       <tr>
        <td class="pls"></td>
        <td class="plc"></td>
       </tr>
      </tbody>
     </table>
     <div id="post_240">
      <table id="pid240" summary="pid240" cellspacing="0" cellpadding="0">
       <tbody>
        <tr>
         <td class="pls">
          <div>
           <div class="avatar" >
            <a href="${path}/member/${map.post.userid}_1.html" target="_blank"><img src="${path}/file/${map.post.avatar}" /></a>
           </div>
           <div class="qing_pi">
            <div class="authi">
             <a href="${path}/member/${map.post.userid}_1.html" target="_blank" class="xw1" <c:if test="${map.post.roleId==1}">style="color: #cc0000"</c:if> >${map.post.loginname}</a>
            </div>
           </div>
           <div class="qing_v_card">
            <p><em <c:if test="${map.post.roleId==1}">style="color: #cc0000"</c:if> >  ${map.post.roleName}</em></p>
             <div class="${map.post.classname}"></div>
            <div class="qcard">
             <div class="tns xg2">
              <table cellspacing="0" cellpadding="0">
               <tbody>
                <tr>
                 <th><p><a href="${path}/member/${map.post.userid}_2.html" class="xi2">${map.post.postCount}</a></p>帖子</th>
                 <th><p><a href="" class="xi2">${map.post.userReplyCount}</a></p>回复</th>
                 <td><p><a href="" class="xi2">${map.post.jifen}</a></p>积分</td>
                </tr>
               </tbody>
              </table>
             </div>
            </div>
           <!-- <p><span class="pbg2" ><span class="pbr2" style="width:36%;"></span></span></p>-->
            <div id="g_up240_menu" class="tip tip_4" style="display: none;">
             <div class="tip_horn"></div>
             <div class="tip_c">
              演示组, 积分 372, 距离下一级还需 628 积分
             </div>
            </div>
           </div>
          </div> </td>
         <td id="plc_240" class="plc">
          <div class="mtm pbm bbda">
           <h1 class="ts"> <a href="${path}/post/${map.post.id}_1_1.html">[${map.post.themeName}]</a>
           <a href="${path}/post/${map.post.id}_1_1.html" id="thread_subject">${map.post.name}</a> </h1>
           <span class="xg1"> </span>
          </div>
          <div class="pi">
           <strong class="xg1"> <a href="javascript:;" title="楼主" >楼主</a> </strong>
           <div class="pti">
            <div class="pdbt">
            </div>
            <div class="authi xg1">
             <em id="authorposton240">发表于 ${map.post.createtime}</em>
             <span class="pipe">|</span> 查看: ${map.post.view}
             <span class="pipe">|</span> 回复: ${map.post.replyCount}
            </div>
           </div>
          </div>
          <div class="pct">
           <style type="text/css">.pcb{margin-right:0}</style>
           <div class="pcb">
            <div class="t_fsz">
             <table cellspacing="0" cellpadding="0">
              <tbody>
               <tr>
                <td class="t_f" style="overflow: hidden;">
                 <div align="left" >
                  ${map.post.contenthtml}

                  <div class="attach_nopermission">
<!-- 					<div>
					<h3>本帖子中包含更多资源</h3>
					<p>您需要 <a href="member.php?mod=logging&amp;action=login" onclick="showWindow('login', this.href);return false;">登录</a> 才可以下载或查看，没有帐号？<a href="member.php?mod=register" title="注册帐号">立即注册</a> </p>
					</div>
					</div> -->
                 </div><br />
                 </td>
               </tr>
              </tbody>
             </table>
            </div>
           </div>
          </div>
          <!-- <table>
           <tbody>
            <tr>
             <td>
              <div class="sign" style="max-height:100px;maxHeightIE:100px;">
               <a href="http://www.dzzsk.com/demo/simple/static/image/smiley/default/smile.gif" target="_blank">http://www.dzzsk.com/demo/simple/static/image/smiley/default/smile.gif</a>
              </div> </td>
            </tr>
           </tbody>
          </table>  -->
          <div id="po_240" class="po">
           <div class="pob cl">
				<p>
				<c:if test="${sessionScope.indexuser.roleId==1 or sessionScope.indexuser.roleId==2 or sessionScope.indexuser.id==map.post.userid}">
					<a href="javascript:;" onclick="deletePost(${map.post.id})">删除帖子</a>
					<a href="javascript:;" onclick="updatePost(${map.post.id})">编辑帖子</a>
           		</c:if>
				<c:if test="${sessionScope.indexuser.roleId==1 or sessionScope.indexuser.roleId==2 }">
				<a href="javascript:;"  class="showmenu" onclick="tzczshoworhide('tzcz')" >操作</a>
					<ul id="tzcz" class="p_pop mgcmn" style="position: absolute; z-index: 3010; display: none;" >
					<%--	<li><a href="javascript:;" onclick="yidongztree();"><img src="${path}/static/image/magic/highlight.small.gif"/>移动板块</a></li>--%>
                     <c:if test="${sessionScope.indexuser.roleId==1}">
						<c:if test="${map.post.top!=2}">
							<li><a href="javascript:;" onclick="Gtop('${map.post.id}');"><img src="${path}/static/image/magic/jack.small.gif"/>全局置顶</a></li>
						</c:if>
						<c:if test="${map.post.top==2}">
							<li><a href="javascript:;" onclick="disGtop('${map.post.id}');"><img src="${path}/static/image/magic/jack.small.gif"/>取消全局</a></li>
						</c:if>
					</c:if>
					<c:if test="${map.post.top!=1}">
						<li><a href="javascript:;" onclick="btop('${map.post.id}');"><img src="${path}/static/image/magic/stick.small.gif"/>板块置顶</a></li>
					</c:if>
					<c:if test="${map.post.top==1}">
						<li><a href="javascript:;" onclick="disTop('${map.post.id}');"><img src="${path}/static/image/magic/stick.small.gif"/>取消板块</a></li>
					</c:if>
					<c:if test="${not empty map.post.excellent}">
						<li><a href="javascript:;" onclick="disBest('${map.post.id}');"><img src="${path}/static/image/magic/bump.small.gif" />取消精华</a></li>
					</c:if>
					<c:if test="${!not empty map.post.exce}">
						<li><a href="javascript:;" onclick="best('${map.post.id}','1');"><img src="${path}/static/image/magic/bump.small.gif" />精华1</a></li>
						<li><a href="javascript:;" onclick="best('${map.post.id}','2');"><img src="${path}/static/image/magic/bump.small.gif" />精华2</a></li>
						<li><a href="javascript:;" onclick="best('${map.post.id}','3');"><img src="${path}/static/image/magic/bump.small.gif" />精华3</a></li>
					</c:if>

					</ul>
				</c:if>
				</p>
			</div>
          </div>
          <c:if test="${not empty map.post.lastupdate}">
          <div id="comment_237">
	          	<div class="cmtl xld xlda">
					<dl class="cl">
						<dd>${map.post.lastupdate}</dd>
					</dl>
				</div>
			</div>
		</c:if>
          <div id="comment_240">
           <div id="cmtform_240"></div>
          </div> </td>
        </tr>
        <c:if test="${!empty replymap.list}">
        <tr class="ad">
         <td class="pls"></td>
         <td class="plc">
          <ul class="ttp cl">

           <li <c:if test="${orderby!='0'}">class="xw1 a"</c:if> ><a href="${path}/post/${map.post.id}_1_1.html">正序浏览</a></li>
           <li <c:if test="${orderby=='0'}">class="xw1 a"</c:if> ><a href="${path}/post/${map.post.id}_1_0.html">倒序浏览</a></li>
          </ul> </td>
        </tr>
        </c:if>
       </tbody>
      </table>
     </div>
     <div >
      <table  cellspacing="0" cellpadding="0">
       <tbody>

       <c:forEach items="${replymap.list}" var="replymap">

        <tr>
         <td class="pls">
          <div>
           <div class="avatar" onmouseover="showauthor(this, 'userinfo257')">
             <a href="${path}/member/${replymap.userid}_1.html" target="_blank"><img src="${path}/file/${replymap.avatar}" /></a>
           </div>
           <div class="qing_pi">
            <div class="authi">
             <a href="${path}/member/${replymap.userid}_1.html" target="_blank" class="xw1" style="color: #CC3333">${replymap.loginname}</a>
            </div>
           </div>
           <div class="qing_v_card">
            <p><em><font <c:if test="${replymap.post.roleId==1}">style="color: #CC3333"</c:if>>${replymap.roleName}</font></em></p>
            <div class="${replymap.classname}"></div>
            <div class="qcard">
             <div class="tns xg2">
              <table cellspacing="0" cellpadding="0">
               <tbody>
                <tr>
                 <th><p><a href="${path}/member/${replymap.userid}_2.html" class="xi2">${replymap.postCount}</a></p>帖子</th>
                 <th><p><a href="" class="xi2">${replymap.userReplyCount}</a></p>回复</th>
                 <td><p><a href="" class="xi2">${replymap.jifen}</a></p>积分</td>
                </tr>
               </tbody>
              </table>
             </div>
            </div>
           </div>
          </div> </td>
         <td id="plc_257" class="plc">
          <div class="pi">
           <strong class="xg1">
           <c:if test="${replymap.orderby==1}">
            <a href="javascript:;" title="" >沙发</a>
           </c:if>
           <c:if test="${replymap.orderby==2}">
            <a href="javascript:;" title="" >板凳</a>
           </c:if>
           <c:if test="${replymap.orderby==3}">
            <a href="javascript:;" title="" >地板</a>
           </c:if>
           <c:if test="${replymap.orderby!=1&&replymap.orderby!=2&&replymap.orderby!=3}">
            <a href="javascript:;" title="" ><em>${replymap.orderby}</em><sup>#</sup></a>
           </c:if>
            </strong>
           <div class="pti">
            <div class="pdbt">
            </div>
            <div class="authi xg1">
             <em id="authorposton257">发表于 ${replymap.createtime}</em>
            </div>
           </div>
          </div>
          <div class="pct">
           <div class="pcb">
            <div class="t_fsz">
             <table cellspacing="0" cellpadding="0">
              <tbody>
               <tr>
                <td class="t_f" style="overflow: hidden;" >
                <c:if test="${not empty  replymap.replyId and replymap.isdel eq '0'}">
	                 <div class="quote">
		                 <blockquote>
		                 <font size="2"><font color="#999999">${replymap.ploginname} 发表于 ${replymap.pcreatetime} </font>
		                 <font style="margin-left : 620px;">
		                 <c:if test="${replymap.porderby==1}">沙发</c:if>
				         <c:if test="${replymap.porderby==2}">板凳</c:if>
				         <c:if test="${replymap.porderby==3}">地板</c:if>
				         <c:if test="${replymap.porderby!=1&&replymap.porderby!=2&&replymap.porderby!=3}">
				            <em>${replymap.porderby}</em><sup>#</sup>
				         </c:if>
		                 </font></font>
		                 <br>${replymap.pcontenthtml}</blockquote>
	                 </div>
                </c:if>
                 ${replymap.contenthtml}
                </td>
               </tr>
              </tbody>
             </table>
            </div>
           </div>
          </div>
          <div id="po_257" class="po">
           <div class="pob cl">
            <p>

           <c:if test="${sessionScope.indexuser !=null}">
            	<c:if test="${replymap.isdel eq '0'}">
	            	<a href="javascript:;" onclick="addReply(${replymap.id})">回复</a>
            	</c:if>
           </c:if>
           	<c:if test="${sessionScope.indexuser.roleId==1 or sessionScope.indexuser.roleId==2 or sessionScope.indexuser.id==replymap.userid}">
            	<c:if test="${replymap.isdel eq '0'}">
            		<a href="javascript:;" onclick="delReply(${replymap.id})">删除回复</a>
            	</c:if>
            </c:if>
            </p>
           </div>
          </div>
          <div id="comment_257">
           <div id="cmtform_257"></div>
          </div> </td>
        </tr>
        <tr class="ad">
         <td class="pls"></td>
         <td class="plc"> </td>
        </tr>


         </c:forEach>
       </tbody>
      </table>
     </div>
    </div>
    <div class="pgs mtm mbm cl">
     <div class="pgt"></div>

    <c:if test="${!empty replymap.list}">
     <div class="bm bw0 pgs cl">
        <div class="pg">
        <c:if test="${replymap.pagestart!=1}">
        <a href="${path}/post/${map.post.id}_${replymap.pagestart-1}_${orderby}.html" class="prev"></a>
        </c:if>
         <strong>${replymap.pagestart}</strong>
         <c:if test="${replymap.pagestart!=replymap.totalPages}">
         <a href="${path}/post/${map.post.id}_${replymap.pagestart+1}_${orderby}.html" class="nxt"></a>
         </c:if>
        </div>
         <span class="y pgb" ><a href="${path}/${map.post.subShorthand}/1_0_0.html">返回列表</a></span>
      </div>
    </c:if>
    </div>
    <div id="diyfastposttop" class="area"></div>
    <div id="f_pst" class="pl mbm">
    <!-- 发帖文件框 -->

      <table cellspacing="0" cellpadding="0">
       <tbody>

       <c:if test="${sessionScope.indexuser!=null}">
       <c:if test="${flag==1 }">
       <!-- 允许回复 -->
        <tr>
         <td class="pls">
          <div class="avatar">
           <img src="${path}/file/${sessionScope.indexuser.avatar}" />
          </div> </td>
         <td class="plc"> <span id="fastpostreturn"></span>
          <div class="cl">
           <div id="fastsmiliesdiv" class="y">
            <div id="fastsmiliesdiv_data">
             <div id="fastsmilies"></div>
            </div>
           </div>
           <div class="hasfsl" id="fastposteditor">
            <div class="tedt mtn">
             <div class="tedt">
				<div id="myEditor" style="width:850px;height:200px;"></div>
        	</div>
            </div>
           </div>
          </div>
           <p class="ptm pnpost">
           <button type="button" class="pn pnc vm" onclick="geteditorhtml();" ><strong>发表回复</strong></button>
             </p> </td>
        </tr>
       </c:if>
       <c:if test="${flag==0 }">
       <!-- 不允许回复 -->
       	<tr>
         <td class="pls">
          <div class="avatar">
           <img src="${path}/file/${sessionScope.indexuser.avatar}" />
          </div> </td>
         <td class="plc"> <span id="fastpostreturn"></span>
          <div class="cl">
           <div id="fastsmiliesdiv" class="y">
            <div id="fastsmiliesdiv_data">
             <div id="fastsmilies"></div>
            </div>
           </div>
           <div class="hasfsl" >
            <div class="tedt mtn">
             <div class="area" >
              <textarea rows="6" cols="100" class="pt" disabled="disabled" ></textarea>
             </div>
            </div>
           </div>
          </div>
           <p class="ptm pnpost">
           <label>您没有回帖权限</label>
             </p> </td>
        </tr>
       </c:if>
        </c:if>


         <c:if test="${sessionScope.indexuser==null}">
         <!-- 没有登录 -->
        <tr>

         <td class="pls">
          <div class="avatar">
            <c:choose>
             <c:when test="${sessionScope.indexuser==null}">
              <img src="${path}/file/touxiang.jpg" />
             </c:when>
             <c:otherwise>
              <img src="${path}/file/${sessionScope.indexuser.avatar}" />
             </c:otherwise>

            </c:choose>
          </div>
         </td>
         <td class="plc"> <span id="fastpostreturn"></span>
         <div class="cl">
		<div id="fastsmiliesdiv" class="y">
            <div id="fastsmiliesdiv_data">
                <div id="fastsmilies">

                </div>
            </div>
        </div>
        <div class="hasfsl" id="fastposteditor">
		<div class="tedt mtn">
		<div class="bar">
		</div>
		<div class="area">
			<div class="pt hm">
			您需要登录后才可以回帖 <a href="${path}/login/index.do"  class="xi2">登录</a> | <a href="${path}/regist/toRegist.do" class="xi2">立即注册</a>
			</div>
		</div>
		</div>
		</div>

           <p class="ptm pnpost">
           <button type="button"  onclick="gotologin();" class="pn pnc vm" ><strong>发表回复</strong></button>
           <label>请登录后发表回复</label>
             </p> </td>
        </tr>
        </c:if>


       </tbody>
      </table>
     <!-- 发帖文件框 -->
    </div>
   </div>
  </div>
<%@ include file="/WEB-INF/inc/foot.jsp"%>
 </body>
</html>