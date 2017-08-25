<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>谋刻职业教育在线测评与学习平台</title>
<link rel="stylesheet" href="${base}/resources/moc/css/register-login.css"/>
<link href="${base}/resources/moc/css/common.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/askarea.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/tab.css" media="screen">
<link rel="stylesheet" href="${base}/resources/moc/css/jquery.bxslider.css">
<link rel="stylesheet" href="${base}/resources/moc/css/askarea.css"/>

<script src="${base}/resources/moc/js/jquery.js"></script>
<script src="${base}/resources/moc/js/jquery.tabs.js"></script>
<script src="${base}/resources/moc/js/mine.js"></script>
<script  type="text/javascript" src="${base}/resources/moc/js/common.js"></script>

<script type="text/javascript">
      $().ready(function(){
      
          var answerFlag=$("#answerFlag").val();
          if(answerFlag=='answer'){
             $("#skipAnswer")[0].click();
          }
          
          $(".answerThread").click(function(){
	          var threadContent=$("#threadContent").val();

	          if(typeof(threadContent)==undefined || threadContent=='' || threadContent=='回复问题'
	          || threadContent=='回答问题...'){
	              $.message("error","请输入回复内容");
	              return false;
	          }
	          $("#threadForm").submit();
          });
      });
</script>
</head>

<body>
  [#include "/moc/include/answerHeader.ftl" /]
  <input type="hidden" id="answerFlag" value="${answerFlag}"/>
  <a id="skipAnswer" style="display:none;" href="#answrite"></a>
<div class="clearh" style="height:20px;"></div>


<div class="listcont">
	<div class="askleft">
    <div class="question">
       <p class="queflag">${courseThread.title}</p>
	   <div class="quecontent">${courseThread.content}</div>
       <div class="clearh"></div>      
	   <p>
		  <span class="ask_label">${courseThread.courseCategory.name}</span>
		  [#if courseThread.courseLesson?? && courseThread.courseLesson!=Null]
			  <span class="ask_label2">
			  	  [#assign courseLesson = courseThread.courseLesson /]
				  • <a target="_blank" href="${base}/member/course/toLearn.jhtml?lessonId=${courseLesson.id}&courseId=${courseThread.course.id}&mediaId=${courseLesson.uploadFiles.id}" class="ask_link_source">
				 	 源自：${courseThread.courseLesson.title}
				  </a>
			  </span>
		  [#else]
		  	 [#if  courseThread.course?? && courseThread.course!=Null]
			  	 <span class="ask_label2"> 
					  • <a target="_blank" href="video.html" class="ask_link_source">
					 	 源自：${courseThread.course.title}
					  </a>
				  </span>
			  [/#if]  
		  [/#if]
		  <span class="ask_last"> 
		  	• [#if courseThread.createDate?? && courseThread.createDate!=Null]
            	  ${courseThread.createDate?string("yyyy-MM-dd")}
              [#else]
				  &nbsp;&nbsp;&nbsp;&nbsp;
		      [/#if]
		  </span>
	  </p>
     </div>
	 <div class="clearh"></div>  
     <div class="que_answer">
        <!--<p class="queflag"><i class="f2"></i></p>--> 
        [#list courseThread.courseThreadPosts as courseThreadPost]        
	        <div class="answerlist">
	      	    <span class="ask_user">
	                <img  width="50" height="50"
	                   src=
	                   [#if courseThreadPost.member.headImage?? && courseThreadPost.member.headImage!=Null]
				                 "${courseThreadPost.member.headImage}"
			            [#else]
							"${base}/resources/moc/images/0-0.JPG"
						[/#if]
						/>
					<p><strong>${courseThreadPost.member.username}</strong></p>
	            </span>
	            <span class="asklist_cont qc">
	            	<p class="asklistp qp">${courseThreadPost.content}</p>
	                <p class="qcuser">
	                	<span class="labtime">
	                   		回复于
	                   		[#if courseThreadPost.createDate?? && courseThreadPost.createDate!=Null]
			                    ${courseThreadPost.createDate?string("yyyy-MM-dd")}
			                [#else]
			  					&nbsp;&nbsp;&nbsp;&nbsp;
			  				[/#if]
	                   	</span>
                   	</p>
	            </span>
	            <div class="clearh"></div>
	         </div>
         [/#list]
      </div>     
      <div class="clearh" style="height:30px;"></div>
      <div class="answrite">
      	<div class="askbox qubox">
        	<form class="askform quform" id="threadForm" action="${base}/courseThread/saveThreadPost.jhtml" method="post">
            	<input type="hidden" name="threadId" value="${courseThread.id}"/>
            	<input type="hidden" class="questionFlag" value="answer"/>
    		    <textarea class="bf2" id="threadContent" name="threadContent" rows="7"/></textarea>
            </form>
         <p class="ask_btn fb"><a class="answerThread" href="#">回复</a></p>
        </div>
      </div>
    </div>
    
    <!--askright-->
  <div class="askright">
  		<div class="askuser">
   	       <div class="askuserimg">
             <p style="text-align:left">发起人</p>
             <img width="80" height="80"
             	 src=
             	 [#if courseThread.member.headImage?? && courseThread.member.headImage!=Null]
	                 "${courseThread.member.headImage}"
	            [#else]
					"${base}/resources/moc/images/0-0.JPG"
				[/#if]
             />
			 <span class="userfloor">${courseThread.member.username}</span>
        <!--<i class="floor fl1">LV1</i> 
        等级一
        <i class="floor fl2">LV11</i> 
        等级二
        <i class="floor fl3">LV21</i> -->
        </div>
        <!--<span class="userfloor">
        	<p class="name">sherlyjia</p>
            <p class="money">1000</p>
        </span>-->
        </div>
        <div class="clearh" style="height:20px;"></div>
    	<h3>相关问题</h3>
        <ul class="hotask">
        	[#list relateThreads as relateThread] 
        		<li>
	        		<a class="ask_link" href="${base}/courseThread/view.jhtml?threadId=${relateThread.id}">
	        			<strong>${relateThread_index+1}</strong>${relateThread.title}
	        		</a>
        		</li>
        	 [/#list]
        </ul>
  </div>
</div>

<div class="clearh"></div>
 [#include "/moc/include/footer.ftl" /]
</body>

</html>

