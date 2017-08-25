<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.industryCategory.list")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $delete = $("#listTable a.delete");
	
	[@flash_message /]

	// 删除
	$delete.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("admin.dialog.deleteConfirm")}",
			onOk: function() {
				$.ajax({
					url: "delete.jhtml",
					type: "POST",
					data: {id: $this.attr("val")},
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
						if (message.type == "success") {
							$this.closest("tr").remove();
						}
					}
				});
			}
		});
		return false;
	});

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 行业管理列表
	</div>
	<form id="listForm" action="parentList.jhtml" method="get">
		<div class="bar">
			<div class="buttonWrap">
				<a href="${base}/admin/industry_category/addParent.jhtml" class="iconButton">
					<span class="addIcon">&nbsp;</span>${message("admin.common.add")}
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
							<a href="javascript:;" [#if page.searchProperty == "name"] class="current" [/#if] val="name">行业名称</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th>
					<span>行业名称</span>
				</th>
				<th>
					<span>排序</span>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			[#list page.content as industryCategory]
				<tr>
					<td>
						${industryCategory.name}
					</td>
					<td>
						${industryCategory.order}
					</td>
					<td>
						<a href="childrenList.jhtml?id=${industryCategory.id}">[${message("admin.common.view")}]</a>
						<a href="editParent.jhtml?id=${industryCategory.id}">[${message("admin.common.edit")}]</a>
						<a href="javascript:;" class="delete" val="${industryCategory.id}">[${message("admin.common.delete")}]</a>
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