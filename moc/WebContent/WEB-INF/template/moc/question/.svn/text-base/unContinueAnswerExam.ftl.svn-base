<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <title>谋题库-职业考试测评利器-错误提示</title>
    <meta name="keywords" content="财会类、计算机类、工程类等考试真题、模拟试题" />
    <meta name="description" content="谋题库-智能职业类考试在线做题系统提供财会类、计算机类、工程类等考试真题、模拟题，智能出题、全真模考，职业考试的备考首选平台" />
    <link rel="stylesheet" href="${base}/resources/moc/css/exam.css" />
    <link rel="stylesheet" href="${base}/resources/moc/css/tab.css" media="screen">
    <script src="${base}/resources/moc/js/jquery.js"></script>
    <script src="${base}/resources/moc/js/jquery.tabs.js"></script>
    <script src="${base}/resources/moc/js/mine.js"></script>
    <script type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
    <script type="text/javascript">
		$(function(){
			//倒计时
            $(".timer").countDown({
                seconds: 3, //初始化时间
                callback: function(times) { //每秒回调
                    $(this).html(times.second);
                },
                callback_doEnd: function() { //计时结束回调
                  	window.close();  
                }
            });
		})
    </script>
</head>

<body>
    [#include "/moc/include/questionHeader.ftl" /]
    <div style="margin:0 atuo;height:300px;text-align:center;margin-top:150px;font-size:14px;">
        <p>您已提交试卷，无法继续作答。</p>
        <p style="color:#999;margin-top:20px;"><div class="timer">3</div>秒后为您返回</p>
    </div>
    <div class="clearh"></div>
    [#include "/moc/include/footer.ftl" /]
</body>

</html>
