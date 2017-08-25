<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.setting.edit")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 三方登录设置
	</div>
	<form id="inputForm" action="update.jhtml" method="post" enctype="multipart/form-data">
	<input type="hidden" name="editType" value="${editType}"/>
		<table class="input tabContent">
		    <tr>
		        <th>
		           <span class="requiredField">*</span>登录设置 ：
		        </th>
		        <td>
		        </td>
		    </tr>
		    <tr>
		    	<th>用户登录限置</th>
		    	<td>
		    		<input type="radio" name="singoLogin" />开启
		    		<input type="radio" name="singoLogin" checked/>关
		    		<i>开启后同一账号只能在同一处登录</i>
		    	</td>
		    </tr>
		    <tr>
		    	<th>第三方登录</th>
		    	<td>
		    		<input type="radio" name="otherLogin" checked/>开启
		    		<input type="radio" name="otherLogin" />关闭
		    	</td>
		    </tr>
		    <tr>
		    	<th>用户登录保护</th>
		    	<td>
		    		<input type="radio" name="safeLogin" />开启
		    		<input type="radio" name="safeLogin" checked/>关闭
		    		<i>开启后，登陆时用户连续多次输入错误密码时暂时封禁用户,此功能不影响admin手动永久封禁用户</i>
		    	</td>
		    </tr>
		    <tr>
		        <th>
		           <span class="requiredField">*</span>QQ账号 ：
		        </th>
		        <td>
		        </td>
		    </tr>
		    <tr>
		    	<th></th>
		    	<td>
			    	<input type="radio" name="innerQQ" checked />开启
			    	<input type="radio" name="innerQQ"  />关闭
			    	<a href="http://wiki.open.qq.com/wiki/%E3%80%90QQ%E7%99%BB%E5%BD%95%E3%80%91%E7%BD%91%E7%AB%99%E6%8E%A5%E5%85%A5#2._QQ.E7.99.BB.E5.BD.95" target="_blank">申请qq</a>
			    </td>
		    </tr>
		    <tr>
		    	<th>App ID</th>
		    	<td>
		    		<input type="text" name="appId" class="text" value="${app_ID}"/>
		    	</td>
		    </tr>
		    <tr>
		    	<th>App Key</th>
		    	<td>
		    		<input type="text" name="appKey" class="text" value="${app_KEY}"/>
		    	</td>
		    </tr>
		    <tr>
		    	<th>登录时必须设置帐号和邮箱</th>
		    	<td>
		    		<input type="radio" name="" checked/> 开启
		    		<input type="radio" name="" /> 关闭
		    		<i>
		    			在QQ帐号官方审核通过您的接入申请以前，请不要开启此选项，否则可能审核将无法通过
		    		</i>
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
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='../common/index.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>