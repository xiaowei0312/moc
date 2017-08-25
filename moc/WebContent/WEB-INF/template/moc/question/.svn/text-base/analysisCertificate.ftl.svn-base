<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <title>谋题库-职业考试测评利器-题库解析</title>
    <meta name="keywords" content="财会类、计算机类、工程类等考试真题、模拟试题" />
    <meta name="description" content="谋题库-智能职业类考试在线做题系统提供财会类、计算机类、工程类等考试真题、模拟题，智能出题、全真模考，职业考试的备考首选平台" />
    <link rel="stylesheet" href="${base}/resources/moc/css/exam.css" />
    <link rel="stylesheet" href="${base}/resources/moc/css/common.css" />
    <script src="${base}/resources/moc/js/jquery-1.8.0.min.js"></script>
    <link rel="stylesheet" href="${base}/resources/moc/css/tab.css" media="screen">
    <script type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
    <script src="${base}/resources/moc/js/jquery.tabs.js"></script>
    <script src="${base}/resources/moc/js/mine.js"></script>
    <script src="${base}/resources/moc/swfobject/swfobject.js" type="text/javascript" ></script>
    <script type="text/javascript">
         $(function() {
         	var $jiexi_video = $(".jiexi_video");
         
         	//查看视频解析
         	$jiexi_video.click(function(){
         		var $this = $(this);
         		var targetId = $this.attr("questionId");
				var targetType="question";
				
				//获取token和文件ID
				$.ajax({
					url:"${base}/member/question/getTokenFileId.jhtml",
					data:{targetType:targetType,targetId:targetId},
					dataType:"json",
					type:"GET",
					cache:false,
					async:false,
					success:function(data){
						var token = data.token;
						var uploadFileId=data.id;
						video_popup('1',800,450,token,uploadFileId);					
					},
					error:function(data){
						$.message("error","没有视频");
						return false;
					}
				});      		
         		
         	});
           
           
           
           //自动统计每个章节的条目数
            $('[id^="myTab3_Content"]').each(function(i) {
                var itemCount = 0;
                var clCount = 0;
                $(this).children('[id^="item_"]').each(function(i) {
                    itemCount++;
                    //材料子题
                    $(this).children().find('[id^="item_"]').each(function(j) {
                        itemCount++;
                        if (j == 0) {
                            clCount++;
                        }
                    });
                });
                $('.count_' + i).html(itemCount - clCount);
                $('.count_' + i).prev().html(itemCount - clCount);
            });
        });

        function nTabs(thisObj, Num) {
            if (thisObj.className == "currexam") return;
            var tabObj = thisObj.parentNode.id;
            var tabList = document.getElementById(tabObj).getElementsByTagName("li");
            for (i = 0; i < tabList.length; i++) {
                if (i == Num) {
                    thisObj.className = "currexam";
                    document.getElementById(tabObj + "_Content" + i).style.display = "block";
                } else {
                    tabList[i].className = "normal";
                    document.getElementById(tabObj + "_Content" + i).style.display = "none";
                }
            }
        }

        //跳转到目标试题
        function moveto(chapterIndex, itemId) {
            $("li").removeClass("currexam");
            $("#chapter_" + chapterIndex).addClass("currexam");
            $(".examlist").hide();
            $("#myTab3_Content" + chapterIndex).show();
            var thetop = $("#item_" + itemId).offset().top;
            $("html:not(:animated),body:not(:animated)").animate({
                scrollTop: thetop-20
            }, 300);
        }
         function nTabs2(num) {
            var beforNum=parseInt(num)-1;
            $("#chapter_"+beforNum).removeClass('currexam').addClass('norma3');
            $("#chapter_"+num).removeClass('norma3').addClass('currexam');
            $("#myTab3_Content" + num).show();
            $("#myTab3_Content" + beforNum).hide();
            
            var thetop = $("#myTab3_Content" + num).offset().top-250;
            $("html:not(:animated),body:not(:animated)").animate({scrollTop: thetop}, 0);
        }
            //只看错题
        $(function() {
            $("#showErrorQuestion").toggle(function() {
                    $(".rightQuestion").each(function(i){
					  	$(this).hide();
					 });
                    $(this).text("查看全部");
                },
                function() {
                    $(".rightQuestion").each(function(i){
					  	$(this).show();
					 });
                    $(this).text("只看错题");
                });
        })
        
        
        [#--视频解析--]
        function video_popup(video_id,width,height,token,uploadFilesId){
        	var src = "${setting.cloudServerSite}/file/view/"+token+"/" + uploadFilesId +".flv"
        	var flashvars = {
			    src: src,
			    autoPlay:false,
			    autoRewind:false,
			    loop:false,
			    bufferTime:8,
			    javascriptCallbackFunction: "onJSBridge"
			};
			
			
			var params = {
			    allowFullScreen: true
			    , allowScriptAccess: "always"
			    , bgcolor: "#000000"
			};
			var attrs = {
			    name: "player"
			};
        
		    var video='<div class="video_player" id="player_'+video_id+'"><span><a class="close" onclick="video_close(\''+video_id+'\');"></a></span><div id="ssss"></div></div>';
			
			$('body').append('<div id="mask"></div>');
			$('#mask').show();
			$('body').append(video);
			var top=$(document).scrollTop()+120;
			$('#player_'+video_id).css('top',top+'px');
			
			swfobject.embedSWF("http://cdn.staticfile.org/GrindPlayerCN/1.0.2/GrindPlayer.swf", "ssss", width, height, "10.2", null, flashvars, params, attrs);
		}

		function video_close(video_id){
			$('#mask').remove();
			$('#player_'+video_id).remove();
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
                    <h2 class="ntitle">${testpaper.name}答案解析</h2>
                    <p style="margin-top:15px;margin-bottom:15px;">交卷时间：${testpaperResult.beginDate?string("yyyy-MM-dd hh:mm:ss")}</p>
                    <div class="titleul">
                    	 <ul id="myTab3">
                            [#list testpaper.testpaperChapters as testpaperChapter]
                             [#if (testpaperChapter_index = 0)]
                            <li class='currexam' id='chapter_${testpaperChapter_index}' onclick="nTabs(this,${testpaperChapter_index});"><a href="#">${testpaperChapter.name} <span class="unfinish_${testpaperChapter.id}">${testpaperChapter.testpaperItems?size}</span>/<span class="count_${testpaperChapter_index}">${testpaperChapter.testpaperItems?size}</span></a></li>
                            [/#if] 
                            [#if (testpaperChapter_index > 0) ]
                            <li class='norma3' id='chapter_${testpaperChapter_index}' onclick="nTabs(this,${testpaperChapter_index});"><a href="#">${testpaperChapter.name} <span class="unfinish_${testpaperChapter.id}">${testpaperChapter.testpaperItems?size}</span>/<span class="count_${testpaperChapter_index}">${testpaperChapter.testpaperItems?size}</span></a></li>
                            [/#if] 
                            [/#list]
                        </ul>
                        <div class="clearh"></div>
                    </div>
                </div>
                <!--题目栏-->
                [#list testpaper.testpaperChapters as testpaperChapter]
                <div [#if testpaperChapter_index=0] style="display: block;" [/#if][#if testpaperChapter_index>0] style="display: none;" [/#if] id="myTab3_Content${testpaperChapter_index}" class="examlist">
                    <p class="examtit">${testpaperChapter.description}</p>
                    [#list testpaperChapter.testpaperItems as testpaperItem]
                    <div class="itemlist [#if testpaperItem.testpaperItemResult.status == "right"]rightQuestion[/#if]" id="item_${testpaperItem.id}">
                        <p>
                        <strong class="quenum">${testpaperItem_index+1}.</strong>&nbsp;&nbsp;
                        <span class="qtype"> 
                            [#if testpaperItem.question.questionType = 'single_choice' ](单选题)[/#if]
                            [#if testpaperItem.question.questionType = 'choice' ](多选题)[/#if]
                            [#if testpaperItem.question.questionType = 'uncertain_singlechoice' ](不定项选择题)[/#if]
                            [#if testpaperItem.question.questionType = 'uncertain_choice' ](不定项选择题)[/#if]
                            [#if testpaperItem.question.questionType = 'determine' ](判断题)[/#if]
                            [#if testpaperItem.question.questionType = 'blanks' ](填空题)[/#if]
                            [#if testpaperItem.question.questionType = 'material' ](材料题)[/#if]
                            [#if testpaperItem.question.questionType = 'essay' ](简答题)[/#if]
                            [#if testpaperItem.question.questionType = 'entry' ](分录题)[/#if]
                        </span>
                        <span class="quename">
	                        [#if testpaperItem.question.questionType != 'blanks']
	                        	${testpaperItem.question.stem}
	                        [#else]
	                        	${testpaperItem.stemToBlanks}
	                        [/#if]
                        </span>
                        </p>
                         [#if testpaperItem.question.questionType ='material']
                         	<p>
					           <a class="dblink1 [#if questionFavoriteIds?seq_contains(testpaperItem.question.id)] material_qx [#else] material_sc[/#if]"  id="${testpaperItem.question.id}">
					           		[#if questionFavoriteIds?seq_contains(testpaperItem.question.id)] 取消收藏 [#else] 收藏本题 [/#if]
					           </a>
				           </p>
                        [/#if]
                        [#if testpaperItem.question.questionType = 'single_choice' || testpaperItem.question.questionType = 'choice' || testpaperItem.question.questionType = 'uncertain_singlechoice' || testpaperItem.question.questionType = 'uncertain_choice' ]
                        <div class="answer">
                            [#list testpaperItem.question.toMetas as meta]
                            <p [#if testpaperItem.question.toCharAnswer?index_of(meta[0..0])!=-1]class="right_anw" [/#if]>
                            	${meta}
                            </p>
                            [/#list]
                        </div>
                        [/#if]
                        [#if testpaperItem.question.questionType != 'material' ]
	                        <p class="ansright spanright no ew">
	                            <span class="answer">
	                            	正确答案是<b class="right_anw">${testpaperItem.question.formatAnswer}</b>，
		                            [#if testpaperItem.testpaperItemResult.status=='none']您没有作答[#else]您的答案是<b class="right_anw">
		                            [#if testpaperItem.question.questionType = 'single_choice' || testpaperItem.question.questionType = 'choice' || testpaperItem.question.questionType = 'uncertain_singlechoice' || testpaperItem.question.questionType = 'uncertain_choice' ]${testpaperItem.testpaperItemResult.choiseAnswer}[/#if]
		                           	[#if testpaperItem.question.questionType = 'determine']${testpaperItem.testpaperItemResult.determineAnswer}[/#if]
		                           	[#if testpaperItem.question.questionType=='essay']${testpaperItem.testpaperItemResult.essayAnswer}[/#if]
		                           	[#if testpaperItem.question.questionType=='blanks']${testpaperItem.testpaperItemResult.blankAnswer}[/#if]
		                           	[#if testpaperItem.question.questionType=='entry']${testpaperItem.testpaperItemResult.entryAnswer}[/#if]
		                           </b>，回答[#if testpaperItem.testpaperItemResult.status=="right"]正确[#else]错误[/#if][/#if]
	                           </span>
	                            <span class="dblink">
	            					<a class="dblink1 [#if questionFavoriteIds?seq_contains(testpaperItem.question.id)] qx [#else] sc[/#if]"  id="${testpaperItem.question.id}">[#if questionFavoriteIds?seq_contains(testpaperItem.question.id)] 取消收藏 [#else] 收藏本题 [/#if]</a><a class="dblink1 db5" >展开解析</a>
	          					</span>
	                            <div class="clearh"></div>
	                        </p>
	                        <div class="jiexilist">
	                        	[#if testpaperItem.question.origin??]<p><strong class="examlabel">来源</strong> ${testpaperItem.question.origin}</p>[/#if]
	                            <p>
	                            	<strong class="examlabel">考点</strong>  
	                            	${testpaperItem.question.outlineCategory.name}
		                            [#--大纲有关联的视频--]
		                            [#if testpaperItem.question.outlineCategory.course_chapter_type?? && testpaperItem.question.outlineCategory.course_chapter_id??]
		                            	<a href="${base}/member/course/getRelatePath.jhtml?course_chapter_type=${testpaperItem.question.outlineCategory.course_chapter_type}&course_chapter_id=${testpaperItem.question.outlineCategory.course_chapter_id}"><span>(点击观看考点视频)</span></a>
		                            [/#if]
	                            </p>
	                            <p><strong class="examlabel">解析</strong>${testpaperItem.question.analysis}</p>
	                            
	                            [#--要判断是否有对应的视频
		                            <p><strong class="examlabel">听老师讲</strong>
		                                <ul class="t_jiexi">
		                                    <li>
		                                        <div class="jiexi_video" questionId="${testpaperItem.question.id}">
		                                            <a href="javascript:void(0);">
		                                            	<img width="100%" height="100" src="
		                                            	${setting.cloudServerSite}/file/viewImage/question/${testpaperItem.question.id}.jhtml
		                                            	">
		                                            </a>
		                                            <p><a href="javascript:void(0);" class="link2 lw">课程标题一讲解</a></p>
		                                        </div>
		                                    </li>
		                                    <div class="clearh"></div>
		                                </ul>
		                            </p>
		                           --]
	                        </div>
	                        <!--jiexilist-->
	                        <div class="clearh"></div>
                        [#else] 
                        <div class="answer" style="padding-left:0;">
	                        [#list testpaperItem.children as child]
			                        <p class="title" id="item_${child.id}">
				                        <span class="quename">
				                        	(${child_index+1})
					                        <span class="qtype"> 
					                            [#if child.question.questionType = 'single_choice' ](单选题)[/#if]
					                            [#if child.question.questionType = 'choice' ](多选题)[/#if]
					                            [#if child.question.questionType = 'uncertain_singlechoice' ](不定项选择题)[/#if]
					                            [#if child.question.questionType = 'uncertain_choice' ](不定项选择题)[/#if]
					                            [#if child.question.questionType = 'determine' ](判断题)[/#if]
					                            [#if child.question.questionType = 'blanks' ](填空题)[/#if]
					                            [#if child.question.questionType = 'material' ](材料题)[/#if]
					                            [#if child.question.questionType = 'essay' ](简答题)[/#if]
					                            [#if child.question.questionType = 'entry' ](分录题)[/#if]
					                        </span>
				                       		[#if child.question.questionType = 'single_choice' || child.question.questionType = 'choice' || child.question.questionType = 'uncertain_singlechoice' || child.question.questionType = 'uncertain_choice' || child.question.questionType=='entry' ]${child.question.stem}[/#if]
				                        	[#if child.question.questionType = 'blanks'  ]${child.stemToBlanks}[/#if]
				                        </span>
			                        </p>
			                        [#if child.question.questionType = 'single_choice' || child.question.questionType = 'choice' || child.question.questionType = 'uncertain_singlechoice' || child.question.questionType = 'uncertain_choice' ]
				                        <div class="answer">
				                            [#list child.question.toMetas as meta]
				                            <p [#if child.question.toCharAnswer?index_of(meta[0..0])!=-1]class="right_anw" [/#if]>${meta}</p>
				                            [/#list]
				                        </div>
			                        [/#if]
			                        <p class="ansright spanright no ew">
			                            <span class="answer">正确答案是<b class="right_anw">
			                            [#if child.question.questionType =='entry']
			                            	[#list child.question.entryAnswer as entryAnswer]
			                            			${entryAnswer[0]},
			                            			[@account_category_list ]
												        [#list accountCategorys as category]
												        	[#if category.code==entryAnswer[1]]
												        		${category.name},
												        	[/#if]
												        [/#list]
												    [/@account_category_list]
			                            			${entryAnswer[2]}元;
			                            	[/#list]
			                         	[#else]
			                         		${child.question.formatAnswer}
			                            [/#if]
			                            </b>
			                            [#if child.testpaperItemResult.status=='none']您没有作答[#else]您的答案是<b class="right_anw">
			                           	[#if child.question.questionType = 'single_choice' || child.question.questionType = 'choice' || child.question.questionType = 'uncertain_singlechoice' || testpaperItem.question.questionType = 'uncertain_choice' ]${child.testpaperItemResult.choiseAnswer}[/#if]
			                           	[#if child.question.questionType = 'determine']${child.testpaperItemResult.determineAnswer}[/#if]
			                           	[#if child.question.questionType=='essay']${child.testpaperItemResult.essayAnswer}[/#if]
			                           	[#if child.question.questionType=='blanks']${child.testpaperItemResult.blankAnswer}[/#if]
			                           	[#if child.question.questionType=='entry']
			                           		[#if child.testpaperItemResult.entryAnswerArrList??]
			                           			[#list child.testpaperItemResult.entryAnswerArrList as entryAnswerArr]
			                           				${entryAnswerArr[0]},
			                           				[#if entryAnswerArr?size gte 2]
				                           				[@account_category_list ]
													        [#list accountCategorys as category]
													        	[#if category.code==entryAnswerArr[1]]
													        		${category.name},
													        	[/#if]
													        [/#list]
													    [/@account_category_list]
													[#else]
														,
												    [/#if]
												    [#if entryAnswerArr?size gte 3]
			                            				${entryAnswerArr[2]}元;
		                            				[#else]
		                            					;
			                            			[/#if]
			                            		[/#list]
			                           		 [/#if]
			                           	[/#if]
			                            </b>,回答[#if child.testpaperItemResult.status=="right"]正确[#else]错误[/#if][/#if]</span>
			                            <span class="dblink">
							            <a class="dblink1 db5" >展开解析</a>
							            </span>
			                            <div class="clearh"></div>
			                        </p>
			                        <div class="jiexilist">
			                            <p><strong class="examlabel">来源</strong> ${testpaper.name}</p>
			                            <p><strong class="examlabel">考点</strong> ${testpaperItem.question.outlineCategory.name}</p>
			                            <p><strong class="examlabel">解析</strong>${child.question.analysis}
			                            </p>
			                           [#--要判断是否有对应的视频
			                            <p><strong class="examlabel">听老师讲</strong>
			                                <ul class="t_jiexi">
			                                    <li>
			                                        <div class="jiexi_video" questionId="${testpaperItem.question.id}">
			                                            <a href="javascript:void(0);"><img width="100%" height="100" src="${base}/resources/moc/images/radio.jpg"></a>
			                                            <p><a href="javascript:void(0);" class="link2 lw">课程标题一讲解</a></p>
			                                        </div>
			                                    </li>
			                                    <div class="clearh"></div>
			                                </ul>
			                            </p>
			                            --]
			                        </div>
			                        <!--jiexilist-->
			                        <div class="clearh"></div>
	                      	  [/#list] 
	                        </div>
                        [/#if]
                    </div>
                    <!--itemlist-->
                    [/#list]
                    [#if testpaperChapter_index != testpaper.testpaperChapters?size-1]
                        <a class="exam_pause" href="javascript:void(0);" onclick="nTabs2(${testpaperChapter_index+1});">进入下一部分</a>
                    [/#if]
                </div>
                <!--examlist-->
                [/#list]

            </div>
            <!--left_nemu-->
            <!--左侧栏-->

            <!--右侧栏-->
            <div class="right_menu">
                <div class="examans">
                    <p class="exam_pause" onClick="window.location.href='${base}/member/question/seeExamDetail.jhtml?testpaperResultId=${testpaperResultId}&outlineCategoryId=${testpaper.outlineCategory.id}'"><span><a href="${base}/member/question/seeExamDetail.jhtml?testpaperResultId=${testpaperResultId}&outlineCategoryId=${testpaper.outlineCategory.id}" class="baogaolink">查看报告</a></span></p>
                    <p class="submitexam" id="showErrorQuestion"><span><a href="#" class="baogaolink"  >只看错题</a></span></p>
                    <p class="save-exercise findAllJiexi"  ><span><a href="javascript:void(0)" class="baogaolink" >展开全部解析</a></span></p>
                </div>

		      <div class="examans child">
		        <h2>答题卡</h2>
		        <div class="clearh"></div>
		        [#list testpaper.testpaperChapters as testpaperChapter]
                    <div class="answcard">
                        <span class="cardspan">${testpaperChapter.name}(<strong class="unfinish unfinish_${testpaperChapter.id}">${testpaperChapter.testpaperItems?size}</strong>/<span class="count_${testpaperChapter_index}">0</span>)</span>
                        <div style="display: none" class="card_exam">
                            <ul class="cardul">
                                [#list testpaperChapter.testpaperItems as testpaperItem] 
	                                [#if testpaperItem.question.questionType = 'material']
	                                <p class="card_cl" onclick="moveto(${testpaperChapter_index},${testpaperItem.id});">材料${testpaperItem_index+1}</p>
	                                [#list testpaperItem.children as itemChildren]
	                               		 <li><span [#if itemChildren.testpaperItemResult.status=="right"]class="right_answer"[#else]class="error_answer"[/#if] id="fastto_item_${itemChildren.id}" onclick="moveto(${testpaperChapter_index},${itemChildren.id});">${itemChildren_index+1}</span></li>
	                                [#if itemChildren_index+1==testpaperItem.children?size ]
			                        <div class="clearh"></div>
			                        [/#if]
                                [/#list] 
                                [/#if] 
                                [#if testpaperItem.question.questionType != 'material']
                             	   <li><span [#if testpaperItem.testpaperItemResult.status=="right"]class="right_answer"[#else]class="error_answer"[/#if]  id="fastto_item_${testpaperItem.id}" onclick="moveto(${testpaperChapter_index},${testpaperItem.id});">${testpaperItem_index+1}</span></li>
                                [/#if] 
                                [/#list]
                            </ul>
                            <div style="height: 0px" class="clearh"></div>
                        </div>
                    </div>
                    [/#list]
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
