
$(function(){
		$('#dg_keyword').datagrid({
			iconCls:'icon-edit',
			width:1200,
			height:800,
			singleSelect:true,
			idField:'id',
			url:'sys/keyword/keywordlist',
			columns:[[
				{field:'id',title:'编号',width:50},
				{field:'name',title:'关键字',width:300,editor:'text'},
				{field:'updatetime',title:'更新时间',width:160},
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
			         url: "/sys/keyword/update",
		         dataType:"text",
		         contentType:"application/json;charset=utf-8",
  			  		data:JSON.stringify(row),
			         async:true,
			         success: function(data){
			        	   	$('#dg_keyword').datagrid('reload',{});
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
		$('#btn_save_keyword').click(function() {
			// submit the form
			$('#ff_keyword').submit();
		});
	});
	function updateActions(index){
		$('#dg_keyword').datagrid('updateRow',{
			index: index,
			row:{}
		});
	}
	
	function getRowIndex(target){
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}
	function editrow(target){
		$('#dg_keyword').datagrid('beginEdit', getRowIndex(target));
	}
	function deleterow(target){
		$.messager.confirm('Confirm','Are you sure?',function(r){
			if (r){
				var row = $('#dg_keyword').datagrid('getSelected');   
				  $.ajax({
				         type: "Post",
				         url: "/sys/keyword/delete",
  	  			  		data:{"id":row.id},
				         async:true,
				         success: function(data){
 				        	   	$('#dg_keyword').datagrid('deleteRow', getRowIndex(target));
				        	 }
				         }); 
			}
		});
	}
	function saverow(target){
 		$('#dg_keyword').datagrid('endEdit', getRowIndex(target));
	}
	function cancelrow(target){
		$('#dg_keyword').datagrid('cancelEdit', getRowIndex(target));
	}
	
	function addKeyWord(){
 		$('#dd_keyword').dialog({
		    title: '填写关键字',
		    width: 400,
		    height: 200,
		    closed: false,
		    cache: false,
		    href: '',
		    modal: true
		});
		$('#dd_keyword').dialog('refresh', '');
		$('#ff_keyword').form({
		    url:'/sys/keyword/insert',
		    onSubmit: function(){
 		    },
		    success:function(data){
        	   	$('#dg_keyword').datagrid('reload',{});
        		$('#dd_keyword').dialog('close','');
 		    },
 		    error:function () {
 		    	alert("error");
 		    }
		});
	}
