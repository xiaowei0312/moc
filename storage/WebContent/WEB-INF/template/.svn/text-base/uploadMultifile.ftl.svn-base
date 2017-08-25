<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<!-- production -->
<link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="${base}/resources/plupload/i18n/zh_CN.js"></script>

</head>
<body >
<br/>
<div id="filelist">你的游览器不支持Flash。</div>
<div id="container">
    <a id="pickfiles" href="javascript:;">[选择文件]</a> 
    <a id="uploadfiles" href="javascript:;">[上传文件]</a>
</div>
<br/>
<div>
<table id="listTable" class="list">
	<tr>
		<th>
			文件名称
		</th>
		<th>
			文件大小
		</th>
		<th>
			进度
		</th>
		<th>
			操作
		</th>
	</tr>
</table>

</div>
<div id="warning" style="color:red">

</div>

<pre id="console"></pre>
<br/>
<br/>
<br/>
<br/>
<script type="text/javascript">
// Custom example logic
var targetId = ${targetId};
var targetType = "${targetType}";
var uploader = new plupload.Uploader({
	runtimes : 'html5,flash,silverlight',
	browse_button : 'pickfiles', // you can pass in id...
	container: document.getElementById('container'), // ... or DOM Element itself
	url : '${base}/uploadFiles/upload.jhtml',
	flash_swf_url : '${base}/resources/plupload/Moxie.swf',
	silverlight_xap_url : '${base}/resources/plupload/Moxie.xap',
	chunk_size : '1mb',
	multipart_params:{
		targetId:'${targetId}',
		targetType:'${targetType}',
		userId:'${userId}',
		token:'${token}'
	},
	filters : {
		max_file_size : '1024mb',
		
		mime_types: [
			[#if targetType == "courselesson"]
				{title : "视频文件", extensions : "mp4,avi,flv,wmv,mov"},
				{title : "音频文件", extensions : "mp3"},
				{title : "PPT文件", extensions : "ppt,pptx"}
			[#else]
				{title : "备用资料文件", extensions : "*"}
			[/#if]
		]
	},
	init: {
		PostInit: function() {
			$('#filelist').html('');
			
			$('#uploadfiles').click (function() {
				uploader.start();
				return false;
			});
		},

		FilesAdded: function(up, files) {
			for(var i=0;i<files.length;i++){
				var tr = "<tr id='" + files[i].id + "tr'><td>" + files[i].name + "</td><td>" + plupload.formatSize(files[i].size) 
					+  "</td><td id='" + files[i].id + "_progress'></td><td><a href='javascript:' onclick=del('" + files[i].id + "')>[删除]</a></td></tr>";
				$('#listTable').append(tr);
			}
			 
		},
 
		UploadProgress: function(up, file) {
			$("#" + file.id + "_progress").html("<span>" + file.percent + "%</span>");
		},
		UploadFile:function(uploader,file){
		},
		ChunkUploaded:function(uploader,file,responseObject){
			var obj = eval('(' + responseObject.response + ')');
			if(obj != null && "error" == obj.message.type){
				uploader.stop();
				$.message(obj.message);
			}
		},
		FileUploaded:function(uploader,file,responseObject){
			var obj = eval('(' + responseObject.response + ')');
			$.message(obj.message);
		},
		UploadComplete:function(uploader,files,responseObject){
			
		},

		Error: function(up, err) {
			$('#console').html($('#console').html() + "\n错误 #" + err.code + ": " + err.message);
		}
	}
	
});

uploader.init();

function del(id){
	uploader.removeFile(id);
	$("#" + id + "tr").remove();
}
</script>
</body>
</html>
