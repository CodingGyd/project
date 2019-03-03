$(document).ready(function() {
	
	var Request = new Object(); 
	Request = GetRequest(); 
	var type_dm = Request['type_dm'];
	var limit = Request['limit'];
	var html = '';
	if ((null != type_dm && '' != type_dm) || (null != limit && '' != limit )) {
		html += '<li><a href="/">首页</a></li>';
/*		html += '<li><a href="list.html">心路历程</a></li>';
*/		html += '<li><a href="/life" target="_blank">我的生活</a></li>';
		html += '<li><a href="/about" target="_blank">关于我</a></li>';
		html += '<li><a href="/labelcloud" target="_blank">标签云</a></li>';
		html += '<li><a href="/videoshare" target="_blank">视频分享</a></li>';

		 $.ajax({
		        type: "Post",
		        url: "/article_types",
		        async:false,
		        success: function(result){

		        	if (null != result) {
		        		var rootIndex;
		        		for(var i=0;i<result.length;i++){
		        			if(result[i].dm == null || result[i].dm == '') {
		        				if ((null == type_dm || '' == type_dm) && (null != limit && '' != limit )) {
			        				html +=  '<li class="menu"><a  id="selected" href="/list?page=1&amp;limit=5"  target="_blank" >'+result[i].ms+'</a><ul class="sub">';
		        				} else {
		        					html +=  '<li class="menu"><a href="/list?page=1&amp;limit=5"  target="_blank" >'+result[i].ms+'</a><ul class="sub">';
		        				}
		        				rootIndex = i;
		        				break;
		        			}
		        		}
		        		
		        		for (var i=0;i<result.length;i++) {
		        			if (i == rootIndex) continue;
		        			else {
		        				if (type_dm == result[i].dm) {
			        				html += '<li><a  id="selected"  href="/list?page=1&amp;limit=5&amp;type_dm='+result[i].dm+'"  target="_blank" >'+result[i].ms+'</a></li>';
		        				} else {
			        				html += '<li><a   href="/list?page=1&amp;limit=5&amp;type_dm='+result[i].dm+'"  target="_blank" >'+result[i].ms+'</a></li>';
		        				}
		        			}
		        		}
		        		html +=	'</ul>';
		        		html += '<span></span></li>';
		        		$('#starlist').empty();
		        		$('#starlist').append(html);
    
		        	} 
		       	}
		     });
	} else {
		 $.ajax({
		        type: "Post",
		        url: "/article_types",
		        async:false,
		        success: function(result){

		        	if (null != result) {
 		        		var rootIndex;
		        		for(var i=0;i<result.length;i++){
		        			if(result[i].dm == null || result[i].dm == '') {
		        				 
		        				html +=  '<li class="menu"><a href="/list?page=1&amp;limit=10">'+result[i].ms+'</a><ul class="sub">';
		        				rootIndex = i;
		        				break;
		        			}
		        		}
		        		
		        		for (var i=0;i<result.length;i++) {
		        			if (i == rootIndex) continue;
		        			else {
			        			html += '<li><a href="/list?page=1&amp;limit=10&amp;type_dm='+result[i].dm+'">'+result[i].ms+'</a></li>';
		        			}
		        		}
		        		
		        		html +=	'</ul>';
		        		html += '<span></span></li>';
		        		$('#starlist').append(html);
		        	} 
		       	}
		     });
	}
 
});


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
