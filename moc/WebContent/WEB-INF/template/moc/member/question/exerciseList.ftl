
<table class="mbmlist train" width="100%" cellpadding="0" cellspacing="0">
	[#list testpaperResults as testpaperResult]
		<tr>
	     	<td  class="mbm1" width="80%">
	          <span  class="blacklink">
		          <a class="blacklink" href="javascript:void(0);">
		          	${testpaperResult.paperName}
		          </a>
	          </span>
			  <a class="gray">共${testpaperResult.testpaper.itemCount}道题</a>&nbsp;&nbsp;&nbsp;&nbsp;
	          <a class="gray">练习时间：${testpaperResult.beginDate?string("yyyy/MM/dd HH:mm:ss")}</a>
	        </td>
	        <td width="9%">
	          	<div>
	          		[#if testpaperResult.status!='finished']
	          			 <a class="mbm_btn no_btn">未完成</a>
	          		 [#else]
	           			<a class="mbm_btn" target="_blank"  href="${base}/member/question/seeExamDetail.jhtml?testpaperResultId=${testpaperResult.id}&&&&outlineCategoryId=${testpaperResult.rootOutlineCategory}">查看报告</a>
	           		[/#if]
	            </div>
	        </td>
	        <td class="trm" width="5%">
	          <div>
	          	[#if testpaperResult.status!='finished']
	          			<a class="mbm_btn" target="_blank" href="${base}/member/question/continueAnswerExam.jhtml?testpaperResultId=${testpaperResult.id}">继续练习</a>
	          		 [#else]
	           			 <a class="mbm_btn" target="_blank" href="${base}/member/question/analysisCertificate.jhtml?testpaperResultId=${testpaperResult.id}">查看解析</a>
	           		[/#if]
	            </div>
	        </td>
		 </tr>
	[/#list]	
</table>