<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.outlineCategory.add")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.treeSelect.js"></script>
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
	[@flash_message /]
	// 表单验证
$("#inputForm").validate({
		rules: {
			code: {
			   required: true,
			   remote:{
	               url:"chack_outlineCategory_code.jhtml",
	               cache: false
	             }
			},
			name: "required",
			order: "required"
		},
		messages: {
			code: {
				remote: "大纲编码已存在"
			}
		}
	});	
   $('#submitId').click(function() {
   		//是否选择了类别
		if($.trim($('#industryCategoryID').val())==""){
			$.message("warn","请选择行业");
			return false;
		}
		if($("select[name='industryCategoryID_select']").size()<2){
			$.message("warn","请选择2级行业");
			return false;
		}
		
		if($("select[name='industryCategoryID_select']").eq(1).val()==""){
				$.message("warn","请选择2级行业");
				return false;
		};
		$('#inputForm').submit();
	});
	
	//行业选择
	$('#industryCategoryID').treeSelect({
		url: "${base}/admin/outline_category/getIndustryCategoryChildren.jhtml"
	});
	
});

</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 添加大纲
	</div>
	<form id="inputForm" action="save.jhtml" method="post">
		<table class="input">
		<tr>
				<th>
					<span class="requiredField">*</span>所属行业:
				</th>
				<td>
					<input type="hidden" name="industryCategoryID" id="industryCategoryID" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>大纲编号:
				</th>
				<td>
					<input type="text" id="code" name="code"  class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>大纲名称:
				</th>
				<td>
					<input type="text" id="name" name="name" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					大纲描述:
				</th>
				<td>
					<input type="text" name="description" class="text"  maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					页面标题:
				</th>
				<td>
					<input type="text" name="seoTitle" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					页面关键字:
				</th>
				<td>
					<input type="text" name="seoKeywords" class="text"  maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					页面描述:
				</th>
				<td>
					<input type="text" name="seoDescription" class="text"  maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>排序:
				</th>
				<td>
					<input type="text" name="order" id="order" class="text" maxlength="9" />
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input id='submitId' type="button" class="button" value="${message("admin.common.submit")}" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='parentList.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>