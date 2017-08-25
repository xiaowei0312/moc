<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.accoutCatalog.edit")} - Powered By Sram</title>
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
	                 url:"chack_accoutCatalog_code.jhtml?id=${accoutCatalog.id}",
	               cache: false
	             }
			},
			name: "required"
		},
		messages: {
			code: {
				remote: "会计账目编码已存在"
			}
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 会计账目编辑
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
		<input type="hidden" name="id" value="${accoutCatalog.id}" />
		<table class="input">
			<tr>
				<th>
					会计账目父节点:
				</th>
				<td>
					<select name="rootId" >
						<option value="">请选择父节点...</option>
						[#list accoutCatalogTree as category]
							[#if category != accoutCatalog ]
								<option value="${category.id}"[#if category == accoutCatalog.parent] selected="selected"[/#if]>
									[#if category.grade != 0]
										[#list 1..category.grade as i]
											&nbsp;&nbsp;
										[/#list]
									[/#if]
									${category.name}
								</option>
							[/#if]
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>会计账目编号:
				</th>
				<td>
					<input type="text" id="code" name="code" class="text" value="${accoutCatalog.code}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>会计账目名称:
				</th>
				<td>
					<input type="text" id="name" name="name" class="text" value="${accoutCatalog.name}" maxlength="200" />
				</td>
			</tr>
			
			<tr>
				<th>
					会计账目描述:
				</th>
				<td>
					<input type="text" name="description" class="text" value="${accoutCatalog.description}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input id='submitId' type="submit" class="button" value="${message("admin.common.submit")}"  />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="history.back()" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>