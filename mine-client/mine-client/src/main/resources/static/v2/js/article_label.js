$(document).ready(function() {
	loadArticleLabelList();
});
  
function loadArticleLabelList(){
	 $.ajax({
	        type: "Post",
	        url: "/keywords",
	        async:true,
	        success: function(result){

	        	if (null != result) {

 	        		for(var i=0;i<result.length;i++){
        				$("#ul_article_label").append('<a href="/list?page=1&amp;limit=5&amp;id='+result[i].id+'">'+result[i].name+'</a>');
	        		}
	        	} 
	       	}
	     });
}
 