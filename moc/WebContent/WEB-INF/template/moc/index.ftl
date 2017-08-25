<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta property="qc:admins" content="253734735365736337" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>谋刻职业教育在线测评与学习平台</title>
<LINK href="favicon.ico" type="image/x-icon" rel=icon>
<link rel="stylesheet" type="text/css" href="${base}/resources/moc/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/moc/css/tab.css" media="screen">
<link rel="stylesheet" type="text/css" href="${base}/resources/moc/css/main.css" id="main-css">
<link href="${base}/resources/moc/css/common.css" rel="stylesheet" type="text/css" />

<script  type="text/javascript" src="${base}/resources/moc/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/rev-setting-1.js"></script>
<script  type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/moc/rs-plugin/js/jquery.themepunch.revolution.min.js"></script>


<!--课程选项卡-->
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


</head>

<body>

[#include "/moc/include/header.ftl" /]
   
<div class="content">
<div class="fullwidthbanner-container">
		<div id="revolution-slider" style="max-height:500px !important; background:#46B0AC">
			<ul>
            	 <li data-transition="fade" data-slotamount="10" data-masterspeed="300" data-thumb="${base}/resources/moc/images-slider/thumbs/thumb1.jpg">
					<img src="${base}/resources/moc/images-slider/wide2.jpg" alt="" />

					<div class="tp-caption sfl"
						data-x="-130"
						data-y="100"
						data-speed="300"
						data-start="300"
						data-easing="easeOutBack">
						<img src="${base}/resources/moc/images-slider/rb.png" alt="">
					</div>

					<div class="caption sfb"
						data-x="0"
						data-y="130"
						data-speed="300"
						data-start="800"
						data-easing="easeOutExpo">
						<h2>谋刻网全新上线</h2>
					</div>

					<div class="tp-caption med-white sfl"
						data-x="0"
						data-y="220"
						data-speed="300"
						data-start="1000"
						data-easing="easeOutExpo" style="font-size:20px;">
						请自备小板凳，强势围观。
					</div>

					<div class="tp-caption small-white sfr"
						data-x="0"
						data-y="260"
						data-speed="300"
						data-start="1200"
						data-easing="easeOutExpo">
						<a href="${base}/question/industry.html" target="_blank" class="btn btn-large btn-primary">在线测评</a>
					</div>

					<div class="tp-caption small-white lfb"
						data-x="300"
						data-y="90"
						data-speed="1000"
						data-start="1400"
						data-easing="easeOutExpo">
						<img src="${base}/resources/moc/images-slider/slider-10.png" alt="" />
					</div>
				</li>
				 <li data-transition="fade" data-slotamount="10" data-masterspeed="300" data-thumb="images-slider/thumbs/thumb1.jpg">
					<img src="${base}/resources/moc/images-slider/wide3.jpg" alt="" />

					<div class="tp-caption sfl"
						data-x="-130"
						data-y="150"
						data-speed="300"
						data-start="300"
						data-easing="easeOutBack">
						<img src="${base}/resources/moc/images-slider/rb.png" alt="">
					</div>

					<div class="tp-caption med-white lfl"
						data-x="0"
						data-y="200"
						data-speed="300"
						data-start="800"
						data-easing="easeOutExpo" style="font-size:18px; line-height:25px;">
						<p>我们一直致力于优秀的职业教育，<br/>为你搭建成就梦想的舞台。</p>
					</div>

					<div class="caption sfb very-big-white"
						data-x="700"
						data-y="230"
						data-speed="300"
						data-start="1200"
						data-easing="easeOutExpo">
						<i>winner</i>
					</div>
					<div class="tp-caption med-white lfl"
						data-x="0"
						data-y="150"
						data-speed="300"
						data-start="800"
						data-easing="easeOutExpo" style="font-size:22px; line-height:30px;">
						<h2 style="font-size:30px">职业教育在线测评与学习平台</h2>
					</div>

					<div class="tp-caption small-white lfr"
						data-x="0"
						data-y="280"
						data-speed="300"
						data-start="1600"
						data-easing="easeOutExpo">
						<a href="${base}/course/list.jhtml" target="_blank" class="btn btn-large btn-primary">在线学习</a>
					</div>


					<div class="tp-caption lfb"
						data-x="390"
						data-y="90"
						data-speed="1000"
						data-start="2000"
						data-easing="easeOutExpo"
						data-endeasing="fade" style="left:700px"
						>
						<img src="${base}/resources/moc/images-slider/guy-shadow.png" alt="">
					</div>

					<div class="tp-caption small-white lft"
						data-x="240"
						data-y="0"
						data-speed="2000"
						data-start="2600"
						data-easing="easeOutExpo">
						<img src="${base}/resources/moc/images-slider/spotlight.png" alt="">
					</div>

					<div class="tp-caption fade"
						data-x="390"
						data-y="90"
						data-speed="2000"
						data-start="2600"
						data-easing="easeOutExpo">
						<img src="${base}/resources/moc/images-slider/guy.png" alt="">
					</div>
				</li>
				
				<li data-transition="fade" data-slotamount="10" data-masterspeed="300" data-thumb="${base}/resources/moc/images-slider/thumbs/thumb1.jpg">
					<img src="${base}/resources/moc/images-slider/wide1.jpg" alt="" />

					<div class="caption large_text sfb"
						data-x="300"
						data-y="207"
						data-speed="300"
						data-start="800"
						data-easing="easeOutExpo">
						<img src="${base}/resources/moc/images-slider/our-features.png" alt="">
					</div>

					<div class="tp-caption sfr"
						data-x="220"
						data-y="115"
						data-speed="300"
						data-start="1000"
						data-easing="easeOutBack">
						<img src="${base}/resources/moc/images-slider/lu.png" alt="">
					</div>

					<div class="tp-caption sfr"
						data-x="-50"
						data-y="110"
						data-speed="300"
						data-start="1200"
						data-easing="easeOutBack">
						<img src="${base}/resources/moc/images-slider/clean.png" alt="">
					</div>

					<div class="tp-caption sfr"
						data-x="180"
						data-y="217"
						data-speed="300"
						data-start="1400"
						data-easing="easeOutBack">
						<img src="${base}/resources/moc/images-slider/lm.png" alt="">
					</div>

					<div class="tp-caption sfr"
						data-x="-20"
						data-y="230"
						data-speed="300"
						data-start="1600"
						data-easing="easeOutBack">
						<img src="${base}/resources/moc/images-slider/responsive.png" alt="">
					</div>

					<div class="tp-caption sfr"
						data-x="220"
						data-y="285"
						data-speed="300"
						data-start="1800"
						data-easing="easeOutBack">
						<img src="${base}/resources/moc/images-slider/lb.png" alt="">
					</div>

					<div class="tp-caption sfr"
						data-x="40"
						data-y="360"
						data-speed="300"
						data-start="2000"
						data-easing="easeOutBack">
						<img src="${base}/resources/moc/images-slider/bootstrap.png" alt="">
					</div>

					<div class="tp-caption sfl"
						data-x="625"
						data-y="115"
						data-speed="300"
						data-start="2200"
						data-easing="easeOutBack">
						<img src="${base}/resources/moc/images-slider/ru.png" alt="">
					</div>

					<div class="tp-caption sfl"
						data-x="730"
						data-y="85"
						data-speed="300"
						data-start="2400"
						data-easing="easeOutBack">
						<img src="${base}/resources/moc/images-slider/solid.png" alt="">
					</div>

					<div class="tp-caption sfl"
						data-x="650"
						data-y="217"
						data-speed="300"
						data-start="2600"
						data-easing="easeOutBack">
						<img src="${base}/resources/moc/images-slider/rm.png" alt="">
					</div>

					<div class="tp-caption sfl"
						data-x="770"
						data-y="217"
						data-speed="300"
						data-start="2800"
						data-easing="easeOutBack">
						<img src="${base}/resources/moc/images-slider/lightweight.png" alt="">
					</div>

					<div class="tp-caption sfl"
						data-x="625"
						data-y="285"
						data-speed="300"
						data-start="3000"
						data-easing="easeOutBack">
						<img src="${base}/resources/moc/images-slider/rb.png" alt="">
					</div>

					<div class="tp-caption sfl"
						data-x="730"
						data-y="345"
						data-speed="300"
						data-start="3200"
						data-easing="easeOutBack">
						<img src="${base}/resources/moc/images-slider/fresh.png" alt="">
					</div>
				</li>

				
			   
			</ul>
		</div>
</div>

<div class="td1">
	<div class="tdcont">
    	<span class="tdimg"><img src="${base}/resources/moc/images/ico4.jpg" width="450"></span>
        <span class="tdtext">
        	<h3>完全免费课程</h3>
            <p>精心录制视频课程与讲解，全部课程完全免费。</p><br/>
            <p><a href="${base}/course/list.jhtml" target="_blank" class="btninto">开始学习</a></p>
        </span>
        <div style="clear:both"></div>
    </div>
</div>
<div class="td2">
	<div class="tdcont">
    	<span class="tdimg1"><img src="${base}/resources/moc/images/ico5.jpg" width="500"></span>
        <span class="tdtext1">
        	<h3 class="co1">智能评估题库</h3>
            <p>智能能力评估，学员可以通过具体题目，匹配相
关知识点,文字解析与视频解析相结合。</p><br/>
			<p><a href="${base}/question/industry.html" target="_blank" class="btninto">点击测评</a></p>
        </span>
        <div style="clear:both"></div>
    </div>
</div>
<div class="td1">
	<div class="tdcont np">
    	<span class="tdimg" style="margin-top:80px"><img src="${base}/resources/moc/images/ico6.jpg" width="550"></span>
        <span class="tdtext" style="margin-top:120px">
        	<h3 class="co2">智能匹配练习</h3>
            <p class="tex1">跟随视频课程同步练习，紧扣重点难
点，点点精通。</p><br/>
			<p><a href="${base}/question/industry.html" target="_blank" class="btninto">开始练习</a></p>
        </span>
        <div style="clear:both"></div>
    </div>
</div>
<div class="td2 np">
	<div class="tdcont np">
    	<span class="tdimg1 ni"><img src="${base}/resources/moc/images/ico7.png" width="300"></span>
        <span class="tdtext1" style="margin-top:100px;">
        	<h3 class="co3">全天答疑解惑</h3>
            <p class="tex1">谋刻答疑社区，老师学员共同交流，我们的讲师更是倾囊相授，有问必答。</p><br/>
            <p><a href="${base}/courseThread/questionList.jhtml?tabFlag=newestThread" target="_blank" class="btninto">提问问题</a></p>
        </span>
        <div style="clear:both"></div>
    </div>
</div>

<div class="crdiv">
		<div class="course">[#--推荐课程模块--]
			    <h2 style="margin-left:20px; font-weight:normal; font-size:34px">推荐课程</h2>
			    <div class="clearh" style="height:20px;"></div>
    
 				<div class="titleul">
				   		<div>
				   			<ul class="courseul" id="myTab3_Content${categoryId_index}" style="display:block;">
				   				
				   			[@course_recommend_list count=4 useCache=false]
				   				[#--该类别下的推荐课程--]
				   				[#list courses as course]
				   				<li>
				 				 	<div class="courselist">
				 						<a href="${base}${course.path}" target="_blank" class="cour_index">
				 							<img width="263" style="border-radius:3px 3px 0 0;" src="
					   					 			[#if course.sourceImage??]
					   					 				${course.sourceImage}
					   					 			[#else]
					   					 				${base}/resources/moc/images/memb-course.jpg
					   					 			[/#if]" >
					   					</a>
				 						<p class="courTit"><a class="cour_index" target="_blank" href="${base}${course.path}">${course.title}</a></p>
				 						<div class="gray">
				 							<span>${course.lessonNum}课时</span>
				 							[#if course.showStudentNumType=="opened"]
               									<span class="sp1">${course.studentNum}人学习</span>
               								[/#if]
                							<div style="clear:both"></div>
                						</div>
                					</div>
           						 </li>
						        [/#list][#--该类别下的推荐课程over--]
							[/@course_recommend_list]
				   			</ul>
				   		</div>
				 
				</div>[#--推荐课程主体结束--]
    			<div class="line no"></div>
	 </div>[#--推荐课程模块结束--]
	 <div class="clearh"></div>
	<span class="morecourse"><a href="${base}/course/list.jhtml" class="btnlink">更多课程</a></span>
</div>
[#include "/moc/include/footer.ftl" /]
<script>
function logmine(){
	document.getElementById("lne").style.display="block";
}
function logclose(){
	document.getElementById("lne").style.display="none";	
}
$(".em").hover(function() {
		$(".showem").toggle();
	});
	$(".qq").hover(function() {
		$(".showqq").toggle();
	});
	$(".wb").hover(function() {
		$(".showwb").toggle();
	});
	$("#top").click(function() {
		if (scroll == "off") return;
		$("html,body").animate({
			scrollTop: 0
		},
		600);
	});
</script>
</body>
</html>
<script>
function logmine(){
	document.getElementById("lne").style.display="block";
}
function logclose(){
	document.getElementById("lne").style.display="none";	
}
</script>
