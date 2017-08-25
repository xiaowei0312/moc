<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>谋刻职业教育在线测评与学习平台</title>
<link href="${base}/resources/moc/css/course.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/register-login.css"/>
<link href="${base}/resources/moc/css/common.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/tab.css" media="screen">
<link rel="stylesheet" href="${base}/resources/moc/css/jquery.bxslider.css">
<link rel="stylesheet" href="${base}/resources/moc/css/askarea.css"/>


<script src="${base}/resources/moc/js/jquery.js"></script>
<script src="${base}/resources/moc/js/jquery.tabs.js"></script>
<script src="${base}/resources/moc/js/mine.js"></script>
<script  type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/base64.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/prng4.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/rng.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/rsa.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/jsbn.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/loginregisterdialog.js"></script>
<script type="text/javascript">
    $().ready(function(){
        var $listForm=$("#listForm");
        var $pageNumber = $("#pageNumber");
        var tabFlag=$("#tabFlag").val();
        var $ask_tag=$(".ask_tag");
        [@flash_message /]
        
        $ask_tag.click(function(){
           var categoryId=$(this).children("input").val();
           $("#categoryId").val(categoryId);
           $listForm.submit();
        });

        if(tabFlag=="newestThread"){
            $(".qolist>a").attr("class","");
            $(".qolist>a:eq(0)").attr("class","askcurr");
        }else if(tabFlag=="hotThread"){
            $(".qolist>a").attr("class","");
            $(".qolist>a:eq(1)").attr("class","askcurr");
        }else if(tabFlag=="waitReply"){
            $(".qolist>a").attr("class","");
            $(".qolist>a:eq(2)").attr("class","askcurr");
        }
        
        $(".qolist>a").each(function(index){
            $(this).click(function(){
                if(index==0){
                    $(".qolist>a").attr("class","");
                    $(".qolist>a:eq(0)").attr("class","askcurr");
                    $("#tabFlag").val("newestThread");
                    $listForm.submit();
                }else if(index==1){
                    $(".qolist>a").attr("class","");
                    $(".qolist>a:eq(1)").attr("class","askcurr");
                    $("#tabFlag").val("hotThread");
                    $listForm.submit();
                }else if(index==2){
                    $(".qolist>a").attr("class","");
                    $(".qolist>a:eq(2)").attr("class","askcurr");
                    $("#tabFlag").val("waitReply");
                    $listForm.submit();
                }
            });
        });
        
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
   [#include "/moc/include/answerHeader.ftl" /]
<div class="clearh" style="height:20px;"></div>
<form id="listForm" action="${base}/courseThread/questionList.jhtml" method="post">
      <input type="hidden" id="pageNumber" name="pageNumber" value="${page.pageNumber}" />
	  <input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}" /> 
	  <input type="hidden" id="tabFlag" name="tabFlag" value="${tabFlag}"/> 
	  <input type="hidden" id="categoryId" name="categoryId" value="${categoryId}"/>
	  <input type="hidden" id="threadContent" name="threadContent" value="${threadContent}"/>
	
	<div class="listcont">
		<div class="askleft">
	    <div class="queoption">
	    	<h3 class="askh3">
	    		[#if courseCategory?? && courseCategory.name?? &&courseCategory.name!=null]
		    	    ${courseCategory.name}
		    	[#else]
		    	    全部问题
		    	[/#if]  
	    	</h3>
	        <span class="qolist">
		        <a class="" href="#">最新</a>
		        <a class="" href="#">热门</a> 
		        <a class="" href="#">待回复</a>
	        </span>
	     </div>
	     <div class="clearh" style="height:10px;"></div>
	     	[#if page.content?size>0]
		     	[#list page.content as obj]
			    	<div class="askarea_list">
			        	<span class="ask_user">
			                <img width="40" height="40"
			                	src=
			                	[#if obj[0].member.headImage?? && obj[0].member.headImage!=Null]
				  					"${obj[0].member.headImage}"
			  				    [#else]
			  					    "${base}/resources/moc/images/0-0.JPG"
			  				    [/#if]
		                	/>
			                <p><strong>${obj[0].member.username}</strong></p>
			            </span>
			            <span class="asklist_cont">
						    <div class="ask_top">
							    <span class="asklistp">
								    <p>
									    <a class="ask_link" href="${base}/courseThread/view.jhtml?threadId=${obj[0].id}">
									    	<strong>${obj[0].title}</strong>
									    </a>
								    </p>
									<div class="ask_bottom">
										<p>
											  <span class="ask_label">${obj[0].courseCategory.name}</span>
											  <span class="ask_label2"> 
											  		[#if obj[0].courseLesson?? && obj[0].courseLesson!=Null]
											  			[#assign courseLesson = obj[0].courseLesson /]
											  			• <a target="_blank" href="${base}/member/course/toLearn.jhtml?lessonId=${courseLesson.id}&courseId=${obj[0].course.id}&mediaId=${courseLesson.uploadFiles.id}" class="ask_link_source">
									                		源自：${obj[0].courseLesson.title}
	    											  	</a>
									                [#else]
									                	[#if obj[0].course?? && obj[0].course!=Null]
								                			源自：${obj[0].course.title}
									                	[/#if]
									                [/#if]
										  	  </span>
											  <span class="ask_last"> 
												  [#if obj[0].createDate?? && obj[0].createDate!=Null]
								                   		• ${obj[0].createDate?string("yyyy-MM-dd")}
								                  [#else]
								  						&nbsp;&nbsp;&nbsp;&nbsp;
								  				  [/#if]
											  </span>
										</p>				  
							       </div>  
								</span>
								<span class="askmas">
									<span class="am1">
									<p>${obj[0].postNum}</br>
									<a class="askfont" href="${base}/courseThread/view.jhtml?threadId=${obj[0].id}&answerFlag=answer">回复</a></p>
									</span>
									<span class="am2">
									<p>${obj[0].hitNum}</br>
									<a class="askfont" href="${base}/courseThread/view.jhtml?threadId=${obj[0].id}">浏览</a></p>
									</span>
								</span>    
							</div>				         	           
			            </span>
			        </div>
		        [/#list]
	        [#else]
		    	 <p class="course_none"> 暂无问答</p>
		 	[/#if] 
	       	[@pagination pageNumber = page.pageNumber totalPages = page.totalPages pattern = "javascript: $.pageSkip({pageNumber});"]
					[#include "/moc/include/pagination.ftl"]
		    [/@pagination]
	    </div>
	    
	    <!--askright-->
	  <div class="askright">
	    	<!--<span class="askad">
	        <img src="${base}/resources/moc/images/ad.jpg" width="200" height="200">
	        </span>
	        <div class="clearh"></div>-->
			<div style="padding-left:10px;padding-bottom:10px;">
			  <h3>话题</h3>
			  <a class="ask_tag" href="#">
			    <input type="hidden" value="0"/>
			    <span class="ask_label">所有类别</span>
			  </a>
			  [#list cateGoryList as category]
			     <a class="ask_tag" href="#">
			        <input type="hidden" value="${category.id}"/>
			        <span class="ask_label">${category.name}</span>
			     </a>
			  [/#list]
			</div>
	        <h3 style="padding-left:10px;">热门话题</h3>
	        [#list hotThreadList as obj]
		        <div class="asktopic">
		          <p class="asktopitle"><a class="ask_link" href="#">${obj[1]}</a></p>
		          <p class="labtime ati">该话题下有${obj[2]}个问题</p>
		        </div>
	        [/#list]       
	        <div class="hotuser">
	       	  <h3>热门用户</h3>
       	  	  [#list list as obj]
	            <div class="userlist">
	            	<img  width="40" height="40"
	            	src=
	            		[#if obj[2]?? && obj[2]!=Null]
		  					"${obj[2]}"
		  				[#else]
		  					"${base}/resources/moc/images/headimg-big.jpg"
		  				[/#if]
            		/>
	                <span class="usermassage">
	                	<p><a class="ask_link" href="#">${obj[1]}</a></p>
	                    <p class="rmyh">回复了${obj[3]}个问题</p>
	                </span>
	                <div class="clearh"></div>
	            </div>
            [/#list]
	    </div>
	    
	  </div>
	</div>
</form>
 [#include "/moc/include/loginRegisterDialog.ftl" /]
 [#include "/moc/include/footer.ftl" /]
</body>

</html>

