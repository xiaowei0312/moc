<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.product.add")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.treeSelect.js"></script>
<script type="text/javascript">
if(typeof(KindEditor) != "undefined") {
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="stem"]', {
			height: "350px",
			width:"80%",
			items:["bold", "italic","forecolor","underline","|","insertorderedlist","insertunorderedlist"
					,"|","link","unlink","|","removeformat","source","image"
					],
			langType: sram.locale,
					syncType: "form",
					filterMode: false,
					pagebreakHtml: '<hr class="pageBreak" \/>',
					allowFileManager: true,
					filePostName: "file",
					fileManagerJson: sram.base + "/admin/file/browser.jhtml",
					uploadJson: sram.base + "/admin/file/upload.jhtml",
					uploadImageExtension: setting.uploadImageExtension,
					uploadFlashExtension: setting.uploadFlashExtension,
					uploadMediaExtension: setting.uploadMediaExtension,
					uploadFileExtension: setting.uploadFileExtension,
					extraFileUploadParams: {
						token: getCookie("token")
					},
			afterChange: function() {
				this.sync();
			}
		});
	});
}
</script>

<style type="text/css">
#addChoice{
	color:#fff;
    padding: 5px 10px;
    margin-left:310px;
    
}
#stemImage{
	color:#fff;
	margin-left:550px;
}
.addChoice,#stemImage{
	border-radius: 3px;
    font-size: 12px;
    line-height: 1.5;
	margin-top:5px;
	margin-bottom:5px;
}
.changeRed{
	background-color:#f43;
}
.changeGray{
	background-color:#666666;
}
.choice{
	width:300px;
}
.choiceText{
	border-color: #999999 #e1e1e1 #e1e1e1 #999999;
    border-radius: 2px;
    border-style: solid;
    border-width: 1px;
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1) inset;
    color: #666666;
    height: 30px;
    line-height: 24px;
    padding: 0 4px;
    width: 60%;
}
</style>
<script type="text/javascript">

$().ready(function(){
	var $inputForm = $("#inputForm");
	var $outlineCategory = $("#outlineCategory");
	var $addChoice =$("#addChoice");
	var $delete = $(".deleteChoice");
	var $browserButton = $(".browserButton");
	var $advanceChoice = $("#advanceChoice");
	var $submitAdd = $(".submitAdd");
	var $answer = $("#answer");
	
	//类别选择
	$outlineCategory.treeSelect({
		url: "${base}/admin/question/getOutlineCategoryChildren.jhtml?number="+Math.random()
	});
	
	var $outlineCategoryId=$("select[name='outlineCategoryId_select']:eq(0)");
	
	[@flash_message /]
	
	//表单提交
	$submitAdd.click(function(){
		//是否选择了类别
		if($.trim($outlineCategory.attr("value"))==""){
			$.message("warn","请选择类别");
			return false;
		}
		//是否填写了题干
		if($("#stem").attr("value")==null ||$.trim($("#stem").attr("value"))==""){
			$.message("warn","请填写题干");
			return false;			
		} 
		
		//是否为所有选项赋值
		var choices=true;
		$("input[name=choices]").each(function(){
			if($.trim($(this).attr("value"))==""){
				choices= false;
				return false;
			}
		});
		if(!choices){
			$.message("warn","请为所有选项赋值");
			return false;
		}
		
		//是否选择了答案
		if($(":checkbox:checked").length<1){
			$.message("warn","请选择答案");
			return false;		
		}
		
		if($("input[name='score']").val()==null || $("input[name='score']").val()==""){
			$.message("warn","分数必须填写");
			return false;
		}
		
		if($("input[name='missScore']").val()==null || $("input[name='missScore']").val()==""){
			$.message("warn","漏选分数必须填写");
			return false;
		}
		
		if($("input[name='score']").val()!=null && $("input[name='score']").val()!=""){
			var re = /^\d+$/;
			if (!re.test($("input[name='score']").val())){  
				$.message("warn","分数必须为正整数");
				return false;
			}
	    }
	    
		if($("input[name='missScore']").val()!=null && $("input[name='missScore']").val()!=""){
			var re = /^\d+$/;
			if (!re.test($("input[name='missScore']").val())){  
				$.message("warn","漏选分数必须为正整数或0");
				return false;
			}
		}
	    
		var questionAnswer='["';
		$(":checkbox:checked").each(function(i){
			var $input=$(this).closest("td").children("input:first");
			if(i==0){
				questionAnswer +=$input.attr("indexChar");
			}else{
				questionAnswer += '","'+$input.attr("indexChar");
			}
		});
		$answer.attr("value",questionAnswer+'"]');
		$inputForm.submit();
	});
	
	// 表单验证
	$inputForm.validate({
			rules:{
				choices:{required:true}
			}
	});

	//高级选项--答案解析及分值
	$advanceChoice.toggle(
		function(){
			$(".analysis").fadeIn();
			KindEditor.create('textarea[name="analysis"]', {
				items:["bold", "italic","forecolor","underline","|","insertorderedlist","insertunorderedlist"
				,"|","link","unlink","|","removeformat","source"
				],
				afterBlur: function(){this.sync();}
			});
		},
		function(){
			$(".analysis").fadeOut();
		}
		
	);
	
	
	//为添加选项按钮changeColor
	$("#addChoice,#stemImage").mouseover(function(){
		$(this).removeClass("changeGray").addClass("changeRed");
		$(this).mouseleave(function(){
			$(this).removeClass("changeRed").addClass("changeGray");
		});
	});
	$addChoice.click(function(){
		//获取页面中有多少个选项
		var size=$(":input[name=choices]").size();
		var choiceIndex=size+65;
		if(choiceIndex>74){
			choiceIndex--;
			$.message("warn","最多能添加十个选项");
			return false;
		}
		[@compress single_line = true]
		var temp=String.fromCharCode(choiceIndex);
		var oldValue;
		var trHtml =
		'<tr>
			<th><span class="requiredField">*</span>选项'+temp+':
			</th>
			<td>
				<input id="input'+choiceIndex+'" indexChar="'+choiceIndex+'" type="text" name="choices" class="choiceText"/>
				<label>
					<input type="checkbox" name="answers" />正确答案&nbsp;&nbsp;&nbsp;&nbsp;
				</label>
				<a href="javascript:;" id="browser'+choiceIndex+'">[添加图片]</a>
				<span class="deleteChoice"><a href="#">[删除]</a></span>
			</td>
		</tr>';
		[/@compress]
		$(".tabContent").append(trHtml);
		$("#browser"+choiceIndex).browser({callback: function(url){
				var oldValue=$("#input"+choiceIndex).attr("value");
				$("#input"+choiceIndex).attr("value",oldValue+'[image]'+url+'[image]');
			}
		});
	});
	
	//为默认选项绑定添加图片的事件
	$(".defaultChoice").each(function(){
		var $input=$(this).closest("td").children("input");
		$(this).browser({callback: function(url){
				var oldValue=$input.attr("value");
				$input.attr("value",oldValue+'[image]'+url+'[image]');
			}
		});
	});
	
	//删除选项
	$delete.live("click", function(){
		var temp;
	
		//每次都定位到最后一个选项
		var size=$(":input[name=choices]").size();
		var choiceIndex=65+size-1;
		
		if(choiceIndex<67){
			$.message("warn","至少添加两个选项");
			return false;
		}
		var $this = $(this);
		var $remainChoices = $this.closest("tr").nextAll("tr");
		var remainSize = $remainChoices.size();
		var $input;
		var oldValue;
		if(remainSize>0){
			temp =remainSize;
			//改变剩余的选项
			$remainChoices.each(function(){
				temp--;
				$(this).children("th").text("").append('<span class="requiredField">*</span>选项:'+String.fromCharCode(choiceIndex-temp-1));
				$input=$(this).find("input:first");
				$input.attr("indexChar",choiceIndex-temp-1);
			});
		}
		$this.closest("tr").remove();
	});
});

</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> [#if materialID??] &raquo; 编辑材料子题[/#if]&raquo; 编辑选择题
	</div>
	<form id="inputForm" action="update.jhtml" method="post" enctype="multipart/form-data">
	<input type="hidden" name="answer" id="answer" />
	<input type="hidden" name="id" value="${question.id}"/>
	 <input type="hidden" name="materialID" value="${materialID}" />
	 [#if materialID?? && materialID!=0]
	 	<input type="hidden" name="parent.id" value="${question.parent.id}"/>
	 [/#if]
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>类别:
				</th>
				<td>
				<input type="hidden" name="materialID" id="materialID" value="${materialID}"/>
				<input type="hidden" name="treePath" id="treePath" value="${question.treePath}">
					<input type="hidden" name="outlineCategoryId" id="outlineCategory" 
					 [#if question.outlineCategory??]
					 		value="${(question.outlineCategory.id)!}"
							treePath="${(question.outlineCategory.treePath)!}"
					 [/#if]
					 />
				</td>
			</tr>
			<tr>
				<th>不定项</th>
				<td>
					[#if question.questionType='uncertain_choice']
						<input type="radio" name="questionType" checked value="uncertain_choice" />是
						<input type="radio" name="questionType" value="choice"/>否
					[#else]
						<input type="radio" name="questionType" value="uncertain_choice" />是
						<input type="radio" name="questionType" checked value="choice"/>否
					[/#if]
				</td>
			</tr>
			<tr>
				<th>
					难度:
				</th>
				<td>
					<label>
						<input type="radio"  name="difficulty"
							[#if question.difficulty='easy']
								checked
							[/#if]
						 value="easy" />简单
					</label>
					<label>
						<input type="radio"  name="difficulty"
							[#if question.difficulty='normal']
								checked
							[/#if]
						 value="normal"/>一般
					</label>
					<label>
						<input type="radio"  name="difficulty" 
							[#if question.difficulty='hard']
								checked
							[/#if]
						value="hard" />困难
					</label>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>分数:
				</th>
				<td>
					<input type="text" name="score" id="score" value="${question.score}"></input>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>漏选分数:
				</th>
				<td>
					<input type="text" name="missScore" id="missScore" value="${question.missScore}"></input>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>题干${answers}:
				</th>
				<td>
					<textarea name="stem" id="stem">${question.stem}</textarea>
				</td>
			</tr>
			[#assign abcd=['A','B','C','D','E','F','G','H','I','J']]
			[#list choices as choice]
				<tr>
					<th>
						<span class="requiredField">*</span>
						选项${abcd[choice_index]}:
					</th>
					<td>
						<input indexChar=#{65+choice_index} type="text" name="choices" value=
						[#assign temp=choice?replace('"','&quot;')]
						"${temp}" class="choiceText" />
						<label>
							<input type="checkbox" 
							[#list answers as answer]
								[#if (65+choice_index)=answer]
									checked 
								[/#if]
							[/#list]
							/>正确答案&nbsp;&nbsp;&nbsp;&nbsp;
						</label> 
						<a href="javascript:;" class="defaultChoice">[添加图片]</a>
						<span class="deleteChoice"><a href="#">[删除]</a></span>
					</td>
				</tr>
			[/#list]
		</table>
		<table class="input">
		<tr>
			<th></th>
			<td style="width:300px">
				<div class="addChoice">
					<a href="javascript:;" id="addChoice" class="changeGray addChoice">新增选项</a>
				</div>
			</td>
		</tr>
		</table>
		<table class="input">
			<tr>
				<th>&nbsp;&nbsp;</th>
				<td><a href="javascript:;" id="advanceChoice" class="blue">&raquo;显示/隐藏 高级选项 ...</a></td>
			</tr>
			<tr>
				<th><span class="analysis hidden">解析:</span></th>
				<td>
					<div class="analysis hidden">
					<textarea name="analysis">${question.analysis}</textarea>
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
					<input type="button" continueAdd="false" class="button submitAdd" value="保存" />
					[#if materialID=0 || !(materialID??)]
						<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='questionList.jhtml'" />
					[#else]
						<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='materialChildrenList.jhtml?id=${materialID}&outlineCategoryId=${outlineCategoryId}'" />
					[/#if]	
				</td>
			</tr>
		</table>
	</form>
</body>
</html>