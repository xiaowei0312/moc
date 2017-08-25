<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.course.list")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.treeSelect.js"></script>
<script type="text/javascript" src="${base}/resources/admin/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="${base}/resources/admin/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="${base}/resources/admin/ui/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="${base}/resources/admin/ui/jquery.ui.sortable.js"></script>
<style type="text/css">
.left{
	position:absolute,
	left:50%
}
.moreTable th {
	width: 50px;
	line-height: 25px;
	padding: 5px 10px 5px 0px;
	font-weight: normal;
	color: #333333;
	background-color: #f8fbff;
}

.moreTable td {
	line-height: 25px;
	padding: 5px;
	color: #666666;
}

.promotion {
	color: #cccccc;
}

.item-actions {
    background: none repeat scroll 0% 0% ;
    margin-left: 10px;
    position: absolute;
    float: right;
    right: 50px;
}

</style>
<script type="text/javascript">
$().ready(function() {

	var $deleteChapter = $("#sortable a.chapter");
	var $deleteLesson = $("#sortable a.lesson");
	var $publishLesson = $("#sortable a.publish");
	var $listForm = $("#listForm");
	var $backList=$("#backList");
	var $outlineCategoryId=$(".outlineCategoryId");
	
	//一键发布--全选----一键删除时可以移除disabled
	var $publishWrap = $("#publishWrap");
	var $publishAll = $("#publishAll");
	var $selectAll=$("#selectAll");
	var $selectIds = $("input[name='selectIds']");
	[@flash_message /]
	
	$publishAll.click(function(){
		if($publishWrap.hasClass("disabled")){
			$.message("error","请选择要发布的课时");
			return false;
		}
		var $selectIds = $("input[name='selectIds']:enabled:checked, .selectIds");
		$.dialog({
			type: "warn",
			content: message("发布确认"),
			ok: message("admin.dialog.ok"),
			cancel: message("admin.dialog.cancel"),
			onOk: function() {
				$.ajax({
					url: "${base}/admin/course_lesson/publishAll.jhtml",
					type: "POST",
					data: $selectIds.serialize(),
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
						if (message.type == "success") {
							location.reload(true);
						}
					}
				});//ajax
			}//ok
		});//dialog
	});
	
	//关联大纲
	$outlineCategoryId.click(function(){
	 	var lessonId=$(this).find("input[class='lessonId']").val();
	 	var categoryId=$(this).find("input[class='categoryId']").val();
	 	var treePath=$(this).find("input[class='treePath']").val();
		$.dialog({
			title:"关联大纲",
			[@compress single_line = true]
				content: '
				<script type="text/javascript">
					$("#outlineCategory").treeSelect({
						url: "${base}/admin/question/getOutlineCategoryChildren.jhtml"
					});
				<\/script>
				<table id="moreTable" class="moreTable" width="100%">
					<tr>
						<th width="25%" style="white-space:nowrap;">
							大纲类别:
						<\/th>
						<td width="85%" style="white-space:nowrap;">
							<input type="hidden" name="outlineCategoryId" id="outlineCategory"
								value="'+categoryId+'"
								treePath="'+treePath+'"
							/> 
						<\/td>
					<\/tr>
				<\/table>',
			[/@compress]
			width:600,
			height:120,
			modal: true,
			ok: "${message("admin.dialog.ok")}",
			cancel: "返 回",
			onOk: function() {
			    var outlineCategoryId=$("input[name='outlineCategoryId']").val();
			    $.ajax({
					url: "${base}/admin/course_lesson/relevanceCategory.jhtml",
					type: "POST",
					data: {lessonId:lessonId,outlineCategoryId:outlineCategoryId},
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
						//延时执行
						setTimeout(function(){
							window.location.reload();		
						},1000)
					}
				});
			}
		});
	});
	
	
	
	$selectIds.click(function(){
		var $this = $(this);
		if ($this.prop("checked")) {
			$this.closest("li").addClass("selected");
			$publishWrap.removeClass("disabled");
		}else{
			$this.closest("li").removeClass("selected");
			if ($("input[name='selectIds']:enabled:checked").size() > 0) {
				$publishWrap.removeClass("disabled");
			} else {
				$publishWrap.addClass("disabled");
			}
		}
	});
	$selectAll.click(function(){
		var $this = $(this);
		var $enabledIds = $("input[name='selectIds']:enabled");
		var $contentRow = $enabledIds.closest("li");
		if ($this.prop("checked")) {
			$enabledIds.prop("checked", true);
			if ($enabledIds.filter(":checked").size() > 0) {
				$publishWrap.removeClass("disabled");
				$enabledIds.addClass("selected");
				$contentRow.addClass("selected");
			} else {
				$publishWrap.addClass("disabled");
			}
		} else {
			$enabledIds.prop("checked", false);
			$publishWrap.addClass("disabled");
			$contentRow.removeClass("selected");
		}
	
	});
	
	
	var $list = $("#sortable").sortable({
		stop:function(event,ui){
			var data = $list.sortable('toArray').toString();	
			$.ajax({
				url: "sort.jhtml",
				type: "POST",
				data: {ids:data},
				dataType: "json",
				cache: false,
				success: function(message) {
					$.message(message);
					//延时执行
					setTimeout(function(){
						window.location.reload();		
					},1000)
				}
			});	
		}		
	});
	$("#sortable").disableSelection();
	
	// 删除章节
	$deleteChapter.click(function() {
		var $this = $(this);
		var data = $list.sortable('toArray').toString();
		$.dialog({
			type: "warn",
			content: "${message("admin.dialog.deleteConfirm")}",
			onOk: function() {
				$.ajax({
					url: "delete.jhtml",
					type: "POST",
					data: {id: $this.attr("value"),ids:data},
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
						//延时执行
						if("success" == message.type){
							setTimeout(function(){
								window.location.reload();		
							},1000);
						}
					}
				});
			}
		});
		
		return false;
	});

	// 删除课时
	$deleteLesson.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("admin.dialog.deleteConfirm")}",
			onOk: function() {
				$.ajax({
					url: "${base}/admin/course_lesson/delete.jhtml",
					type: "POST",
					data: {id: $this.attr("value")},
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
						if("success" == message.type){
							setTimeout(function(){
								window.location.reload();		
							},1000);					
						}
					}
				});
			}
		});
		
		return false;
	});
	
	// 发布课时
	$publishLesson.click(function() {
		var $this = $(this);
		var index = $this.html().indexOf("[发布]");
		var status = index>=0?"published":"unpublished";
		var content = index>=0?"你确定要发布课时吗？":"你确定要取消发布课时吗？";
		$.dialog({
			type: "warn",
			content: content,
			onOk: function() {
				$.ajax({
					url: "${base}/admin/course_lesson/publish.jhtml?course.id=${courseId}",
					type: "POST",
					data: {id: $this.attr("value"),status:status},
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
						if("success" == message.type){
							setTimeout(function(){
								window.location.reload();		
							},1000);					
						}
					}
				});
			}
		});
		
		return false;
	});
	
	
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a>&raquo;课时列表(${courseTitle})
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<input type="hidden"  value="${courseId}" name="course.id" class="selectIds"/>
		
		<div class="bar">
			<div class="buttonWrap">
				<a href="${base}/admin/course_chapter/addChapter.jhtml?courseId=${courseId}&pageNumber=${pageNumber}" class="button">添加章节</a>
				<a href="javascript:void(0);" id="publishWrap" class="iconButton disabled">
					<input type="checkbox" id="selectAll" />
					<span id="publishAll">
						一键发布
					</span>
				</a>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>${message("admin.common.refresh")}
				</a>
				<a href="javascript:;" onclick="location.href='${base}/admin/course/list.jhtml?courseId=${courseId}'" class="button">返回</a>
			</div>
		</div>
		<ul id="sortable" class="list">
			[#assign x = 0]
			[#assign z = 0]
			[#assign sameChapter=0]
			[#list chapters as courseChapter]
				[#if sameChapter !=courseChapter.id]
					[#assign sameChapter=courseChapter.id]
					[#if courseChapter.type == 'chapter']
						[#assign x = x+1]
						[#assign y = 0]
						<li id="chapter-${courseChapter.id}" name="chapter-${courseChapter.id}">
							<span><strong>第${x}章 &nbsp;&nbsp;${courseChapter.title}</strong></span>
							<span class="item-actions">
								<a href="${base}/admin/course_chapter/addUnit.jhtml?courseId=${courseId}&parentId=${courseChapter.id}&pageNumber=${pageNumber}" >[添加小节]</a>
								<a href="${base}/admin/course_lesson/add.jhtml?courseId=${courseId}&chapterId=${courseChapter.id}" >[添加课时]</a>
								<a href="${base}/admin/course_chapter/editChapter.jhtml?id=${courseChapter.id}" >[编辑]</a>
								<a href="javascript:;" class="delete chapter" value="${courseChapter.id}" >[删除]</a>
							</span>
						</li>
						
						[#list courseChapter.courseLessons as lesson]
						[#assign z = z+1]
						<li id="lesson-${lesson.id}" name="lesson-${lesson.id}">
							<span style="margin-left:4%;color:blue">
								<input type="checkbox" name="selectIds" value="${lesson.id}" 
									[#if "${lesson.status}"!="unpublished"]
											disabled
									[/#if]
								/>
								课时${z} &nbsp;&nbsp;${lesson.title} 
							</span>
							<span class="item-actions">
								[#if "${lesson.status}"="unpublished"]
									<a href="javascript:;" class="publish red" value="${lesson.id}" >[发布]</a>
									<a href="${base}/admin/course_lesson/edit.jhtml?id=${lesson.id}" >[编辑]</a>
									<a href="javascript:;" class="delete lesson" value="${lesson.id}" >[删除]</a>
								[#else]
									<a href="javascript:;" class="publish" value="${lesson.id}">[取消发布]</a>
								[/#if]
								<a href="${base}/admin/course_material/add.jhtml?lessonId=${lesson.id}&courseId=${courseId}">[添加资料]</a>
								<a class="outlineCategoryId" href="javascript:;">
								    <input type="hidden" class="lessonId" value="${lesson.id}"/>
								    <input type="hidden" class="categoryId"
								    	value=
								    	[#if lesson.outlineCategory?? && lesson.outlineCategory.id!=0]
								       		"${lesson.outlineCategory.id}"
							       		[#else]
							       		    ""
							       		[/#if]
							       		/>
							        <input type="hidden" class="treePath"
							        	value=
							        	[#if lesson.outlineCategory?? && lesson.outlineCategory.id!=0]
								       		"${(lesson.outlineCategory.treePath)!}"
							       		[#else]
							       		    ""
							       		[/#if]
							        />
									[关联大纲]
								</a>
							</span>
						</li>
						
						[/#list]
						
					[#else]
						[#assign y = y+1]
						<li id="chapter-${courseChapter.id}" name="chapter-${courseChapter.id}">
							<span style="margin-left:2%">第${y}节 ${courseChapter.title}</span>
							<span class="item-actions">
								<a href="${base}/admin/course_lesson/add.jhtml?courseId=${courseId}&chapterId=${courseChapter.id}" >[添加课时]</a>
								<a href="${base}/admin/course_chapter/editUnit.jhtml?id=${courseChapter.id}">[编辑]</a>
								<a href="javascript:;" class="delete chapter" value="${courseChapter.id}">[删除]</a>
							</span>
							
						</li>
						
						[#list courseChapter.courseLessons as lesson]
						 [#assign z = z+1]
						<li id="lesson-${lesson.id}" name="lesson-${lesson.id}">
							<span style="margin-left:4%;color:blue"> 
								<input type="checkbox" name="selectIds" 
									[#if "${lesson.status}"!="unpublished"]
										disabled
									[/#if]
								 value="${lesson.id}" />
								课时${z}&nbsp;&nbsp;${lesson.title}
							</span>
							<span class="item-actions">
								[#if "${lesson.status}"="unpublished"]
									<a href="javascript:;" class="publish  red" value="${lesson.id}" >[发布]</a>
									<a href="${base}/admin/course_lesson/edit.jhtml?id=${lesson.id}" >[编辑]</a>
									<a href="javascript:;" class="delete lesson" value="${lesson.id}" >[删除]</a>
									
								[#else]
									<a href="javascript:;" class="publish" value="${lesson.id}" >[取消发布]</a>
									<a href="javascript:;" class="delete lesson" value="${lesson.id}" >[删除]</a>
								[/#if]
								<a href="${base}/admin/course_material/add.jhtml?lessonId=${lesson.id}&courseId=${courseId}">[添加资料]</a>
								<a class="outlineCategoryId" href="javascript:;">
								    <input type="hidden" class="lessonId" value="${lesson.id}"/>
								    <input type="hidden" class="categoryId"
								    	value=
								    	[#if lesson.outlineCategory?? && lesson.outlineCategory.id!=0]
								       		"${lesson.outlineCategory.id}"
							       		[#else]
							       		    ""
							       		[/#if]
							       		/>
							        <input type="hidden" class="treePath"
							        	value=
							        	[#if lesson.outlineCategory?? && lesson.outlineCategory.id!=0]
								       		"${(lesson.outlineCategory.treePath)!}"
							       		[#else]
							       		    ""
							       		[/#if]
							        />
									[关联大纲]
								</a>
							</span>
						</li>
						
						[/#list]
						
					[/#if]
				[/#if]				
			[/#list]
		</ul>
	</form>
</body>
</html>
