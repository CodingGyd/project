// JavaScript Document
 $(document).ready(function(e) {
		$.ajax({  
            type: "POST",  
            url: "http://localhost:8181/mine-server/api/func/utilfunctions",  
            dataType: "jsonp",  
            jsonp: "callback",
            success:function(data){
         
            	$.each(data, function(idx, obj) {
            	    $(".util-function-list-group").append("<a href='#' class='list-group-item'>"+obj.title+" </a>");
            	});
              },
              error:function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
              }});
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
 
	/* //点击主页
	 $("#alink-main-page").click(function(){
	 
		$("#li-util-function").removeClass("active");
		$("#li-it-technology").removeClass("active");
		$("#li-main-page").addClass("active");
		
		$("#div-util-function").hide("active");
		$("#div-it-technology").hide("active");
		$("#div-main-page").show("active");
		
	});*/

	 //点击功能函数
	$("#alink-util-function").click(function(){
		$("#li-it-technology").removeClass("active");
		$("#li-util-function").addClass("active");
		
		$("#div-it-technology").hide("active");
		$("#div-util-function").show("active");
	});
 
	 //点击IT学习
	$("#alink-it-technology").click(function(){
		$("#li-util-function").removeClass("active");
		$("#li-it-technology").addClass("active");
		
		$("#div-util-function").hide("active");
		$("#div-it-technology").show("active");
	});
 });
