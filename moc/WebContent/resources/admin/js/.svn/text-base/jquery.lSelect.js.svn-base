/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 * 
 * JavaScript - lSelect
 * Version: 3.0
 */

(function($) {
	$.fn.extend({
		
		//方法名：方法体
		lSelect: function(options) {
			
			//默认设置
			var settings = {
				choose: "请选择...",
				emptyValue: "",
				cssStyle: {"margin-right": "4px"},
				url: null,
				type: "GET",
				addressLimit:3
				
			};
			
			//options接收用户自定义请求---扩展原对象
			$.extend(settings, options);
			
			//初始化隐藏域
			var cache = {};
			
			//this---调用该方法的jquery对象（为每一个$areaId都执行该方法）
			//function为对于该jquery对象所要执行的函数
			return this.each(function() {
				
				//每一个option封装为一个jquery对象$input
				//$areaId
				var $input = $(this);
				var id = $input.val();
				var treePath = $input.attr("treePath");
				
				var count=0;
				
				//为了区别与调用该方法的$areaId的name属性
				var selectName = $input.attr("name") + "_select";
				
				//有指定的路径
				if (treePath != null && treePath != "") {
					var ids = (treePath + id + ",").split(",");
					
					var $position = $input;
					for (var i = 1; i < ids.length; i ++) {
						$position = addSelect($position, ids[i - 1], ids[i],count);
					}
				} else {
					
					//$input为$areaId
					addSelect($input, null, null,count);
				}
				
				//添加select标签---$position为触发函数的位置
				function addSelect($position, parentId, currentId,count) {
					if(settings.addressLimit<3){
						count++;
						if(count>settings.addressLimit){
							return false;
						}
					}
					
					//清除无用的select标签
					$position.nextAll("select[name=" + selectName + "]").remove();
					
					//没有选择顶级地区
					if ($position.is("select") && (parentId == null || parentId == "")) {
						return false;
					}
					
					//获得顶级地区---获得对应的孩子cache[parentId]中的数据为父类为parentId的节点
					//作用是获得数据并存入cache{}中
					if (cache[parentId] == null) {
						$.ajax({
							
							//获取数据的地址
							url: settings.url,
							type: settings.type,
							
							//若为null获得的数据是顶级地区，，否则是其孩子
							data: parentId != null ? {parentId: parentId} : null,
							dataType: "json",
							cache: false,
							async: false,
							success: function(data) {
								
								//第一次为cache[null]=map<id,name>顶级地区的集合
								cache[parentId] = data;
							}
						});
					}
					
					
					var data = cache[parentId];
					
					//看data是否有属性---parentId为叶子时结束添加标签
					if ($.isEmptyObject(data)) {
						return false;
					}
					var select = '<select name="' + selectName + '">';
					
					//设置默认的option--‘请选择’这一项
					if (settings.emptyValue != null && settings.choose != null) {
						select += '<option value="' + settings.emptyValue + '">' + settings.choose + '</option>';
					}
					
					//data是一个map集合(id,name)
					//遍历data,,回调函数的两个参数--对象的成员（或数组的索引）：对应的变量（或内容）
					//此处索引为id,,内容为地区名
					$.each(data, function(value, name) {
						if(value == currentId) {
							select += '<option value="' + value + '" selected="selected">' + name + '</option>';
						} else {
							select += '<option value="' + value + '">' + name + '</option>';
						}
					});
					select += '</select>';
					
					//$position发生变化时在其后插入select标签（含有对应的option）
					//为返回的jquery对象绑定一个change函数
					return $(select).css(settings.cssStyle).insertAfter($position).bind("change", function() {
						var $this = $(this);
						
						//$(input).val的值存放的是（最后选中的有效地址id）
						if($this.val() == "") {
							//选中了‘请选择’
							var $prev = $this.prev("select[name=" + selectName + "]");
							if ($prev.size() > 0) {
								$input.val($prev.val());
							} else {
								$input.val(settings.emptyValue);
							}
						}else {
							$input.val($this.val());
						}
						
						addSelect($this, $this.val(), null,count);
					});
				}
			});
			
		}
	});
})(jQuery);