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
	var $submitAdd = $(".submitAdd");
	
	[@flash_message /]
	
	$submitAdd.click(function(){
		var continueAdd=$(this).attr("continueAdd");
		$("#continueAdd").val(continueAdd);
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
				order: "digits",
				parentId:"required"
			},
			messages: {
				code: {
					remote: "大纲编码已存在"
				},
				parentId:{
					required:"父大纲必须选择"
				}
			}
		});
	});
	
});
</script>
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 添加大纲
	</div>
	<form id="inputForm" action="save.jhtml" method="post">
		<input type="hidden" id="rootId" name="rootId" value="${rootId}" />
		<input type="hidden" id="industryCategoryID" name="industryCategoryID" value="${industryCategoryID}" />
		<input type="hidden" id="continueAdd" name="continueAdd" value=""/>
		<table class="input">
		<tr>
				<th>
					<span class="requiredField">*</span>父节点:
				</th>
				<td>
					<select id="parentId" name="parentId" >
						<option value="">请选择父节点...</option>
						[#list outlineCategoryTree as category]
							<option value="${category.id}" 
								[#if parentId?? && parentId!='']
									[#if category.id == parentId ] 
										selected 
									[/#if]
								[#else]
									[#if category.id == rootId ] 
										selected 
									[/#if]
								[/#if]
								>
								[#if category.grade != 0]
									[#list 1..category.grade as i]
										&nbsp;&nbsp;
									[/#list]
								[/#if]
								${category.name}
							</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>大纲编号:
				</th>
				<td>
					<input type="text" id='code' name="code" class="text"  maxlength="200" />
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
					&nbsp;
				</th>
				<td>
					<input type="submit" continueAdd="true" class="button submitAdd" value="保存并继续添加" />
					<input type="submit" continueAdd="false" class="button submitAdd" value="保存" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="history.back()" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>