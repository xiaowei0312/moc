
<!doctype html>
<html><!-- InstanceBegin template="/Templates/dwt.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">
<!-- InstanceBeginEditable name="doctitle" -->
<title>资讯列表页</title>
<link rel="stylesheet" type="text/css" href="${base}/resources/moc/css/article.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/moc/css/common.css"/>
<script type="text/javascript" src="${base}/resources/moc/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/mine.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
<script type="text/javascript">
$(function(){
	var $articleForm = $("#articleForm");
	var $pageNumber = $("#pageNumber");
	
	$articleForm.submit(function() {
		if ($pageNumber.val() == "" || $pageNumber.val() == "1") {
			$pageNumber.prop("disabled", true)
		}
	});
	$.pageSkip = function(pageNumber) {
		$pageNumber.val(pageNumber);
		$articleForm.submit();
		return false;
	}
	$(document).ready(function(){
		var temp;
		
		for(var i=0;i<$(".abbrCont").size();i++){
			temp=$.trim($("#cont"+i).text());
			$("#cont"+i).text(temp);
		}
	  
	    $("span.bread a").each(function(){  
	        $this = $(this);
	        var url = String(window.location);
	        if($this[0].href==url.substring(0,url.lastIndexOf("jhtml")+5)){  
	            $this.addClass("fombtn cur");  
	        }
	     }); 
	 }); 
	
});
</script>
</head>
<body>
[#include "/moc/include/header.ftl" /]
<div class="coursecont">
	<div class="coursepic">
		<h3 class="righttit">全部资讯</h3>
	    <div class="clearh"></div>
	    <span class="bread nob">
	        [@article_category_root_list count = 10]
			[#list articleCategories as category]
				[#if category.name!="底部导航"]
					<a class="fombtn" href="${base}${category.path}">${category.name}</a>
					[#list category.children as articleCategory]
						<a class="fombtn" href="${base}${articleCategory.path}">${articleCategory.name}</a>
					[/#list]
				[/#if]
			[/#list]
			[/@article_category_root_list]
	    </span>
	</div>
		<div class="clearh"></div>
		<div class="coursetext">
			[#if page.content?has_content]
			[#list page.content as article]
				<div class="articlelist">
					<h3><a class="artlink" title="${article.title}" href="${base}${article.path}">${abbreviate(article.title, 80, "...")}</a></h3>
					<p class="abbrCont" id="cont${article_index}">
						${abbreviate(article.text, 220, "...")}
					</p>
					<p class="artilabel">
						<span class="ask_label">${article.articleCategory.name}</span>
						<b class="labtime" title="${article.createDate?string("yyyy-MM-dd HH:mm:ss")}">${article.createDate}</b>
					</p>
					<div class="clearh"></div>
				</div>
			[/#list]
			[#else]
				暂无
			[/#if]
		
		<div class="clearh" style="height:10px;"></div>
	    <form id="articleForm" action="${base}${articleCategory.path}" method="get">
	    	<input type="hidden" id="pageNumber" name="pageNumber" value="${page.pageNumber}" />
			[@pagination pageNumber = page.pageNumber totalPages = page.totalPages pattern = "javascript: $.pageSkip({pageNumber});"]
				[#include "/moc/include/pagination.ftl"]
			[/@pagination]
		</form>
	</div>
	<div class="courightext">
		<div class="ctext">
		    <div class="cr1">
	    		<h3 class="righttit">热门资讯</h3>
			    <div class="gonggao">
					<ul class="hotask">
						[@article_list articleCategoryId = articleCategory.id count = 10 orderBy="isTop desc,hits desc" useCache=false]
							[#if articles?? && articles?size>0]
								[#list articles as article]
									<li>
										<a class="ask_link" href="${base}${article.path}" title="${article.title}"><strong>●</strong>${abbreviate(article.title, 30)}</a>
									</li>
								[/#list]
							[#else]
								暂无
							[/#if]
						[/@article_list]
			    	</ul>
			    </div>
		    </div>
		</div>

		<div class="ctext">
    		<div class="cr1">
    			<h3 class="righttit">推荐课程</h3>
    			<div class="teacher">
    				[@course_recommend_list count=3 useCache=false]
					[#list courses as course]
						 <div class="teapic">
			        		<a href="${base}${course.path}"  target="_blank">
			        			<img src="
			        				[#if course.sourceImage??]
			        					${course.sourceImage}
			        				[#else]
			        					${base}/resources/moc/images/c1.jpg 
			        				[/#if]" height="60" title="${course.title}"></a>
			       			 <h3 class="courh3">
			       			 	<a href="${base}${course.path}" class="peptitle" target="_blank">${course.title}</a>
			       			 </h3>
			    		</div>
			    		<div class="clearh"></div>
					[/#list]
    				[/@course_recommend_list]
    			</div>
    		</div>
		</div>[#--ctext--]
	</div>[#--courightext--]
	<div class="clearh"></div>
</div>
[#include "/moc/include/footer.ftl" /]
</body>


