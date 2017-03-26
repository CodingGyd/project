// JavaScript Document
 $(document).ready(function(e) {
	 //加载功能函数模块基础数据
	 $.ajax({  
            type: "POST",  
            url: "http://localhost:8181/mine-server/api/func/utilfunctions",  
            dataType: "jsonp",  
            jsonp: "callback",
            success:function(data){
         
            	$.each(data, function(idx, obj) {
              		var content = obj.content;
            	    $(".util-function-list-group").append("<a href='#' class='list-group-item' title='"+content+"'>"+obj.title+" </a>");
            	});
            	   $(".list-group-item").on("click",function(){
                   	$("#model-content").empty();
                   	$("#model-content").append("<pre id='util-content'>"+$(this).attr("title")+"</pre>");
                   	$("#myModal").modal('show');
                   });
              },
              error:function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
              }});
	 //加载IT学习模块基础数据
		$.ajax({  
            type: "POST",  
            url: "http://localhost:8181/mine-server/api/func/learnsite",  
            dataType: "jsonp",  
            jsonp: "callback",
            success:function(data){
         
            	$.each(data, function(idx, obj) {
            	    $(".it-technology-list-group").append("<a href="+obj.url+" target='_blank' class='list-group-item'>"+obj.title+" </a>");
            	});
              },
              error:function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
              }});
		
	 //点击功能函数
	$("#alink-util-function").click(function(){
		$("#li-it-technology").removeClass("active");
		$("#li-data-export").removeClass("active");
		$("#li-util-function").addClass("active");
 
		$("#div-it-technology").hide("active");
		$("#div-data-export").hide("active");
		$("#div-util-function").show("active");
	});
 
	 //点击IT学习
	$("#alink-it-technology").click(function(){
		$("#li-util-function").removeClass("active");
		$("#li-data-export").removeClass("active");
		$("#li-it-technology").addClass("active");
		$("#div-util-function").hide("active");
		$("#div-data-export").hide("active");
		$("#div-it-technology").show("active");
	});
	
	 //点击通用导出
	$("#alink-data-export").click(function(){
		$("#li-util-function").removeClass("active");
		$("#li-it-technology").removeClass("active");
		$("#li-data-export").addClass("active");

		$("#div-util-function").hide("active");
		$("#div-it-technology").hide("active");
		$("#div-data-export").show("active");
	});
	
	  //定义一个下载的方法
	  jQuery.download = function(url, method, script){
		    jQuery('<form action="'+url+'" method="'+method+'"><input type="hidden" name="script" value="'+script+'"/></form>')
		    .appendTo('body').submit().remove();
		};
		
	 //点击通用导出
	$("#bt-data-export").click(function(){
		 var script = $("#script").val();
		  if(!checkScript(script)){
			  alert("脚本不合法!");
			  return false;
		  }
	    $.download('http://localhost:8181/mine-server/api/func/data-export', 'POST', script); // 下载文件
	});
	
	
	
 });
 
 
 
 
 
 /**
  * 验证sql是否合法，只能为查询语句
  * @param script
  * @returns
  */
 	function checkScript(script){
 		script=script.toUpperCase();//测试用sql语句
 		var column="(\\w+\\s*(\\w+\\s*){0,1})";//一列的正则表达式 匹配如 product p
 		var columns=column+"(,\\s*"+column+")*"; //多列正则表达式 匹配如 product p,category c,warehouse w
 		var ownerenable="((\\w+\\.){0,1}\\w+\\s*(\\w+\\s*){0,1})";//一列的正则表达式 匹配如 a.product p
 		var ownerenables=ownerenable+"(,\\s*"+ownerenable+")*";//多列正则表达式 匹配如 a.product p,a.category c,b.warehouse w
 		var top = "(\\s*TOP\\s+[0-9]{1,}\\s*){0,1}";//匹配如TOP 1 
 		var from="FROM\\s+"+columns;
 		var condition="(\\w+\\.){0,1}\\w+\\s*(>|<|>=|<=|=|LIKE|IS)\\s*'?(\\w+\\.){0,1}[\\w%]+'?";//条件的正则表达式 匹配如 a=b 或 a is b..
 		var conditions=condition+"(\\s+(AND|OR)\\s*"+condition+"\\s*)*";//多个条件 匹配如 a=b and c like 'r%' or d is null 
 		var where="(WHERE\\s+"+conditions+"){0,1}";
 		var pattern="\\s*SELECT\\s+"+top+"(\\*|"+ownerenables+")\\s+"+from+"\\s*"+where+"\\s*"; //匹配最终sql的正则表达式
 		var reg = new RegExp(pattern);  
 		if(reg.test(script)){
 			return true;
 		}else {
 			return false;
 		}
 	}
