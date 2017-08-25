<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.outlineCategory.list")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/treeTable.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.treeSelect.js"></script>
<script type="text/javascript">
$().ready(function() {
	treeTable('listTable');	 
	[#if isOpen==1]	openAllNode();moveto(${currentId});[/#if]
	var $delete = $("#listTable a.delete");
	var $relateCourseChapter =$(".relateCourseChapter");
	
	[@flash_message /]
	
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
						$("#selectCourseChapter").attr("treePath",$("#storageTreePath").val())
						.attr("value",$("#storageTreePath").attr("coursePath"))
						.treeSelect({
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
				var selectCourseCategoryVal=$("#moreTable").find("select:eq(0)").val();
				var selectCourseVal=$("#moreTable").find("select:eq(1)").val();
				var selectChapterVal=$("#moreTable").find("select:eq(2)").val();
				var selectUnitVal=$("#moreTable").find("select:eq(3)").val();
				
				var lastTreePath;
				
				if(selectCourseVal==null || selectCourseVal==""){
					$.message("error","必须选择课程");
					return false;
				}else if(selectUnitVal !=null && selectUnitVal!=""){
					lastTreePath=selectCourseCategoryVal+","+selectCourseVal+","+selectChapterVal+","+selectUnitVal+",";
					//选择了小节
					relateType="chapter";
					relateId=selectUnitVal;
				}else if(selectChapterVal!=null && selectChapterVal!=""){
					lastTreePath=selectCourseCategoryVal+","+selectCourseVal+","+selectChapterVal+",";
					//选了章
					relateType="chapter";
					relateId=selectChapterVal;
				}else{
					lastTreePath=selectCourseCategoryVal+","+selectCourseVal+",";
					//只选了课程
					relateType="course";
					relateId=selectCourseVal;
				}
				$("#storageTreePath").attr("value",lastTreePath).attr("coursePath",selectCourseVal);
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
	    //跳转到目标
        function moveto(data_id) {
            var thetop = $("td[data_id="+data_id+"]").offset().top;
            $("html:not(:animated),body:not(:animated)").animate({
                scrollTop: thetop
            }, 0);
        }
	
</script>
</head>
<body>
<input type="hidden" id="storageTreePath" coursePath="7" value=""/>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 大纲分类列表
	</div>
	<div class="bar">
		<div class="buttonWrap">
			<a href="${base}/admin/outline_category/addChildren.jhtml?id=${rootId}" class="iconButton">
				<span class="addIcon">&nbsp;</span>${message("admin.common.add")}
			</a>
			<a href="javascript:;" id="refreshButton" class="iconButton">
				<span class="refreshIcon">&nbsp;</span>${message("admin.common.refresh")}
			</a>
			<a href="javascript:;" id="openAllNode" class="iconButton" onclick="openAllNode()">
				<span class="backIcon">&nbsp;</span>全部展开
			</a>
			<a href="${base}/admin/outline_category/parentList.jhtml" class="iconButton">
				<span class="backIcon">&nbsp;</span>${message("admin.common.back")}
			</a>
		</div>
	</div>
	<table id="listTable" class="list t_zsd">
		<tr>
			<th>
				<span>大纲名称</span>
			</th>
			<th>
				<span>大纲编码</span>
			</th>
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
		[#assign chapterIndex = 0 /]
		[#assign itemIndex1 = 0 /] [#--第1节的索引--]
		[#assign itemIndex2 = 0 /] [#--第1.1节的索引--]
		[#list outlineCategoryTree as outlineCategory]
			[#if outlineCategory_index==0]
				[#assign outlineCategoryName = outlineCategory.name /]
			[/#if]
			
			<tr>
				<td  gradeClass="keypoint-level-${outlineCategory.grade}"  tree_path="${outlineCategory.treePath?replace(",","-")}" order="${outlineCategory.order}" data_id="${outlineCategory.id}" [#if outlineCategory.parent??] data_parent_id="${outlineCategory.parent.id}" [/#if]>
					<span class="tree_parent"     style="margin-left: ${outlineCategory.grade * 20}px;[#if outlineCategory.grade == 0] color: #000000;[/#if]">
						[#if outlineCategory.grade == 1]
							[#assign chapterIndex =chapterIndex + 1]
							[#assign itemIndex1 = 0 /]
							[#assign itemIndex2 = 0 /]
							第${chapterIndex}章&nbsp;${outlineCategory.name}
						[#elseif outlineCategory.grade == 2 ]
							[#assign itemIndex1 =itemIndex1 + 1]
							[#assign itemIndex2 = 0 /]
							第${chapterIndex}.${itemIndex1}节&nbsp;${outlineCategory.name}
						[#elseif outlineCategory.grade == 3 ]
							[#assign itemIndex2 =itemIndex2 + 1]
							第${chapterIndex}.${itemIndex1}.${itemIndex2}节&nbsp;${outlineCategory.name}
						[#else]
							${outlineCategory.name}
						[/#if]
					</span>
				</td>
				<td>
					${outlineCategory.code}
				</td>
				<td>
					${outlineCategory.industryCategory.name}
				</td>
				<td>
					[#if outlineCategory.parent??]  <a onclick="moveNode(${outlineCategory.id},'up',${outlineCategory.order},'updateOutlineOrder')">上移</a>&nbsp;&nbsp;<a onclick="moveNode(${outlineCategory.id},'down',${outlineCategory.order},'updateOutlineOrder')">下移</a> [/#if]
				</td>
				<td>
					<a href="addChildren.jhtml?parentId=${outlineCategory.id}&id=${rootId}">[添加子节点]</a>
					<a href="editChildren.jhtml?id=${outlineCategory.id}&rootId=${rootId}">[${message("admin.common.edit")}]</a>
					<a href="javascript:;" class="delete" val="${outlineCategory.id}">[${message("admin.common.delete")}]</a>
					[#--
						<a href="fileList.jhtml?outlineCategoryId=${outlineCategory.id}&outlineCategoryName=${outlineCategoryName}&parentId=[#if outlineCategory.parent==null][#else]${outlineCategory.treePath?substring(1,outlineCategory.treePath?index_of(',',2))}[/#if]" class="fileManger">[视频解析]</a>
					--]
					<a href="javascript:void(0);" value="${outlineCategory.id}" relateType="${outlineCategory.course_chapter_type}" relateId="${outlineCategory.course_chapter_id}" class="relateCourseChapter">[关联课程章节]</a>
				</td>
			</tr>
		[/#list]
	</table>
</body>
</html>