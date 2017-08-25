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

	[@flash_message /]

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo;学币规则列表
	</div>
	<form id="listForm" action="list.jhtml" method="get">
	<input type="hidden" value="${courseId}" name="courseId" />
		<div class="bar">
			<div class="buttonWrap">
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>${message("admin.common.refresh")}
				</a>
				<a href="javascript:;" onclick="history.back();" class="button">返回</a>
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th>
					<a href="javascript:;" class="sort" name="title">名称</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="articleCategory">类型</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="articleCategory">可获经验值</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="articleCategory">每日经验值上限</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="isPublication">可获学币</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="isPublication">每日学币上限</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">任务数量</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">PC拦截路径</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">app拦截路径</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">描述</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">操作</a>
				</th>
			</tr>
			[#list integralRules as integralRule]
				<tr>
					<td>
						${integralRule.name}
					</td>
					<td>
						[#if integralRule.taskType=="newTask"]
							新手任务
						[#elseif integralRule.taskType=="dailyTask"]
							日常任务
						[#elseif integralRule.taskType=="overallTask"]
							全局任务
						[/#if]
					</td>
					<td>
						${integralRule.experienceValue}
					</td>
					<td>
						[#if integralRule.isSetEvDailyLimit]
							${integralRule.evDailyLimit}
						[#else]
							无上限
						[/#if]
					</td>
					<td>
						${integralRule.learningCoin}
					</td>
					<td>
						[#if integralRule.isSetLcDailyLimit]
							${integralRule.lcDailyLimit}
						[#else]
							无上限
						[/#if]
					</td>
					<td>
						${integralRule.taskCount}
					</td>
					<td>
						${integralRule.urlPath}
					</td>
					<td>
						${integralRule.appUrlPath}
					</td>
					<td>
						${integralRule.description}
					</td>
					<td>
						<a href="edit.jhtml?integralRuleId=${integralRule.id}">[编辑]</a>
					</td>
				</tr>
			[/#list]
		</table>
	</form>
</body>
</html>