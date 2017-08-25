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
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>

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
	var $findByOutlineCategory = $("#findByOutlineCategory");
	var $testPaperStatu=$(".testPaperStatu");
	
	[@flash_message /]
	
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
	
	//更改试卷状态
	$testPaperStatu.click(function(){
		var testPaperId=$(this).find("input[class='testPaperId']").val();
		//试卷当前状态
		var currentStatu=$(this).find("input[class='currentStatu']").val();
		
		var radioItems = '';
		var draftChecked = "";
		var openChecked = "";
		var closedChecked = "";
		if(currentStatu =='draft'){
			draftChecked += 'checked="checked"';
		}else if(currentStatu =='open'){
			openChecked = 'checked="checked"';
			draftChecked += 'disabled="true"'; 
		}else if(currentStatu =='closed'){
			closedChecked = 'checked="checked"';
			draftChecked += 'disabled="true"'; 
		}
							
		$.dialog({
			title:"更改试卷状态",
			[@compress single_line = true]
				content: '
				<table id="moreTable" class="moreTable" width="100%">
					<tr>
						<th width="35%" style="white-space:nowrap;">
							试卷状态:
						<\/th>
						<td width="65%" style="white-space:nowrap;">
							<input type="radio" name="status"  value="draft" '+draftChecked+'\/>草稿
							<input type="radio" name="status"  value="open"  '+openChecked+'\/>开放
							<input type="radio" name="status"  value="closed" '+closedChecked+'\/>关闭
						<\/td>
					<\/tr>
				<\/table>',
			[/@compress]
			width:400,
			height:120,
			modal: true,
			ok: "${message("admin.dialog.ok")}",
			cancel: "返 回",
			onOk: function() {
			    var status=$("input[name='status']:checked").val();;
			    $.ajax({
					url: "${base}/admin/testpaper/updateTestPaperStatu.jhtml",
					type: "POST",
					data: {testPaperId:testPaperId,status:status},
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
						//延时执行
						setTimeout(function(){
							window.location.reload();		
						},500)
					}
				});
			}
		});
	});
	
	//根据类别查找题目
	$findByOutlineCategory.click(function() {
	
		$.dialog({
			title:"更多设置",
			[@compress single_line = true]
				content: '
				<table id="moreTable" class="moreTable" width="100%">
					<tr>
						<th style="white-space:nowrap;">
							题目类别:
						<\/th>
						<td>
							<select id="selectCategoryId">
								<option value="">请选择类别<\/option>
								[#list outlineCategoryTree as outlineCategory]
									<option  value="${outlineCategory.id}"[#if "${outlineCategory.id}" = outlineCategoryId] selected="selected"[/#if]>
										[#if outlineCategory.grade != 0]
											[#list 1..outlineCategory.grade as i]
												&nbsp;&nbsp;
											[/#list]
										[/#if]
										${outlineCategory.name}
									<\/option>
								[/#list]
							<\/select>
						<\/td>
					<\/tr>
					<tr>
						<th style="white-space:nowrap;">
							出卷日期：
						<\/th>
						<td>
							<input type="text" name="beginDateString"  onFocus="WdatePicker({dateFmt:\'yyyy-MM\',isShowClear:true,readOnly:true})" class="Wdate validate[required] form-textbox" style="width:100px;" size="10"
								[#if beginDateStr??]
									value="${beginDateStr?string("yyyy-MM")}"
								[#else]
									value=""
								[/#if]
							/>
							至
							<input type="text"  name="endDateString"  style="width:100px;" size="10"  class="Wdate validate[required] form-textbox" onFocus="WdatePicker({dateFmt:\'yyyy-MM\',isShowClear:true,readOnly:true})"
								[#if endDateStr??]
									value="${endDateStr?string("yyyy-MM")}"
								[#else]
									value=""
								[/#if]
							/>
						<\/td>
					<\/tr>
				<\/table>',
			[/@compress]
			width: 450,
			height:150,
			modal: true,
			ok: "${message("admin.dialog.ok")}",
			cancel: "返 回",
			onOk: function() {
					$("#outlineCategoryId").attr("value",$("#selectCategoryId").attr("value"));
					$("#beginDate").val($("input[name='beginDateString']").val());
					$("#endDate").val($("input[name='endDateString']").val());
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
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 试卷管理列表 <span>(${message("admin.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="list.jhtml" method="get">
	    <input type="hidden" id="testpaperType" name="testpaperType" value="${testpaperType}"/>
    	<input type="hidden" id="outlineCategoryId" name="outlineCategoryId" value="${outlineCategoryId}" />
    	<input type="hidden" id="beginDate" name="beginDateStr" 
	    	[#if beginDateStr??]
	    		value="${beginDateStr?string("yyyy-MM")}"
	    	[#else]
	    	    value=""
		    [/#if]	
		/>
    	<input type="hidden" id="endDate" name="endDateStr" 
    		[#if endDateStr??]
	    		value="${endDateStr?string("yyyy-MM")}"
	    	[#else]
	    	    value=""
		    [/#if]	
    	/>
		<div class="bar">
			<div class="buttonWrap">
				<div class="menuWrap">
					<a href="add.jhtml?testpaperType=munualsimulation" class="iconButton">
						<span class="addIcon">&nbsp;</span>手工组卷
					</a>
				</div>
			</div>
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
							<li class="separator">
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
				<a href="javascript:;" id="findByOutlineCategory" class="button">更多设置</a>
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
							<a href="javascript:;"[#if page.searchProperty == "name"] class="current"[/#if] val="name">${message("MemberRank.name")}</a>
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
					出卷时间
				</th>
				<th>
					试卷类型
				</th>
				<th>
					创建人
				</th>
				<th>
					总时长(秒)
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
			[#list page.content as testpaper]
				<tr>
					<td>
						<input type="checkbox" name="ids"[#if testpaper.isDefault] title="${message("admin.testpaper.deleteDefaultNotAllowed")}" disabled="disabled"[#else] value="${testpaper.id}"[/#if] />
					</td>
					<td title="${testpaper.name}">
						[#if testpaper.name??]
							[#if testpaper.testpaperType == 'oldexam' || testpaper.testpaperType == 'munualsimulation' || testpaper.testpaperType == 'other']
								[#if testpaper.name?length gte 50]
									${testpaper.name?substring(0,50)}...
								[#else]
									${testpaper.name}
								[/#if]
							[#else]
							    [#assign index=testpaper.name?index_of('-') /]
								[#assign name=testpaper.name?substring(0,testpaper.name?index_of('-',index+1))/]
								[#if name?length gte 50]
									${name?substring(0,50)}...
								[#else]
									${name}
								[/#if]
							[/#if]
						[/#if]
					</td>
					<td>
						${testpaper.oldYearMonth}
					</td>
					<td>
						[#if testpaper.testpaperType == 'intellexercise']快速智能练习[/#if]
						[#if testpaper.testpaperType == 'specialexercise']考点专项练习[/#if]
						[#if testpaper.testpaperType == 'genrationexam']组卷模考[/#if]
						[#if testpaper.testpaperType == 'munualsimulation']手工模考[/#if]
						[#if testpaper.testpaperType == 'oldexam']真题模考[/#if]
						[#if testpaper.testpaperType == 'other']其他[/#if]
					</td>
					<td>
						[#if testpaper.createMember??]
							${testpaper.createMember.username}
						[/#if]
						[#if testpaper.createAdmin??]
							${testpaper.createAdmin.username}
						[/#if]
					</td>
					<td>
						${testpaper.limitedTime}
					</td>
					<td>
						 [#if testpaper.status == 'draft']草稿[/#if]
			           	 [#if testpaper.status == 'open']开放[/#if]
			           	 [#if testpaper.status == 'closed']关闭[/#if]
					</td>
					<td>
						${testpaper.score}
					</td>
					<td>
						[#if testpaper.testpaperType=='oldexam' || testpaper.testpaperType=='munualsimulation']
							[#if testpaper.status == 'draft']
								<a href="editTestpaper.jhtml?id=${testpaper.id}">[${message("admin.common.edit")}]</a>
								<a href="${base}/admin/testpaper_chapter/add.jhtml?testpaperId=${testpaper.id}">[章节设置]</a>
								<a href="${base}/admin/testpaper_item/list.jhtml?testpaperId=${testpaper.id}">[题目设置]</a>
							[/#if]
							[#if testpaper.testpaperType == 'oldexam']
								<a href="javascript:;" class="testPaperStatu">
									<input type="hidden" class="testPaperId" value="${testpaper.id}"/>
									<input type="hidden" class="currentStatu" value="${testpaper.status}"/>
									[更改试卷状态]
								</a>
							[/#if]
						[#else]
							<a href="editTestpaper.jhtml?id=${testpaper.id}">[查看]</a>
							<a href="${base}/admin/testpaper_chapter/add.jhtml?testpaperId=${testpaper.id}">[查看章节]</a>
							<a href="${base}/admin/testpaper_item/list.jhtml?testpaperId=${testpaper.id}">[查看题目]</a>
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