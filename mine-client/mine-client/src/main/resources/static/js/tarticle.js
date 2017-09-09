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
              url: "http://127.0.0.1:8080/data/utilfunction",  
          	  dataType: "json",  
           // url: "http://180.76.134.57:8080/mine-server/api/func/utilfunctions",  
           // dataType: "jsonp",  
          //  jsonp: "callback",
            success:function(data){
            	alert("=====加载函数"+data);
             	$.each(data, function(idx, obj) {
              		var content = obj.content;
            	    $(".article_list_container").append("<a href='#' class='list-group-item' title='"+content+"'>"+obj.title+" </a>");
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
         url: "http://127.0.0.1:8080/data/itinfo",  
         dataType: "json",  
         success:function(data){
         	alert("=====加载文章"+data);
         	$.each(data, function(idx, obj) {
         	    $(".article_list_container").append("<a href="+obj.url+" target='_blank' class='list-group-item'>"+obj.title+" </a>");
         	});
           },
           error:function(XMLHttpRequest, textStatus, errorThrown) {
             alert(XMLHttpRequest.status);
             alert(XMLHttpRequest.readyState);
             alert(textStatus);
           }});
 }
  
