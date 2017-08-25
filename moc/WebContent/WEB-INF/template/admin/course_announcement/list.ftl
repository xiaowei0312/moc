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
	var $backList=$("#backList");
	        [@flash_message /]
	        
	        //点击的是返回按钮---课程列表页面
	$backList.click(function(){
		var url="${base}/admin/course/list.jhtml?&pageNumber=${coursePageNumber}";
		window.open(url,target="iframe");
	});
});
</script>
</head>
<body>
     <div class="path">
		<a href="${base}/admin/common/index.jhtml">首页</a> &raquo;
		 公告列表(${courseTitle}) <span>(${message("admin.page.total", page.total)})</span>
	</div>
     <div class="bar">
        <a href="add.jhtml?courseId=${courseId}" class="iconButton">
				<span class="addIcon">&nbsp;</span>添加
		</a>
        <div class="buttonWrap">
			<a href="javascript:;" id="deleteButton" class="iconButton disabled">
				<span class="deleteIcon">&nbsp;</span>删除
			</a>
		</div>
		<div class="buttonWrap">
			<a href="javascript:;" id="backList" class="button">返回</a>
		</div>
    </div>
    <form id="listForm" action="list.jhtml" method="get">
     <table id="listTable" class="list" style="width:100%">
            <tr>
	         <th class="check" style="width:5%;">
					<input type="checkbox" id="selectAll" />
			 </th>
			 <th style="width:85%;">
					<a href="" class="sort">公告内容</a>
			 </th>
			 <th style="width:10%;">
			         操作
			 </th>
	      </tr>
		   [#list page.content as courseAnnouncement]
		     <tr>
		        <td>
					<input type="checkbox" name="ids" value="${courseAnnouncement.id}" />
				</td>
				<td>
						${courseAnnouncement.content}
				</td>
				<td>
						<a href="edit.jhtml?id=${courseAnnouncement.id}">[编辑]</a>
				</td>
			 </tr>		
		   [/#list]
   </table>
   [@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
			[#include "/admin/include/pagination.ftl"]
	[/@pagination]
	</form>
</body>
</html>