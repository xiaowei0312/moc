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
	var $courseCategoryId = $("#courseCategoryId");
	var $browserButton = $("#browserButton");
	var $current = $(".current");
	var $default = $(".default");
	var $type = $("#type");
	var $mediaId = $("#mediaId");
	var $mediaName = $("#mediaName");
	var $length = $("#length");
	var $isUploadedFile = $("#isUploadedFile");
	var $btnSubmit = $("#btnSubmit");
	var $free = $("#free");
	var $list = $(".list tr"); 
	var $videoMinute = $("#videoMinute"); 
	var $videoSeconds = $("#videoSeconds"); 
	var $audioMinute = $("#audioMinute"); 
	var $audioSeconds = $("#audioSeconds"); 
	
	
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
	
	$free.click(function(){
		if($(this).is(':checked')){
			$free.val("1");
		}else{
			$free.val("0");
		}
	});
	
	$list.click(function(){
		$(this).css("background-color","#B2E0FF");
		$(this).siblings().each(function(){
			$(this).css("background-color","#FFFFFF");
		});
		$mediaId.val($(this).attr("id"));
		$mediaName.val($(this).children(":first-child").html());
		$length.val($(this).attr("length"));
		if('video'==$type.val()){
			$videoMinute.val(parseInt($length.val()/60));
			$videoSeconds.val(parseInt($length.val()%60));
		}else if('audio'==$type.val()){
			$audioMinute.val(parseInt($length.val()/60));
			$audioSeconds.val(parseInt($length.val()%60));
		}
		$isUploadedFile.val('true');
	})

	
	if ($.tools != null) {
		var $tab = $("#tab");

		// Tab效果
		$tab.tabs("table.tabContent, div.tabContent", {
			tabs: "input",
			onClick:function(index){  
				var targetValue = index.target.defaultValue;
				if(targetValue == '视频'){
					$type.val('video');
					recoverInitialVal();
				}else if(targetValue == '音频'){
					$type.val('audio');
					recoverInitialVal();
				}else if(targetValue == '图文'){
					$type.val('text');
					recoverInitialVal();
					//图片不考虑上传附件，设置为true
					$isUploadedFile.val('true');
				}
				return true;  
			},
		});
	}
	//恢复初始值
	function recoverInitialVal(){
		$isUploadedFile.val('false');
		$videoMinute.val("");
		$videoSeconds.val("");
		$audioMinute.val("");
		$audioSeconds.val("");
		$mediaId.val("");
		$mediaName.val("");
		$length.val("");
		//清除背景色
		$list.each(function(){
			$(this).css("background-color","#FFFFFF");
		});
	}
	
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
<style>
iframe{
	height:50px;
}
</style>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 添加课时
	</div>
	<form id="inputForm" action="save.jhtml" method="post">
		<input type="hidden" name="uploadFiles.id" id="mediaId"/>
		<input type="hidden" name="mediaName" id="mediaName"/>
		<input type="hidden" name="length" id="length" value="0"/>
		<input type="hidden" name="course.id" id="courseId" value="${courseId}"/>
		<input type="hidden" name="courseChapter.id" id="chapterId" value="${chapterId}"/>
		<input type="hidden" name="mediaSource" id="mediaSource" value="self"/>
		<input type="hidden" name="type" id="type" value="video"/>
		<input type="hidden" name="replayStatus" id="replayStatus" value="ungenerated"/>
		<input type="hidden" name="status" id="status" value="unpublished"/>
		<input type="hidden" name="isUploadedFile" id="isUploadedFile" value="false"/>
		
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="视频" />
			</li>
			<li>
				<input type="button" value="音频" />
			</li>
			<li>
				<input type="button" value="图文" />
			</li>
			
		</ul>
		
		<table class="input">
			<tr>
				<th>
					标题:
				</th>
				<td>
						<input type="text" name="title" class="text" style="width:400px;"/>
						<input type="checkbox" name="free" id="free" value="0" checked="checked"/>免费课时
				</td>
			</tr>
			<tr>
				<th>
					学币
				</th>
				<td>
					<input type="text" name="coin" value="0"/>
				</td>
			</tr>
			<tr>
				<th>
					摘要:
				</th>
				<td>
						<textarea name="summary" cols="50" class="text" ></textarea>
				</td>
			</tr>
		</table>
		<table class="input tabContent">
			<tr>
				<th>
					视频:
				</th>
				<td>
					<span style="cursor:pointer;"  class="gray green current" name="courseMedia">从课程文件中选择</span>
					<span style="cursor:pointer;"  class="gray current" name="libraryMedia">从资料库中选择</span>
					<div >
						<div id="courseMedia" style="overflow:auto;">
							<table  class="list">
								[#list courseLessonFiles as uploadFile]
									[#if uploadFile.fileType="video"]
										<tr id="${uploadFile.id}" length="${uploadFile.length}">
											<td>${uploadFile.filename}</td>
											<td>${uniConversion.bytes2kb(uploadFile.size)}</td>
											<td>
												${uploadFile.createDate?string("yyyy-MM-dd HH:mm:ss")}
											</td>
										</tr>
									[/#if]
								[/#list]
							</table>
											
						</div>
						<div id="libraryMedia" class="hidden">
							<table  class="list">
								[#list courseMaterialFiles as uploadFile]
									[#if uploadFile.fileType="video"]
										<tr id="${uploadFile.id}" length="${uploadFile.length}">
											<td>${uploadFile.filename}</td>
											<td>${uniConversion.bytes2kb(uploadFile.size)}</td>
											<td>
												${uploadFile.createDate?string("yyyy-MM-dd HH:mm:ss")}
											</td>
										</tr>
									[/#if]
								[/#list]
							</table>
						</div>
					</div>
					
				</td>
			</tr>
			
			<tr>
				<th>
					视频时长:
				</th>
				<td>
					<input name="videoMinute" id="videoMinute" placeholder="请输入整数" style="width:100px;" />&nbsp;&nbsp;分
					<input name="videoSeconds" id="videoSeconds" placeholder="请输入整数" style="width:100px;" />&nbsp;&nbsp;秒
				</td>
			</tr>
		</table>
		<table class="input tabContent">
			<tr>
				<th>
					音频:
				</th>
				<td>
					<span style="cursor:pointer;"  class="gray green current" name="courseMusic">从课程文件中选择</span>
					<span style="cursor:pointer;"  class="gray current" name="libraryMusic">从资料库中选择</span>
					<div>
						<div id="courseMusic" >
							<table  class="list">
								[#list courseLessonFiles as uploadFile]
									[#if uploadFile.fileType="audio"]
										<tr id="${uploadFile.id}" length="${uploadFile.length}">
											<td>${uploadFile.filename}</td>
											<td>${uniConversion.bytes2kb(uploadFile.size)}</td>
											<td>
												${uploadFile.createDate?string("yyyy-MM-dd HH:mm:ss")}
											</td>
										</tr>
									[/#if]
								[/#list]
							</table>
						</div>
						<div id="libraryMusic" class="hidden" >
							<table  class="list">
								[#list courseMaterialFiles as uploadFile]
									[#if uploadFile.fileType="audio"]
										<tr id="${uploadFile.id}" length="${uploadFile.length}">
											<td>${uploadFile.filename}</td>
											<td>${uniConversion.bytes2kb(uploadFile.size)}</td>
											<td>
												${uploadFile.createDate?string("yyyy-MM-dd HH:mm:ss")}
											</td>
										</tr>
									[/#if]
								[/#list]
							</table>
						</div>
					</div>
				</td>
			</tr>
		
			<tr>
				<th>
					音频时长:
				</th>
				<td>
					<input name="audioMinute"  id="audioMinute" placeholder="请输入整数" style="width:100px;height:20px" />&nbsp;&nbsp;分
					<input name="audioSeconds"  id="audioSeconds" placeholder="请输入整数" style="width:100px;height:20px" />&nbsp;&nbsp;秒
				</td>
			</tr>
		</table>
		<table class="input tabContent">
			<tr>	
				<th>图文</th>
				
				<td>
					<textarea id="editor" name="content" style="width:500px;" ></textarea>
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
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='${base}/admin/course_chapter/list.jhtml?courseId=${courseId}'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
