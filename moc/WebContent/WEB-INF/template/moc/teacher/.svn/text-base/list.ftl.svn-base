<!doctype html>
<html><!-- InstanceBegin template="/Templates/dwt.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">
<!-- InstanceBeginEditable name="doctitle" -->
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
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
<script type="text/javascript">
 	 $().ready(function(){
 	 	  var $listForm=$("#listForm");
 	 	  var $pageNumber = $("#pageNumber");
 	 	  
 	 	  //分页
	       $.pageSkip = function(pageNumber) {
				$pageNumber.val(pageNumber);
				$listForm.submit();
				return false;
		   }
 	 });
</script>
</head>

<body>
[#include "/moc/include/header.ftl" /]
<!-- InstanceBeginEditable name="EditRegion1" -->
    <form id="listForm" action="${base}/teacher/list.jhtml" method="post">
     	<input type="hidden" id="pageNumber" name="pageNumber" value="${page.pageNumber}" />
  		<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}" /> 
		<div class="coursecont" style="background: none repeat scroll 0 0 #fff;border-radius: 3px;box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);" >
		    <h3 class="righttit" style="padding-left:50px;padding-top: 10px;padding-bottom: 10px;">优秀讲师</h3>
		    [#list page.content as teacher]
	    	[#if teacher.status =="published"]
		    	
				<div class="coursepic tecti" style="box-shadow:none;">
					<div class="teaimg">
						<a href="${base}${teacher.path}" target="_blank">
							<img  width="150"
							src=
							  [#if teacher.image ?? && teacher.image!=""]
			                      "${teacher.image}"
			                  [#else]
								 "${base}/resources/moc/images/teacher.png"
							  [/#if]
							/>
						</a>
					</div>
					<div class="teachtext">
						<h3>
							<a href="${base}${teacher.path}" target="_blank" class="teatt">
								${teacher.truename}
							</a>
							&nbsp;&nbsp;<strong>${teacher.signature}</strong>
						</h3>
						<h4>个人简介</h4>
						<p>${abbreviate(teacher.about, 220, "...")}</p>
						<h4>授课风格</h4>
						<p>${abbreviate(teacher.teachingStyle, 220, "...")}</p>
					</div>
					<div class="clearh"></div>
				</div>
			[/#if]
			[/#list]
			<div class="clearh" style="height:40px;">
		       [@pagination pageNumber = page.pageNumber totalPages = page.totalPages pattern = "javascript: $.pageSkip({pageNumber});"]
						[#include "/moc/include/pagination.ftl"]
			   [/@pagination]
	        </div>
		</div>
	</form>
<!-- InstanceEndEditable -->

[#include "/moc/include/footer.ftl" /]
</body>
</html>
