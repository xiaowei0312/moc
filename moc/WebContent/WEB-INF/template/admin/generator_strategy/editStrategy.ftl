<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.member.add")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/admin/noUiSlider5.0/jquery.nouislider.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/noUiSlider5.0/jquery.nouislider.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.treeSelect.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<style type="text/css">
.testpaper-question-options input {
	width: 50px;
}
.testpaper-question-options span{
	paddingLeft :"250px";
}
</style>
<script type="text/javascript">
$(function() {
	[#if exercise.generatorType=='RANDOM']$('#difficultyInput').hide();[/#if]
	[#if exam.generatorType=='RANDOM']$('#difficultyInput1').hide();[/#if]
	var questionIndex = ${exam.generatorQuestionConfigs?size};
	//大纲选择
	$('#outlineCategory').treeSelect({
		url: "${base}/admin/question/getOutlineCategoryChildren.jhtml"
	});
	//TAB1 radio监听
	$("input[class=GeneratorType]").change(function(){
		var value=$(this).val();
		var tr=$('#difficultyInput');
		if(value=='DIFFICULTY'){
			tr.fadeIn(500);
		}else{
			tr.fadeOut(500);
		}
	});
	//TAB2 radio监听
	$("input[class=GeneratorType1]").change(function(){
		var value=$(this).val();
		var tr=$('#difficultyInput1');
		if(value=='DIFFICULTY'){
			tr.fadeIn(500);
		}else{
			tr.fadeOut(500);
		}
	});
	//TAB1滑动块
	$('.slider').noUiSlider({
	                range: [0, 100],
	                start: [#if exercise.generatorType=='DIFFICULTY'] [${exercise.sliderStart},${exercise.sliderEnd}][#else][30, 70][/#if],
	                step: 5,
	                serialization: {
	                    resolution: 1
	                },
	                slide: function() {
	                    this.trigger('change');
	                }
	            }).change(function() {
	                var values = $(this).val();
	                 var simplePercentage = values[0],
	                 normalPercentage = values[1] - values[0],
	                 difficultyPercentage = 100 - values[1];
	                $('.simple-percentage-text').html('简单' + simplePercentage + '%');
	                $('.normal-percentage-text').html('一般' + normalPercentage + '%');
	                $('.difficulty-percentage-text').html('困难' + difficultyPercentage + '%');
	                
	                $('input[name="percentagesSimple"]').val(simplePercentage+'%');
	                $('input[name="percentagesNormal"]').val(normalPercentage+'%');
	                $('input[name="percentagesDifficulty"]').val(difficultyPercentage+'%');
	            });
	//TAB2滑动块
	$('.slider1').noUiSlider({
	                range: [0, 100],
	                start: [#if exam.generatorType=='DIFFICULTY'] [${exam.sliderStart},${exam.sliderEnd}][#else][30, 70][/#if],
	                step: 5,
	                serialization: {
	                    resolution: 1
	                },
	                slide: function() {
	                    this.trigger('change');
	                }
	            }).change(function() {
	                var values1 = $(this).val();
	                 var simplePercentage1 = values1[0],
	                 normalPercentage1 = values1[1] - values1[0],
	                 difficultyPercentage1 = 100 - values1[1];
	                $('.simple-percentage-text1').html('简单' + simplePercentage1 + '%');
	                $('.normal-percentage-text1').html('一般' + normalPercentage1 + '%');
	                $('.difficulty-percentage-text1').html('困难' + difficultyPercentage1 + '%');
	                
	                $('input[name="percentagesSimpleVO"]').val(simplePercentage1+'%');
	                $('input[name="percentagesNormalVO"]').val(normalPercentage1+'%');
	                $('input[name="percentagesDifficultyVO"]').val(difficultyPercentage1+'%');
	            });
	    //增加试题类型      
	    $(".addQuestion").live("click",function() {
		[@compress single_line = true]
			var trHtml = 
			'
			<div class="newAdd">
				<select name="configs['+questionIndex+'].questionType" class="quesType">
						  <option value ="single_choice">单选题<\/option>
						  <option value ="choice">多选题<\/option>
						  <option value="uncertain_singlechoice">不定项单选<\/option>
						  <option value="uncertain_choice">不定项多选<\/option>
						  <option value="determine">判断题<\/option>
						  <option value="material">材料题<\/option>
						  <option value="essay">简答题<\/option>
						  <option value="blanks">填空题<\/option>
						  <option value="entry">分录题<\/option>
						<\/select>&nbsp;
			<span>题目数量：<\/span><input type="text" name="configs['+questionIndex+'].count" value="3" style="width: 50px;">
			<span>章节名称：<\/span><input type="text" name="configs['+questionIndex+'].chapterName" style="width: 80px;">
			<span>题目分值：<\/span><input type="text" name="configs['+questionIndex+'].score" value="2" style="width: 50px;" class="questionScore" >
			<a class="toggleDescription" href="javascript:;">[描述]<\/a>
			<a class="addQuestion" href="javascript:;">[增加]<\/a>
			<a href="javascript:;" class="deleteQuestion">[${message("admin.common.delete")}]<\/a>
  			<br\/>
		    <textarea class="descriptionTextarea" name="configs['+questionIndex+'].description" style="width:600px;"><\/textarea>
			</div>
			';
		[/@compress]
		$(this).parent().parent().find('.descriptionTextarea').hide();
		$('.testpaper-question-options').append(trHtml);
		questionIndex ++;
		});
		
		// 删除
		$('a.deleteQuestion').live("click", function() {
			var $this = $(this);
			if($(".testpaper-question-options .newAdd").length==1){
			
			}else{
				$.dialog({
				type: "warn",
				content: "${message("admin.dialog.deleteConfirm")}",
				onOk: function() {
					$this.closest("div").remove();
				}
		 	});
			}
		});
		
		//添加删除漏选分值
		$('.quesType').live("change",function(){
			var value = $(this).val();
            var nameValue = $(this).attr('name');
            var v1 = nameValue.substring(nameValue.lastIndexOf('[') + 1, nameValue.lengh);
            var index = v1.substring(0, v1.lastIndexOf(']'));
			if (value == 'choice' || value == 'uncertain_choice') {
			$(this).parent().find(".questionScore").after('<span class="missSpan">漏选分值：<\/span><input type="text" name="configs[' + index + '].missScore" class="missScore" value="1" style="width: 50px;">');
				}else{
					$(this).parent().find(".missScore").remove();
				}
		});
		
		//增加隐藏描述
		$(".toggleDescription").live("click",function(){
			$(this).parent().find('.descriptionTextarea').toggle();
		});
	});
	
	
	function validateForm(){
		if($("input[name='questionCount']").val()==null || $("input[name='questionCount']").val()==""){
			alert("智能/专项练习题目数量必须填写");
			return false;
		}
		if($("input[name='questionCount']").val()!=null && $("input[name='questionCount']").val()!=""){
			if(isNaN($("input[name='questionCount']").val())){
				alert("智能/专项练习题目数量必须为数字");
				return false;
			}
		}
		if($("input[name='timeLimitStr']").val()==null || $("input[name='timeLimitStr']").val()==""){
			alert("智能/专项练习限制时间必须填写");
			return false;
		}
		if($("input[name='timeVOStr']").val()==null || $("input[name='timeVOStr']").val()==""){
			alert("组卷模考限制时间必须填写");
			return false;
		}
	}
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 添加通用策略
	</div>
	<form id="inputForm" action="updateStrategy.jhtml" method="post" onsubmit="return validateForm();">
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="智能/专项练习" />
			</li>
			<li>
				<input type="button" value="组卷模考" />
			</li>
		</ul>
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>所属类别:
				</th>
				<td>
					<span>[#if exercise.outlineCategory??]${exercise.outlineCategory.name}[#else]缺省策略[/#if]</span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>题目数量:
				</th>
				<td>
					[#assign configs = exercise.generatorQuestionConfigs /]  
					[#list configs as configs]   
					<input type="text" name="questionCount" value="${configs.count}" />  
					[/#list]   
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>生成方式:
				</th>
				<td>
					<input type="radio" [#if exercise.generatorType=='RANDOM']checked="checked"[/#if]  value="RANDOM" name="GeneratorType" class="GeneratorType"> 随机生成<input type="radio" value="DIFFICULTY" [#if exercise.generatorType=='DIFFICULTY']checked="checked"[/#if] name="GeneratorType" class="GeneratorType">按难易程度
				</td>
			</tr>
			<tr id="difficultyInput">
				<th>
					<span class="requiredField" >*</span>难度:
				</th>
				<td>
					<div class="slider" style=" width:60%;"></div>
					<span class="simple-percentage-text">[#if exercise.generatorType=='DIFFICULTY']简单${exercise.percentagesSimple}%[#else]简单30%[/#if]</span>
					<span class="normal-percentage-text">[#if exercise.generatorType=='DIFFICULTY']一般${exercise.percentagesNormal}%[#else]一般40%[/#if]</span>
					<span class="difficulty-percentage-text">[#if exercise.generatorType=='DIFFICULTY']困难${exercise.percentagesDifficulty}%[#else]困难30%[/#if]</span>
					<br>
					<span class="text-info">如果某个难度的题目数不够，将会随机选择题目来补充。</span>
					<input type="hidden" name="percentagesSimple" value="[#if exercise.generatorType=='DIFFICULTY']${exercise.percentagesSimple}%[#else]30%[/#if]">
					<input type="hidden" name="percentagesNormal" value="[#if exercise.generatorType=='DIFFICULTY']${exercise.percentagesNormal}%[#else]40%[/#if]">
					<input type="hidden" name="percentagesDifficulty" value="[#if exercise.generatorType=='DIFFICULTY']${exercise.percentagesDifficulty}%[#else]30%[/#if]">
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>时间限制:
				</th>
				<td>
					<input type="text" id="timeLimitStr" name="timeLimitStr" style="width:80px;" size="10"  class="Wdate validate[required] form-textbox" style="width:200px;" onFocus="WdatePicker({dateFmt:'HH:mm:ss',isShowClear:true,readOnly:true})" value="${timeLimitStr}"/>
					<input type="hidden" name="id" value="${exercise.id}"/>
					0秒钟，表示无限制
				</td>
			</tr>
		</table>
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>生成方式:
				</th>
				<td>
					<input type="radio" [#if exam.generatorType=='RANDOM']checked="checked"[/#if] value="RANDOM" name="generatorTypeVO" class="GeneratorType1"> 随机生成<input type="radio" value="DIFFICULTY" [#if exam.generatorType=='DIFFICULTY']checked="checked"[/#if] name="generatorTypeVO" class="GeneratorType1">按难易程度
				</td>
			</tr>
			<tr id="difficultyInput1">
				<th>
					<span class="requiredField" >*</span>难度:
				</th>
				<td>
					<div class="slider1" style=" width:60%;"></div>
					<span class="simple-percentage-text1">[#if exam.generatorType=='DIFFICULTY']简单${exam.percentagesSimple}%[#else]简单30%[/#if]</span>
					<span class="normal-percentage-text1">[#if exam.generatorType=='DIFFICULTY']一般${exam.percentagesNormal}%[#else]一般40%[/#if]</span>
					<span class="difficulty-percentage-text1">[#if exam.generatorType=='DIFFICULTY']困难${exam.percentagesDifficulty}%[#else]困难30%[/#if]</span>
					<br>
					<span class="text-info">如果某个难度的题目数不够，将会随机选择题目来补充。</span>
					<input type="hidden" name="percentagesSimpleVO" value="[#if exam.generatorType=='DIFFICULTY']${exam.percentagesSimple}%[#else]30%[/#if]">
					<input type="hidden" name="percentagesNormalVO" value="[#if exam.generatorType=='DIFFICULTY']${exam.percentagesNormal}%[#else]40%[/#if]">
					<input type="hidden" name="percentagesDifficultyVO" value="[#if exam.generatorType=='DIFFICULTY']${exam.percentagesDifficulty}%[#else]30%[/#if]">
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>时间限制:
				</th>
				<td>
					<input type="text" id="timeVOStr" name="timeVOStr" style="width:80px;" size="10"  class="Wdate validate[required] form-textbox" style="width:200px;" onFocus="WdatePicker({dateFmt:'HH:mm:ss',isShowClear:true,readOnly:true})" value="${timeVOStr}"/>
					0秒钟，表示无限制
					<input type="hidden" name="idVO" value="${exam.id}"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>题目:
				</th>
				<td>
					<div class="testpaper-question-options">
						[#assign configs = exam.generatorQuestionConfigs /]  
						[#list configs as configs]   
						<div class="newAdd">
							<select name="configs[${configs_index}].questionType"class="quesType">
									  <option value ="single_choice" [#if configs.questionType=='single_choice']selected="selected"[/#if]>单选题</option>
									  <option value ="choice" [#if configs.questionType=='choice']selected="selected"[/#if]>多选题</option>
									  <option value="uncertain_singlechoice" [#if configs.questionType=='uncertain_singlechoice']selected="selected"[/#if]>不定项单选</option>
									  <option value="uncertain_choice" [#if configs.questionType=='uncertain_choice']selected="selected"[/#if]>不定项多选</option>
									  <option value="determine" [#if configs.questionType=='determine']selected="selected"[/#if]>判断题</option>
									  <option value="material" [#if configs.questionType=='material']selected="selected"[/#if]>材料题</option>
									  <option value="essay" [#if configs.questionType=='essay']selected="selected"[/#if]>简答题</option>
									  <option value="blanks" [#if configs.questionType=='blanks']selected="selected"[/#if]>填空题</option>
									  <option value="entry" [#if configs.questionType=='entry']selected="selected"[/#if]>分录题</option>
									</select>&nbsp;
							<span>题目数量：</span><input type="text" name="configs[${configs_index}].count" value="${configs.count}" style="width: 50px;">
							<span>章节名称：</span><input type="text" name="configs[${configs_index}].chapterName" value="${configs.chapterName}" style="width: 80px;">
							<span>题目分值：</span><input type="text" name="configs[${configs_index}].score" value="${configs.score}" style="width: 50px;" class="questionScore">
							[#if configs.questionType=='choice'||configs.questionType=='uncertain_choice'] <span class="missSpan">漏选分值：</span><input type="text" name="configs[${configs_index}].missScore" class="missScore" value="${configs.missScore}" style="width: 50px;"> [/#if]
							<a class="toggleDescription" href="javascript:;">[描述]</a>
						    <a class="addQuestion" href="javascript:;">[增加]</a>
						    <a class="deleteQuestion" href="javascript:;">[删除]</a>
						    <br/>
						    <textarea class="descriptionTextarea" name="configs[${configs_index}].description" style="width:600px;">${configs.description}</textarea>
						</div>
						[/#list]   
					</div>
				</td>
			</tr>
		</table>
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("admin.common.submit")}" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>