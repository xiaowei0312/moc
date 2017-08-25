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
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript">


$().ready(function() {

	
	var $selectAll2 = $("#selectAll2");
	var $deleteButton2 = $("#deleteButton2");
	var $addButton=$("#addButton");
	var $contentRow2 = $("#listTable2 tr:gt(0)");
	var $ids2 = $("#listTable2 input[name='ids']");
	var $inputForm = $("#inputForm");
	
	[@flash_message /]
	
	//为课时添加资料
	$("#listTable input[name='ids'],#selectAll").click(function(){
		var $this=$(this);
		if($this.prop("checked")){
			$addButton.removeClass("disabled");
		}else{
			$addButton.addClass("disabled");
		}
	});
	$addButton.click(function(){
		var $this=$(this);
		if ($this.hasClass("disabled")) {
			return false;
		}
		$inputForm.submit();
	});
	
	// 全选
	$selectAll2.click( function() {
		var $this = $(this);
		var $enabledIds = $("#listTable2 input[name='ids']:enabled");
		if ($this.prop("checked")) {
			$enabledIds.prop("checked", true);
			if ($enabledIds.filter(":checked").size() > 0) {
				$deleteButton2.removeClass("disabled");
				$contentRow2.addClass("selected");
			} else {
				$deleteButton2.addClass("disabled");
			}
		} else {
			$enabledIds.prop("checked", false);
			$deleteButton2.addClass("disabled");
			$contentRow2.removeClass("selected");
		}
	});

	// 选择
	$ids2.click( function() {
		var $this = $(this);
		if ($this.prop("checked")) {
			$this.closest("tr").addClass("selected");
			$deleteButton2.removeClass("disabled");
		} else {
			$this.closest("tr").removeClass("selected");
			if ($("#listTable2 input[name='ids']:enabled:checked").size() > 0) {
				$deleteButton2.removeClass("disabled");
			} else {
				$deleteButton2.addClass("disabled");
			}
		}
	});
	
	// 删除
	$deleteButton2.click( function() {
		var $this = $(this);
		if ($this.hasClass("disabled")) {
			return false;
		}
		var $checkedIds = $("#listTable2 input[name='ids']:enabled:checked");
		$.dialog({
			type: "warn",
			content: message("admin.dialog.deleteConfirm"),
			ok: message("admin.dialog.ok"),
			cancel: message("admin.dialog.cancel"),
			onOk: function() {
				$.ajax({
					url: "delete.jhtml",
					type: "POST",
					data: $checkedIds.serialize(),
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
						if (message.type == "success") {
							setTimeout(function() {
								location.reload(true);
							}, 1000);
						}
						$deleteButton2.addClass("disabled");
						$selectAll2.prop("checked", false);
						$checkedIds.prop("checked", false);
					}
				});
			}
		});
	});
	
});



</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 添加资料
	</div>
	
	<form id="inputForm2" action="save.jhtml" method="post">
		
		<ul id="tab2" class="tab">
			<li >
				<input type="button" value="已选资料" />
			</li>
		</ul>
		<div class="bar">
			<div class="buttonWrap">
				<a href="javascript:;" id="deleteButton2" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>${message("admin.common.delete")}
				</a>				
			</div>
		</div>
		<table id="listTable2" class="list tabContent " >
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll2" />
				</th>
				<th>
					文件名称
				</th>
				<th>
					文件大小
				</th>
				<th>
					创建时间
				</th>
			</tr>
			[#list courseLessonMaterials as courseMaterial]
			<tr>
				<td>
					<input type="checkbox" name="ids" value="${courseMaterial.id}" />
				</td>
				<td>${courseMaterial.uploadFiles.filename}</td>
				<td>${uniConversion.bytes2kb(courseMaterial.uploadFiles.size)}</td>
				<td>
					${courseMaterial.createDate?string("yyyy-MM-dd HH:mm:ss")}
				</td>
			</tr>
			[/#list]
		</table>
	</form>
	<form id="inputForm" action="save.jhtml" method="post">
		<input type="hidden" name="lessonId" id="lessonId" value="${lessonId}"/>
		<input type="hidden" name="courseId" id="courseId" value="${courseId}"/>
		<ul id="tab" class="tab">
			<li >
				<input type="button" value="资料库中选择" />
			</li>
			<li>
				<input type="button" value="导入网络视频" />
			</li>
		</ul>
		<table id="listTable" class="list tabContent " >
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					文件名称
				</th>
				<th>
					文件大小
				</th>
				<th>
					创建时间
				</th>
			</tr>
			[#list courseMaterialFiles as uploadFile]
			<tr>
				<td>
					<input type="checkbox" name="ids" value="${uploadFile.id}" />
				</td>
				<td>${uploadFile.filename}</td>
				<td>${unitConversion.bytes2kb(uploadFile.size)}</td>
				<td>
					${uploadFile.createDate?string("yyyy-MM-dd HH:mm:ss")}
				</td>
			</tr>
			[/#list]
		</table>
		<table class="input tabContent">
			<tr>
				<td>
						网络导入
				</td>
			</tr>
			
		</table>
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<div class="buttonWrap">
						<a href="javascript:;" id="addButton"  class="iconButton disabled">
							添加
						</a>
					</div>
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='${base}/admin/course_chapter/list.jhtml?courseId=${courseId}'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>