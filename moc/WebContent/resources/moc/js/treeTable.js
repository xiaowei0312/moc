//设置节点隐藏
	//tableId 使用此方法时传ID ，加class t_zsd
	//<table id="listTable" class="list t_zsd">
    function treeTable(tableId){
    	
	$('#'+tableId+' [gradeClass^="keypoint-level-"]').each(function(i){
		  	var clsasStr=$(this).attr('class');
			var data_id=$(this).attr('data_id');
			var data_parent_id=$(this).attr('data_parent_id');
			if($(this).find('span').attr('class')=='tree_parent'){
				$('#'+tableId+" td [data_parent_id='"+data_id+"']").parent().show();
				class_children(this);
			}
		 });
		 
	$('#'+tableId+'  [gradeClass^="keypoint-level-"]').on("click", function(){
				var clsasStr=$(this).attr('class');
				var data_id=$(this).attr('data_id');
				var data_parent_id=$(this).attr('data_parent_id');
				if($(this).find('span').attr('class')=='tree_none'){
					return true;
				}
				if($(this).find('span').attr('class')=='tree_parent'){
					$(this).find('span').removeClass("tree_parent");
					$(this).find('span').addClass("tree_children");
					$('#'+tableId+" td[data_parent_id='"+data_id+"']").parent().show();
				}else{
					$(this).find('span').removeClass("tree_children");
					$(this).find('span').addClass("tree_parent");
					children(this);
				}
			});
	
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
			if($('#'+tableId+" td[data_parent_id='"+data_id+"']").html() == undefined){
				if($('#'+tableId+" td[data_id='"+data_id+"']").find("span").attr('class')=='tree_none'){
					return true;
				}else{
					$('#'+tableId+" td[data_id='"+data_id+"']").find("span").removeClass();
					$('#'+tableId+" td[data_id='"+data_id+"']").find("span").addClass("tree_none");
					return true;
				}
			}else{
				$('#'+tableId+" td[data_parent_id='"+data_id+"']").each(function(i){
					if($(this).find('span').attr('class')=='tree_none'){
						return false;
					}
						clsasStr=$(this).find('span').attr('class');
						data_id=$(this).attr('data_id');
				  		var children_id=$(this).attr('data_id');
				  		$('#'+tableId+" td[data_id='"+data_id+"']").parent().hide();
				  		if(clsasStr!='tree_none'){
					  		$(this).find('span').removeClass("tree_children");
							$(this).find('span').addClass("tree_parent");
				  		}
				  		if($('#'+tableId+" td[data_parent_id='"+data_id+"']").html() != undefined){
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
			if($('#'+tableId+" td[data_parent_id='"+data_id+"']").html() == undefined){
				return true;
			}else{
				$('#'+tableId+" td[data_parent_id='"+data_id+"']").each(function(i){
						clsasStr=$(this).find('span').attr('class');
						data_id=$(this).attr('data_id');
				  		var children_id=$(this).attr('data_id');
				  		$('#'+tableId+" td[data_id='"+data_id+"']").parent().hide();
				  		$(this).find('span').removeClass("tree_children");
						$(this).find('span').addClass("tree_parent");
				  		if($('#'+tableId+" td[data_parent_id='"+data_id+"']").html() != undefined){
				  			children2(data_id);
				  		}
				});
			}
		}
    }
   