[#list testpaperChapterList as testpaperChapter]
<tr class="info">
	<td>
		<input type="checkbox" name="ids" value="${testpaperChapter.id}" />
	</td>
	<td>
		${testpaperChapter.name}
	</td>
	<td>
		${testpaperChapter.order}
	</td>
	<td>
		${testpaperChapter.description}
	</td>
	<td>
		<a href="javascript:void(0);"  onclick="findTestpaperChapter(${testpaperChapter.id});">[${message("admin.common.edit")}]</a>
	</td>
</tr>
[/#list]
