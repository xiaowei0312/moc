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
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $productCategoryId = $("#productCategoryId");
	var $productImageTable = $("#productImageTable");
	var $addProductImage = $("#addProductImage");
	var fileIndex = 1;
	var $outlineCategory = $("#outlineCategory");
	var $deleteProductImage = $("a.deleteProductImage");
	
	[@flash_message /]
	
	var previousProductCategoryId = getCookie("previousProductCategoryId");
	if (previousProductCategoryId != null) {
		$productCategoryId.val(previousProductCategoryId);
	} else {
		previousProductCategoryId = $productCategoryId.val();
	}
	
	// 增加商品图片
	$addProductImage.click(function() {
		[@compress single_line = true]
			var trHtml = 
			'<tr>
				<td>
					<input type="file" name="file[' + fileIndex + ']" class="productImageFile" \/>
				<\/td>
				<td>
					<a href="javascript:;" class="deleteProductImage">[${message("admin.common.delete")}]<\/a>
				<\/td>
			<\/tr>';
		[/@compress]
		$productImageTable.append(trHtml);
		fileIndex ++;
	});
	
	$.validator.addMethod("imageFileRequired", $.validator.methods.required,"请选择一个文件");   
	
	$.validator.addClassRules({
		productImageFile: {
			imageFileRequired: true,
			extension: "${setting.uploadFileExtension}"
		}
	});
	
    // 删除文件
	$deleteProductImage.live("click", function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("admin.dialog.deleteConfirm")}",
			onOk: function() {
				$this.closest("tr").remove();
			}
		});
	});
	
	// 表单验证
	$inputForm.validate({
	
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 题目导入
	</div>
	<form id="inputForm" action="${base}/admin/questionImport/save.jhtml" method="post" enctype="multipart/form-data">

		<table id="productImageTable" class="input tabContent">
			<tr>
				<td colspan="4">
					<input type="hidden" name="materialID" id="materialID" value="${materialID}"/>
					<input type="hidden" name="outlineCategoryId" id="outlineCategory" 
					 [#if outlineCategory??]
					 		value="${outlineCategory.id}"
							treePath="${outlineCategory.treePath}"
					 [/#if]
					 />
					<a href="javascript:;" id="addProductImage" class="button">添加文件</a>
					<a href="${base}/resources/admin/download/template.xls"  class="button">模板下载</a>
				</td>
			</tr>
			<tr class="title">
				<td>
					${message("ProductImage.file")}
				</td>
				<td>
					操作
				</td>
			</tr>
			<tr>
				<td>
					<input type="file" name="file[0]" class="productImageFile" />
				</td>
				<td>
					
				</td>
			</tr>
		</table>
	
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("admin.common.submit")}" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>