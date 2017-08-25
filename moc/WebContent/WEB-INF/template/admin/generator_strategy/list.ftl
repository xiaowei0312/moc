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
<script type="text/javascript">
$().ready(function() {
	var flag=${flag};
	var flag2=${flag2};
	if(flag){
		$("#addDefault").addClass("disabled").attr("href","javascript:void(0);");
	}
	if(!flag2){
		$("#addSpecial").addClass("disabled").attr("href","javascript:void(0);");
	}
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 试卷生成策略
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<div class="bar">
	    <div class="buttonWrap">
            <div class="menuWrap">
            <a href="addStrategy.jhtml?strategyType=default" class="iconButton" id="addDefault">
                <span class="addIcon">&nbsp;</span>增加缺省策略
            </a>
            <a href="addStrategy.jhtml?strategyType=outlineCategory" class="iconButton" id="addSpecial">
                <span class="addIcon">&nbsp;</span>增加大纲策略
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
					顶级大纲
				</th>
				<th>
					试卷类型
				</th>
				<th>
					生成策略
				</th>
				<th>
					难易程度
				</th>
				<th>
					总时长(秒)
				</th>
				<th>
					<span>${message("admin.common.handle")}</span>
				</th>
			</tr>
			[#list page.content as strategys]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="[#if strategys.outlineCategory??]${strategys.outlineCategory.id}[#else]-1[/#if]"/>
					</td>
					<td>
						[#if strategys.outlineCategory??]${strategys.outlineCategory.name}[#else]缺省策略[/#if]
					</td>
					<td>
						[#if strategys.testpaperType == 'intellexercise']智能/专项练习[/#if]
						[#if strategys.testpaperType == 'genrationexam']组卷模考[/#if]
					</td>
					<td>
						[#if strategys.generatorType == 'RANDOM']随机生成[/#if]
						[#if strategys.generatorType == 'DIFFICULTY']根据难度值生成[/#if]
					</td>
					<td>
						[#if strategys.difficulty?? && strategys.difficulty!='']
							[#assign difficulty = strategys.difficulty?replace('{','')?replace('}','')?replace(':','')?replace('"','')?replace(',','')?replace('easy','简单')?replace('normal','一般')?replace('hard','困难')/]
							${difficulty}
						[/#if]
					</td>
					<td>
						${strategys.timeLimit}
					</td>
					<td>
						<a href="editStrategy.jhtml?outlineCategoryId=[#if strategys.outlineCategory??]${strategys.outlineCategory.id}[#else]-1[/#if]">[${message("admin.common.edit")}]</a>
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