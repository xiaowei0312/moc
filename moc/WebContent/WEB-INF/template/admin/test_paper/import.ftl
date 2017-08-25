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
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.lSelect.js"></script>

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
	var $display=false;
	//类别选择
	$('#outlineCategory').treeSelect({
		url: "${base}/admin/question/getOutlineCategoryChildren.jhtml"
	});
	// 地区选择
	$('#areaId').lSelect({
		url: "${base}/admin/common/area.jhtml",
		addressLimit:1
	});
	[@flash_message /]
	
	$.validator.addMethod("imageFileRequired", $.validator.methods.required,"请选择一个文件"); 
	 $.validator.addClassRules({
		xlsFile: {
			imageFileRequired: true,
			extension: "${setting.uploadFileExtension}"
		}
	});
	
	// 表单验证
	$inputForm.validate({
	
	});
	
	//类别选择
	$submitAdd.click(function(){
		if($("#outlineCategory").attr("value")==null ||$.trim($("#outlineCategory").attr("value"))==""){
			$.message("warn","请填写所属类别");
			return false;			
		} 
		if($("#name").attr("value")==null ||$.trim($("#name").attr("value"))==""){
			$.message("warn","请填写试卷名称");
			return false;			
		} 
		if($("input[name='testpaperType']:checked").val()==null ||$.trim($("input[name='testpaperType']:checked").val())==""){
			$.message("warn","请选择试卷类型");
			return false;			
		} 
		if($("#description").attr("value")==null ||$.trim($("#description").attr("value"))==""){
			$.message("warn","请填试卷说明");
			return false;			
		} 
		if($("#defineTime").attr("value")==null ||$.trim($("#defineTime").attr("value"))==""){
			$.message("warn","请填试时间限制");
			return false;			
		} 
		if($("#score").attr("value")==null ||$.trim($("#score").attr("value"))==""){
			$.message("warn","请填总分");
			return false;			
		} 
		if($("#score").attr("value")!=null && $.trim($("#score").attr("value"))!=""){
			var reg = new RegExp("^[0-9]*$");
			if(!reg.test($("#score").attr("value"))){
				$.message("warn","总分不能为非数字");
			    return false;	
			}
		}
		if($("#passedScore").attr("value")==null || $.trim($("#passedScore").attr("value"))==""){
			$.message("warn","请填通过分数");
			return false;			
		}
		if($("#passedScore").attr("value")!=null && $.trim($("#passedScore").attr("value"))!=""){
			var reg = new RegExp("^[0-9]*$");
			if(!reg.test($("#passedScore").attr("value"))){
				$.message("warn","通过分数不能为非数字");
			    return false;	
			};			
		}
		$inputForm.submit();
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 试卷导入
	</div>
	<form id="inputForm" action="${base}/admin/testpaper/saveImport.jhtml" method="post" enctype="multipart/form-data">
		<table class="input tabContent">
			<tr>
				<td colspan="4">
					<a href="${base}/resources/admin/download/testPaper.xls"  class="button">模板下载</a>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>试卷名称:
				</th>
				<td>
				<input type="text" id="name" name="name"  class="text" maxlength="200" style="width:600px;"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>试卷说明:
				</th>
				<td>
					<textarea id='description' name="description" style="width:600px;" rows="5"></textarea>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>试卷类型:
				</th>
				<td>
					<label>
						<input type="radio"  name="testpaperType" value="oldexam" class="requiredField" checked/>真题试卷
					</label>
					<label>
						<input type="radio"  name="testpaperType" value="munualsimulation" class="requiredField"/>模考试卷
					</label>
					<label>
						<input type="radio"  name="testpaperType" value="other" class="requiredField"/>其他试卷
					</label>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>所属类别:
				</th>
				<td>
					<input type="hidden" name="outlineCategoryId" id="outlineCategory"/> 
				</td>
			</tr>
			<tr>
				<th>
					所属区域(不选代表全国):
				</th>
				<td>
					<input type="hidden" id="areaId" name="areaId" />
				</td>
			</tr>
			<tr>
				<th>
					出卷年月:
				</th>
				<td>
				<input type="text" id="oldYearMonth" name="oldYearMonth"  size="10"  class="Wdate validate[required] form-textbox" onFocus="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true,readOnly:true})"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>时间限制:
				</th>
				<td>
				<input type="text" id="defineTime" name="defineTime" style="width:80px;" size="10"  class="Wdate validate[required] form-textbox" style="width:200px;" onFocus="WdatePicker({dateFmt:'HH:mm:ss',isShowClear:true,readOnly:true})" value="${defineTime}"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>试卷总分:
				</th>
				<td>
				<input type="text" id="score" name="score"  />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>通过分数:
				</th>
				<td>
				<input type="text" id="passedScore" name="passedScore"  />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>试卷文件:
				</th>
				<td>
					<label>
						<input type="file"  name="file" id="file" class="xlsFile" />
					</label>
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
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>