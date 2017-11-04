
$(function(){
		$('#dg').datagrid({
			iconCls:'icon-edit',
			width:1200,
			height:800,
			singleSelect:true,
			 onDblClickRow: function (index, row) {  
                  addTab("编辑文章", "modules/article_update?id="+row.id, "icon-application-form-edit", "0");
		        },  
			idField:'id',
			url:'sys/article/articlelist',
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
			         url: "/sys/article/update_notwithcontent",
		         dataType:"text",
		         contentType:"application/json;charset=utf-8",
  			  		data:JSON.stringify(row),
			         async:true,
			         success: function(data){
			        	   	$('#dg').datagrid('reload',{});
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
				         url: "/sys/article/delete",
  	  			  		data:{"id":row.id},
				         async:true,
				         success: function(data){
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
	
	function addArticle(){
		addTab("发布文章", "modules/article_add", "icon-chart-organisation", 0);
	}
