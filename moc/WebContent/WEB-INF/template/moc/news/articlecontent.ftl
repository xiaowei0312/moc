
<!doctype html>
<html><!-- InstanceBegin template="/Templates/dwt.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">
<!-- InstanceBeginEditable name="doctitle" -->
<title>谋刻职业教育在线测评与学习平台</title>

<link rel="stylesheet" href="${base}/resources/moc/css/article.css">
<link rel="stylesheet" href="${base}/resources/moc/css/common.css">
<script src="${base}/resources/moc/js/jquery-1.8.0.min.js"></script>
<script src="${base}/resources/moc/js/common.js"></script>
<script src="${base}/resources/moc/js/mine.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $hits = $("#hits");

	// 查看点击数
	$.ajax({
		url: "${base}/article/hits/${article.id}.jhtml",
		type: "GET",
		success: function(data) {
			$hits.text(data);
		}
	});

});
</script>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="head" -->
<!-- InstanceEndEditable -->

</head>

<body>
[#assign articleCategory = article.articleCategory /]
[#include "/moc/include/header.ftl" /]
<!-- InstanceBeginEditable name="EditRegion1" -->
<div class="coursecont">
	<div class="coursepic">
		<h3 class="righttit">全部资讯</h3>
	    <div class="clearh"></div>
	    <span class="bread">
	      <a class="ask_link" href="${base}${allArticle.path}">全部资讯</a>&nbsp;/&nbsp;
	      <a class="ask_link" href="${base}${article.articleCategory.path}">${article.articleCategory.name}</a>&nbsp;/&nbsp;
	      ${article.title}
	    </span>
	</div>
	<div class="clearh"></div>
	<div class="coursetext">
		<span class="articletitle">
	        <h2>${article.title}</h2>
	        <div class="gray">
	        	<i>${article.createDate?string("yyyy-MM-dd HH:mm:ss")}</i>
	        	[#--<i>点击数：${article.hits}</i>--]
	        </div>
	    </span>
	   	<div>${article.content}</div>
		<div class="clearh" style="height:30px;"></div>
		[@pagination pageNumber = article.pageNumber totalPages = article.totalPages pattern = "{pageNumber}.html"]
					[#include "/moc/include/pagination.ftl"]
		[/@pagination]
		<div class="clearh" style="height:30px;"></div>
	    <a class="pagebtn lpage" 
	   		[#if prevArticle??]
	    		title="上一篇" href="${base}${prevArticle.path}" 
	    	[#else]
	    		title="亲，到头了"
	    	[/#if]>上一篇</a>
        <a class="pagebtn npage" 
        	[#if nextArticle??]
        		title="下一篇" href="${base}${nextArticle.path}" 
        	[#else]
        		title="亲，到尾了"
        	[/#if]>下一篇</a>
	</div>

	<div class="courightext">
		<div class="ctext">
		    <div class="cr1">
		    <h3 class="righttit">热门资讯</h3>
		    <div class="gonggao">
				<ul class="hotask">
					[@article_list articleCategoryId = articleCategory.id count = 10 orderBy="hits desc"]
							[#list articles as article]
								<li>
									<a class="ask_link" href="${base}${article.path}" title="${article.title}"><strong>●</strong>${abbreviate(article.title, 30)}</a>
								</li>
							[/#list]
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
	</div>
   
</div>
</div>
[#include "/moc/include/footer.ftl" /]
</body>
<!-- InstanceEnd --></html>
