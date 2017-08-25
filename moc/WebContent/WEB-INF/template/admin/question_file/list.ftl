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
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript">

$().ready(function() {
	var $uploadQuestion = $("#uploadQuestion");
	
	$uploadQuestion.click(function(){
		var $this = $(this);
		if($this.hasClass("disabled")){
			return false;
		}
		window.location.href="${base}/admin/uploadFile/addQuestion.jhtml?targetType=${targetType}&targetId=${targetId}";
	
	});

	$("td a").click(function(){
		var $this =$(this);
		var uploadFileId =$this.attr("value");
		$.dialog({
			type: "warn",
			content: "确认删除？",
			ok: "确定",
			cancel:"取消",
			onOk: function() {
				$.ajax({
					url:"delete.jhtml",
					type: "POST",
					data: {"ids":uploadFileId},
					dataType: "json",
					cache: false,
					success: function(message) {
						$(".file_"+uploadFileId).remove();
						$uploadQuestion.removeClass("disabled");
						$.message(message);
					}
				});
			}
		});
	});
});
</script>
<style>
	#listTable tr th{
		width:90px;
	}
</style>
</head>
<body style="overflow-x:hidden">
	<form id="listForm" action="${base}/admin/outline_category/fileList.jhtml" method="get">
    <input type="hidden" name="outlineCategoryId" value="${targetId}"/>
    <input type="hidden" name="parentId" value="${parentId}"/>
	<div class="bar">
		<div class="buttonWrap">
          <a id="uploadQuestion"
      		href="javascript:;" 
          	[#if fileList?? && fileList?size>0]
          		title="一个题只能有一个视频解析"
          		class="iconButton disabled"
          	[#else]
          		class="iconButton"  
          	[/#if]	
          	>
			<span class="addIcon">&nbsp;</span>视频解析文件上传
		  </a>
		</div>
        <div class="buttonWrap">
			<a href="javascript:;" id="refreshButton" class="iconButton">
				<span class="refreshIcon">&nbsp;</span>${message("admin.common.refresh")}
			</a>
		</div>
	</div>
	
  
   	<table id="listTable" class="input tabContent">
        <tr>
        	<th>文件名称:</th>
        	 [#list fileList as uploadFile]
 				<td class="file_${uploadFile.id}">${uploadFile.filename}</td>
			[/#list]
			<td></td>
        </tr>
        <tr>
        	<th>文件格式:</th>
        	 [#list fileList as uploadFile]
 				<td class="file_${uploadFile.id}">${uploadFile.ext}</td>
			[/#list]
			<td></td>
        </tr>
        <tr>
        	<th>大小:</th>
        	 [#list fileList as uploadFile]
			 	<td class="file_${uploadFile.id}">${unitConversion.bytes2kb(uploadFile.size)}</td>
 			[/#list]
 			<td></td>
        </tr>
        <tr>
        	<th>状态:</th>
        	 [#list fileList as uploadFile]
        	 <td class="file_${uploadFile.id}">
			 	[#if uploadFile.convertStatus=="waiting"]
				[正在上传]
				[#elseif uploadFile.convertStatus=="doing"]
				[正在转换]
				[#elseif uploadFile.convertStatus=="error"]
				[转换失败]
				[#elseif uploadFile.convertStatus=="success"]
				[转换成功]
				[/#if]
			</td>
 			[/#list]
 			<td></td>
        </tr>
        <tr>
        	<th>上传人:</th>
    	 	[#list fileList as uploadFile]
			 	<td class="file_${uploadFile.id}">${uploadFile.updatedUser.username}</td>
 			[/#list]
 			<td></td>
        </tr>
        <tr>
        	<th>上传时间:</th>
        	[#list fileList as uploadFile]
			 	<td class="file_${uploadFile.id}">${uploadFile.modifyDate?string("yyyy-MM-dd HH:mm:ss")}</td>
 			[/#list]
 			<td></td>
        </tr>
        <tr>
        	<th>操作:</th>
        	[#list fileList as uploadFile]
			 	<td title="${uploadFile.filename}" class="file_${uploadFile.id}">
			 		<a href="javascript:;" value="${uploadFile.id}" class="blue">[删除]</a>
			 	</td>
 			[/#list]
 			<td></td>
        </tr>
  	</table>
	</form>
	
		
</body>
</html>