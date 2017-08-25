(function($){
	$.fn.extend({
		treeSelect:function(options){
			var settings={
				choose: "请选择...",
				choose2: "请选择...",
				choose3: "请选择...",
				choose4: "请选择...",
				emptyValue: "",
				cssStyle: {"margin-right": "4px"},
				url: null,
				type: "GET",
				limitSelect:2,
				breakPoint:null,
				callback: null
			};
			$.extend(settings,options);
			var cache={};
			
			//为页面上的每一个input标签绑定select
			return this.each(function(){
				var $input=$(this);
				var id = $input.val();
				var treePath = $input.attr("treePath");
				var selectName = $input.attr("name")+"_select";
				var count=1;
				var limitS=settings.limitSelect+1;
				var jsonNameValue=null;
				
				if(treePath !=null && treePath !=""){
					limitS=limitS-1;
					var ids = (treePath + id + ",").split(",");
					var $position = $input;
					$position = addSelect($position, null, ids[1],-1);
					addSelect($position, ids[1], id,count);
				}else{
					addSelect($input,null,null,count);
				}
				
				function addSelect($position,parentId,currentId,count){
					if(count==0){
						return false;
					}
					count++;
					if(count>limitS){
						return false;
					}
					
					$position.nextAll("select[name=" + selectName + "]").remove();
					if ($position.is("select") && (parentId == null || parentId == "")) {
						return false;
					}
					
					var jsonData;
					if(settings.breakPoint==null){
						jsonData=parentId != null ? {parentId: parentId} : null;
					}else{
						 //不是同一种数据须清除
						 cache={};
						 
						//获取的数据不是同一类型
						if((count-2)==settings.breakPoint){
							
							//分隔点
							cache[parentId] = null;
							parentId=null;
							jsonNameValue=$input.val();
						}else if((count-2)<settings.breakPoint){
							jsonNameValue=null;
						}
						jsonData={parentId: parentId,"jsonName":jsonNameValue};
					}
					
					if (cache[parentId] == null) {
						
						$.ajax({
							url: settings.url,
							type: settings.type,
							data: jsonData,
							dataType: "json",
							cache: false,
							async: false,
							success: function(data) {
								cache[parentId] = data;
							}
						});
					}
					var data = cache[parentId];
					if ($.isEmptyObject(data)) {
						return false;
					}
					var select = '<select name="' + selectName + '">';
					if (settings.emptyValue != null && settings.choose != null) {
						if(count==2){
							select += '<option value="' + settings.emptyValue + '">' + settings.choose + '</option>';
						}else if(count==3){
							select += '<option value="' + settings.emptyValue + '">' + settings.choose2 + '</option>';
						}else if(count==4){
							select += '<option value="' + settings.emptyValue + '">' + settings.choose3 + '</option>';
						}else{
							select += '<option value="' + settings.emptyValue + '">' + settings.choose4 + '</option>';
						}
					}
					$.each(data, function(index, value) {
						var temp=value.split(",");
						var space=" ";
						for(var i=0;i<temp[0];i++){
							space +="&nbsp;&nbsp;&nbsp;&nbsp;";
						}
						if(temp[1]== currentId) {
							select += '<option value="' + temp[1] + '" selected="selected">' 
							+space+ temp[2] + '</option>';
						} else {
							select += '<option value="' + temp[1] + '">' +space+ temp[2] + '</option>';
						}
					});
					select += '</select>';
					return $(select).css(settings.cssStyle).insertAfter($position).bind("change", function() {
						var $this = $(this);
						if($this.val() == "") {
							var $prev = $this.prev("select[name=" + selectName + "]");
							if ($prev.size() > 0) {
								$input.val($prev.val());
							} else {
								$input.val(settings.emptyValue);
							}
						}else {
							$input.val($this.val());
						}
						if (settings.callback != null && typeof settings.callback == "function") {
							settings.callback($this.val());
						}
						if(count==0 && treePath !=null && treePath !=""){
							count=-1;
						}
						addSelect($this, $this.val(), null,count);
					});
				}
			});
		}
	});
})(jQuery);