<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.outlineCategory.edit")} - Powered By Sram</title>
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
	
	[@flash_message /]
	// 表单验证
	$("#inputForm").validate({
		rules: {
			code: {
			   required: true,
			   remote:{
	               url:"chack_outlineCategory_code.jhtml?id=${outlineCategory.id}",
	               cache: false
	             }
			},
			name: "required"
		},
		messages: {
			code: {
				remote: "大纲编码已存在"
			}
		}
	});
	  $('#submitId').click(function() {
   		if($('#parentId').val()==$('#id').val()){
   			$.message("warn","大纲父节点不能是自己。");
   			return false;
   		}
   		var flag=false;
   		$.ajax( {
				type : "POST",
				url : "cheackParetTreePath.jhtml",
				data : {"parentId":$('#parentId').val()},
				dataType:"text",
				async:false,
				success : function(msg) {
					if(msg.indexOf(","+$('#id').val()+",")>0){
						flag=true;
					}else{
						flag=false;
					}
				}
		});
   		
   		if(flag){
   			$.message("warn","大纲父节点不能是自己的子大纲。");
   			return false;
   		}
		$('#inputForm').submit();
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 大纲编辑
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
		<input type="hidden" name="id" id="id" value="${outlineCategory.id}" />
		<input type="hidden" name="rootId" value="${rootId}" />
		<input type="hidden" name="order" id="order"  value="${outlineCategory.order}" />
		<table class="input">
			<tr>
				<th>
					大纲父节点:
				</th>
				<td>
					<select name="parentId" id="parentId">
						<option value="">请选择父节点...</option>
						[#list outlineCategoryTree as category]
								<option value="${category.id}"[#if category == outlineCategory.parent] selected="selected"[/#if]>
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
					<input type="text" id="code" name="code" class="text" value="${outlineCategory.code}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>大纲名称:
				</th>
				<td>
					<input type="text" id="name" name="name" class="text" value="${outlineCategory.name}" maxlength="200" />
				</td>
			</tr>
			
			<tr>
				<th>
					大纲描述:
				</th>
				<td>
					<input type="text" name="description" class="text" value="${outlineCategory.description}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					页面标题:
				</th>
				<td>
					<input type="text" name="seoTitle" class="text" value="${outlineCategory.seoTitle}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					页面关键字:
				</th>
				<td>
					<input type="text" name="seoKeywords" class="text" value="${outlineCategory.seoKeywords}" maxlength="200" />
				</td>
			</tr>
			
			<tr>
				<th>
					页面描述:
				</th>
				<td>
					<input type="text" name="seoDescription" class="text" value="${outlineCategory.seoDescription}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input id='submitId' type="button" class="button" value="${message("admin.common.submit")}"  />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="history.back()" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>