<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.role.edit")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<style type="text/css">
.authorities label {
	min-width: 120px;
	_width: 120px;
	display: block;
	float: left;
	padding-right: 4px;
	_white-space: nowrap;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $selectAll = $("#inputForm .selectAll");
	
	[@flash_message /]
	
	$selectAll.click(function() {
		var $this = $(this);
		var $thisCheckbox = $this.closest("tr").find(":checkbox");
		if ($thisCheckbox.filter(":checked").size() > 0) {
			$thisCheckbox.prop("checked", false);
		} else {
			$thisCheckbox.prop("checked", true);
		}
		return false;
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			name: "required",
			authorities: "required"
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">首页</a> &raquo;编辑角色
		${message("admin.role.edit")}
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
		<input type="hidden" name="id" value="${role.id}" />
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<input type="text" name="name" class="text" value="${role.name}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					描述:${message("Role.description")}:
				</th>
				<td>
					<input type="text" name="description" class="text" value="${role.description}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="全选此组权限">课程管理:</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="authorities" value="admin:course"[#if role.authorities?seq_contains("admin:course")] checked="checked"[/#if] />课程管理
						</label>
					
						
						<label>
							<input type="checkbox" name="authorities" value="admin:courseCategory"[#if role.authorities?seq_contains("admin:courseCategory")] checked="checked"[/#if] />课程分类
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:liveCourse"[#if role.authorities?seq_contains("admin:liveCourse")] checked="checked"[/#if] />直播课程管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:reviewCourse"[#if role.authorities?seq_contains("admin:reviewCourse")] checked="checked"[/#if] />评价管理
						<label>
							<input type="checkbox" name="authorities" value="admin:thread"[#if role.authorities?seq_contains("admin:thread")] checked="checked"[/#if] />问答管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:noteCourse"[#if role.authorities?seq_contains("admin:noteCourse")] checked="checked"[/#if] />笔记管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:dataCourse"[#if role.authorities?seq_contains("admin:dataCourse")] checked="checked"[/#if] />数据管理
						</label>
					</span>
				</td>
			</tr>
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="全选此组权限">题库管理</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="authorities" value="admin:outlineCategory"  [#if role.authorities?seq_contains("admin:outlineCategory")] checked="checked"[/#if] />大纲类别
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:question" [#if role.authorities?seq_contains("admin:question")] checked="checked"[/#if]/>题目管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:questionImport" [#if role.authorities?seq_contains("admin:questionImport")] checked="checked"[/#if]/>题目导入/到处
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:questionManager" [#if role.authorities?seq_contains("admin:questionManager")] checked="checked"[/#if]/>试卷管理
						</label>
						
					</span>
				</td>
			</tr>
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="全选此组权限">会员管理</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="authorities" value="admin:member"[#if role.authorities?seq_contains("admin:member")] checked="checked"[/#if] />会员管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:memberRank"[#if role.authorities?seq_contains("admin:memberRank")] checked="checked"[/#if] />会员等级管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:memberAttribute"[#if role.authorities?seq_contains("admin:memberAttribute")] checked="checked"[/#if] />会员注册项
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:review"[#if role.authorities?seq_contains("admin:review")] checked="checked"[/#if] />评价管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:consultation"[#if role.authorities?seq_contains("admin:consultation")] checked="checked"[/#if] />咨询管理
					</span>
				</td>
			</tr>
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="全选此组权限">订单管理：</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="authorities" value="admin:order"[#if role.authorities?seq_contains("admin:order")] checked="checked"[/#if] />订单管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:print"[#if role.authorities?seq_contains("admin:print")] checked="checked"[/#if] />打印管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:payment"[#if role.authorities?seq_contains("admin:payment")] checked="checked"[/#if] />收款管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:refunds"[#if role.authorities?seq_contains("admin:refunds")] checked="checked"[/#if] />退款管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:shipping"[#if role.authorities?seq_contains("admin:shipping")] checked="checked"[/#if] />发货管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:returns"[#if role.authorities?seq_contains("admin:returns")] checked="checked"[/#if] />退货管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:deliveryCenter"[#if role.authorities?seq_contains("admin:deliveryCenter")] checked="checked"[/#if] />发货点管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:deliveryTemplate"[#if role.authorities?seq_contains("admin:deliveryTemplate")] checked="checked"[/#if] />快递单模板管理
					</span>
				</td>
			</tr>
			
			
		<!--<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="全选此组权限">${message("admin.role.organizationGroup")}</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="authorities" value="admin:organization"[#if role.authorities?seq_contains("admin:organization")] checked="checked"[/#if] />${message("admin.role.organization")}
						</label>
						
					</span>
				</td>
			</tr>-->
			
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="全选此组权限">内容管理</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="authorities" value="admin:navigation"[#if role.authorities?seq_contains("admin:navigation")] checked="checked"[/#if] />导航管理
						<label>
							<input type="checkbox" name="authorities" value="admin:article"[#if role.authorities?seq_contains("admin:article")] checked="checked"[/#if] />文章管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:articleCategory"[#if role.authorities?seq_contains("admin:articleCategory")] checked="checked"[/#if] />文章分类管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:tag"[#if role.authorities?seq_contains("admin:tag")] checked="checked"[/#if] />标签管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:friendLink"[#if role.authorities?seq_contains("admin:friendLink")] checked="checked"[/#if] />友情链接管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:adPosition"[#if role.authorities?seq_contains("admin:adPosition")] checked="checked"[/#if] />广告位管理
						<label>
							<input type="checkbox" name="authorities" value="admin:ad"[#if role.authorities?seq_contains("admin:ad")] checked="checked"[/#if] />广告管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:template"[#if role.authorities?seq_contains("admin:template")] checked="checked"[/#if] />模板管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:cache"[#if role.authorities?seq_contains("admin:cache")] checked="checked"[/#if] />缓存管理
						<label>
							<input type="checkbox" name="authorities" value="admin:static"[#if role.authorities?seq_contains("admin:static")] checked="checked"[/#if] />静态化管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:index"[#if role.authorities?seq_contains("admin:index")] checked="checked"[/#if] />索引管理
						</label>
					</span>
				</td>
			</tr>
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="全选此组权限">营销管理</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="authorities" value="admin:promotion"[#if role.authorities?seq_contains("admin:promotion")] checked="checked"[/#if] />促销管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:coupon"[#if role.authorities?seq_contains("admin:coupon")] checked="checked"[/#if] />优惠卷管理
						<label>
							<input type="checkbox" name="authorities" value="admin:seo"[#if role.authorities?seq_contains("admin:seo")] checked="checked"[/#if] />SEO设置
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:sitemap"[#if role.authorities?seq_contains("admin:sitemap")] checked="checked"[/#if] />Sitemap管理
						</label>
					</span>
				</td>
			</tr>
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="全选此组权限">统计管理</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="authorities" value="admin:statistics"[#if role.authorities?seq_contains("admin:statistics")] checked="checked"[/#if] />访问统计管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:sales"[#if role.authorities?seq_contains("admin:sales")] checked="checked"[/#if] />销售统计
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:salesRanking"[#if role.authorities?seq_contains("admin:salesRanking")] checked="checked"[/#if] />销售排行
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:purchaseRanking"[#if role.authorities?seq_contains("admin:purchaseRanking")] checked="checked"[/#if] />消费排行
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:deposit"[#if role.authorities?seq_contains("admin:deposit")] checked="checked"[/#if] />预存款
						</label>
					</span>
				</td>
			</tr>
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="全选此组权限">系统设置</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="authorities" value="admin:setting"[#if role.authorities?seq_contains("admin:setting")] checked="checked"[/#if] />系统设置
						<label>
							<input type="checkbox" name="authorities" value="admin:area"[#if role.authorities?seq_contains("admin:area")] checked="checked"[/#if] />地区设置
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:paymentMethod"[#if role.authorities?seq_contains("admin:paymentMethod")] checked="checked"[/#if] />支付方式管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:shippingMethod"[#if role.authorities?seq_contains("admin:shippingMethod")] checked="checked"[/#if] />配送方式管理
						<label>
							<input type="checkbox" name="authorities" value="admin:deliveryCorp"[#if role.authorities?seq_contains("admin:deliveryCorp")] checked="checked"[/#if] />物流公司管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:paymentPlugin"[#if role.authorities?seq_contains("admin:paymentPlugin")] checked="checked"[/#if] />支付插件
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:storagePlugin"[#if role.authorities?seq_contains("admin:storagePlugin")] checked="checked"[/#if] />存储插件
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:admin"[#if role.authorities?seq_contains("admin:admin")] checked="checked"[/#if] />账户管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:role"[#if role.authorities?seq_contains("admin:role")] checked="checked"[/#if] />角色管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:message"[#if role.authorities?seq_contains("admin:message")] checked="checked"[/#if] />消息管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:log"[#if role.authorities?seq_contains("admin:log")] checked="checked"[/#if] />日志管理
						</label>
					</span>
				</td>
			</tr>
			[#if role.isSystem]
				<tr>
					<th>
						&nbsp;
					</th>
					<td>
						<span class="tips">${message("admin.role.editSystemNotAllowed")}</span>
					</td>
				</tr>
			[/#if]
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("admin.common.submit")}"[#if role.isSystem] disabled="disabled"[/#if] />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>