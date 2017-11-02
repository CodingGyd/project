
$(function(){
		$('#dg_daily').datagrid({
			iconCls:'icon-edit',
			width:1200,
			height:800,
			singleSelect:true,
			idField:'id',
			url:'sys/dailyessay/dailyessaylist',
			columns:[[
				{field:'id',title:'编号',width:50},
				{field:'content',title:'随笔内容',width:300,editor:'text'},
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
			         url: "/sys/dailyessay/update",
		         dataType:"text",
		         contentType:"application/json;charset=utf-8",
  			  		data:JSON.stringify(row),
			         async:true,
			         success: function(data){
			        	   	$('#dg_daily').datagrid('reload',{});
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
		$('#btn_save_daily').click(function() {
			// submit the form
			$('#ff_daily').submit();
		});
	});
	function updateActions(index){
		$('#dg_daily').datagrid('updateRow',{
			index: index,
			row:{}
		});
	}
	
	function getRowIndex(target){
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}
	function editrow(target){
		$('#dg_daily').datagrid('beginEdit', getRowIndex(target));
	}
	function deleterow(target){
		$.messager.confirm('Confirm','Are you sure?',function(r){
			if (r){
				var row = $('#dg_daily').datagrid('getSelected');   
				  $.ajax({
				         type: "Post",
				         url: "/sys/dailyessay/delete",
  	  			  		data:{"id":row.id},
				         async:true,
				         success: function(data){
				        	   	alert("删除成功!");
				        	   	$('#dg_daily').datagrid('deleteRow', getRowIndex(target));
				        	 }
				         }); 
			}
		});
	}
	function saverow(target){
 		$('#dg_daily').datagrid('endEdit', getRowIndex(target));
	}
	function cancelrow(target){
		$('#dg_daily').datagrid('cancelEdit', getRowIndex(target));
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
		if(tabPanel.tabs('exists',title)){
 			tabPanel.tabs("close",title);
		}
		
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
	
	function addDailyEssay(){
 		$('#dd_daily').dialog({
		    title: '填写随笔',
		    width: 400,
		    height: 200,
		    closed: false,
		    cache: false,
		    href: '',
		    modal: true
		});
		$('#dd_daily').dialog('refresh', '');
		$('#ff_daily').form({
		    url:'/sys/dailyessay/insert',
		    onSubmit: function(){
 		    },
		    success:function(data){
        	   	$('#dg_daily').datagrid('reload',{});
        		$('#dd_daily').dialog('close','');
 		    },
 		    error:function () {
 		    	alert("error");
 		    }
		});
	}
