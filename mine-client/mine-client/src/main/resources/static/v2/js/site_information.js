$(document).ready(function() {
	loadArticleType();
});
  
function loadArticleType(){
	 $.ajax({
	        type: "Post",
	        url: "/siteinfo",
	        async:true,
	        success: function(result){
	        	if (null != result) {
	        		$("#ul_site_info").empty();
    				$("#ul_site_info").append('<li><b>建站时间</b>：'+result.timeOfSiteCreate+'</li>');
    				$("#ul_site_info").append('<li><b>文章统计</b>：'+result.numOfArticles+'</li>');
    				$("#ul_site_info").append('<li><b>文章评论</b>：'+result.numOfComment+'</li>');
    				$("#ul_site_info").append('<li><b>我的微信</b>：扫描二维码，关注我</li>');
    				$("#ul_site_info").append('<img src="v2/images/wx.jpg" class="tongji_gzh"/>');

	        	} 
	       	}
	     });
}
 