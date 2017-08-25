<script type="text/javascript">
       jQuery(function() {
          var username = getCookie("username");
          var $headerUsername= $("#headerUsername");
          if(username!=null){
               $("#preLogin").attr("style","display:none;");
               $headerUsername.html('<i class="log_user"></i>'+username);
               $("#beLogin").attr("style","display:block;");
          }else{
               $("#preLogin").attr("style","display:block;");
               $("#beLogin").attr("style","display:none;");
          }
	   		$(document).ready(function(){  
			    $(".nag a").each(function(){  
			        $this = $(this);
			        var url=$this[0].href;
			        var url2=url.substring(0,url.lastIndexOf("/"));
			        var currentURL=String(window.location);
			        var currentURL2=currentURL.substring(0,currentURL.lastIndexOf("/"));
			        if(url2==currentURL2){  
			            $this.addClass("link1 current");  
			        }  
				 }); 
		 	}); 
     });
</script>
<div class="head" id="fixed">
	<div class="nav">
    	<span class="navimg"><a href="${base}/"><img border="0" src="${base}/resources/moc/images/logo.png"></a></span>
        <ul class="nag">
        	[@navigation_list position = "top"]
				[#list navigations as navigation]
					<li>
						<a class="link1 navt"  href="${navigation.url}"[#if navigation.isBlankTarget] target="_blank"[/#if]>${navigation.name}</a>
					</li>
				[/#list]
			[/@navigation_list]
        </ul>
        
        <span class="massage">
	         <span id="preLogin" class="exambtn_lore">
	        	<a href="${base}/login/index.jhtml" class="tkbtn tklog">登录</a>
	            <a href="${base}/register/index.jhtml" class="tkbtn tkreg">注册</a>
	          </span>
	         <span id="beLogin" style="display:none;">
	            <a id="headerUsername" href="${base}/member/course/myCourseList.jhtml?membTab=tab1"  onMouseOver="logmine()" style="width:150px;height:30px;overflow:hidden;
					" class="link2 he ico" target="_blank">
				</a>
	            <span id="lne" style="display:none" onMouseOut="logclose()" onMouseOver="logmine()">
	                <span style="background:#fff;">
	                	<a id="headerUserName" href="${base}/member/course/myCourseList.jhtml?tabFlag=learning" style="width:150px;height:30px;overflow:hidden;" class="link2 he ico" target="_blank"></a>
	                </span>
	                <div class="clearh"></div>
	                <span class="img_user"></span>
	                <ul class="logmine" >
	                    <li><a class="link1" href="${base}/member/course/myCourseList.jhtml?tabFlag=learning">我的课程</a></li>
	                    <li><a class="link1" href="${base}/member/courseThread/list.jhtml?questionType=question">我的问答</a></li>
	                    <li><a class="link1" href="${base}/member/courseNote/list.jhtml">我的笔记</a></li>
	                    <li><a class="link1" href="${base}/member/question/myQuestionList.jhtml">我的题库</a></li>
	                    <li><a class="link1" href="${base}/member/integralRule/list.jhtml">我的任务</a></li>
	                    <li><a class="link1" href="${base}/logout/logout.jhtml">退出</a></li>
	                </ul>
	            </span>
	        </span>
   	 	</span>
   	 </div>
</div>