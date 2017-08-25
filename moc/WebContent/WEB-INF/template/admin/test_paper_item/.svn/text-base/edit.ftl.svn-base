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
			height: "150px",
			width:"30%",
			items:["bold", "italic","forecolor","underline","|","insertorderedlist","insertunorderedlist"
					,"|","link","unlink","|","removeformat","source","image","squareBrackets"
					],
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
	var $submitAdd = $(".submitAdd");
	var $inputForm =$("#inputForm");
	
	[@flash_message /]
//类别选择
	$submitAdd.click(function(){
		if($("#name").attr("value")==null ||$.trim($("#name").attr("value"))==""){
			$.message("warn","请填写试卷名称");
			return false;			
		} 
		if($("#description").attr("value")==null ||$.trim($("#description").attr("value"))==""){
			$.message("warn","请填试卷说明");
			return false;			
		} 
		if($("#limitedTime").attr("value")==null ||$.trim($("#limitedTime").attr("value"))==""){
			$.message("warn","请填试时间限制");
			return false;			
		} 
		$inputForm.submit();
	});
	
	//高级选项--答案解析及分值
	$("#advanceChoice").toggle(
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
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 创建试卷
	</div>
	<form id="inputForm" action="update.jhtml" method="post" enctype="multipart/form-data">
	<input type="hidden" name="submitType" id="submitType" />
	<input type="hidden" name="questionType" id="questionType" value="blanks"/>
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>试卷名称:
				</th>
				<td>
				<input type="text" id="name" name="name"  class="text" value="${testpaper.name}" maxlength="200"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>试卷说明:
				</th>
				<td>
					<textarea name="description">${testpaper.description}</textarea>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>时间限制:
				</th>
				<td>
				<input type="text" id="limitedTime" name="limitedTime" class="text" value="${testpaper.limitedTime}" />
				</td>
			</tr>
			<tr>
				<th>
					生成方式:
				</th>
				<td>
					<label>
						<input type="radio"  name="difficulty" value="easy" checked />生成方式
					</label>
					<label>
						<input type="radio"  name="difficulty" value="normal" />按难易程度
					</label>
				</td>
			</tr>
			<tr>
				<th>
					出题范围:
				</th>
				<td>
					<label>
						<input type="radio"  name="difficulty2" value="easy" checked />出题范围
					</label>
					<label>
						<input type="radio"  name="difficulty2" value="normal" />按课时范围
					</label>
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
					<textarea name="analysis"></textarea>
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
					<input type="button" continueAdd="false" class="button submitAdd" value="保存" />
					<input type="button"  value="${message("admin.common.back")}" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>