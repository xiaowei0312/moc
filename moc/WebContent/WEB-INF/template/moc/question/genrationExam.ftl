<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <title>谋题库-职业考试测评利器-组卷模考</title>
    <meta name="keywords" content="财会类、计算机类、工程类等考试真题、模拟试题" />
    <meta name="description" content="谋题库-智能职业类考试在线做题系统提供财会类、计算机类、工程类等考试真题、模拟题，智能出题、全真模考，职业考试的备考首选平台" />
    <link rel="stylesheet" href="${base}/resources/moc/css/exam.css" />
    <script src="${base}/resources/moc/js/jquery-1.8.0.min.js"></script>
    <script src="${base}/resources/moc/js/mine.js"></script>
    <script type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
    <script type="text/javascript">
    $(function() {
        $('input[type="text"]').keyup(resizeInput).each(resizeInput);
        //复选的快速标示
        $('input:checkbox').click(function() {
            var itemName = $(this).attr('name');
            var itemID = itemName.substring(itemName.lastIndexOf('_') + 1);
            var flag = false;
            $("input[name='" + itemName + "']").prop("checked", function(i, val) {
                if (val) {
                    flag = true;
                }
            });
            if (flag) {
                $("#fastto_item_" + itemID).addClass("over_answer");
            } else {
                $("#fastto_item_" + itemID).removeClass("over_answer");
            }
        });
        //单选的快速标示
        $('input:radio').click(function() {
            var itemName = $(this).attr('name');
            var itemID = itemName.substring(itemName.lastIndexOf('_') + 1);
            var flag = false;
            $("input[name='" + itemName + "']").prop("checked", function(i, val) {
                if (val) {
                    flag = true;
                }
            });
            if (flag) {
                $("#fastto_item_" + itemID).addClass("over_answer");
            } else {
                $("#fastto_item_" + itemID).removeClass("over_answer");
            }
        });
        //文本框的快速标示
        $("textarea.textfocus").keyup(function() {
            var itemName = $(this).attr('name');
            var itemID = itemName.substring(itemName.lastIndexOf('_') + 1);
            if ($(this).val() != null && $(this).val() != "") {
                $("#fastto_item_" + itemID).addClass("over_answer");
            } else {
                $("#fastto_item_" + itemID).removeClass("over_answer");
            }
        });
        //提交试卷
        $('.submitexam').click(function() {
            $.ajax({
                url: "saveOldexm.jhtml",
                type: "POST",
                data: $("#oldexamForm").serialize(),
                dataType: "json",
                cache: false,
                success: function(message) {
                    $.message(message);
                    //setTimeout(function(){location.reload(true);}, 1000);
                }
            });
        });
    });

    function resizeInput() {
        var itemName = $(this).attr('name');
        var itemID = itemName.substring(itemName.lastIndexOf('_') + 1);
        if ($(this).val() != null && $(this).val() != "") {
            $("#fastto_item_" + itemID).addClass("over_answer");
        } else {
            $("#fastto_item_" + itemID).removeClass("over_answer");
        }
        var size = "";
        if ($(this).val().length > 8) {
            size = $(this).val().length + 20;
        }
        $(this).attr('size', size);
    }

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
    function moveto(chapterIndex, chapterId, itemId) {
        var thetop = $("#item_" + itemId).offset().top;
        $("li").removeClass("currexam");
        $("#chapter_" + chapterId).addClass("currexam");
        $(".examlist").hide();
        $("#myTab3_Content" + chapterIndex).show();
        $("html:not(:animated),body:not(:animated)").animate({
            scrollTop: thetop
        }, 1000);
    }
    </script>
    <script>
    var w = $(window).width();
    var lw = (w - 980) / 2 - 10;
    $(window).scroll(function() {
        $(".right_menu").css({
            "position": "fixed",
            "top": "90px",
            "right": lw
        });
    });
    </script>
</head>

<body>
    [#include "/moc/include/questionHeader.ftl" /]
    <div class="clearh"></div>
    <div class="listcont">
        <div class="examtext">
            <!--左侧栏-->
            <div class="left_nemu" style="min-height:600px">
                <!--题目栏TAB-->
                <div class="examhead">
                    <h2 class="ntitle">${testpaper.name}</h2>
                    <div class="titleul" style="border-top:none;">
                        <ul id="myTab3">
                            [#list testpaper.testpaperChapters as testpaperChapter] [#if (testpaperChapter_index = 0)]
                            <li class='currexam' id='chapter_${testpaperChapter.id}' onclick="nTabs(this,${testpaperChapter_index});">
                                <a href="#">${testpaperChapter.name}
                                    <span class="unfinish_${testpaperChapter.id}">1</span>/${testpaperChapter.testpaperItems?size}</a>
                            </li>
                            [/#if] [#if (testpaperChapter_index > 0) ]
                            <li class='norma3' id='chapter_${testpaperChapter.id}' onclick="nTabs(this,${testpaperChapter_index});">
                                <a href="#">${testpaperChapter.name}
                                    <span class="unfinish_${testpaperChapter.id}">1</span>/${testpaperChapter.testpaperItems?size}</a>
                            </li>
                            [/#if] [/#list]
                        </ul>
                        <div class="clearh"></div>
                    </div>
                </div>
                <!--题目栏TAB结束-->
                <!--章节内容开始-->
                [#list testpaper.testpaperChapters as testpaperChapter]
                <div [#if testpaperChapter_index=0] style="display: block;" [/#if][#if testpaperChapter_index>0] style="display: none;" [/#if] id="myTab3_Content${testpaperChapter_index}" class="examlist">
                    <p class="examtit">${testpaperChapter.description}</p>
                    <!--条目内容开始-->
                    [#list testpaperChapter.testpaperItems as testpaperItem]
                    <div class="itemlist" id="item_${testpaperItem.id}">
                        <p>
                            <strong class="quenum">${testpaperItem_index+1}.</strong>&nbsp;&nbsp;
                            <!--题号-->
                            <span class="quename">[#if testpaperItem.question.questionType != 'blanks' ]${testpaperItem.question.stem}[/#if][#if testpaperItem.question.questionType = 'blanks' ]${testpaperItem.stemToBlanks}[/#if]</span>
                            <!--题干-->
                        </p>
                        <!--选择题选项-->
                        [#if testpaperItem.question.questionType = 'single_choice' || testpaperItem.question.questionType = 'choice' || testpaperItem.question.questionType = 'uncertain_singlechoice' || testpaperItem.question.questionType = 'uncertain_choice' ]
                        <div class="answer">
                            [#list testpaperItem.question.toMetas as meta]
                            <p>${meta}</p>
                            [/#list]
                        </div>
                        [/#if]
                        <!-- 选择题-->
                        [#if testpaperItem.question.questionType = 'single_choice' || testpaperItem.question.questionType = 'choice' || testpaperItem.question.questionType = 'uncertain_singlechoice' || testpaperItem.question.questionType = 'uncertain_choice' ]
                        <span class="ansright spanright no">
                            [#list testpaperItem.question.toMetasChar as mchar]
                            <input type="[#if testpaperItem.question.questionType = 'single_choice']radio[#else]checkbox[/#if]" name="testpaperItem_${testpaperItem.id}" value="${mchar}">
                            <label for="">${mchar}</label>
                            [/#list]
                            <a class="dblink1 db2" style="float:right;cursor:pointer;">收藏本题</a>
                        </span>
                        [/#if]
                        <!-- 判断题-->
                        [#if testpaperItem.question.questionType = 'determine']
                        <span class="ansright spanright no ew">
                            <input type="radio" name="testpaperItem_${testpaperItem.id}" value="true">
                            <label for="">正确</label>
                            <input type="radio" name="testpaperItem_${testpaperItem.id}" value="false">
                            <label for="">错误</label>
                            <a class="dblink1 db2" style="float:right;cursor:pointer;">收藏本题</a>
                        </span>
                        [/#if]
                        <!--填空-->
                        [#if testpaperItem.question.questionType = 'blank' ]
                        <span class="ansright spanright no er">
                            <input type="text" value="填空(1)答案，请填在这里" name="" class="vtk lfloat">
                            <br/>
                            <input type="text" value="填空(2)答案，请填在这里" name="" class="vtk lfloat">
                            <br/>
                            <a style="float:right;cursor:pointer;" class="dblink1 db2">收藏本题</a>
                        </span>
                        [/#if]
                        <!--简答-->
                        [#if testpaperItem.question.questionType = 'essay' ]
                        <span class="ansright spanright no et">
                            <textarea class="textfocus"></textarea>
                            <a style="float:right;cursor:pointer;" class="dblink1 db2">收藏本题</a>
                        </span>
                        [/#if]
                        <!-- 材料题-->
                        [#if testpaperItem.question.questionType = 'material' ]
                        <div class="answer" id="item_${testpaperItem.id}" style="padding-left:0;">
                            [#list testpaperItem.children as children]
                            <!--选择1-->
                            [#if children.question.questionType = 'single_choice' || children.question.questionType = 'choice' || children.question.questionType = 'uncertain_singlechoice' || children.question.questionType = 'uncertain_choice' ]
                            <p class="title">(${children_index+1})${children.question.stem}</p>
                            <div class="answer">
                                [#list children.question.toMetas as meta]
                                <p>${meta}</p>
                                [/#list]
                            </div>
                            <span class="ansright spanright no">
                                [#list children.question.toMetasChar as mchar]
                                <input type="[#if children.question.questionType = 'single_choice']radio[#else]checkbox[/#if]" name="testpaperItem_${children.id}" value="${mchar}">
                                <label for="">${mchar}</label>
                                [/#list]
                            </span>
                            [/#if]
                            <!-- 判断题-->
                            [#if children.question.questionType = 'determine' ]
                            <p class="title">(${children_index+1})
                                <span class="quename">${children.question.stem}</span>
                            </p>
                            <span class="ansright spanright no">
                                <input type="radio" name="testpaperItem_${testpaperItem.id}" value="true">
                                <label for="">正确</label>
                                <input type="radio" name="testpaperItem_${testpaperItem.id}" value="false">
                                <label for="">错误</label>
                            </span>
                            [/#if]
                            <!--填空-->
                            [#if children.question.questionType = 'blanks' ]
                            <p class="title">(${children_index+1})
                                <span class="quename">${children.stemToBlanks}</p>
                            <span class="ansright spanright no ew">
                                <input type="text" value="填空(1)答案，请填在这里" name="" class="vtk lfloat">
                                <br/>
                                <input type="text" value="填空(2)答案，请填在这里" name="" class="vtk lfloat">
                                <br/>
                            </span>
                            [/#if]
                            <!--问答-->
                            [#if children.question.questionType = 'essay' ]
                            <p class="title">
                                <span>(${children_index+1})${children.question.stem}</span>
                            </p>
                            <span class="ansright spanright no et">
                                <textarea class="textfocus" name="testpaperItem_${testpaperItem.id}"></textarea>
                            </span>
                            [/#if]
                            <!--[#if children_index+1==testpaperItem.children?size ]
                             <a class="dblink1 db2" style="float:right;cursor:pointer;">收藏本题</a>
                        [/#if]-->
                            [/#list]
                        </div>
                        [/#if]
                        <div class="clearh"></div>
                    </div>
                    [/#list]
                    <!--条目内容结束-->
                </div>
                [/#list]
                <!--TAB内容结束-->
                <!--左侧栏end-->
            </div>
        </div>
        <!--右侧栏-->
        <div class="right_menu">
            <div class="examans">
                <div>
                    <img width="80" height="80" src="${base}/resources/moc/images/clock.png" ">
                        <p class="exam_time ">已用时00:15:23</p>
                    </div>
                    <a class="link3 " onclick="pause_open(); " href="javascript:void(0) ">
                        <p class="exam_pause ">
                            <span>暂停</span>
                        </p>
                    </a>
                    <p class="save-exercise ">
                        <span>下次做</span>
                    </p>
                    <p class="submitexam ">
                        <span>提交试卷</span>
                    </p>
                </div>
                <div class="examans child ">
                    <h2>答题卡</h2>
                    <ul>
                        <li>
                            <span class="over_answer ">1</span>
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
        <div class="clearh "></div>
    </div>
    <div id="stop ">
        <span class="close " onclick="pause_close(); ">×</span>
        <a href="# " class="mbm_btn mpink " onclick="pause_close(); ">继续作答</a>
    </div>
    <div class="clearh "></div>
  [#include "/moc/include/footer.ftl " /]
    <!--右侧浮动-->
    <div class="rmbar ">
        <span class="barico qq " style="position:relative ">
            <div class="showqq ">
                <p>官方客服QQ:
                    <br> 335049335
                </p>
            </div>
        </span>
        <span class="barico em " style="position:relative ">
            <img src="${base}/resources/moc/images/num.png " width="75 " class="showem ">
        </span>
        <span class="barico wb " style="position:relative ">
            <img src="${base}/resources/moc/images/wb.png " width="75 " class="showwb ">
        </span>
        <span class="barico top " id="top ">置顶</span>
    </div>
</body>

</html>
