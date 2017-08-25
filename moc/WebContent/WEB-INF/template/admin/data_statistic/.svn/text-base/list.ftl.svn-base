<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.article.list")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>

</head>
<body>
     <div class="path">
		<a href="${base}/admin/common/index.jhtml">首页</a> &raquo;
		课程数据统计列表 <span>(${message("admin.page.total", page.total)})</span>
	</div>
	 <form id="listForm" action="list.jhtml" method="get">
 <div class="bar">
 	<div class="buttonWrap">
		<select id="courseCategoryId" name="courseCategoryId" class="button" >
		    <option value="0">全部分类</option>
			[#list courseCategoryTree as courseCategory]
				<option value="${courseCategory.id}"
				 [#if courseCategory.id==courseCategoryId]
				     selected="selected"
				 [/#if]
				>
					[#if courseCategory.grade != 0]
						[#list 1..courseCategory.grade as i]
							&nbsp;&nbsp;
						[/#list]
					[/#if]
					${courseCategory.name}
				</option>
			[/#list]
		</select>&nbsp;
	</div>
	<div class="menuWrap">
	   <div class="search">
			<span id="searchPropertySelect" class="arrow">&nbsp;</span>
			<input type="text" id="searchValue" name="searchValue" value="${page.searchValue}" maxlength="200" />
			<button type="submit">&nbsp;</button>
	  </div>
	 <div class="popupMenu">
	   <ul id="searchPropertyOption">
	      	<li>
				<a href="javascript:;"[#if page.searchProperty == "title"] class="current"[/#if] val="title">课程名称</a>
			</li>
			<li>
				<a href="javascript:;"[#if page.searchProperty == "admin"] class="current"[/#if] val="admin">创建人用户名</a>
			</li>
	   </ul>
	</div>
 </div>
</div>
		 <table id="listTable" class="list">
		    <tr>
		       <th>
		                 课程名称
		       </th>
		       <th>
		               课时数
		       </th>
		       <th>
		                学员人数
		       </th>
		       <th>
		                完成课程人数
		       </th>
		       <th>
		                 课程学习时长
		       </th>
		       <th>
		                 操作
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
		              <a href="lesson_list.jhtml?courseId=${obj[0]}">查看课时数据</a>
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