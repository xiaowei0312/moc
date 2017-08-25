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
			editor = K.create("#content", {
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
   
	$().ready(function() {
	    [@flash_message /]
	});
   
</script>
</head>
<html>
    <div class="path">
		<a href="${base}/admin/common/index.jhtml">首页</a> &raquo; 添加公告
	</div>
	<form id="inputForm" action="update.jhtml" method="post" enctype="multipart/form-data">
	   <input type="hidden" name="id" value="${courseAnnouncement.id}"/>
	   <input type="hidden" name="userId" value="${courseAnnouncement.userId}"/>
	   <input type="hidden" name="createDate" value="${courseAnnouncement.createDate}"/>
	   <input type="hidden" name="course.id" value="${courseAnnouncement.course.id}"/>
	   <table  class="input tabContent">
	       <tr>
	           <th>
	                  <span class="requiredField">*</span> 公告内容：
	           </th>
	           <td>
	              <textarea name="content" id="content" >${courseAnnouncement.content}</textarea>
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
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="history.back();" />
				</td>
			</tr>
	</form>
</html>