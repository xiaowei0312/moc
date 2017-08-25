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
       $('input[name="targetType"]').click(function(){
       		$("#listForm").submit();
       });
  });
  

</script>
</head>
<body>
    <div class="path">
		<a href="${base}/admin/common/index.jhtml">首页</a> &raquo; 
	          文件列表(${courseTitle})
	</div>
	<form id="listForm" action="${base}/admin/uploadFile/courseFileList.jhtml" method="get">
    <input type="hidden" name="courseId" value="${courseId}"/>
    <input type="hidden" name="coursePageNumber" value="${coursePageNumber}"/>
	<div class="bar">
		<div class="buttonWrap">
          <a href="${base}/admin/uploadFile/add.jhtml?targetType=courselesson&targetId=${targetId}" class="iconButton">
			<span class="addIcon">&nbsp;</span>课时文件上传
		  </a>
		  <a href="${base}/admin/uploadFile/add.jhtml?targetType=coursematerial&targetId=${targetId}" class="iconButton">
			<span class="addIcon">&nbsp;</span>备用资料上传
		  </a>
		</div>
        <div class="buttonWrap">
			<a href="javascript:;" id="deleteButton" class="iconButton disabled">
				<span class="deleteIcon">&nbsp;</span>删除
			</a>
			<a href="${base}/admin/course/list.jhtml" class="button">返回</a>
		</div>
		<div class="buttonWrap">
			<input type="radio" name="targetType" class="button" value="courselesson" [#if targetType?? && targetType="courselesson"]checked[/#if]/>课时文件
			<input type="radio" name="targetType" class="button" value="coursematerial" [#if targetType?? && targetType="coursematerial"]checked[/#if]/>备用资料
		</div>
	</div>
	
  
   	<table id="listTable" class="list">
        <tr>
         <th class="check">
				<input type="checkbox" id="selectAll" />
		 </th>
		 <th>
				文件名称
		 </th>
		 <th>
				文件类型
		 </th>
		 <th>
				大小
		 </th>
		 <th>
				文件状态
		 </th>
		 <th>
		 		上传人
		 </th>
		 <th>
		 		更新时间
		 </th>
      </tr>
	   [#list page.content as uploadFile]
	     <tr>
	        <td>
				<input type="checkbox" name="ids" value="${uploadFile.id}" />
			</td>
			<td>
			    ${uploadFile.filename}
			</td>
			<td>
			    [#if uploadFile.fileType="video"]
			                  视频
			    [#elseif uploadFile.fileType="audio"]  
			                 音频
			    [#elseif uploadFile.fileType="ppt"]  
			      ppt
			    [#elseif uploadFile.fileType="txt"]       
			      txt
			     [#else]
			                   其他
			    [/#if]
			</td>
			<td>
				${unitConversion.bytes2kb(uploadFile.size)}
			</td>
			<td>
			[#if uploadFile.fileType =="video"]
				[#if uploadFile.convertStatus=="waiting"]
				[正在上传]
				[#elseif uploadFile.convertStatus=="doing"]
				[正在转换]
				[#elseif uploadFile.convertStatus=="error"]
				[转换失败]
				[#elseif uploadFile.convertStatus=="success"]
				[转换成功]
				[/#if]
			[/#if]
			</td>
			<td>
				${uploadFile.updatedUser.username}
			</td>
			<td>
				${uploadFile.modifyDate?string("yyyy-MM-dd HH:mm:ss")}
			</td>
		 </tr>		
	   [/#list]
  	</table>
   	[@pagination pageNumber = page.pageNumber totalPages = page.totalPages pattern = "{pageNumber}.jhtml"]
			[#include "/admin/include/pagination.ftl"]
	[/@pagination]
	</form>
	
		
</body>
</html>