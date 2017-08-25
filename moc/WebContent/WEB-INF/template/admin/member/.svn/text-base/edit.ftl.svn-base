<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.member.edit")} - Powered By Sram</title>
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
			birth:{
			    required: true
			},
			name:{
			    required: true
			},
			password: {
				pattern: /^[^\s&\"<>]+$/,
				minlength: ${setting.passwordMinLength},
				maxlength: ${setting.passwordMaxLength}
			},
			rePassword: {
				equalTo: "#password"
			},
			email: {
				required: true,
				email: true
				[#if !setting.isDuplicateEmail]
					,remote: {
						url: "check_email.jhtml?previousEmail=${member.email?url}",
						cache: false
					}
				[/#if]
			},
			idCard:{
			      required: false,
				  pattern: /^[1-9]\d{16}[\d|x|X]$/
			},
			modifyPoint: {
				integer: true,
				min: -${member.point}
			},
			modifyBalance: {
				min: -${member.balance},
				decimal: {
					integer: 12,
					fraction: ${setting.priceScale}
				}
			}
		},
		messages: {
			password: {
				pattern: "${message("admin.validate.illegal")}"
			}
			[#if !setting.isDuplicateEmail]
				,email: {
					remote: "${message("admin.validate.exist")}"
				}
			[/#if]
		}
	});

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.member.edit")}
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
		<input type="hidden" name="id" value="${member.id}" />
		<input type="hidden" name="memberRank.id" value="${member.memberRank.id}"/>
		<input type="hidden" name="salt" value="${member.salt}"/>
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="${message("admin.member.base")}" />
			</li>
			<li>
				<input type="button" value="个人中心" />
			</li>
			<li>
				<input type="button" value="${message("admin.member.point")}" />
			</li>
			<li>
				<input type="button" value="${message("admin.member.deposit")}" />
			</li>
			<li>
				<input type="button" value="头像" />
			</li>
		</ul>
		<table class="input tabContent">
			<tr>
				<th>
					${message("Member.username")}:
				</th>
				<td>
					${member.username}
				</td>
			</tr>
			<tr>
				<th>
					${message("Member.password")}:
				</th>
				<td>
					<input type="password" id="password" name="password" class="text" maxlength="20" title="${message("admin.member.passwordTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.member.rePassword")}:
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
					<input type="text" name="email" class="text" value="${member.email}" maxlength="200" />
				</td>
			</tr>
				<tr>
				<th>
					身份证号:
				</th>
				<td>
				    <input type="text" name="idCard" class="text" value="${member.idCard}"/>	
				</td>
			</tr>
			<tr>
				<th>
					${message("Member.memberRank")}:
				</th>
				<td>
					<select name="memberRankId">
						[#list memberRanks as memberRank]
							<option value="${memberRank.id}"[#if memberRank == member.memberRank] selected="selected"[/#if]>${memberRank.name}</option>
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
						<input type="checkbox" name="isEnabled" value="true" [#if member.isEnabled] checked="checked"[/#if] />${message("Member.isEnabled")}
						<input type="hidden" name="_isEnabled" value="false" />
					</label>
					[#if member.locked]
						<label>
							<input type="checkbox" name="isLocked" value="true" checked="checked" />${message("Member.isLocked")}
							<input type="hidden" name="_isLocked" value="false" />
						</label>
					[/#if]
				</td>
			</tr>
			<tr>
				<th>
					${message("Member.amount")}:
				</th>
				<td>
					${currency(member.amount, true)}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.common.createDate")}:
				</th>
				<td>
					${member.createDate?string("yyyy-MM-dd HH:mm:ss")}
				</td>
			</tr>
			<tr>
				<th>
					${message("Member.registerIp")}:
				</th>
				<td>
					${member.registerIp}
				</td>
			</tr>
		    <tr>
				<th>
					<span class="requiredField">*</span>姓名:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="20" value="${member.name}"/>
				</td>
			</tr>
		    <tr>
               <th>
					<span class="requiredField">*</span>出生日期:
		      </th>
		      <td>
		          <input type="text" name="birth" class="text Wdate" onfocus="WdatePicker();" value="${member.birth}"/>
		      </td>
            </tr>
		</table>
		<table class="input tabContent">
			<tr>
               <th>
					<span class="requiredField"></span>性别:
		      </th>
	          <td>
	              <input type="radio" name="gender" value="male" [#if member.gender="male"] checked [/#if]/>
	              <label>男</label>&nbsp;&nbsp;&nbsp;&nbsp;
	              <input type="radio" name="gender" value="female" [#if member.gender="female"] checked [/#if]/>
	              <label>女</label>
	          </td>
            </tr>
		    <tr>
				<th>
					地区:
				</th>
				<td>
				    <span class="fieldSet">
						<input type="hidden" id="areaId" name="areaId" value="${(member.area.id)!}" treePath="${(member.area.treePath)!}" />
					</span>	
				</td>
		   </tr>
		    <tr>
               <th>
					<span class="requiredField"></span>地址:
		      </th>
		      <td>
		           <input type="text" name="address" class="text" maxlength="20" value="${member.address}"/>
		      </td>
		    </tr>
		     <tr>
               <th>
					<span class="requiredField"></span>手机号:
		      </th>
		      <td>
		           <input type="text" name="mobile" class="text" maxlength="20" value="${member.mobile}"/>
		      </td>
		    </tr>
		    <tr>
				<th>
					<span class="requiredField"></span>公司:
				</th>
				<td>
					<input type="text" id="company" name="company" value="${member.company}"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField"></span>头衔:
				</th>
				<td>
					<input type="text" id="honor" name="honor" value="${member.honor}"/>
				</td>
			</tr>
				<tr>
				<th>
					<span class="requiredField"></span>个人签名:
				</th>
				<td>
					<input type="text" id="signature" name="signature" value="${member.signature}"/>
				</td>
			</tr>
				<tr>
				<th>
					<span class="requiredField"></span>自我介绍:
				</th>
				<td>
					<textarea rows="6" name="introduce">${member.introduce}</textarea>
				</td>
			</tr>
				<tr>
				<th>
					<span class="requiredField"></span>个人主页:
				</th>
				<td>
					<input type="text" id="site" name="site" value="${member.site}"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField"></span>微博:
				</th>
				<td>
					<input type="text" id="weibo" name="weibo" value="${member.weibo}"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField"></span>微信:
				</th>
				<td>
					<input type="text" id="weixin" name="weixin" value="${member.weixin}"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField"></span>qq:
				</th>
				<td>
					<input type="text" id="qq" name="qq" value="${member.qq}"/>
				</td>
			</tr>
		</table>
		<table class="input tabContent">
			<tr>
				<th>
					${message("admin.member.currentPoint")}:
				</th>
				<td>
					${member.point}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.member.modifyPoint")}:
				</th>
				<td>
					<input type="text" name="modifyPoint" class="text" maxlength="9" title="${message("admin.member.modifyPointTitle")}" />
				</td>
			</tr>
		</table>
		<table class="input tabContent">
			<tr>
				<th>
					${message("admin.member.currentBalance")}:
				</th>
				<td>
					${currency(member.balance, true)}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.member.modifyBalance")}:
				</th>
				<td>
					<input type="text" name="modifyBalance" class="text" maxlength="16" title="${message("admin.member.modifyBalanceTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.member.depositMemo")}:
				</th>
				<td>
					<input type="text" name="depositMemo" class="text" maxlength="200" />
				</td>
			</tr>
		</table>
			<table id="courseImageTable" class="input tabContent" style="text-align:center">
			<tr class="cent">
				<td >
					<span class="fieldSet">
						<input type="text" name="headImage" class="text" maxlength="200" readonly="readonly" value="${member.headImage}"/>
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