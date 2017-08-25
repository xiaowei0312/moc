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

</head>
<body>
      <div class="path">
		<a href="${base}/admin/common/index.jhtml">首页</a> &raquo;
		课程数据统计列表 <span>(${message("admin.page.total", page.total)})</span>
	</div>
	 <div class="bar">
	    <div class="buttonWrap">
			<a href="javascript:;" onclick="location.href='${base}/admin/datastatistic/list.jhtml'" class="button">返回</a>
		</div>
	 </div>
	 <form id="listForm" action="lesson_list.jhtml" method="get">
	  <input type="hidden" name="courseId" value="${courseId}"/>
	  <table id="listTable" class="list">
	     <tr>
	        <th>
	                    课时名
	        </th>
	        <th>
	                  课时学习人数
	        </th>
	        <th>
	                   课时完成人数
	        </th>
	        <th>
	                  课时平均学习时长（分）
	        </th>
	        <th>
	                  音视频时长（分）
	        </th>
	        <th>
	          音视频平均观看时长（分）
	        </th>
	     </tr>
	      [#list page.content as obj]
		     <tr>
		        <td>
		           ${obj[1]}
		        </td>
		        <td>
		           ${obj[2]}
		        </td>
		        <td>
		           ${obj[3]}
		        </td>
		        <td>
		           ${obj[4]}
		        </td>
		        <td>
		           ${obj[5]}
		        </td>
		        <td>
		           ${obj[6]}
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