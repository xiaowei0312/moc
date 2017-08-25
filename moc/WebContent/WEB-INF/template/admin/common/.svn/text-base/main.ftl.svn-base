[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.main.title")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/admin/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<style type="text/css">
*{
	font: 12px tahoma, Arial, Verdana, sans-serif;
}
html, body {
	width: 100%;
	height: 100%;
	overflow: hidden;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $nav = $("#nav a");
	var $menu = $("#menu dl");
	var $menu_dd_a = $("#menu dl a");
	var $menuItem = $("#menu a");
	
	$nav.click(function() {
		var $this = $(this);
		$nav.removeClass("current");
		$this.addClass("current");
		var $currentMenu = $($this.attr("href"));
		$menu.hide();
		$menu_dd_a.next().slideUp();
		$currentMenu.show();
		return false;
	});
	
	$menuItem.click(function() {
		var $this = $(this);
		$menuItem.removeClass("current");
		$this.addClass("current");
	});
	
	
	
	$menu_dd_a.click(function(){
		var $ul = $(this).next();
		$ul.slideUp();
		if($ul.is(':visible')){
			$ul.slideUp();
		}else{
			$ul.slideDown();
		}
	});

});
</script>
</head>
<body>
	<script type="text/javascript">
		if (self != top) {
			top.location = self.location;
		};
	</script>
	<table class="main">
		<tr>
			<th class="logo">
				<a href="main.jhtml">
					<img src="${base}/resources/admin/images/header_logo.gif" alt="Sram" />
				</a>
			</th>
			<th>
				<div id="nav" class="nav">
					<ul>
						[#list ["admin:course", "admin:courseCategory", "admin:liveCourse", "admin:reviewCourse", "admin:thread", "admin:noteCourse", "admin:dataCourse"] as permission]
							[@shiro.hasPermission name = permission]
								<li>
									<a href="#course">课程</a>
								</li>
								[#break /]
							[/@shiro.hasPermission]
						[/#list]
						
						[#list ["admin:question","admin:questionCategory"] as permission]
							[@shiro.hasPermission name = permission]
								<li>
									<a href="#question">题库</a>
								</li>
								[#break /]
							[/@shiro.hasPermission]
						[/#list]
						
						[#list ["admin:member", "admin:memberRank"] as permission]
							[@shiro.hasPermission name = permission]
								<li>
									<a href="#member">会员</a>
								</li>
								[#break /]
							[/@shiro.hasPermission]
						[/#list]
						
						
						
						[#list ["admin:navigation", "admin:article", "admin:articleCategory", "admin:tag", "admin:friendLink", "admin:adPosition", "admin:ad", "admin:template", "admin:cache", "admin:static", "admin:index"] as permission]
							[@shiro.hasPermission name = permission]
								<li>
									<a href="#content">内容</a>
								</li>
								[#break /]
							[/@shiro.hasPermission]
						[/#list]
						
						<li>
							<a href="#dataStatic">统计</a>
						</li>
						
						
						[#list ["admin:setting", "admin:area", "admin:paymentMethod", "admin:shippingMethod", "admin:deliveryCorp", "admin:paymentPlugin", "admin:storagePlugin", "admin:admin", "admin:role", "admin:message", "admin:log"] as permission]
							[@shiro.hasPermission name = permission]
								<li>
									<a href="#system">系统</a>
								</li>
								[#break /]
							[/@shiro.hasPermission]
						[/#list]
						
					   		
					</ul>
				</div>
				<div class="link">
					<a href="${base}/" target="_blank">${message("admin.main.home")}</a>|
					<a href="http://www.sram-edu.com" target="_blank">${message("admin.main.official")}</a>|
					<a href="http://bbs.sram-edu.com" target="_blank">${message("admin.main.bbs")}</a>|
					<a href="http://www.sram-edu.com/about.html" target="_blank">${message("admin.main.about")}</a>
				</div>
				<div class="link">
					<strong>[@shiro.principal /]</strong>
					${message("admin.main.hello")}!
					<a href="../profile/edit.jhtml" target="iframe">[${message("admin.main.profile")}]</a>
					<a href="../logout.jsp" target="_top">[${message("admin.main.logout")}]</a>
				</div>
			</th>
		</tr>
		<tr>
			<td id="menu" class="menu">
				<dl id="course" class="default">
					<dt>课程管理</dt>
					[@shiro.hasPermission name="admin:courseCategory"]
						<dd>
							<a href="../course_category/list.jhtml" target="iframe">课程分类</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:course"]
						<dd>
							<a href="../course/list.jhtml" target="iframe">课程管理</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:course"]
						<dd>
							<a href="../course/recommendList.jhtml" target="iframe">推荐课程</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:liveCourse"]
						<dd>
							<a href="../parameter_group/list.jhtml" target="iframe">直播课程管理</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:course"]
						<dd>
							<a href="../courseReview/list.jhtml" target="iframe">评价管理</a>
						</dd>
						<dd>
							<a href="../courseThread/list.jhtml" target="iframe">问答管理</a>
						</dd>
						<dd>
							<a href="../courseNote/list.jhtml" target="iframe">笔记管理</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:dataCourse"]
						<dd>
							<a href="../datastatistic/list.jhtml" target="iframe">数据统计</a>
						</dd>
					[/@shiro.hasPermission]
				</dl>
				
				<dl id="question" >
					<dt>题库管理</dt>
					[@shiro.hasPermission name="admin:question"]
						<dd>
							 <a  href="javascript:void(0)" target="iframe">基础设置</a>
							<ul class="menuson">
						 		<li ><cite></cite><a class="subMenu" href="../accout_catalog/parentList.jhtml" target="iframe" >会计账目</a></li>
						 		<li ><cite></cite><a class="subMenu" href="../industry_category/parentList.jhtml" target="iframe" >行业管理</a></li>
						        <li><cite></cite><a class="subMenu" href="../outline_category/parentList.jhtml" target="iframe">大纲管理</a></li>
						     </ul>
						</dd>
						[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:question"]
						<dd>
							<a  href="javascript:void(0)" target="iframe">题库管理</a>
							<ul class="menuson">
								<li><cite></cite><a class="subMenu" href="../question/questionList.jhtml" target="iframe">题目管理</a></li>
								<li ><cite></cite><a class="subMenu" href="../questionImport/add.jhtml" target="iframe">题目导入</a></li>
							</ul>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:question"]
						<dd>
							<a  href="javascript:void(0)" target="iframe">试卷管理</a>
							<ul class="menuson">
								<li><cite></cite><a class="subMenu" href="../generatorStrategy/list.jhtml" target="iframe">试卷生成策略</a></li>
								<li><cite></cite><a class="subMenu" href="../testpaper/list.jhtml" target="iframe">试卷管理</a></li>
								<li><cite></cite><a class="subMenu" href="../testpaper/toAdd.jhtml" target="iframe">试卷导入/导出</a></li>
							</ul>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:question"]
						<dd>
							<a href="../testpaper_result/list.jhtml" target="iframe">考试管理</a>
						</dd>
					[/@shiro.hasPermission]
				</dl>
				
				
				<dl id="member">
					<dt>${message("admin.main.memberGroup")}</dt>
					[@shiro.hasPermission name="admin:member"]
						<dd>
							<a href="../member/list.jhtml" target="iframe">${message("admin.main.member")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:memberRank"]
						<dd>
							<a href="../member_rank/list.jhtml" target="iframe">${message("admin.main.memberRank")}</a>
						</dd>
					[/@shiro.hasPermission]
					

					
						<dd>
							<a href="../teacher/list.jhtml" target="iframe">教师管理</a>
						</dd>
						<dd>
							<a href="../integralRule/list.jhtml" target="iframe">学币规则管理</a>
						</dd>
				</dl>
				
				
				
				
				<dl id="content">
					<dt>${message("admin.main.contentGroup")}</dt>
					[@shiro.hasPermission name="admin:navigation"]
						<dd>
							<a href="../navigation/list.jhtml" target="iframe">${message("admin.main.navigation")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:article"]
						<dd>
							<a href="../article/list.jhtml" target="iframe">${message("admin.main.article")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:articleCategory"]
						<dd>
							<a href="../article_category/list.jhtml" target="iframe">${message("admin.main.articleCategory")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:tag"]
						<dd>
							<a href="../tag/list.jhtml" target="iframe">${message("admin.main.tag")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:friendLink"]
						<dd>
							<a href="../friend_link/list.jhtml" target="iframe">${message("admin.main.friendLink")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:adPosition"]
						<dd>
							<a href="../ad_position/list.jhtml" target="iframe">${message("admin.main.adPosition")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:ad"]
						<dd>
							<a href="../ad/list.jhtml" target="iframe">${message("admin.main.ad")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:template"]
						<dd>
							<a href="../template/list.jhtml" target="iframe">${message("admin.main.template")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:cache"]
						<dd>
							<a href="../cache/clear.jhtml" target="iframe">${message("admin.main.cache")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:static"]
						<dd>
							<a href="../static/build.jhtml" target="iframe">${message("admin.main.static")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:index"]
						<dd>
							<a href="../index/build.jhtml" target="iframe">${message("admin.main.index")}</a>
						</dd>
					[/@shiro.hasPermission]
				</dl>
				
				<dl id="statistics">
					<dt>${message("admin.main.statisticsGroup")}</dt>
					[@shiro.hasPermission name="admin:statistics"]
						<dd>
							<a href="../statistics/view.jhtml" target="iframe">${message("admin.main.statistics")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:statistics"]
						<dd>
							<a href="../statistics/setting.jhtml" target="iframe">${message("admin.main.statisticsSetting")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:sales"]
						<dd>
							<a href="../sales/view.jhtml" target="iframe">${message("admin.main.sales")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:salesRanking"]
						<dd>
							<a href="../sales_ranking/list.jhtml" target="iframe">${message("admin.main.salesRanking")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:purchaseRanking"]
						<dd>
							<a href="../purchase_ranking/list.jhtml" target="iframe">${message("admin.main.purchaseRanking")}</a>
						</dd>
					[/@shiro.hasPermission]
						[@shiro.hasPermission name="admin:deposit"]
						<dd>
							<a href="../deposit/list.jhtml" target="iframe">${message("admin.main.deposit")}</a>
						</dd>
					[/@shiro.hasPermission]
				</dl>
				<dl id="dataStatic">
 					<dt>${message("admin.role.statisticsGroup")}</dt>

					<dd>
						<a  href="javascript:void(0)" target="iframe">用户统计</a>
						<ul class="menuson">
					 		<li ><cite></cite><a class="subMenu" href="../analysisstatistic/dataAnaChartIndex.jhtml?analysisType=register" target="iframe" >新注册用户</a></li>
					 		<li ><cite></cite><a class="subMenu" href="../analysisstatistic/dataAnaChartIndex.jhtml?analysisType=allUserCount" target="iframe" >用户总数</a></li>
					     </ul>
					     <a  href="javascript:void(0)" target="iframe">课程统计</a>
				     	 <ul class="menuson">
					 		<li ><cite></cite><a class="subMenu" href="../analysisstatistic/dataAnaChartIndex.jhtml?analysisType=allCourseCount" target="iframe" >课程总数</a></li>
					 		<li ><cite></cite><a class="subMenu" href="../analysisstatistic/dataAnaChartIndex.jhtml?analysisType=course" target="iframe" >新增课程数</a></li>
					 		<li ><cite></cite><a class="subMenu" href="../analysisstatistic/dataAnaChartIndex.jhtml?analysisType=lesson" target="iframe" >新增课时数</a></li>
					 		<li ><cite></cite><a class="subMenu" href="../analysisstatistic/dataAnaChartIndex.jhtml?analysisType=courseLearn" target="iframe" >加入学习数</a></li>
					 		<li ><cite></cite><a class="subMenu" href="../analysisstatistic/dataAnaChartIndex.jhtml?analysisType=finishedLesson" target="iframe" >完成课时学习数</a></li>
					     </ul>
					</dd>
				</dl>
				<dl id="system">
					<dt>${message("admin.main.systemGroup")}</dt>
					[@shiro.hasPermission name="admin:setting"]
						<dd>
							<a href="javascript:void(0)" target="iframe">${message("admin.main.setting")}</a>
							<ul class="menuson">
						 		<li ><cite></cite><a class="subMenu" href="../setting/edit.jhtml?editType=basicEdit" target="iframe" >基础设置</a></li>
						 		<li ><cite></cite><a class="subMenu" href="../setting/edit.jhtml?editType=viewEdit" target="iframe" >显示设置</a></li>
						        <li><cite></cite><a class="subMenu" href="../setting/edit.jhtml?editType=safeAndRegisterEdit" target="iframe">注册与安全</a></li>
						        <li><cite></cite><a class="subMenu" href="../setting/edit.jhtml?editType=loginEdit" target="iframe">登录设置</a></li>
						        <li><cite></cite><a class="subMenu" href="../setting/edit.jhtml?editType=mailEdit" target="iframe">邮件设置</a></li>
						        <li><cite></cite><a class="subMenu" href="../setting/edit.jhtml?editType=shareEdit" target="iframe">分享设置</a></li>
						        <li><cite></cite><a class="subMenu" href="../setting/edit.jhtml?editType=otherEdit" target="iframe">其它设置</a></li>
						     </ul>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:area"]
						<dd>
							<a href="../area/list.jhtml" target="iframe">${message("admin.main.area")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:paymentMethod"]
						<dd>
							<a href="../payment_method/list.jhtml" target="iframe">${message("admin.main.paymentMethod")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:shippingMethod"]
						<dd>
							<a href="../shipping_method/list.jhtml" target="iframe">${message("admin.main.shippingMethod")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:deliveryCorp"]
						<dd>
							<a href="../delivery_corp/list.jhtml" target="iframe">${message("admin.main.deliveryCorp")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:paymentPlugin"]
						<dd>
							<a href="../payment_plugin/list.jhtml" target="iframe">${message("admin.main.paymentPlugin")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:storagePlugin"]
						<dd>
							<a href="../storage_plugin/list.jhtml" target="iframe">${message("admin.main.storagePlugin")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:admin"]
						<dd>
							<a href="../admin/list.jhtml" target="iframe">${message("admin.main.admin")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:role"]
						<dd>
							<a href="../role/list.jhtml" target="iframe">${message("admin.main.role")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:message"]
						<dd>
							<a href="../message/send.jhtml" target="iframe">${message("admin.main.send")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:message"]
						<dd>
							<a href="../message/list.jhtml" target="iframe">${message("admin.main.message")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:message"]
						<dd>
							<a href="../message/draft.jhtml" target="iframe">${message("admin.main.draft")}</a>
						</dd>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="admin:log"]
						<dd>
							<a href="../log/list.jhtml" target="iframe">${message("admin.main.log")}</a>
						</dd>
					[/@shiro.hasPermission]
				</dl>
				
			
			</td>
			<td>
				<iframe id="iframe" name="iframe" src="index.jhtml" frameborder="0"></iframe>
			</td>
		</tr>
	</table>
</body>
</html>