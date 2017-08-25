<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
[#assign paseLearn=true]
[#if currentLesson.id?? && currentLesson.id!=""]
	[#assign paseLearn=false]
[/#if]
<meta charset="utf-8">
[#--360用极速模式打开--]
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<title>谋刻职业教育在线测评与学习平台</title>
<link href="${base}/resources/moc/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/moc/css/course.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/moc/css/tab.css" media="screen" rel="stylesheet" type="text/css"/>
<script src="${base}/resources/moc/js/jquery-1.8.0.min.js" type="text/javascript" ></script>
<script src="${base}/resources/moc/editor/kindeditor.js" type="text/javascript" ></script>
<script src="${base}/resources/moc/layer/layer.js"></script>
<script src="${base}/resources/moc/js/mine.js" type="text/javascript" ></script>
<script src="${base}/resources/moc/js/common.js" type="text/javascript" ></script>
<script src="${base}/resources/moc/js/jquery.tabs.js" type="text/javascript" ></script>
<script type="text/javascript">
KindEditor.ready(function(K) {
			editorNote =KindEditor.create('#textNote', {
								resizeType:1,
								pasteType : 1,
								width:"100%",
								height:"450px",
								items:["bold", "italic","forecolor","underline","|","insertorderedlist","insertunorderedlist"
								,"|","link","unlink","|","removeformat","source"],
								afterBlur: function(){this.sync();}
							});
			editorThread =KindEditor.create("#textQuestion", {
							resizeType:1,
							pasteType : 1,
							width:"95%",
							height:"100px",
							items:["bold", "italic","forecolor","underline","|","insertorderedlist","insertunorderedlist"
							,"|","link","unlink","|","removeformat","source"],
							afterBlur: function(){this.sync();}
						});
			
			editorToPost =KindEditor.create("#goPost", {
							resizeType:1,
							pasteType : 1,
							width:"95%",
							height:"100px",
							items:["bold", "italic","forecolor","underline","|","insertorderedlist","insertunorderedlist"
							,"|","link","unlink","|","removeformat"],
							afterBlur: function(){this.sync();}
						});
		});
</script>
<script type="text/javascript">
window.onresize=function(){
	var hclinet = $(window).height();
	var wclinet = $(window).width();	
	$(".tabcard").height(hclinet - 20);	
	$(".linevideo").height(hclinet);
	$(".signp").width(wclinet - 400).css("width", wclinet - 420);

}
$(function() {
	var hclinet = $(window).height();
	var wclinet = $(window).width();
    $(".tabcard").height(hclinet - 45);	
	$(".linevideo").height(hclinet);
	$(".signp").width(wclinet - 400).css("width", wclinet - 420);
	var inum = 0;
	$(".ii").click(function() {
		inum++;
		if (inum % 2 != 0) {
			$(".interact").animate({
				right: '-360px'
			},
			'fast');
			$(".linevideo").css("width", "100%");
			$(".vframe1").css("width", "100%");
			$(".vframe2").css("width", "96%");
			$(".vframe2").css("margin", "0 auto");
			$(this).text("<");
			$(".signp").width("100%").css("width", "100%");
		} else {
			$(".interact").animate({
				right: '0px'
			},
			'fast');
			$(".vframe2").css("width", "auto");
			$(".vframe2").css("margin-right", "370px");
		}
	});
});
</script>
<script type="text/javascript">
$().ready(function() {
	var $lesson = $(".cataloglink");
	var $nextcourse =$(".nextcourse");
	var $saveNoteForm = $("#saveNote");
	var $threadForm = $("#threadForm");
	var $saveNoteBtn = $("#saveNoteBtn");
	var $note = $("#note");
	var $noteId =$("#noteId");
	var $saveThreadBtn = $("#saveThreadBtn");
	var $cardInit = $(".cardInit");
	var $lessonId = $("#lessonId");
	var $courseId = $("#courseId");
	var $questionBtn = $(".questionBtn");
	var $questionList = $("#questionList");
	var $questionPosts=$("#questionPosts");
	var $studyFinish =$("#studyFinish");
	var $peptitle =$(".peptitle");
	var $questionBody=$("#questionBody");
	var $questionAnswers=$("#questionAnswers");
	var $saveAnswerBtn=$("#saveAnswerBtn");
	var $askInfo=$("#askInfo");
	var $iframe=$("#iframe");
	var $courseMaterial=$("#courseMaterial");
	var $mainframeid=$("#mainframeid");
	
	var username = getCookie("username");
	var $currentLessonId =$("#currentLessonId");
	var $backCourse = $("#backCourse");
	
 	var $selectedLesson = $(".selectedLesson");
 	var currentLessonId=$("#currentLessonId").val();
 	
 	 var Y =$("#lesson_${currentLesson.id}").offset().top;  //jquery的方法，取得对应组件的坐标
	var X =$("#lesson_${currentLesson.id}").offset().left;
  	 //$(".tabcard").scrollTo(X,Y); 
  	 $(".tabcard").scrollTop(Y-200);   //控制滚动条的坐标
 	
 	
 	//当前课时是否已经学完
 	var booleanHasFF= $selectedLesson.prev("i").hasClass("ff");
 	if(booleanHasFF){
		 $studyFinish.addClass("sign01").removeClass("sign");
 	}
 	$("#currentLessonNum").text($selectedLesson.children("span:first").text());
 	lessonLearn(currentLessonId,"${course.id}");
	 	 
	
	//返回课程${base}/${course.path2}
	$backCourse.click(function(){
		$("#quitLearn").attr("value",true);
		window.location.href="${base}${course.path2}";
	});
	
	//下一个课时
	$nextcourse.click(function(){
		var $selectedLesson=$(".selectedLesson");
		var currentIndex=$lesson.index($selectedLesson);
		var $nextLesson=$lesson.eq(currentIndex+1);
		var nextLessonId=$nextLesson.attr("value");
		if(nextLessonId==null){
			$.message("error","亲，木有下一个课时了");
			return false;
		}
		var lessonCoin=$nextLesson.find("input[class='lessonCoin']").val();
		purchaseLesson(lessonCoin,nextLessonId,$nextLesson);
	});
	
	
	//提交答复
	$saveAnswerBtn.click(function(){
		var $this=$(this);
		var postContent=$this.prev().attr("value");
		var postSize=$questionPosts.children("li").size();
		var threadId=$this.attr("value");
		var memberImage;
		if(postContent==''){
			$.message("error","不能提交空值");
			return false;
		}
		$.ajax({
			url:"${base}/member/course/savePost.jhtml",
			type:"GET",
			data:{"courseThread.id":threadId,"content":postContent,"courseId":$courseId.attr("value"),"lessonId":$lessonId.attr("value")},
			dataType: "json",
			success:function(data){
				$.message("success","回复成功");
				
				memberImage=data.headImage=='null'?'${base}/resources/moc/images/0-0.JPG':data.headImage;
				//回复数加1
				var $postNumSpan=$("h5>span");
				$postNumSpan.attr("value",$postNumSpan.attr("value")+1);
				$postNumSpan.html($postNumSpan.attr("value"));
				
				$("#listPostNum"+threadId).html($postNumSpan.attr("value"));
				
				editorToPost.html("");
				$questionPosts.prepend('<li>'
	                +'<div class="vptext">'
					+'<div class="vptext1"><img width="30" src="'  
					+memberImage+'"></div>'
					+'<div class="vptext2">'
					+'<a class="peptitle" value="'+threadId+'" href="javascript:;">'
					+postContent+'</a></div>'
					+'</div>'
	               +'<p class="peptime pswer"><b class="coclass">'
	               +'<span style="float:left;"><b class="coclass">'
	               +username+'&nbsp;&nbsp;'
	               +'发表于 ${.now?string("yyyy/MM/dd")}</b></span>'
	               +'</b></p></li>'
				);
			}
		});	
	});
	
	//回复该question,并查看answers------userAndTime
	$peptitle.live('click',function(){
		var $this=$(this);
		var title=$this.html();
		var tempImage;
		
		//提问人及提问时间
		var userAndTime =$this.closest("div.vptext").next().children("span:first").html();
		var threadId=$this.attr("value");
		var temp;
		//为回复提交按钮赋值threadId
		$saveAnswerBtn.attr("value",threadId);
		$askInfo.children("p>b").html("");
		$askInfo.children("span").html("");
		$questionPosts.html("");
			//获取回复
			$.ajax({
				url:"${base}/member/course/getPosts.jhtml",
				type:"GET",
				data:{"threadId":threadId},
				dataType: "json",
				success:function(data){
					$.each(data,function(i,item){
							
							$("h5>span").attr("value",$(item).length).text($(item).length);
							//为question添加内容
							var t=(i==null||i=="")?"无":i;
							$askInfo.children("p:last").html(t);
							
							
							$.each(item,function(i,item){
								temp=item.content==null?"无":item.content;
								tempImage=item.member.headImage==null?'${base}/resources/moc/images/0-0.JPG':item.member.headImage;
								$questionPosts.append('<li>'
				                   +'<div class="vptext">'
									+'<div class="vptext1"><img width="30" src="'  
									+tempImage+'" title="'+item.title+'"></div>'
									+'<div class="vptext2">'
									+'<a class="peptitle" value="'+item.id+'" href="javascript:;">'
									+item.content+'</a></div>'
									+'</div>'
				                   +'<p class="peptime pswer"><span style="float:left;"><b class="coclass">'
				                   +item.member.username+'发表于'+getLocalTime(item.createDate)
				                   +'</b></span></p></li>'
								);
							});
					});
				}
			});	
		
		//为question添加usernameandtime,title
		$askInfo.children("p:first").children("b").html(title);
		$askInfo.children("span").append(userAndTime);
		
		//获取该question的posts
		$questionBody.fadeOut();
		$questionAnswers.fadeIn();
	});
	$("#showQuestions").click(function(){
		$questionBody.fadeIn();
		$questionAnswers.fadeOut();
	});
	
	//初始化卡片,1-笔记、2，问
	$cardInit.click(function(){
		var indexCard = $(".vmulu>li").index($(this));
		var lessonId = $lessonId.attr("value");
		var imageTemp;
		if(indexCard==4 || indexCard==0){
			return false;
		}
		
		$.ajax({
			url:"${base}/member/course/cardInit.jhtml",
			type:"GET",
			data:{"lessonId":lessonId,"indexCard":indexCard,"courseId":${course.id}},
			dataType: "json",
			cache:false,
			success:function(data){
				//alert(data.length);
				$questionList.html("");
				$courseMaterial.html("");
				
				if(data.length<1 && indexCard==3){
					$courseMaterial.append('<p style="text-align:center;">此课时暂无资料</p>');
				}
				
				$.each(data,function(i,item){
					if(indexCard==1){
							$("#noteId").attr("value",item.id);
							editorNote.html(item.content);					
					}else if(indexCard==2){
						imageTemp=item.member.headImage==null?'${base}/resources/moc/images/0-0.JPG':item.member.headImage;
						$questionList.append('<li><div class="vptext">'
							+'<div class="vptext1"><img width="30" src="'  
							+imageTemp+'" title="'+item.title+'"></div>'
							+'<div class="vptext2">'
							+'<a class="peptitle" value="'+item.id+'" href="javascript:;">'
							+item.title+'</a></div>'
							+'</div>'
							+'<p class="peptime pswer"><span style="float:left;">'
							+'<b class="coclass">'+item.member.username+'发表于'+getLocalTime(item.createDate)+'</b></span>'
							+'<span class="pepask" style="float:right;">回答('
							+'<strong style="color:#3eb0e0;"><a id="listPostNum'+item.id+'" href="#"'
							+'class="bluelink">'+item.postNum+'</a></strong>)&nbsp;&nbsp;&nbsp;&nbsp;浏览'
							+'(<strong style="color:#3eb0e0 ;"><a href="#"'
							+'class="bluelink">'+item.hitNum+'</a></strong>)</span></p>'
							+'</li>');
					}else if(indexCard==3){
						$courseMaterial.append('<p><a href="'
						+'${setting.cloudServerSite}/file/download/'
						+item.fileUri+'.'+item.fileMime+'/'
						+item.uploadFiles.id
						+'.jhtml" target="_blank" class="peptitle">'
						+item.title+'</a></p>');
					}
				});
			}
		});
	});
	
	//问答内容的显示隐藏
	$("#askQustion").focus(function(){
		$(".questionBody").fadeIn();
	});
	$("#textQuestion").siblings("a:last").click(function(){
		$(".questionBody").fadeOut();
	});

	//切换课时---更换页面中课时的id
	$lesson.click(function(){
		var $this =$(this);
		var lessonId = $this.attr("value");
		var lessonCoin=$(this).find("input[class='lessonCoin']").val();
		purchaseLesson(lessonCoin,lessonId,$this);
	});
	
	//保存笔记
	$saveNoteBtn.click(function(){
		//验证是否有笔记
		if($("#textNote").attr("value")==''){
			$.message("error","亲，您木有写笔记哦");
			return false;
		}
		$.ajax({
			url: "${base}/member/course/saveNote.jhtml",
			type: "POST",
			data: $saveNoteForm.serialize(),
			dataType: "json",
			cache: false,
			success: function(data) {
				//操作成功后需返回笔记的id
				$("#noteId").attr("value",data.id);
				$.message("success",data.message);
			}
		});
	});
	
	//已学习
	$studyFinish.click(function(){
		//是否有对号
		var $this=$(this);
		if($this.hasClass("sign01")){
			$.message("error","亲，课程已学过了");
			return false;
		}
	      //获得当前课时Id
	      var lessonId=$lessonId.attr("value");
		  $.ajax({
				url: "${base}/member/course/learnFinish.jhtml",
				type: "GET",
				data: {"lessonId":lessonId},
				dataType: "json",
				cache: false,
				success: function(message) {
				    $.message(message);
					if(message.type=="success"){
					    $(".selectedLesson").prev("i").addClass("ff").removeClass("nn fn");
					    $this.addClass("sign01").removeClass("sign");
					}
				}
			});
	});
	
	//保存问答--需返回该question的id
	$saveThreadBtn.click(function(){
		var title = $("#askQustion").attr("value");
		if(title=='我要提问'){
			$.message("error","亲，请填写标题");
			return false;
		}
		var content=$("#textQuestion").attr("value");
		$.ajax({
			url: "${base}/member/course/saveThread.jhtml",
			type: "POST",[#--问答必须有类别id--]
			data: {"courseCategory.id":"${course.courseCategory.id}","title":title,"content":content,"course.id":$courseId.attr("value"),"courseLesson.id":$lessonId.attr("value")},
			dataType: "json",
			cache: false,
			async:false,
			success: function(thread) {
				$.message("success","操作成功");
				editorThread.html("");
				$questionList.prepend('<li>'
							+'<div class="vptext">'
							+'<div class="vptext1"><img width="30" src="'  
							+(thread.headImage==null?"${base}/resources/moc/images/0-0.JPG":thread.headImage)
							+'" title="'+title+'"></div>'
							+'<div class="vptext2">'
							+'<a class="peptitle"  value="'+thread.id+'" href="javascript:;">'
							+title+'</a></div>'
							+'</div>'
							+'<p class="peptime pswer"><span style="float:left;">'
							+'<b class="coclass">'+username+'发表于${.now?string("yyyy/MM/dd")}</b></span>'
							+'<span style="float:right;">回答('
							+'<strong style="color:#3eb0e0;"><a id="listPostNum'+thread.id+'" href="#"'
							+'class="bluelink">0</a></strong>)&nbsp;&nbsp;&nbsp;&nbsp;浏览'
							+'(<strong style="color:#3eb0e0 ;">'
							+'<a href="#" class="bluelink">0</a></strong>)</span></p>'
							+'</li>');
				$("#askQustion").attr("value","我要提问");
				$(".questionBody").fadeOut();
			}
		});
	});
	
	$('.demo2').Tabs({
		event:'click'
	});
	
	
	
});


//金币购买课时
function purchaseLesson(lessonCoin,lessonId,$this){
		var $iframe=$("#iframe");
		var $mainframeid=$("#mainframeid");
		var $lesson = $(".cataloglink");
		var $nextcourse =$(".nextcourse");
		if(lessonCoin=='' || lessonCoin==null){
			lessonCoin=0;
		}
		if(lessonCoin>0){
		    //为了防止用户做了任务金币却没有更新
			$.ajax({
						url: "${base}/member/course/findMemberCoin.jhtml",
						type: "POST",
						data: {},
						dataType: "text",
						async:false,
						cache: false,
						success: function(data) {
							var tempCoin=parseInt(data);
							$("#memberCoin").val(tempCoin);
						}
					});
		   var memberCoin=$("#memberCoin").val();
		   if(parseInt(memberCoin)>=parseInt(lessonCoin)){
		        $.ajax({
						url: "${base}/member/course/hasBuyLesson.jhtml",
						type: "POST",
						data: {"lessonId":lessonId,"lessonCoin":lessonCoin},
						dataType: "text",
						async:false,
						cache: false,
						success: function(data) {
							//没有购买，提示消耗金币购买
						    if(data=='false'){
						    	$(".wrapper").attr("style","position:relative;z-index:1;");
		    			        var costCoinStr="观看该视频需要<span style='color:#FF910C'>"+lessonCoin+"个金币，确认购买</span>";
						    	var index=layer.confirm(costCoinStr, {
								    btn: ['确认','取消'], //按钮
								    shade: 0.3 //显示遮罩
								}, function(){
								    $.ajax({
										url: "${base}/member/course/spendCoin.jhtml",
										type: "POST",
										data: {"lessonCoin":lessonCoin},
										dataType: "text",
										async:false,
										cache: false,
										success: function(data) {
											lessonLearn(lessonId,"${course.id}");
											switchLesson(lessonId,$this,data,index);
										}
									});
								}, function(){
								    layer.msg('<span style="color:#FF910C;">亲，您取消了消耗学币购买该课程</span>', {shift: 6});
								    $(".wrapper").attr("style","");
								});
						    }else if(data=='unEnounghCoin'){
						    	//积分不足，无法购买课程
						    	doTask(lessonCoin);
						    }else{
						    	switchLesson(lessonId,$this,"","");
						    }
						}
					});
		   }else{
		   		doTask(lessonCoin);
		   }
		}else{
			lessonLearn(lessonId,"${course.id}");
			switchLesson(lessonId,$this,"","");
		}
}
function getLocalTime(dataString) {     
  return new Date(parseInt(Date.parse(dataString.replace(/-/g, "/")))).toLocaleDateString();
} 

function switchLesson(lessonId,$this,data,index){
	var $iframe=$("#iframe");
	var $mainframeid=$("#mainframeid");
    var $studyFinish =$("#studyFinish");
    var $currentLessonId =$("#currentLessonId");
    var $lesson = $(".cataloglink");
	var $nextcourse =$(".nextcourse");
    
	$("#lessonId").attr("value",lessonId);
	editorNote.html("");
	//视频的当前lesson
	$("#currentLessonNum").text($this.children("span:first").text());
	$("#currentLessonTitle").text($this.children("span:last").text());
	
	$(".selectedLesson").removeClass("selectedLesson");
	$this.addClass("selectedLesson");

	//当前课时是否已经学完
	var booleanHasFF= $this.prev("i").hasClass("ff");
	if(booleanHasFF){
		$studyFinish.addClass("sign01").removeClass("sign");
	}else{
		$studyFinish.addClass("sign").removeClass("sign01");
	}
	$iframe.attr("src","${base}/member/course/toMedia.jhtml?lessonId="+lessonId+"&courseId=${course.id}");
	$currentLessonId.attr("value",lessonId);
	$mainframeid.attr("src","${base}/member/course/toEvalution.jhtml?lessonId="+lessonId);
	if(data!=null && data!=''){
		setTimeout(function() {
			//操作成功后播放该课时
			layer.msg('<span style="color:#FF910C;">该视频消耗了'+data+'个学币,购买成功</span>', {shift: 6});
		}, 1000);
		if(index!=null && index!=''){
			//关闭当前层
		    layer.close(index);
	    }
       $(".wrapper").attr("style","");
	}
} 
//学币不足，提醒做任务
function doTask(lessonCoin){
	$(".wrapper").attr("style","position:relative;z-index:1;");
	//学币不足，提醒做任务
	var unEnoughCoinStr="该视频需要<span style='color:#FF910C'>"+lessonCoin+"个学币</span>,你所剩学币无法购买，您可以通过<span style='color:#FF910C'>做任务赚取金币</span>";
	var index=layer.confirm(''+unEnoughCoinStr+'', {
	    btn: ['做任务去','留在该页面'], //按钮
	    shade: 0.3 //不显示遮罩
	}, function(){
	    window.open('${base}/member/integralRule/list.jhtml','_blank');
	    //关闭当前层
	    layer.close(index);
	    $(".wrapper").attr("style","");
	}, function(){
	    //关闭当前层
	    layer.close(index);
	     $(".wrapper").attr("style","");
	});
}

function lessonLearn(lessonId,courseId){
	$.ajax({
		url: "${base}/member/course/lessonLearn.jhtml",
		type: "POST",
		data: {"courseId":courseId,"lessonId":lessonId},
		dataType: "json",
		cache: false,
		success: function(message) {
		  
		}
	});
}   
</script>
<style>
.selectedLesson{
	color:red;
}
.wrapper{
	bottom: 0;left: 0;position: fixed;right: 0;top: 0;
}
.linevideo {
	background: #202020;float:left;width:100%;
}
.interact{
	position:relative;float:right;width:360px;margin-left:-360px;background: #fff;
}
.vframe1{
	bottom: 50px;position: absolute;top: 50px;width: 100%;
}
.vframe2{
	margin-right:370px;height:100%;position:relative;
}
</style>
</head>
<body>
<div class="wrapper" style="">
<input type="hidden" id="memberCoin" value="${memberCoin}"/>
<input type="hidden" id ="currentLessonId" value="${currentLesson.id}"/>
<input type="hidden" id ="quitLearn" value="false" />
<div class="linevideo">[#--左侧栏--]
	 
	<span class="returnindex"><a class="gray" id="backCourse" href="javascript:;" style="font-size:14px;">返回课程</a></span>   
    <span class="taskspan">
    	<span class="ts" id="currentLessonNum">
		</span> 
		<b class="tasktit" id="currentLessonTitle">
				&nbsp;&nbsp;${currentLesson.title}
		</b>
	</span>
    
	<div class="vframe1">
		<div class="vframe2">	
		    <iframe id="iframe" scrolling="no" class="videoifram" name="iframe" style="width:100%; height:100%;margin-top:10px;" allowTransparency="true"
				 src="${base}/member/course/toMedia.jhtml?lessonId=${currentLesson.id}&courseId=${course.id}" 
				frameborder="0" >
		 	</iframe>
			<p class="signp"><span class="sign" id="studyFinish">学过了</span><span class="nextcourse" title="下一课时">∨</span></p>
		</div>  
    </div> 
</div> [#--左结束--]   
<div class="interact">[#--右开始--]
   		<span class="ii" title="展开或收起">></span>
         <div class="clearh"></div>
  
          <div class="box1 demo2">
			<ul class="tab_menu vmulu">
				<li class="current">目录</li>
				<li class="cardInit">笔记</li>
				<li class="cardInit">问答</li>
				<li class="cardInit">资料</li>
                <li class="cardInit">测评</li>
				
                
            </ul>
			<div class="tab_box tabcard">
				<div>
					<dl class="mulu noo1">
						[#assign x=0]
						[#assign y=0]
						[#assign lessonIndex=0]
						[#assign sameChapter=0]
                        [#list chapters as chapter]
                        	[#if chapter.id !=sameChapter]
                        		[#assign sameChapter=chapter.id]
                        	
                        	[#if chapter.type == 'chapter']
                        		[#assign x=x+1]
                        		[#assign y=0]
                        		<dt>第${x}章&nbsp;&nbsp;${chapter.title}</dt>
                        		[#if chapter.courseLessons??]
                        			[#list chapter.courseLessons as lesson]
                        			[#assign lessonIndex=lessonIndex+1]
                        				<dd>
		                    				<i class="forwa  
	                        				[#--课时学习状态--]
	                        				[#assign noChapterLearn=1]
	                        				[#list courseLessonLearns as lessonLearn]
	                        					[#if lessonLearn.courseLesson==lesson]
	                        						[#assign noChapterLearn=0]
	                        						[#if lessonLearn.status=="learning"]
	                        							fn
	                        						[#else]
	                        							ff
	                        						[/#if]
	                        					[/#if]	
	                        				[/#list]
	                        				[#if noChapterLearn==1]
	                        					nn
	                        				[/#if]
	                        					"></i>
	                        				[#if lesson.status=='published']
			                    				<strong id="lesson_${lesson.id}" value="${lesson.id}"  class="cataloglink [#if currentLesson?? && currentLesson.id==lesson.id] selectedLesson[/#if]" style="cursor:pointer">
			                    					<input type="hidden" class="lessonCoin" value="${lesson.coin}"/>
			                    					<span>课时${lessonIndex}:   </span><span>${lesson.title}</span>
			                    				</strong>
		                    				[#else]
		                    					<span>&nbsp;&nbsp;</span><span class="gray">课时${lessonIndex}:${lesson.title}&nbsp;&nbsp;(未发布)</span>
		                    				[/#if]
                        				</dd>
                        			[/#list]
                        		[/#if]
                        	[#else]
                        		[#assign y=y+1]
                        		<dd class="smalltitle"><strong>第${y}节 ${chapter.title}</strong></dd>
                        		[#if chapter.courseLessons??]
                        			[#list chapter.courseLessons as lesson]
                        			[#assign lessonIndex=lessonIndex+1]
                        				<dd>
                        					<i class="forwa  
                        				[#--课时学习状态--]
	                        				[#assign noLearn=1]
	                        				[#list courseLessonLearns as lessonLearn]
	                        					[#if lessonLearn.courseLesson==lesson]
	                        						[#assign noLearn=0]
	                        						[#if lessonLearn.status=="learning"]
	                        							fn
	                        						[#else]
	                        							ff
	                        						[/#if]
	                        					[/#if]	
	                        				[/#list]
	                        				[#if noLearn==1]
	                        					nn
	                        				[/#if]
                        				"></i>
                        					[#if lesson.status=='published']
                        						<strong id="lesson_${lesson.id}" value="${lesson.id}" class="cataloglink [#if currentLesson?? && currentLesson.id==lesson.id] selectedLesson[/#if]" style="cursor:pointer">
                        							<input type="hidden" class="lessonCoin" value="${lesson.coin}"/>
                        							<span>课时${lessonIndex}</span>：<span>${lesson.title}</span>
                        						</strong>
                        					[#else]
                        						<span>&nbsp;&nbsp;</span><span class="gray">课时${lessonIndex}:${lesson.title}&nbsp;&nbsp;(未发布)</span>
                        					[/#if]
                        				</dd>
                        			[/#list]
                        		[/#if]
                        	[/#if]
                        [/#if]
                        [/#list]
    				</dl>
				</div>
				<div class="hide">
					<div>
                    <div style="padding-left:25px">
                    	<div class="c_eform" style="width:280px;margin-left:10px;">
                    		<div class="clearh"></div>
	                        <form id="saveNote">
	                        	<textarea id="textNote" name="content">[#if paseLearn]无课时[/#if] </textarea>
	                        	<input id="courseId" type="hidden" name="course.id" value="${course.id}" />
	      					 	<input id="lessonId" type="hidden" name="courseLesson.id" value="${currentLesson.id}" />
	      					 	<input id="noteId" type="hidden" name="id"/>
	 	                        <input type="radio" name="status" class="bj_type" value="1" />公开
		                        <input type="radio" name="status" class="bj_type" checked value="0" />私有
	                        </form>
	                       <a href="javascript:;" style="margin-right:10px;" class="fombtn" [#if paseLearn]title="无课时不能保存笔记"[#else]id="saveNoteBtn"[/#if]>提交</a>
	                       <div class="clearh"></div>
                       </div>
                    </div>
				</div>
				</div>
                <div class="hide">
                	<div id="questionAnswers" class="hide c_eform" style="width:90%">
                		<div style="margin-left:20px;margin-right:10px; width:280px">
      		  				<a href="javascript:;" class="fombtn rbtn" id="showQuestions" >返回</a>
	                    	<div class="clearh" ></div>
                    		<span id="askInfo" style="display:block; padding-bottom:20px; border-bottom:1px solid #eaeaea">
                    			<p style="width:100%; color:#333;font-size: 14px;"><b>标题：</b></p>
                    			<div class="clearh"></div>
                    			<p style="width:100%;font-size: 14px;"></p>
                    			<span style="display:block; margin-top:10px">
                    				
                    			</span>
                    		</span>
	                    </div>
	                    <div style="border-top:1px solid #ccc;position: relative;">
	                    	<h5 style="position: absolute; top: -10px;left: 15px;background: #fff;padding: 0 5px;color: #333">
	                    		<span></span>回答
	                    	</h5>
	                    </div>
	                    <div class="clearh"></div>
						<ul class="evalucourse" id="questionPosts" style="margin-right:10px;width:280px; font-size: 14px;">
	                    </ul>
	                    <div class="clearh"></div>
	                    <div style="margin-left:20px;">
		                    <textarea id="goPost"></textarea>
		                    <a id="saveAnswerBtn" href="javascript:;" style="margin-right:15px;" class="fombtn">回复</a>
	          		  	</div>
                	</div>
					
					<div id="questionBody" style="padding-left:15px;">
	                    <div class="c_eform veform">
                    		<div class="clearh" ></div>
                    		<span>标题：</span>
                    		<input id="askQustion" value="我要提问" onblur="if (this.value =='') this.value='我要提问';" onclick="if (this.value=='我要提问') this.value='';" name="title" class="inputitle" type="text"/><br/>
                    		<div class="questionBody" style="margin-top:10px;display:none">
                  		  		<textarea id="textQuestion"></textarea>
                  		  		<a id="saveThreadBtn" style="margin-right:15px;" href="#" class="fombtn">发布</a>
                  		  		<a href="javascript:void(0);" class="fombtn">取消</a>
                  		  	</div>
                       		<div class="clearh"></div>
	                    </div>
						<ul class="evalucourse" id="questionList" style="width:280px;">
	                    </ul>
					</div>
				</div>
				<div class="hide">
                    <div class="c_eform veform" style="margin-left:35px;" id="courseMaterial">
				    </div>
				</div>
				
				<div class="hide" style="height:100%;">				
					   <!--<p>此课时暂无作业</p>-->
					  <iframe width="100%" height="100%" id="mainframeid"  name="mainframeid" marginwidth="0" scrolling="no"
	               	 	marginheight="0" frameborder="0"  src="${base}/member/course/toEvalution.jhtml?lessonId=${currentLesson.id}"   allowtransparency="true"></iframe>
	
				</div>
			</div>
		</div>
    </div>[#--右结束--]
</div>
<script>
//var wintop=document.getElementById('lesson_${currentLesson.id}').offsetTop + (document.body||document.documentElement).scrollTop;
window.scrollTo("0","800px");
</script>
</body>
</html>

