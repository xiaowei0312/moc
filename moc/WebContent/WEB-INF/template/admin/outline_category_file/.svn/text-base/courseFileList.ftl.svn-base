<input type="hidden" id="pageTotal" value="${page.total}" />
<script>
	$("button[type='submit']").click(function(){
		$("#pageNumberReal").val($("#pageNumber").val());
		getCourseFiles($(".sendData").serialize());
	});
	// 全选
	$("#selectAll").click( function() {
		var $this = $(this);
		var $enabledIds = $("#listTable input[name='ids']:enabled");
		var $contentRow=$("#listTable tr:gt(0)");
		if ($this.prop("checked")) {
			$enabledIds.prop("checked", true);
			if ($enabledIds.filter(":checked").size() > 0) {
				$contentRow.addClass("selected");
			} 
		} else {
			$enabledIds.prop("checked", false);
			 $contentRow.removeClass("selected");
		}
	});
	
	// 选择
	$("#listTable input[name='ids']").click( function() {
		var $this = $(this);
		if ($this.prop("checked")) {
			$this.closest("tr").addClass("selected");
		} else {
			$this.closest("tr").removeClass("selected");
		}
	});
</script>
<table id="listTable" class="list">
        <tr>
         <th class="check">
				<input type="checkbox" id="selectAll" />
		 </th>
		 <th>
				文件名称
		 </th>
		 <th>
				文件类型
		 </th>
		 <th>
				大小
		 </th>
		 <th>
				文件状态
		 </th>
		 <th>
		 		上传人
		 </th>
		 <th>
		 		更新时间
		 </th>
		 <th>
		 		所属课程
		 </th>
      </tr>
      [#-- 0--id,1--文件名称,2--文件类型,3--大小,4--文件状态,5--上传人,6--更新时间,7--所属课程--]
	   [#list page.content as obj]
	     <tr>
	        <td>
				<input type="checkbox" name="ids" value="${obj[0]}" />
			</td>
			<td>
			    ${obj[1]}
			</td>
			<td>
			    [#if obj[2]=="video"]
			                  视频
			    [#elseif obj[2]=="audio"]  
			                 音频
			    [#elseif obj[2]=="ppt"]  
			      ppt
			    [#elseif obj[2]=="txt"]       
			      	txt
			     [#else]
			                   其他
			    [/#if]
			</td>
			<td>
				${unitConversion.bytes2kb(obj[3])}
			</td>
			<td>
			[#if obj[2]=="video"]
				[#--0--none, 1--waiting, 2--doing, 3--success, 4--error--]
				[#if obj[4]==1]
				[正在上传]
				[#elseif obj[4]==2]
				[正在转换]
				[#elseif obj[4]==4]
				[转换失败]
				[#elseif obj[4]==3]
				[转换成功]
				[/#if]
			[/#if]
			</td>
			<td>
				${obj[5]}
			</td>
			<td>
				${obj[6]?string("yyyy-MM-dd HH:mm:ss")}
			</td>
			<td>
				${obj[7]}
			</td>
		 </tr>		
	   [/#list]
</table>
[@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
	[#include "/admin/include/pagination.ftl"]
[/@pagination]
