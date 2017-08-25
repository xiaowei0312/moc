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
// 编辑器
	KindEditor.ready(function(K) {
			editor=KindEditor.create("#aboutCourse", {
				height: "200px",
				width:"60%",
				items:[
					"bold","fontsize", "italic","forecolor","underline","|","insertorderedlist","insertunorderedlist"
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
</script>
<script type="text/javascript">

$().ready(function() {

	var $inputForm = $("#inputForm");
	var $courseCategoryId = $("#courseCategoryId");
	var $browserButton = $("#browserButton");
	var $courseImageTable = $("#courseImageTable");
	var $submit=$(".submit");
	
	
	//下拉框左右选择变量
	var $add = $("#add");
	var $add_all = $("#add_all");
	var $remove = $("#remove");
	var $remove_all = $("#remove_all");
	
	[@flash_message /]
	
	$submit.click(function(){
		if(editor.count()>(1048576-1000)){
			$.message("error","编缉器的数据过多");
			return false;
		}
		$inputForm.submit();
	});
	
	var previousProductCategoryId = getCookie("previousProductCategoryId");
	if (previousProductCategoryId != null) {
		$courseCategoryId.val(previousProductCategoryId);
	} else {
		previousProductCategoryId = $courseCategoryId.val();
	}
	
	$browserButton.browser({callback: function(url){
		$("#sourceImage").attr("src",url);
	}});
	
	//下拉框左右选择
	$('.select_option span').mouseover(function(){
		$(this).addClass("blue");
		$(this).mouseleave(function(){
			$(this).removeClass("blue");
		});
	});
	//移到左边--添加
    $add.click(function() {
    //获取选中的选项，删除并追加给对方
        $('#allTeachers option:selected').appendTo('#teacherIds');
    });
    //移到右边
    $remove.click(function() {
        $('#teacherIds option:selected').appendTo('#allTeachers');
    });
    //全部移到左边
    $add_all.click(function() {
        //获取全部的选项,删除并追加给对方
        $('#allTeachers option').appendTo('#teacherIds');
        $('#teacherIds option').each(function(){
        	$(this).attr("selected",true);
        });
    });
    //全部移到右边
    $remove_all.click(function() {
        $('#teacherIds option').appendTo('#allTeachers');
    });
    //双击选项
    $('#allTeachers').dblclick(function(){ //绑定双击事件
        //获取全部的选项,删除并追加给对方
        $("option:selected",this).appendTo('#teacherIds'); //追加给对方
    });
    //双击选项
    $('#teacherIds').dblclick(function(){
       $("option:selected",this).appendTo('#allTeachers');
    });
	
	// 表单验证
	$inputForm.validate({
		rules: {
			courseCategoryId: "required",
			daysOfNotifyBeforeDeadline:"digits",
			title: "required"
		}
	});
	
});
</script>
<style type="text/css">
	#assignTeacher div{
		float:left;
	}
	.select_side select{
		width:100px;
		height:200px;
	}
	.select_option{
		padding-top:70px;
		padding-left:10px;
		padding-right:10px;
	}
	.select_option span{
		cursor:pointer;
	}
</style>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.course.add")}
	</div>
	<form id="inputForm" action="save.jhtml" method="post" enctype="multipart/form-data">
	
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="${message("admin.course.base")}" />
			</li>
			<li>
				<input type="button" value="${message("admin.course.particular")}" />
			</li>
			<li>
				<input type="button" value="${message("admin.course.courseImage")}" />
			</li>
		</ul>
		<table class="input tabContent">
			<tr>
				<th>
					${message("Course.courseCategory")}:
				</th>
				<td>
					<select id="courseCategoryId" name="courseCategory.id">
						[#list courseCategoryTree as courseCategory]
							<option value="${courseCategory.id}">
								[#if courseCategory.grade != 0]
									[#list 1..courseCategory.grade as i]
										&nbsp;&nbsp;
									[/#list]
								[/#if]
								${courseCategory.name}
							</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>课程名称:
				</th>
				<td>
					<input type="text" name="title" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Course.seoTitle")}:
				</th>
				<td>
					<input type="text" name="seoTitle" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Course.tags")}:
				</th>
				<td>
					<input type="text" name="tags" class="text"/>
				</td>
			</tr>
			<tr>
				<th>
					有效期:
				</th>
				<td>
					<input type="text" name="daysOfNotifyBeforeDeadline" class="text" maxlength="16" value="0"/>天
				</td>
			</tr>
			
			<tr>
				<th>
					到期提醒
				</th>
				<td>
					[#list enum as val]
						[#if "${val}"="none"]
							<input type="radio" name="deadlineNotify" checked value="${val}"/>否
						[#else]
							<input type="radio" name="deadlineNotify" value="${val}"/>是
						[/#if]
					[/#list]
				</td>
			</tr>
			<tr>
				<th>
					连载模式:
				</th>
				<td>
					[#list enum2 as val]
						[#if "${val}"="none"]
							<input type="radio" name="serializeMode" checked value="${val}"/>非连载
						[#elseif "${val}"="finished"]
							<input type="radio" name="serializeMode" value="${val}"/>完结
						[#else]
							<input type="radio" name="serializeMode" value="${val}"/>连载中
						[/#if]
					[/#list]
				</td>
			</tr>
			<tr>
				<th>
					学员人数是否显示:
				</th>
				<td>
					[#list enum3 as val]
						[#if "${val}"="opened"]
							<input type="radio" name="showStudentNumType" checked value="${val}"/>开启
						[#else]
							<input type="radio" name="showStudentNumType" value="${val}"/>关闭
						[/#if]
					[/#list]
				</td>
			</tr>
			<tr>
				<th>
					课程价格：
				</th>
				<td>
					<input type="text" name="price" value="0" class="text" />
				</td>
			</tr>
			<tr>
				<th>
					序号：
				</th>
				<td>
					<input type="text" name="order" value="0" class="text" />
				</td>
			</tr>
		</table>
		<table class="input tabContent">
			<tr>
				<th>${message("Course.about")}:</th>
				<td>
					<textarea id="aboutCourse" name="about" class="text" style="width: 90%;"></textarea>
				</td>
			</tr>
			<tr>
				<th></th>
				<td>
					<i id="aboutCourseTips" style="color:red"></i>
				</td>
			</tr>
		[#--
			<tr>
				<th>
					${message("Course.goals")}:
				</th>
				<td>
					<textarea id="goals" name="goals"></textarea>
				</td>
			</tr>
			<tr>
				<th>
					${message("Course.audiences")}:
				</th>
				<td>
					<textarea id="audiences" name="audiences"></textarea>
				</td>
			</tr>
		--]
			<tr>
				<th>
					指派老师:
				</th>
				<td>
					<div id="assignTeacher">
					     <div class="select_side">
					     	<p><strong>已选区</strong></p>
						     <select id="teacherIds" name="teacherIds" multiple="multiple">
						     </select>
						 </div>
					     <div class="select_option">
					        <span id="add" >&lt;&lt;选中添加到左边</span><br>
            				<span id="add_all" >&lt;&lt;全部添加到左边</span><br>
            				<span id="remove">选中删除到右边&gt;&gt;</span><br>
            				<span id="remove_all">全部删除到右边&gt;&gt;</span>
					     </div>
						 <div class="select_side">
						      <p>待选区</p>
						     <select id="allTeachers" multiple="multiple">
						         [#list teachers as teacher]
						         	<option value="${teacher.id}">${teacher.truename}</option>
						         [/#list]
						     </select>
     					</div>
					 </div>
				</td>
			</tr>
		</table>
		<table id="courseImageTable" class="input tabContent" style="text-align:center">
			<tr class="cent">
				<td >
					<span class="fieldSet">
						<input type="text" name="sourceImage" class="text" maxlength="200" readonly="readonly" value="${base}/resources/admin/images/c1.jpg"/>
						<input type="button" id="browserButton" class="button" value="${message("admin.browser.select")}" />
					</span>
				</td>
			</tr>
			<tr>
				<td style="align:center">
					<img src="${base}/resources/admin/images/c1.jpg" id="sourceImage" width="300px"/>
					<p>你可以上传jpg、gif、png格式的文件，图片建议尺寸至少为500*280。文件大小不能超过50K。</p>
				</td>
			</tr>
			
		</table>
		
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="button" class="button submit" value="${message("admin.common.submit")}" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>