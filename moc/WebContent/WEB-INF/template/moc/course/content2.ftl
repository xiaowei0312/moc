<!doctype html>
<html><!-- InstanceBegin template="/Templates/dwt.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<!-- InstanceBeginEditable name="doctitle" -->
<title>谋刻职业教育在线测评与学习平台</title>
<link href="${base}/resources/moc/css/course.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/moc/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/moc/css/register-login.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/moc/css/tab.css" media="screen" rel="stylesheet" type="text/css"/>
<script src="${base}/resources/moc/js/jquery-1.8.0.min.js" type="text/javascript" ></script>
<script type="text/javascript" src="${base}/resources/moc/js/jquery.validate.js"></script>
<script src="${base}/resources/moc/editor/kindeditor.js" type="text/javascript" ></script>
<script src="${base}/resources/moc/js/jquery.pagination.js" type="text/javascript" ></script>
<script src="${base}/resources/moc/js/jquery.tabs.js" type="text/javascript" ></script>
<script src="${base}/resources/moc/js/mine.js" type="text/javascript" ></script>
<script src="${base}/resources/moc/js/common.js" type="text/javascript" ></script>
<script type="text/javascript" src="${base}/resources/moc/js/jsbn.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/prng4.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/rng.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/rsa.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/base64.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/loginregisterdialog.js"></script>
<script type="text/javascript">
$(function(){
	var $loginForm = $("#loginForm");
	var $captchaImage =$("#captchaImage");
	var $password=$("#password");
	var $captcha=$("#captcha");
	var $isRememberUsername=$("#isRememberUsername");
	var $loginBtn=$("#loginBtn");
	
	
	pageSize=10;
	username = getCookie("username");
	$askQuestion = $("#askQuestion");
	$questionBody = $("#questionBody");
	
	//保存评价
	$("#sendReview").click(function(){
		var oStar = document.getElementById("star");
		var oSpan = oStar.getElementsByTagName("span")[1];
		var review =oSpan.innerHTML;
		if(review==""){
			$.message("error","请先打分");
			return false;
		}
		 //验证登录
	    var b=volidLogin();
	      if(!b){
	         return false;
	      }
		ajaxSaveReview();
	});
	
	//问答提问
	$questionBody.children("a:first").click(function(){
		var title = $askQuestion.attr("value");
		var content = $("#textQuestion").attr("value");
		if(title=="请输入问题标题"){
			$.message("error","请输入标题");
			return false;
		}
		 //验证登录
	      var b=volidLogin();
	      if(!b){
	         return false;
	      }
		ajaxSaveThread();
	});
	
	//问答主体隐藏
	$questionBody.children("a:last").click(function(){
		$questionBody.fadeOut();
	});
	$askQuestion.focus(function(){
		$questionBody.fadeIn();
		questionEditor =KindEditor.create('#textQuestion', {
						width:"100%",
						pasteType : 1,
						items:["bold", "italic","forecolor","underline","|","insertorderedlist","insertunorderedlist"
						,"|","link","unlink","|","removeformat","source"],
						afterBlur: function(){this.sync();}
					});
	});
	$('.demo2').Tabs({
		event:'click',
		callback:function(){
			var index=$(".current").index();
			
			if(index==0 || index==3 || $("#pageN_"+index).attr("value")!=1){
				return false;
			}
			dataCount(index);
			
			if(index==1){
				reviewEditor =	KindEditor.create('#textReview', {
					width:"100%",
					pasteType : 1,
					items:["bold", "italic","forecolor","underline","|","insertorderedlist","insertunorderedlist"
					,"|","link","unlink","|","removeformat","source"],
					afterBlur: function(){this.sync();}
				});
			}
		}
	});
});
function loginSuccess(){
	$("#headerUsername").html($("#username").val()).attr("style","display:block");
	$("#headerLogout").attr("style","display:block");
	$("#headerLogin,#headerRegister").attr("style","display:none");
	
	var index=$(".current").index();
	if(index==1){
		
		//当前是评价
		ajaxSaveReview();	
	}else if(index==2){
		
		//当前是问答
		ajaxSaveThread();
	}
}
function dataCount(index){
	var count=0;
	$.ajax({
			type : "POST",
			url : "${base}/course/getCount.jhtml",
			data : {"index":index,"courseId":${course.id}},
			success : function(msg) {
				$("#pagination"+index).pagination(msg, {
					ellipse_text : "...",// 	省略的页数用什么文字表示
					num_edge_entries : 0,//两侧显示的首尾分页的条目数
					num_display_entries : 5,//连续分页主体部分显示的分页条目数
					items_per_page:10,// 	每页显示的条目数
					callback:pageselectCallback
				});
				
				//获取数据信息
				function pageselectCallback(page_id, jq) {
					page_id=page_id+1;
					$.ajax( {
						type : "POST",
						url : "${base}/course/getInfo.jhtml",
						data : {"index":index,"pageSize":10,"pageNumber":page_id,"courseId":${course.id}},
						success : function(msg) {
							$("#manger"+index).html(msg);
							$("#pageN_"+index).attr("value",page_id);
						}
					});
				}
		}
	});
}

//保存问答
function ajaxSaveThread(){
	var content = $("#textQuestion").attr("value");
	var title = $askQuestion.attr("value");
	$.ajax({
			url: "${base}/member/course/saveThread.jhtml",
			type: "POST",[#--问答必须有类别id--]
			data: {"courseCategory.id":${course.courseCategory.id},"title":title,"content":content,"course.id":${course.id}},
			dataType: "json",
			cache: false,
			success: function(data) {
				$.message("success","提问成功");
				var headImage=data.headImage=="null"?"${base}/resources/moc/images/0-0.JPG":data.headImage;
				var $li =$("#questionList").children("li");
				$("#questionList").prepend('<li>'
						+'<span class="pephead"><img src='
						+headImage
						+' width="50" title="无名">'
						+'<p class="pepname">'+username+'</p> '
						+'</span>'
						+'<span class="pepcont">'
						+'<p><a href="${base}/courseThread/view.jhtml?threadId='+data.id+'" class="peptitle">'+title+'</a></p>'
						+'<p class="peptime pswer"><span class="pepask">回答(<strong><a class="bluelink" href="${base}/courseThread/view.jhtml?threadId='+data.id+'">0</a></strong>)&nbsp;&nbsp;&nbsp;&nbsp;浏览(<strong><a class="bluelink" href="${base}/courseThread/view.jhtml?threadId='+data.id+'">0</a></strong>)'
						+'</span>${.now?string("yyyy-MM-dd")}</p>'
						+'</span></li>');
				if(($li.size()+1)>pageSize){
					$li.last().remove();
				}
				$askQuestion.attr("value","");		
				questionEditor.html("");
				$questionBody.fadeOut();
			}
		});
}

//保存评价
function ajaxSaveReview(){
		var oStar = document.getElementById("star");
		var oSpan = oStar.getElementsByTagName("span")[1];
		var review =oSpan.innerHTML;
		var content=$("#textReview").attr("value");
		var point = oStar.getElementsByTagName("strong")[0].innerHTML;
		$.ajax({
				url: "${base}/member/course/saveReview.jhtml",
				type: "POST",
				data: {"title":review,"rating":parseInt(point),"content":content,"course.id":${course.id}},
				dataType: "json",
				cache: false,
				success: function(data) {
					$.message("success","评论成功");
					var headImage=data.headImage=="null"?"${base}/resources/moc/images/0-0.JPG":data.headImage;
					
					var $li=$("#reviewList").children("li");
					$("#reviewList").prepend('<li>'
							+'<span class="pephead"><img src='
							+headImage
							+' width="50" title="'+username+'">'
							+'<p class="pepname">'+username+'</p> '
							+'</span>'
							+'<span class="pepcont">'
							+'<p class="pep_pj pep_pj1">'
							+'<img width="100" height="18" src="${base}/resources/moc/images/evaluate'+parseInt(point)+'.png" data-bd-imgshare-binded="1">'
							+review+'</p>'
							+'<p class="pep_pj1">'+content+'</p>'
							+'<p class="peptime pswer pep_pj1">${.now?string("yyyy-MM-dd")}</p>'
							+'</span></li>');
					if($li.size()>pageSize){
						$li.last().remove();
					}		
					//判断当页数据是否小于页码大小
					reviewEditor.html("");
				}
			});
}
</script>

[#--锚点被隐藏了--]
<script type="text/javascript">
    
 $(document).ready(function(){
             var currentURL=String(window.location);
             if(currentURL.lastIndexOf("#") != (-1)){
             	var courseChapterId=currentURL.substring((currentURL.lastIndexOf("#")+1));
             	var $course_chapter_id=$("#"+courseChapterId);
             	[#--章下面的课时没有current_mulu_con--]
         		$(".noo").children("div:first").find('span.mulu_zd').html("+");
	             $("div.current_mulu_con").removeClass('current_mulu_con').parent('div');
	             
	             if($course_chapter_id.is("dd")){
	             	//节
	             	$course_chapter_id.children("strong").addClass("currentChapter");
	             }else{
	             	$course_chapter_id.parent().addClass("currentChapter");
	             }
	             
	             $("#"+courseChapterId).closest('div.mulu_con').addClass('current_mulu_con').parent('div').find('span.mulu_zd').html("-");;
             }
        });  

</script>
<style type="text/css">
	.current_mulu_con{
		display:block;
	}
	.currentChapter{
		color:#fb5e55;
	}
</style>
<script>
	function onAfterClickShare(cmd){	
		var b=true;
	        $.ajax( {
	            type : "GET",
	            url :"${base}/course/aleradyLogin.jhtml",
	            async:false,
	            cache:false,
	            dataType:"json",
	            success : function(message) {
                 	b=message.aleradyLogin=='fail'?false:true;
	            }
	        });
        
             if(!b){
                return confirm("登录后分享有积分\r\n是否继续分享？");
            }
       		var $this = $(this);
			if(cmd!="more" && b){
				$.ajax({
					url:"${base}/course/shareCourse/"+cmd+".jhtml",
					data:{"shareType":cmd},
					dataType:"json",
					type:"GET",
					cache:false,
					async:false,
					success:function(){
						$.message("success","分享成功");
					},
					error:function(){
						$.message("error","分享失败");
					}
				});
			
			}     
	}
</script>
</head>
<body>
<input type="hidden" id="pageN_1" value="1"/>
<input type="hidden" id="pageN_2" value="1"/>
[#include "/moc/include/header.ftl" /]
<!-- InstanceBeginEditable name="EditRegion1" -->
<div class="coursecont">
<div class="coursepic1">
   <div class="coursetitle1">
    	<h2 class="courseh21">${course.title}</h2>
    	<div  style="margin-top:-40px;margin-right:25px;float:right;">
			<div class="bdsharebuttonbox">	
					<a title="分享到QQ空间" href="#" class="bds_qzone" data-cmd="qzone"></a>
					<a title="分享到新浪微博" href="#" class="bds_tsina" data-cmd="tsina"></a>
					<a title="分享到腾讯微博" href="#" class="bds_tqq" data-cmd="tqq"></a>
					<a title="分享到QQ好友" href="#" class="bds_sqq" data-cmd="sqq"></a>
					<a title="分享到微信" href="#" class="bds_weixin" data-cmd="weixin"></a>
					<a href="#" class="bds_more" data-cmd="more"></a>
			</div>
			<script>
			window._bd_share_config={"common":{
											"onAfterClick":function(cmd){
												onAfterClickShare(cmd);
											},
											"bdSnsKey":{},
											"bdText":"",
											"bdMini":"2",
											"bdMiniList":false,
											"bdPic":"",
											"bdStyle":"0",
											"bdSize":"24"
										},"share":{},"image":{"viewList":["qzone","tsina","tqq","sqq","weixin"],"viewText":"分享到：","viewSize":"16"}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
			</script>
		</div>
   </div>
   <div class="course_img1">
	   <img height="140"
	     src=
	       	[#if course.sourceImage?? && course.sourceImage!=""]
                "${course.sourceImage}"
            [#else]
				"${base}/resources/moc/images/c1.jpg"
			[/#if] 
	   />	   
   </div>
   <div class="course_xq">
       <span class="courstime1"><p>课时<br/><span class="coursxq_num">${course.lessonNum}课时</span></p></span>
       <span class="courstime1">
       		[#if course.showStudentNumType="opened"]
	   			<p>学习人数<br/><span class="coursxq_num">${course.studentNum}人</span></p>
	   		[#else]
	   			<p>&nbsp;&nbsp;<br/><span class="coursxq_num">&nbsp;&nbsp;</span></p>
	   		[/#if]
	   </span>
	   <span class="courstime1"><p style="border:none;">课程时长<br/><span class="coursxq_num">
	   		[#if (lessonLength/60/60) gte 1]
	   			${(lessonLength/60/60)?string('0')}小时
	   		[/#if]
	   		${(lessonLength/60%60)?string('0')}分</span></p></span>
   </div>
   <div class="course_xq2">
      <a class="course_learn" href="${base}/member/course/toLearn.jhtml?courseId=${course.id}">开始学习</a>
   </div> 
    <div class="clearh"></div>
</div>

<div class="clearh"></div>
<div class="coursetext">
	<div class="box demo2" style="position:relative">
			<ul class="tab_menu">
				<li class="current course1">章节</li>
				<li class="course1">评价</li>
				<li class="course1">问答</li>
                <li class="course1">资料区</li>
			</ul>
			<div class="tab_box">[#--目录、问答、评价、资料（主体）div--]
			<div>[#-- 目录div--]
				<dl class="mulu noo">
					[#assign x = 0][#--章--]
		    		[#assign y = 0][#--节--]
		    		[#assign lessonIndex=0]
		    		[#assign chapterDiv =0][#--表示是否少</div>--]
		    		[#assign lessonDiv =0][#--判断节是否缺少div--]
		    		[#assign sameChapter=0]
		    		[#list courseChapters as courseChapter]
	    			[#if sameChapter!=courseChapter.id]
		    			[#assign sameChapter=courseChapter.id]	
		    			[#if courseChapter.type="chapter"]
			    			[#assign x=x+1]
			    			[#assign y=0]
			    			
			    			[#if lessonDiv=1]
			    			[#assign lessonDiv =0]
			    				</div>
			    			[/#if]
			    			
			    			[#if chapterDiv=1]
			    				[#assign chapterDiv=0]
			    				</div>
			    			[/#if]
			    			<div>[#-- 包含单个章节的div--]
				    			<dt class="mulu_title">
									<span class="mulu_img" id="course_chapter_${courseChapter.id}"></span>第${x}章&nbsp;&nbsp;${courseChapter.title}
									<span class="mulu_zd">[#if x=1]-[#else]+[/#if]</span>
								</dt>
								
								[#--章下面的课时--]
							 <div class="mulu_con [#if x==1]current_mulu_con[/#if]" >
								[#list courseChapter.courseLessons as lesson]
								[#assign lessonIndex=lessonIndex+1]
									[#if lesson.status=="unpublished"]
										<a href="javascript:void(0);" title="未发布">
											<dd class="mulu_01">
												<strong class="cataloglink gray">课时${lessonIndex}：${lesson.title}</strong>
												<span class="coursetime">未发布</span>
												<i class="courseplay"></i>
											</dd>
										</a>
									[#elseif lesson.uploadFiles??]
										<a href="${base}/member/course/toLearn.jhtml?lessonId=${lesson.id}&courseId=${course.id}"
											+"&mediaId=${lesson.uploadFiles.id}">
											<dd class="mulu_01">
												<strong class="cataloglink">课时${lessonIndex}:${lesson.title}</strong>
		                                		<span class="coursetime">${((lesson.length)/60)?floor?string("00")}:${((lesson.length)%60)?string('00')}</span>
		                               			<i class="courseplay"></i>
		                               		</dd>
										</a>
									[#else]
										<a href="${base}/member/course/toLearn.jhtml?lessonId=${lesson.id}&courseId=${course.id}"
											+"&courseId=${course.id}">
											<dd class="mulu_01">
												<strong class="cataloglink">课时${lessonIndex}：${lesson.title}</strong>
		                                		<span class="coursetime">${lesson.type}</span>
		                               			<i class="courseplay"></i>
		                               		</dd>
										</a>
									[/#if]
								[/#list]
							[#--章下面的课时结束--]
							</div>
							[#assign chapterDiv=1]
			    		[#else]
				    			[#if y=0][#--第一节--]
				    				<div class="mulu_con [#if x==1]current_mulu_con[/#if]">
				    				[#assign lessonDiv=1]
				    			[/#if]
			    				
			    				[#assign y=y+1]
								<dd class="smalltitle" name="course_chapter_${courseChapter.id}" id="course_chapter_${courseChapter.id}"><strong>第${y}节&nbsp;&nbsp;${courseChapter.title}</strong></dd>
								[#list courseChapter.courseLessons as lesson]
								[#assign lessonIndex=lessonIndex+1]
									[#if lesson.status=="unpublished"]
										<a href="javascript:void(0);" title="未发布">
											<dd class="mulu_01">
												<strong class="cataloglink  gray">课时${lessonIndex}：${lesson.title}</strong>
												<span class="coursetime">未发布</span>
												<i class="courseplay"></i>
											</dd>
										</a>
									[#elseif lesson.uploadFiles??]
										<a href="${base}/member/course/toLearn.jhtml?lessonId=${lesson.id}&courseId=${course.id}"
											+"&mediaId=${lesson.uploadFiles.id}">
											<dd class="mulu_01">
												<strong class="cataloglink">课时${lessonIndex}:${lesson.title}</strong>
		                                		<span class="coursetime">${((lesson.length)/60)?floor?string("00")}:${((lesson.length)%60)?string('00')}</span>
		                               			<i class="courseplay"></i>
		                               		</dd>
										</a>
									[#else]
										<a href="${base}/member/course/toLearn.jhtml?lessonId=${lesson.id}&courseId=${course.id}"
											+"&courseId=${course.id}">
											<dd class="mulu_01">
												<strong class="cataloglink">课时${lessonIndex}：${lesson.title}</strong>
		                                		<span class="coursetime">${lesson.type}</span>
		                               			<i class="courseplay"></i>
		                               		</dd>
										</a>
									[/#if]
								[/#list]
		    			[/#if]
		    		[/#if]
		    		[/#list]
		    [#if courseChapters?? && courseChapters?size>0]
			</div>[#--补上包含最后一个章节的div--]
			[/#if]
	      </dl>                   
	</div>
	<div class="hide">
		<div>[#--评价div--]
			<div id="star">
                <span class="startitle">请打分</span>
                <ul>
                    <li><a href="javascript:;">1</a></li>
                    <li><a href="javascript:;">2</a></li>
                    <li><a href="javascript:;">3</a></li>
                    <li><a href="javascript:;">4</a></li>
                    <li><a href="javascript:;">5</a></li>
                </ul>
                <span></span>
                <p></p>
	        </div>
             <div class="c_eform">                      
                <textarea id="textReview"></textarea>
               <a id="sendReview" href="javascript:void(0)"  class="fombtn">发布评论</a>
               <div class="clearh"></div>
             </div>
			<div id='manger1'>
			</div>
		 	<div id="pagination1"  class="pagination" style="clear:none;margin-top:10px;font-size: 15px;line-height: 20px; margin-left: 20px;"  >
		 	</div>
		</div>[#--评价div结束标签--]
	</div>
    <div class="hide">
    		<div>[#-- 问题div--]
                 		<h3 class="pingjia">提问题</h3>
                    	<div class="c_eform">
                        	<input id="askQuestion" type="text" class="pingjia_con" value="请输入问题标题" onblur="if (this.value =='') this.value='请输入问题标题';this.className='pingjia_con'" onclick="if (this.value=='请输入问题标题') this.value='';this.className='pingjia_con_on'"/><br/>
	                       <div id="questionBody" class="hide">
								<textarea id="textQuestion" class=""></textarea>	              
							   <a href="javascript:void(0)" class="fombtn">发布</a>
		                       <a href="javascript:void(0)" class="fombtn">取消</a>
	                       </div>
	                  </div>
                       <div class="clearh"></div>
                       <div id='manger2'>
						</div>
						 <div id="pagination2"  class="pagination" style="clear:none;margin-top:10px;font-size: 15px;line-height: 20px; margin-left: 20px;"  >
						</div>
				</div>[#-- 问题div结束标签--]
	</div>
				
	<div class="hide">
		<div>[#--资料div--]
			<div>
				<ul class="notelist" >
				[#assign hasMateria=false]
				[#list courseMaterial as material]
					[#assign hasMateria=true]
				      <li>
						   	<p class="mbm mem_not">
						   		<a href="${setting.cloudServerSite}/file/download/${material.fileUri}.${material.fileMime}/${material.uploadFiles.id}.jhtml" target="_blank" class="peptitle">${material.title}</a>
						   	</p>
					   		<p class="gray">
					   		[#if material.courseLesson??]
					   		    <b class="coclass">课时:
					   		    	<a href="${base}/member/course/toLearn.jhtml?lessonId=${material.courseLesson.id}&mediaId=${material.uploadFiles.id}&courseId=${course.id}
					   		    	" target="_blank">
				   		    		${material.courseLesson.title}
					   		    	</a>
					   		   	</b>
					   		 [/#if]
					   		   	<b class="cotime">上传时间：
					   		   		<b class="coclass" >${material.createDate}</b>
					   		   	</b>
					        </p>
				      </li>     
				[/#list]
				[#if !hasMateria]
					暂无
				[/#if]
			    </ul>
			</div>
		</div>[#--资料div结束标签--]
	</div>				
</div>[#--目录、问答、评价、资料box div主体结束标签--]
</div>[#--目录、问答、评价、资料box div bar栏结束标签--]
</div>[#-- 正中部分显示div结束标签--]

<div class="courightext">
	<div class="ctext">
	    <div class="cr1">
	    	<h3 class="righttit">授课讲师</h3>
	    	[#list teachers as teacher]
		    	<div class="teacher">
				    <div class="teapic ppi">
				    	<a href="${base}${teacher.path}" target="_blank">
				    	<img  width="80" class="teapicy" title="${teacher.truename}"
				    	src=
				    	[#if teacher.image ?? && teacher.image!=""]
		                     "${teacher.image}"
		                 [#else]
							"${base}/resources/admin/images/teacher.png"
						 [/#if]
				    	/>
				    	</a>
				    	<h3 class="tname">
					    	<a href="${base}${teacher.path}" class="peptitle" target="_blank">
					    		${teacher.truename}
					    	</a>
					    	<p style="font-size:14px;color:#666">${teacher.signature}</p>
				    	</h3>
				    </div>
				    <div class="clearh"></div>
			    	<p>${abbreviate(teacher.about, 120, "...")}</p>
			    </div>
	    	[/#list]
	    </div>
	</div>

	[#if relevantMember?? && relevantMember?size>0]
		<div class="ctext">
		    <div class="cr1">
		    <h3 class="righttit">最新学员</h3>
		        <div class="teacher zxxy">
		        <ul class="stuul">
		        	[#list relevantMember as member]
		        		<li>
		        			<img src="
		        				[#if member.headImage?? && member.headImage!=""]
		        				${member.headImage} 
		        				[#else]
		        					${base}/resources/moc/images/0-0.JPG
		        				[/#if]"
		        				width="60" title="${member.username}">
		        			<p class="stuname">${member.username}</p>
		        		</li>
		        	[/#list]
		        </ul>
		        <div class="clearh"></div>
		        </div>
		    </div>
		</div>
	[/#if]
	<div class="ctext">
	    <div class="cr1">
	    	<h3 class="righttit">相关课程</h3>
	    	<div class="teacher">
	    			[#list relevantCourse as course]
					    <div class="teapic">
					        <a href="${base}${course.path}"  target="_blank">
					        	<img src=[#if course.sourceImage?? && course.sourceImage!="" ]
						        			"${course.sourceImage}" 
						        		[#else]
						        			"${base}/resources/moc/images/c1.jpg"
						        		[/#if] height="60" title="${course.title}"></a>
					        <h3 class="courh3"><a href="${base}${course.path}" class="peptitle" target="_blank">${course.title}</a></h3>
					    </div>
					    <div class="clearh"></div>
					[/#list]
	    	</div>
	    </div>
	</div>
</div>


<div class="clearh"></div>
</div>
<!-- InstanceEndEditable -->
[#include "/moc/include/loginRegisterDialog.ftl" /]
[#include "/moc/include/footer.ftl" /]
</body>

<!-- InstanceEnd --></html>
