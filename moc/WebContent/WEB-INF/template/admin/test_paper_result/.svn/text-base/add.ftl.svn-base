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
	//类别选择
	$('#outlineCategory').treeSelect({
		url: "${base}/admin/question/getOutlineCategoryChildren.jhtml"
	});
	// 地区选择
	$('#areaId').lSelect({
		url: "${base}/admin/common/area.jhtml",
		addressLimit:2
	});
	[@flash_message /]
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
		if($("#description").attr("value")==null ||$.trim($("#description").attr("value"))==""){
			$.message("warn","请填试卷说明");
			return false;			
		} 
		if($("#limitedTime").attr("value")==null ||$.trim($("#limitedTime").attr("value"))==""){
			$.message("warn","请填试时间限制");
			return false;			
		}
		if($("#areaId").attr("value")==null ||$.trim($("#areaId").attr("value"))==""){
			$.message("warn","请填所属区域");
			return false;			
		} 
		if($("#score").attr("value")==null ||$.trim($("#score").attr("value"))==""){
			$.message("warn","请填总分");
			return false;			
		} 
		if($("#passedScore").attr("value")==null ||$.trim($("#passedScore").attr("value"))==""){
			$.message("warn","请填通过分数");
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
	<form id="inputForm" action="save.jhtml" method="post" enctype="multipart/form-data">
		<table class="input tabContent">
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
					<span class="requiredField">*</span>所属区域:
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
					<span class="requiredField">*</span>试卷名称:
				</th>
				<td>
				<input type="text" id="name" name="name"  class="text" maxlength="200"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>试卷说明:
				</th>
				<td>
					<textarea id='description' name="description"></textarea>
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
					<span class="requiredField">*</span>时间限制(秒):
				</th>
				<td>
				<input type="text" id="limitedTime" name="limitedTime"  />
				</td>
			</tr>
			<tr>
				<th>
					试卷类型:
				</th>
				<td>
					<label>
						<input type="radio"  name="testpaperType" value="oldexam" checked />真题试卷
					</label>
					<label>
						<input type="radio"  name="testpaperType" value="munualsimulation" />手工试卷
					</label>
					<label>
						<input type="radio"  name="testpaperType" value="other" />其他试卷
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
				<th>
					&nbsp;
				</th>
				<td>
					<input type="button" continueAdd="true" class="button submitAdd" value="保存并继续添加" />				
					<input type="button" continueAdd="false" class="button submitAdd" value="保存" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>