<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html><!-- InstanceBegin template="/Templates/dwt.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">

<!-- InstanceBeginEditable name="doctitle" -->
<title>谋刻职业教育在线测评与学习平台</title>
<link href="${base}/resources/moc/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/moc/css/course.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/moc/css/tab.css" media="screen" rel="stylesheet" type="text/css"/>

<script  type="text/javascript" src="${base}/resources/moc/js/jquery.js"></script>
<script src="${base}/resources/moc/js/jquery.tabs.js" type="text/javascript" ></script>
<script src="${base}/resources/moc/js/mine.js" type="text/javascript" ></script>
<script  type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
	
<script type="text/javascript">
    $().ready(function(){
        var $listForm=$("#listForm");
        var $searchCourse=$("#searchCourse");
        var $pageNumber = $("#pageNumber");
        var $courseCategory = $("#courseul a.courseCategory");
        var categoryId=$("#categoryId").val();
        var rootFlag=$("#rootFlag").val();
        var $whitea=$(".whitea");
        
        if(categoryId!=''){
	        if(rootFlag=='true'){
	        	$("#courseul li").attr("class","");
    			$("#category"+categoryId).attr("class","graylink cur");
	        }else if(rootFlag=='false'){
                $("#courseul li").attr("class","");
            	$("#category"+categoryId).attr("class","course_curr");
	        }
        }
        
        $whitea.click(function(){
            $("#courseul li").attr("class","");
            $("#categoryId").val(0);
            $("#listForm").submit();
        });
        
        
       $searchCourse.click(function(){
          var courseName=$("#courseName").val();
          $listForm.submit();
       }); 
       //分页
       $.pageSkip = function(pageNumber) {
			$pageNumber.val(pageNumber);
			$listForm.submit();
			return false;
	    }
	    
    });
    
    
    function findByCategory(id){
        $("#courseul li").attr("class","");
        $("#category"+id).attr("class","course_curr");
        $("#rootFlag").val("false");
        $("#categoryId").val(id);
        $("#listForm").submit();
    }
    
    function findByRootCategory(id){
    	$("#courseul li").attr("class","");
    	 $("#category"+id).attr("class","graylink cur");
    	 $("#rootFlag").val("true");
    	 $("#categoryId").val(id);
         $("#listForm").submit();
    }
</script>
</head>
<body>
[#include "/moc/include/header.ftl" /]
<!-- InstanceBeginEditable name="EditRegion1" -->
  
 <div class="coursecont">
 <form id="listForm" action="${base}/course/list.jhtml" method="post">
    <div class="courseleft">
	<span class="select">
	  <input type="hidden" id="rootFlag" name="rootFlag" value="${rootFlag}"/>
	  <input type="hidden" id="pageNumber" name="pageNumber" value="${page.pageNumber}" />
	  <input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}" />     
	  <input type="hidden" id="categoryId" name="categoryId" value="${categoryId}"/>	
      <input type="text" id="courseName" name="courseName" class="pingjia_con" title="请输入关键字"
       value=
          [#if courseName?? && courseName!=Null]
            "${courseName}"
           [#else]
             "请输入关键字"
           [/#if]
           onblur="if (this.value =='') this.value='请输入关键字';" onclick="if (this.value=='请输入关键字') this.value='';"
        />
      <a href="#" id="searchCourse" class="sellink"></a>        
    </span>
    <ul id="courseul" class="courseul">
       <li class="curr" style="border-radius:3px 3px 0 0;background:#fb5e55;">
       <h3 style="color:#fff;"><a href="#" class="whitea">全部课程</a></h3>
       [@course_category_root_list]
         [#list courseCategories as rootCategory]
	       <li>
		        <h4>
			        <a href="javascript:void(0);" id="category${rootCategory.id}" style="white-space:nowrap;" class="graylink" onclick="findByRootCategory(${rootCategory.id})">
			        	${rootCategory.name}
			        </a>
		        </h4>
		        <ul id="sortul" class="sortul">
		           [#list rootCategory.children as childone]
			          <li id="category${childone.id}" class="">
			              <a href="javascript:void(0);" style="white-space:nowrap;"  onclick="findByCategory(${childone.id})">${childone.name}</a>
			          </li>
			       [/#list]
		        </ul>
	         <div class="clearh"></div>
	       </li>
	     [/#list]
       [/@course_category_root_list]
    </ul>

    <div style="height:20px;border-radius:0 0 5px 5px; background:#fff;box-shadow:0 2px 4px rgba(0, 0, 0, 0.1)"></div>
    </div>
    <div class="courseright">
        <div class="clearh">
      <ul class="courseulr">
	      [#if page.content?size>0] 
		      [#list page.content as obj]
		        <li>
		        	<div class="courselist">
		        	[#if loginFlag]
		            	<a href="${base}/course/alreadyLearn.jhtml?courseId=${obj[0]}&createDateStr=${obj[6]?string('yyyyMM')}" target="_blank">
	            	[#else]
	            		<a href="${base}/course/content/${obj[6]?string('yyyyMM')}/${obj[0]}.html" target="_blank">
		            [/#if]
		                <img style="border-radius:3px 3px 0 0;" width="240" height="120" src=
		                [#if obj[1]?? && obj[1]!=Null]
		                     "${obj[1]}"
		                [#else]
							"${base}/resources/moc/images/c1.jpg"
						[/#if]
		                 title="${obj[2]}"/>
		            </a>
		            <p class="courTit">
		            	[#if loginFlag]
			            	<a href="${base}/course/alreadyLearn.jhtml?courseId=${obj[0]}&createDateStr=${obj[6]?string('yyyyMM')}" target="_blank">
			            [#else]
			            	<a href="${base}/course/content/${obj[6]?string('yyyyMM')}/${obj[0]}.html" target="_blank">
			            [/#if]
			            	${obj[2]}
			            </a>
		            </p>
		            <div class="gray">
		            <span>
		            	${obj[3]}课时 
			            [#if obj[5]??]
			                [#if obj[5]/60/60 gte 1]
		                    	${(obj[5]/60/60)?string('0')}小时
		                    [/#if]
		                    [#if obj[5]/60%60 gte 1]
				            	${(obj[5]/60%60)?string('0')}分钟
				            [/#if]
			            [/#if]
		            </span>
		            <span class="sp1">
			             [#if obj[7]==0]
				            [#if obj[4]??]
				            	${obj[4]}人学习
				            [/#if]
				         [/#if]
		            </span>
		            <div style="clear:both"></div>
		            </div>
		            </div>
		       </li>
		       [/#list]
        [#else]
        	<p class="course_none">没有搜到相关课程，请换个关键词试试！</p>
	    [/#if]
    </ul>
    </div>
     <div class="clearh">
       [@pagination pageNumber = page.pageNumber totalPages = page.totalPages pattern = "javascript: $.pageSkip({pageNumber});"]
				[#include "/moc/include/pagination.ftl"]
	 [/@pagination]
     </div>
    </div>
 
</div>
</form>
</div>
[#include "/moc/include/footer.ftl" /]
</body>
</html>