<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.product.add")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.treeSelect.js"></script>
<script type="text/javascript">
// 编辑器
if(typeof(KindEditor) != "undefined") {
	KindEditor.ready(function(K) {
			editor = K.create("#stem", {
				height: "350px",
				width:"80%",
				items:[
					"bold", "italic","forecolor","underline","|","insertorderedlist","insertunorderedlist"
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
<script type="text/javascript">
$().ready(function(){
	var $outlineCategory = $("#outlineCategory");
	var $submitAdd = $(".submitAdd");
	var $inputForm =$("#inputForm");
	var $borrow =$("#borrow");
	var test="";
	var choiceIndex=0;
	[@flash_message /]
	$submitAdd.click(function(){
		//是否填写了题干
		if($("#stem").attr("value")==null ||$.trim($("#stem").attr("value"))==""){
			$.message("warn","请填写题干");
			return false;			
		} 
		if($(this).attr("continueAdd")=="true"){
			$("#submitType").attr("value","continueAdd");
		}
		if($(".borrowselect").length==0){
			$.message("warn","请点击借/贷按钮，填写借贷信息");
			return false;	
		}
		$inputForm.submit();
	});
	//高级选项--答案解析及分值
	$("#advanceChoice").toggle(
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
	$("#borrow").click(function(){
		[@compress single_line = true]
		choiceIndex++;
		var trHtml =
		'<tr>
				<th>
					<span class="requiredField">*</span>借:
				</th>
				<td>
				<input type="hidden" name="keys" value="借"/>
					<select name="borrowselect" class="borrowselect">
						<option value="">---请选择---</option>
						[#list accoutCatalogs as category]
							<option value="${category.code}">
								[#if category.grade != 0]
									[#list 1..category.grade as i]
										&nbsp;&nbsp;
									[/#list]
								[/#if]
								${category.name}
							</option>
						[/#list]
					</select>
					<input type="text"  name="borrowanswer" class="text"  maxlength="200" />
					<input type="button" value="-"  onClick="$(this).closest(\'tr\').remove();">
				</td>
			</tr>';
		[/@compress]
		$("#myborrow").after(trHtml);
	});
	$("#loan").click(function(){
		[@compress single_line = true]
		var trHtml =
		'<tr>
				<th>
					<span class="requiredField">*</span>贷:
				</th>
				<td>
					<input type="hidden" name="keys" value="贷"/>
					<select name="borrowselect" class="borrowselect">
						<option value="">---请选择---<\/option>
						[#list accoutCatalogs as category]
							<option value="${category.code}">
								[#if category.grade != 0]
									[#list 1..category.grade as i]
										&nbsp;&nbsp;
									[/#list]
								[/#if]
								${category.name}
							</option>
						[/#list]
					</select>
					<input type="text"  name="borrowanswer" class="text"  maxlength="200" />
					<input type="button" value=" - "  onClick="$(this).closest(\'tr\').remove();">
				</td>
			</tr>';
		[/@compress]
		$("#myborrow").after(trHtml);
	});
});

</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a>[#if materialID??] &raquo; 添加材料子题[/#if] &raquo; 添加分录题
	</div>
	<form id="inputForm" action="save.jhtml" method="post" enctype="multipart/form-data">
	<input type="hidden" name="questionType" id="questionType" value="entry"/>
	<input type="hidden" name="materialID" id="materialID" value="${materialID}"/>
	<input type="hidden" name="submitType" id="submitType" />
	<input type="hidden" name="outlineCategoryId" value="${outlineCategoryId}"/>
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>题干:
				</th>
				<td>
					<textarea name="stem" id="stem"></textarea>
				</td>
			</tr>
			<tr>
				<th>
					难度:
				</th>
				<td>
					<label>
						<input type="radio"  name="difficulty" value="easy" />简单
					</label>
					<label>
						<input type="radio"  name="difficulty" value="normal" checked/>一般
					</label>
					<label>
						<input type="radio"  name="difficulty" value="hard" />困难
					</label>
				</td>
			</tr>
			<tr id="myborrow">
			<th></th>
				<td>
					<a href="javascript:;" id="borrow" class="button" >借</a>
					<a href="javascript:;" id="loan" class="button">贷</a>
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
					<textarea name="analysis"></textarea>
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
					<input type="button" continueAdd="true" class="button submitAdd" value="保存并继续添加" />				
					<input type="button" continueAdd="false" class="button submitAdd" value="保存" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='materialChildrenList.jhtml?id=${materialID}&outlineCategoryId=${outlineCategoryId}'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>