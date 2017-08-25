<!doctype html>
<html><!-- InstanceBegin template="/Templates/dwt.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<!-- InstanceBeginEditable name="doctitle" -->
<title>谋刻职业教育在线测评与学习平台</title>
<link href="${base}/resources/moc/css/common.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/course.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/member.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/tab.css" media="screen">

<script  type="text/javascript" src="${base}/resources/moc/js/jquery.js"></script>
<script  type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
<script  type="text/javascript" src="${base}/resources/moc/js/jquery.tabs.js"></script>
<script src="${base}/resources/moc/js/mine.js"></script>
<script type="text/javascript">
$(function(){

	$('.demo2').Tabs({
		event:'click'
	});
	
});	
</script>
<script type="text/javascript">
$().ready(function(){
    var $listForm=$("#listForm");
    var $pageNumber = $("#pageNumber");
    
    var questionType=$("#questionType").val();
    if(questionType=='question'){
        $("#myQuestion").attr("class","current");
        $("#myAnswer").attr("class","");
    }else if(questionType=='answer'){
        $("#myQuestion").attr("class","");
        $("#myAnswer").attr("class","current");
    }
    
    $("#tab_menu>li").each(function(index){
        $(this).click(function(){  
           if(index==0){
               $("#myQuestion").attr("class","current");
               $("#myAnswer").attr("class","");
               $("#questionType").val("question");
               $listForm.submit();
           }else if(index==1){
               $("#myQuestion").attr("class","");
               $("#myAnswer").attr("class","current");
               $("#questionType").val("answer");
               $listForm.submit();
           }
        });
    });

	$.pageSkip = function(pageNumber) {
		$pageNumber.val(pageNumber);
		$listForm.submit();
		return false;
	}
});	


</script>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="head" -->
<!-- InstanceEndEditable -->

</head>

<body>

   [#include "/moc/include/memberHeader.ftl" /]
	<div class="membcont">
<h3 class="mem-h3">我的问答</h3>
<div class="box demo2" style="width:795px; padding-left:40px;margin-top:20px;">
		<ul id="tab_menu" class="tab_menu">
			<li id="myQuestion" class="">我问的</li>
			<li id="myAnswer" class="">我答的</li>
		</ul>
		<div class="tab_box">
		 <form id="listForm" action="${base}/member/courseThread/list.jhtml" method="get">
		  <input type="hidden" id="pageNumber" name="pageNumber" value="${page.pageNumber}" />
		  <input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}" />
         <input type="hidden" id="questionType" name="questionType" value="${questionType}"/>
         <input type="hidden" name="membTab" value="${membTab}"/>
			<div>
			 <div>
				<ul class="notelist nl1" >
				    [#if page.content?size>0]
						[#if questionType="question"]
							[#list page.content as myQuestionPage]
						       <li>
						       		<span class="mbm mw">
						       		<a class="blacklink" href="${base}/member/courseThread/view.jhtml?threadId=${myQuestionPage.id}">${myQuestionPage.title}</a>
						       		</span>
						            <p class="peptime pswer" style="text-align:left">
									    <b class="coclass mcn">
										     [#if myQuestionPage.courseLesson?? && myQuestionPage.courseLesson!=Null]
										        [#assign courseLesson = myQuestionPage.courseLesson /]
										     	<a class="hwtit" href="${base}/member/course/toLearn.jhtml?lessonId=${courseLesson.id}&courseId=${myQuestionPage.course.id}&mediaId=${courseLesson.uploadFiles.id}" target="_blank">
										    		源自：${myQuestionPage.courseLesson.title}
										    	</a>
									    	 [#else]
								                	[#if myQuestionPage.course?? && myQuestionPage.course!=Null]
							                			源自：${myQuestionPage.course.title}
								                	[/#if]
										     [/#if]	
									    </b>
						            	<b class="cotime mct">时间：${myQuestionPage.createDate?string("yyyy-MM-dd HH:mm:ss")}</b>             
						                <span class="pepask" style="float:right">
						                <b class="asklabel">回答(<strong><a class="bluelink" href="${base}/member/courseThread/view.jhtml?threadId=${myQuestionPage.id}&answerFlag=answer">${myQuestionPage.postNum}</a></strong>)</b>
						                <b class="asklabel">浏览(<strong><a class="bluelink" href="${base}/member/courseThread/view.jhtml?threadId=${myQuestionPage.id}">${myQuestionPage.hitNum}</a></strong>)</b>
						                </span>
						            </p>
						       </li> 
						    [/#list] 
					    [#elseif questionType="answer"]
					    	[#list page.content as myAnswerObj]
						       <li>
						       		<span class="mbm mw">
						       		<a class="blacklink" href="${base}/member/courseThread/view.jhtml?threadId=${myAnswerObj[0]}">${myAnswerObj[1]}</a>
						       		</span>
						            <p class="peptime pswer" style="text-align:left">
									    <b class="coclass mcn">
										    [#if myAnswerObj[2]?? && myAnswerObj[2]!=Null]
											    <a class="hwtit" href="${base}/member/course/toLearn.jhtml?lessonId=${myAnswerObj[6]}&mediaId=${myAnswerObj[7]}&courseId=${myAnswerObj[8]}" target="_blank">
											    	源自：${myAnswerObj[2]}
											    </a>
										    [#else]
								                	[#if  myAnswerObj[6]?? && myAnswerObj[6]!=Null]
							                			源自：${myAnswerObj[6]}
								                	[/#if]
										    [/#if]
									    </b>
						            	<b class="cotime mct">时间：${myAnswerObj[3]?string("yyyy-MM-dd HH:mm:ss")}</b>             
						                <span class="pepask" style="float:right">
						                <b class="asklabel">回答(<strong><a class="bluelink" href="${base}/member/courseThread/view.jhtml?threadId=${myAnswerObj[0]}&answerFlag=answer">${myAnswerObj[4]}</a></strong>)</b>
						                <b class="asklabel">浏览(<strong><a class="bluelink" href="${base}/member/courseThread/view.jhtml?threadId=${myAnswerObj[0]}">${myAnswerObj[5]}</a></strong>)</b>
						                </span>
						            </p>
						       </li> 
						    [/#list] 
					    [/#if]
				 	[#else]
				    	 <p class="course_none"> 暂无问答</p>
				 	[/#if] 
                </ul>
                </div>
                </div>
			    <br/>
			     <div class="clearh">
				      [@pagination pageNumber = page.pageNumber totalPages = page.totalPages pattern = "javascript: $.pageSkip({pageNumber});"]
						     [#include "/moc/include/pagination.ftl"]
					   [/@pagination]
                </div> 
                </form>    
			</div>
		</div>
</div>


<div class="clearh"></div>
</div>

<!-- InstanceEndEditable -->

  [#include "/moc/include/footer.ftl" /]
</body>

<!-- InstanceEnd --></html>
