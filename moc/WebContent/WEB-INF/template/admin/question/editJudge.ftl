<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.product.add")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.treeSelect.js"></script>
<script type="text/javascript">
if(typeof(KindEditor) != "undefined") {
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="stem"]', {
			height: "350px",
			width:"80%",
			items:["bold", "italic","forecolor","underline","|","insertorderedlist","insertunorderedlist"
					,"|","link","unlink","|","removeformat","source","image"
					],
			langType: sram.locale,
					syncType: "form",
					filterMode: false,
					pagebreakHtml: '<hr class="pageBreak" \/>',
					allowFileManager: true,
					filePostName: "file",
					fileManagerJson: sram.base + "/admin/file/browser.jhtml",
					uploadJson: sram.base + "/admin/file/upload.jhtml",
					uploadImageExtension: setting.uploadImageExtension,
					uploadFlashExtension: setting.uploadFlashExtension,
					uploadMediaExtension: setting.uploadMediaExtension,
					uploadFileExtension: setting.uploadFileExtension,
					extraFileUploadParams: {
						token: getCookie("token")
					},
			afterChange: function() {
				this.sync();
			}
		});
	});
}
</script>
<style type="text/css">
	.specificationSelect {
		height: 100px;
		padding: 5px;
		overflow-y: scroll;
		border: 1px solid #cccccc;
	}
	
	.specificationSelect li {
		float: left;
		min-width: 150px;
		_width: 200px;
	}
</style>
<script type="text/javascript">
$().ready(function(){
	var $outlineCategory = $("#outlineCategory");
	var $submitAdd = $(".submitAdd");
	var $inputForm =$("#inputForm");
	var $advanceChoice = $("#advanceChoice");
	
	[@flash_message /]
	//类别选择
	$outlineCategory.treeSelect({
		url: "${base}/admin/question/getOutlineCategoryChildren.jhtml"
	});
	
	$submitAdd.click(function(){
		$("#questionType").attr("value","determine");
		$inputForm.submit();
	});
	//高级选项--答案解析及分值
	$advanceChoice.toggle(
		function(){
			$(".analysis").fadeIn();
			KindEditor.create('textarea[name="analysis"]', {
				items:["bold", "italic","forecolor","underline","|","insertorderedlist","insertunorderedlist"
				,"|","link","unlink","|","removeformat","source"
				],
				afterBlur: function(){this.sync();}
			});
		},
		function(){
			$(".analysis").fadeOut();
		}
		
	);
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a>[#if materialID??] &raquo; 编辑材料子题[/#if] &raquo; 编辑判断题
	</div>
	<form id="inputForm" action="update.jhtml" method="post" enctype="multipart/form-data">
	<input type="hidden" name="questionType" id="questionType" />
	<input type="hidden" name="submitType" id="submitType" />
	<input type="hidden" name="id" value="${question.id}" />
	[#if materialID?? && materialID!=0]
	 	<input type="hidden" name="parent.id" value="${question.parent.id}"/>
	 [/#if]
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>类别:
				</th>
				<td>
				    <input type="hidden" name="materialID" value="${materialID}" />
					<input type="hidden" name="outlineCategoryId" id="outlineCategory" 
					[#if question.outlineCategory??]
					 		value="${question.outlineCategory.id}"
							treePath="${question.outlineCategory.treePath}"
					 [/#if]
					 />
				</td>
			</tr>
			<tr>
				<th>
					难度:
				</th>
				<td>
					<label>
						<input type="radio"  name="difficulty" value="easy" 
						[#if question.difficulty="easy"]checked[/#if]/>简单
					</label>
					<label>
						<input type="radio"  name="difficulty" value="normal"
						 [#if question.difficulty="normal"]checked[/#if]/>一般
					</label>
					<label>
						<input type="radio"  name="difficulty" value="hard" 
						[#if question.difficulty="hard"]checked[/#if]/>困难
					</label>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>分数:
				</th>
				<td>
					<input type="text" name="score" id="score" value="${question.score}"></input>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>题干:
				</th>
				<td>
					<textarea name="stem">${question.stem}</textarea>
				</td>
			</tr>
			<tr>
				<th>答案:</th>
				<td>
					<input type="radio" name="answer" value="true" 
					[#if question.answer="true"]checked[/#if]/>正确
					<input type="radio" name="answer"value="false"
					[#if question.answer="false"]checked[/#if]/>错误
				</td>
			</tr>
		</table>
		<table class="input">
			<tr>
				<th>&nbsp;&nbsp;</th>
				<td><a href="javascript:;" id="advanceChoice" class="blue">&raquo;显示/隐藏 高级选项 ...</a></td>
			</tr>
			<tr>
				<th><span class="analysis hidden">解析:</span></th>
				<td>
					<div class="analysis hidden">
					<textarea name="analysis">${question.analysis}</textarea>
					</div>
				</td>
			</tr>
		</table>
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="button" continueAdd="false" class="button submitAdd" value="确 定" />
					[#if materialID=0 || !(materialID??)]
						<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='questionList.jhtml'" />
					[#else]
						<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='materialChildrenList.jhtml?id=${materialID}&outlineCategoryId=${outlineCategoryId}'" />
					[/#if]
				</td>
			</tr>
		</table>
	</form>
</body>
</html>