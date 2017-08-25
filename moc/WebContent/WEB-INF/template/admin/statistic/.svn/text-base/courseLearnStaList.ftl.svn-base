<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>${message("admin.member.list")} - Powered By Sram</title>
		<meta name="author" content="Sram Team" />
		<meta name="copyright" content="Sram" />
		<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
		<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
		<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
		<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
	</head>
	<body>
	    <form id="listForm" action="dataAnalysisList.jhtml" method="post">
	        <input type="hidden" name="analysisType" value="${analysisType}"/>
		 	<input type="hidden" id="beginDate" name="beginDateStr" value="${beginDateStr?string('yyyy-MM-dd')}"/>
		    <input type="hidden" id="endDate" name="endDateStr" value="${endDateStr?string('yyyy-MM-dd')}"/>
			<table id="listTable" class="list">
				<tr>
				   <th>用户名</th>
				   <th>加入课程</th>
				   <th>加入时间</th>
				</tr>
				[#if page.content?size>0] 
					[#list page.content as obj]
						<tr>
						    <td>${obj[0]}</td>
						    <td>${obj[1]}</td>
						    <td>${obj[2]?string("yyyy-MM-dd HH:mm:ss")}</td>
						</tr>
					[/#list]
				 [#else]
				    <tr>
				    	<td colspan="3" style="text-align:center;color:red;font-size:15px;">
				 			${beginDateStr?string('yyyy-MM-dd')}至${endDateStr?string('yyyy-MM-dd')}期间没有数据，请重新选择一个时间段
				 		</td>
				 	</tr>
				 [/#if]
			</table>
			[@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
				[#include "/admin/include/pagination.ftl"]
			[/@pagination]
		</form>
	</body>
</html>