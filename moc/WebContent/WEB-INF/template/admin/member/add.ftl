<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.member.add")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>

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
		addressLimit:2
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			username: {
				required: true,
				pattern: /^[0-9a-z_A-Z\u4e00-\u9fa5]+$/,
				minlength: ${setting.usernameMinLength},
				maxlength: ${setting.usernameMaxLength},
				remote: {
					url: "check_username.jhtml",
					cache: false
				}
			},
			name:{
			    required: true
			},
			birth:{
			    required: true
			},
			password: {
				required: true,
				pattern: /^[^\s&\"<>]+$/,
				minlength: ${setting.passwordMinLength},
				maxlength: ${setting.passwordMaxLength}
			},
			rePassword: {
				required: true,
				equalTo: "#password"
			},
			email: {
				required: true,
				email: true
				[#if !setting.isDuplicateEmail]
					,remote: {
						url: "check_email.jhtml",
						cache: false
					}
				[/#if]
				,pattern:/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
			},
			point: {
				required: true,
				digits: true
			},
			balance: {
				required: true,
				min: 0,
				decimal: {
					integer: 12,
					fraction: ${setting.priceScale}
				}
			}
		},
		messages: {
			username: {
				pattern: "${message("admin.validate.illegal")}",
				remote: "${message("admin.member.disabledExist")}"
			},
			password: {
				pattern: "${message("admin.validate.illegal")}"
			},
			email: {
				[#if !setting.isDuplicateEmail]	
						remote: "${message("admin.validate.exist")}",
				[/#if]
				pattern:"邮箱格式不正确"
			}
		}
	});

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.member.add")}
	</div>
	<form id="inputForm" action="save.jhtml" method="post">
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="${message("admin.member.base")}" />
			</li>
			<li>
				<input type="button" value="个人资料" />
			</li>
			<li>
				<input type="button" value="头像" />
			</li>
		</ul>
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Member.username")}:
				</th>
				<td>
					<input type="text" name="username" class="text" maxlength="20" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Member.password")}:
				</th>
				<td>
					<input type="password" id="password" name="password" class="text" maxlength="20" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("admin.member.rePassword")}:
				</th>
				<td>
					<input type="password" name="rePassword" class="text" maxlength="20" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Member.email")}:
				</th>
				<td>
					<input type="text" name="email" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Member.point")}:
				</th>
				<td>
					<input type="text" name="point" class="text" value="${setting.registerPoint}" maxlength="9" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Member.balance")}:
				</th>
				<td>
					<input type="text" name="balance" class="text" value="0" maxlength="16" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Member.memberRank")}:
				</th>
				<td>
					<select name="memberRankId">
						[#list memberRanks as memberRank]
							<option value="${memberRank.id}"[#if memberRank.isDefault] selected="selected"[/#if]>${memberRank.name}</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.common.setting")}:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isEnabled" value="true" checked="checked" />${message("Member.isEnabled")}
						<input type="hidden" name="_isEnabled" value="false" />
					</label>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>姓名:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="20" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>出生日期:
				</th>
				<td>
					<input type="text" name="birth" class="text Wdate" onfocus="WdatePicker();" />
				</td>
			</tr>
		</table>
		<table class="input tabContent">
			<tr>
               <th>
					<span class="requiredField"></span>性别:
		      </th>
	          <td>
	              <input type="radio" name="gender" value="male" />
	              <label>男</label>&nbsp;&nbsp;&nbsp;&nbsp;
	              <input type="radio" name="gender" value="female" />
	              <label>女</label>
	          </td>
            </tr>
			<tr>
				<th>
					<span class="requiredField"></span>地区:
				</th>
				<td>
					<input type="hidden" id="areaId" name="areaId" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField"></span>地址:
				</th>
				<td>
					<input type="text" id="address" name="address" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField"></span>手机:
				</th>
				<td>
					<input type="text" id="mobile" name="mobile" />
				</td>
			</tr>
				<tr>
				<th>
					<span class="requiredField"></span>公司:
				</th>
				<td>
					<input type="text" id="company" name="company" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField"></span>头衔:
				</th>
				<td>
					<input type="text" id="honor" name="honor" />
				</td>
			</tr>
				<tr>
				<th>
					<span class="requiredField"></span>个人签名:
				</th>
				<td>
					<input type="text" id="signature" name="signature" />
				</td>
			</tr>
				<tr>
				<th>
					<span class="requiredField"></span>自我介绍:
				</th>
				<td>
					<textarea rows="6" name="introduce"></textarea>
				</td>
			</tr>
				<tr>
				<th>
					<span class="requiredField"></span>个人主页:
				</th>
				<td>
					<input type="text" id="site" name="site" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField"></span>微博:
				</th>
				<td>
					<input type="text" id="weibo" name="weibo" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField"></span>微信:
				</th>
				<td>
					<input type="text" id="weixin" name="weixin" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField"></span>qq:
				</th>
				<td>
					<input type="text" id="qq" name="qq" />
				</td>
			</tr>
		</table>
		<table id="courseImageTable" class="input tabContent" style="text-align:center">
			<tr class="cent">
				<td >
					<span class="fieldSet">
						<input type="text" name="headImage" class="text" maxlength="200" readonly="readonly" />
						<input type="button" id="browserButton" class="button" value="${message("admin.browser.select")}" />
					</span>
				</td>
			</tr>
			<tr>
				<td style="align:center">
					<img src="${base}/resources/admin/images/folder_icon.gif" id="sourceImage" width="300px"/>
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