// JavaScript Document
//初始页码
$(document).ready(function(e) {
 	//加载随机文章
	loadRandomArticles(); 
});
 

//随机文章
function loadRandomArticles(){
	 $.ajax({
        type: "Post",
        url: "/daily_essays",
        async:true,
        success: function(data){
	        	// 设置模板  
	            $("#daily-list").setTemplateElement("templateDailys");  
	            // 给模板加载数据  
	            $("#daily-list").processTemplate(data);  
       	 }
        });
}
 