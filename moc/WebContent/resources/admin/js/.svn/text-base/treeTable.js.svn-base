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
	
	//上移、下移
	function moveNode(nodeId,type,order,moveUrl){
		var str=$("td[data_id='"+nodeId+"']").parent();
		var currentTreePath=$(str).children("td").attr('tree_path');//当前节点的treePath
		var currentOrder=$(str).children("td").attr('order');//当前节点的排序号
		var currentAllNodes= new  Array();//当前的所有节点
		var current_count=0;//当前节点计数
		var currentAllBrother= new  Array();//当前的同辈兄弟节点
		var currentBrother_count=0;//当前同辈兄弟计数
		currentAllNodes[current_count++]=str;//加入当前节点
		
		var current_index=0;//当前节点的位置
		//所有兄弟包括自己
		$("[tree_path="+currentTreePath+"]").each(function(i){
			currentAllBrother[currentBrother_count++]=$(this);
			if($(this).parent().children("td").attr('data_id')==nodeId){
				current_index=i;
			}
		});
		//当前节点的所有子节点
		$("[tree_path^="+currentTreePath+nodeId+"-]").each(function(i){
			currentAllNodes[current_count++]=$(this).parent();
		});
		if(type=='up'){
			if(current_index>0){
				var move_node=currentAllBrother[--current_index].parent();
				var moveOrder=$(move_node).children("td").attr('order');//移动节点的排序号
				var moveNodeId=$(move_node).children("td").attr('data_id');//移动节点的id
				//更新数据库的排序号
				if(updateMoveNode(nodeId,currentOrder,moveNodeId,moveOrder,moveUrl)){
					$(move_node).before(currentAllNodes);
				}else{
					$.message("移动失败。");
				}
			}
			
		}	
		if(type=='down'){
			//移动节点的所有子节点
			if(current_index<currentAllBrother.length-1){
				var move_node=currentAllBrother[++current_index].parent();
				var moveOrder=$(move_node).children("td").attr('order');//移动节点的排序号
				var moveNodeId=$(move_node).children("td").attr('data_id');//移动节点的id
				var moveAllNodes= new  Array();//移动节点的所有节点
				var move_count=0;//移动节点计数
				moveAllNodes[move_count++]=move_node;
				$("[tree_path^="+currentTreePath+moveNodeId+"-]").each(function(i){
					moveAllNodes[move_count++]=$(this).parent();
				});
				//更新数据库的排序号
				if(updateMoveNode(nodeId,currentOrder,moveNodeId,moveOrder,moveUrl)){
					$(moveAllNodes[moveAllNodes.length-1]).after(currentAllNodes);
				}else{
					$.message("移动失败。");
				}
			}
		}
	}
	
	function updateMoveNode(currentNodeId,currentOrder,moveNodeId,moveOrder,moveUrl){
		var flag=false;
		$.ajax( {
				type : "POST",
				url : moveUrl+".jhtml",
				data : {"currentNodeId":currentNodeId,"currentOrder":currentOrder,"moveNodeId":moveNodeId,"moveOrder":moveOrder},
				dataType:"text",
				async:false,
				success : function(msg) {
					if(msg=='Y'){
						flag=true;
					}else{
						flag=false;
					}
				}
		});
		return flag;
	}
	function openAllNode(){
		$('#listTable tr').each(function(i){
			if($(this).find("span").attr('class')=='tree_parent'){
				$(this).find("span").removeClass('tree_parent');
				$(this).find("span").addClass('tree_children');
			}
			$(this).show();
		});
	}