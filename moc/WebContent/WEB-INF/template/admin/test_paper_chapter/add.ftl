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
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.treeSelect.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>

<script type="text/javascript">
if(typeof(KindEditor) != "undefined") {
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="stem"]', {
			height: "150px",
			width:"30%",
			items:["bold", "italic","forecolor","underline","|","insertorderedlist","insertunorderedlist"
					,"|","link","unlink","|","removeformat","source","image","squareBrackets"
					],
			afterChange: function() {
				this.sync();
			}
		});
	});
}

</script>
<style type="text/css">
	.specificationSelect {
		height: 100px;
		padding: 5px;
		overflow-y: scroll;
		border: 1px solid #cccccc;
	}
	
	.specificationSelect li {
		float: left;
		min-width: 150px;
		_width: 200px;
	}
	.u433 {
	    background-color:#CCCCCC;
	    text-align: left;;
	    font-family:'Arial';
	    font-size: 13px;
	    font-style:normal;
	    font-weight:normal;
	    text-decoration:none;
     }
</style>
<script type="text/javascript">
$().ready(function(){
	var $submitAdd = $(".submitAdd");
	var $inputForm =$("#inputForm");
	//类别选择
	$('#outlineCategory').treeSelect({
		url: "${base}/admin/question/getOutlineCategoryChildren.jhtml"
	});
	[@flash_message /]
	
	loadInfo();
	
//类别选择
	$submitAdd.click(function(){
		var $name = $("#name").val();
		var $order = $("#order").val();
		if($name == ""){
			$.message("warn","请填写章节名称");
			return false;			
		} 
		if($order == ""){
			$.message("warn","请填写序号");
			return false;			
		} 
		$.ajax({
			url: "save.jhtml",
			type: "POST",
			data:$('#inputForm').serialize(),
			dataType: "json",
			cache: false,
			success: function(message) {
				$.message(message);
				$("#id").val("");
				$("#name").val("");
				$("#order").val("");
				$("#description").val("");
				loadInfo();
			}
		});
	});
});
function findTestpaperChapter(id){
	$.ajax({
			url: "findTestpaperChapter.jhtml",
			type: "POST",
			data:{"id":id},
			dataType: "json",
			cache: false,
			success: function(data) {
				$("#id").val(data.id);
				$("#name").val(data.name);
				$("#order").val(data.order);
				$("#description").val(data.description);
				loadInfo();
			}
		});
}

function loadInfo(){
	$.ajax({
			url: "list.jhtml",
			type: "POST",
			data:{'testpaperId':${testpaper.id}},
			dataType: "text",
			cache: false,
			success: function(message) {
				$('#listTable').find('.info').remove();
				$('#listTable>tbody').append(message);
				$("input[name='ids']").click( function() {
					if( $("input[name='ids']:enabled:checked").length>=1){
						$("#selectAll").prop("checked", true);
						$("#deleteButton").removeClass("disabled");
					}else{
						$("#deleteButton").addClass("disabled");
						$("#selectAll").prop("checked", false);
					}
				});
			}
		});
}

</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 创建试卷 &raquo;章节列表
	</div>
	<form id="inputForm" action="save.jhtml" method="post" enctype="multipart/form-data">
		<table class="input tabContent">
			<tr>
				<th>
					所属试卷:
				</th>
				<td>
					<input type="hidden" name="id" id="id" /> 
					<input type="hidden" name="testpaperId" value="${testpaper.id}" /> 
					<input type="text" class="text u433" maxlength="200" name="testpaperName" value="${testpaper.name}" disabled="disabled" /> 
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>章节名称:
				</th>
				<td>
					<input type="text" id="name" name="name"  class="text" maxlength="200"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>序号:
				</th>
				<td>
					<input type="text" id="order" name="order"  class="text" maxlength="200"/>
				</td>
			</tr>
			<tr>
				<th>
					备注:
				</th>
				<td>
					<textarea id='description' name="description"></textarea>
				</td>
			</tr>
			
		</table>
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					[#if testpaper.testpaperType=='oldexam' || testpaper.testpaperType=='munualsimulation']
						<input type="button" continueAdd="false" class="button submitAdd" value="保存" />
					[/#if]
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='${base}/admin/testpaper/list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
	<div class="bar">
			<div class="buttonWrap">
				[#if testpaper.testpaperType=='oldexam' || testpaper.testpaperType=='munualsimulation']
					<a href="javascript:;" id="deleteButton" class="iconButton disabled">
						<span class="deleteIcon">&nbsp;</span>${message("admin.common.delete")}
					</a>
				[/#if]
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>${message("admin.common.refresh")}
				</a>
			</div>
	</div>
	<table id="listTable" class="list">
		<tbody>
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<a href="javascript:;" class="sort" >章节名称</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" >序号</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" >备注</a>
				</th>
				<th>
					<span>${message("admin.common.handle")}</span>
				</th>
			</tr>
		</tbody>
	</table>
</body>
</html>