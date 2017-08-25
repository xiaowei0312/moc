<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>${message("admin.static.build")} - Powered By Sram</title>
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
				   <th style="width:20%;">课时名称</th>
				   <th style="width:20%;">所属课程</th>
				   <th style="width:20%;">课时类型</th>
				   <th style="width:20%;">创建者</th>
				   <th style="width:20%;">创建时间</th>
			   </tr>
			   [#if page.content?size>0] 
				   [#list page.content as courseLesson]
				   		<tr>
				   			<td>
				   				${courseLesson.title}
				   			</td>
				   			<td>
				   				${courseLesson.course.title}
				   			</td>
				   			<td>
				   				[#if courseLesson.type='video']
						    		 视频
					    		[#elseif courseLesson.type="audio"]  
							                 音频
							    [#elseif courseLesson.type="ppt"]  
							      	ppt
							    [#elseif courseLesson.type="txt"]       
							      	txt
							    [#else]
						           	 其他
					    		[/#if]	
				   			</td>
				   			<td>
				   				${courseLesson.admin.username}
				   			</td>
				   			<td>
				   				${courseLesson.createDate?string("yyyy-MM-dd HH:mm:ss")}
				   			</td>
				   		</tr>
				   [/#list]
			   [#else]
				    <tr>
				    	<td colspan="5" style="text-align:center;color:red;font-size:15px;">
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