<script type="text/javascript">
    $().ready(function() {
        var $exambtn_lore=$(".exambtn_lore");
	    var $headerUsername = $("#headerUsername");
	    var $vlogin=$(".vlogin");
	    var $searchbtn=$(".searchbtn");
	    var $listForm=$("#listForm");
	    var $searchProblem=$("#searchProblem");
	    var $askbtn=$(".askbtn");
	    var $headerUserName= $("#headerUserName");
   	    var username = getCookie("username");
   	    
	    if (username != null) {
	        $exambtn_lore.hide();
		    $headerUsername.html('<i class="log_user"></i>'+username);
            $("#beLogin").attr("style","display:block;");
	    }else{
            $exambtn_lore.show();
            $("#beLogin").attr("style","display:none;");
	    }
	    
	      $(".askbtn").click(function(){
              //验证登录
	          var b=volidLogin();
	          if(!b){
	             return false;
	          }
	           var tabFlag=$("#tabFlag").val();
               window.location.href="${base}/courseThread/add.jhtml?tabFlag="+tabFlag
               +"&askContent="+$("#searchProblem").val();
        });
        
	    $searchbtn.click(function(){
	    	var threadContent=$("#searchProblem").val();
	    	if($listForm.length>0){
	    	    $("#threadContent").val(threadContent);
	    	    var tabFlag=$("#tabFlag").val();
	    		$listForm.submit();
	    	}else{
	    	    window.location.href="${base}/courseThread/questionList.jhtml?tabFlag=newestThread&threadContent="+threadContent;
	    	}
        });
    });
    
     
    function loginSuccess(){
        var tabFlag=$("#tabFlag").val();
        window.location.href="${base}/courseThread/add.jhtml?tabFlag="+tabFlag
         +"&askContent="+$("#searchProblem").val();
    }
</script>
<div class="exam_head">
	<div class="examlogo">
    <a class="elogo" href="${base}/courseThread/questionList.jhtml?tabFlag=newestThread">
    	<img src="${base}/resources/moc/images/asklogo.png" height="40">
    </a>
	<span class="emenu"><a href="/">首页</a></span>
	<span class="emenu"><a href="${base}/courseThread/questionList.jhtml?tabFlag=newestThread">问题</a></span>
	<input type="text" id="searchProblem"  class="form-control"
		value=
			  [#if threadContent?? && threadContent!=Null]
	            "${threadContent}"
	           [#else]
	           		"请输入关键字"
	           [/#if]
	            onblur="if (this.value =='') this.value='请输入关键字';" 
	            onclick="if (this.value=='请输入关键字') this.value='';"
		/>
	<a href="javascript:void(0);" class="searchbtn">搜索</a>
	<span><a href="javascript:void(0);" class="askbtn">+提问</a></span>
	<!--登录状态-->
    <span class="massage">
    	<!--未登录-->
	    <span class="exambtn_lore" >
	        <a class="tkbtn tklog" href="${base}/login/index.jhtml">登录</a>
	        <a class="tkbtn tkreg" href="${base}/register/index.jhtml">注册</a>
	    </span>
	     <span id="beLogin" style="display:none;">
	            <a id="headerUsername" href="${base}/member/course/myCourseList.jhtml?membTab=tab1"  onMouseOver="logmine()" style="width:150px;height:30px;margin-top:10px;overflow:hidden;
					" class="link2 he ico" target="_blank"><i class="log_user"></i>
				</a>
	            <span id="lne" style="display:none" onMouseOut="logclose()" onMouseOver="logmine()">
	                <div class="clearh"></div>
	                <span class="img_user" style="top:0;"></span>
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