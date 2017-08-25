<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.member.add")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.treeSelect.js"></script>
<script type="text/javascript">

$().ready(function() {
	var $getUploadFiles = $("#getUploadFiles");
	var $searchPropertyOption = $("#searchPropertyOption a");
	var $categoryAndCourse =$("#categoryAndCourse");
	var $searchPropertyCurrent = $("#searchPropertyOption a.current");
	var $pageSizeOption = $("#pageSizeOption a");
	var $pageSize = $("#pageSize");
	var $pageNumber = $("#pageNumber");
		//类别选择
	$categoryAndCourse.treeSelect({
		url: "${base}/admin/outline_category_material/categoryAndCourse.jhtml",
		cssStyle:{"float":"left","margin-right":"4px"},
		choose: "请选择类别",
		choose2: "请选择课程"
	});

		// 每页记录数
	$pageSizeOption.click( function() {
		var $this = $(this);
		
		if($this.hasClass("current")){
			return false;
		}
		$("#pageSizeOption a.current").removeClass("current");
		$this.addClass("current");
		$pageSize.val($this.attr("val"));
		$pageNumber.val("1");
		getCourseFiles($(".sendData").serialize());
		return false;
	});	
	//重新定义分页方法
	// 页码跳转
	$.pageSkip = function(pageNumber) {
	 	$pageNumber.val(pageNumber);
		getCourseFiles($(".sendData").serialize());
	}
	
	//获取数据
	$getUploadFiles.click(function(){
		var courseCategoryId=$("select:eq(0)").val();
		var courseId = $("select:eq(1)").val();
		
		//searchProperty表单是追加的不能在上面声明
		$("#searchProperty").val($searchPropertyCurrent.attr("val")).addClass("sendData");
		
		if(courseId!=null){
			//选择了课程
			$categoryAndCourse.attr("name","targetId");
		}
		if(courseCategoryId==""){
			$.message("error","请先选择课程类别");
			return false;
		}
		getCourseFiles($(".sendData").serialize());
	});
	
});
//ajax发送请求
function getCourseFiles(sendData){
	$.ajax({
			url:"${base}/admin/outline_category_material/courseUploadFiles.jhtml",
			type:"POST",
			data:sendData,
			async:false,
			success:function(data){
				 $("#courseFileList").html(data);
				 	//显视数据条数
					$("#showCount").html($("#pageTotal").val());
			},
			error:function(data){
				$.message("error","操作失败");
			}
		});
}
</script>
</head>
<body>
<input class="sendData" type="hidden" id="pageSize" name="pageSize" value="10"/>
<input class="sendData" type="hidden" id="pageNumber" name="pageNumber" value="1"/>
	<div class="bar">
        <div class="buttonWrap" style="margin-left:20px">
        	[#--课程id或课程类别id--]
        	<input type="hidden" name="courseCategoryId" id="categoryAndCourse" class="sendData"/>
			<a href="javascript:;" id="refreshButton" class="iconButton">
				<span class="refreshIcon">&nbsp;</span>${message("admin.common.refresh")}
			</a>
			<div class="menuWrap">
					<a href="javascript:;" id="pageSizeSelect" class="button">
						每页显示<span class="arrow">&nbsp;</span>
					</a>
					<div style="float:left">
						共<span id="showCount">0</span>条数据
					</div>
					<div class="popupMenu">
						<ul id="pageSizeOption">
							<li>
								<a href="javascript:;" val="10" class="current">10</a>
							</li>
							<li>
								<a href="javascript:;" val="20">20</a>
							</li>
							<li>
								<a href="javascript:;" val="50">50</a>
							</li>
							<li>
								<a href="javascript:;" val="100">100</a>
							</li>
						</ul>
					</div>
			</div>
		</div>
		<div class="menuWrap" style="float:left">
				<div class="search">
					<span id="searchPropertySelect" class="arrow">&nbsp;</span>
					<input type="text" id="searchValue" name="searchValue" class="sendData" value="" maxlength="200" />
					<button id="getUploadFiles" title="获取文件">&nbsp;</button>
				</div>
				<div class="popupMenu">
					<ul id="searchPropertyOption">
						<li>
							<a href="javascript:;" class="current" val="fileName">文件名</a>
						</li>
						<li>
							<a href="javascript:;" val="courseName">课程名</a>
						</li>
					</ul>
				</div>
		</div>
	</div>
	
  	<div id="courseFileList">
  		<span style="margin-left:100px;font-size:25px">
  			请先选择课程类别
  		</span>
	</div>
		
</body>
</html>