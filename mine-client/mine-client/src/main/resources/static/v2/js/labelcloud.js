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
        				$("#ul_article_label")
        				.append('<a target="_blank" href="/label_dt?page=1&amp;limit=10&amp;label_dm='+result[i].id+'&amp;label_ms='+result[i].name+'">'+result[i].name+'</a>');
 	        		}
	        	} 
	       	}
	     });
}
 