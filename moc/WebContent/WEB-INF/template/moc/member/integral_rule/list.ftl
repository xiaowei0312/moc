<!doctype html>
<html><!-- InstanceBegin template="/Templates/dwt.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">
<!-- InstanceBeginEditable name="doctitle" -->
<title>谋刻职业教育在线测评与学习平台</title>
<link rel="stylesheet" href="${base}/resources/moc/css/course.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/common.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/member.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/tab.css" media="screen">

<script  type="text/javascript" src="${base}/resources/moc/js/jquery.js"></script>
<script  type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
<script  type="text/javascript" src="${base}/resources/moc/js/jquery.tabs.js"></script>
<script src="${base}/resources/moc/js/mine.js"></script>
<script type="text/javascript">
$(function(){	
	$('.demo2').Tabs({
		event:'click'
	});	
});	
</script>
<script type="text/javascript">
function MoveBox(obj) {
	var divTop = $(obj).offset().top;
	var divLeft = $(obj).offset().left;
	$(obj).css({ "position": "absolute", "z-index": "500", "left":  "400px", "top": divTop + "px" });
	$(obj).animate(
					{ "right": "20px", 
					 "top": ($(document).scrollTop() + 300) + "px",
			 		 "width": "100px",
		 		 	 "height": "30px",
	 		  		 fontSize:"16px" 
	 		  		},
	 		  	 500, 
	 		  	 function () {$(obj).animate(
		 		  	 			{"right":"20px",
		 		  	 			 "top": ($(document).scrollTop() + 300) + "px" 
		 		  	 			 }, 
	 		  	 			 	500,
		 		  	 			 function (){
		 		  	 			 	$(obj).fadeTo(1000,0.1).hide(0);
		 		  	 			 	$(obj).closest("tr").remove();
		 		  	 			 });
	 		  	 			}
				);
}

//taskId任务ID已领取积分
function getExpereceCoin(experence,coin,taskId){
	$.ajax({
		url:"getExperienceCoin.jhtml",
		data:{experienceValue:experence,learningCoin:coin,taskId:taskId},
		dataType:"JSON",
		cache:false,
		async:false,
		success:function(msg){
			//$.message(msg);
			var $currentCoin=$("#currentCoin");
			var $currentExperience=$("#currentExperience");
			
			$currentCoin.html(parseInt($currentCoin.html())+coin);
			$currentExperience.html(parseInt($currentExperience.html())+experence);
			
		},
		error:function(msg){
			$.message(msg);
		}
	});
}
</script>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="head" -->
<!-- InstanceEndEditable -->
</head>

<body>
[#include "/moc/include/memberHeader.ftl" /]
	<div class="membcont">
      <h3 class="mem-h3">我的任务</h3>
	  <div class="box demo2" style="width:92%;">
	        <div class="experience_head">
				<div class="experience_left">
					<div id="currentCoin" class="num">${experienceCoin[1]}</div>
					<p>您当前的学币</p>
				</div>
				<div class="experience_right">
					<div id="currentExperience" class="num">${experienceCoin[0]}</div>
					<p>您当前的经验</p>
				</div>
										
			</div>
            <div class="clearh"></div>
			<p class="explain">学币可用于购买课程。一起来开启学习旅程吧</p>
			<p class="explain">经验是您在谋刻网学习获得的，记录点滴努力和成长。一起来做经验土豪吧：）</p>
			<div class="clearh"></div>         
					<div>
						<table class="mbmlist1" width="100%" cellpadding="0" cellspacing="0" style="padding-left:0">
						[#-- 0,ir.name,    1,ir.task_type,    2,ir.experience_value,     3,ir.learning_coin,     4,ir.description,    5,ir.task_count 
							6,irt.accumulative_acount,    7,irt.is_receive,     8,irt.id,      9,irt.modify_date
							10,ir.url_path,  11,ir.app_url_path,    12,ir.id as ww,     13,irt.id as uu
						--]
						[#--4，因为currentType中没有4--]
						[#assign currentType=4]
						[#assign taskDone=false]
						[#list integralRules as allTasks]
							[#if allTasks[1]!=currentType && !allTasks[7]]
								[#assign currentType=allTasks[1]]
								<tr>
									<td width="90%" style="background:#e8e8e8;height:30px;padding-left:10px;" colspan="2">
										[#--newTask, dailyTask, overallTask--]
						  				<span class="tree_parent">
						  				[#if currentType==0]
						  					新手任务
						  				[#elseif currentType==1]
						  					日常任务
						  				[#elseif currentType==2]
						  					全局任务
						  				[/#if]
						  				</span> 
									</td>		
								</tr>
							[#elseif allTasks[7] && !taskDone]
								<tr>
									<td width="90%" style="background:#e8e8e8;height:30px;padding-left:10px;" colspan="2">
						  				<span class="tree_parent">
						  					已完成任务
						  				</span> 
									</td>		
								</tr>
							[/#if]
						  	[#if allTasks[8]??][#--已领取任务--]
						  		[#if allTasks[7]]
						  			[#--已领取学币--]
						  			[#assign taskDone=true]
						  			<tr>
										<td width="90%" style="padding-left:15px;">
							  				<span class="tree_parent">${allTasks[0]}(${allTasks[6]?default('0')}/${allTasks[5]})</span> 
										</td>
										<td width="10%">
											<p><span class="jifen_num">+${allTasks[3]}</span><img src="${base}/resources/moc/images/jifen_ico.png" width="15" height="15"/></p>
										</td>
									</tr>
						  			
						  		[#elseif allTasks[6] gte allTasks[5]][#--已完成任务，但没有领取学币--]
						  		<tr>
						  			<td width="90%" style="padding-left:15px;">
						  				<span class="tree_parent">${allTasks[0]}(${allTasks[6]?default('0')}/${allTasks[5]})</span> 
									</td>
									<td width="10%">
						 				<div>
											<p><span class="btn_jifen">
													<span class="jifen_num">+${allTasks[3]}</span>
													<img src="${base}/resources/moc/images/jifen_ico.png" width="15" height="15"/>
												</span>
												<a href="javascript:void(0);" class="get_jifen" onClick="MoveBox(this);getExpereceCoin(${allTasks[2]},${allTasks[3]},${allTasks[13]});">
													领取${allTasks[3]}学币
												</a>
											</p>
										</div>
									<td>
								</tr>
								[#else][#--没有完成的任务，任务数量达标（大于0）--]
								<tr>
									<td width="90%" style="padding-left:15px;">
					  					<span class="tree_parent">${allTasks[0]}(${allTasks[6]?default('0')}/${allTasks[5]})</span> 
									</td>
									<td width="10%">
										<p><span class="jifen_num">+${allTasks[3]}</span><img src="${base}/resources/moc/images/jifen_ico.png" width="15" height="15"/></p>
									</td>
								</tr>
						  		[/#if]
							[#else][#--没有接收的任务（任务数量为0）--]
							<tr>
								<td width="90%" style="padding-left:15px;">
					  				<span class="tree_parent">${allTasks[0]}(${allTasks[6]?default('0')}/${allTasks[5]})</span> 
								</td>
								<td width="10%">
									<p><span class="jifen_num">+${allTasks[3]}</span><img src="${base}/resources/moc/images/jifen_ico.png" width="15" height="15"/></p>
								</td>
							</tr>
							[/#if]
						[/#list]	
						</table>
					</div>		                           		                					
	  </div>
</div>


<div class="clearh"></div>
</div>

<div class="clearh"></div>
[#include "/moc/include/footer.ftl" /]
</body>
</html>
