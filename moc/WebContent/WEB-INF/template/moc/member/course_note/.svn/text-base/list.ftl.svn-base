<!doctype html>
<html><!-- InstanceBegin template="/Templates/dwt.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<!-- InstanceBeginEditable name="doctitle" -->
<title>谋刻职业教育在线测评与学习平台</title>
<link rel="stylesheet" href="${base}/resources/moc/css/common.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/course.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/member.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/tab.css" media="screen">

<script type="text/javascript" src="${base}/resources/moc/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/jquery.tabs.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/mine.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>

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
       
       [@flash_message /]
       
       $.pageSkip = function(pageNumber) {
			$pageNumber.val(pageNumber);
			$listForm.submit();
			return false;
	  }
    });
      
   function del(courseNoteId){
      if (confirm("确认要删除？")) {
		      $.ajax({
					url: "${base}/member/courseNote/delete.jhtml",
					type: "POST",
					data: {"courseNoteId":courseNoteId},
					dataType: "json",
					cache: false,
					success: function(message) {
					   $.message(message);
					   location.reload(true);
					}
			 });
	 }
   }
</script>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="head" -->
<!-- InstanceEndEditable -->

</head>

<body>
  [#include "/moc/include/memberHeader.ftl" /]

	<form id="listForm" action="list.jhtml" method="get">
     <input type="hidden" id="pageNumber" name="pageNumber" value="${page.pageNumber}" />
	 <input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}" />
	<div class="membcont">
		<h3 class="mem-h3">我的笔记</h3>
		<ul class="notelist home" >
			 [#if page.content?size>0] 
			     [#list page.content as courseNote]
			       <li>
			            <div class="clearh"></div>
			       		<p class="notext">${courseNote.content}</p>
			            <p class="gray">
				            <b class="coclass mcn">
				            	源自课时:
			            		[#if courseNote.courseLesson??]
				            		[#assign courseLesson = courseNote.courseLesson /]
					            	<a target="_blank" href="${base}/member/course/toLearn.jhtml?lessonId=${courseLesson.id}&courseId=${courseNote.course.id}&mediaId=${courseLesson.uploadFiles.id}" class="hwtit">
					            		${courseNote.courseLesson.title}
				            	    </a>
			            		[/#if]	
				            </b>
				            <b class="cotime mct">最新更新时间：${courseNote.modifyDate}</b>
				            <a href="javascript:void(0);" class="delete" onclick="del(${courseNote.id})">X&nbsp;删除</a>
			            </p>
			            <input type="hidden" class="courseNoteId" value="${courseNote.id}"/>
			       </li>  
			      [/#list]
			  [#else]
			  		<p class="course_none"> 暂无笔记</p>
		      [/#if]	        
		  </ul>
		      [@pagination pageNumber = page.pageNumber totalPages = page.totalPages pattern = "javascript: $.pageSkip({pageNumber});"]
							[#include "/moc/include/pagination.ftl"]
			 [/@pagination]
		</div>
	</form>
		
		<div class="clearh"></div>
<!-- InstanceEndEditable -->
</div>

<div class="clearh"></div>
 [#include "/moc/include/footer.ftl" /]
</body>

<!-- InstanceEnd --></html>
