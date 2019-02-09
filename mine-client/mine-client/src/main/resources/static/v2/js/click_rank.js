$(document).ready(function() {
	
	 $.ajax({
	        type: "Post",
	        url: "/rank_article",
	        async:false,
	        success: function(result){
	        	
	        	if (null != result) {
 	        		for(var i=0;i<result.length;i++){
	        			$("#ul_rank").append('<li><i></i><a href="/article_dt/'+result[i].id+'" target="_blank">'+result[i].title+'</a></li>');
	        		}
	        	} 
	       	}
	     });
});