// JavaScript Document
 $(document).ready(function(e) {
		$.ajax({  
            type: "POST",  
            url: "http://localhost:8181/mine-server/api/func/login?phone=15974154924&password=123456",  
            dataType: "jsonp",  
            success:function(data){
//            	for ( var j = 0, j < 10; j++ ) {   
            		$(".list-group").append("<a href='#' class='list-group-item'>"+data.phone+" </a>");
//            	}  
 
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
		$("#li-main-page").addClass("active");
		
		$("#div-desktop-programe-developer").hide("active");
		$("#div-website-developer").hide("active");
		$("#div-mobile-developer").hide("active");
		$("#div-util-function").hide("active");
		$("#div-main-page").show("active");
		
	});

	 //点击桌面程序
		$("#alink-desktop-programe-developer").click(function(){
			$("#li-main-page").removeClass("active");
			$("#li-mobile-developer").removeClass("active");
			$("#li-util-function").removeClass("active");
			$("#li-website-developer").removeClass("active");
			$("#li-desktop-programe-developer").addClass("active");
			
			
			$("#div-website-developer").hide("active");
			$("#div-mobile-developer").hide("active");
			$("#div-util-function").hide("active");
			$("#div-main-page").hide("active");
			$("#div-desktop-programe-developer").show("active");
		});
	
	 //点击网站开发
	$("#alink-website-developer").click(function(){
		$("#li-main-page").removeClass("active");
		$("#li-mobile-developer").removeClass("active");
		$("#li-util-function").removeClass("active");
		$("#li-desktop-programe-developer").removeClass("active");
		$("#li-website-developer").addClass("active");
		
		$("#div-mobile-developer").hide("active");
		$("#div-util-function").hide("active");
		$("#div-main-page").hide("active");
		$("#div-desktop-programe-developer").hide("active");
		$("#div-website-developer").show("active");
	});
	
	 //点击桌面程序
	$("#alink-mobile-developer").click(function(){
		$("#li-main-page").removeClass("active");
		$("#li-util-function").removeClass("active");
		$("#li-website-developer").removeClass("active");
		$("#li-desktop-programe-developer").removeClass("active");
		$("#li-mobile-developer").addClass("active");
		
		$("#div-util-function").hide("active");
		$("#div-main-page").hide("active");
		$("#div-desktop-programe-developer").hide("active");
		$("#div-website-developer").hide("active");
		$("#div-mobile-developer").show("active");
	});
	
	 //点击联系我
	$("#alink-util-function").click(function(){
		$("#li-main-page").removeClass("active");
		$("#li-mobile-developer").removeClass("active");
		$("#li-desktop-programe-developer").removeClass("active");
		$("#li-website-developer").removeClass("active");
		$("#li-util-function").addClass("active");
		
		$("#div-main-page").hide("active");
		$("#div-desktop-programe-developer").hide("active");
		$("#div-website-developer").hide("active");
		$("#div-mobile-developer").hide("active");
		$("#div-util-function").show("active");
	});
 
 });
