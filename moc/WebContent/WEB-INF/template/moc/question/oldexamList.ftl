<table class="tc" ellspacing="0" cellpadding="0" width="100%" >
		[#list testpapers as testpaper]
		    <tr>
		      <td class="titlelist" width="85%">${testpaper.name}</td>
		      <td width="15%">
		          <div>
		            <a href="${base}/member/question/generateoldexam.jhtml?testpaperID=${testpaper.id}&&outlineCategoryId=${outlineCategoryID}" class="mbm_btn" style="width:70px;" target="_blank">开始考试</a>
		          </div>
		      </td>
		    </tr>
		 [/#list]
		 [#if !testpapers??]
		 	<tr align="center"><td colspan='2'>未找到相关记录。</td></tr>
		 [/#if]
</table>