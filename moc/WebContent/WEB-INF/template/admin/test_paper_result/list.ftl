<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.memberRank.list")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<style type="text/css">
.moreTable th {
	width: 80px;
	line-height: 25px;
	padding: 5px 10px 5px 0px;
	text-align: right;
	font-weight: normal;
	color: #333333;
	background-color: #f8fbff;
}

.moreTable td {
	line-height: 25px;
	padding: 5px;
	color: #666666;
}

.promotion {
	color: #cccccc;
}
</style>
<script type="text/javascript">
$().ready(function() {
	var $delete = $("#listTable a.delete");
	var $filterSelect = $("#filterSelect");
	var $filterOption = $("#filterOption a");
	var $listForm = $("#listForm");
	var $moreButton = $("#moreButton");
	
	// 课程筛选
	$filterSelect.mouseover(function() {
		var $this = $(this);
		var offset = $this.offset();
		var $menuWrap = $this.closest("div.menuWrap");
		var $popupMenu = $menuWrap.children("div.popupMenu");
		$popupMenu.css({left: offset.left, top: offset.top + $this.height() + 2}).show();
		$menuWrap.mouseleave(function() {
			$popupMenu.hide();
		});
	});
	
	$moreButton.click(function(){
		$.dialog({
			title:"更多设置",
			[@compress single_line = true]
				content: '
				<table id="moreTable" class="moreTable" width="100%">
					<tr>
						<th style="white-space:nowrap;">
							试卷状态:
						<\/th>
						<td>
							<select name="testpaperStatu">
								<option value="">全部状态<\/option>
								[#list status as statu]
									<option  value="${statu}" [#if testpaperStatu="${statu}"] selected="selected" [/#if]>
										[#if "${statu}"="doing"]
											考试中
										[#elseif "${statu}"="paused"]
											考试暂停
										[#elseif "${statu}"="reviewing"]
											待批阅
										[#elseif "${statu}"="finished"]
											批阅结束
										[/#if]
									<\/option>
								[/#list]
							<\/select>
						<\/td>
					<\/tr>
					<tr>
						<th style="white-space:nowrap;">
							考试人员姓名:
						<\/th>
						<td>
							<input type="text" name="papermemberName" value="${memberName}"/>
						</td>
					</tr>	
				<\/table>',
			[/@compress]
			width: 450,
			height:150,
			modal: true,
			ok: "${message("admin.dialog.ok")}",
			cancel: "返 回",
			onOk: function() {
					var testpaperStatu=$("select[name='testpaperStatu'] option:selected").val();
					$("#testpaperStatu").val(testpaperStatu);
					$("#memberName").val($("input[name='papermemberName']").val());
					$listForm.submit();
				}
			});
	});
		// 筛选选项
	$filterOption.click(function() {
		var $this = $(this);
		var $dest = $("#" + $this.attr("name"));
		if ($this.hasClass("checked")) {
			$dest.val("");
		} else {
			$dest.val($this.attr("val"));
		}
		$listForm.submit();
		return false;
	});
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
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 试卷管理列表 <span>(${message("admin.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<input type="hidden" id="testpaperStatu" name="testpaperStatu" value="${testpaperStatu}"/>
		<input type="hidden" id="testpaperType" name="testpaperType" value="${testpaperType}"/>
		<input type="hidden" id="memberName" name="memberName" value="${memberName}"/>
		<div class="bar">
			<div class="buttonWrap">
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>${message("admin.common.delete")}
				</a>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>${message("admin.common.refresh")}
				</a>
				<div class="menuWrap">
					<a href="javascript:;" id="filterSelect" class="button">
						试卷类型筛选<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="filterOption" class="check">
							<li>
								<a href="javascript:;" name="testpaperType" val="" [#if !(testpaperType??) || testpaperType=""] class="checked"[/#if]>全部</a>
							</li>
							<li>
								<a href="javascript:;" name="testpaperType" val="intellexercise"[#if testpaperType?? && testpaperType="intellexercise"] class="checked"[/#if]>快速智能练习</a>
							</li>
							<li>
								<a href="javascript:;" name="testpaperType" val="specialexercis"[#if testpaperType?? && testpaperType="specialexercis"] class="checked"[/#if]>考点专项练习</a>
							</li>
							<li>
								<a href="javascript:;" name="testpaperType" val="genrationexam"[#if testpaperType?? && testpaperType="genrationexam"] class="checked"[/#if]>组卷模考</a>
							</li>
							<li>
								<a href="javascript:;" name="testpaperType" val="oldexam"[#if testpaperType?? && testpaperType="oldexam"] class="checked"[/#if]>真题试卷</a>
							</li>
							<li>
								<a href="javascript:;" name="testpaperType" val="munualsimulation"[#if testpaperType?? && testpaperType="munualsimulation"] class="checked"[/#if]>模考试卷</a>
							</li>
							<li>
								<a href="javascript:;" name="testpaperType" val="other"[#if testpaperType?? && testpaperType="other"] class="checked"[/#if]>其他试卷</a>
							</li>
						</ul>
					</div>
				</div>
				<a href="javascript:;" id="moreButton" class="button">更多设置</a>
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
							<a href="javascript:;"[#if page.searchProperty == "paperName"] class="current"[/#if] val="paperName">试卷名称</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					试卷名称
				</th>
				<th>
					试卷类型
				</th>
				<th>
					考试人员
				</th>
				<th>
					总时长(分)
				</th>
				<th>
					试卷状态
				</th>
				<th>
					总分
				</th>
				<th>
					<span>${message("admin.common.handle")}</span>
				</th>
			</tr>
			[#list page.content as testpaperesult]
				<tr>
					<td>
						<input type="checkbox" name="ids"[#if testpaperesult.isDefault] title="${message("admin.testpaperesult.deleteDefaultNotAllowed")}" disabled="disabled"[#else] value="${testpaperesult.id}"[/#if] />
					</td>
					<td>
						${testpaperesult.paperName}
					</td>
					<td>
						[#if testpaperesult.testpaper.testpaperType="intellexercise"]
								快速智能练习
						[#elseif testpaperesult.testpaper.testpaperType="specialexercis"]
								考点专项练习
						[#elseif testpaperesult.testpaper.testpaperType="genrationexam"]
								组卷模考
						[#elseif testpaperesult.testpaper.testpaperType="oldexam"]
								真题试卷
						[#elseif testpaperesult.testpaper.testpaperType="munualsimulation"]
								手工试卷
						[#else]
								其他试卷
						[/#if]
					</td>
					<td>
						${testpaperesult.member.name}
					</td>
					<td>
						${testpaperesult.testpaper.limitedTime/60}
					</td>
					<td>
						[#if testpaperesult.status="doing"]
								考试中
						[#elseif testpaperesult.status="paused"]
								考试暂停
						[#elseif testpaperesult.status="reviewing"]
								待批阅
						[#elseif testpaperesult.status="finished"]
								批阅结束
						[/#if]
					</td>
					<td>
						${testpaperesult.score}
					</td>
					<td>
						<a href="editTestpaperResult.jhtml?id=${testpaperesult.id}">[${message("admin.common.edit")}]</a>
						<a href="editParent.jhtml?id=${testpaperesult.id}">[批阅]</a>
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