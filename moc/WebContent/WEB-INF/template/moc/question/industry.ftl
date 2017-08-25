<!doctype html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<!-- InstanceBeginEditable name="doctitle" -->
<title>谋刻职业教育在线测评与学习平台</title>

	<link rel="stylesheet" href="${base}/resources/moc/css/exam.css"/>
	<script src="${base}/resources/moc/js/jquery-1.8.0.min.js"></script>
	<link rel="stylesheet" href="${base}/resources/moc/css/tab.css" media="screen">
	<link rel="stylesheet" href="${base}/resources/moc/css/jquery.bxslider.css">
	<script src="${base}/resources/moc/js/jquery.tabs.js"></script>
	<script src="${base}/resources/moc/js/mine.js"></script>
	<script type="text/javascript" src="${base}/resources/moc/js/jquery.bxslider_e88acd1b.js"></script>
	<script type="text/javascript" src="${base}/resources/moc/js/common.js"></script>

	 <script type="text/javascript">
		function nTabs(thisObj,Num){
		if(thisObj.className == "currexam")return;
		var tabObj = thisObj.parentNode.id;
		var tabList = document.getElementById(tabObj).getElementsByTagName("li");
		for(i=0; i <tabList.length; i++)
		{
		if (i == Num)
		{
		   thisObj.className = "currexam"; 
		      document.getElementById(tabObj+"_Content"+i).style.display = "block";
		}else{
		   tabList[i].className = "normal"; 
		   document.getElementById(tabObj+"_Content"+i).style.display = "none";
		}
		} 
		}
	</script>
	<script>
		 var w=$(window).width();
		 var lw=(w-980)/2-10;
		 $(window).scroll(function(){
		   $(".right_menu").css({"position":"fixed","top":"90px","right":lw});
		}) 
		
		<!---->	
		$(function(){
		$(".dblink1.db5").click(function(){
			$(".jiexilist").toggle(1000);
			});
		
		$(".dblink1.db5").toggle(
		
		function(){
			$(this).css({"background":"url(${base}/resources/moc/images/ico_detail_item.png) right center no-repeat","background-position":"0px -1871px"})
			},function(){
				$(this).css({"background":"url(${base}/resources/moc/images/ico_detail_item.png) right center no-repeat","background-position":"0px -1833px"})
				}
		);
		})
	</script>
</head>
<body>
    [#include "/moc/include/questionHeader.ftl" /]
			   <div style="width:100%">
<div class="top_slide_wrap">
	<ul class="slide_box bxslider">
		<li>
			<a href="#"><img src="${base}/resources/moc/images/tbanner2.jpg" alt=""></a>
			
		</li>
		<li>
			<a href="#"><img src="${base}/resources/moc/images/tbanner1.jpg" alt="" title=""></a>
			
		</li>
		
	</ul>
	
	<div class="op_btns clearfix">
		<a href="#" class="op_btn op_prev"><span></span></a>
		<a href="#" class="op_btn op_next"><span></span></a>
	</div>
</div>
<div class="listcont">

<!--<div class="youshi">
    <ul>
        <li><img width="100%" height="150" src=""></li>
        <li><img width="100%" height="150" src=""></li>
        <li><img width="100%" height="150" src=""></li>
        <li><img width="100%" height="150" src=""></li>       
    </ul>
</div>-->
<!--分类一-->
<!--<div class="cate">
    <div class="clearh"></div>
    <h2>财会类</h2>
    <div class="clearh"></div>
    <ul class="cate_content">
        <li><a href="training.html"><img src="${base}/resources/moc/images/sort.png" width="236" height="140"></a></li>
        <li><a href="#"><img src="${base}/resources/moc/images/sort1.png" width="236" height="140"></a></li>
        <li><a href="#"><img src="${base}/resources/moc/images/sort2.png" width="236" height="140"></a></li>
        <li><a href="#"><img src="${base}/resources/moc/images/sort3.png" width="236" height="140"></a></li> 
        
            
    </ul>
</div>-->
<!--分类二-->
[#list rootsIndustry as root]
	<div class="cate">
	    <div class="clearh"></div> 
	    <h2>${root.name}</h2>
	    <div class="clearh"></div>
	    <ul class="cate_content">
	    [#list root.children as children]
	    	<a class="sortlink" href="${base}/question/training/${children.id}.html"><li><span class="sortbox s${children_index+1}" >${children.name}</span><div class="shadow"><span class="intoexam">进入考试>></span></div></li></a>
	  	[/#list]
	    </ul>
	</div>
[/#list]
	</div>
	
	<div class="clearh"></div>
	[#include "/moc/include/footer.ftl" /]
	<script type="text/javascript">
		(function(){
			$(".bxslider").bxSlider({
				auto:true,
				prevSelector:$(".top_slide_wrap .op_prev")[0],nextSelector:$(".top_slide_wrap .op_next")[0]
			});
		})();
	</script>
</body>
</html>