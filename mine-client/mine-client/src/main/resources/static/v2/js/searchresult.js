$(document).ready(function() {
	var Request = new Object(); 
	Request = GetRequest(); 
	searchcontent = decodeURIComponent(Request['input_text']);
	
	loadList(searchcontent);
});

function loadList(searchcontent){
	
	$("#search_ms").text("搜索结果");
 	 $.ajax({
	        type: "Post",
	        url: "/article_search",
	        data:{"searchcontent":searchcontent},
	        async:true,
	        success: function(data){
	        	
	        	if (null != data ){
	        		for(var i=0;i<data.length;i++){
	        			$('#ul_search_dt').append(
	        	        '<li>'+
	        	          '<h3 class="blogtitle"><a href="/article_dt/'+data[i].id+'" target="_blank">'+data[i].title+'</a></h3>'+
	        	          '<span class="blogpic imgscale"><i><a href="/article_dt/'+data[i].id+'" target="_blank">原创</a></i><a href="/article_dt/'+data[i].id+'" target="_blank" title=""><img src="v2/images/b01.jpg" alt=""/></a></span>'+
	        	          '<p class="blogtext">'+data[i].descs+'</p>'+
	        	          '<p class="bloginfo"><i class="avatar"><img src="v2/images/avatar.png"/></i><span>顺顺郭</span><span>'+data[i].updatetime+'</span></p>'+
	        	          '<a href="/article_dt/'+data[i].id+'" target="_blank" class="viewmore">阅读更多</a> </li>'
	        			 );
	        		}

	        	} 
	       	}
	     });
}

function GetRequest() { 
	var url = location.search; //获取url中"?"符后的字串 
	var theRequest = new Object(); 
	if (url.indexOf("?") != -1) { 
	var str = url.substr(1); 
	strs = str.split("&"); 
	for(var i = 0; i < strs.length; i ++) { 
	theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
	} 
	} 
	return theRequest; 
} 