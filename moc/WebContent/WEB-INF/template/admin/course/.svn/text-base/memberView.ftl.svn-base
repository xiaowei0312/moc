<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.course.add")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>

</head>
<body>
    <div class="path">
		<a href="${base}/admin/common/index.jhtml">首页</a> &raquo;查看会员信息
	</div>
   	<table class="input tabContent">
   	    <tr>
	         <th>
	                              用户名：
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
					<span class="requiredField">*</span>姓名:
				</th>
				<td>
				   ${member.name}
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
					&nbsp;
				</th>
				<td>
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="history.back()" />
				</td>
			</tr>
   	</table>
</body>
</html>