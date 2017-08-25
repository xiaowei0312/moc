[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.course.list")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<style type="text/css">
#listTable th,td{
	text-align:center;
}
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

	var $listForm = $("#listForm");
	var $filterSelect = $("#filterSelect");
	var $filterOption = $("#filterOption a");
	var $changeStatus = $("#listTable a.publish");
	
	[@flash_message /]
	
	$("#moreTable input:checked").each().click(function(){
		$(this).attr("checked",false);
	});
	
    
	//对课程状态进行修改
	$changeStatus.click(function(){
		var $this=$(this);
		var index=$this.html().indexOf("[发布]");
		var status=index>=0?"published":"closed";
		var content=index>=0?"您确定要发布么？":"您确定要关闭么？";
		$.dialog({
			type: "warn",
			content: content,
			onOk: function() {
				$.ajax({
					url: "${base}/admin/teacher/changeStatus.jhtml",
					type: "POST",
					data: {id:$this.attr("value"),status:status},
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
						if(status=="published"){
							$this.closest("td").children("span:first").text("已发布");
							$this.closest("td").children("span:last").text("");
							$this.closest("td").children("a.seeTeacher").text("[查看生成页面]");
							$this.closest("td").next().children("a:first").text("");
							$this.closest("td").next().children("i").text("课程已发布不能进行更改");
							$this.text("[关闭]");
						}else{
							$this.closest("td").children("span:first").text("   关闭");
							$this.closest("td").children("span:last").text("[查看生成页面]");
							$this.closest("td").children("a.seeTeacher").text("");
							$this.closest("td").next().children("a:first").text("[编辑]");
							$this.closest("td").next().children("i").text("");
							$this.text("  [发布]");
						}
					}
				});
			}
		});
		return false;
	});
	
	// 教师筛选
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
	
	// 筛选选项
	$filterOption.click(function() {
		var $this = $(this);
		var $goal = $("#" + $this.attr("name"));
		if ($this.hasClass("checked")) {
			$goal.val("");
		} else {
			$goal.val($this.attr("value"));
		}
		$listForm.submit();
		return false;
	});

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">教师列表</a> &raquo; <span>(${message("admin.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<input type="hidden" name="hasCourses" id="hasCourse"/>
		<div class="bar">
			<a href="add.jhtml" class="iconButton">
				<span class="addIcon">&nbsp;</span>${message("admin.common.add")}
			</a>
			<div class="buttonWrap">
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>${message("admin.common.delete")}
				</a>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>${message("admin.common.refresh")}
				</a>
				<div class="menuWrap">
					<a href="javascript:;" id="filterSelect" class="button">
						教师筛选<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="filterOption" class="check">
							<li class="separator">
								<a href="javascript:;" name="price" val="0"[#if price?? && price=0.0] class="checked"[/#if]>推荐教师</a>
							</li>
						</ul>
					</div>
				</div>
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
							<a href="javascript:;" class="current" val="truename">教师姓名</a>
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
					<a href="" class="sort">头像</a>
				</th>
				
				<th>
					<a href="javascript:;" class="sort" name="name">姓名</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="courseCategory">性别</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="price">推荐教师</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">注册时间</a>
				</th>
				<th>
				     状态
				</th>
				<th>
					<span>${message("admin.common.handle")}</span>
				</th>
			</tr>
			[#list page.content as teacher]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${teacher.id}" />
					</td>
					<td>
						<img src=[#if teacher.image??]"${teacher.image}"[#else]"${base }/resources/admin/images/headDefault.jpg"[/#if] width="50px"/>
					</td>
					<td>
						<span title="${teacher.truename}">
							${teacher.truename}
						</span>
					</td>
					<td>
						<span>
							[#if teacher.gender='male']
								才子
							[#elseif teacher.gender='female']
								才女
							[#else]
								保密
							[/#if]
						</span>
					</td>
					<td>
						否
					</td>
					<td>
						<span title="${teacher.createDate?string("yyyy-MM-dd HH:mm:ss")}">${teacher.createDate}</span>
					</td>
					<td>
					  [#if teacher.status="published"]
							<span>已发布</span>
							<span></span>
							<a href="${base}${teacher.path}" class="seeTeacher" target="_blank">[查看生成页面]</a>
							<a href="javascript:;" value="${teacher.id}" class="publish">[关闭]</a>
						[#elseif teacher.status="closed"]
							<span>关闭</span>
							<span>&nbsp;&nbsp;[查看生成页面]</span>
							<a href="${base}${teacher.path}" class="seeTeacher" target="_blank"></a>
							<a href="javascript:;" value="${teacher.id}" class="publish">[发布]</a>
						[#else]
							<span>草稿</span>
							<span>[查看生成页面]</span>
							<a href="${base}${teacher.path}" class="seeTeacher" target="_blank"></a>
							<a href="javascript:;" value="${teacher.id}" class="publish">&nbsp;[发布]</a>
						[/#if]
					</td>
					<td>
					    <i>
							[#if teacher.status="published"]
							       教师已发布不能进行更改
							[/#if]
						</i>
						[#if teacher.status!="published"]
						   <a href="edit.jhtml?id=${teacher.id}">[${message("admin.common.edit")}]</a>
						[/#if]
						
						[#if teacher.adminId=='' && teacher.adminId=0]
						    <a href="toAccount.jhtml?id=${teacher.id}">[分配账号]</a>
						[#else]
						         权限已分配
						[/#if]
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