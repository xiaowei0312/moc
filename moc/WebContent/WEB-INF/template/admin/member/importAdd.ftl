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

<script type="text/javascript">
   $().ready(function() {
       	var fileIndex = 1;
       	var $inputForm = $("#inputForm");
       	var $productImageTable = $("#productImageTable");
	    var $addProductImage = $("#addProductImage");
		var $deleteProductImage = $("a.deleteProductImage");
		
		[@flash_message /]
	       	// 增加文件
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
		
		$.validator.addMethod("imageFileRequired", $.validator.methods.required,"请选择一个文件");   
	
		$.validator.addClassRules({
			productImageFile: {
				imageFileRequired: true,
				extension: "${setting.uploadFileExtension}"
			}
		});
		
		// 表单验证
		$inputForm.validate({
		
		});
	
   });
</script>
</head>
<body>
   <form id="inputForm" action="${base}/admin/memberImport/save.jhtml" method="post" enctype="multipart/form-data">

		<table id="productImageTable" class="input tabContent">
			<tr>
				<td colspan="4">
					<a href="javascript:;" id="addProductImage" class="button">添加文件</a>
					<a href="${base}/resources/admin/download/template1.xls"  class="button">模板下载</a>
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
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='${base}/admin/member/list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>