<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.productCategory.add")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<style type="text/css">

</style>
<script type="text/javascript">
$().ready(function() {
})
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 回复列表 <span>(${message("admin.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="lookPosts.jhtml" method="get">
	<input type="hidden" value="${courseThread.member.username}" name="member.username"/>
	<input type="hidden" value="${courseThread.title}" name="title"/>
	<input type="hidden" value="${courseThread.id}" name="id"/>
	
		<div class="bar">
			<div class="buttonWrap">
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>${message("admin.common.delete")}
				</a>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>${message("admin.common.refresh")}
				</a>
				<div class="menuWrap">
					<a href="javascript:;" id="pageSizeSelect" class="button">
						${message("admin.page.pageSize")}<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="pageSizeOption">
							<li>
								<a href="javascript:;"[#if page.pageSize == 10] class="current"[/#if] val="10">10</a>
							</li>
							<li>
								<a href="javascript:;"[#if page.pageSize == 20] class="current"[/#if] val="20">20</a>
							</li>
							<li>
								<a href="javascript:;"[#if page.pageSize == 50] class="current"[/#if] val="50">50</a>
							</li>
							<li>
								<a href="javascript:;"[#if page.pageSize == 100] class="current"[/#if] val="100">100</a>
							</li>
						</ul>
					</div>
				</div>
				<a href="javascript:;" onclick="history.back();" class="button">返回</a>
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
							<a href="javascript:;"[#if page.searchProperty == "content"] class="current"[/#if] val="content">回复内容</a>
						</li>
						<li>
							<a href="javascript:;"[#if page.searchProperty == "member"] class="current"[/#if] val="member">回复人</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th>
				</th>
				<th style="text-align: center;">
					提问人
				</th>
				<th style="text-align: center;">
					标题
				</th>
				<th></th>
			</tr>
			<tr>
				<td>
				</td>
				<td style="text-align: center;cursor:pointer;">
					${courseThread.member.username}
				</td>
				<td style="text-align: center; cursor:pointer;">
					<span title="${courseThread.title}">${abbreviate(courseThread.title, 50, "...")}</span>
				</td>
				<td></d>
			</tr>
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<a href="javascript:;" class="sort" name="title">回复人</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="articleCategory">回复内容</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="articleCategory">回复时间</a>
				</th>
			</tr>
			
			[#list page.content as courseNote]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${courseNote.id}" />
					</td>
					<td>
						${courseNote.member.username}
					</td>
					<td>
						<span title="${courseNote.content}">${abbreviate(courseNote.content, 50, "...")}</span>
					</td>
					<td>
						<span title="${courseNote.createDate?string("yyyy-MM-dd HH:mm:ss")}">${courseNote.createDate}</span>
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