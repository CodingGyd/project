$(document).ready(function() {
//	loadDt(getRequestParam());
});

function loadDt(id){
	 $.ajax({
	        type: "Post",
	        url: "/article_dt/"+id,
	        async:false,
	        success: function(result){
	        	if (null != result ) {
	        	 alert(result.title);
	        	} 
	       	}
	     });
}

function getRequestParam(){
	var url = location.search; //获取url中"?"符后的字串  
 	 if (url.indexOf("?") != -1) {  
 	     return url.substr(url.indexOf("?")+1,url.length);  
	 }  
}
 