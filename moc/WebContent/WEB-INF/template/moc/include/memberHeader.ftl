<script type="text/javascript">
    $().ready(function() {
	  var username = getCookie("username");
	  if(username!=null){
	  	$("#showUserName").text(""+username);
	  }else{
	  	$("#showUserName").text("");
	  }
      $("#memb a").each(function(){  
	        $this = $(this);
	        var url=$this[0].href;
	        var url2=url.substring(0,url.lastIndexOf("/"));
	        var currentURL=String(window.location);
	        var currentURL2=currentURL.substring(0,currentURL.lastIndexOf("/"));
	        if(url2==currentURL2){  
	            $this.parent("li").addClass("currnav");  
	        }  
	 }); 
    });
</script>
[#include "/moc/include/header.ftl" /]

<!-- InstanceBeginEditable name="EditRegion1" -->
<div class="clearh"></div>
<div class="membertab">
<div class="memblist">
	<div class="membhead">
    <div style="text-align:center;">
       <img  width="80" 
      	 src=
      	    [#if headImage?? && headImage!=Null]
                 "${headImage}"
            [#else]
				"${base}/resources/moc/images/0-0.JPG"
			[/#if] 
       />
    </div>
    <div style="width:220px;text-align:center;">
    <p class="membUpdate mine" id="showUserName"></p> 
    <p class="membUpdate mine"><a href="${base}/member/profile/edit.jhtml">修改信息</a>&nbsp;|&nbsp;
    <a href="${base}/member/password/edit.jhtml">修改密码</a></p>
    <div class="clearh"></div>
    </div>
    </div>
    <div class="memb">
   
    <ul id="memb">
        <li class=""><a class="mb1" href="${base}/member/course/myCourseList.jhtml?tabFlag=learning">我的课程</a></li>
		<li class=""><a class="mb3" href="${base}/member/courseThread/list.jhtml?questionType=question">我的问答</a></li>
		<li class=""><a class="mb4" href="${base}/member/courseNote/list.jhtml">我的笔记</a></li>
        <li class=""><a class="mb2" href="${base}/member/question/myQuestionList.jhtml">我的题库</a></li>
        <li class=""><a class="mb2" href="${base}/member/integralRule/list.jhtml">我的任务</a></li>
    </ul>
   
    </div>

    
  </div>
