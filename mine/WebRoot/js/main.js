// JavaScript Document
 $(document).ready(function(e) {
	 //点击主页
	 $("#alink-main-page").click(function(){
		$("#li-desktop-programe-developer").removeClass("active");
		$("#li-website-developer").removeClass("active");
		$("#li-mobile-developer").removeClass("active");
		$("#li-contact-me").removeClass("active");
		$("#li-main-page").addClass("active");
		
		$("#div-desktop-programe-developer").hide("active");
		$("#div-website-developer").hide("active");
		$("#div-mobile-developer").hide("active");
		$("#div-contact-me").hide("active");
		$("#div-main-page").show("active");
		
	});

	 //点击桌面程序
		$("#alink-desktop-programe-developer").click(function(){
			$("#li-main-page").removeClass("active");
			$("#li-mobile-developer").removeClass("active");
			$("#li-contact-me").removeClass("active");
			$("#li-website-developer").removeClass("active");
			$("#li-desktop-programe-developer").addClass("active");
			
			
			$("#div-website-developer").hide("active");
			$("#div-mobile-developer").hide("active");
			$("#div-contact-me").hide("active");
			$("#div-main-page").hide("active");
			$("#div-desktop-programe-developer").show("active");
		});
	
	 //点击网站开发
	$("#alink-website-developer").click(function(){
		$("#li-main-page").removeClass("active");
		$("#li-mobile-developer").removeClass("active");
		$("#li-contact-me").removeClass("active");
		$("#li-desktop-programe-developer").removeClass("active");
		$("#li-website-developer").addClass("active");
		
		$("#div-mobile-developer").hide("active");
		$("#div-contact-me").hide("active");
		$("#div-main-page").hide("active");
		$("#div-desktop-programe-developer").hide("active");
		$("#div-website-developer").show("active");
	});
	
	 //点击桌面程序
	$("#alink-mobile-developer").click(function(){
		$("#li-main-page").removeClass("active");
		$("#li-contact-me").removeClass("active");
		$("#li-website-developer").removeClass("active");
		$("#li-desktop-programe-developer").removeClass("active");
		$("#li-mobile-developer").addClass("active");
		
		$("#div-contact-me").hide("active");
		$("#div-main-page").hide("active");
		$("#div-desktop-programe-developer").hide("active");
		$("#div-website-developer").hide("active");
		$("#div-mobile-developer").show("active");
	});
	
	 //点击联系我
	$("#alink-contact-me").click(function(){
		$("#li-main-page").removeClass("active");
		$("#li-mobile-developer").removeClass("active");
		$("#li-desktop-programe-developer").removeClass("active");
		$("#li-website-developer").removeClass("active");
		$("#li-contact-me").addClass("active");
		
		$("#div-main-page").hide("active");
		$("#div-desktop-programe-developer").hide("active");
		$("#div-website-developer").hide("active");
		$("#div-mobile-developer").hide("active");
		$("#div-contact-me").show("active");
	});
	
	$("#testBt").click(function(){
		$.ajax({  
            type: "GET",  
            url: "http://localhost:8181/mine-server/api/func/login?username=guoyading&password=123",  
            dataType: "jsonp",  
            success:function(data){
                alert(data.phone);
              },
              error:function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
              }});
	});
 });
