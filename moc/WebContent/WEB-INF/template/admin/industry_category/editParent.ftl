<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.industryCategory.edit")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<style type="text/css">
.brands label {
	width: 150px;
	display: block;
	float: left;
	padding-right: 6px;
}
</style>
<script type="text/javascript">
$().ready(function() {
	var $browserButton = $("#browserButton");
	var $inputForm = $("#inputForm");
	
	[@flash_message /]
	$browserButton.browser({callback: function(url){
		$("#sourceImage").attr("src",url);
	}});
	// 表单验证
	$inputForm.validate({
		rules: {
			name: "required",
			order: "digits"
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 大纲编辑
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="${message("admin.member.base")}" />
			</li>
			<li>
				<input type="button" value="行业图片" />
			</li>
		</ul>
		<table class="input tabContent">
		<input type="hidden" name="id" value="${industryCategory.id}" />
		<input type="hidden" id="rootId" name="rootId" value="${rootId}" />
			<tr>
				<th>
					<span class="requiredField">*</span>行业名称:
				</th>
				<td>
					<input type="text" id="name" name="name" class="text" value="${industryCategory.name}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					行业描述:
				</th>
				<td>
					<input type="text" name="description" class="text" value="${industryCategory.description}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					内容排序:
				</th>
				<td>
					<input type="text" name="order" class="text" value="${industryCategory.order}" maxlength="9" />
				</td>
			</tr>
		</table>
		<table id="courseImageTable" class="input tabContent" style="text-align:center">
			<tr class="cent">
				<td >
					<span class="fieldSet">
						<input type="text" name="image" class="text" value='${industryCategory.image}' maxlength="200" readonly="readonly" />
						<input type="button" id="browserButton" class="button" value="${message("admin.browser.select")}" />
					</span>
				</td>
			</tr>
			<tr>
				<td style="align:center">
					<img src="${base}/resources/admin/images/folder_icon.gif" id="sourceImage" width="300px"/>
				</td>
			</tr>
			
		</table>
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("admin.common.submit")}"  />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="history.back()" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>