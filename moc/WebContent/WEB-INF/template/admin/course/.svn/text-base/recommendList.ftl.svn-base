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
<script type="text/javascript">
$().ready(function() {
	var $edit=$(".edit");
	
	[@flash_message /]
	$edit.click(function(){
		var $this =$(this);
		if($this.index()==0){
					$.dialog({
						title:"设置序号",
						[@compress single_line = true]
							content: '
							<table class="moreTable">
								<tr>
									<th>
										序号:
									<\/th>
									<td>
										<input type="text" id="recommendedSeq" value="0"\/>
									<\/td>
								<\/tr>
							<\/table>',
						[/@compress]
						width: 280,
						height:100,
						modal: true,
						ok: "${message("admin.dialog.ok")}",
						cancel: "返 回",
						onOk: function() {
						var recommendedSeq=$("#recommendedSeq").attr("value");
							$.ajax({
								url:"${base}/admin/course/changeRecommendedSeq.jhtml",
								type:"POST",
								data:{"id":$this.attr("value"),"recommendedSeq":recommendedSeq},
								dataType:"json",
								cache:"false",
								success:function(message){
									$.message(message);
									$this.closest("td").siblings("td:eq(0)").html(recommendedSeq);
								}
							});
						}
						});//设置序号结束
			}else{
				//取消推荐
					$.dialog({
						type:"warn",
						content:"您确定要取消推荐么？",
						onOk:function(){
							$.ajax({
								url:"${base}/admin/course/changeRecommend.jhtml",
								type:"POST",
								data:{"id":$this.attr("value"),"isRecommend":false},
								dataType:"json",
								cache: false,
								success:function(message){
									$.message(message);
									$this.closest("tr").remove();
								}
							});
						}
					});//取消推荐结束
			}
	});

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 推荐列表 <span>(${message("admin.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="recommendList.jhtml" method="get">
		<div class="bar">
			<div class="buttonWrap">
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
			
		</div>
		<table id="listTable" class="list">
			<tr>
				<th>
					<a href="javascript:;" class="sort" name="title">推荐序列</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="articleCategory">课程名</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="articleCategory">所属类别</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="articleCategory">课程状态</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="articleCategory">创建者</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="isPublication">推荐时间</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">操作</a>
				</th>
			</tr>
			[#list page.content as obj]
				<tr>
					<td>
						<span>${obj[0].recommendedSeq}</span>
					</td>
					<td>
						${obj[0].title}
					</td>
					<td>
						${obj[2]}
					</td>
					<td>
						[#if obj[0].status=='published']
							已发布
						[#else]
							未发布
						[/#if]
					</td>
					<td>
						${obj[1]}
					</td>
					<td>
						<span>${obj[0].recommendedTime}</span>
					</td>
					<td>
						<a href="javascript:;" class="edit" value="${obj[0].id}"><span>[设置序号]</span></a>
						<a href="javascript:;" class="edit" value="${obj[0].id}"><span>[取消推荐]</span></a>
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