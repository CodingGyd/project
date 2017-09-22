// JavaScript Document
//初始页码
var initPage = 1;
//初始分页大小
var initLimit = 5;
//当前选择的文章分类
var type_dm;
$(document).ready(function(e) {
	loadArticleType();
	loadArticle(initPage,initLimit);
	addListener();
});

//查询分类下的文章列表
function showArticleListOfType(type){
	 type_dm = type;
	 loadArticle(initPage,initLimit,type_dm);
}

//加载文章分类
function loadArticleType() {
	 $.ajax({
         type: "Post",
         url: "/article_types",
         async:true,
         success: function(data){
	        	// 设置模板  
	            $("#resultTypeList").setTemplateElement("templateType");  
	            // 给模板加载数据  
	            $("#resultTypeList").processTemplate(data);  
        	 }
         });
}

//分页加载文章列表
function loadArticle(page,limit,type_dm) {
	 $.ajax({
         type: "Post",
         url: "/article_page",
         dataType: "json",
         data:{"page":page,"limit" :limit,"type_dm" : type_dm},
         async:true,
         success: function(data){
        	 
	        	// 设置模板  
	            $("#result").setTemplateElement("template");  
	            // 给模板加载数据  
	            $("#result").processTemplate(data);  
             
        	 	var page = data.paginator.page;
        	 	var limit = data.paginator.limit;
        	 	var totalPages = data.paginator.totalPages;
        	 	initPager(page,limit,totalPages);
        	 	
        	 	 // 滚动到顶部
	            pageScroll();
        	 }
         });
}

//初始化分页插件
function initPager( page, limit, totalPages) {
	
	var options = {

		bootstrapMajorVersion : 3, // 版本

		currentPage : page, // 当前页数

		totalPages : totalPages, // 总页数

		numberOfPages : totalPages,

		itemTexts : function(type, page, current) {
			switch (type) {

			case "first":

				return "首页";

			case "prev":

				return "上一页";

			case "next":

				return "下一页";

			case "last":

				return "末页";

			case "page":

				return page;

			}

		},// 点击事件，用于通过Ajax来刷新整个list列表

		onPageClicked : function(event, originalEvent, type, page) {
			$.ajax({

				url : "/article_page",

				type : "Post",

				dataType : "json",

				data : {"page":page,"limit" :limit,"type_dm" : type_dm},

				success : function(data) {
				 	// 设置模板  
		            $("#result").setTemplateElement("template");  
		            // 给模板加载数据  
		            $("#result").processTemplate(data);  
		            // 滚动到顶部
		            pageScroll();
				}

			});

		}

	};

	$('#pageUl').bootstrapPaginator(options);

}

//滚动到顶部
function pageScroll() { 
	$('html,body').animate({scrollTop:0},'fast');
} 