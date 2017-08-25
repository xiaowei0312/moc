<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.member.add")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<style>
   .mpp {
		background: #E6E6E6;
		height: 15px;
		width: 200px;
		border-radius: 5px;
	}
	
	.lv {
		height: 100%;
		background: #3eb0e0;
		border-radius: 5px;
	}
	.clearh {
		clear: both;
		height: 10px;
	}
</style>

<script type="text/javascript">
    function show(memberId){
			window.location.href="${base}/admin/course/viewMember.jhtml?id="+memberId
    }
    
    $().ready(function() {
        var $listForm=$("#listForm");
        var $memberRemark=$(".memberRemark");
        
        $memberRemark.click(function(){
            var id=$(this).children("input[id='id']").val();
            var description=$(this).children("input[id='description']").val();
        	$.dialog({
        	    title:"学员备注",
				[@compress single_line = true]
					content:'
					<table id="moreTable" class="moreTable">
					   <tr>
					    	<td>
					    	   <textarea rows="6" style="width:360px;margin:10px 10px;" name="description">
					    	   '+description+'<\/textarea>
					    	<\/td>
					   <\/tr>
					<\/table>',
				[/@compress]
				width: 400,
				modal: true,
				onOk:function(){
				   var description=$("#moreTable textarea[name='description']").val();
				   if(description!=''){
				    	$.ajax({
							url:"${base}/admin/course/changeRemark.jhtml",
							type:"POST",
							data:{"id":id,"description":description},
							dataType:"json",
							cache: false,
							success:function(message){
								$.message(message);	
								$listForm.submit();
							}
						});
				    }
				}
			});
        });
    });
</script>
</head>
<body>
    <div class="path">
		<a href="${base}/admin/common/index.jhtml">首页</a> &raquo;
		 学员列表(${courseTitle}) <span>(${message("admin.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="${base}/admin/course/memberList.jhtml" method="get">
	<input type="hidden" id="courseId" name="courseId" value="${courseId}"/>
	<div class="bar">
		<div class="buttonWrap">
			<a href="${base}/admin/course/list.jhtml" class="button">返回</a>
		</div>
		<div class="menuWrap">
				<div class="search">
					<span id="searchPropertySelect" class="arrow">&nbsp;</span>
					<input type="text" id="searchValue" name="searchValue" value="${page.searchValue}" maxlength="200" />
					<button type="submit">&nbsp;</button>
				</div>
				<div class="popupMenu">
					<ul id="searchPropertyOption">
						<li>
							<a href="javascript:;" [#if page.searchProperty == "username"] class="current" [/#if] val="username">用户名</a>
						</li>
					</ul>
				</div>
			</div>
	</div>
	<table id="listTable" class="list" style="width:100%"
	    <tr>
	    	<th style="width:35%;">
	    	     学员
	    	</th>
	    	<th style="width:35%;">
	    	   学习进度
	    	</th>
	    	<th style="width:30%;">
	    	   操作
	    	</th>
	    </tr>
	    [#list page.content as obj]
		    <tr>
		    	<td>
		    	    <img id="imgHeadPhoto"  style="width:50px;height:50px;border:solid 1px #d2e2e2;margin-right:10px;float:left;" alt="" 
		                 src=
		                  [#if obj[3]?? && obj[3]!=Null]
		  					"${obj[3]}"
		  				  [#else]
		  					"${base}/resources/moc/images/headimg-big.jpg"
		  				  [/#if]
		              />
		              <span style="margin-left:10px;">
			              ${obj[2]}<br/>
			                           加入时间:${obj[1]?string("yyyy-MM-dd HH:mm")}
		              </span>
		              <div class="clearh"></div>
		    	</td>
		    	<td>
		    		<div class="mpp">
                        <div class="lv" style=
                           [#if obj[4]?? && obj[4]!=Null]
                              "width:${obj[4]}%;"
                           [#else]
                              "width:0%;"
                           [/#if]
                        >
                        </div>
                    </div>
		    	</td>
		    	<td>
		    	   <a href="javascript:void(0)" onclick="show(${obj[5]})">[查看资料]</a>
		    	   <a href="javascript:;" class="memberRemark">
		    	       <input type="hidden" id="id" value="${obj[0]}"/>
		    	       <input type="hidden" id="description" value="${obj[6]}"/>
		    	   		[备注]
		    	   	</a>
		    	</td>
		    </tr>
		[/#list]
	</table>
	  [@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
				[#include "/admin/include/pagination.ftl"]
		[/@pagination]
	</form>
</body>
</html>