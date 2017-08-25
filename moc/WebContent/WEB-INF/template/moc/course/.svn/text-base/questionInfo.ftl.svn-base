<ul class="evalucourse" id="questionList">
	[#list page.content as thread]
	<li>
		[#if thread.member??]
	    	<span class="pephead"><img src="
	    	[#if thread.member.headImage?? && thread.member.headImage!=""]
	    	${thread.member.headImage}
	    	[#else]
	    		${base}/resources/moc/images/0-0.JPG
	    	[/#if]
	    	" width="50" title="${thread.member.username}">
			<p class="pepname">${thread.member.username}</p>                             
	        </span>
	    [#else]
	    	<span class="pephead"><img src="${base}/resources/moc/images/0-0.JPG" width="50" title="无名">
			<p class="pepname">未知</p>                             
	        </span>
	    [/#if]
        <span class="pepcont">
        <p><a href="${base}/courseThread/view.jhtml?threadId=${thread.id}" class="peptitle">${thread.title}</a></p>
        <p class="peptime pswer"><span class="pepask">回答(<strong><a class="bluelink" href="${base}/courseThread/view.jhtml?threadId=${thread.id}">${thread.postNum}</a></strong>)&nbsp;&nbsp;&nbsp;&nbsp;浏览(<strong><a class="bluelink" href="${base}/courseThread/view.jhtml?threadId=${thread.id}">${thread.hitNum}</a></strong>)</span>${thread.createDate}</p>                        
        </span>
    </li>
    [/#list]
</ul>
