	<style>
		.star_zy {
			background: none repeat scroll 0 0 #3eb0e0;
			border-radius: 5px;
			color: #fff;
			cursor: pointer;
			height: 30px;
			line-height: 30px;
			text-align: center;
			width: 100px;
			margin-top: 10px;
			padding: 5px 10px;
		}
		
		.c_eform.veform {
			width: 300px;
			margin-left: 20px;
			font-size: 14px;
		}
		
		.c_eform.veform p {
			margin-top: 10px
		}
		
		.test {
			font-weight: normal;
			color: #fb5e55;
			text-decoration:none;
			font-size:14px;
		}
		.test:link,.test:visited {
			color: #fb5e55;
			text-decoration: none
		}

		.test:hover {
			color: #fb5e55;
			text-decoration: none
		}
		
		.mbm_btn {
			background: none repeat scroll 0 0 #3eb0e0;
			border-radius: 3px;
			color: #fff;
			display: block;
			font-size: 14px;
			padding-bottom: 5px;
			padding-top: 5px;
			text-align: center;
			width: 70px;
			text-decoration:none;
		  
		}
		.c_eform.veform p{
			line-height:25px;
		}
		.c_eform.veform{
			margin-left:30px;
		}
		
		.clearh {
			clear: both;
			height: 10px;
		}
		
	</style>
	<link href="${base}/resources/moc/css/common.css" rel="stylesheet" type="text/css"/>
	<script  type="text/javascript" src="${base}/resources/moc/js/jquery.js"></script>
	<script  type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
	<script  type="text/javascript">
		$().ready(function(){
			var $listForm=$("#listForm");
			var $pageNumber = $("#pageNumber");
			//分页
	       $.pageSkip = function(pageNumber) {
				$pageNumber.val(pageNumber);
				$listForm.submit();
				return false;
		    }
		});
	</script>
	<div class="c_eform veform">
	  <p style="text-align:center;">
	  	 [#if outlineCategoryId?? && outlineCategoryId!=0]
			  <a class="mbm_btn" href="${base}/member/question/exercise.jhtml?outlineCategoryId=${outlineCategoryId}&rootOutlineCategoryId=${rootOutlineCategoryId}&testpaperType=specialexercis" target="_blank">
			 	 开始测评
			  </a>
	     [/#if]
	  </p>
	  [#if page?? && page.content?size>0] 
		  <form id="listForm" action="${base}/member/course/toEvalution.jhtml" method="post">
		  	 <input type="hidden" id="pageNumber" name="pageNumber" value="${page.pageNumber}" />
		  	 <input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}" />
		  	 <input type="hidden" name="lessonId" value="${lessonId}"/>
			 [#list page.content as testpaperResult]
				  <div style="border-bottom:1px solid #ccc;">
					  <p>
					  	[#if testpaperResult.status=="finished"]
							<a class="test" target="_blank" href="${base}/member/question/analysisCertificate.jhtml?testpaperResultId=${testpaperResult.id}">
								·${testpaperResult.paperName}
							</a>
						[#else]
							<a class="test" target="_blank" href="${base}/member/question/continueAnswerExam.jhtml?testpaperResultId=${testpaperResult.id}">
								·${testpaperResult.paperName}
							</a>
						[/#if]
						<br>
						<span style="font-size:12px;color:#666;margin-left: 10px">
							练习时间：${testpaperResult.createDate?string("yyyy-MM-dd HH:mm")}
						</span>
					  </p>
				 </div>
			[/#list]
			<div class="clearh"></div>
	       [@pagination pageNumber = page.pageNumber totalPages = page.totalPages pattern = "javascript: $.pageSkip({pageNumber});"]
					[#include "/moc/include/pagination.ftl"]
		    [/@pagination]
		</form>
	[#else]
		<p style="text-align:center;color:#333;">
			暂无测评数据
		</p>
	[/#if]
  </div>