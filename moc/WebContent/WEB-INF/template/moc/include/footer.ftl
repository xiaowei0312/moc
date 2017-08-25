<div class="clearh"></div>
<div class="foot">
<div class="fcontainer">
      <div class="fwxwb"> 
	       <div class="fwxwb_1">
		       <span>关注微信</span><img width="95" alt="" src="${base}/resources/moc/images/num.png">
		   </div>
           <div>
               <span>关注微博</span><img width="95" alt="" src="${base}/resources/moc/images/wb.png">
		   </div>	              
      </div>
      <div class="fmenu">
      	<p>
      	 [@navigation_list position = "bottom"]
      	 	[#list navigations as navigation]
      	 		 <a href="${navigation.url}">${navigation.name}</a>
      	 		 [#if navigation_has_next]|[/#if]
      	 	[/#list]
      	 [/@navigation_list]
	    </p>
      </div>
      <div class="copyright">      
        <div><a href="/">谋刻网</a>所有&nbsp;晋ICP备12006957号-9</div>
      </div>
    </div>
</div>
</div>

<!--右侧浮动-->
<div class="rmbar">
	<span class="barico qq" style="position:relative">
	<div  class="showqq">
	   <p>官方客服QQ:<br>335049335</p>
	</div>
	</span>
	<span class="barico em" style="position:relative">
	  <img src="${base}/resources/moc/images/num.png" width="75" class="showem">
	</span>
	<span class="barico wb" style="position:relative">
	  <img src="${base}/resources/moc/images/wb.png" width="75" class="showwb">
	</span>	
	<span class="barico top" id="top">置顶</span>	
</div>
<!-- InstanceEnd -->
