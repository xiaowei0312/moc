<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.productCategory.add")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
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
	var $inputForm = $("#inputForm");
	
	$inputForm.validate({
		rules: {
			title:"required"
		}
	});
})
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 添加章节
	</div>
	<form id="inputForm" action="save.jhtml" method="post">
		<input type="hidden"  name="type" value="chapter" />
		<input type="hidden"  name="course.id" value="${courseId}" />
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>章节名称:
				</th>
				<td>
					<input type="text" id="title" name="title" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
			   <th>
					概述:
				</th>
				<td>
					<textarea onKeyUp="if(this.value.length > 300){ this.value=this.value.substr(0,300); alert('最多500个字符');}" rows="6" name="summary" style="width:500px;"></textarea>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("admin.common.submit")}" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="history.back()" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>