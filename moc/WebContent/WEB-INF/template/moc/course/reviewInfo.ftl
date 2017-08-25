<ul class="evalucourse" id="reviewList">
	[#list page.content as review]
	<li>
		[#if review.member??]
			<span class="pephead"><img src="
			[#if review.member.headImage?? && review.member.headImage !=""]
				${review.member.headImage}
			[#else]
				${base}/resources/moc/images/0-0.JPG
			[/#if]
			" width="50" title="${review.member.username}">
	        <p class="pepname">${review.member.username}</p>                           
	        </span>
		[#else]
			<span class="pephead"><img src="${base}/resources/moc/images/0-0.JPG" width="50" title="候候">
	        <p class="pepname">候候候候</p>                           
	        </span>
		[/#if]
        <span class="pepcont">
        	<p class="pep_pj pep_pj1">
        		<img width="100" height="18" src="${base}/resources/moc/images/evaluate${review.rating}.png" data-bd-imgshare-binded="1">
        		${review.title}
        	</p>
        	[#if review.content??]
	        	<p class="pep_pj1">${review.content}</p>
	        [/#if]
        	<p class="peptime pswer pep_pj1">${review.createDate}</p>
        </span>
    </li>
    [/#list]
</ul>
