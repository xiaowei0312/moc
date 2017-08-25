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
	var $moreButton = $("#moreButton");
	var $filterSelect = $("#filterSelect");
	var $filterOption = $("#filterOption a");
	var $changeStatus = $("#listTable a.publish");
	var $managerChapter = $(".managerChapter");
	var $changeRecommend = $(".changeRecommend");
	[@flash_message /]
	
	//课程是否推荐
	$changeRecommend.click(function(){
		var $this=$(this);
		var index=$this.html().indexOf("[推荐]");
		var content=index>=0?"要推荐该课程么":"要取消推荐该课程么";
		var recommend=index>=0?true:false;
		$.dialog({
			type:"warn",
			content:content,
			onOk:function(){
				$.ajax({
					url:"${base}/admin/course/changeRecommend.jhtml",
					type:"POST",
					data:{"id":$this.attr("value"),"isRecommend":recommend,"status":$this.attr("courseStatus")},
					dataType:"json",
					cache: false,
					success:function(message){
						$.message(message);
						if(index>=0){
							$this.html("[取消推荐]")
						}else{
							$this.html("[推荐]");
						}
						
					}
				});
			}
		});
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
					url: "${base}/admin/course/changeStatus.jhtml",
					type: "POST",
					data: {id:$this.attr("value"),status:status},
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
						if(status=="published"){
							$this.closest("td").children("span:first").text("已发布").removeClass("red");
							$this.closest("td").children("span:last").text("");
							$this.closest("td").children("a.seeCourse").text("[查看生成页面]");
							$this.text("  [关闭]");
						}else{
							$this.closest("td").children("span:first").text("关闭").addClass("red");
							$this.closest("td").children("span:last").text("[查看生成页面]");
							$this.closest("td").children("a.seeCourse").text("");
							$this.text("  [发布]");
						}
						
						//改变章节管理中的课程状态
						$this.closest("tr").next().children("td").children("a:first").attr("courseStatus",status)
					}
				});
			}
		});
		return false;
	});
	
	$("#moreTable input:checked").each().click(function(){
		$(this).attr("checked",false);
	});
	
	// 更多选项
	$moreButton.click(function() {
		$.dialog({
			title: "${message("admin.course.moreOption")}",
			[@compress single_line = true]
				content: '
				<table id="moreTable" class="moreTable">
					<tr>
						<th>
							${message("Course.courseCategory")}:
						<\/th>
						<td>
							<select name="courseCategoryId">
								<option name="courseCategoryId" value="">${message("admin.common.choose")}<\/option>
								[#list courseCategoryTree as courseCategory]
									<option name="courseCategoryId" value="${courseCategory.id}"[#if "${courseCategory.id}" = courseCategoryId] selected="selected"[/#if]>
										[#if courseCategory.grade != 0]
											[#list 1..courseCategory.grade as i]
												&nbsp;&nbsp;
											[/#list]
										[/#if]
										${courseCategory.name}
									<\/option>
								[/#list]
							<\/select>
						<\/td>
					<\/tr>
					<tr>
						<th>
							连载模式:
						<\/th>
						<td>
							[#list enum2 as val]
								[#if "${val}"="none"]
									<input type="radio" name="serializeMode" [#if serializeMode?? && serializeMode="none"]checked[/#if] value="${val}" />草稿
								[#elseif "${val}"="finished"]
									<input type="radio" name="serializeMode" [#if serializeMode?? && serializeMode="finished"]checked[/#if] value="${val}"/>完结
								[#else]
									<input type="radio" name="serializeMode" [#if serializeMode?? && serializeMode="serialize"]checked[/#if] value="${val}"/>连载中
								[/#if]
							[/#list]
						<\/td>
					<\/tr>
					<tr>
						<th>
							学员人数是否显示:
						<\/th>
						<td>
							[#list enum3 as val]
								[#if "${val}"="opened"]
									<input type="radio" name="showStudentNumType" [#if showStudentNumType?? && showStudentNumType="opened"]checked[/#if] value="${val}"/>开启
								[#else]
									<input type="radio" name="showStudentNumType" [#if showStudentNumType?? && showStudentNumType="closed"]checked[/#if] value="${val}"/>关闭
								[/#if]
							[/#list]
						<\/td>
					<\/tr>
					<tr>
						<th>
							到期提醒:
						<\/th>
						<td>
							[#list enum as val]
								[#if "${val}"="none"]
									<input type="radio" name="deadlineNotify" [#if deadlineNotify?? && deadlineNotify="none"] checked[/#if]   value="${val}"/>否
								[#else]
									<input type="radio" name="deadlineNotify" [#if deadlineNotify?? && deadlineNotify="active"] checked[/#if] value="${val}"/>是
								[/#if]
							[/#list]
						<\/td>
					<\/tr>
				<\/table>',
			[/@compress]
			width: 470,
			modal: true,
			ok: "${message("admin.dialog.ok")}",
			cancel: "${message("admin.dialog.cancel")}",
			onOk: function() {
				$("#moreTable input:checked").each(function() {
					var $this = $(this);
					$("#" + $this.attr("name")).val($this.val());
				});
				$("#moreTable select option:selected").each(function() {
					var $this = $(this);
					$("#" + $this.attr("name")).val($this.val());
				});
				
				
				$listForm.submit();
			}
		});
	});
	
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
	


});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 课程列表 <span>(${message("admin.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<input type="hidden" id="courseCategoryId" name="courseCategoryId" value="${courseCategoryId}" />
		<input type="hidden" id="status" name="status" value="${status}" />
		<input type="hidden" id="price" name="price" value="${price}" />
		<input type="hidden" id="vipLevelId" name="vipLevelId" value="${vipLevelId}" />
		<input type="hidden" id="recommended" name="recommended" value="${recommended}" />
		<input type="hidden" id="serializeMode" name="serializeMode" value="${serializeMode}" />
		<input type="hidden" id="showStudentNumType" name="showStudentNumType" value="${showStudentNumType}" />
		<input type="hidden" id="deadlineNotify" name="deadlineNotify" value="${deadlineNotify}" />
		
		
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
						${message("admin.course.filter")}<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="filterOption" class="check">
							<li>
								<a href="javascript:;" name="status" val="published"[#if status?? && status="published"] class="checked"[/#if]>已发布的课程</a>
							</li>
							<li>
								<a href="javascript:;" name="status" val="draft"[#if status?? && status="draft"] class="checked"[/#if]>未发布的课程</a>
							</li>
							<li>
								<a href="javascript:;" name="status" val="closed"[#if status?? && status="closed"] class="checked"[/#if]>关闭的课程</a>
							</li>
							<li class="separator">
								<a href="javascript:;" name="price" val="0"[#if price?? && price=0.0] class="checked"[/#if]>免费课程</a>
							</li>
							<li>
								<a href="javascript:;" name="price" val="1"[#if price?? && price != 0.0] class="checked"[/#if]>收费课程</a>
							</li>
							<li class="separator">
								<a href="javascript:;" name="vipLevelId" val="1"[#if vipLevelId?? && vipLevelId != 0] class="checked"[/#if]>vip课程</a>
							</li>
							<li class="separator">
								<a href="javascript:;" name="recommended" val="1"[#if recommended?? && recommended=1] class="checked"[/#if]>推荐课程</a>
							</li>
						</ul>
					</div>
				</div>
				<a href="javascript:;" id="moreButton" class="button">${message("admin.course.moreOption")}</a>
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
							<a href="javascript:;" class="current" val="title">课程名称</a>
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
					<a href="" class="sort">课程图片</a>
				</th>
				
				<th>
					<a href="javascript:;" class="sort" name="title">课程名</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="courseCategory">${message("Course.courseCategory")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="price">${message("Course.price")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">状态</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">编辑</a>
				</th>
			</tr>
			[#list page.content as course]
				<tr >
					<td rowspan="2">
						<input type="checkbox" name="ids" value="${course.id}" />
					</td>
					<td>
						<img title="${course.sourceImage}" src=
							[#if course.sourceImage??]
							"${course.sourceImage}"
							[#else]
							"${base}/resources/admin/images/c1.jpg"
							[/#if]
							 width="50px"/>
					</td>
					<td>
						<span title="${course.title}">
							${abbreviate(course.title, 50, "...")}
						</span>
					</td>
					<td>
						${course.courseCategory.name}
					</td>
					<td>
						${currency(course.price)}
					</td>
					<td>
						[#if course.status="published"]
							<span>已发布</span>
							<span></span>
							<a href="${base}${course.path}" class="seeCourse" target="_blank">[查看生成页面]</a>
							<a href="javascript:;" value="${course.id}" class="publish">[关闭]</a>
						[#elseif course.status="closed"]
							<span class="red">关闭</span>
							<span>&nbsp;&nbsp;[查看生成页面]</span>
							<a href="${base}${course.path}" class="seeCourse" target="_blank"></a>
							<a href="javascript:;" value="${course.id}" class="publish">[发布]</a>
						[#else]
							<span class="red">草稿</span>
							<span>[查看生成页面]</span>
							<a href="${base}${course.path}" class="seeCourse" target="_blank"></a>
							<a href="javascript:;" value="${course.id}" class="publish">&nbsp;[发布]</a>
						[/#if]
					</td>
					<td>
						<a href="edit.jhtml?courseid=${course.id}&id=${course.courseCategory.id}">
								[编辑]
						</a>
						<a href="view.jhtml?courseid=${course.id}">
						     [查看]
						</a>
						<a href="javascript:;" class="changeRecommend" courseStatus="${course.status}" value="${course.id}">
							[#if course.isRecommend]
								[取消推荐]
							[#else]
								[推荐]
							[/#if]
						</a>
					</td>
				</tr>
				<tr>
					<td  colspan="7" style="text-align:left;">
						<a href="${base}/admin/course_chapter/list.jhtml?courseId=${course.id}" target="iframe">[章节管理]</a>
						<a href="../uploadFile/courseFileList.jhtml?courseId=${course.id}" target="iframe">[文件管理]</a>
						<a href="../courseannouncement/list.jhtml?courseId=${course.id}&coursePageNumber=${page.pageable.pageNumber}" target="iframe">[公告管理]</a>
						<a href="../courseNote/list.jhtml?courseId=${course.id}" target="iframe">[笔记管理]</a>
						<a href="../courseThread/list.jhtml?courseId=${course.id}" target="iframe">[问答管理]</a>
						<a href="../courseReview/list.jhtml?courseId=${course.id}" target="iframe">[评价管理]</a>
						<a href="${base}/admin/course/memberList.jhtml?courseId=${course.id}">[学员管理]</a>
					</td>
				</tr>
			[/#list]
		</table>
		[@pagination pageNumber = page.pageNumber totalPages = page.totalPages pattern = "{pageNumber}.jhtml"]
			[#include "/admin/include/pagination.ftl"]
		[/@pagination]
	</form>
</body>
</html>