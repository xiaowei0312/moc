<!doctype html>
<html><!-- InstanceBegin template="/Templates/dwt.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">
<!-- InstanceBeginEditable name="doctitle" -->
<title>谋刻职业教育在线测评与学习平台</title>
<link rel="stylesheet" href="${base}/resources/moc/css/register-login.css"/>
<link href="${base}/resources/moc/css/course.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/moc/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/moc/css/tab.css" media="screen" rel="stylesheet" type="text/css"/>

<script src="${base}/resources/moc/js/jquery.js" type="text/javascript" ></script>
<script src="${base}/resources/moc/js/jquery.tabs.js" type="text/javascript" ></script>
<script src="${base}/resources/moc/js/mine.js" type="text/javascript" ></script>
<script  type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/base64.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/prng4.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/rng.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/rsa.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/jsbn.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/loginregisterdialog.js"></script>
<script type="text/javascript">

    var collectFlag=false;
	/** 
	*删除数组指定下标或指定对象 
	*/ 
	Array.prototype.indexOf = function(val) {        
	    for (var i = 0; i < this.length; i++) {      
			if (this[i] == val) 
				return i;          
		}          
		return -1;       
	};      
	Array.prototype.remove = function(val) {       
		var index = this.indexOf(val);  
		if (index > -1) {               
			this.splice(index, 1);     
		}        
	}; 
	    
    $().ready(function() {
       var courseId=$("#courseId").val();
       var $coursefavorite=$("#coursefavorite");
       var $cancelfavorite=$("#cancelfavorite");
       
       var courseFavoriteCode=getCookie("courseFavoriteCode");
		
     
       
       
       if(courseFavoriteCode!=null && typeof(courseFavoriteCode) != undefined){
	       var arrays=courseFavoriteCode.split(",");
	       for(i=0;i<arrays.length;i++){
	           if(arrays[i]==courseId){
	               collectFlag=true;
	           }
	       }
       }
       if(collectFlag){
          $coursefavorite.hide();
          $cancelfavorite.show();
       }else{
       	  $coursefavorite.show();
          $cancelfavorite.hide();
       }
 
       $coursefavorite.click(function(){
             //验证登录
	        var b=volidLogin();
	        if(!b){
	            return false;
	        }
            collectCourse();
       });
       
       $cancelfavorite.click(function(){
              //验证登录
	        var b=volidLogin();
	        if(!b){
	            return false;
	        }
	        cancelCourse();
       });
       
    });
    
    function loginSuccess(){
    	   //获得课程Id
           var courseId=$("#courseId").val();
           if(collectFlag){
              cancelCourse();
           }else{
              collectCourse();
           }
    }
    
    function collectCourse(){
         //获得课程Id
         var courseId=$("#courseId").val();
    	 $.ajax({
				url:"${base}/member/coursefavorite/collectCourse.jhtml",
				type:"GET",
				cache:false,
				data:{"courseId":courseId},
				dataType: "json",
				success:function(message){
				   if(message.type=='success' || message.type=='error'){
				       var courseFavoriteCode=getCookie("courseFavoriteCode");
				       if(courseFavoriteCode==null || typeof(courseFavoriteCode) == undefined || courseFavoriteCode==""){
				          courseFavoriteCode="";
				          courseFavoriteCode+=""+courseId+",";
				       }else{
				          courseFavoriteCode+=","+courseId+",";
				       }
				       if(courseFavoriteCode.indexOf(",")!=-1){
				          courseFavoriteCode=courseFavoriteCode.substring(0,courseFavoriteCode.length-1);
				       }
				       addCookie("courseFavoriteCode",courseFavoriteCode, {expires: 7 * 24 * 60 * 60,path:"${base}/"});
				   }
	               $.message(message);
	               setTimeout(function() {
						location.reload(true);
					}, 1000);
				}
		   });
    }
    
    function cancelCourse(){
    	//获得课程Id
        var courseId=$("#courseId").val();
        
        $.ajax({
				url: "${base}/member/coursefavorite/delete.jhtml",
				type: "POST",
				data: {"courseId":courseId},
				dataType: "json",
				cache: false,
				success: function(message) {
				   if(message.type=='success'){
				   		var courseFavoriteCode=getCookie("courseFavoriteCode");
				   		if(courseFavoriteCode!=null){
					   		var arrays=courseFavoriteCode.split(",");
					   		arrays.remove(courseId);
					   		addCookie("courseFavoriteCode",arrays.join(","), {expires: 7 * 24 * 60 * 60,path:"${base}/"});
				   		}
				   }
				   $.message(message);
				   setTimeout(function() {
						location.reload(true);
					}, 500);
				}
		 });
    }
    
</script>
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
[#include "/moc/include/header.ftl" /]
<!-- InstanceBeginEditable name="EditRegion1" -->
<input type="hidden" id="courseId" value="${course.id}"/>
<div class="coursecont">
	<div class="coursepic">
			<div class="course_img"><img  class="course_img"
			  src=
			    [#if course.sourceImage?? && course.sourceImage!=""]
                     "${course.sourceImage}"
                [#else]
					"${base}/resources/moc/images/c1.jpg"
				[/#if]
			></div>
		    <div class="coursetitle">
		    	<a class="state">
		    		[#if course.serializeMode="none"]
		    			非连载课程
		    		[#elseif course.serializeMode="serialize"]
		    			更新中
		    		[#else]
		    			已完结
		    		[/#if]
		    	</a>
		    	<h2 class="courseh2">${course.title}</h2>
		    	[#if course.price?? && course.price gt 0]
		    		<p class="courstime">价格：
		    			<span class="course_tt">${currency(course.price, true, true)}</span>
		    		</p>  
		    	[/#if]  
		        <p class="courstime">总课时：<span class="course_tt">${course.lessonNum}课时</span></p>
				<p class="courstime">课程时长：<span class="course_tt">
					[#if (lessonLength/60/60) gte 1]
						${(lessonLength/60/60)?string('0')}小时
					[/#if]
					${(lessonLength/60%60)?string('0')}分</span></p>
				[#if course.showStudentNumType="opened"]
		        	<p class="courstime">学习人数：<span class="course_tt">${course.studentNum}人</span></p>
		        [/#if]
				<p class="courstime">讲师：[#list teachers as teacher]
		        			[#if teacher_index=(teachers?size-1)]
		        				${teacher.truename}
		        			[#else]
		        				${teacher.truename}、
		        			[/#if]
		        		[/#list]
		        </p>
				<p class="courstime">课程评价：
					[#if reviewPoint[0]?? && reviewPoint[0]!=0]
						<img width="100" height="18" src="${base}/resources/moc/images/evaluate${reviewPoint[1]?string("0")}.png">&nbsp;&nbsp;
						<span class="hidden-sm hidden-xs">${reviewPoint[1]?string("0.0")}分（${reviewPoint[0]}人评价）</span>
					[#else]
						<img width="100" height="18" src="${base}/resources/moc/images/evaluate0.png">&nbsp;&nbsp;
						<span class="hidden-sm hidden-xs">（0人评价）</span>
					[/#if]
				</p>
		        <span class="coursebtn">
		        	<a class="btnlink" href="${base}${course.path2}">
		        	[#if course.price?? && course.price gt 0]
		        		购买课程
		        	[#else]
		        		加入学习
		        	[/#if]
		        	</a>
		        <a class="codol fx" href="javascript:void(0);" onclick="$('#bds').toggle();">分享课程</a>
		        <a class="codol sc" href="javascript:void(0);" id="coursefavorite" style="background-position:1px -5px;">收藏课程</a>
		        <a class="codol sc" href="javascript:void(0);" id="cancelfavorite" style="background-position:0px -1800px;display:none;">取消收藏</a>
		        </span> 
				<div style="clear:both;"></div>
				<div id="bds">
		            <div class="bdsharebuttonbox">	
						<a title="分享到QQ空间" href="javascript:void(0);" class="bds_qzone" data-cmd="qzone"></a>
						<a title="分享到新浪微博" href="javascript:void(0)" class="bds_tsina" data-cmd="tsina"></a>
						<a title="分享到腾讯微博" href="javascript:void(0)" class="bds_tqq" data-cmd="tqq"></a>
						<a title="分享到QQ好友" href="javascript:void(0)" class="bds_sqq" data-cmd="sqq"></a>
						<a title="分享到微信" href="javascript:void(0)" class="bds_weixin" data-cmd="weixin"></a>
						<a href="javascript:void(0)" class="bds_more" data-cmd="more"></a>
					</div>
					<script>
						window._bd_share_config={"common":{
						"onAfterClick":function(cmd){
												onAfterClickShare(cmd);
											},
						"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"24"},"share":{},"image":{"viewList":["qzone","tsina","tqq","sqq","weixin"],"viewText":"分享到：","viewSize":"16"}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
					</script>
				</div>
		    </div>
    		<div class="clearh"></div>
	</div>

<div class="clearh"></div>
<div class="coursetext">
	<div style="position:relative" class="box demo2">
		<ul class="tab_menu">
			<li class="course1 course2 current">课程简介</li>
			<li class="course1 course2">课程目录</li>               
		</ul>
		<div class="tab_box">
			<div class="">
				<p class="coutex">
					[#if course.about?? && course.about!=""]
    					${course.about}
    				[#else]
    					暂无简介
    				[/#if]
				</p>           
			</div>
			<div class="hide">
				<dl class="mulu">
	    		[#assign x=0]
	    		[#assign sameChapter=0]
	    		[#list courseChapters as courseChapter]
	    		[#if sameChapter != courseChapter.id]
	    			[#assign sameChapter=courseChapter.id]
	    			[#if courseChapter.type="chapter"]
	    			[#assign x=x+1]
    					<dt><a class="graylink">第${x}章：${courseChapter.title}</a></dt>
    					<dd>${courseChapter.summary}</dd>
    				[/#if]
    			[/#if]
	    		[/#list]
   				</dl>
			</div>
		</div>
	</div>
</div>
<div class="courightext">
<div class="ctext">
    <div class="cr1">
    <h3 class="righttit">授课讲师</h3>
    	[#list teachers as teacher]
	    	<div class="teacher">
			    <div class="teapic ppi">
			    	<a href="${base}${teacher.path}" target="_blank">
			    	   <img width="80" class="teapicy" title="${teacher.truename}"
			    	         src=
			    	         [#if teacher.image?? && teacher.image!=""]
					        			"${teacher.image}" 
					        		[#else]
					        			"${base }/resources/admin/images/teacher.png"
					        		[/#if]
			    	         />
			    	   </a>
			    	<h3 class="tname">
			    		<a href="${base}${teacher.path}" class="peptitle" target="_blank">${teacher.truename}</a>
			    		<p style="font-size:14px;color:#666">${teacher.signature}</p>
			    	</h3>
			    </div>
			    <div class="clearh"></div>
		    	<p>${abbreviate(teacher.about, 120, "...")}</p>
		    </div>
    	[/#list]
    </div>
</div>

<div class="ctext">
    <div class="cr1">
    	<h3 class="righttit">课程公告</h3>
	    <div class="gonggao">
			<div class="clearh"></div>
			[#if courseAnnouncements?? && courseAnnouncements?size>0]
				[#list courseAnnouncements as courseAnnouncement]
					<p>${courseAnnouncement.content}<br/>
						<span class="gonggao_time"> ${courseAnnouncement.modifyDate?string("yyyy-MM-dd HH:mm")}</span>
					</p>
				[/#list]
			[#else]
				<p style="text-align:center">暂无课程公告</p>
			[/#if]
				<div class="clearh"></div>
	    </div>
    </div>
</div>

<div class="ctext">
 	 <div class="cr1">
    	<h3 class="righttit">相关课程</h3>
    		<div class="teacher">
		    	[#list relevantCourse as course]
				    <div class="teapic">
				        <a href="${base}${course.path}"  target="_blank">
				        	<img src=[#if course.sourceImage?? && course.sourceImage!=""]
					        			"${course.sourceImage}" 
					        		[#else]
					        			"${base}/resources/moc/images/c1.jpg"
					        		[/#if] height="60" title="${course.title}"></a>
				        <h3 class="courh3"><a href="${base}${course.path}" class="peptitle" target="_blank">${course.title}</a></h3>
				    </div>
				    <div class="clearh"></div>
				[/#list]
			</div>
   			<div class="clearh"></div>
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
