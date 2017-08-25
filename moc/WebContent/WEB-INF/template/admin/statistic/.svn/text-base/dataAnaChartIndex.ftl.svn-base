<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>${message("admin.member.add")} - Powered By Sram</title>
		<meta name="author" content="Sram Team" />
		<meta name="copyright" content="Sram" />
		<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
		<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
		<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
		<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
		<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
		<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
		
		
		<script type="text/javascript">
		    $().ready(function() {
	         	var beginDate=$("#beginDate").val();
            	var endDate=$("#endDate").val();
		        $("#mainframeid1").attr("src","${base}/admin/analysisstatistic/dataAnalysisList.jhtml?analysisType=${analysisType}&beginDateStr="+beginDate+"&endDateStr="+endDate);
		        $("#tab>li").each(function(index){
		            $(this).click(function(){
		            	var beginDate=$("#beginDate").val();
		            	var endDate=$("#endDate").val();
			           if(index==0){
			              $("#tabFlag").val("detail");
			              $("#tabs-1").css("display","block");
			              $("#tabs-2").css("display","none");
			              $("#mainframeid1").attr("src","${base}/admin/analysisstatistic/dataAnalysisList.jhtml?analysisType=${analysisType}&beginDateStr="+beginDate+"&endDateStr="+endDate);
			           }else if(index==1){
			              $("#tabFlag").val("trend");
			              $("#tabs-2").css("display","block");
			              $("#tabs-1").css("display","none");
			              $("#mainframeid2").attr("src","${base}/admin/analysisstatistic/dataAnaChartLine.jhtml?analysisType=${analysisType}&beginDateStr="+beginDate+"&endDateStr="+endDate);
			           }
			         });
		         });
		    });
		    
		    function query(){
		    	var beginDate=$("#beginDate").val();
            	var endDate=$("#endDate").val();
		    	var tabFlag=$("#tabFlag").val();
		    	if(tabFlag=='detail'){
		    		 $("#tabs-1").css("display","block");
		             $("#tabs-2").css("display","none");
		             $("#mainframeid1").attr("src","${base}/admin/analysisstatistic/dataAnalysisList.jhtml?analysisType=${analysisType}&beginDateStr="+beginDate+"&endDateStr="+endDate);
		    	}else if(tabFlag=='trend'){
		    		 $("#tabs-2").css("display","block");
		             $("#tabs-1").css("display","none");
		             $("#mainframeid2").attr("src","${base}/admin/analysisstatistic/dataAnaChartLine.jhtml?analysisType=${analysisType}&beginDateStr="+beginDate+"&endDateStr="+endDate);
		    	}
		    }
		</script>
	</head>
	<body>
	    <input type="hidden" id="tabFlag" value="${tabFlag}"/>
		<div class="bar">
			起始时间:
			<input type="text" id="beginDate" name="beginDateStr" value="${beginDateStr?string('yyyy-MM-dd')}" style="width:150px;" size="10"  class="Wdate validate[required] form-textbox" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,readOnly:true})"
			/>
			&nbsp;&nbsp;
			终止日期:
			<input type="text" id="endDate" name="endDateStr" value="${endDateStr?string('yyyy-MM-dd')}" style="width:150px;" size="10"  class="Wdate validate[required] form-textbox" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,readOnly:true})"
				/>
			&nbsp;&nbsp;
			<input type="button" class="button" value="查询" onclick="query();"/>
		</div>
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="明细" />
			</li>
			<li>
				<input type="button" value="趋势" />
			</li>
		</ul>
		<div id="tabs-1">
			<table id="courseLesson" class="input tabContent">
				<iframe id="mainframeid1" name="mainframeid1" marginwidth="0"
	               	 	marginheight="0" frameborder="0"  src=""  width="100%"  style="min-height:800px;" allowtransparency="yes"></iframe>
			</table>
		</div>
		<div id="tabs-2" style="display:none;">
			<table id="coursematerial" class="input tabContent">
			  <iframe id="mainframeid2" name="mainframeid2"marginwidth="0"
	               	 	marginheight="0" frameborder="0"  src=""  width="100%"  style="min-height:800px;" allowtransparency="yes"></iframe>
			</table>
	    </div>
	</body>
</html>