<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <title>谋题库-职业考试测评利器-答题报告</title>
    <meta name="keywords" content="财会类、计算机类、工程类等考试真题、模拟试题" />
    <meta name="description" content="谋题库-智能职业类考试在线做题系统提供财会类、计算机类、工程类等考试真题、模拟题，智能出题、全真模考，职业考试的备考首选平台" />
    <link rel="stylesheet" href="${base}/resources/moc/css/exam.css" />
    <link rel="stylesheet" href="${base}/resources/moc/css/tab.css" media="screen">
    <script src="${base}/resources/moc/js/jquery-1.8.0.min.js"></script>
    <script src="${base}/resources/moc/js/jquery.tabs.js"></script>
    <script src="${base}/resources/moc/js/mine.js"></script>
    <script type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
    <script>
	     $(function() {
	     	//动态设置正确和总数，正确率
	     	//设置节点隐藏
	     	$('[gradeClas^="keypoint-level-"]').each(function(i){
			  	var clsasStr=$(this).attr('class');
			  	
				var data_id=$(this).attr('data_id');
				var data_parent_id=$(this).attr('data_parent_id');
				if($(this).find('span').attr('class')=='tree_parent'){
					$("td[data_parent_id='"+data_id+"']").parent().show();
					class_children(this);
				}
			 });
	     	
	     
			$('[gradeClas^="keypoint-level-"]').on("click", function(){
				if($(this).find('span').attr('class')=='tree_none'){
					return true;
				}
				var clsasStr=$(this).attr('class');
				var data_id=$(this).attr('data_id');
				var data_parent_id=$(this).attr('data_parent_id');
				if($(this).find('span').attr('class')=='tree_parent'){
					$(this).find('span').removeClass("tree_parent");
					$(this).find('span').addClass("tree_children");
					$("td[data_parent_id='"+data_id+"']").parent().show();
				}else{
					$(this).find('span').removeClass("tree_children");
					$(this).find('span').addClass("tree_parent");
					children(this);
				}
			});
		})
		//设置class
		function class_children(value){
			var clsasStr=$(value).find('span').attr('class');
			var data_id=$(value).attr('data_id');
			
		  	if(clsasStr == 'tree_none'){
	  			return true;
		  	}else{
		  		class_children2(data_id);
		  	}
		}
		
		function class_children2(data_id){
			
			if($("td[data_parent_id='"+data_id+"']").html() == undefined){
				$("td[data_id='"+data_id+"']").find("span").removeClass();
				$("td[data_id='"+data_id+"']").find("span").addClass("tree_none");
				return true;
			}else{
				$("td[data_parent_id='"+data_id+"']").each(function(i){
						clsasStr=$(this).find('span').attr('class');
						data_id=$(this).attr('data_id');
				  		var children_id=$(this).attr('data_id');
				  		$("td[data_id='"+data_id+"']").parent().hide();
				  		$(this).find('span').removeClass("tree_children");
						$(this).find('span').addClass("tree_parent");
				  		if($("td[data_parent_id='"+data_id+"']").html() != undefined){
				  			class_children2(data_id);
				  		}
				  		
				});
			}
		}
		function children(value){
			var clsasStr=$(value).find('span').attr('class');
			var data_id=$(value).attr('data_id');
		  	if(clsasStr == 'tree_none'){
	  			return true;
		  	}else{
		  		children2(data_id);
		  	}
		}
		
		function children2(data_id){
			if($("td[data_parent_id='"+data_id+"']").html() == undefined){
				return true;
			}else{
				$("td[data_parent_id='"+data_id+"']").each(function(i){
						clsasStr=$(this).find('span').attr('class');
						data_id=$(this).attr('data_id');
				  		var children_id=$(this).attr('data_id');
				  		$("td[data_id='"+data_id+"']").parent().hide();
				  		if(clsasStr!='tree_none'){
					  		$(this).find('span').removeClass("tree_children");
							$(this).find('span').addClass("tree_parent");
				  		}
				  		if($("td[data_parent_id='"+data_id+"']").html() != undefined){
				  			children2(data_id);
				  		}
				});
			}
		}
    </script>
     <script>
        var w = $(window).width();
        var lw = (w - 980) / 2 - 10;
        $(window).scroll(function() {
            var topHeight = $(window).scrollTop();
            if (topHeight > 100) {
                $(".right_menu").css({
                    "position": "fixed",
                    "top": "10px",
                    "right": lw
                });
            } else {
                $(".right_menu").css({
                    "position": "fixed",
                    "top": "90px",
                    "right": lw
                });
            }
        });
        $(document).ready(function(){
    		 var currentURL=String(window.location);
        	 var maoIndex=currentURL.lastIndexOf("#");
        	 if(maoIndex!=(-1)){
        		 var courseChapterId=currentURL.substring((maoIndex+1));
        		 $("#"+courseChapterId).attr("display","block");
        	 }
        });  
    </script>
</head>

<body>
    [#include "/moc/include/questionHeader.ftl" /]
    <div class="clearh"></div>
    <div class="listcont">
        <div class="examtext">
            <!--左侧栏-->
            <div class="left_nemu">
                <!--题目栏-->
                <div class="examhead">
                    <h2>${testpaper.name}</h2>
                    <p style="margin-top:20px;margin-bottom:20px;">交卷时间：${submitTime}</a>
                    </p>
                </div>
                <!--题目栏-->
                <div class="examlist" style="min-height:10px;">
                    <div class="pagescore" style="width:100%">
                        <div class="paper_t" style="width:360px; text-align:center">
                            <p>本次练习${testpaper.totalCount}道题，你答对了</p>
                            <span class="paper_right">${testpaper.totalCount-testpaper.wrongCount}</span>道
                        </div>
                        <div class="paper_t" style="width:360px; float:right; text-align:center">
                            <p>本次练习${testpaper.totalCount}道题，你答错了</p>
                            <span class="paper_wrong">${testpaper.wrongCount}</span>道
                        </div>
                    </div>
                    <div class="clearh"></div>
                    [#list testpaper.testpaperChapters as chapter]
	                    <div class="paper_answer">
	                        <p class="examtit corec">${chapter.name}</p>
	                    [#list chapter.testpaperItems as testpaperItem]
	                    	 [#if testpaperItem.questionType=="material"]
	                    	 	<div>
	                	 			<div class="examtit corec corec_zi">材料${testpaperItem_index+1}</div>
	                    	 		<ul>
			                    	 	[#list testpaperItem.children as child]
					                    	<li [#if (child_index+1)%5==0]class="fenli"[/#if] ><span class="[#if child.testpaperItemResult.status=="right"]right_answer[#else]error_answer[/#if]">${child_index+1}</span></li>	
			                    	 	[/#list]
	                    	 		</ul>
	                    	 		<div style="clear:both;"></div>
                    	 		</div>
	                    	 [#else]
	                    	 	<ul>
		                            [#if testpaperItem.testpaperItemResult??]
		                            <li [#if (testpaperItem_index+1)%5==0]class="fenli"[/#if] ><span class="[#if testpaperItem.testpaperItemResult.status=="right"]right_answer[#else]error_answer[/#if]">${testpaperItem_index+1}</span></li>
		                            [/#if]
	                        	</ul>
	                    	 [/#if]
	                    [/#list]
	                    </div>
                    [/#list]
                </div>
                <div class="examlist">
                    <div class="paper_t" style="width:720px;font-size:16px;font-weight:700">涉及考点</div>
                    <table width="95%" class="sjkd" border="1">
                        <tr>
                            <th>考点</th>
                            <th>答题量</th>
                            <th>正确率</th>
                            <th>相关课程</th>
                        </tr>
                        [#assign tempParent=null /]
                        [#list outlineCategories as outlineCategorie]
                        	[#if outlineCategorie[7]!=0]
		                        <tr [#if tempParent != outlineCategorie[2]]  [/#if]>
		                            <td class="first end" gradeClas="keypoint-level-${outlineCategorie[3]}"
		                            	[#if !outlineCategorie[2]??] data_id="${outlineCategorie[0]}" [/#if] 
										[#if outlineCategorie[2]??] data_id="${outlineCategorie[0]}" data_parent_id="${outlineCategorie[2]}" [/#if]
		                            >
		                                <span style="margin-left: ${outlineCategorie[3] * 20}px;" class="tree_parent">${outlineCategorie[1]}</span>
		                            </td>
		                            <td>${outlineCategorie[6]}</b>道/${outlineCategorie[7]}道</td>
		                            <td>${(outlineCategorie[6]/outlineCategorie[7]*100)?string('#.##')}%</td>
		                            <td>
		                            	[#--8关联的类型，9关联的主键--]
		                            	
                                		[#if outlineCategorie[8]?? && outlineCategorie[9]??]
	                                	<a class="bg_btn" href="${base}/member/course/getRelatePath.jhtml?course_chapter_type_ordinal=${outlineCategorie[8]}&course_chapter_id=${outlineCategorie[9]}">
	                                		视频学习
	                                	</a>
                                		[/#if]
		                            </td>
		                        </tr>
		                        [#assign tempParent = outlineCategorie[0] /]
	                        [/#if]
                        [/#list]
                    </table>
                </div>
            </div>
            <!--左侧栏-->
            <!--右侧栏-->
            <div class="right_menu">
                <div class="examans">
                    <img src="${base}/resources/moc/images/book.png" width="100" height="100">
                    <p class="exam_pause" onClick="window.location.href='${base}/member/question/list.jhtml?industryCategoryID=${testpaper.outlineCategory.industryCategoryID}&outlineCategoryID=${testpaper.outlineCategory.id}'">
                        <span>能力评估</span>
                    </p>
                    <p class="exam_pause" onClick="window.location.href='analysisCertificate.jhtml?testpaperResultId=${testpaperResultId}'">
                        <a class="baogaolink">查看解析</a>
                    </p>
                </div>
            </div>
            <!--右侧栏-->
        </div>
        <div class="heigt"></div>
    </div>
    <div class="clearh"></div>
    [#include "/moc/include/footer.ftl" /]
</body>
</html>
