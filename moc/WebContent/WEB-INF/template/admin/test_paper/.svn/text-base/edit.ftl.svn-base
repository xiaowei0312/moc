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
		addressLimit:1
	});
	
	[@flash_message /]
//类别选择
	$submitAdd.click(function(){
		 
		if($("#outlineCategory").attr("value")==null ||$.trim($("#outlineCategory").attr("value"))==""){
			$.message("warn","请填写大纲名称");
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
		if($("#passedScore").attr("value")==null ||$.trim($("#passedScore").attr("value"))==""){
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
		if($("#defineTime").attr("value")==null ||$.trim($("#defineTime").attr("value"))==""){
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
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo;
		[#if testpaper.testpaperType=='oldexam' || testpaper.testpaperType=='munualsimulation']
			 修改试卷
		[#else]
			查看试卷
		[/#if]
		 
	</div>
	<form id="inputForm" action="update.jhtml" method="post" enctype="multipart/form-data">
	<input type="hidden" name="id" value="${testpaper.id}"/>
	[#if testpaper.createAdmin??]
	    <input type="hidden" name="createAdmin.id" value="${testpaper.createAdmin.id}"/>
	[/#if]
	[#if testpaper.createMember??]
		<input type="hidden" name="createMember.id" value="${testpaper.createMember.id}"/>
	[/#if]
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>所属类别:
				</th>
				<td>
					<input type="hidden" name="outlineCategoryId" id="outlineCategory"  value="${(testpaper.outlineCategory.id)!}" treePath="${(testpaper.outlineCategory.treePath)!}"/> 
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>所属区域:
				</th>
				<td>
					<input type="hidden" id="areaId" name="areaId" value="${(testpaper.area.id)!}" treePath="${(testpaper.area.treePath)!}"/>
				</td>
			</tr>
			<tr>
				<th>
					出卷年月:
				</th>
				<td>
				<input type="text" id="oldYearMonth" name="oldYearMonth" style="width:80px;" value="${testpaper.oldYearMonth}" size="10"  class="Wdate validate[required] form-textbox" onFocus="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true,readOnly:true})"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>试卷名称:
				</th>
				<td>
				<input type="text" id="name" name="name"  class="text" value="${testpaper.name}" maxlength="200" style="width:600px;"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>试卷说明:
				</th>
				<td>
					<textarea  name="description" id="description" rows="5" style="width:600px;">${testpaper.description}</textarea>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>试卷总分:
				</th>
				<td>
				<input type="text" id="score" name="score" value="${testpaper.score}" style="width:50px;"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>通过分数:
				</th>
				<td>
				<input type="text" id="passedScore" name="passedScore" value="${testpaper.passedScore}" style="width:50px;"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>时间限制:
				</th>
				<td>
				<input type="text" id="defineTime" style="width:80px;" name="defineTime"  size="10"  class="Wdate validate[required] form-textbox" style="width:200px;" onFocus="WdatePicker({dateFmt:'HH:mm:ss',isShowClear:true,readOnly:true})" value="${defineTime}"/>
				</td>
			</tr>
			<tr>
				<th>
					试卷类型:
				</th>
				<td>
					<label>
						<input type="radio"  name="testpaperType" value="oldexam" [#if testpaper.testpaperType=="oldexam"] checked [/#if]  />真题试卷
					</label>
					<label>
						<input type="radio"  name="testpaperType" value="munualsimulation" [#if testpaper.testpaperType=="munualsimulation"] checked [/#if]  />模考试卷
					</label>
					<label>
						<input type="radio"  name="testpaperType" value="other" [#if testpaper.testpaperType=="other"] checked [/#if]  />其他试卷
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
					[#if testpaper.testpaperType=='oldexam' || testpaper.testpaperType=='munualsimulation']
						<input type="button" continueAdd="false" class="button submitAdd" value="保存" />
					[/#if]
					<input type="button"  class="button" value="${message("admin.common.back")}" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>