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

</style>
<script type="text/javascript">
$().ready(function() {

	var $listForm = $("#listForm");
	var $filterSelect = $("#filterSelect");
	var $filterOption = $("#filterOption a");
	var $changeStatus = $(".changeStatus");
	var $lookPosts=$(".lookPosts");
	[@flash_message /]
	
	$lookPosts.click(function(){
		$this=$(this);
		
		//查看回复
		var url="${base}/admin/courseThreadPost/lookPosts.jhtml?id="+$this.attr("value")
		+"&member.username="+$this.attr("threadMember")
		+"&title="+$this.attr("threadTitle");
		window.open(url,target="iframe");
	});
	//关闭在前，开启在后
	$changeStatus.live('click',function(){
		var $this=$(this);
		var $span=$(this).closest("span");
		var sHtml=$this.html();
		
		var isClosed=sHtml=='[关闭]'?true:false;
		var $iconSpan=$span.siblings(":first");
		$.ajax({
			url:"${base}/admin/courseThread/changeStatus.jhtml",
			type:"GET",
			data:{"isClosed":isClosed,"id":$iconSpan.attr("value")},
			dataType: "json",
			success:function(message){
				$.message(message);
				
				if(isClosed){
					$iconSpan.attr("class","falseIcon");
					$span.html(sHtml);
					$span.next().html('<a href="javascript:;" class="changeStatus">[开启]</a>');		
				}else{
					$iconSpan.attr("class","trueIcon");
					$span.html(sHtml);
					$span.prev().html('<a href="javascript:;" class="changeStatus">[关闭]</a>');		
				}	
			}
		});
	});
	
	// 问答筛选
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
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 问答列表(${courseTitle}) <span>(${message("admin.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="list.jhtml" method="get">
	<input type="hidden" value="${courseThreadPosts}" id="courseThreadPosts" name="courseThreadPosts" />
	<input type="hidden" value="${type}" id="type" name="type" />
	<input type="hidden" value="${isStick}" id="isStick" name="isStick" />
	<input type="hidden" value="${isElite}" id="isElite" name="isElite" />
	<input type="hidden" value="${isClosed}" id="isClosed" name="isClosed" />
	<input type="hidden" value="${courseId}" name="courseId" />
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
						问答筛选<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="filterOption" class="check">
							<li>
								<a href="javascript:;" name="courseThreadPosts" val="0"[#if courseThreadPosts?? && courseThreadPosts="0"] class="checked"[/#if]>未回复</a>
							</li>
							<li>
								<a href="javascript:;" name="courseThreadPosts" val="1"[#if courseThreadPosts?? && courseThreadPosts="1"] class="checked"[/#if]>有回复</a>
							</li>
							<li class="separator">
								<a href="javascript:;" name="type" val="discussion"[#if type?? && type="discussion"] class="checked"[/#if]>话题</a>
							</li>
							<li>
								<a href="javascript:;" name="type" val="question"[#if type?? && type="question"] class="checked"[/#if]>提问</a>
							</li>
							<li class="separator">
								<a href="javascript:;" name="isStick" val="1"[#if isStick?? && isStick != 0] class="checked"[/#if]>置顶</a>
							</li>
							<li class="separator">
								<a href="javascript:;" name="isElite" val="1"[#if isElite?? && isElite!=0] class="checked"[/#if]>精华</a>
							</li>
							<li class="separator">
								<a href="javascript:;" name="isClosed" val="0"[#if isClosed?? && isClosed==0] class="checked"[/#if]>关闭</a>
							</li>
							<li>
								<a href="javascript:;" name="isClosed" val="1"[#if isClosed?? && isClosed==1] class="checked"[/#if]>开启</a>
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
					[#if courseId??]
					<a href="javascript:;" onclick="history.back();" class="button">返回</a>
					[/#if]
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
						[#if !courseId??]
						<li>
							<a href="javascript:;" [#if page.searchProperty == "course"] class="current"[/#if] val="course">课程名称</a>
						</li>
						[/#if]
						<li>
							<a href="javascript:;" [#if page.searchProperty == "courseLesson"] class="current"[/#if] val="courseLesson">课时名称</a>
						</li>
						<li>
							<a href="javascript:;" [#if page.searchProperty == "member"] class="current"[/#if] val="member">作者昵称</a>
						</li>
						<li>
							<a href="javascript:;" [#if page.searchProperty == "title"] class="current"[/#if] val="title">问答标题</a>
						</li>
						<li>
							<a href="javascript:;" [#if page.searchProperty == "content"] class="current"[/#if] val="content">问答内容</a>
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
					<a href="" class="sort">问答标题</a>
				</th>
				
				<th>
					<a href="javascript:;" class="sort" name="name">问答内容</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="name">查看回复</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="courseCategory">发布者</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="courseCategory">所属类别</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="courseCategory">课程名</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="price">课时名</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">是否打开</a>
				</th>
				<th>
					<span>创建时间</span>
				</th>
				<th>
					<span>最后回复时间</span>
				</th>
			</tr>
			[#list page.content as obj]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${obj[0]}" />
					</td>
					<td>
						<span title="${obj[1]}">${abbreviate(obj[1], 50, "...")}</span>
					</td>
					<td>
						<span title="${obj[2]}">${abbreviate(obj[2], 50, "...")}</span>
					</td>
					<td style="text-align:center">
						<a href="javascript:;" threadTitle="${obj[1]}" threadMember="${obj[1]}" value="${obj[0]}" class="lookPosts"><span>[查看]</span></a>
					</td>
					<td>
						${obj[6]}
					</td>
					<td>
						[#--所属类别名称--]
						<span>${obj[9]}</span>
					</td>
					<td>
						<span>
							[#if obj[7]??]
								${obj[7]}
							[#else]
								<i>课程外的问答</i>
							[/#if]
						</span>
					</td>
					<td>
						<span>
							[#if obj[8]??]
								${obj[8]}
							[#else]
								<i>课程外的问答</i>
							[/#if]
						</span>
					</td>
					<td style="width:120px;">
					[#if !obj[3]]
						<span style="float:left" class="trueIcon" value="${obj[0]}">&nbsp;&nbsp;</span>
						<span style="float:left; line-hight:24px;margin-left:20px;"><a href="javascript:;" class="changeStatus">[关闭]</a></span>
						<span style="float:left; line-hight:24px;">[开启]</span>
					[#else]	
						<span style="float:left" class="falseIcon" value="${obj[0]}">&nbsp;&nbsp;</span>
						<span style="float:left; line-hight:24px;margin-left:20px;">[关闭]</span>
						<span style="float:left; line-hight:24px;"><a href="javascript:;" class="changeStatus">[开启]</a></span>
					[/#if]	
					</td>
					<td>
						<span title="${obj[4]?string("yyyy-MM-dd HH:mm:ss")}">${obj[4]}</span>
					</td>
					<td>
						<span title="${obj[5]}">
							[#if obj[5]??]
								${obj[5]}
							[#else]
								无回复
							[/#if]
						</span>
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