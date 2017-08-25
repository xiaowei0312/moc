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
	var $filterOption = $("#filterOption a");

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
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 材料题子题列表
	</div>
	<form id="listForm" action="questionList.jhtml" method="get">
	<input type="hidden" id="questionType" name="questionType" value="${questionType}"/>
	<input type="hidden" id="difficulty" name="difficulty" value="${difficulty}" />
	<input type="hidden" id="outlineCategoryId" name="outlineCategoryId" value="${outlineCategoryId}" />
	
	<div class="bar">
		<div class="buttonWrap">
			<div class="menuWrap">
				<a href="${base}/admin/question/add.jhtml?materialID=${parentQuestion.id}&outlineCategoryId=${outlineCategoryId}" class="iconButton">
					<span class="addIcon">&nbsp;</span>添加选择题
				</a>
				<a href="${base}/admin/question/add.jhtml?questionType=determine&materialID=${parentQuestion.id}&outlineCategoryId=${outlineCategoryId}" class="iconButton">
					<span class="addIcon">&nbsp;</span>添加判断题
				</a>
				<a href="${base}/admin/question/add.jhtml?questionType=blanks&materialID=${parentQuestion.id}&outlineCategoryId=${outlineCategoryId}" class="iconButton">
					<span class="addIcon">&nbsp;</span>添加填空题
				</a>
				<a href="${base}/admin/question/add.jhtml?questionType=essay&materialID=${parentQuestion.id}&outlineCategoryId=${outlineCategoryId}" class="iconButton">
					<span class="addIcon">&nbsp;</span>添加简答题
				</a>
				<a href="${base}/admin/question/add.jhtml?questionType=entry&materialID=${parentQuestion.id}&outlineCategoryId=${outlineCategoryId}" class="iconButton">
					<span class="addIcon">&nbsp;</span>添加分录题
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
		<a href="${base}/admin/question/questionList.jhtml" class="iconButton">
					<span class="backIcon">&nbsp;</span>${message("admin.common.back")}
				</a>
			</div>
		</div>	
	</div>
	<table id="listTable" class="list">
		<tr>
			<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
			<th>
				<span>父题干</span>
			</th>
			<th>
				<span>子题干</span>
			</th>
			<th>
				<span>难易度</span>
			</th>
			<th>
				<span>类型</span>
			</th>
			<th>
				<span>操作</span>
			</th>
		</tr>
		[#list questions as question]
			<tr>
				<td>
						<input type="checkbox" name="ids" value="${question.id}" />
					</td>
				<td title='${parentQuestion.stemToHtml}'>
					<span style="margin-left: 10px;">
						[#if parentQuestion.stemToHtml?length gt 20 ]
						 	${parentQuestion.stemToHtml?substring(0,20)}
					 	[#else]
						 	${parentQuestion.stemToHtml}
						[/#if]
					</span>
				</td>
				<td title='${question.stemToHtml}'>
					[#if question.stemToHtml?length gt 30 ]
						 ${question.stemToHtml?substring(0,30)}
					 [#else]
						 ${question.stemToHtml}
					[/#if]
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
					[#elseif question.questionType="entry"]
						<i>&nbsp;分录题</i>
					[#else]
						选择题<i>&nbsp;不定项</i>
					[/#if]
				</td>
				<td>
					<a href="editQuestion.jhtml?id=${question.id}&materialID=${parentQuestion.id}&outlineCategoryId=${outlineCategoryId}">[${message("admin.common.edit")}]</a>
				</td>
			</tr>
		[/#list]
	</table>
	</form>
</body>
</html>