<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("shop.error.title")}[#if systemShowPowered] - Powered By Sram[/#if]</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/moc/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/moc/css/exam.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/moc/css/error.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/moc/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
</head>
<body>
	[#include "/moc/include/header.ftl" /]
	<div class="container error">
		<div class="span24">
			<div class="main">
				<dl>
					<dt>${message("shop.error.message")}</dt>
					[#if message??]
						<dd>${content}</dd>
					[/#if]
					[#if constraintViolations?has_content]
						[#list constraintViolations as constraintViolation]
							<dd>[${constraintViolation.propertyPath}] ${constraintViolation.message}</dd>
						[/#list]
					[/#if]
					<dd>
						<a href="javascript:;" onclick="window.history.back(); return false;">${message("shop.error.back")}</a>
					</dd>
					<dd>
						<a href="${base}/">&gt;&gt; ${message("moc.error.home")}</a>
					</dd>
				</dl>
			</div>
		</div>
	</div>
	[#include "/moc/include/footer.ftl" /]
</body>
</html>