<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加类别</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link rel="stylesheet" type="text/css" href="${base}/resources/admin/css/common.css"  />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
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
	
	var $checkboxLimit = $(":checkbox");
	
	$checkboxLimit.click(function(){
		var $this = $(this);
		var $limitInput=$this.closest("tr").find("input[type='text']");
		if($this.prop("checked")){
			
			//加上限
			$limitInput.removeAttr("disabled");
		}else{
			$limitInput.attr("disabled","disabled");
		}
	});

	// 表单验证
	$inputForm.validate({
		rules: {
			name: "required",
			order: "digits"
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">首页</a> &raquo; 编辑
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
	<input type="hidden" name="id" value="${integralRule.id}"/>
		<table class="input">
			<tr>
				<th>
					名称:
				</th>
				<td>
					<input type="text" name="name" class="text" readonly value="${integralRule.name}" />
				</td>
			</tr>
			<tr>
				<th>
					数量:
				</th>
				<td>
					<input type="text" name="taskCount" class="text" readonly value="${integralRule.taskCount}" />
				</td>
			</tr>
			<tr>
				<th>
					任务类型:
				</th>
				<td>
					<input type="hidden" name="taskType" value="${integralRule.taskType}" />
					[#if integralRule.taskType=="newTask"]
							新手任务
						[#elseif integralRule.taskType=="dailyTask"]
							日常任务
						[#elseif integralRule.taskType=="overallTask"]
							全局任务
					[/#if]
				</td>
			</tr>
			<tr>
				<th>
					可获经验值:
				</th>
				<td>
					<input type="text" class="text" name="experienceValue" value="${integralRule.experienceValue}"/>
				</td>
			</tr>
			<tr>
				<th>
					每日经验值上限<input type="checkbox" name="isSetEvDailyLimit" 
						[#if integralRule.isSetEvDailyLimit]
							value="true"
							checked
						[#else]
							value="false"
						[/#if]/>:
				</th>
				<td>
					<input type="text" name="evDailyLimit" [#if !integralRule.isSetEvDailyLimit] disabled [/#if]value="${integralRule.evDailyLimit}" class="text" />
				</td>
			</tr>
			<tr>
				<th>
					可获学币:
				</th>
				<td>
					<input type="text" class="text" name="learningCoin" value="${integralRule.learningCoin}"/>
				</td>
			</tr>
			<tr>
				<th>
					每日学币上限<input type="checkbox" name="isSetLcDailyLimit"
						[#if integralRule.isSetLcDailyLimit]
							value="true"
							checked
						[#else]
							value="false"
						[/#if]/>:
				</th>
				<td>
					<input type="text"  name="lcDailyLimit" [#if !integralRule.isSetLcDailyLimit] disabled [/#if]value="${integralRule.lcDailyLimit}" class="text" />
				</td>
			</tr>
			<tr>
				<th>
					描述
				</th>
				<td>
					<textarea name="description">${integralRule.description}</textarea>
				</td>
			</tr>
			<tr>
				<th>
					PC拦截路径
				</th>
				<td>
					<input name="urlPath" title="不能修改"  type="text" class="text" readonly value="${integralRule.urlPath}" />
				</td>
			</tr>
			<tr>
				<th>
					APP拦截路径
				</th>
				<td>
					<input name="appUrlPath" title="不能修改" type="text" class="text" readonly value="${integralRule.appUrlPath}" />
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="保存" />
					<input type="button" class="button" value="返回" onclick="history.back();" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>