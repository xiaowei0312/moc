<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>谋题库-职业考试测评利器-题库解析</title>
    <meta name="keywords" content="财会类、计算机类、工程类等考试真题、模拟试题" />
    <meta name="description" content="谋题库-智能职业类考试在线做题系统提供财会类、计算机类、工程类等考试真题、模拟题，智能出题、全真模考，职业考试的备考首选平台" />
    <link rel="stylesheet" href="${base}/resources/moc/css/exam.css" />
    <script src="${base}/resources/moc/js/jquery-1.8.0.min.js"></script>
    <link rel="stylesheet" href="${base}/resources/moc/css/tab.css" media="screen">
    <script type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
    <script src="${base}/resources/moc/js/jquery.tabs.js"></script>
    <script src="${base}/resources/moc/js/mine.js"></script>
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
                <div style="min-height:10px;padding-top:15px;padding-bottom:15px;" class="examhead">
   					 <h2 style="margin-bottom:0;">【${outlineCategory.name}】错题答案解析</h2>       
				</div>
                <!--题目栏-->
                 <div class="examlist">
                 	[#assign temp_index=0]
                    [#list errorQuestions as question]
	                 	[#if tempId!=question.id]
	                 	[#assign temp_index=temp_index+1 ]
                    <div class="itemlist">
                        <p>
                        <strong class="quenum">${temp_index}.</strong>&nbsp;&nbsp;
                        <span class="qtype"> 
                            [#if question.questionType = 'single_choice' ](单选题)[/#if]
                            [#if question.questionType = 'choice' ](多选题)[/#if]
                            [#if question.questionType = 'uncertain_singlechoice' ](不定项选择题)[/#if]
                            [#if question.questionType = 'uncertain_choice' ](不定项选择题)[/#if]
                            [#if question.questionType = 'determine' ](判断题)[/#if]
                            [#if question.questionType = 'blanks' ](填空题)[/#if]
                            [#if question.questionType = 'material' ](材料题)[/#if]
                            [#if question.questionType = 'essay' ](简答题)[/#if]
                            [#if question.questionType = 'entry' ](分录题)[/#if]
                        </span>
                        <span class="quename">
                        	[#if question.questionType != 'blanks']
                        		${question.stem}
                        		[#if question.questionType = 'material' ]
                        			<a class="dblink1 [#if questionFavoriteIds?seq_contains(question.id)] qx [#else] sc[/#if]"  id="${question.id}">[#if questionFavoriteIds?seq_contains(question.id)] 取消收藏 [#else] 收藏本题 [/#if]</a>
                        		[/#if]
                        	[#else]
                        		${question.stemToBlanks}
                        	[/#if]
                        </span>
                        </p>
                        [#if question.questionType = 'single_choice' || question.questionType = 'choice' || question.questionType = 'uncertain_singlechoice' || question.questionType = 'uncertain_choice' ]
	                        <div class="answer">
	                            [#list question.toMetas as meta]
	                            <p [#if question.toCharAnswer?index_of(meta[0..0])!=-1]class="right_anw" [/#if]>${meta}</p>
	                            [/#list]
	                        </div>
	                    [/#if]    
                        [#if question.questionType != 'material' ]
	                          <p class="ansright spanright no ew">
	                            <span class="answer">
	                            	正确答案是<b class="right_anw">${question.formatAnswer}</b>，答错${question.wrongTestpaperItemResults?size}次，
	                            	[#list question.wrongTestpaperItemResults[0] as testpaperItemResult]
			                            [#if testpaperItemResult.status=='none']您没有作答[#else]您的答案是<b class="right_anw">
			                            [#if question.questionType = 'single_choice' || question.questionType = 'choice' || question.questionType = 'uncertain_singlechoice' || question.questionType = 'uncertain_choice' ]${testpaperItemResult.choiseAnswer}[/#if]
			                           	[#if question.questionType = 'determine']${testpaperItemResult.determineAnswer}[/#if]
			                           	[#if question.questionType ='essay']${testpaperItemResult.essayAnswer}[/#if]
			                           	[#if question.questionType ='blanks']${testpaperItemResult.blankAnswer}[/#if]
			                           	[#if question.questionType ='entry']${testpaperItemResult.entryAnswer}[/#if]
			                           </b>，回答[#if testpaperItemResult.status=="right"]正确[#else]错误[/#if][/#if]
		                           [/#list]
	                           </span>
	                            <span class="dblink">
	            					<a class="dblink1 [#if questionFavoriteIds?seq_contains(question.id)] qx [#else] sc[/#if]"  id="${question.id}">[#if questionFavoriteIds?seq_contains(question.id)] 取消收藏 [#else] 收藏本题 [/#if]</a><a class="dblink1 db5" >展开解析</a>
	          					</span>
	                            <div class="clearh"></div>
	                        </p>
	                        <div class="jiexilist">
	                        	[#if question.origin??]<p><strong class="examlabel">来源</strong> ${question.origin}</p>[/#if]
	                            <p>
	                           		 <strong class="examlabel">考点</strong>${question.outlineCategory.name}
	                             [#--大纲有关联的视频--]
		                            [#if question.outlineCategory.course_chapter_type?? && question.outlineCategory.course_chapter_id??]
		                            	<a href="${base}/member/course/getRelatePath.jhtml?course_chapter_type=${question.outlineCategory.course_chapter_type}&course_chapter_id=${question.outlineCategory.course_chapter_id}"><span>(点击观看考点视频)</span></a>
		                            [/#if]
	                              </p>
	                            <p><strong class="examlabel">解析</strong>${question.analysis}
	                            </p>
	                        </div>
	                        <!--jiexilist-->
	                        <div class="clearh"></div>
                        [#else] 
                        <div class="answer" style="padding-left:0;">
                        	[#assign childTemp_index=0]
	                        [#list question.children as child]
	                        	[#if childTempId!=child.id]
	                        	[#assign childTemp_index=childTemp_index+1 ]
			                        <p class="title" id="item_${child.id}">
			                        <span class="quename">(${childTemp_index})
			                        <span class="qtype"> 
			                            [#if child.questionType = 'single_choice' ](单选题)[/#if]
			                            [#if child.questionType = 'choice' ](多选题)[/#if]
			                            [#if child.questionType = 'uncertain_singlechoice' ](不定项选择题)[/#if]
			                            [#if child.questionType = 'uncertain_choice' ](不定项选择题)[/#if]
			                            [#if child.questionType = 'determine' ](判断题)[/#if]
			                            [#if child.questionType = 'blanks' ](填空题)[/#if]
			                            [#if child.questionType = 'material' ](材料题)[/#if]
			                            [#if child.questionType = 'essay' ](简答题)[/#if]
			                            [#if child.questionType = 'entry' ](分录题)[/#if]
			                        </span>
			                        [#if child.questionType = 'single_choice' || child.questionType = 'choice' || child.questionType = 'uncertain_singlechoice' || child.questionType = 'uncertain_choice' || child.questionType=='entry' ]${child.stem}[/#if][#if child.questionType = 'blanks'  ]${child.stemToBlanks}[/#if]</span>
			                        </p>
			                        [#if child.questionType = 'single_choice' || child.questionType = 'choice' || child.questionType = 'uncertain_singlechoice' || child.questionType = 'uncertain_choice' ]
				                        <div class="answer">
				                            [#list child.toMetas as meta]
				                            <p [#if child.toCharAnswer?index_of(meta[0..0])!=-1]class="right_anw" [/#if]>${meta}</p>
				                            [/#list]
				                        </div>
			                        [/#if]
			                        <p class="ansright spanright no ew">
			                            <span class="answer">正确答案是<b class="right_anw">
			                            [#if child.questionType =='entry']
			                            	[#list child.entryAnswer as entryAnswer]
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
			                         		${child.formatAnswer}
			                            [/#if]
			                            </b>,答错${child.wrongTestpaperItemResults?size}次，
			                           [#list child.wrongTestpaperItemResults[0] as testpaperItemResult]
				                            [#if testpaperItemResult.status=='none']您没有作答[#else]您的答案是<b class="right_anw">
				                            [#if child.questionType = 'single_choice' || child.questionType = 'choice' || child.questionType = 'uncertain_singlechoice' || child.questionType = 'uncertain_choice' ]${testpaperItemResult.choiseAnswer}[/#if]
				                           	[#if child.questionType = 'determine']${testpaperItemResult.determineAnswer}[/#if]
				                           	[#if child.questionType=='essay']${testpaperItemResult.essayAnswer}[/#if]
				                           	[#if child.questionType=='blanks']${testpaperItemResult.blankAnswer}[/#if]
				                           	[#if child.questionType=='entry']
				                           		[#if testpaperItemResult.entryAnswerArrList??]
			                           			[#list testpaperItemResult.entryAnswerArrList as entryAnswerArr]
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
				                           </b>，回答[#if testpaperItemResult.status=="right"]正确[#else]错误[/#if][/#if]
			                            [/#list]
			                            </span>
			                            <span class="dblink">
							           	 <a class="dblink1 db5" >展开解析</a>
							            </span>
			                            <div class="clearh"></div>
			                        </p>
			                        <div class="jiexilist">
			                            <p><strong class="examlabel">考点</strong> ${child.outlineCategory.name}
			                            	[#--大纲有关联的视频--]
				                            [#if child.outlineCategory.course_chapter_type?? && child.outlineCategory.course_chapter_id??]
				                            	<a href="${base}/member/course/getRelatePath.jhtml?course_chapter_type=${child.outlineCategory.course_chapter_type}&course_chapter_id=${child.outlineCategory.course_chapter_id}"><span>(点击观看考点视频)</span></a>
				                            [/#if]
			                            </p>
			                            <p><strong class="examlabel">解析</strong>${child.analysis}
			                            </p>
			                        </div>
			                        <!--jiexilist-->
			                        <div class="clearh"></div>
			                        [/#if]
                    			[#assign childTempId=child.id ]
	                      	  [/#list] 
	                        </div>
                        [/#if]
                    </div>
                    [/#if]
                    <!--itemlist-->
                    [#assign tempId=question.id ]
                    [/#list]
            </div>
            <!--left_nemu-->
            <!--左侧栏-->

            <!--右侧栏-->
            <div class="right_menu">
                <div class="examans">
                	<img width="100" height="100" src="${base}/resources/moc/images/book.png">
                    <p class="exam_pause"><span>查看题目</span></p>
                    <p class="save-exercise findAllJiexi"  ><span><a href="javascript:void(0)" class="baogaolink" >展开全部解析</a></span></p>
                </div>

            </div>
            <!--右侧栏-->
        </div>
        <div class="heigt"></div>
    </div>
    
    <div class="clearh"></div>
    </div>
    [#include "/moc/include/footer.ftl" /]
</body>

</html>