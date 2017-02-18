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
	 //点击主页
	 $("#alink-main-page").click(function(){
		$("#li-desktop-programe-developer").removeClass("active");
		$("#li-website-developer").removeClass("active");
		$("#li-mobile-developer").removeClass("active");
		$("#li-util-function").removeClass("active");
		$("#li-it-technology").removeClass("active");
		$("#li-main-page").addClass("active");
		
		$("#div-desktop-programe-developer").hide("active");
		$("#div-website-developer").hide("active");
		$("#div-mobile-developer").hide("active");
		$("#div-util-function").hide("active");
		$("#div-it-technology").hide("active");
		$("#div-main-page").show("active");
		
	});

	 //点击桌面程序
		$("#alink-desktop-programe-developer").click(function(){
			$("#li-main-page").removeClass("active");
			$("#li-mobile-developer").removeClass("active");
			$("#li-util-function").removeClass("active");
			$("#li-website-developer").removeClass("active");
			$("#li-it-technology").removeClass("active");
			$("#li-desktop-programe-developer").addClass("active");
			
			
			$("#div-website-developer").hide("active");
			$("#div-mobile-developer").hide("active");
			$("#div-util-function").hide("active");
			$("#div-main-page").hide("active");
			$("#div-it-technology").hide("active");
			$("#div-desktop-programe-developer").show("active");
		});
	
	 //点击网站开发
	$("#alink-website-developer").click(function(){
		$("#li-main-page").removeClass("active");
		$("#li-mobile-developer").removeClass("active");
		$("#li-util-function").removeClass("active");
		$("#li-desktop-programe-developer").removeClass("active");
		$("#li-it-technology").removeClass("active");
		$("#li-website-developer").addClass("active");
		
		$("#div-mobile-developer").hide("active");
		$("#div-util-function").hide("active");
		$("#div-main-page").hide("active");
		$("#div-desktop-programe-developer").hide("active");
		$("#div-it-technology").hide("active");
		$("#div-website-developer").show("active");
	});
	
	 //点击移动开发
	$("#alink-mobile-developer").click(function(){
		$("#li-main-page").removeClass("active");
		$("#li-util-function").removeClass("active");
		$("#li-website-developer").removeClass("active");
		$("#li-desktop-programe-developer").removeClass("active");
		$("#li-it-technology").removeClass("active");
		$("#li-mobile-developer").addClass("active");
		
		$("#div-util-function").hide("active");
		$("#div-main-page").hide("active");
		$("#div-desktop-programe-developer").hide("active");
		$("#div-website-developer").hide("active");
		$("#div-it-technology").hide("active");
		$("#div-mobile-developer").show("active");
	});
	
	 //点击功能函数
	$("#alink-util-function").click(function(){
		$("#li-main-page").removeClass("active");
		$("#li-mobile-developer").removeClass("active");
		$("#li-desktop-programe-developer").removeClass("active");
		$("#li-website-developer").removeClass("active");
		$("#li-it-technology").removeClass("active");
		$("#li-util-function").addClass("active");
		
		$("#div-main-page").hide("active");
		$("#div-desktop-programe-developer").hide("active");
		$("#div-website-developer").hide("active");
		$("#div-mobile-developer").hide("active");
		$("#div-it-technology").hide("active");
		$("#div-util-function").show("active");
	});
 
	 //点击IT资讯
	$("#alink-it-technology").click(function(){
		$("#li-main-page").removeClass("active");
		$("#li-mobile-developer").removeClass("active");
		$("#li-desktop-programe-developer").removeClass("active");
		$("#li-website-developer").removeClass("active");
		$("#li-util-function").removeClass("active");
		$("#li-it-technology").addClass("active");
		
		$("#div-main-page").hide("active");
		$("#div-desktop-programe-developer").hide("active");
		$("#div-website-developer").hide("active");
		$("#div-mobile-developer").hide("active");
		$("#div-util-function").hide("active");
		$("#div-it-technology").show("active");
	});
 });
