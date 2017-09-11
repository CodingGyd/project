// JavaScript Document
 $(document).ready(function(e) {
	 
	 	//加载编程文章列表-功能函数
	    loadTArticleUtilFunctionList();
		//加载编程文章列表-IT资讯网站
	 	loadTarticleLearnSiteList();
 });
 
 function loadTArticleUtilFunctionList() {
 
	 $.ajax({  
            type: "POST",  
              url: "data/utilfunction",  
          	  dataType: "json",  
           // url: "http://180.76.134.57:8080/mine-server/api/func/utilfunctions",  
           // dataType: "jsonp",  
          //  jsonp: "callback",
            success:function(data){
             
             	$.each(data, function(idx, obj) {
              		var content = obj.content;
              		
            	    $(".article_list_container").append("<a href='/detail?content="+content+"' target='_blank' class='list-group-item' title='"+content+"'>"+obj.title+" </a>");
            	});
              },
              error:function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
              }});
 }
 
 
 function loadTarticleLearnSiteList() {

		$.ajax({  
         type: "POST",
         url: "data/itinfo",  
         dataType: "json",  
         success:function(data){
          
         	$.each(data, function(idx, obj) {
         		var content = obj.content;
         	    $(".article_list_container").append("<a href='/detail?content="+content+"' target='_blank' class='list-group-item'>"+obj.title+" </a>");
         	});
           },
           error:function(XMLHttpRequest, textStatus, errorThrown) {
             alert(XMLHttpRequest.status);
             alert(XMLHttpRequest.readyState);
             alert(textStatus);
           }});
 }
  
