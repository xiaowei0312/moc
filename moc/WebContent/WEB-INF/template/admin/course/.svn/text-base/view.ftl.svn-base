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
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.member.view")}
	</div>
	<ul id="tab" class="tab">
			<li>
				<input type="button" value="基本信息" />
			</li>
			<li>
				<input type="button" value="详细信息" />
			</li>
			<li>
				<input type="button" value="课程图片" />
			</li>
	</ul>
	<table class="input tabContent">
	      <tr>
	         <th>
	                                课程类别：
	         </th>
	         <td>
	            ${course.courseCategory.name}
	         </td>
	      </tr>
	      <tr>
	         <th>
	                                课程名称：
	         </th>
	         <td>
	            ${course.title}
	         </td>
	      </tr>
	       <tr>
	         <th>
	                                副标题：
	         </th>
	         <td>
	            ${course.seoTitle}
	         </td>
	      </tr>
	      <tr>
	         <th>
	                                标签：
	         </th>
	         <td>
	            ${course.tags}
	         </td>
	      </tr>
	       <tr>
	         <th>
	                               有效期：
	         </th>
	         <td>
	            ${course.daysOfNotifyBeforeDeadline}天
	         </td>
	      </tr>
	         <tr>
	         <th>
	                               到期提醒：
	         </th>
	         <td>
	            <input type="radio" name="deadlineNotify" [#if course.deadlineNotify="none"] checked [/#if] value="none"/>
	            <label>否</label>&nbsp;&nbsp;&nbsp;&nbsp;
	            <input type="radio" name="deadlineNotify" [#if course.deadlineNotify="active"] checked [/#if] value="active"/>
	            <label>是</label>&nbsp;&nbsp;&nbsp;&nbsp;
	         </td>
	      </tr>
	      <tr>
	         <th>
	                               连载模式：
	         </th>
	         <td>
	            <input type="radio" name="serializeMode" [#if course.serializeMode="none"] checked [/#if] value="none"/>
	            <label>草稿</label>&nbsp;&nbsp;&nbsp;&nbsp;
	            <input type="radio" name="serializeMode" [#if course.serializeMode="serialize"] checked [/#if] value="serialize"/>
	            <label>连载中</label>&nbsp;&nbsp;&nbsp;&nbsp;
	            <input type="radio" name="serializeMode" [#if course.serializeMode="finished"] checked [/#if] value="finished"/>
	            <label>完结</label>&nbsp;&nbsp;&nbsp;&nbsp;
	         </td>
	      </tr>
	      <tr>
	         <th>
	                               学生人数是否显示：
	         </th>
	         <td>
	            <input type="radio" name="showStudentNumType" [#if course.showStudentNumType="opened"] checked [/#if] value="opened"/>
	            <label>显示</label>&nbsp;&nbsp;&nbsp;&nbsp;
	             <input type="radio" name="showStudentNumType" [#if course.showStudentNumType="closed"] checked [/#if] value="closed"/>
	            <label>关闭</label>&nbsp;&nbsp;&nbsp;&nbsp;
	         </td>
	      </tr>
	             <tr>
	         <th>
	                               课程价格：
	         </th>
	         <td>
	            ${course.price}元
	         </td>
	      </tr>
	</table>
	<table class="input tabContent">
	      	<tr>
				<th>课程简介</th>
				<td>
					<textarea name="about" class="text" style="width: 90%;" value="${course.about}"></textarea>
				</td>
			</tr>
			<tr>
				<th>
					课程目标:
				</th>
				<td>
					${course.goals}
				</td>
			</tr>
			<tr>
				<th>
					适应人群:
				</th>
				<td>
				   ${course.audiences}
				</td>
			</tr>
			<tr>
				<th>
					指派老师:
				</th>
				<td>
					<div id="assignTeacher">
					     <div class="select_side">
					     	<p><strong>已选区</strong></p>
						     <select id="teacherIds" name="teacherIds" multiple="multiple">
						     	[#list teachers as teacher]
						     		<option value="${teacher.id}">${teacher.truename}</option>
						     	[/#list]
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
						         [#list selectedTeachers as teacher]
						         	<option value="${teacher.id}">${teacher.truename}</option>
						         [/#list]
						     </select>
     					</div>
					 </div>
				</td>
	</table>
	<table class="input tabContent">
	   <tr>
			<th>
				课程图片:
			</th>
			<td>
				<td>
					<img id="sourceImage" src=[#if course.thumbnailImage?? && course.thumbnailImage!=Null]
							"${course.thumbnailImage}"
							[#else]
							"${base}/resources/admin/images/coursePicture.png"
							[/#if]
							 width="300px"/>
				</td>
			</td>
	</tr>
	</table>
	<table class="input">
		<tr>
			<th>
				&nbsp;
			</th>
			<td>
				<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='list.jhtml'" />
			</td>
		</tr>
	</table>
</body>
</html>