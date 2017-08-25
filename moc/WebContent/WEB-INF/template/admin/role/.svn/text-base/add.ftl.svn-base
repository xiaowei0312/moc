<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.role.add")} - Powered By Sram</title>
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
		<a href="${base}/admin/common/index.jhtml">首页</a> &raquo; 添加角色
	</div>
	<form id="inputForm" action="save.jhtml" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					描述:
				</th>
				<td>
					<input type="text" name="description" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="全选此组权限">课程管理</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="authorities" value="admin:course" />课程管理
						</label>
					
						
						<label>
							<input type="checkbox" name="authorities" value="admin:courseCategory"/>课程分类
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:liveCourse" />直播课程管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:reviewCourse" />评价管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:thread" />问答管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:noteCourse" />笔记管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:dataCourse" />数据管理
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
							<input type="checkbox" name="authorities" value="admin:outlineCategory" />大纲类别
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:question" />题目管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:questionImport" />题目导入/到处
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:questionManager" />试卷管理
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
							<input type="checkbox" name="authorities" value="admin:member" />会员管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:memberRank" />会员等级管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:memberAttribute" />会员注册项
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:review" />评论管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:consultation" />咨询管理
						</label>
					</span>
				</td>
			</tr>
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="全选此组权限">订单管理</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="authorities" value="admin:order" />订单管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:print" />打印管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:payment" />收款管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:refunds" />退款管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:shipping" />发货管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:returns" />退货管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:deliveryCenter" />发货点管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:deliveryTemplate" />快递单模板管理
						</label>
					</span>
				</td>
			</tr>
			
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="全选此组权限">内容管理</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="authorities" value="admin:navigation" />导航管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:article" />文章管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:articleCategory" />文章分类管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:tag" />标签管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:friendLink" />友情链接管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:adPosition" />广告位管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:ad" />广告管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:template" />模板管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:cache" />缓存管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:static" />静态化管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:index" />索引管理
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
							<input type="checkbox" name="authorities" value="admin:promotion" />促销管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:coupon" />优惠券管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:seo" />SEO设置
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:sitemap" />Sitemap管理
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
							<input type="checkbox" name="authorities" value="admin:statistics" />访问统计管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:sales" />销售统计
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:salesRanking" />销售排行
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:purchaseRanking" />消费排行
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:deposit" />预存款
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
							<input type="checkbox" name="authorities" value="admin:setting" />系统设置
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:area" />地区管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:paymentMethod" />支付方式管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:shippingMethod" />配送方式管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:deliveryCorp" />物流公司管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:paymentPlugin" />支付插件
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:storagePlugin" />存储插件
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:admin" />账户管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:role" />角色管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:message" />消息管理
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:log" />日志管理
						</label>
					</span>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("admin.common.submit")}" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>