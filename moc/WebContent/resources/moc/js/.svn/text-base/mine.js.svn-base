// JavaScript Document

/*!
* exam
*/
$(function() {

    /*题库导航*/
	$(".hovver").mouseover(function() {
		$(".hidebox").slideDown();
	});
	$(".hidebox").mouseover(function() {
		$(".hidebox").show();
	});
	$(".hidebox").mouseleave(function() {
		$(".hidebox").slideUp();
	});

	/*题库四大模块滑动效果*/
	$(".body_content").hover(function() {
		$(this).find(".topbox").stop().animate({
			left: '-80'
		});
	},
	function() {
		$(this).find(".topbox").stop().animate({
			left: '-468'
		});
	});

	$(".bcont1").hover(function() {
		$(this).find(".topbox1").stop().animate({
			left: '-80'
		});
	},
	function() {
		$(this).find(".topbox1").stop().animate({
			left: '-468'
		});
	});

	$(".bcont2").hover(function() {
		$(this).find(".topbox2").stop().animate({
			left: '-80'
		});
	},
	function() {
		$(this).find(".topbox2").stop().animate({
			left: '-468'
		});
	});

	$(".bcont3").hover(function() {
		$(this).find(".topbox3").stop().animate({
			left: '-80'
		});
	},
	function() {
		$(this).find(".topbox3").stop().animate({
			left: '-468'
		});
	});

	/*解析展开*/
	$(".dblink1.db5").click(function(){
		var text=$(this).text();
		if(text=="收起解析"){
			$(this).text("展开解析");
	        $(this).parent(".dblink").parent(".ansright").nextAll(".jiexilist :first").hide(1000);
	        $(this).removeClass("db6");
		}else{
			$(this).text("收起解析");
	        $(this).parent(".dblink").parent(".ansright").nextAll(".jiexilist :first").show(1000);
	        $(this).addClass("db6");
		}
	});
	
    
    //展开全部
    $(".findAllJiexi").toggle(
    		function() {
    			$(this).children().find("a").text("收起全部解析");
    			$(".dblink1.db5").each(function(){
    				$(this).text("收起解析");
    				$(this).parent(".dblink").parent(".ansright").siblings("div").show(1000);
    				$(this).addClass("db6");
    			});
    	    },
    	    function() {
    	    	$(this).children().find("a").text("展开全部解析");
    	    	$(".dblink1.db5").each(function(){
	    	    	$(this).text("展开解析");
	    	        $(this).parent(".dblink").parent(".ansright").siblings(".jiexilist").hide(1000);
	    	        $(this).removeClass("db6");
    	    	});
    	    }
    );
    /*解析展开*/
	/*题库分类*/
	$(".cate_content li").hover(function() {
		$(this).children(".shadow").animate({
			top: 0
		},
		200);
	},
	function() {
		$(this).children(".shadow").animate({
			top: -90
		},
		200);
	});

	/*输入框*/	
	$(".textfocus").focus(function() {
        $(this).css("border", "2px solid #3eb0e0");
    });
    $(".textfocus").blur(function() {
        $(this).css("border", "1px solid #999");
    });
    $(".ansright .vtk.lfloat").focus(function() {
        $(this).css("border", "2px solid #3eb0e0");
    });
    $(".ansright .vtk.lfloat").blur(function() {
        $(this).css("border", "1px solid #999");
    });

	/*试卷收藏效果*/
    $(".dblink1").click(function() {
    	var  questionId =$(this).attr("id");
    	var strClass=$(this).attr("class");
    		if(strClass.indexOf("sc")!=-1){
    			$.ajax({
    				url: "addOrRemoveFavorite.jhtml?status=add&questionId="+questionId,
    				dataType:"json",
    				success:function(data){
    					if(data.msg=='Y'){
    						$("#"+questionId).removeClass("sc");
    						$("#"+questionId).addClass("qx");
    						$("#"+questionId).text("取消收藏");
    					}
    				}
    			});
    		}
    		if(strClass.indexOf("qx")!=-1){
		        $.ajax({
		            url: "addOrRemoveFavorite.jhtml?status=remove&questionId="+questionId,
		            dataType:"json",
		            success:function(data){
		            	if(data.msg=='Y'){   
		            		$("#"+questionId).removeClass("qx");
		            		$("#"+questionId).addClass("sc");
		            		$("#"+questionId).text("收藏本题");
		            	}
		            }
		         });
    		}
    });

	/*试卷右侧栏*/
    var w = $(window).width();
    var lw = (w - 980) / 2 - 10;
    $(".right_menu").css({
        "position": "fixed",
        "top": "100px",
        "right": lw + 10
    });

	/*真题地区选择*/
	var p = 0;
    $(".sort").click(function() {
        p++;
        if (p % 2 != 0) {
            $(".sortext").css("display", "block");
            $(".sortext p").click(function() {
                var pt = $(this).text();
                $(".sort").text(pt);
                $(".sortext").css("display", "none");
                p = 0;
            });
        } else {
            $(".sortext").css("display", "none");
        }
    });	
	
	/*答题卡收缩*/
	$(".cardspan").click(function(){
		var $ul= $(this).next();
		if($ul.is(':visible')){
			$ul.slideUp();
		}else{
			$(".card_exam").slideUp();
			$ul.slideDown();
		}
	});
});
	/*题库做题暂停*/
	function pause_open() {
		$('body').append('<div id="mask" onclick="pause_close();"></div>');
		$('#mask').show();
		$('#stop').css('display', 'block');
		$(".timer").attr("data-time-pause",1);
	}
	function pause_close() {
		$('#mask').remove();
		$('#stop').css('display', 'none');
		$(".timer").attr("data-time-pause",0);
	}
	
	/*专项弹窗*/
	function tanchuang_open() {
		$('body').append('<div id="mask" onclick="tanchuang_close();"></div>');
		$('#mask').show();
		$('#training_tc').css('display', 'block');
	}
	function tanchuang_close() {
		$('#mask').remove();
		$('#training_tc').css('display', 'none');
	}


/*!
* course
*/
$(function() {

	/*取消收藏*/
	$(".courseli.mysc").hover(function() {
		$(this).children('.mask').toggle();
	});

	/*课程目录折叠*/
	$(".mulu_title").toggle(function() {
		$(this).siblings("div").show();
		$(this).children(".mulu_zd").text("-");
	},
	function() {
		$(this).siblings("div").hide();
		$(this).children(".mulu_zd").text("+");
	});	
	/*课程评价*/
	window.onload = function() {
		var oStar = document.getElementById("star");
		if (oStar != null) {
			var aLi = oStar.getElementsByTagName("li");
			var oUl = oStar.getElementsByTagName("ul")[0];
			var oSpan = oStar.getElementsByTagName("span")[1];
			var oP = oStar.getElementsByTagName("p")[0];
			var i = iScore = iStar = 0;
			var aMsg = ["很差|完全不懂在讲什么", "较差|不喜欢", "还行|勉强可以听", "推荐|不错，内容比较受用", "力荐|非常棒，强力推荐"]

			for (i = 1; i <= aLi.length; i++) {
				aLi[i - 1].index = i;
				//鼠标移过显示分数
				aLi[i - 1].onmouseover = function() {
					fnPoint(this.index);
					//浮动层显示
					oP.style.display = "block";
					//计算浮动层位置
					oP.style.left = oUl.offsetLeft + this.index * this.offsetWidth - 104 + "px";
					//匹配浮动层文字内容
					oP.innerHTML = "<em><b>" + this.index + "</b> 分 " + aMsg[this.index - 1].match(/(.+)\|/)[1] + "</em>" + aMsg[this.index - 1].match(/\|(.+)/)[1]

				};

				//鼠标离开后恢复上次评分
				aLi[i - 1].onmouseout = function() {
					fnPoint();
					//关闭浮动层
					oP.style.display = "none"
				};

				//点击后进行评分处理
				aLi[i - 1].onclick = function() {
					iStar = this.index;
					oP.style.display = "none";
					oSpan.innerHTML = "<strong>" + (this.index) + " 分</strong> (" + aMsg[this.index - 1].match(/\|(.+)/)[1] + ")"
				}
			}

			//评分处理
			function fnPoint(iArg) {
				//分数赋值
				iScore = iArg || iStar;
				for (i = 0; i < aLi.length; i++) aLi[i].className = i < iScore ? "on": "";
			}
		}

	};
	/*底部信息页面*/
	$(".pageul li a").hover(
		function(){
			$(this).children("b").css({"background-position":"0 -2559px"});
		},function(){
			$(this).children("b").css("background-position","0 -1115px");
		}
	);
});


/*!
* ask
*/
$(function() {

	/*问答*/
	$(".quform .bf2").focus(function() {
        $(this).css("color", "#333");
    });
    $(".quform .bf2").blur(function() {
        $(this).css("color", "#666");
    });
    $(".askform input").focus(function() {
        $(this).css("color", "#333");
    });
    $(".askform textarea").focus(function() {
        $(this).css("color", "#333");
    });
    $(".askform textarea").blur(function() {
        if ($(this).val() != "") {
            $(this).css("color", "#666");
        } else {
        	var questionFlag=$(this).prev(".questionFlag").val();
        	if(questionFlag=="answer"){
                $(this).val("回复问题");
        	}else{
        		$(this).val("提问问题");
        	}
            $(this).css("color", "#666");
        }
    });
    $(".askform input").blur(function() {
        if ($(this).val() != "") {
            $(this).css("color", "#ccc");
        } else {
            $(this).val("请输入标题");
            $(this).css("color", "#666");
        }
    });
});
   

/*!
* floatbox
*/
$(function() {
    /*右侧客服飘窗*/
	$(".label_pa li").click(function() {
		$(this).siblings("li").find("span").css("background-color", "#fff").css("color", "#666");
		$(this).find("span").css("background", "#fb5e55").css("color", "#fff");
	});
	$(".em").hover(function() {
		$(".showem").toggle();
	});
	$(".qq").hover(function() {
		$(".showqq").toggle();
	});
	$(".wb").hover(function() {
		$(".showwb").toggle();
	});
	$("#top").click(function() {
		if (scroll == "off") return;
		$("html,body").animate({
			scrollTop: 0
		},
		600);
	});
	
	/*个人中心
	$(".link2.he.ico").mousemove(function(){
		$(".logmine").slideDown();
	});
	
	$(".logmine").mouseleave(function(){
		$(".logmine").slideUp();
	})
	$("body").click(function(){
		$(".hidebox").slideUp();
		$(".logmine").slideUp();
	});*/

});
	/*弹窗登录透明层*/
	function reglog_open() {
		$('body').append('<div id="mask" onclick="reglog_close();"></div>');
		$('#mask').show();
		$('#reglog').css('display', 'block');
	}
	function reglog_close() {
		$('#mask').remove();
		$('#reglog').css('display', 'none');
	}
	
	/*登录后个人中心下拉框*/
	function logmine(){
		document.getElementById("lne").style.display="block";
	}
	function logclose(){
		document.getElementById("lne").style.display="none";	
	}

    //倒计时
    (function ($) {
        $.fn.countDown = function (options) {
            var defaults = { seconds: 120 * 60, factor: -1, changeElement: null, pauseElement: null, callback: null, callback_doEnd: null, callback_timeOut: null };
            var opts = $.extend(defaults, options);
            $(this).each(function (index, item) {
                countdown(item, opts);
            });
        };

        var countdown = function (item, config) {
            var seconds = typeof ($(item).attr("data-time-over")) == "undefined" ? config.seconds : parseInt($(item).attr("data-time-over"));
            var timer = null;
            var doWork = function () {
                //累计耗时属性
                var timeElapsed = (typeof ($(item).attr("data-time-elapsed")) == "undefined" ? 0 : parseInt($(item).attr("data-time-elapsed")));
                $(item).attr("data-time-elapsed", ++timeElapsed);
                var timePause = (typeof ($(item).attr("data-time-pause")) == "undefined" ? 0 : parseInt($(item).attr("data-time-pause")));
                if (timePause == 0) {
                    //页面显示倒计时
                    if (seconds >= 0) {
                        if (typeof (config.callback) == "function") {
                            var data = {
                                total: seconds,
                                minute: Math.floor(seconds / 60) < 10 ? "0" + Math.floor(seconds / 60) : Math.floor(seconds / 60),
                                second: Math.floor(seconds % 60) < 10 ? "0" + Math.floor(seconds % 60) : Math.floor(seconds % 60)
                            };
                            config.callback.call(item, data);
                        }
                        //倒计时为0时回调
                        if (seconds == 0 && typeof (config.callback_doEnd) == "function") {
                            config.callback_doEnd.call(item);
                        }
                        seconds--;
                    } else {
                        //超时继续倒计时
                        if (typeof (config.callback_timeOut) == "function") {
                            data = {
                                total: seconds * config.factor,
                                minute: Math.floor((seconds * config.factor) / 60) < 10 ? "0" + Math.floor((seconds * config.factor) / 60) : Math.floor((seconds * config.factor) / 60),
                                second: Math.floor((seconds * config.factor) % 60) < 10 ? "0" + Math.floor((seconds * config.factor) % 60) : Math.floor((seconds * config.factor) % 60)
                            };
                            config.callback_timeOut.call(item, data);
                            seconds--;
                        } else {
                            //如果没有超时回调函数，则清除timer
                            window.clearInterval(timer);
                        }
                    }
                }
            };
            timer = window.setInterval(doWork, 1000);
            doWork();
        };
    })(jQuery);