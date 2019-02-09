$(document).ready(function() {
	loadArticleType();
});
  
function loadArticleType(){
	 $.ajax({
	        type: "Post",
	        url: "/article_types",
	        async:false,
	        success: function(result){
	        	if (null != result) {
	    
 	        		for(var i=0;i<result.length;i++){
        				$("#ul_article_type").append('<a href="/list?page=1&amp;limit=5&amp;type_dm='+result[i].dm+'">'+result[i].ms+'</a>');
	        		}
	        	} 
	       	}
	     });
}
 