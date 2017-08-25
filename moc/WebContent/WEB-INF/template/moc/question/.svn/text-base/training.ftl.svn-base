<!doctype html>
<html>

<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=8" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- InstanceBeginEditable name="doctitle" -->
    <title>谋刻职业教育在线测评与学习平台</title>

    <link rel="stylesheet" href="${base}/resources/moc/css/exam.css" />
    <script src="${base}/resources/moc/js/jquery-1.8.0.min.js"></script>
    <link rel="stylesheet" href="${base}/resources/moc/css/tab.css" media="screen">
    <link rel="stylesheet" href="${base}/resources/moc/css/jquery.bxslider.css">
    <link href="${base}/resources/moc/css/pagination1.css" rel="stylesheet" type="text/css" />
    <script src="${base}/resources/moc/js/jquery.pagination.js" type="text/javascript"></script>
    <script type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
    <script type="text/javascript" src="${base}/resources/moc/js/treeTable.js"></script>
    <script src="${base}/resources/moc/js/jquery.tabs.js"></script>
    <script src="${base}/resources/moc/js/mine.js"></script>
    <script type="text/javascript" src="${base}/resources/moc/js/jquery.bxslider_e88acd1b.js"></script>

    <script type="text/javascript">
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
        })
        <!----> 
        $(function() {
        	treeTable('myOutline');
            $(".dblink1.db5").click(function() {
                $(".jiexilist").toggle(1000);
            });

            $(".dblink1.db5").toggle(

                function() {
                    $(this).css({
                        "background": "url(${base}/resources/moc/images/ico_detail_item.png) right center no-repeat",
                        "background-position": "0px -1871px"
                    })
                },
                function() {
                    $(this).css({
                        "background": "url(${base}/resources/moc/images/ico_detail_item.png) right center no-repeat",
                        "background-position": "0px -1833px"
                    })
                }
            );
        });
        var areaID = "";

        function zt_open() {
            $.ajax({
                type: "POST",
                url: "${base}/question/oldexamCount.jhtml",
                data: {
                    "outlineCategoryID": ${outline.id},//大纲ID
                    "areaID": areaID
                },
                dataType: "json",
                success: function(msg) {
                    $("#pagination").pagination(msg, {
                        ellipse_text: "...", //     省略的页数用什么文字表示
                        num_edge_entries: 0, //两侧显示的首尾分页的条目数
                        num_display_entries: 5, //连续分页主体部分显示的分页条目数
                        items_per_page: 8, //    每页显示的条目数
                        callback: pageselectCallback
                    });

                    //获取数据信息
                    function pageselectCallback(page_id, jq) {
                        page_id = page_id + 1;
                        $.ajax({
                            type: "POST",
                            url: "${base}/question/oldexamList.jhtml",
                            data: {
                                "page": page_id,
                                "outlineCategoryID": ${outline.id},//大纲ID
                                "areaID": areaID
                            },
                            dataType: "text",
                            success: function(msg) {
                                $("#manger").html(msg);
                                $('body').append('<div id="ztmask" onclick="zt_close2();"></div>');
                                $('#ztmask').show();
                                $('#training_zt').css('display', 'block');
                            }
                        });
                    }
                }
            });
        }

        function findArea(value) {
            areaID = value;
            zt_open();
        }

        function zt_close2() {
            $('#ztmask').remove();
            $('#training_zt').css('display', 'none');
            $('#training_zt').find('.tc').remove();
            areaID = "";
        }
    </script>
</head>

<body>
    [#include "/moc/include/questionHeader.ftl" /]
    <div class="trainingbar">
        <span class="trbar">
            <span class="hovver">
               <strong class="trbar_catname">${current.name}</strong><b style="font-size:18px;">▽</b>
            </span>
        <span class="hidebox">
        	[#list rootsIndustry as root]
            <div class="sortlist">
            <h4>${root.name}</h4>
            <ul class="hideul">
			[#list root.children as child]
    		<li><a href="${base}/question/training/${child.id}.html">${child.name}</a></li>
    		[/#list]
            </ul>
            </div>
            [/#list]
            </span>
        [#list outlines as outline]
    	<span class="trbar_zl">&nbsp;&nbsp;<a [#if outline_index==selectedIndex] class="cur_zl" [/#if] href="${base}/question/training/${current.id}/${outline.id}.html">${outline.name}</a>&nbsp;&nbsp;</span> 
    	[/#list]
        </span>
    </div>

    <div class="clearh" style="height:20px"></div>
    <div class="listcont">
        <div class="examtext">
            <div class="trainhead">
                <ul class="etitle">
                    <li class="currtrain"><a href="#" class="t1"><strong>练习与模考</strong></a></li>
                    <li><a style="margin-left:12.5px;" href="${base}/member/question/list.jhtml?industryCategoryID=${current.id}&outlineCategoryID=${outline.id}" class="t2"><strong>能力评估报告</strong></a></li>
                    <li><a style="float:right" href="${base}/member/question/myExercise.jhtml?industryCategoryID=${current.id}&outlineCategoryID=${outline.id}" class="t3"><strong>我的练习</strong></a></li>
                </ul>
            </div>
            <div class="clearh" style="height:20px;"></div>
            <div class="ebody">
                <ul>
                    <li class="eb1">
                        <div class="body_content">
                            <img src="${base}/resources/moc/images/box1.jpg" width="478" height="200"/>
                            <div class="topbox">
                                <img src="${base}/resources/moc/images/bgpepo.png" width="478" height="200"/>
                                <span class="dec">根据你的掌握情况，智能出题，覆盖所有知识点，提升综合能力。</span>
                                <a href="${base}/member/question/exercise.jhtml?outlineCategoryId=${outline.id}&testpaperType=intellexercise" class="trinto" target="_blank">来20道</a>
                            </div>
                        </div>
                    </li>

                    <li class="eb2">
                        <div class="bcont1">
                            <img src="${base}/resources/moc/images/box2.jpg" width="478" height="200"/>
                            <div class="topbox1">
                                <img src="${base}/resources/moc/images/bgpepo.png" width="478" height="200"/>
                                <span class="dec">自选考点练习，弱项集中提高，所有考点逐个击破。</span>
                                <a href="javascript:void(0);" onclick="tanchuang_open();" class="trinto">开始练习</a>

                            </div>
                        </div>
                    </li>
                    <li class="eb3">
                        <div class="bcont2">
                            <img src="${base}/resources/moc/images/box3.jpg" width="478" height="200"/>
                            <div class="topbox2">
                                <img src="${base}/resources/moc/images/bgpepo.png" width="478" height="200"/>
                                <span class="topbox3"><img src="${base}/resources/moc/images/bgpepo.png" width="478" height="200"/></span>
                                <span class="dec">自动生成模考试卷，锻炼答题技巧，提高自身水平。</span>
                                <a href="${base}/member/question/exercise.jhtml?outlineCategoryId=${outline.id}&testpaperType=genrationexam" class="trinto" target="_blank">开始练习</a>
                            </div>
                        </div>
                    </li>
                    <li class="eb4">
                        <div class="bcont3">
                            <img src="${base}/resources/moc/images/box4.jpg" width="478" height="200"/>
                            <div class="topbox3">
                                <img src="${base}/resources/moc/images/bgpepo.png" width="478" height="200"/><span class="topbox4"><img src="${base}/resources/moc/images/bgpepo.png" width="478" height="200"/></span>
                                <span class="dec">提供近年来会计考试真题模考，祝你成功！</span>
                                <a href="javascript:void(0);" onclick="zt_open();" class="trinto">开始练习</a>
                            </div>
                        </div>
                    </li>
                </ul>
                <div class="clearh" style="height:30px;"></div>
            </div>
        </div>
    </div>
    <div id="training_tc">
        <span class="close" onclick="tanchuang_close();">×</span>
        <div class="t_title">
            <h2>请选择知识点</h2>
        </div>
        <div class="training_tc">
	        <table id="myOutline"  width="550" class="t_zsd" style="padding-left:30px;margin-top:10px;">
	            [#list outlineCategorys as outlineCategory]
		            <tr>
		                <td    gradeClass="keypoint-level-${outlineCategory.grade}"
	                        	[#if !outlineCategory.parent??] data_id="${outlineCategory.id}" [/#if] 
								[#if outlineCategory.parent??] data_id="${outlineCategory.id}" data_parent_id="${outlineCategory.parent.id}" [/#if]>
								<span style="margin-left: ${outlineCategory.grade * 20}px;" class="tree_parent">${outlineCategory.name}</span>
							</td>
		                <td width="60" align="right" >
		                    <div>
		                        <a href="${base}/member/question/exercise.jhtml?outlineCategoryId=${outlineCategory.id}&rootOutlineCategoryId=${outline.id}&testpaperType=specialexercis" class="mbm_btn" style="width:60px;" target="_blank">来20道</a>
		                    </div>
		                </td>
		            </tr>
	            [/#list]
	        </table>
	     </div>
    </div>
    <div id="training_zt">
        <span class="close" onclick="zt_close2();">×</span>
        <div class="t_title">
            <h2>请选择真题试卷</h2>
        </div>
        <div class="label_provice">
            <ul class="label_pa">
                <li class="active"><span onclick="findArea()" title="全部">全部</span></li>
                [#list areas as area]
               		 <li><span onclick="findArea(${area.id})" title="${area.name}">${area.name}</span></li>
                [/#list]
            </ul>
        </div>
        <div class="clearh"></div>
        <div id='manger'>
            <div align="center">
                <img src="${base}/resources/moc/images/loading_bar.gif" />
                <br/> 数据正在加载中，请勿关闭浏览器,稍等。。。
            </div>
        </div>
        <div id="pagination" style="clear:none; margin-top:10px"></div>
    </div>
    </div>
    [#include "/moc/include/footer.ftl" /]
</body>

</html>
