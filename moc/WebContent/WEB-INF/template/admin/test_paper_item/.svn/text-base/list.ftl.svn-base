<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.product.add")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/moc/css/pagination.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/admin/css/exampage.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script src="${base}/resources/moc/js/jquery.pagination.js" type="text/javascript" ></script>
<script>
var h=$(window).height();
$(function(){
	/*做题页面右侧固定导航定位*/
 
var eprw=$(".exampright").width();
 var boxh=$(".examplbox").height();	
 var titleh=$(".examtime").height();
 var h3h=$(".eLeftTile").height();
boxh=h-titleh-h3h-170;
//$(".examplbox").css("height",boxh);
		
		$(".shrink").click(function(e){
			
			var $item = $(this).parent().nextAll();
			$item.slideUp();
			if($item.is(':visible')){
				$item.slideUp();
			}else{
				$item.slideDown();
			}
		});
	
	[@flash_message /]
		$('.submitAdd').click(function(){
				$.ajax({
					url: "save.jhtml",
					type: "POST",
					data:$("#itemForm").serialize(),
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
						setTimeout(function() {
									location.reload(true);
								}, 1000);
					}
				});
		});
		$('.selbtn').click(function(){
				dataCount();
		});
		dataCount();
	});
	
	
function show_children(value){
	var $item = $(value).parent("span").parent("p").siblings(".examleftchild");
			$item.slideUp();
			$(".popbox").hide();
			$(value).text("展开");
			if($item.is(':visible')){
				$(value).text("展开");
				$item.slideUp();
			}else{
				$(value).text("收起");
				$item.slideDown();
			}
}
function dataCount(){
	$.ajax({
			type : "POST",
			url : "questionCount.jhtml",
			data : {"outlineCategoryId":$('#outlineCategoryId').val(),"questionType":$('#questionType').val(),"stem":$('#stem').val()},
			dataType:"json",
			success : function(msg) {
				$("#pagination").pagination(msg, {
					ellipse_text : "...",// 	省略的页数用什么文字表示
					num_edge_entries : 0,//两侧显示的首尾分页的条目数
					num_display_entries : 5,//连续分页主体部分显示的分页条目数
					items_per_page:15,// 	每页显示的条目数
					callback:pageselectCallback
				});
			
				//获取数据信息
				function pageselectCallback(page_id, jq) {
					page_id=page_id+1;
					$.ajax( {
						type : "POST",
						url : "questionList.jhtml",
						data : {"page":page_id,"outlineCategoryId":$('#outlineCategoryId').val(),"questionType":$('#questionType').val(),"stem":$('#stem').val()},
						dataType:"text",
						success : function(msg) {
							$("#manger").html(msg);
							/*弹出框*/
							$(".tr_of_question").hover(
								function(e){
									var $X=e.clientX;
									var $Y=e.clientY;
									//alert(h+","+$X+","+$Y);
									if(h-$Y<100){
										//$(this).children(".td_of_question").children("div").css("left",$X);
										$(this).children(".td_of_question").children("div").css("top",$Y-150);
									}
									$(this).children(".td_of_question").children("div").show();
								},
								function(){
									$(this).children(".td_of_question").children("div").hide();
								}
							);
						}
					});
				}
		}
	});
	
}

function addQuestion(id,questionType,score,missScore){
		$.dialog({
			title: '增加题目',
			[@compress single_line = true]
				content: '
				<table >
					<tr>
						<th >
							&nbsp;&nbsp;目标章节:
						<\/th>
						<td>
							<select name="chapter" id="chapterId">
								[#list testpaperChapters as testpaperChapter]
									<option  value="${testpaperChapter.id}">
										${testpaperChapter.name}
									<\/option>
								[/#list]
							<\/select>
						<\/td>
						<th >&nbsp;&nbsp;本题分值：<input type="text" name="score" id="score" size="5" value="'+score+'"/>分<\/th>
						<th >&nbsp;&nbsp;漏选分值:<input type="text" name="missScore" id="missScore" size="5" value="'+missScore+'"/><\/th>
					<\/tr>
				<\/table>',
			[/@compress]
			width: 470,
			modal: true,
			ok: "${message("admin.dialog.ok")}",
			cancel: "${message("admin.dialog.cancel")}",
			onOk: function() {
				var chapterId=$('#chapterId').val();
				if(chapterId==null){
					alert("请选择一个章节。");
					return false;
				}
					var stem=$('#quetitle_'+id).text();
					var questionID=id;
					var score =$('#score').val();
					if(score=='' || score==null || score<=0){
						alert("请填写一个大于0的分值");
						return false;
					}
					var missScore=$("#missScore").val();
					if(missScore=='' || missScore==null || missScore<0){
						alert('请填写一个大于等于0的漏选分值');
						return false;
					}
				var str ="";	
				var str2 ="";	
				if($("#examleftItem_"+questionID).val() != undefined){
					alert("试卷中已存在本题目。");
					return false;
				}
				var str_type ="";
				var str_score="";
				if(questionType=='material'){
					str_type ="| <a href='javascript:void(0);' class='exedit spread' onclick='show_children(this)'>展开</a>";
					str_score="	readonly='readonly' ";
				}else{
					str_type="";
					str_score="";
				}
				//父节点提示
				var questionTs=$('#question_'+id).children('.td_of_question').children('.popboxub').html();
					str+="<div style='position:relative' id='examleftItem_"+questionID+"'>";
					str+="  <div class='examleftItem' >";
					str+="	  <input type='hidden' name='testpaperItemStr' value='"+chapterId+"-"+"-"+questionID+"-"+questionType+"-"+score+"-"+missScore+"'>";
					str+="	  <p><b class='Lexamnum'>编号：["+questionID+"]</b>";   
					str+="    <b class='score'>分值：<input type='text' name='parentScore' value='"+score+"' onchange='changerScore(this)' "+str_score+" /></b>";
					str+="	  <b class='score'>漏选分值:<input type='text' name='missScore' value='"+missScore+"' onchange='changerMissScore(this)' /></b>";  
					str+="    <span class='examedit'><a href='javascript:void(0);' onclick=\"removeItem('examleftItem_"+questionID+"');\" class='exedit'>移除</a> | <a href='javascript:void(0);' class='exedit' onclick=\"moveQuestion("+questionID+",'up')\" >上移</a> | <a href='javascript:void(0);' class='exedit' onclick=\"moveQuestion("+questionID+",'down')\" >下移</a> | <a  href='javascript:void(0);' class='exedit ex' onclick='chakan(this)'>详情</a>"+str_type+"</span>";
					str+="    </p>";
					str+="    <p>"+stem+"</p>";
					str+=" 	  <div class='examleftchild'>";
							$('#parent_'+questionID).children('.question_children').each(function(i){
					   				var children_id="";
					   				var children_type="";
					   				var children_stem="";
					   				var children_popbox="";
					   				var children_score;
					   				var children_missScore;
					   				$(this).children().each(function(j){
						   				if(j==0){
						   					var id_type=$(this).val().split('-');;
						   					children_id=id_type[0];
						   					children_type=id_type[1];
						   					children_score=id_type[2];
						   					children_missScore=id_type[3];
						   				}
						   				if(j==1){
						   					children_stem=$(this).text();
						   				}
						   				if(j==2){
						   					children_popbox=$(this).html();
						   				}
								 	});
								 	if(children_score=='' || children_score==null){
								 		children_score=0;
								 	}
								 	if(children_missScore=='' || children_missScore==null){
								 		children_missScore=0;
								 	}
							   		 str+="            	<div class='elclild' id='examleftItem_"+children_id+"'>";
							   		 str+="	  			<input type='hidden' name='testpaperItemStr' value='"+chapterId+"-"+"-"+questionID+"-"+children_id+"-"+children_type+"-"+children_score+"-"+children_missScore+"'>";
									str+="                     <p><b class='Lexamnum'>编号：["+children_id+"]</b>";   
									 str+="                    <b class='score'>分值：<input type='text' name='childrenScore' value='"+children_score+"'  onchange='changerchildrenScore(this)'/></b>";
									 str+="						<b class='score'>漏选分值:<input type='text' name='childrenMissScore' value='"+children_missScore+"' onchange='changeChildMissScore(this)'/></b>";
									 str+="                    <span class='examedit'><a href='javascript:void(0);' onclick=\"removeItem('examleftItem_"+children_id+"');\" class='exedit'>移除</a> | <a href='javascript:void(0);' class='exedit' onclick=\"moveQuestion("+children_id+",'up')\" >上移</a> | <a href='javascript:void(0);' class='exedit' onclick=\"moveQuestion("+children_id+",'down')\" >下移</a> | <a  href='javascript:void(0);' class='exedit ex' onclick='chakanChildren(this)'>详情</a></span>";
									 str+="                    </p>";
									 str+="                    <p>"+children_stem+"</p>";
									str+=" 							<div class='popbox ub'>";
									str+=children_popbox;
									str+=" 							</div>";
									str+="                 </div>";
							 });
					str+="       </div>";
					str+="  </div>";
					str+="	<div class='popbox ub'>";
					str+=questionTs;
					str+="             </div>";
					str+="</div>";
					$("#chapter_"+chapterId).append(str);
			}
		});
}

function removeItem(item_id){
	var childrenItemStr =$('#'+item_id).children("input[name='testpaperItemStr']:first-child").val();
	if(childrenItemStr!=undefined && childrenItemStr.split('-').length==7){
		var temp=childrenItemStr.split('-');
		var parentID=temp[2];
		var childrenScore=parseFloat(temp[5]);
		
		var parentItemStr =$('#examleftItem_'+parentID).children('.examleftItem').children("input[name='testpaperItemStr']:first-child").val();
		var parentScore =parseFloat($('#examleftItem_'+parentID).children('.examleftItem').find("input[name='parentScore']").val());
		parentScore=parentScore-childrenScore;
		var index=parentItemStr.lastIndexOf('-');
		parentItemStr=parentItemStr.substring(0,parentItemStr.lastIndexOf('-',index-1)+1)+parentScore+parentItemStr.substring(index,parentItemStr.length);
		$('#examleftItem_'+parentID).children('.examleftItem').children("input[name='testpaperItemStr']:first-child").val(parentItemStr)
		$('#examleftItem_'+parentID).children('.examleftItem').find("input[name='parentScore']").val(parentScore);
	}
	$('#'+item_id).remove();
}
function moveQuestion(questionID,type){
	if(type=='up'){
		var prev=$('#examleftItem_'+questionID).prev();
		
		$('#examleftItem_'+questionID).prev().remove();
		$('#examleftItem_'+questionID).after(prev);
	}
	if(type=='down'){
		var next =$('#examleftItem_'+questionID).next();
		$('#examleftItem_'+questionID).next().remove();
		$('#examleftItem_'+questionID).before(next);
	}
}

function changerScore(value){
	var score = $(value).val();
	var str=$(value).parent().parent().prev().val();
	var index=str.lastIndexOf('-');
	var str2=str.substring(0,str.lastIndexOf('-',index-1)+1)+score+str.substring(index,str.length);
	$(value).parent().parent().prev().val(str2);
}


function changerMissScore(value){
	var missScore=$(value).val();
	var str=$(value).parent().parent().prev().val();
	var str2=str.substring(0,str.lastIndexOf('-')+1)+missScore;
	$(value).parent().parent().prev().val(str2);
}
function changerchildrenScore(value){
	var parent_score=0;
	var testpaperItemStr=$(value).parents('.examleftItem').children("input[name='testpaperItemStr']:first-child").val();
	$(value).parents('.examleftItem').find("input[name='childrenScore']").each(function(i){
   			parent_score+=parseFloat($(this).val());
 	});
 	$(value).parents('.examleftItem').find("input[name='parentScore']").val(parent_score);
 	var index=testpaperItemStr.lastIndexOf('-');
	testpaperItemStr=testpaperItemStr.substring(0,testpaperItemStr.lastIndexOf('-',index-1)+1)+parent_score+testpaperItemStr.substring(index,testpaperItemStr.length);
	
	$(value).parents('.examleftItem').children("input[name='testpaperItemStr']:first-child").val(testpaperItemStr);
	 
	
	var score = $(value).val();
	var str=$(value).parent('b').parent('p').parent('.elclild').children("input[name='testpaperItemStr']").val();
	var childIndex=str.lastIndexOf('-');
	var str2=str.substring(0,str.lastIndexOf('-',childIndex-1)+1)+score+str.substring(childIndex,str.length);
	$(value).parent('b').parent('p').parent('.elclild').children("input[name='testpaperItemStr']").val(str2);
}


function changeChildMissScore(value){
	var childMissScore=$(value).val();
	var str=$(value).parent('b').parent('p').parent('.elclild').children("input[name='testpaperItemStr']").val();
	var str2=str.substring(0,str.lastIndexOf('-')+1)+childMissScore;
	$(value).parent('b').parent('p').parent('.elclild').children("input[name='testpaperItemStr']").val(str2);
}

function chakan(value){
	$(".popbox").hide();
	$(value).parent(".examedit").parent("p").parent("div").siblings(".popbox").show();
	$(".popbox").mouseleave(function(){
		$(this).hide();
	});
}
function chakanChildren(value){
	$(".popbox").hide();
	$(value).parent(".examedit").parent("p").parent("div").children(".popbox").show();
	$(".popbox").mouseleave(function(){
		$(this).hide();
	});
}
	
</script>
</head>

<body style="overflow: auto;">
	<div class="path">
		<a href="${base}/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 试卷管理  &raquo;题目列表
	</div>
	<div class="exampaper">
		<div class="optionL" style="width:100%">
			<h3 class="eLeftTile">${testpaper.name}</h3>
	        <div class="examtime">
		        <p class="Leftmassage l1">
		            <b><strong>创建时间</strong>: ${testpaper.createDate} </b>
		            <b><strong>试卷状态</strong>：
		           	 [#if testpaper.status == 'draft']不开放[/#if]
		           	 [#if testpaper.status == 'open']开放[/#if]
		           	 [#if testpaper.status == 'closed']关闭[/#if]
		            </b>
		        </p>
		       
				<p class="Leftmassage l2">					
					<b class="Lb1">
						<strong>试卷类型</strong>：
						[#if testpaper.testpaperType == 'intellexercise']快速智能练习[/#if]
						[#if testpaper.testpaperType == 'specialexercise']考点专项练习[/#if]
						[#if testpaper.testpaperType == 'genrationexam']组卷模考[/#if]
						[#if testpaper.testpaperType == 'munualsimulation']手工模考[/#if]
						[#if testpaper.testpaperType == 'oldexam']真题模考[/#if]
						[#if testpaper.testpaperType == 'munualsimulation']其他[/#if]
				    </b>
					<b class="Lb1"><strong>试卷总分</strong>：${testpaper.score} </b>
					<b class="Lb2"><strong>考试时长</strong>：${testpaper.limitedTime} 秒</b>
				</p>
			</div>
			<div class="exampleft" style="height:560px;">
		        <div class="examplbox" style="height:550px;">
			        <form id="itemForm" name="itemForm">
				        	[#list testpaperChapters as testpaperChapter]
						        <div id="chapter_${testpaperChapter.id}">
							    	<span class="examtitle">
							        	<span>${testpaperChapter.name}</span>
							            <b title="收起" class="shrink">-</b>
							        </span>
						        	<input type="hidden" name="testpaperId" value="${testpaper.id}" >
								    [#list testpaperChapter.testpaperItems as testpaperItem]
								    	<div style="position:relative" id="examleftItem_${testpaperItem.question.id}">
									        <div class="examleftItem" >
									        	<input type="hidden" name="testpaperItemStr" value="${testpaperChapter.id}-${testpaperItem.id}-${testpaperItem.question.id}-${testpaperItem.questionType}-${testpaperItem.score}-${testpaperItem.missScore}">
									        	<p><b class="Lexamnum">编号：[${testpaperItem.question.id}]</b>   
									            <b class="score">分值：<input name="parentScore" type="text" value="${testpaperItem.score}" onchange='changerScore(this)' [#if testpaperItem.questionType=='material'] readonly='readonly'[/#if]/></b>  
									            <span class="examedit"><a href="javascript:void(0);" class="exedit" onclick="removeItem('examleftItem_${testpaperItem.question.id}');">移除</a> | <a href="javascript:void(0);" class="exedit" onclick="moveQuestion(${testpaperItem.question.id},'up')" >上移</a> | <a href="javascript:void(0);" class="exedit" onclick="moveQuestion(${testpaperItem.question.id},'down')" >下移</a> | <a href='javascript:void(0);' class='exedit ex' onclick='chakan(this)'>详情</a> [#if testpaperItem.questionType=='material']| <a href='javascript:void(0);' class="exedit spread" onclick='show_children(this)'>展开</a>[/#if]</span>
									            </p>
									            <p>${testpaperItem.question.stemToHtml}</p>
									            <div class="examleftchild" >
										             [#list testpaperItem.children as childrenItem]
										            	<div class="elclild" id='examleftItem_${childrenItem.question.id}'>
										            		<input type='hidden' name='testpaperItemStr' value='${testpaperChapter.id}-${childrenItem.id}-${testpaperItem.question.id}-${childrenItem.question.id}-${childrenItem.question.questionType}-${childrenItem.score}-${childrenItem.missScore}'>
										                    <p><b class="Lexamnum">编号：[${childrenItem.question.id}]</b>   
										                    <b class="score">分值：<input type="text" name='childrenScore' value="${childrenItem.score}" onchange='changerchildrenScore(this)'/></b>  
										                     <span class='examedit'><a href='javascript:void(0);' onclick="removeItem('examleftItem_${childrenItem.question.id}');" class='exedit'>移除</a> | <a href='javascript:void(0);' class='exedit' onclick="moveQuestion(${childrenItem.question.id},'up')" >上移</a> | <a href='javascript:void(0);' class='exedit' onclick="moveQuestion(${childrenItem.question.id},'down')" >下移</a> | <a  href='javascript:void(0);' class='exedit ex' onclick='chakanChildren(this)'>详情</a></span>
										                    </p>
										                    <p>${childrenItem.question.stemToHtml}</p>
										                    <div class='popbox ub'>
																<b>题干:</b>
											                    <p> ${childrenItem.question.stemToHtml}</p>
											                    [#if childrenItem.question.questionType = 'single_choice' || childrenItem.question.questionType = 'choice' || childrenItem.question.questionType = 'uncertain_singlechoice' || childrenItem.question.questionType = 'uncertain_choice' ]
																	<b>选项：</b>
											                    	 [#list childrenItem.question.toMetas as meta]
											                   		 	 <p>${meta}</p>
											                    	 [/#list]
											                    	 <b>答案:</b>
											                   		 <p>${childrenItem.question.toAnswer}</p>
																[/#if]
											                    [#if childrenItem.question.questionType = 'determine']
											                    	 <b>答案:</b>
											                   		 <p>${childrenItem.question.toAnswer}</p>
																[/#if]
											                    [#if childrenItem.question.questionType = 'material' || childrenItem.question.questionType = 'essay']
											                    	 <b>答案:</b>
											                   		 <p>${childrenItem.question.answer}</p>
																[/#if]
										                	</div>
										                </div>
										            [/#list]
									            </div>
									        </div>
									        <div class="popbox ub">
						                		<b>题干:</b>
							                    <p> ${testpaperItem.question.stemToHtml}</p>
							                    [#if testpaperItem.question.questionType = 'single_choice' || testpaperItem.question.questionType = 'choice' || testpaperItem.question.questionType = 'uncertain_singlechoice' || testpaperItem.question.questionType = 'uncertain_choice' ]
													<b>选项：</b>
							                    	 [#list testpaperItem.question.toMetas as meta]
							                   		 	 <p>${meta}</p>
							                    	 [/#list]
							                    	 <b>答案:</b>
							                   		 <p>${testpaperItem.question.toAnswer}</p>
												[/#if]
							                    [#if testpaperItem.question.questionType = 'determine']
							                    	 <b>答案:</b>
							                   		 <p>${testpaperItem.question.toAnswer}</p>
												[/#if]
							                    [#if testpaperItem.question.questionType = 'material' || testpaperItem.question.questionType = 'essay']
							                    	 <b>答案:</b>
							                   		 <p>${testpaperItem.question.answer}</p>
												[/#if]
								             </div>
							           </div>
							    	 [/#list]
						        </div>
						     [/#list]
					   </form>
		        		</div>
		    		</div>
				    <div class="exampright" style="height:560px;">
				    	<div class="examinput">
			               <input type="hidden" name="testpaperItemIds" value="" />
				        	<select name="outlineCategoryId" id="outlineCategoryId">
				            	[#list outlineCategoryTree as category]
										<option value="${category.id}"   [#if category.id == rootId ] selected [/#if]>
											[#if category.grade != 0]
												[#list 1..category.grade as i]
													&nbsp;&nbsp;
												[/#list]
											[/#if]
											${category.name}
										</option>
									[/#list]
				            </select>
				            <select name="questionType" id="questionType">
				            	<option value="">-选择题型-</option>
				            	<option value="single_choice">单选题</option>
				            	<option value="choice">多选题</option>
				            	<option value="uncertain_singlechoice">不定项单选题</option>
				            	<option value="uncertain_choice">不定项多选题</option>
				            	<option value="determine">判断题</option>
				            	<option value="material">材料题</option>
				            	<option value="essay">简答题</option>
				            	<option value="blanks">填空题</option>
				            	
				            </select>
				            <input name="stem" id="stem" type="text"/>
				            <a class="selbtn" href="#">搜索</a>
				        </div>
		         		<div  id='manger'>
							<div align="center">
						     	<img src="${base}/resources/moc/images/loading_bar.gif"/><br/>
						     	数据正在加载中，请勿关闭浏览器,稍等。。。
							</div>
						</div>
						<div id="pagination"  style="clear:none;padding-right:10px;margin-top:10px;">
						</div>
					</div>
					<div class="clearh"></div>
					<div align="center" style="margin-top:30px;height:30px;">
				    	[#if testpaper.testpaperType=='oldexam' || testpaper.testpaperType=='munualsimulation']
				        		<input type="button"  class="button submitAdd" value="提交" />
			        	[/#if]
			        	<input type="button"  class="button" value="返回" onclick="location.href='${base}/admin/testpaper/list.jhtml'"/>	
				    </div>
	    		</div>
			</div>
</body>
</html>