<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>谋题库-职业考试测评利器-我的练习</title>
<meta name="keywords" content="财会类、计算机类、工程类等考试真题、模拟试题" />
<meta name="description"
	content="谋题库-智能职业类考试在线做题系统提供财会类、计算机类、工程类等考试真题、模拟题，智能出题、全真模考，职业考试的备考首选平台" />
<link rel="stylesheet" href="${base}/resources/moc/css/exam.css" />
<link rel="stylesheet" href="${base}/resources/moc/css/common.css"/>
<link href="${base}/resources/moc/css/pagination.css" rel="stylesheet" type="text/css"/>
<script src="${base}/resources/moc/js/jquery-1.8.0.min.js"></script>
<link rel="stylesheet" href="${base}/resources/moc/css/tab.css"	media="screen">
<script src="${base}/resources/moc/js/jquery.tabs.js"></script>
<script src="${base}/resources/moc/js/mine.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/treeTable.js"></script>
<script src="${base}/resources/moc/js/jquery.pagination.js" type="text/javascript" ></script>
<script>
	$(function() {
		
		$('.demo2').Tabs({
			event : 'click'
		});
		treeTable('myFavorite');
		treeTable('myWrong');
		exercise(${exerciseCount});
	});
	
	//练习
	function exercise(exerciseCount){
		$("#exercisePagination").pagination(exerciseCount, {
			ellipse_text : "...",// 	省略的页数用什么文字表示
			num_edge_entries : 0,//两侧显示的首尾分页的条目数
			num_display_entries : 5,//连续分页主体部分显示的分页条目数
			items_per_page:10,// 	每页显示的条目数
			callback:pageselectCallback
		});
	
		//获取数据信息
		function pageselectCallback(page_id, jq) {
			page_id=page_id+1;
			$.ajax( {
				type : "POST",
				url : "showExerciseList.jhtml?outlineCategoryID=${outlineCategoryID}",
				data : {"page":page_id},
				dataType:"text",
				success : function(msg) {
					$("#exercise").html(msg);
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
	            [#if child.id==industryCategoryID]
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
	    </span> [@industry_category_list ] [#list rootIndustryCategorys as root] [#list root.children as child] [#if child.id==industryCategoryID] [#list child.outlineCategories as outline]
	    <span class="trbar_zl">&nbsp;&nbsp;<a [#if outline.id==outlineCategoryID] class="cur_zl" [/#if] href="${base}/member/question/myExercise.jhtml?industryCategoryID=${industryCategoryID}&outlineCategoryID=${outline.id}">${outline.name}</a>&nbsp;&nbsp;</span> [/#list] [/#if] [/#list]
	    [/#list] [/@industry_category_list]
	    </span>
	</div>
	<div class="clearh" style="height: 20px"></div>
	<div class="listcont">
		<div class="examtext">
			<div class="trainhead">
				<ul class="etitle">
					<li><a href="${base}/question/training/${industryCategoryID}.html" class="t1"><strong>练习与模考</strong>
					</a></li>
					<li><a style="margin-left: 12.5px;"
						href="${base}/member/question/list.jhtml?industryCategoryID=${industryCategoryID}&outlineCategoryID=${outlineCategoryID}" class="t2"><strong>能力评估报告</strong>
					</a></li>
					<li class="currtrain"><a style="float: right"
						href="${base}/member/question/myExercise.jhtml?industryCategoryID=${industryCategoryID}&outlineCategoryID=${outlineCategoryID}" class="t3"><strong>我的练习</strong>
					</a></li>
				</ul>
			</div>
			<div class="clearh" style="height: 20px;"></div>
			<div class="traincont">

				<div class="box demo2" style="width: 90%">
					<ul class="tab_menu">
						<li class="current">练习记录</li>
						<li>收藏</li>
						<li>错题本</li>
					</ul>
					<div class="tab_box">
						<div>
							<div  id='exercise'>
								<div align="center">
							     	<img src="${base}/resources/moc/images/loading_bar.gif"/><br/>
							     	数据正在加载中，请勿关闭浏览器,稍等。。。
								</div>
							</div>
							<div id="exercisePagination"  style="clear:none;padding-right:10px;margin-top:10px;"></div>
						</div>

						<div class="hide">
							<table id="myFavorite" class="mbmlist1" width="100%" cellpadding="0"	cellspacing="0">
			                        [#list outlineCategorys as outlineCategory]
			                        	[#if outlineCategory[6]!=0]
					                        <tr >
					                            <td class="first end" gradeClass="keypoint-level-${outlineCategory[3]}"
					                            	[#if !outlineCategory[2]??] data_id="${outlineCategory[0]}" [/#if] 
													[#if outlineCategory[2]??] data_id="${outlineCategory[0]}" data_parent_id="${outlineCategory[2]}" [/#if]  >
					                                <span style="margin-left: ${outlineCategory[3] * 20}px;" class="tree_parent">${outlineCategory[1]}(共${outlineCategory[6]}道收藏)</span>
					                            </td>
					                           <td width="5%">
													<div>
														<a class="mbm_btn" href="${base}/member/question/showCollectionlist.jhtml?outlineCategoryId=${outlineCategory[0]}">查看题目</a>
													</div>
												</td>
					                        </tr>
				                        [/#if]
			                        [/#list]
							</table>
						</div>

						<div class="hide">
							<table id="myWrong" class="mbmlist1" width="100%" cellpadding="0"	cellspacing="0">
									
									 [#list wrongOutlineCategorys as wrongOutlineCategory]
			                        	[#if wrongOutlineCategory[6]!=0]
					                        <tr >
					                            <td  gradeClass="keypoint-level-${wrongOutlineCategory[3]}"
					                            	[#if !wrongOutlineCategory[2]??] data_id="${wrongOutlineCategory[0]}" [/#if] 
													[#if wrongOutlineCategory[2]??] data_id="${wrongOutlineCategory[0]}" data_parent_id="${wrongOutlineCategory[2]}" [/#if]>
					                                <span style="margin-left: ${wrongOutlineCategory[3] * 20}px;" class="tree_parent">${wrongOutlineCategory[1]}(共${wrongOutlineCategory[6]}道错题)</span>
					                            </td>
					                           <td width="5%">
													<div>
														<a class="mbm_btn" href="${base}/member/question/showErrorList.jhtml?outlineCategoryId=${wrongOutlineCategory[0]}">查看题目</a>
													</div>
												</td>
					                        </tr>
				                        [/#if]
                      			  [/#list]
									
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="clearh"></div>
	[#include "/moc/include/footer.ftl" /]
</body>
</html>