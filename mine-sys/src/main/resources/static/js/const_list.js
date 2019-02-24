
$(function(){
		$('#dg_const').datagrid({
			iconCls:'icon-edit',
			width:1200,
			height:800,
			singleSelect:true,
			idField:'id',
			url:'sys/const/list',
			columns:[[
				{field:'id',title:'编号',width:50},
				{field:'lb',title:'常量分类代码',width:100,editor:'text'},
				{field:'lbmc',title:'常量分类名称',width:100,editor:'text'},
				{field:'dm',title:'常量代码',width:100,editor:'text'},
				{field:'ms',title:'常量名称',width:100,editor:'text'},
				{field:'remarks',title:'备注',width:300,editor:'text'},
				{field:'updatetimestr',title:'更新时间',width:160},
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
			         url: "/sys/const/update",
		         dataType:"text",
		         contentType:"application/json;charset=utf-8",
  			  		data:JSON.stringify(row),
			         async:true,
			         success: function(data){
			        	   	$('#dg_const').datagrid('reload',{});
			        	 },
			        error:function(){
			        	alert("Error");
			        }
			         }); 

			},
			onCancelEdit:function(index,row){
				row.editing = false;
				updateActions(index);
			}
		});
		$('#btn_save_const').click(function() {
			// submit the form
			$('#ff_const').submit();
		});
	});
	function updateActions(index){
		$('#dg_const').datagrid('updateRow',{
			index: index,
			row:{}
		});
	}
	
	function getRowIndex(target){
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}
	function editrow(target){
		$('#dg_const').datagrid('beginEdit', getRowIndex(target));
	}
	function deleterow(target){
		$.messager.confirm('Confirm','Are you sure?',function(r){
			if (r){
				var row = $('#dg_const').datagrid('getSelected');   
				  $.ajax({
				         type: "Post",
				         url: "/sys/const/delete",
  	  			  		data:{"id":row.id},
				         async:true,
				         success: function(data){
				        	   	alert("删除成功!");
				        	   	$('#dg_const').datagrid('deleteRow', getRowIndex(target));
				        	 }
				         }); 
			}
		});
	}
	function saverow(target){
 		$('#dg_const').datagrid('endEdit', getRowIndex(target));
	}
	function cancelrow(target){
		$('#dg_const').datagrid('cancelEdit', getRowIndex(target));
	}
	function addSysConst(){
 		$('#dd_const').dialog({
		    title: '填写新常量',
		    width: 400,
		    height: 200,
		    closed: false,
		    cache: false,
		    href: '',
		    modal: true
		});
		$('#dd_const').dialog('refresh', '');
		$('#ff_const').form({
		    url:'/sys/const/insert',
		    onSubmit: function(){
 		    },
		    success:function(data){
        	   	$('#dg_const').datagrid('reload',{});
        		$('#dd_const').dialog('close','');
 		    },
 		    error:function () {
 		    	alert("error");
 		    }
		});
	}
