<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <title>谋题库-职业考试测评利器-快速智能</title>
    <meta name="keywords" content="财会类、计算机类、工程类等考试真题、模拟试题" />
    <meta name="description" content="谋题库-智能职业类考试在线做题系统提供财会类、计算机类、工程类等考试真题、模拟题，智能出题、全真模考，职业考试的备考首选平台" />
    <link rel="stylesheet" href="${base}/resources/moc/css/exam.css" />
    <script type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
    <script src="${base}/resources/moc/js/jquery-1.8.0.min.js"></script>
    <script src="${base}/resources/moc/js/mine.js"></script>
</head>

<body>
	[#assign orderIndex=1]
	[#include "/moc/include/questionHeader.ftl" /]
    <div class="clearh"></div>
    <div class="listcont">
        <div class="examtext">
            <!--左侧栏-->
            <div style="min-height:600px" class="left_nemu">
                <!--标题栏-->
                <div style="min-height:10px;padding-top:15px;padding-bottom:15px;" class="examhead">
                    <h2 class="ntitle">${testpaper.name}</h2>
                </div>
                <!--题目栏-->
                [#list testpaper.testpaperChapters as testpaperChapter]
                [#list testpaperChapter.testpaperItems as testpaperItem]
                <div class="examlist">
                    <div class="itemlist item_zn">
                        <p>
                            <strong class="quenum">${orderIndex}.</strong>[#assign orderIndex=orderIndex+1]
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
                            <span class="quename">${testpaperItem.question.stem}</span>
                        </p>
                        
                        <!--普通选择题直接出答案和选项-->
                        [#if testpaperItem.question.questionType = 'single_choice' || testpaperItem.question.questionType = 'choice' || testpaperItem.question.questionType = 'uncertain_singlechoice' || testpaperItem.question.questionType = 'uncertain_choice' ] 
                        <div class="answer">
                        [#list testpaperItem.question.toMetas as meta]
                        <p>${meta}</p>
                        [/#list]
                        </div>
                        
                        <span class="ansright spanright no">
                        [#list testpaperItem.question.toMetasChar as mchar]
                        <input type="[#if testpaperItem.question.questionType = 'single_choice']radio[#else]checkbox[/#if]" id="" name="testpaperItem_${testpaperItem.id}">
                        <label for="">${mchar}</label>
                        [/#list]
                        <a style="float:right;cursor:pointer;" class="dblink1 db2">收藏本题</a>
                        </span>
                        [/#if]
                        
	                    [#if testpaperItem.question.questionType = 'material' ]
							 [#list testpaperItem.children as children]
							 <p class="title">(${children_index+1})
							 <span class="qtype">
							 [#if children.question.questionType = 'single_choice' ](单选题)[/#if]
                             [#if children.question.questionType = 'choice' ](多选题)[/#if]
                             [#if children.question.questionType = 'uncertain_singlechoice' ](不定项选择题)[/#if]
                             [#if children.question.questionType = 'uncertain_choice' ](不定项选择题)[/#if]
                             </span>
							 ${children.question.stem}
							 </p>
							 
							 <div class="answer">
                             [#list children.question.toMetas as meta]
                             <p>${meta}</p>
                             [/#list]
                             </div>
							 
							 <span class="ansright spanright no">
                             [#list children.question.toMetasChar as mchar]
                             <input type="[#if testpaperItem.question.questionType = 'single_choice']radio[#else]checkbox[/#if]" id="" name="testpaperItem_${children.id}">
                             <label for="">${mchar}</label>
                             [/#list]
                             [#if children_index+1==testpaperItem.children?size ]
                             <a class="dblink1 db2" style="float:right;cursor:pointer;">收藏本题</a>
                             [/#if]
                             </span>
                             
							 [/#list] 
	                    [/#if]
                    </div>
                </div>
                [/#list]
                [/#list]
                
              </div>
            </div>
            <!--左侧栏-->
            <!--右侧栏-->
            <div class="right_menu">
                <div class="examans">
                    <div>
                        <img width="80" height="80" src="${base}/resources/moc/images/clock.png"">
                        <p class="exam_time">已用时00:15:23</p>
                    </div>
                    <a class="link3" onclick="pause_open();" href="javascript:void(0)">
                        <p class="exam_pause">
                            <span>暂停</span>
                        </p>
                    </a>
                    <p class="save-exercise">
                        <span>下次做</span>
                    </p>
                    <p class="submitexam">
                        <span>提交试卷</span>
                    </p>
                </div>
                <div class="examans child">
                    <h2>答题卡</h2>
                    <ul>
                        <li>
                            <span class="over_answer">1</span>
                        </li>
                        <li>
                            <span>2</span>
                        </li>
                        <li>
                            <span>3</span>
                        </li>
                        <li>
                            <span>4</span>
                        </li>
                        <li>
                            <span>5</span>
                        </li>
                        <li>
                            <span>6</span>
                        </li>
                        <li>
                            <span>7</span>
                        </li>
                        <li>
                            <span>1</span>
                        </li>
                        <li>
                            <span>2</span>
                        </li>
                        <li>
                            <span>3</span>
                        </li>
                        <li>
                            <span>4</span>
                        </li>
                        <li>
                            <span>5</span>
                        </li>
                        <li>
                            <span>6</span>
                        </li>
                        <li>
                            <span>7</span>
                        </li>
                        <li>
                            <span>1</span>
                        </li>
                        <li>
                            <span>2</span>
                        </li>
                        <li>
                            <span>3</span>
                        </li>
                        <li>
                            <span>4</span>
                        </li>
                        <li>
                            <span>5</span>
                        </li>
                        <li>
                            <span>6</span>
                        </li>
                    </ul>
                </div>
            </div>
            <!--右侧栏-->
        </div>
        <div class="clearh"></div>
    </div>
    <div id="stop">
        <span class="close" onclick="pause_close();">×</span>
        <a href="#" class="mbm_btn mpink" onclick="pause_close();">继续作答</a>
    </div>
    <div class="clearh"></div>
	[#include "/moc/include/footer.ftl" /]
    <!--右侧浮动-->
    <div class="rmbar">
        <span class="barico qq" style="position:relative">
            <div class="showqq">
                <p>官方客服QQ:
                    <br> 335049335
                </p>
            </div>
        </span>
        <span class="barico em" style="position:relative">
            <img src="images/num.png" width="75" class="showem">
        </span>
        <span class="barico wb" style="position:relative">
            <img src="images/wb.png" width="75" class="showwb">
        </span>
        <span class="barico top" id="top">置顶</span>
    </div>
</body>

</html>
