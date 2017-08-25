<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>${message("admin.member.list")} - Powered By Sram</title>
		<meta name="author" content="Sram Team" />
		<meta name="copyright" content="Sram" />
		<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
		<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
		<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${base}/resources/admin/fusionCharts/fusioncharts.js"></script>
		<script type="text/javascript" src="${base}/resources/admin/fusionCharts/fusioncharts.charts.js"></script>
	</head>
	<body>
		<form id="listForm" action="dataAnaChartLine.jhtml" method="post">
			<input type="hidden" name="analysisType" value="${analysisType}"/>
		    <input type="hidden" id="beginDate" name="beginDateStr" value="${beginDateStr?string('yyyy-MM-dd')}"/>
		    <input type="hidden" id="endDate" name="endDateStr" value="${endDateStr?string('yyyy-MM-dd')}"/>
		 	<div id="chartContainer" align="center" ></div>
			<script type="text/javascript">
			        var analysisType="${analysisType}";
		            var dateStr="${dateStr}";
		            var countStr="${countStr}";
		            var dateObj=eval('(' + dateStr + ')');
		            var countObj=eval('(' + countStr + ')');
					FusionCharts.ready(function () {
					    var analysisChart = new FusionCharts({
					        type: 'stackedColumn2DLine',
					        renderAt: 'chartContainer',
					        width: '100%',
					        height: '400',
					        dataFormat: 'json',
					        dataSource: {
					            "chart": {
					                [#if analysisType=="register"]
					                	"toolTipSepChar":"<br/>注册人数:",
					                	"caption": "用户实时注册人数",
				                	[#elseif analysisType=="course"]
				                		"toolTipSepChar":"<br/>新增课程数:",
				                		"caption": "新增课程数",
			                		[#elseif analysisType=="lesson"]
			                			"toolTipSepChar":"<br/>新增课时数:",
			                			"caption": "新增课时数",
		                			[#elseif analysisType=="courseLearn"]
		                				"toolTipSepChar":"<br/>课程学习情况:",
		                				"caption": "课程学习情况",
	                				[#elseif analysisType=="finishedLesson"]
	                					"toolTipSepChar":"<br/>课时完成情况:",
	                					"caption": "课时完成情况",
                					[#elseif analysisType=="allUserCount"]
                						"toolTipSepChar":"<br/>用户总数:",
                						"caption": "用户总数",
            						[#elseif analysisType=="allCourseCount"]
            							"toolTipSepChar":"<br/>课程总数:",
            							"caption": "课程总数",
				                	[/#if]
					                [#--"xaxisname": "日期",
					                [#if analysisType=="register"]
					                	"yaxisname": "注册人数",
				                	[#elseif analysisType=="course"]
				                		"yaxisname": "新增课程数",
			                		[#elseif analysisType=="lesson"]
			                			"yaxisname": "新增课时数",
		                			[#elseif analysisType=="courseLearn"]
		                				"yaxisname": "加入学习课程数",
	                				[#elseif analysisType=="finishedLesson"]
	                					"yaxisname": "完成课时数",
	                				[#elseif analysisType=="allUserCount"]
	                					"yaxisname": "用户总数",
                					[#elseif analysisType=="allCourseCount"]
                						"yaxisname": "课程总数",
					                [/#if]--]
					                "showValues": "1",
					                "showBorder": "0",
					                "lineThickness": "0",
					                "paletteColors": "#0075c2",
					                "canvasBgColor": "#ffffff",
					                "bgColor": "#ffffff",
					                "canvasBgAlpha": "80",
					                "canvasBorderThickness":"0",
					                "anchorBorderThickness":"10",
					                "baseFont": "Helvetica Neue,Arial",
					                "captionFontSize": "20",
					                "baseFontSize":"14",
					                "baseFontColor": "#333333",
					               	"showYAxisValues":"1",
					                "divlineAlpha": "66",
					                "divlineColor": "#999999",
					                "divLineIsDashed": "1",
					                "divLineDashLen": "5",
					                "divLineGapLen": "1",
					                "showAlternateHGridColor": "0",
					                "toolTipBorderThickness": "0",
     								"toolTipBgColor": "#ffffff",
								    "toolTipBgAlpha": "50",
								    "toolTipBorderRadius": "2",
 									"toolTipPadding": "5"
					            },
					            "categories": [
					                {
					                    "category":dateObj
					                }
					            ],
					            "dataset": [
					                {
					                    "renderas": "Area",
					                    "data":countObj
					                }
					            ]
					        }
					    }).render();
					});
			</script>
		</form>
	</body>
</html>