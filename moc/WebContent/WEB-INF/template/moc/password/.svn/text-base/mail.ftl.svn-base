<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("shop.password.mailTitle")}[#if systemShowPowered] - Powered By Sram[/#if]</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
</head>
<body>
	<p>${username}:</p>
	<p>您好！</p>
	<p>欢迎使用${setting.siteName}找回密码功能，请点击以下链接重置您的密码[#if safeKey.expire??](有效期至: ${safeKey.expire?string("yyyy-MM-dd HH:mm")})[/#if]</p>
	<p>
		<a href="${setting.siteUrl}/password/reset.jhtml?username=${username}&key=${safeKey.value}" target="_blank">${setting.siteUrl}/password/reset.jhtml?username=${username}&key=${safeKey.value}</a>
	</p>
	<p>${setting.siteName}</p>
	<p>${.now?string("yyyy-MM-dd")}</p>
</body>
</html>