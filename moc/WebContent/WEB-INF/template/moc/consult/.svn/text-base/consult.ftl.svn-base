<!doctype html>
<html><!-- InstanceBegin template="/Templates/dwt.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">
<!-- InstanceBeginEditable name="doctitle" -->
<title>谋刻职业教育在线测评与学习平台</title>
<link rel="stylesheet" href="${base}/resources/moc/css/course.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/tab.css" media="screen">
<script src="${base}/resources/moc/js/jquery-1.8.0.min.js"></script>
<script src="${base}/resources/moc/js/jquery.tabs.js"></script>
<script src="${base}/resources/moc/js/mine.js"></script>
<script src="${base}/resources/moc/js/common.js"></script>

<script>
$(function(){
	/*左侧栏*/
    var w = $(window).width();
    var lw = (w - 1100) / 2 - 10;
    $(".courseleft").css({
        "position": "fixed",
        "top": "80px",
        "left": lw + 10
    });
	 $(document).ready(function(){  
	    $(".pageul li a").each(function(){  
	        $this = $(this);
	        var url=$this[0].href;
	        var currentURL=String(window.location);
	        if(url==currentURL){  
	            $this.closest("li").addClass("curr");  
	        }  
	 	}); 
	 });  
});
</script><script>
$(function(){
	/*左侧栏*/
    var w = $(window).width();
    var lw = (w - 1100) / 2 - 10;
    $(".courseleft").css({
        "position": "fixed",
        "top": "90px",
        "left": lw + 10
    });
	 $(document).ready(function(){  
	    $(".pageul li a").each(function(){  
	        $this = $(this);
	        var url=$this[0].href;
	        var currentURL=String(window.location);
	        if(url==currentURL){  
	            $this.closest("li").addClass("curr");  
	        }  
	 	}); 
	 });  
});
</script>
</head>

<body>
[#include "/moc/include/header.ftl" /]
<!-- InstanceBeginEditable name="EditRegion1" -->
<div class="coursecont">
	<div class="courseleft">[#--侧边栏，底部导航的类别--]
	    <ul class="courseul pageul">
	    	[@navigation_list position = "bottom" useCache=false]
	    		[#list navigations as navigation]
	    			[#if navigations?first==navigation]
	    				<li style="border-radius:3px 3px 0 0">
	    					<a href="${navigation.url}" >${navigation.name}<b></b></a>
	    				</li>
	    			[#elseif navigations?last==navigation]
		    			 <li style="border-radius:0 0 3px 3px">
		    			 	<a href="${navigation.url}">${navigation.name}<b></b></a>
		    			 </li>
		    		[#else]
		    			 <li>
		    			 	<a href="${navigation.url}" >${navigation.name}<b></b></a>
		    			 </li>
	    			[/#if]
	    		[/#list]
	    	[/@navigation_list]
	    </ul>
    </div>[#--侧边栏，底部导航的类别结束--]
    
    <div class="courseright pageright" style="min-height:500px;">
    	<h3 class="mem-h3">${article.title}</h3>
        <div class="clearh"></div>
        <div class="pagetext">
        	${article.content}
        </div>
    </div>
    <div class="clearh"></div>
</div>
</div>
[#include "/moc/include/footer.ftl" /]
</body>
<!-- InstanceEndEditable -->
</html>