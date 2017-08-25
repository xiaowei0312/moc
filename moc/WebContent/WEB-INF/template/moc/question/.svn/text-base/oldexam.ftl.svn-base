<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <title>谋题库-职业考试测评利器-真题模考</title>
    <meta name="keywords" content="财会类、计算机类、工程类等考试真题、模拟试题" />
    <meta name="description" content="谋题库-智能职业类考试在线做题系统提供财会类、计算机类、工程类等考试真题、模拟题，智能出题、全真模考，职业考试的备考首选平台" />
    <link rel="stylesheet" href="${base}/resources/moc/css/common.css" />
    <link rel="stylesheet" href="${base}/resources/moc/css/exam.css" />
    <script src="${base}/resources/moc/js/jquery-1.8.0.min.js"></script>
    <script src="${base}/resources/moc/layer/layer.js"></script>
    <!--弹出层-->
    <script src="${base}/resources/moc/js/mine.js"></script>
    <script type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
    <script type="text/javascript">
        var limitedTime = parseInt(${testpaper.limitedTime});
        //分录题的值格式化
        function entryFormat(document){
            var mark =$(document).closest("div").find("select[name='entry_select_mark']").val();
            var code =$(document).closest("div").find("select[name='entry_select_code']").val();
            var value =$(document).closest("div").find("input[name='entry_input_value']").val();
            $(document).closest("div").find("input[type=hidden]").val(mark+","+code+","+value);
            //异步提交结果
            updateResult($(document).closest("div").find("input[type=hidden]"));
            //改变答题卡样式
            var itemNameStr=$(document).closest("div").find("input[type=hidden]").attr("name");
            var itemName = itemNameStr.split("_");
            var chapaterID = itemName[1];
            var itemID = itemName[3];
            var tempCount = $(".unfinish_" + chapaterID).html();
            var flag=true;//该分录题的所有答案为空
            $("input[name="+itemNameStr+"]").each(function(){
				if(!($(this).val()=="借,,"||$(this).val()=="贷,,")){
					flag=false;
				}            	
            });
            if (!flag) {
                if ($("#fastto_item_" + itemID).attr("class") == undefined || $("#fastto_item_" + itemID).attr("class") == "") {
                    $(".unfinish_" + chapaterID).html(parseInt(tempCount) - 1);
                    $("#fastto_item_" + itemID).addClass("over_answer");
                }
            } else {
                $("#fastto_item_" + itemID).removeClass("over_answer");
                if ($("#fastto_item_" + itemID).attr("class") == undefined || $("#fastto_item_" + itemID).attr("class") == "") {
                    $(".unfinish_" + chapaterID).html(parseInt(tempCount) + 1);
                }
            }
        } 
        $(function() {
            //右上角倒计时
            $(".timer").countDown({
                seconds: limitedTime, //初始化时间
                callback: function(times) { //每秒回调
                    $(this).html(times.minute + ":" + times.second);
                    $("input[name=usedTime]").val(limitedTime-times.total);
                },
                callback_doEnd: function() { //计时结束回调
                    layer.alert("该交卷了！", 11, ['提示', 'background:#89D0EF;']);
                },
                callback_timeOut: function(times) { //超时后每秒回调
                    $(this).html("-" + times.minute + ":" + times.second);
                    $("input[name=usedTime]").val(limitedTime+times.total);
                    if (times.total % 300 == 0) {
                        layer.alert("您已超时" + times.total / 60 + "分钟，请掌握好时间，尽快交卷", 11, ['提示', 'background:#89D0EF;']);
                    }
                }
            });
        //分录题的增加按钮事件  
        $(".plus").live("click",function() {
        	[@compress single_line = true]
            var submitName=$(this).closest("div").find("input[type='hidden']").attr("name");
            var elements = 
            '<div>
                <select name="entry_select_mark" onChange="entryFormat(this);">
                    <option value="借">借<\/option>
                    <option value="贷">贷<\/option>
                <\/select>
                <select name="entry_select_code" onChange="entryFormat(this);">
                    <option value="">---请选择---<\/option>
                        [#list accoutCatalogs as category]
                            <option value="${category.code}">
                                [#if category.grade != 0]
                                    [#list 1..category.grade as i]
                                        &nbsp;&nbsp;
                                    [/#list]
                                [/#if]
                                ${category.name}
                            <\/option>
                        [/#list]
                <\/select>
                <input name="entry_input_value" onKeyup="entryFormat(this);" AUTOCOMPLETE="off">
                <input type="hidden" name="'+submitName+'">
                <input type="button" value=" + " class="plus">
                <input type="button" value=" - "  onClick="$(this).closest(\'div\').remove();">
             <\/div>
            ';
       		 [/@compress]
       		 $(this).closest("div").after(elements);
        });

            $('input[type="text"]').keyup(resizeInput).each(resizeInput);
            //自动统计每个章节的条目数
            $('[id^="myTab3_Content"]').each(function(i) {
                var itemCount = 0;
                var clCount = 0;
                $(this).children('[id^="item_"]').each(function(i) {
                    //alert($(this).attr('id')+","+i);
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
            //填空题的修改前的个数
            var checkCount = 0;
            $("input:text").click(function() {
            //排除分录题
            if($(this).attr('name').indexOf("entry")!=-1){
            	return;
            }
                checkCount = 0;
                var itemNameStr = $(this).attr('name');
                var itemName = $(this).attr('name').split("_");
                var chapaterID = itemName[1];
                var itemID = itemName[3];
                var tempCount = $(".unfinish_" + chapaterID).html();
                $("input[name='" + itemNameStr + "']").each(function(i) {
                    if ($(this).val() != null && $(this).val() != "") {
                        checkCount++;
                    } else {
                        checkCount--;
                    }
                });
            });

            $("input:text").change(function() {
	             //排除分录题
	             if($(this).attr('name').indexOf("entry")!=-1){
	            	return;
	             }
                var itemNameStr = $(this).attr('name');
                var itemName = $(this).attr('name').split("_");
                var chapaterID = itemName[1];
                var itemID = itemName[3];
                var tempCount = $(".unfinish_" + chapaterID).html();
                var tcount = 0;
                $("input[name='" + itemNameStr + "']").each(function(i) {
                    if ($(this).val() != null && $(this).val() != "") {
                        tcount++;
                    } else {
                        tcount--;
                    }
                });
                //之前的有值与本题的所填写的数目一致，减1
                if (checkCount == $("input[name='" + itemNameStr + "']").length) {
                    if (tcount != checkCount) {
                        $("#fastto_item_" + itemID).removeClass("over_answer");
                        $(".unfinish_" + chapaterID).html(parseInt(tempCount) + 1);
                    }
                } else if ($("input[name='" + itemNameStr + "']").length == tcount) {
                    $(".unfinish_" + chapaterID).html(parseInt(tempCount) - 1);
                    $("#fastto_item_" + itemID).addClass("over_answer");
                } else if (tcount < 0 && (Math.abs($("input[name='" + itemNameStr + "']").length) == tcount)) {
                    $("#fastto_item_" + itemID).removeClass("over_answer");
                    $(".unfinish_" + chapaterID).html(parseInt(tempCount) + 1);
                }
                checkCount = 0;
                tcount = 0;
                
                //异步提交答案
               updateResult($(this));
            });

            $("textarea.textfocus").change(function() {
                var itemNameStr = $(this).attr('name');
                var itemName = $(this).attr('name').split("_");
                var chapaterID = itemName[1];
                var itemID = itemName[3];
                var tempCount = $(".unfinish_" + chapaterID).html();
                if ($(this).val() != null && $(this).val() != "") {
                    if ($("#fastto_item_" + itemID).attr("class") == undefined || $("#fastto_item_" + itemID).attr("class") == "") {
                        $(".unfinish_" + chapaterID).html(parseInt(tempCount) - 1);
                        $("#fastto_item_" + itemID).addClass("over_answer");
                    }
                } else {
                    $("#fastto_item_" + itemID).removeClass("over_answer");
                    if ($("#fastto_item_" + itemID).attr("class") == undefined || $("#fastto_item_" + itemID).attr("class") == "") {
                        $(".unfinish_" + chapaterID).html(parseInt(tempCount) + 1);
                    }
                }
                //异步提交答案
               updateResult($(this));
            });

            //提交试卷
            $('.submitexam').click(function() {
	            var total=$(".cardul>li").size();
	            var completeNum=$(".cardul>li").find(".over_answer").size();
	            var suffix="";
	            if(total-completeNum>0){
	                suffix="您还有"+(total-completeNum)+"道题目没有做完。";
	            }
                layer.confirm('确定交卷吗？'+suffix, function(){
               		 $("#oldexamForm").submit();
                });
            });
            var checkboxFlag="";
            $("input:checkbox").click(function(){
            	checkboxFlag=$(this).attr("checked");
            });
            
            $(".ansLable").click(function(){
	        		var meta=$(this).children('input');
	        		var type=$(meta).attr('type');
	            	 //单选的快速标示
		           if(type=='radio'){
			            $(meta).attr("checked", "checked");
		                var itemNameStr = $(meta).attr('name');
		                var itemName = itemNameStr.split("_");
		                var chapaterID = itemName[1];
		                var itemID = itemName[3];
		                var tempCount = $(".unfinish_" + chapaterID).html();
		                var flag = false;
		                $("input[name='" + itemNameStr + "']").prop("checked", function(i, val) {
		                    if (val) {
		                        flag = true;
		                    }
		                });
		                if (flag) {
		                    if ($("#fastto_item_" + itemID).attr("class") == undefined || $("#fastto_item_" + itemID).attr("class") == "") {
		                        $(".unfinish_" + chapaterID).html(parseInt(tempCount) - 1);
		                        $("#fastto_item_" + itemID).addClass("over_answer");
		                    }
		                } else {
		                    $("#fastto_item_" + itemID).removeClass("over_answer");
		                    if ($("#fastto_item_" + itemID).attr("class") == undefined || $("#fastto_item_" + itemID).attr("class") == "") {
		                        $(".unfinish_" + chapaterID).html(parseInt(tempCount) + 1);
		                    }
		                }
		               //增加选项选中效果
		               $(meta).parent().parent().find("input").each(function(){
		               		if($(meta).attr("checked")==undefined){
		                   		$("#"+itemNameStr+"_"+$(meta).val()).find("span").removeClass("optionSelected");
		               		}else{
		               			$("#"+itemNameStr+"_"+$(this).val()).find("span").removeClass("optionSelected");
				                $("#"+itemNameStr+"_"+$(meta).val()).find("span").addClass("optionSelected");
		               		}
		               });
		            }
	            
		             //复选的快速标示
		              if(type=='checkbox'){
		                var itemNameStr = $(meta).attr('name');
		                var itemName = itemNameStr.split("_");
		              	if((checkboxFlag=="checked" && $(meta).attr("checked")) || (checkboxFlag!=undefined && $(meta).attr("checked")==undefined)  ){
			            	$(meta).attr("checked", "checked");
	           			}else{
	           				$(meta).removeAttr("checked");
	           			}
			                var chapaterID = itemName[1];
			                var itemID = itemName[3];
			                var tempCount = $(".unfinish_" + chapaterID).html();
			                var flag = false;
			                $("input[name='" + itemNameStr + "']").prop("checked", function(i, val) {
			                    if (val) {
			                        flag = true;
			                    }
			                });
			                if (flag) {
			                    if ($("#fastto_item_" + itemID).attr("class") == undefined || $("#fastto_item_" + itemID).attr("class") == "") {
			                        $(".unfinish_" + chapaterID).html(parseInt(tempCount) - 1);
			                        $("#fastto_item_" + itemID).addClass("over_answer");
			                    }
			                } else {
			                    $("#fastto_item_" + itemID).removeClass("over_answer");
			                    if ($("#fastto_item_" + itemID).attr("class") == undefined || $("#fastto_item_" + itemID).attr("class") == "") {
			                        $(".unfinish_" + chapaterID).html(parseInt(tempCount) + 1);
			                    }
			                }
			               //增加选项选中效果
			               $(meta).parent().parent().find("input").each(function(){
			                    if($(this).attr("checked")==undefined ){
			                        $("#"+itemNameStr+"_"+$(this).val()).find("span").removeClass("optionSelected");
			                    }else{
			                        $("#"+itemNameStr+"_"+$(this).val()).find("span").addClass("optionSelected");
			                    }
			               })
			               checkboxFlag="";
		            }
               //异步提交答案
               updateResult($(meta));
            });
            //选项的单击事件
            $(".option_content").click(function(){
                var $clickOption=$(this);
                var target=$(this).attr("id").substr(0,$(this).attr("id").length-2);
                var optionValue=$(this).children("span").text().substr(0,1);
               if($("input[name="+target+"]").attr("type")=="checkbox"){
                    if($("input[name="+target+"][value="+optionValue+"]").attr('checked')){
                        $("input[name="+target+"][value="+optionValue+"]").attr('checked',false);
                        $clickOption.children("span").removeClass("optionSelected");                        
                    }else{
                        $("input[name="+target+"][value="+optionValue+"]").attr("checked","checked");
                        $clickOption.children("span").addClass("optionSelected");
                    }
                }else{
                	if($("input[name="+target+"][value="+optionValue+"]").attr("checked")){
	                    $("input[name="+target+"][value="+optionValue+"]").attr("checked",false);
	                    $clickOption.parent().find("span").removeClass("optionSelected");
                	}else{
	                    $("input[name="+target+"][value="+optionValue+"]").attr("checked",true);
	                    $clickOption.parent().find("span").removeClass("optionSelected");
	                    $clickOption.children("span").addClass("optionSelected");
                	}
                }
                
                //异步提交
                updateResult($("input[name="+target+"]"));
                
                //checbox
                var itemNameStr = target;
                var itemName = target.split("_");
                var chapaterID = itemName[1];
                var itemID = itemName[3];
                var tempCount = $(".unfinish_" + chapaterID).html();
                var flag = false;
                $("input[name='" + itemNameStr + "']").prop("checked", function(i, val) {
                    if (val) {
                        flag = true;
                    }
                });
                if (flag) {
                    if ($("#fastto_item_" + itemID).attr("class") == undefined || $("#fastto_item_" + itemID).attr("class") == "") {
                        $(".unfinish_" + chapaterID).html(parseInt(tempCount) - 1);
                        $("#fastto_item_" + itemID).addClass("over_answer");
                    }
                } else {
                    $("#fastto_item_" + itemID).removeClass("over_answer");
                    if ($("#fastto_item_" + itemID).attr("class") == undefined || $("#fastto_item_" + itemID).attr("class") == "") {
                        $(".unfinish_" + chapaterID).html(parseInt(tempCount) + 1);
                    }
                }
                
            })
        });

        function resizeInput() {
            var itemName = $(this).attr('name');
            var itemID = itemName.substring(itemName.lastIndexOf('_') + 1);
            if ($(this).val() != null && $(this).val() != "") {
                $("#fastto_item_" + itemID).addClass("over_answer");
            } else {
                $("#fastto_item_" + itemID).removeClass("over_answer");
            }
            var size ="";
            if ($(this).val().length > 8) {
                size = $(this).val().length + 20;
                $(this).attr('size', size);
            }
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
        function nTabs2(num) {
            var beforNum=parseInt(num)-1;
            $("#chapter_"+beforNum).removeClass('currexam').addClass('norma3');
            $("#chapter_"+num).removeClass('norma3').addClass('currexam');
            $("#myTab3_Content" + num).show();
            $("#myTab3_Content" + beforNum).hide();
            
            var thetop = $("#myTab3_Content" + num).offset().top-250;
            $("html:not(:animated),body:not(:animated)").animate({scrollTop: thetop}, 0);
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
        
        //异步提交问题答案
        function updateResult(elements){
            var userAnswer="";
            var itemNameStr = elements.attr('name');
            //判断文本域(简答)
            if(elements.attr("type") == undefined){
            	userAnswer=elements.val();
            }else{
            	$("input[name="+itemNameStr+"]").each(function(){
            		//判断选择题或者分录题
            		if($(this).attr("type") == "text"||$(this).attr("type") == "hidden"){
            			userAnswer+=$(this).val()+"|";
            		}
            		//单选多选判断
	                if($(this).attr("checked")=="checked"){
	                    userAnswer+=$(this).val()+"|";
	                }
            	})
            }
            var elapsedTime=$("input[name=usedTime]").val();
            var testpaperResultId=$("input[name=testpaperResultId]").val();
            var questionArray=itemNameStr.split("_");
            $.ajax({
                type : "POST",
                url : "${base}/question/saveQuestion.jhtml",
                data : {"elapsedTime":elapsedTime,
                        "testpaperResultId":testpaperResultId,
                        "testpaperItemId":questionArray[3],
                        "userAnswer":userAnswer
                       },
                dataType:"text",
                success : function(msg) {
                   // alert(msg);
                }
            });
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
            <div class="left_nemu" style="min-height:600px">
                <!--题目栏-->
                <div class="examhead">
                    <h2 class="ntitle">${testpaper.name}</h2>
                    <div class="titleul" style="border-top:none;">
                        <ul id="myTab3">
                            [#list testpaperChapters as testpaperChapter]
                                 [#if (testpaperChapter_index = 0)]
                                    <li class='currexam' id='chapter_${testpaperChapter_index}' onclick="nTabs(this,${testpaperChapter_index});">
                                    <a href="#">${testpaperChapter.name} <span class="unfinish_${testpaperChapter.id}">${testpaperChapter.testpaperItems?size}</span>/<span class="count_${testpaperChapter_index}">${testpaperChapter.testpaperItems?size}</span></a>
                                    </li>
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
                <form name="oldexamForm" id="oldexamForm" action="saveOldexm.jhtml" method="post">
                    <input type="hidden" name="usedTime">
                    <input type="hidden" name="testpaperType" value="${testpaper.testpaperType}">
                    <input type="hidden" name="outlineCategoryId" value="${outlineCategoryId}">
                    <input type="hidden" name="testpaperResultId" value="${testpaperResultId}">
                    <input type="hidden" name="testpaperId" value="${testpaper.id}">
                     [#list testpaperChapters as testpaperChapter]
                    <div [#if testpaperChapter_index=0] style="display: block;" [/#if][#if testpaperChapter_index>0] style="display: none;" [/#if] id="myTab3_Content${testpaperChapter_index}" class="examlist">
                        <p class="examtit">${testpaperChapter.description}</p>
                        [#list testpaperChapter.testpaperItems as testpaperItem]
                        <div class="itemlist" id="item_${testpaperItem.id}">
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
                                    [#if testpaperItem.question.questionType != 'blanks'  ]
                                        ${testpaperItem.question.stem}
                                        [#if testpaperItem.question.questionType == 'material' || testpaperItem.question.questionType == 'essay']
		                        			<a class="dblink1 [#if questionFavoriteIds?seq_contains(testpaperItem.question.id)] qx [#else] sc[/#if]"  id="${testpaperItem.question.id}">[#if questionFavoriteIds?seq_contains(testpaperItem.question.id)] 取消收藏 [#else] 收藏本题 [/#if]</a>
		                        		[/#if]
                                    [/#if]
                                    [#if testpaperItem.question.questionType == 'blanks'  ]
                                        ${testpaperItem.stemToBlanks}
                                       	<a class="dblink1 [#if questionFavoriteIds?seq_contains(testpaperItem.question.id)] qx [#else] sc[/#if]"  id="${testpaperItem.question.id}">[#if questionFavoriteIds?seq_contains(testpaperItem.question.id)] 取消收藏 [#else] 收藏本题 [/#if]</a>
                                    [/#if]
                                </span>
                            </p>
                            [#if testpaperItem.question.questionType = 'single_choice' || testpaperItem.question.questionType = 'choice' || testpaperItem.question.questionType = 'uncertain_singlechoice' || testpaperItem.question.questionType = 'uncertain_choice' ] 
	                            <div class="options">
		                            <ul>
		                                [#list testpaperItem.question.toMetas as meta]
		                                    <li class="option_content" id="testpaperItem_${testpaperChapter.id}_${testpaperItem.question.id}_${testpaperItem.id}_${meta?substring(0,1)}">
		                                        <span class="option_index">${meta?substring(0,2)}</span>${meta?substring(2,meta?length)}
		                                    </li>
		                                [/#list]
		                            </ul>
	                            </div>
                            [/#if]
                            <!-- 选择题-->
                            [#if testpaperItem.question.questionType = 'single_choice' || testpaperItem.question.questionType = 'choice' || testpaperItem.question.questionType = 'uncertain_singlechoice' || testpaperItem.question.questionType = 'uncertain_choice' ]
                                <span class="ansright spanright no">
                                    [#list testpaperItem.question.toMetasChar as mchar]
                                        <span class="ansLable"><input type="[#if testpaperItem.question.questionType = 'single_choice']radio[#else]checkbox[/#if]"  name="testpaperItem_${testpaperChapter.id}_${testpaperItem.question.id}_${testpaperItem.id}"  value="${mchar}"><label for="">${mchar}</label></span>
                                    [/#list]
                                    <a class="dblink1 [#if questionFavoriteIds?seq_contains(testpaperItem.question.id)] qx [#else] sc[/#if]"  id="${testpaperItem.question.id}">[#if questionFavoriteIds?seq_contains(testpaperItem.question.id)] 取消收藏 [#else] 收藏本题 [/#if]</a>      
                                </span> 
                            [/#if]
                            <!-- 判断题-->
                            [#if testpaperItem.question.questionType = 'determine' ]
                                <span class="ansright spanright no ew">
                                     <span class="ansLable"><input type="radio"  name="testpaperItem_${testpaperChapter.id}_${testpaperItem.question.id}_${testpaperItem.id}" value="true"><label for="">正确</label></span>
                                     <span class="ansLable"><input type="radio"  name="testpaperItem_${testpaperChapter.id}_${testpaperItem.question.id}_${testpaperItem.id}" value="false"><label for="">错误</label></span>
                                     <a class="dblink1 [#if questionFavoriteIds?seq_contains(testpaperItem.question.id)] qx [#else] sc[/#if]"  id="${testpaperItem.question.id}">[#if questionFavoriteIds?seq_contains(testpaperItem.question.id)] 取消收藏 [#else] 收藏本题 [/#if]</a>     
                                </span> 
                            [/#if] 
                            [#if testpaperItem.question.questionType = 'essay' ]
                                <span class="ansright spanright no et">
                                	<textarea class="textfocus" name="testpaperItem_${testpaperChapter.id}_${testpaperItem.question.id}_${testpaperItem.id}"></textarea>
                                </span>
                            [/#if]
                            <!-- 材料题-->
                            [#if testpaperItem.question.questionType = 'material' ]
                                <input type="hidden" name="testpaperItem_${testpaperChapter.id}_${testpaperItem.question.id}_${testpaperItem.id}" value="material">
                                <div class="answer" style="padding-left:0;">
                                    [#list testpaperItem.children as children]
                                    <p class="title" id="item_${children.id}">(${children_index+1})
                                    <span class="qtype"> 
                                    [#if children.question.questionType = 'single_choice' ](单选题)[/#if]
                                    [#if children.question.questionType = 'choice' ](多选题)[/#if]
                                    [#if children.question.questionType = 'uncertain_singlechoice' ](不定项选择题)[/#if]
                                    [#if children.question.questionType = 'uncertain_choice' ](不定项选择题)[/#if]
                                    [#if children.question.questionType = 'determine' ](判断题)[/#if]
                                    [#if children.question.questionType = 'blanks' ](填空题)[/#if]
                                    [#if children.question.questionType = 'material' ](材料题)[/#if]
                                    [#if children.question.questionType = 'essay' ](简答题)[/#if]
                                    [#if children.question.questionType = 'entry' ](分录题)[/#if]
                                    </span>
                                    [#if children.question.questionType != 'blanks'  ]${children.question.stem}[#else]${children.stemToBlanks}[/#if]</p>
                                    
                                    <!--选择1-->
                                    [#if children.question.questionType = 'single_choice' || children.question.questionType = 'choice' || children.question.questionType = 'uncertain_singlechoice' || children.question.questionType = 'uncertain_choice' ]
                                    <div class="options">
                                        <ul>
                                        [#list children.question.toMetas as meta]
                                            <li class="option_content" id="testpaperItem_${testpaperChapter.id}_${children.question.id}_${children.id}_${meta?substring(0,1)}">
                                                <span class="option_index">${meta?substring(0,2)}</span>
                                                ${meta?substring(2,meta?length)}
                                            </li>                                           
                                        [/#list]
                                        </ul>
                                    </div>
                                    <span class="ansright spanright no">
                                            [#list children.question.toMetasChar as mchar]
                                               <span class="ansLable"><input type="[#if children.question.questionType = 'single_choice']radio[#else]checkbox[/#if]"  name="testpaperItem_${testpaperChapter.id}_${children.question.id}_${children.id}" value="${mchar}">
                                               <label for="">${mchar}</label></span>
                                            [/#list]
                                    </span> 
                                     [/#if]
                                    <!-- 判断题-->
                                    [#if children.question.questionType = 'determine' ]
                                    <span class="quename">${children.question.stem}</span>
                                    </p>
                                    <span class="ansright spanright no">
                                            <span class="ansLable"><input type="radio" name="testpaperItem_${testpaperChapter.id}_${children.question.id}_${children.id}" value="true"><label for="">正确</label></span>
                                            <span class="ansLable"><input type="radio" name="testpaperItem_${testpaperChapter.id}_${children.question.id}_${children.id}" value="false"><label for="">错误</label></span>
                                            </span> 
                                    [/#if]
                                    <!--问答-->
                                    [#if children.question.questionType = 'essay'  ]
                                        <span class="ansright spanright no et">
                                            <textarea class="textfocus" name="testpaperItem_${testpaperChapter.id}_${children.question.id}_${children.id}"></textarea>
                                        </span>
                                    [/#if] 
                                        <!--分录题-->
                                        [#if children.question.questionType = 'entry' ]
                                            <span class="ansright spanright no">
                                                <div>
                                                    <select name="entry_select_mark" onChange="entryFormat(this);">
                                                        <option value="借">借</option>
                                                        <option value="贷">贷</option>
                                                    </select>
                                                    <select name="entry_select_code" onChange="entryFormat(this);">
                                                        <option value="">---请选择---</option>
                                                        [#list accoutCatalogs as category]
                                                            <option value="${category.code}">
                                                                [#if category.grade != 0]
                                                                    [#list 1..category.grade as i]
                                                                        &nbsp;&nbsp;
                                                                    [/#list]
                                                                [/#if]
                                                                ${category.name}
                                                            </option>
                                                        [/#list]
                                                    </select>
                                                    <input name="entry_input_value" onKeyup="entryFormat(this);" AUTOCOMPLETE="off" >
                                                    <input type="hidden" name="testpaperItem_${testpaperChapter.id}_${children.question.id}_${children.id}">
                                                    <input type="button" value=" + " class="plus">
                                                    <input type="button" value=" - ">
                                                 </div>
                                            </span> 
                                        [/#if]
                                    [/#list]
                                </div>
                            [/#if]
                            [#if testpaperItem_index >0 ]
                            <div class="clearh"></div>
                            [/#if]
                        </div>
                        [/#list]
                        [#if testpaperChapter_index != testpaperChapters?size-1]
                        <a class="exam_pause" href="javascript:void(0);" onclick="nTabs2(${testpaperChapter_index+1});">进入下一部分</a>
                        [/#if]
                    </div>
                    [/#list]
                </form>
            </div>
            <!--左侧栏-->

            <!--右侧栏-->
            <div class="right_menu">

                <div class="examans small">
                    <div><img src="${base}/resources/moc/images/clock.png" width=70 height=70>
                        <p class="exam_time time1">
                            <div class="timer"></div>
                        </p>
                    </div>
                    <a class=link3 onclick=pause_open(); href="javascript:void(0)">
                        <span class="exam_pause small" id="timeSwitch">暂停</span> </a>
                    <span class="submitexam small">提交</span>
                    <div style="height: 0px" class=clearh></div>
                </div>

                <div class="examans child" id="answcard">
                    <h2>答题卡</h2>
                    <div class="clearh"></div>
                    [#list testpaperChapters as testpaperChapter]
                    <div class="answcard">
                        <span class="cardspan">${testpaperChapter.name}(<strong class="unfinish unfinish_${testpaperChapter.id}">${testpaperChapter.testpaperItems?size}</strong>/<span class="count_${testpaperChapter_index}">0</span>)</span>
                        <div style="display: none" class="card_exam">
                            <ul class="cardul">
                                [#list testpaperChapter.testpaperItems as testpaperItem] 
                                [#if testpaperItem.question.questionType = 'material']
                                <p class="card_cl" onclick="moveto(${testpaperChapter_index},${testpaperItem.id});">材料${testpaperItem_index+1}</p>
                                [#list testpaperItem.children as itemChildren]
                                <li><span id="fastto_item_${itemChildren.id}" onclick="moveto(${testpaperChapter_index},${itemChildren.id});">${itemChildren_index+1}</span></li>
                                [#if itemChildren_index+1==testpaperItem.children?size ]
                                <div class="clearh"></div>
                                [/#if]
                                [/#list] 
                                [/#if] 
                                [#if testpaperItem.question.questionType != 'material']
                                <li><span id="fastto_item_${testpaperItem.id}" onclick="moveto(${testpaperChapter_index},${testpaperItem.id});">${testpaperItem_index+1}</span></li>
                                [/#if] 
                                [/#list]
                            </ul>
                            <div style="height: 0px" class="clearh"></div>
                        </div>
                    </div>
                    [/#list]
                </div>

            </div>
            <!--右侧栏end-->
        </div>
        <div class="clearh"></div>
    </div>
    <div id="stop">
        <span class="close" onclick="pause_close();">×</span>
        <a href="#" class="mbm_btn mpink" onclick="pause_close();">继续作答</a>
    </div>
    [#include "/moc/include/footer.ftl" /]
</body>

</html>
