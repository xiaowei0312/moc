<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.accoutCatalog.list")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/treeTable.js"></script>
<script type="text/javascript">
$().ready(function() {
	treeTable('listTable');	
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
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 会计账目列表
	</div>
	<div class="bar">
		<div class="buttonWrap">
			<a href="${base}/admin/accout_catalog/addChildren.jhtml?id=${rootId}" class="iconButton">
				<span class="addIcon">&nbsp;</span>${message("admin.common.add")}
			</a>
			<a href="javascript:;" id="refreshButton" class="iconButton">
				<span class="refreshIcon">&nbsp;</span>${message("admin.common.refresh")}
			</a>
			<a href="javascript:;" id="openAllNode" class="iconButton" onclick="openAllNode()">
				<span class="backIcon">&nbsp;</span>全部展开
			</a>
			<a href="${base}/admin/accout_catalog/parentList.jhtml" class="iconButton">
				<span class="backIcon">&nbsp;</span>${message("admin.common.back")}
			</a>
		</div>
	</div>
	<table id="listTable" class="list t_zsd">
		<tr>
			<th>
				<span>会计账目名称</span>
			</th>
			<th>
				<span>会计账目编码</span>
			</th>
			<th>
				<span>排序</span>
			</th>
			<th>
				<span>操作</span>
			</th>
		</tr>
		[#list accoutCatalogTree as accoutCatalog]
			<tr>
				<td  gradeClass="keypoint-level-${accoutCatalog.grade}"  tree_path="${accoutCatalog.treePath?replace(",","-")}" order="${accoutCatalog.order}" data_id="${accoutCatalog.id}" [#if accoutCatalog.parent??] data_parent_id="${accoutCatalog.parent.id}" [/#if]>
					<span class="tree_parent"     style="margin-left: ${accoutCatalog.grade * 20}px;[#if accoutCatalog.grade == 0] color: #000000;[/#if]">
						${accoutCatalog.name}
					</span>
				</td>
				<td>
					${accoutCatalog.code}
				</td>
				<td>
					[#if accoutCatalog.parent??]  <a onclick="moveNode(${accoutCatalog.id},'up',${accoutCatalog.order},'updateAccoutCatalogOrder')">上移</a>&nbsp;&nbsp;<a onclick="moveNode(${accoutCatalog.id},'down',${accoutCatalog.order},'updateAccoutCatalogOrder')">下移</a> [/#if]
				</td>
				<td>
					<a href="editChildren.jhtml?id=${accoutCatalog.id}&rootId=${rootId}">[${message("admin.common.edit")}]</a>
					<a href="javascript:;" class="delete" val="${accoutCatalog.id}">[${message("admin.common.delete")}]</a>
				</td>
			</tr>
		[/#list]
	</table>
</body>
</html>