[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
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
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>

</head>
<body style="overflow-x:hidden;overflow-y:hidden">
	<form id="inputForm" action="save.jhtml" method="post">
		<div class="bar">
			<a href="javascript:;" onclick="location.href='${base}/admin/uploadFile/questionFileList.jhtml?targetId=${targetId}&targetType=${targetType}'" class="button">返回</a>
		</div>
		
		[#if targetType=="question"]
		<div style="background:#d9edf7">
			<iframe style="" 
		src="${setting.cloudServerSite}/uploadFiles/toUpload.jhtml?userId=${userId}&userName=[@shiro.principal /]&targetType=${targetType}&targetId=${targetId}" frameborder="0" width="100%" height="500"  scrolling="auto"  
					></iframe>
				支持<strong>mp3, mp4, avi, flv, wmv, mov, ppt, pptx</strong>格式的视频文件上传，文件大小不能超过<strong>1 GB</strong>。<br/>
				支持<strong>断点续传</strong>（仅支持HTML5浏览器）。<br/>
				视频将上传到<strong>云视频服务器</strong>，上传之后会对视频进行格式转换，转换过程需要一定的时间，在这个过程中视频将无法播放。
		</div>
		[/#if]
		
				
	</form>
</body>
</html>