<!doctype html>
<html><!-- InstanceBegin template="/Templates/dwt.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<!-- InstanceBeginEditable name="doctitle" -->
<title>谋刻职业教育在线测评与学习平台</title>
<link rel="stylesheet" href="${base}/resources/moc/css/member.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/course.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/common.css"/>
<link href="${base}/resources/moc/css/pagination.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/moc/css/tab.css" media="screen" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="${base}/resources/moc/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/mine.js"></script>
<script  type="text/javascript" src="${base}/resources/moc/js/jquery.tabs.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
<script src="${base}/resources/moc/js/jquery.pagination.js" type="text/javascript" ></script>

<script type="text/javascript">
$(function(){

	$('.demo2').Tabs({
		event:'click'
	});
	exercise(${exerciseCount});
	favorites(${favoritesCount});
	wrong(${wrongCount});
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
					url : "exerciseData.jhtml",
					data : {"page":page_id},
					dataType:"text",
					success : function(msg) {
						$("#exercise").html(msg);
					}
				});
			}
}
//收藏
function favorites(favoritesCount){
	$("#favoritesPagination").pagination(favoritesCount, {
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
					url : "favoritesData.jhtml",
					data : {"page":page_id},
					dataType:"text",
					success : function(msg) {
						$("#favorites").html(msg);
					}
				});
			}
}
//错误
function wrong(wrongCount){
	$("#wrongPagination").pagination(wrongCount, {
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
					url : "wrongData.jhtml",
					data : {"page":page_id},
					dataType:"text",
					success : function(msg) {
						$("#wrong").html(msg);
					}
				});
			}
}
</script>

</head>

<body>
	 [#include "/moc/include/memberHeader.ftl" /]
		<div class="membcont">
		      <h3 class="mem-h3">我的题库</h3>
			  <div class="box demo2" style="width:92%;">
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
		                <div class="hide"  >
							<div  id='favorites'>
								<div align="center">
							     	<img src="${base}/resources/moc/images/loading_bar.gif"/><br/>
							     	数据正在加载中，请勿关闭浏览器,稍等。。。
								</div>
							</div>
							<div id="favoritesPagination"  style="clear:none;padding-right:10px;margin-top:10px;"></div>
						</div>		
		                <div class="hide" >
							<div  id='wrong'>
								<div align="center">
							     	<img src="${base}/resources/moc/images/loading_bar.gif"/><br/>
							     	数据正在加载中，请勿关闭浏览器,稍等。。。
								</div>
							</div>
							<div id="wrongPagination"  style="clear:none;padding-right:10px;margin-top:10px;"></div>
						</div>		                					
					</div>
			  </div>
		</div>	
		<div class="clearh"></div>
	</div>
	<div class="clearh"></div>
	 [#include "/moc/include/footer.ftl" /]
	</body>
</html>