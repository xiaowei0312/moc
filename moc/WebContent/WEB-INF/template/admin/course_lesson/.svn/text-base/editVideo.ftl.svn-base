[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.course.add")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>

<script type="text/javascript">


$().ready(function() {

	var $inputForm = $("#inputForm");
	var $current = $(".current");
	var $default = $(".default");
	var $type = $("#type");
	var $mediaId = $("#mediaId");
	var $length = $("#length");
	var $isUploadedFile = $("#isUploadedFile");
	var $btnSubmit = $("#btnSubmit");
	var $editVideo = $("#editVideo");
	var $divEdit = $("#divEdit");
	var $divDisplay = $("#divDisplay");
	var $list = $(".list tr"); 
	var $videoMinute = $("#videoMinute"); 
	var $videoSeconds = $("#videoSeconds"); 
	
	[@flash_message /]
	
	$current.mouseover(function(){
		$(this).addClass("blue current");
		$(this).mouseleave(function(){
			$(this).removeClass("blue");
		});
	});
	
	$current.click(function(){
		var currentName = $(this).attr("name");
		var $currentShow = $("#"+$(this).attr("name"));
		$currentShow.prevAll().hide();
		$currentShow.nextAll().hide();
		$currentShow.show();
		
		$(this).prevAll().removeClass("green");
		$(this).nextAll().removeClass("green");
		$(this).addClass("green");
		recoverInitialVal();
	});


	function recoverInitialVal(){
		//禁止提交
		$isUploadedFile.val('false');
		//清除背景色
		$list.each(function(){
			$(this).css("background-color","#FFFFFF");
		});
		$mediaId.val("");
		$length.val("");
		$videoMinute.val("");
		$videoSeconds.val("");
	}

	$list.click(function(){
		$(this).css("background-color","#B2E0FF");
		$(this).siblings().each(function(){
			$(this).css("background-color","#FFFFFF");
		});
		$mediaId.val($(this).attr("id"));
		$length.val($(this).attr("length"));
		$isUploadedFile.val('true');
		
		$videoMinute.val(parseInt($length.val()/60));
		$videoSeconds.val(parseInt($length.val()%60));
	})
	
	$editVideo.click(function(){
		$divDisplay.hide();
		$divEdit.show();
		recoverInitialVal();
	});
	
	
	// 表单验证
	$inputForm.validate({
		rules: {
			title: {
				required: true
			},
			coin:{
				required:true,
				digits:true,
				min:0
			}
		},
		messages: {
			title: {
				pattern: "${message("admin.validate.required")}"
			},
			coin:{
				required:'学币必须填写',
				digits:'学币必须是正整数',
				min:'学币不能小于0'
			}
		}
	});
	
	$btnSubmit.click(function(e){
		if($isUploadedFile.val() == "false"){
			$.message("warn","请选择文件");
			return false;
		}
	})
});



</script>
<style type="text/css">

iframe{
	padding: 0px;
	margin: 0px;
	border: 0px;
	outline: 0px;

}

div.container{
	width:100%;
	height:80%;
}
</style>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 编辑课时
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
		<input type="hidden" name="id" id="id" value="${courseLesson.id}"/>
		<input type="hidden" name="uploadFiles.id" id="mediaId" value="${courseLesson.uploadFiles.id}"/>
		<input type="hidden" name="isUploadedFile" id="isUploadedFile" value="true"/>
		<input type="hidden" name="length" id="length" value="${courseLesson.length}"/>
		<input type="hidden" name="course.id" id="courseId" value="${courseLesson.course.id}"/>
		<table class="input">
			<tr>
				<th>
					标题:
				</th>
				<td>
						<input type="text" name="title" class="text" style="width:400px;" value="${courseLesson.title}"/>
						<input type="checkbox" name="free" [#if courseLesson.free == "0"] checked="checked" [/#if] value="0"/>免费课时
				</td>
			</tr>
			<tr>
				<th>
					学币：
				</th>
				<td>
					<input type="text" name="coin" value="${courseLesson.coin}"/>
				</td>
			</tr>
			<tr>
				<th>
					摘要:
				</th>
				<td>
						<textarea name="summary" cols="50" class="text">${courseLesson.summary}</textarea>
				</td>
			</tr>
			
			<tr> 
				<th>
					视频:
				</th>
				<td>
					<div id="divDisplay">${courseLesson.mediaName}<span id="editVideo" style="cursor:pointer;">[编辑]</span></div>	
					<div style="display:none" id="divEdit">
						<span style="cursor:pointer;"  class="gray green current" name="courseMedia">从课程文件中选择</span>
						<span style="cursor:pointer;"  class="gray current" name="libraryMedia">从资料库中选择</span>
						<div>
							
							<div id="courseMedia">
								<table id="videoListTable" class="list">
									[#list videoUploadFiles as uploadFile]
										<tr id="${uploadFile.id}" length="${uploadFile.length}">
											<td>${uploadFile.filename}</td>
											<td>${(uploadFile.size/1048576)?string('0.00')}M</td>
											<td>
												${uploadFile.createDate}
											</td>
											
										</tr>
									[/#list]
								</table>
							</div>
							<div id="libraryMedia" class="hidden">库</div>
						</div>
					</div>
					
				</td>
			</tr>
			
			<tr>
				<th>
					视频时长:
				</th>
				<td>
					<input name="videoMinute" id="videoMinute" placeholder="请输入整数" style="width:100px;" value="${(courseLesson.length/60)?string('0')}"/>&nbsp;&nbsp;分
					<input name="videoSeconds" id="videoSeconds" placeholder="请输入整数" style="width:100px;" value="${(courseLesson.length%60)}"/>&nbsp;&nbsp;秒
				</td>
			</tr>
		</table>
		
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("admin.common.submit")}" id="btnSubmit" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='${base}/admin/course_chapter/list.jhtml?courseId=${courseLesson.course.id}'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>