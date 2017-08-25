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
	

	var $chooseFile = $("#chooseFile");
	//从课程文件中选择文件
	$chooseFile.click(function() {
		$.dialogIframe({
			title:"从课程文件选择",
			width: 800,
			height:400,
			modal: true,
			ok: "确定",
			cancel: "返 回",
			iframeSrc: "${base}/admin/outline_category_material/fromCourseFile.jhtml",
			onOk: function(dialogIframe) {
				var data=$(dialogIframe.document).find("input[name='ids']").serialize();
				if(data!=null && data!=""){
					$.ajax({
						url:"${base}/admin/outline_category_material/save.jhtml",
						data:$("#outlineCategoryId").serialize()+"&"+data,
						type:"POST",
						success:function(msg){
							window.location.reload();
						}
					});
				}
			}
		});
	});
});
</script>
</head>
<body>
    <div class="path">
		<a href="${base}/admin/common/index.jhtml">首页</a> &raquo; 
	          文件列表(${outlineCategoryName})
	</div>
	<form id="listForm" action="${base}/admin/outline_category/fileList.jhtml" method="get">
    <input type="hidden" id="outlineCategoryId" name="id" value="${outlineCategoryId}"/>
	<div class="bar">
		<div class="buttonWrap">
          <a href="${base}/admin/outline_category/addFile.jhtml?targetType=${targetType}&targetId=${targetId}" class="iconButton">
			<span class="addIcon">&nbsp;</span>文件上传
		  </a>
		   <a href="javascript:;" class="iconButton" id="chooseFile">
			<span class="addIcon">&nbsp;</span>从课程文件中选择
		  </a>
		</div>
        <div class="buttonWrap">
			<a href="javascript:;" id="deleteButton" class="iconButton disabled" url="${base}/admin/outline_category/deleteFile.jhtml">
				<span class="deleteIcon">&nbsp;</span>删除
			</a>
			<a href="javascript:;" id="refreshButton" class="iconButton">
				<span class="refreshIcon">&nbsp;</span>${message("admin.common.refresh")}
			</a>
			[#if parentId==null]
				<a href="${base}/admin/outline_category/parentList.jhtml" class="button">返回</a>
			[#else]
				<a href="${base}/admin/outline_category/childrenList.jhtml?id=${parentId}" class="button">返回</a>
			[/#if]
		</div>
	</div>
	
  
   	<table id="listTable" class="list">
        <tr>
         <th class="check">
				<input type="checkbox" id="selectAll" />
		 </th>
		 <th>
				资料名称
		 </th>
		 <th>
				文件后缀名
		 </th>
		 <th>
				大小
		 </th>
		 <th>
		 		创建人
		 </th>
		 <th>
		 		更新时间
		 </th>
      </tr>
	   [#list OutlineCategoryMaterials as material]
	     <tr>
	        <td>
				<input type="checkbox" name="ids" value="${material.id}" />
			</td>
			<td>
			    ${material.title}
			</td>
			<td>
			  ${material.fileMime}
			</td>
			<td>
				${unitConversion.bytes2kb(material.fileSize)}
			</td>
			<td>
				${material.admin.username}
			</td>
			<td>
				${material.modifyDate?string("yyyy-MM-dd HH:mm:ss")}
			</td>
		 </tr>		
	   [/#list]
  	</table>
	</form>
	
		
</body>
</html>