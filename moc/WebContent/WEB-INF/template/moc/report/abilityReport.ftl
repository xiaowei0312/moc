<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <title>谋题库-职业考试测评利器-能力评估报告</title>
    <meta name="keywords" content="财会类、计算机类、工程类等考试真题、模拟试题" />
    <meta name="description" content="谋题库-智能职业类考试在线做题系统提供财会类、计算机类、工程类等考试真题、模拟题，智能出题、全真模考，职业考试的备考首选平台" />
    <link rel="stylesheet" href="${base}/resources/moc/css/exam.css" />
    <script src="${base}/resources/moc/js/jquery-1.8.0.min.js"></script>
    <script src="${base}/resources/moc/Highcharts4.0.3/highcharts.js"></script>
    <script type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
    <link rel="stylesheet" href="${base}/resources/moc/css/tab.css" media="screen">
    <script src="${base}/resources/moc/js/jquery.tabs.js"></script>
    <script src="${base}/resources/moc/js/mine.js"></script>
    <script language="javascript" type="text/javascript">
            $(function() {
            $(".bg3").highcharts({
                chart: {
                    renderTo: 'aaa',
                    type: 'spline',
                    marginRight: 10,
                    marginTop: 60,
                    marginBottom: 40
                },
                title: {
                    text: '预测分趋势图',
                    x: -140, //center
                    y: 16
                },
                xAxis: {
                    categories: 0
                },
                yAxis: {
                    title: {
                        text: ' '
                    },
                    plotLines: [{
                        value: 101010,
                        width: 1,
                        color: '#808080'
                    }]
                },
                tooltip: {
                    formatter: function() {
                        return '<b>' + this.series.name + ':</b>' + this.y + '分';
                    }
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'top',
                    x: 100,
                    y: 10,
                    borderWidth: 0
                },
                series: [{
                    name: '考试分数',
                    data: ${chartJson}
                }]
            });
            });
            
            
            //能力图表
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
				  		$(this).find('span').removeClass("tree_children");
						$(this).find('span').addClass("tree_parent");
				  		if($("td[data_parent_id='"+data_id+"']").html() != undefined){
				  			children2(data_id);
				  		}
				});
			}
		}
            
        </script>
</head>

<body>
     [#include "/moc/include/questionHeader.ftl" /]
	<div class="trainingbar">
	    <span class="trbar">
	    <span class="hovver">
	    [@industry_category_list ]
	        [#list rootIndustryCategorys as root]
	        [#list root.children as child]
	            [#if child.id==currentIndustryCategoryID]
	            <strong class="trbar_catname">${child.name}</strong><b style="font-size:18px;">▽</b>
	            [/#if]
	        [/#list]
	        [/#list]
	    [/@industry_category_list]
	    </span>
	    <span class="hidebox">
	    [@industry_category_list ]
	    [#list rootIndustryCategorys as root]
	    <div class="sortlist">
	    <h4>${root.name}</h4>
	    <ul class="hideul">
	        [#list root.children as child]
	        <li><a href="${base}/question/training/${child.id}.html">${child.name}</a></li>
	        [/#list]
	    </ul>
	    </div>
	    [/#list]
	    [/@industry_category_list]
	    </span> [@industry_category_list ] [#list rootIndustryCategorys as root] [#list root.children as child] [#if child.id==currentIndustryCategoryID] [#list child.outlineCategories as outline]
	    <span class="trbar_zl">&nbsp;&nbsp;<a [#if outline.id==currentOutlineCategoryID] class="cur_zl" [/#if] href="${base}/member/question/list.jhtml?industryCategoryID=${currentIndustryCategoryID}&outlineCategoryID=${outline.id}">${outline.name}</a>&nbsp;&nbsp;</span> [/#list] [/#if] [/#list]
	    [/#list] [/@industry_category_list]
	    </span>
	</div>
    <div class="clearh" style="height:20px"></div>
    <div class="listcont">
        <div class="examtext">
            <div class="trainhead">
                <ul class="etitle">
                    <li><a href="${base}/question/training/${currentIndustryCategoryID}/${currentOutlineCategoryID}.html" class="t1"><strong>练习与模考</strong></a></li>
                    <li class="currtrain"><a style="margin-left:12.5px;" href="#" class="t2"><strong>能力评估报告</strong></a></li>
                    <li><a style="float:right" href="${base}/member/question/myExercise.jhtml?industryCategoryID=${currentIndustryCategoryID}&outlineCategoryID=${currentOutlineCategoryID}" class="t3"><strong>我的练习</strong></a></li>
                </ul>
            </div>
            <div class="clearh" style="height:20px;"></div>
            <div class="traincont">
                <p class="paper_t title">能力评估</p>
                <div class="clearh" style="height:20px;"></div>
                <!--<div class="bg1">
        <p class="ansnum1">20</p>
            <h3>答题数量</h3></div>
        <div class="bg2">
            <p class="ansnum">15</p>
            <h3>答题数量</h3>
        </div>-->
                <div class="exam_yc">
                    <div class="bg1">
                        <p class="ansnum1">${myAvgScore*100}<span>分</span></p>
                    </div>
                    <div class="exam_yc_pm">
                        <h3>预测分</h3>
                        <p>全站排名：<span>${scoreRanking}/${allMember}名</span></p>
                        <p>全站平均预测分：<span><strong>${avgCount[1]*100}分</strong></span></p>
                    </div>
                </div>
                <div class="exam_yc_1">
                    <div class="bg2">
                        <p class="ansnum1">${outlineCategorys[0][7]}<span>道</span></p>
                    </div>
                    <div class="exam_yc_pm">
                        <h3>答题总量</h3>
                        <p>全站排名：<span>${ranking}/${allMember}名</span></p>
                        <p>全站平均答题量：<span>${avgCount[0]}道</span></p>
                    </div>
                </div>
                <div class="bg3" width="461" height="300"></div>
                <div class="clearh"></div>
            </div>

            <div class="clearh" style="height:20px;"></div>
            <div class="examlist" style="width:940px">
                <p class="paper_t title">能力图表</p>
                <table width="95%" class="sjkd" border="1">
                    <tr>
                        <th width="35%">考点</th>
                        <th width="22%">答题量</th>
                        <th width="14%">正确率</th>
                        <th width="29%">能力评估</th>
                    </tr>
					
					 [#assign tempParent=null /]
                        [#list outlineCategorys as outlineCategorie]
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
	                            	[#if outlineCategorie[6]/outlineCategorie[7]==0]
	                               		 <img src="${base}/resources/moc/images/evaluate0.png" height="20">
	                            	[#elseif outlineCategorie[6]/outlineCategorie[7]<0.2]
	                               		 <img src="${base}/resources/moc/images/evaluate1.png" height="20">
	                            	[#elseif  outlineCategorie[6]/outlineCategorie[7]<0.4]
	                               		 <img src="${base}/resources/moc/images/evaluate2.png" height="20">
	                            	[#elseif outlineCategorie[6]/outlineCategorie[7]<0.6]
	                               		 <img src="${base}/resources/moc/images/evaluate3.png" height="20">
	                            	[#elseif outlineCategorie[6]/outlineCategorie[7]<0.8]
	                               		 <img src="${base}/resources/moc/images/evaluate4.png" height="20">
	                            	[#elseif outlineCategorie[6]/outlineCategorie[7]<=1]
	                               		 <img src="${base}/resources/moc/images/evaluate5.png" height="20">
	                            	[/#if]
	                            </td>
	                        </tr>
	                        [#assign tempParent = outlineCategorie[0] /]
                        	[/#if]
                        [/#list]
                </table>
            </div>
        </div>
    </div>
    <div class="clearh"></div>
  	[#include "/moc/include/footer.ftl" /]
</body>

</html>

