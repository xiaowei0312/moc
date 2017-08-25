<!doctype html>
<html><!-- InstanceBegin template="/Templates/dwt.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<!-- InstanceBeginEditable name="doctitle" -->
<title>谋刻职业教育在线测评与学习平台</title>
<link rel="stylesheet" href="${base}/resources/moc/css/member.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/course.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/common.css"/>
<link href="${base}/resources/moc/css/tab.css" media="screen" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="${base}/resources/moc/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/mine.js"></script>
<script  type="text/javascript" src="${base}/resources/moc/js/jquery.tabs.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/common.js"></script>

<script type="text/javascript">
$(function(){

	$('.demo2').Tabs({
		event:'click'
	});

});	
</script>
<script type="text/javascript">

   	/** 
	*删除数组指定下标或指定对象 
	*/ 
	Array.prototype.indexOf = function(val) {        
	    for (var i = 0; i < this.length; i++) {      
			if (this[i] == val) 
				return i;          
		}          
		return -1;       
	};      
	Array.prototype.remove = function(val) {       
		var index = this.indexOf(val);  
		if (index > -1) {               
			this.splice(index, 1);     
		}        
	}; 
	
   $().ready(function(){
      var $qxsc=$(".qxsc");
      var courseFavoriteCode=getCookie("courseFavoriteCode");
      var $tabFlag=$("#tabFlag");
      var tabFlag=$tabFlag.val();
      var $listForm=$("#listForm");
      
      if(tabFlag=="learning"){
      	$(".tab_menu>li").attr("class","");
      	$(".tab_menu>li:eq(0)").attr("class","current");
      }else if(tabFlag=="finished"){
      	$(".tab_menu>li").attr("class","");
      	$(".tab_menu>li:eq(1)").attr("class","current");
      }else if(tabFlag=="favorite"){
  	 	$(".tab_menu>li").attr("class","");
      	$(".tab_menu>li:eq(2)").attr("class","current");
      }
      
      $(".tab_menu>li").each(function(index){
      		$(this).click(function(){
      			$(".tab_menu>li").attr("class","");
      			$(this).attr("class","current");
      			if(index==0){
      			    $tabFlag.val("learning");
      			}else if(index==1){
      				$tabFlag.val("finished");
      			}else if(index==2){
      				$tabFlag.val("favorite");
      			}
      			$listForm.submit();
      		});		
      });
      
      $(".qxsc").click(function(){
          var courseId=$(this).children("input").val();
          if (confirm("确认要删除？")) {
	           $.ajax({
						url: "${base}/member/coursefavorite/delete.jhtml",
						type: "POST",
						data: {"courseId":courseId},
						dataType: "json",
						cache: false,
						success: function(message) {
						   if(message.type=='success'){
						   		if(courseFavoriteCode!=null){
							   		var arrays=courseFavoriteCode.split(",");
							   		arrays.remove(courseId);
							   		arrays=arrays.join(",")
							   		addCookie("courseFavoriteCode",arrays, {expires: 7 * 24 * 60 * 60,path:"${base}/"});
						   		}
						   }
						   $.message(message);
						   setTimeout(function() {
								location.reload(true);
							}, 500);
						}
				 });
			}
      });
   });
   
</script>
</head>

<body>

 [#include "/moc/include/memberHeader.ftl" /]
<form id="listForm" action="${base}/member/course/myCourseList.jhtml" method="post">
    <input type="hidden" id="tabFlag" name="tabFlag" value="${tabFlag}"/>
	<div class="membcont">
		<h3 class="mem-h3">我的课程</h3>
		<div class="box demo2" style="width:795px; padding-left:5px">
					<ul class="tab_menu">
						<li class="">学习中</li>
						<li class="">已学完</li>
						<li class="">收藏</li>
					</ul>
					<div class="tab_box">
						<div>
						<ul class="memb_course">
							[#if courseList?size>0] 
			                     [#list courseList as obj]	   
			                        <li>
			                            <div class="courseli mysc">
				                             <a href="${base}/course/content/${obj[1]?string('yyyyMM')}/${obj[0]}.html" target="_blank">
												 <img src=
					                               [#if obj[2]?? && obj[2]!=Null]
									  					"${obj[2]}"
									  			   [#else]
									  					"${base}/resources/moc/images/memb-course.jpg"
									  			  [/#if]
					                               width="250"/>                        
				                            </a>
				                            <p class="memb_courname">
				                            	 <a class="blacklink" href="${base}/course/content/${obj[1]?string('yyyyMM')}/${obj[0]}.html" target="_blank">
				                            		${obj[3]}
				                            	 </a>
				                            </p>
				                            <div class="mpp">
				                                <div class="lv" style=
				                                   [#if obj[5]?? && obj[5]!=Null]
				                                      "width:${obj[5]}%;"
				                                   [#else]
				                                      "width:0%;"
				                                   [/#if]
				                                   ></div>
				                            </div>
				                            [#if obj[4]=="learning"]
					                            <p class="goon">
						                            <a href="${base}/member/course/toLearn.jhtml?courseId=${obj[0]}">
						                           	 	<span>继续学习</span>
						                            </a>
					                            </p>
					                        [#elseif obj[4]=="finished"]
					                        	<p class="goon">
						                            <a href="${base}/course/content2/${obj[1]?string('yyyyMM')}/${obj[0]}2.html">
						                           	 	<span>查看课程</span>
						                            </a>
					                            </p>
				                            [/#if]
				                            [#if tabFlag=="favorite"]
					                            <div class="mask">
					                            	<span class="qxsc"  title="移除收藏">
					                            	   <input type="hidden" value="${obj[0]}"/>
					                            		▬
					                            	</span>
					                            </div>
				                            [/#if]
			                            </div>
			                        </li>
			                       [/#list] 
			                        <div class="clearfix" style="height:10px;"></div>
		                        [#else]
		                        	<p class="course_none"> 暂无课程</p>
			                    [/#if]   
		                    </ul>
						</div>
					</div>
						
					</div>
				</div>
		</div>
	<div class="clearh"></div>
</div>
</form>
<!-- InstanceEndEditable -->
<div class="clearh"></div>
 [#include "/moc/include/footer.ftl" /]
</body>

<!-- InstanceEnd --></html>
