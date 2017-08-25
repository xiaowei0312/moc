<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>谋刻职业教育在线测评与学习平台</title>
<link rel="stylesheet" href="${base}/resources/moc/css/askarea.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/tab.css" media="screen">
<link rel="stylesheet" href="${base}/resources/moc/css/jquery.bxslider.css">
<link href="${base}/resources/moc/css/common.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="${base}/resources/moc/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/jquery.tabs.js"></script>
<script  type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/mine.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/jquery.treeSelect.js"></script>

<script type="text/javascript">
    $().ready(function(){
       var $courseCategory=$("#courseCategory");
       var $threadPublish=$("#threadPublish");
       var $inputForm=$("#inputForm");
       
       //类别选择
		$courseCategory.treeSelect({
			url: "${base}/member/courseThread/getCourseCategoryChildren.jhtml"
			
		});
		
		$threadPublish.click(function(){
		    var $courseCategoryId=$("select[name='courseCategoryId_select']");
		    var size=$courseCategoryId.size();
	        var title=$("#title").val();
	        var content=$("#content").val();
	        if(title=="请输入标题" || title=="" || title=="标题"){
	           $.message("error","请输入标题");
	           return false;
	        }
	        if(content=="" || content=="提问问题..."){
	           $.message("error","请输入提问问题");
	           return false;
	        }
	        [#--修改人--荣刚平---只可以在叶子类别下提问题---2015，4，24--]
	        if(size<=1){
	        	var rootCategoryId=$("select[name='courseCategoryId_select']:eq(0) option:selected").val();
	        	if(rootCategoryId==""){
	        		$.message("error","请选择一级分类");
	        		return false;
	        	}
	        }else{
	            var categoryId=$("select[name='courseCategoryId_select']:eq(1) option:selected").val();
	            if(categoryId==""){
	                $.message("error","请选择子分类");
	                return false;
	            }
	        }
	        $inputForm.submit();
		});
    });
    
</script>

</head>

<body>
 [#include "/moc/include/answerHeader.ftl" /]
<div class="clearh" style="height:20px;"></div>
<div class="listcont">
	<div class="askleft">
    <div class="queoption">
    	<h3 class="askh3">发起问题</h3>
        
     </div>
     <div class="clearh" style="height:30px;"></div>
    	<div class="askbox">
        	<form class="askform" id="inputForm" action="${base}/courseThread/save.jhtml" method="post">
				<p>
					请选择项目：
					<input type="hidden" name="courseCategoryId" id="courseCategory"/>
				</p>
            	<input type="text" class="bf1" id="title" name="title"
            	 [#if askContent??]
            	 	value="${askContent}"
    			 [#else]        	 
	            	 value="标题"
            	 [/#if]
            	 onblur="if (this.value =='') this.value='标题';" 
            	 onclick="if (this.value=='标题' || this.value=='请输入关键字') this.value='';"
            	 /><br/>
                <div class="clearh"></div>
                <textarea class="bf2" rows="10" id="content" name="content"
                 onblur="if (this.value =='') this.value='提问问题...';" 
            	 onclick="if (this.value=='提问问题...') this.value='';"
                />提问问题...</textarea>
            </form>
         <p class="ask_btn fb"><a id="threadPublish" href="#">发布</a></p>
        </div>
    </div>
    
    <!--askright-->
  <div class="askright">   	
        <h3>热门问题</h3>
        <ul class="hotask">
         [#list list as courseThread]
        	<li class="hot">
        	<a class="ask_link" href="${base}/courseThread/view.jhtml?threadId=${courseThread.id}" title="${courseThread.title}">${courseThread.title}</a>
        	</li>
         [/#list]
        </ul>
        <span class="askad">
           <img src="${base}/resources/moc/images/ad.jpg" width="200" height="200">
        </span>
  </div>
</div>

<div class="clearh"></div>
 [#include "/moc/include/footer.ftl" /]
</body>

</html>

