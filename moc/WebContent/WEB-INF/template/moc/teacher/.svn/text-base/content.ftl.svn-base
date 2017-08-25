<!doctype html>
<html><!-- InstanceBegin template="/Templates/dwt.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<!-- InstanceBeginEditable name="doctitle" -->
<title>谋刻职业教育在线测评与学习平台</title>
<link href="${base}/resources/moc/css/common.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/course.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/tab.css" media="screen">

<script src="${base}/resources/moc/js/jquery.js"></script>
<script src="${base}/resources/moc/js/jquery.tabs.js"></script>
<script src="${base}/resources/moc/js/mine.js"></script>
<script  type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="head" -->
<!-- InstanceEndEditable -->
</head>

<body>
   [#include "/moc/include/header.ftl" /]
       
       <!-- InstanceBeginEditable name="EditRegion1" -->
<div class="coursecont">
	<div class="tecont">
		<div class="teaimg">
		    <img  width="150"
		      src=
		      [#if teacher.image?? && teacher.image!=Null]
				    "${teacher.image}" 
			  [#else]
				     "${base}/resources/admin/images/headDefault.jpg"
			 [/#if]
		    /> 
	    </div>
	    <div class="teachtext">
	    	<h3>${teacher.truename}&nbsp;&nbsp;<strong>${teacher.signature}</strong></h3>
	        <h4>个人简介</h4>
	        <p>${teacher.about}</p>
	        <h4>授课风格</h4>
	        <p>${teacher.teachingStyle}</p>
	    </div>
	    <div class="clearh"></div>
	</div>
	
	<div class="clearh"></div>
	
	<div class="tcourselist">
	<h3 class="righttit" style="padding-left:50px;">在教课程</h3>
	<ul class="tcourseul">
	 [#list courses as course]
		<li>
		    <span class="courseimg tcourseimg">
		    <a href="${base}${course[0].path}" target="_blank">
		    <img width="230" 
		       src=
		            [#if course[0].sourceImage?? && course[0].sourceImage!=Null]
	                     "${course[0].sourceImage}"
	                [#else]
						"${base}/resources/admin/images/teacher.jpg"
					[/#if]
		       />
		    </a></span>
		    <span class="tcoursetext">
		       <h4>
			       <a href="${base}${course[0].path}" target="_blank" class="teatt">
			       		${course[0].title}
			       </a>
			       <a class="state">
		       			[#if course[0].serializeMode="none"]
			    			非连载课程
			    		[#elseif course[0].serializeMode="serialize"]
			    			更新中
			    		[#else]
			    			已完结
			    		[/#if]
			       </a>
		       </h4>
		       <p class="teadec">${abbreviate(course[0].about,120,"...")}</p>
		       <p class="courselabel clock">
		       <span class="learn_time" style="width:155px">
			        ${course[0].lessonNum}课时
			        [#if course[1]??]
			        	 [#if course[1]/60/60 gte 1]
				        	 ${(course[1]/60/60)?string('0')}小时
			              [/#if]
			              [#if course[1]/60%60 gte 1]
  				             ${(course[1]/60%60)?string('0')}分钟
			              [/#if]
		            [/#if]
		        </span>
       	   		<span class="courselabel student">
		       	   [#if course[0].showStudentNumType=="opened"]
			       			${course[0].studentNum}人学习
		       	   [/#if]
	       	   </span>
		       <span class="courselabel pingjia">评价：
		           <img width="100" height="18"  data-bd-imgshare-binded="1"
		            src=
		            [#if course[3]?? && course[3]!=0]
		                "${base}/resources/moc/images/evaluate${course[3]?string("0")}.png"
		            [#else]
		            	"${base}/resources/moc/images/evaluate0.png"
		            [/#if]
		            />
		       </span></p>
		   </span>
		   <div style="height:0" class="clearh"></div>
		</li>
	 [/#list]
	
	<div class="clearh"></div>
	</ul>
	</div>

<div class="clearh"></div>
</div>
<!-- InstanceEndEditable -->

   [#include "/moc/include/footer.ftl" /]
</body>
</html>
