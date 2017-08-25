<script type="text/javascript">
    jQuery(function() {
        var username = getCookie("username");
        var $headerUsername = $("#headerUsername");
        var $headerUserName = $("#headerUserName");
        if (username != null) {
            $("#preLogin").attr("style", "display:none;");
            $headerUsername.html('<i class="log_user"></i>' + username);
            $headerUserName.html('<i class="log_user"></i>' + username);
            $("#beLogin").attr("style", "display:block;");
        } else {
            $("#preLogin").attr("style", "display:block;");
            $("#beLogin").attr("style", "display:none;");
        }
    });
</script>
<div class="exam_head">
	<span class="return_back" style="position:absolute;left:0;top:-10px;">
		<a href="${base}/">
			<img src="${base}/resources/moc/images/return-back.png" height="110">
		</a>
	</span>
    <div class="examlogo">
        <a class="elogo" href="${base}/question/industry.html"><img src="${base}/resources/moc/images/tklogo.png" height="50"></a>
        <span class="massage">
        <!--未登录-->
        <span class="exambtn_lore" id="preLogin">
            <a class="tkbtn tklog" href="${base}/login/index.jhtml">登录</a>
            <a class="tkbtn tkreg" href="${base}/register/index.jhtml">注册</a>
        </span>
        <!--已登录-->
        <div class="logined" id="beLogin">
            <a id="headerUsername" href="#" onMouseOver="logmine()" style="width:100px" class="link2 he ico"></a>
            <span id="lne" style="display:none" onMouseOut="logclose()" onMouseOver="logmine()">
                <span style="background:#fff;">
                    <a id="headerUserName" href="#" style="width:100px; display:block;" class="link2 he ico" ></a>
                </span>
            <div class="clearh"></div>
            <ul class="logmine">
                <li><a class="link1" href="${base}/member/course/myCourseList.jhtml?tabFlag=learning">我的课程</a></li>
                <li><a class="link1" href="${base}/member/courseThread/list.jhtml?questionType=question">我的问答</a></li>
                <li><a class="link1" href="${base}/member/courseNote/list.jhtml">我的笔记</a></li>
                <li><a class="link1" href="${base}/member/question/myQuestionList.jhtml">我的题库</a></li>
                <li><a class="link1" href="${base}/member/integralRule/list.jhtml">我的任务</a></li>
                <li><a class="link1" href="${base}/logout/logout.jhtml">退出</a></li>
            </ul>
            </span>
        </div>
        </span>
    </div>
</div>
