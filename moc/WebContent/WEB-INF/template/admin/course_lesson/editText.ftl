[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.course.add")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>

<script type="text/javascript">


$().ready(function() {

	var $inputForm = $("#inputForm");
	var $courseCategoryId = $("#courseCategoryId");
	var $browserButton = $("#browserButton");
	var $current = $(".current");
	var $default = $(".default");
	var $type = $("#type");
	var $btnSubmit = $("#btnSubmit");
	
	
	[@flash_message /]
	
	$current.mouseover(function(){
		$(this).addClass("blue current");
		$(this).mouseleave(function(){
			$(this).removeClass("blue");
		});
	});
	
	$current.click(function(){
		var currentName = $(this).attr("name");
		var $currentShow = $("#"+$(this).attr("name"));
		$currentShow.prevAll().hide();
		$currentShow.nextAll().hide();
		$currentShow.show();
		
		$(this).prevAll().removeClass("green");
		$(this).nextAll().removeClass("green");
		$(this).addClass("green");
	});

	
	// 表单验证
	$inputForm.validate({
		rules: {
			title: {
				required: true
			},
			coin:{
				required:true,
				digits:true,
				min:0
			}
		},
		messages: {
			title: {
				pattern: "${message("admin.validate.required")}"
			},
			coin:{
				required:'学币必须填写',
				digits:'学币必须是正整数',
				min:'学币不能小于0'
			}
		}
	});
	
	
});



</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 编辑课时
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
		<input type="hidden" name="id" id="id" value="${courseLesson.id}"/>
		
		<input type="hidden" name="mediaName" id="mediaName" value="${courseLesson.mediaName}"/>
		<input type="hidden" name="length" id="length" value="${courseLesson.length}"/>
		<input type="hidden" name="course.id" id="courseId" value="${courseLesson.course.id}"/>
		<input type="hidden" name="courseChapter.id" id="chapterId" value="${courseLesson.courseChapter.id}"/>
		<input type="hidden" name="mediaSource" id="mediaSource" value="${courseLesson.mediaSource}" />
		<input type="hidden" name="type" id="type" value="${courseLesson.type}" />
		<input type="hidden" name="replayStatus" id="replayStatus" value="${courseLesson.replayStatus}" />
		<input type="hidden" name="status" id="status" value="${courseLesson.status}"/>
		<input type="hidden" name="learnedNum" value="${courseLesson.learnedNum}"/>
		
		<table class="input">
			<tr>
				<th>
					标题:
				</th>
				<td>
						<input type="text" name="title" class="text" style="width:400px;" value="${courseLesson.title}"/>
						<input type="checkbox" name="free" [#if courseLesson.free == "0"] checked="checked" [/#if] value="0"/>免费课时
				</td>
			</tr>
			<tr>
				<th>
					学币：
				</th>
				<td>
					<input type="text" name="coin" value="${courseLesson.coin}"/>
				</td>
			</tr>
			<tr>
				<th>
					摘要:
				</th>
				<td>
						<textarea name="summary" cols="50">${courseLesson.summary}</textarea>
				</td>
			</tr>
			
		</table>
		
		<table class="input tabContent">
			<tr>	
				<th>图文</th>
				
				<td>
					<textarea id="editor" name="content" style="width:500px;" >${courseLesson.content?html}</textarea>
				</td>
			</tr>
			
		</table>
		
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("admin.common.submit")}" id="btnSubmit" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='${base}/admin/course_chapter/list.jhtml?courseId=${courseLesson.course.id}'"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>