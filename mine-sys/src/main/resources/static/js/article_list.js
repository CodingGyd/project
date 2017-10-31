
$(function(){
		$('#dg').datagrid({
			iconCls:'icon-edit',
			width:1200,
			height:800,
			singleSelect:true,
			 onDblClickRow: function (index, row) {  
                 alert(index);
                 addTab("编辑文章", "modules/article_update", "icon-application-form-edit", "0");
		        },  
			idField:'id',
			url:'sys/articlelist',
			columns:[[
				{field:'id',title:'编号',width:50},
				{field:'title',title:'标题',width:300,editor:'text'},
				{field:'descs',title:'描述',width:300,editor:'text'},
				{field:'type',title:'类型',width:60,editor:'numberbox'},
				{field:'updatetime',title:'更新时间',width:160},
				{field:'readingcount',title:'浏览量',width:160,editor:'numberbox'},
				{field:'action',title:'操作',width:90,align:'center',
					formatter:function(value,row,index){
						if (row.editing){
							var s = '<a href="#" onclick="saverow(this)">保存</a> ';
							var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
							return s+c;
						} else {
							var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
							var d = '<a href="#" onclick="deleterow(this)">删除</a>';
							return e+d;
						}
					}
				}
			]],
			onBeforeEdit:function(index,row){
				row.editing = true;
				updateActions(index);
			},
			onAfterEdit:function(index,row){
				row.editing = false;
				updateActions(index);
				  $.ajax({
			         type: "Post",
			         url: "/sys/updatebatch",
		         dataType:"json",
		         contentType:"application/json;charset=utf-8",
  			  		data:JSON.stringify(row),
			         async:true,
			         success: function(data){
			        	   	alert("修改成功!");
			        	   	$('#dg').datagrid('reload');
			        	 }
			         }); 

			},
			onCancelEdit:function(index,row){
				row.editing = false;
				updateActions(index);
			}
		});
	});
	function updateActions(index){
		$('#dg').datagrid('updateRow',{
			index: index,
			row:{}
		});
	}
	
	function getRowIndex(target){
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}
	function editrow(target){
		$('#dg').datagrid('beginEdit', getRowIndex(target));
	}
	function deleterow(target){
		$.messager.confirm('Confirm','Are you sure?',function(r){
			if (r){
				var row = $('#dg').datagrid('getSelected');   
				  $.ajax({
				         type: "Post",
				         url: "/sys/delete",
  	  			  		data:{"id":row.id},
				         async:true,
				         success: function(data){
				        	   	alert("删除成功!");
				        	   	$('#dg').datagrid('deleteRow', getRowIndex(target));
				        	 }
				         }); 
			}
		});
	}
	function saverow(target){
 		$('#dg').datagrid('endEdit', getRowIndex(target));
	}
	function cancelrow(target){
		$('#dg').datagrid('cancelEdit', getRowIndex(target));
	}
	/**
	* Name 添加菜单选项
	* Param title 名称
	* Param href 链接
	* Param iconCls 图标样式
	* Param iframe 链接跳转方式（true为iframe，false为href）
	*/	
	function addTab(title, href, iconCls, iframe){
		var tabPanel = $('#wu-tabs');
		if(!tabPanel.tabs('exists',title)){
			var content = '<iframe scrolling="auto" frameborder="0"  src="'+ href +'" style="width:100%;height:100%;"></iframe>';
			if(iframe){
				tabPanel.tabs('add',{
					title:title,
					content:content,
					iconCls:iconCls,
					fit:true,
					cls:'pd3',
					closable:true
				});
			}
			else{
				tabPanel.tabs('add',{
					title:title,
					href:href,
					iconCls:iconCls,
					fit:true,
					cls:'pd3',
					closable:true
				});
			}
		}
		else
		{
			tabPanel.tabs('select',title);
		}
	}

//	var editIndex = undefined;
//		function endEditing(){
//			if (editIndex == undefined){return true}
//			if ($('#dg').datagrid('validateRow', editIndex)){
//			 	var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'id'});
//				$('#dg').datagrid('endEdit', editIndex);  
//				editIndex = undefined;
//				return true;
//			} else {
//				return false;
//			}
//		}
//		function onClickRow(index){
//			if (editIndex != index){
//				if (endEditing()){
//					$('#dg').datagrid('selectRow', index)
//							.datagrid('beginEdit', index);
//					editIndex = index;
//				} else {
//					$('#dg').datagrid('selectRow', editIndex);
//				}
//			}
//		}
//		function append(){
//			if (endEditing()){
// 				editIndex = $('#dg').datagrid('getRows').length-1;
//				$('#dg').datagrid('selectRow', editIndex)
//						.datagrid('beginEdit', editIndex);
//			}
//		}
//		function removeit(){
//			if (editIndex == undefined){return}
//			$('#dg').datagrid('cancelEdit', editIndex)
//					.datagrid('deleteRow', editIndex);
//			editIndex = undefined;
//		}
//		function accept(){
//			if (endEditing()){
//				var rows = $('#dg').datagrid('getChanges');
//				  if (rows.length > 0) {  
//					  alert("11221"+JSON.stringify(rows));
//// 
////					  $.ajax({
////					         type: "Post",
////					         url: "/sys/updatebatch",
////					         dataType:"json",
//////					         contentType:"application/json;charset=utf-8",
//////					  		data:"articles" :"1",
////					         data:JSON.stringify(rows),
////					         async:true,
////					         success: function(data){
////					        	   	alert("修改成功!");
////					        	 }
////					         }); 
//				  }
//				$('#dg').datagrid('acceptChanges');
//			}
//		}
//		function reject(){
//			$('#dg').datagrid('rejectChanges');
//			editIndex = undefined;
//		}
//		function getChanges(){
//			var rows = $('#dg').datagrid('getChanges');
//			alert(rows.length+' 行有改动!');
//		}