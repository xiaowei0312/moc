<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.outlineCategory.list")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $delete = $("#listTable a.delete");
	var $listForm = $("#listForm");
	var $filterSelect = $("#filterSelect");
	var $filterOption = $("#filterOption a");
	var $findByOutlineCategory = $("#findByOutlineCategory");
	var $analyVideo = $(".analyVideo");
	[@flash_message /]
	
	//视频解析
	$analyVideo.click(function(){
		var $this =$(this);
		var questionTitle=$this.closest("td").attr("title");
		$.dialogIframe({
			title:'题:'+questionTitle,
			width: 800,
			height:400,
			modal: true,
			ok: "确定",
			cancel: "返 回",
			iframeSrc: "${base}/admin/uploadFile/questionFileList.jhtml?targetType=question&targetId="+$this.attr("id"),
			onOk: function(dialogIframe) {
				alert($(dialogIframe.document).find("#name").val());
			}
		});
		
	});
	
	//根据类别查找题目
	$findByOutlineCategory.click(function() {
		$.dialog({
			title:"更多设置",
			[@compress single_line = true]
				content: '
				<table id="moreTable" class="moreTable" width="100%">
					<tr>
						<th width="30%" style="white-space:nowrap;">
							题目类别:
						<\/th>
						<td width="70%">
							<select id="selectCategoryId">
								<option value="">请选择类别<\/option>
								[#list outlineCategoryTree as outlineCategory]
									<option  value="${outlineCategory.id}"[#if "${outlineCategory.id}" = outlineCategoryId] selected="selected"[/#if]>
										[#if outlineCategory.grade != 0]
											[#list 1..outlineCategory.grade as i]
												&nbsp;&nbsp;
											[/#list]
										[/#if]
										${outlineCategory.name}
									<\/option>
								[/#list]
							<\/select>
						<\/td>
					<\/tr>
				<\/table>',
			[/@compress]
			width:450,
			height:100,
			modal: true,
			ok: "${message("admin.dialog.ok")}",
			cancel: "返 回",
			onOk: function() {
					$("#outlineCategoryId").attr("value",$("#selectCategoryId").attr("value"));
					$listForm.submit();
				}
			});
		});
	// 题目筛选
	$filterSelect.mouseover(function() {
		var $this = $(this);
		var offset = $this.offset();
		var $menuWrap = $this.closest("div.menuWrap");
		var $popupMenu = $menuWrap.children("div.popupMenu");
		$popupMenu.css({left: offset.left, top: offset.top + $this.height() + 2}).show();
		$menuWrap.mouseleave(function() {
			$popupMenu.hide();
		});
	});
	// 筛选选项
	$filterOption.click(function() {
		var $this = $(this);
		var $goal = $("#" + $this.attr("name"));
		if ($this.hasClass("checked")) {
			$goal.val("");
		} else {
			$goal.val($this.attr("value"));
		}
		$listForm.submit();
		return false;
	});
	


	// 删除
	$delete.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("admin.dialog.deleteConfirm")}",
			onOk: function() {
				$.ajax({
					url: "delete.jhtml",
					type: "POST",
					data: {id: $this.attr("val")},
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
						if (message.type == "success") {
							$this.closest("tr").remove();
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
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 问题分类列表
	</div>
	<form id="listForm" action="questionList.jhtml" method="get">
	<input type="hidden" id="questionType" name="questionType" value="${questionType}"/>
	<input type="hidden" id="difficulty" name="difficulty" value="${difficulty}" />
	<input type="hidden" id="outlineCategoryId" name="outlineCategoryId" value="${outlineCategoryId}" />
	
	<div class="bar">
		<div class="buttonWrap">
			<div class="menuWrap">
		<a href="${base}/admin/question/add.jhtml" class="iconButton">
			<span class="addIcon">&nbsp;</span>添加选择题
		</a>
		<a href="${base}/admin/question/add.jhtml?questionType=determine" class="iconButton">
			<span class="addIcon">&nbsp;</span>添加判断题
		</a>
		<a href="${base}/admin/question/add.jhtml?questionType=blanks" class="iconButton">
			<span class="addIcon">&nbsp;</span>添加填空题
		</a>
		<a href="${base}/admin/question/add.jhtml?questionType=essay" class="iconButton">
			<span class="addIcon">&nbsp;</span>添加简答题
		</a>
		<a href="${base}/admin/question/add.jhtml?questionType=material" class="iconButton">
			<span class="addIcon">&nbsp;</span>添加材料题
		</a>
		</div>
		</div>
		<div class="buttonWrap">
			<div class="menuWrap">
			  <a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>${message("admin.common.delete")}
				</a>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>${message("admin.common.refresh")}
				</a>
				<a href="javascript:;" id="filterSelect" class="button">
					题目筛选<span class="arrow">&nbsp;</span>
				</a>
				<div class="popupMenu">
					<ul id="filterOption" class="check">
						<li class="separator">
							<a href="javascript:;" name="questionType" value="choices"[#if questionType?? && questionType="choices"] class="checked"[/#if]>选择题</a>
						</li>
						<li>
							<a href="javascript:;" name="questionType" value="determine"[#if questionType?? && questionType="determine"] class="checked"[/#if]>判断题</a>
						</li>
						<li>
							<a href="javascript:;" name="questionType" value="blanks"[#if questionType?? && questionType="blanks"] class="checked"[/#if]>填空题</a>
						</li>
						<li>
							<a href="javascript:;" name="questionType" value="essay"[#if questionType?? && questionType="essay"] class="checked"[/#if]>简答题</a>
						</li>
						<li class="separator">
							<a href="javascript:;" name="difficulty" value="easy"[#if difficulty?? && difficulty="easy"] class="checked"[/#if]>简单</a>
						</li>
						<li>
							<a href="javascript:;" name="difficulty" value="normal"[#if difficulty?? && difficulty="normal"] class="checked"[/#if]>一般</a>
						</li>
						<li>
							<a href="javascript:;" name="difficulty" value="hard"[#if difficulty?? && difficulty="hard"] class="checked"[/#if]>困难</a>
						</li>
					</ul>
				</div>
			</div>
			<a href="javascript:;" id="findByOutlineCategory" class="button">更多设置</a>
			<div class="menuWrap">
				<a href="javascript:;" id="pageSizeSelect" class="button">
					${message("admin.page.pageSize")}<span class="arrow">&nbsp;</span>
				</a>
				<div class="popupMenu">
					<ul id="pageSizeOption">
						<li>
							<a href="javascript:;"[#if page.pageSize == 10] class="current"[/#if] val="10">10</a>
						</li>
						<li>
							<a href="javascript:;"[#if page.pageSize == 20] class="current"[/#if] val="20">20</a>
						</li>
						<li>
							<a href="javascript:;"[#if page.pageSize == 50] class="current"[/#if] val="50">50</a>
						</li>
						<li>
							<a href="javascript:;"[#if page.pageSize == 100] class="current"[/#if] val="100">100</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="menuWrap">
				<div class="search">
					<span id="searchPropertySelect" class="arrow">&nbsp;</span>
					<input type="text" id="searchValue" name="searchValue" value="${page.searchValue}" maxlength="200" />
					<button type="submit">&nbsp;</button>
				</div>
				<div class="popupMenu">
					<ul id="searchPropertyOption">
						<li>
							<a href="javascript:;"class="current" val="stem">题干</a>
						</li>
					</ul>
				</div>
			</div>
	</div>
	<table id="listTable" class="list" width="100%">
		<tr>
		   <th class="check" style="width:5%">
					<input type="checkbox" id="selectAll" />
				</th>
			<th style="width:20%">
				<span>类别名称</span>
			</th>
			<th style="width:45%">
				<span>题干</span>
			</th>
			<th style="width:5%">
				<span>难易度</span>
			</th>
			<th style="width:10%">
				<span>类型</span>
			</th>
			<th style="width:15%">
				<span>操作</span>
			</th>
		</tr>
		[#list page.content as question]
			<tr>
					<td>
						<input type="checkbox" name="ids" value="${question.id}" />
					</td>
				<td title="${question.outlineCategory.name}">
					${abbreviate(question.outlineCategory.name,30, "...")}
				</td>
				<td title="${question.stemToHtml}" class="questionStem">
					${abbreviate(question.stemToHtml, 60, "...")}
				</td>
				<td>
					[#if question.difficulty="normal"]
						一般
					[#elseif question.difficulty="hard"]
						困难
					[#else]
						简单
					[/#if]
				</td>
				<td>
					[#if question.questionType="choice"]
						选择题<i>&nbsp;多选</i>
					[#elseif question.questionType="single_choice"]
						选择题<i>&nbsp;单选</i>
					[#elseif question.questionType="determine"]
						<i>&nbsp;判断题</i>
					[#elseif question.questionType="blanks"]
						<i>&nbsp;填空题</i>
					[#elseif question.questionType="material"]
						<i>&nbsp;材料题</i>
					[#elseif question.questionType="essay"]
						<i>&nbsp;简答题</i>
					[#else]
						选择题<i>&nbsp;不定项</i>
					[/#if]
				</td>
				<td title="${abbreviate(question.stemToHtml, 80, "...")}">
					[#if question.questionType="material"]
						<a href="materialChildrenList.jhtml?id=${question.id}&outlineCategoryId=${question.outlineCategory.id}">[${message("admin.common.view")}]</a>
					[/#if]
					<a href="editQuestion.jhtml?id=${question.id}">[${message("admin.common.edit")}]</a>
					<a href="javascript:;" title="${question.stemToHtml}" id=${question.id} class="analyVideo">[视频解析]</a>
				</td>
			</tr>
		[/#list]
	</table>
	[@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
			[#include "/admin/include/pagination.ftl"]
		[/@pagination]
	</form>
</body>
</html>