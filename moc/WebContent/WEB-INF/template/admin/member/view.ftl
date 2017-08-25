<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.member.view")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>

<script type="text/javascript">
$().ready(function() {
   var $areaId = $("#areaId");
   
   // 地区选择
	$areaId.lSelect({
		url: "${base}/admin/common/area.jhtml",
		addressLimit:2
	});
});

</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.member.view")}
	</div>
	<ul id="tab" class="tab">
		<li>
			<input type="button" value="${message("admin.member.base")}" />
		</li>
		<li>
			<input type="button" value="个人中心" />
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
				${message("Member.email")}:
			</th>
			<td>
				${member.email}
			</td>
		</tr>
		<tr>
			<th>
				${message("Member.memberRank")}:
			</th>
			<td>
				${member.memberRank.name}
			</td>
		</tr>
		<tr>
			<th>
				${message("admin.member.status")}:
			</th>
			<td>
				[#if !member.isEnabled]
					<span class="red">${message("admin.member.disabled")}</span>
				[#elseif member.isLocked]
					<span class="red"> ${message("admin.member.locked")} </span>
				[#else]
					<span class="green">${message("admin.member.normal")}</span>
				[/#if]
			</td>
		</tr>
		[#if member.isLocked]
			<tr>
				<th>
					${message("Member.lockedDate")}:
				</th>
				<td>
					${member.lockedDate?string("yyyy-MM-dd HH:mm:ss")}
				</td>
			</tr>
		[/#if]
		<tr>
			<th>
				${message("Member.point")}:
			</th>
			<td>
				${member.point}
			</td>
		</tr>
		<tr>
			<th>
				${message("Member.balance")}:
			</th>
			<td>
				${currency(member.balance, true)}
				<a href="../deposit/list.jhtml?memberId=${member.id}">[${message("admin.member.viewDeposit")}]</a>
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
				${message("Member.loginDate")}:
			</th>
			<td>
			  [#if member.loginDate?? && member.loginDate!=Null]
				${(member.loginDate?string("yyyy-MM-dd HH:mm:ss"))!"-"}
			  [/#if]
			</td>
		</tr>
		<tr>
			<th>
				${message("Member.registerIp")}:
			</th>
			<td>
				${(member.registerIp)!"-"}
			</td>
		</tr>
		<tr>
			<th>
				${message("Member.loginIp")}:
			</th>
			<td>
				${(member.loginIp)!"-"}
			</td>
		</tr>
	    <tr>
			<th>
				<span class="requiredField">*</span>姓名:
			</th>
			<td>
			   ${member.name}
			</td>
		</tr>
		<tr>
           <th>
				<span class="requiredField"></span>出生日期:
	      </th>
	      <td>
	          <input type="text" name="birth" class="text Wdate" onfocus="WdatePicker();" value="${member.birth}"/>
	      </td>
        </tr>
	</table>
	<table class="input tabContent">
	           <tr>
				<th>
					<span class="requiredField">*</span>头像:
				</th>
				<td>
				     <img id="imgHeadPhoto"  style="width: 150px; height: 150px; border: solid 1px #d2e2e2;" alt="" 
		                 src=
		                  [#if member.headImage?? && member.headImage!=Null]
		  					"${member.headImage}"
		  				  [#else]
		  					"${base}/resources/moc/images/headimg-big.jpg"
		  				  [/#if]
		              />
				</td>
			</tr>
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
		           ${member.address}
		      </td>
		    </tr>
		     <tr>
               <th>
					<span class="requiredField"></span>手机号:
		      </th>
		      <td>
		           ${member.mobile}
		      </td>
		    </tr>
		    <tr>
				<th>
					<span class="requiredField"></span>公司:
				</th>
				<td>
					${member.company}
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField"></span>头衔:
				</th>
				<td>
					${member.honor}
				</td>
			</tr>
				<tr>
				<th>
					<span class="requiredField"></span>个人签名:
				</th>
				<td>
					${member.signature}
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
					${member.site}
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField"></span>微博:
				</th>
				<td>
					${member.weibo}
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField"></span>微信:
				</th>
				<td>
					${member.weixin}
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField"></span>qq:
				</th>
				<td>
					${member.qq}
				</td>
			</tr>
		</table>
	<table class="input">
		<tr>
			<th>
				&nbsp;
			</th>
			<td>
				<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='list.jhtml'" />
			</td>
		</tr>
	</table>
</body>
</html>