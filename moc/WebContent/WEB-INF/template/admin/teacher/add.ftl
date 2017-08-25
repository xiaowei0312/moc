<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.course.add")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/admin/css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/admin/ui/jquery-ui-datepicker.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $areaId = $("#areaId");
	var $browserButton = $("#browserButton");
	[@flash_message /]
	
	
	$browserButton.browser({callback: function(url){
		$("#sourceImage").attr("src",url);
	}});
	
	// 地区选择
	$areaId.lSelect({
		url: "${base}/admin/common/area.jhtml",
		choose:"你住哪啊？",
		addressLimit:2
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			courseCategoryId: "required",
			daysOfNotifyBeforeDeadline:"digits",
			title: "required",
			sourceImage:"required",
			truename: {
				required: true,
				pattern: /^[\u4E00-\u9FA5]{2,4}$/
			},
			idcard: {
				required: false,
				pattern: /^[1-9]\d{16}[\d|x|X]$/,
				remote: {
					url: "check_idcard.jhtml",
					cache: false
				}
			},
			qq: {
				required: false,
				pattern: /^[1-9]\\d{4,10}$/
			},
			mobile: {
				required: true,
				pattern: /(^((\(\d{3}\))|(\d{3}\-))?(1[358]\d{9})$)/,
				remote: {
					url: "check_mobile.jhtml",
					cache: false
				}
			}
		},
		messages:{
			truename:{
				pattern:"只能输入中文，至少两个字"
			},
			idcard:{
				pattern:"格式有误，请重新输入",
				remote:"该身份证号已被使用"
			},
			mobile:{
				pattern:"手机格式有误，请重新输入",
				remote:"该手机号已被使用"
			}
		}
	});
	//日期组件
	$("#date_2").prop("readOnly", true).datepicker({
		//navigationAsDateFormat: true,
		dateFormat: 'yy年mm月dd日',
		yearRange:'1900',
		minDate:'-100y',
		maxDate:'-6y'
	});
	
	//需写道调用这个组件的后边---也可以在样式中设置display
	$("#ui-datepicker-div").hide(); 
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 添加教师
	</div>
	<form onsubmit="javascript:return false" name="teacherForm" id="inputForm" action="save.jhtml" method="post" enctype="multipart/form-data">
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="${message("admin.course.base")}" />
			</li>
			<li>
				<input type="button" value="${message("admin.course.particular")}" />
			</li>
		</ul>
		<table class="input tabContent">
			<tr>
				<th>
					头像:
				</th>
				<td style="> 
  					<div id="showImage">
  						<img id="sourceImage" width=100 style='border:6px double #ccc' src="${base }/resources/admin/images/headDefault.jpg"/>
  					</div>
  					<br>
  					<span class="fieldSet">
						<input type="text" name="image" class="text" maxlength="200" readonly="readonly" />
						<input type="button" id="browserButton" class="button" value="${message("admin.browser.select")}" />
					</span>
  				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>姓名:
				</th>
				<td>
					<input type="text" name="truename" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					性别:
				</th>
				<td>
					<input type="radio" name="gender" value="male" />才子
					<input type="radio" name="gender" value="female" />才女
					<input type="radio" name="gender" value="secret" checked/>保密
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>身份证号:
				</th>
				<td>
					<input type="text" name="idcard" class="text" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>手机:
				</th>
				<td>
					<input type="text" name="mobile" class="text"/>
				</td>
			</tr>
			<tr>
				<th>
					学校:
				</th>
				<td>
					<input type="text" name="school" class="text" />
				</td>
			</tr>
			<tr>
				<th>
					工作:
				</th>
				<td>
					<input type="text" name="job" class="text"/>
				</td>
			</tr>
			<tr>
				<th>
					公司:
				</th>
				<td>
					<input type="text" name="company" class="text" />
				</td>
			</tr>
			<tr>
				<th>城市:</th>
				<td>
					<span class="fieldSet">
						<input type="hidden" id="areaId" name="city" />
					</span>
				</td>
			</tr>
		</table>
		<table class="input tabContent">
			<tr>
				<th>
					QQ:
				</th>
				<td>
					<input type="text" name="qq" class="text" />
				</td>
			</tr>
			<tr>
				<th>
					生日:
				</th>
				<td>
					<input type="text" name="birthday" id="date_2" class="text" />
				</td>
			</tr>
			<tr>
				<th>
					签名:
				</th>
				<td>
					<input type="text" name="signature" class="text" />
				</td>
			</tr>
			
			<tr>
				<th>个人简介:</th>
				<td>
					<textarea name="about" class="text" style="width: 60%;"></textarea>
				</td>
			</tr>
			
			<tr>
				<th>
					微信:
				</th>
				<td>
					<input type="text" name="weixin" class="text" />
				</td>
			</tr>
			<tr>
				<th>
					微博:
				</th>
				<td>
					<input type="text" name="weibo" class="text" />
				</td>
			</tr>
			<tr>
				<th>
					网站:
				</th>
				<td>
					<input type="text" name="site" class="text" />
				</td>
			</tr>
			<tr>
				<th>授课风格:</th>
				<td>
					<textarea name="teachingStyle" class="text" style="width: 60%;"></textarea>
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
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>