<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.outlineCategory.list")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
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
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.treeSelect.js"></script>
<script type="text/javascript">
$().ready(function() {
	var $listForm = $("#listForm");
	var $moreButton = $("#moreButton");
	var $delete = $("#listTable a.delete");
	var $relateCourseChapter =$(".relateCourseChapter");
	
	[@flash_message /]
	
	// 更多设置
	$moreButton.click(function() {
		$.dialog({
			title:"更多设置",
			[@compress single_line = true]
				content: '
				<table id="moreTable" class="moreTable">
					<tr>
						<th>
							一级行业名称:
						<\/th>
						<td>
							<input type="text" name="firIndustryName" value="${firstIndustryName}"/>
						<\/td>
					<\/tr>
					<tr>
						<th>
							二级行业名称:
						<\/th>
						<td>
							<input type="text" name="secoIndustryName" value="${secondIndustryName}"/>
						<\/td>
					<\/tr>
				<\/table>',
			[/@compress]
			width: 360,
			height:150,
			modal: true,
			ok: "${message("admin.dialog.ok")}",
			cancel: "返 回",
			onOk: function() {
					$("#firstIndustryName").val($("input[name='firIndustryName']").val());
					$("#secondIndustryName").val($("input[name='secoIndustryName']").val());
					$listForm.submit();
				}
			});
	});
	
	
	//关联课程章节
	$relateCourseChapter.click(function(){
		var $this = $(this);
		var relateType = $this.attr("relateType");
		var relateId = $this.attr("relateId");
		var relateData={"id":$this.attr("value"),"course_chapter_type":relateType,"course_chapter_id":relateId};
		var relateInfo=relateType=="course"?"已关联课程":"已关联章节";
		
		if(relateType=="" || relateId==""){
			relateInfo=relateInfo+"-----:暂无";
		
		}else{
		
			//获取该大纲的关联信息
			$.ajax({
				url:"relateInfo.jhtml",
				data:relateData,
				type:"GET",
				dataType: "json",
				async: false,
				cache: false,
				success:function(msg){
					relateInfo=relateInfo+"------"+msg.title;
				},
				error:function(msg){
					$.message("error","操作失败");
					return false;
				}
			});
		}
	
		$.dialog({
			title:relateInfo,
			[@compress single_line = true]
				content: '
					<script type="text/javascript">
						$("#selectCourseChapter").treeSelect({
							url: "relateCourseChapter.jhtml",
							choose: "请选择类别",
							choose2: "请选择课程",
							choose3: "请选择章",
							choose4: "请选择节",
							limitSelect:4,
							breakPoint:2,
							hasTest:true
						});
					<\/script>
					<table id="moreTable" class="moreTable" width="100%">
					<tr>
						<th width="25%" style="white-space:nowrap;">
							大纲类别:
						<\/th>
						<td width="100%" style="white-space:nowrap;">
							[#--57是要关联的章，7是所属课程--]
							<input type="hidden" treePath="11,7,57," value="7" id="selectCourseChapter"/> 
						<\/td>
					<\/tr>
				<\/table>',
			[/@compress]
			width:800,
			height:120,
			modal: true,
			ok: "确定",
			cancel: "返 回",
			onOk: function() {
				//var selectSize=$("#moreTable").find("select").size();
				//0类别，1课程，2章，3节
				var selectCourseVal=$("#moreTable").find("select:eq(1)").val();
				var selectChapterVal=$("#moreTable").find("select:eq(2)").val();
				var selectUnitVal=$("#moreTable").find("select:eq(3)").val();
				
				if(selectCourseVal==null || selectCourseVal==""){
					$.message("error","必须选择课程");
					return false;
				}else if(selectUnitVal !=null && selectUnitVal!=""){
					
					//选择了小节
					relateType="chapter";
					relateId=selectUnitVal;
				}else if(selectChapterVal!=null && selectChapterVal!=""){
					
					//选了章
					relateType="chapter";
					relateId=selectChapterVal;
				}else{
				
					//只选了课程
					relateType="course";
					relateId=selectCourseVal;
				}
				
				//关联课程章节
				$.ajax({
					url:"related.jhtml",
					data:{"id":$this.attr("value"),"course_chapter_type":relateType,"course_chapter_id":relateId},
					type:"POST",
					success:function(){
						$.message("success","关联成功");
						$this.attr("relateType",relateType);
						$this.attr("relateId",relateId);
					},
					error:function(){
						$.message("error","关联失败");
					}
					
				});
			}
		});
	});
	
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
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 大纲分类列表
	</div>
	<form id="listForm" action="parentList.jhtml" method="get">
	    <input type="hidden" id="firstIndustryName" name="firstIndustryName" value="${firstIndustryName}"/>
	    <input type="hidden" id="secondIndustryName" name="secondIndustryName" value="${secondIndustryName}"/>
		<div class="bar">
			<div class="buttonWrap">
				<a href="${base}/admin/outline_category/addParent.jhtml" class="iconButton">
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
				<a href="javascript:;" id="moreButton" class="button">更多设置</a>
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
							<a href="javascript:;" [#if page.searchProperty == "name"] class="current" [/#if] val="name">大纲名称</a>
						</li>
						<li>
							<a href="javascript:;" [#if page.searchProperty == "code"] class="current" [/#if] val="code">大纲编码</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<table id="listTable" class="list" style="boder:1px;">
			<tr>
				
				<th>
					<span>1级行业</span>
				</th>
				<th>
					<span>2级行业</span>
				</th>
				<th>
					<span>大纲编码</span>
				</th>
				<th>
					<span>大纲名称</span>
				</th>
				<th>
					<span>创建人</span>
				</th>
				<th>
					<span>修改人</span>
				</th>
				<th>
					<span>排序</span>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			[#list page.content as outlineCategory]
				<tr >
					<td tree_path1='[#if outlineCategory.industryCategory.parent??]${outlineCategory.industryCategory.parent.id}[/#if]'>
						[#if outlineCategory.industryCategory.parent??] ${outlineCategory.industryCategory.parent.name}[#else]${outlineCategory.industryCategory.name}[/#if]
					</td>
					<td tree_path2='${outlineCategory.industryCategory.tree_path}'>
						[#if outlineCategory.industryCategory.parent??] ${outlineCategory.industryCategory.name}[/#if]
					</td>
					<td>
						<span style="margin-left: ${outlineCategory.grade * 20}px;[#if outlineCategory.grade == 0] color: #000000;[/#if]">
							${outlineCategory.code}
						</span>
					</td>
					<td>
						<span style="margin-left: ${outlineCategory.grade * 20}px;[#if outlineCategory.grade == 0] color: #000000;[/#if]">
							${outlineCategory.name}
						</span>
					</td>
					<td>
							${outlineCategory.createAdmin.username}
					</td>
					<td>
							${outlineCategory.updatedAdmin.username}
					</td>
					<td>
						${outlineCategory.order}
					</td>
					<td>
						<a href="childrenList.jhtml?id=${outlineCategory.id}">[${message("admin.common.view")}]</a>
						<a href="editParent.jhtml?id=${outlineCategory.id}">[${message("admin.common.edit")}]</a>
						<a href="javascript:;" class="delete" val="${outlineCategory.id}">[${message("admin.common.delete")}]</a>
						[#--
							<a href="${base}/admin/outline_category_material/fileList.jhtml?outlineCategoryId=${outlineCategory.id}" class="fileManger">[视频解析]</a>
						--]
						<a href="javascript:void(0);" value="${outlineCategory.id}" relateType="${outlineCategory.course_chapter_type}" relateId="${outlineCategory.course_chapter_id}" class="relateCourseChapter">[关联课程章节]</a>
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