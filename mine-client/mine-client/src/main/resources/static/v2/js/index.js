$(document).ready(function() {
	loadLatestArticles();
	loadArticleTypes();
	addListener();
});

 
function loadLatestArticles(){

	 $.ajax({
        type: "Post",
        url: "/latest_article",
        async:true,
        success: function(result){
        	if (null != result && null != result.data) {
        		var data = result.data;
        		for(var i=0;i<data.length;i++){
        			$('#ul_latest_article').append(
        	        '<li>'+
        	          '<h3 class="blogtitle"><a href="/article_dt/'+data[i].id+'" target="_blank" >'+data[i].title+'</a></h3>'+
        	          '<span class="blogpic imgscale"><i><a href="/article_dt/'+data[i].id+'" target="_blank"  >原创</a></i><a href="/article_dt/'+data[i].id+'" target="_blank"  title=""><img src="v2/images/b01.jpg" alt=""/></a></span>'+
        	          '<p class="blogtext">'+data[i].descs+'</p>'+
        	          '<p class="bloginfo"><i class="avatar"><img src="v2/images/avatar.png"/></i><span>顺顺郭</span><span>'+data[i].updatetime+'</span></p>'+
        	          '<a href="/article_dt/'+data[i].id+'" target="_blank" class="viewmore">阅读更多</a> </li>'
        			 );
        		}
        	} 
       	}
     });
}

function loadArticleTypes(){
	 $.ajax({
	        type: "Post",
	        url: "/article_types",
	        async:false,
	        success: function(result){
	        	if (null != result) {
	    
 	        		for(var i=0;i<result.length;i++){
 	        			if (i == 0) {
 	        				alert("-");
 	        				$('#ul_article_types').append(
 	        						'<li class="newscurrent" value='+result[0].dm+'>'+result[0].ms+'</li>'
 	        				);
 	        			} else {
 	        				$('#ul_article_types').append(
 	        						'<li value='+result[i].dm+'>'+result[i].ms+'</li>'
 	        				);
 	        			}
 	           		
	        			$('#dv_article_types').append(
	    	         	      '<div class="newsitem">'+
	  	        	         '<div class="newspic">'+
	  	        	            '<ul>'+
	  	        	              '<li><img src="v2/images/2.jpg"/><span>个人博客，属于我的小世界！</span></li>'+
	  	        	              '<li><img src="v2/images/4.jpg"/><span>个人网站做好需要注意很多细节</span></li>'+
	  	        	            '</ul>'+
	  	        	          '</div>'+
	  	        	          '<ul class="newslist">'+
	  	        	          '</ul>'+
	  	        	        '</div>' 	
	        			);
	        		}
	        	} 
	       	}
	     });
}
 
function addListener(){
	  //tab
    $('#ul_article_types li').click(function() {
        $(this).addClass('newscurrent').siblings().removeClass('newscurrent');
        $('.newstab>div:eq(' + $(this).index() + ')').show().siblings().hide();
        $('.newstab div[style="display: block;"]').children(".newslist").empty();
        loadList(1,5,$(this).val());
        $("#article_more").attr("href","/list?page=1&limit=5&type_dm="+$(this).val());
    });
}

function loadList(page,limit,type_dm){
 	 $.ajax({
	        type: "Post",
	        url: "/article_page?page="+page+"&limit="+limit+"&type_dm="+type_dm,
	        async:true,
	        success: function(result){
	        
	        	if (null != result && null != result.data) {
	        		var data = result.data;
	        		for(var i=0;i<data.length;i++){
	        			$('.newstab div[style="display: block;"]').children(".newslist").append(
	        				'<li><i></i><a href="/article_dt/'+data[i].id+'" target="_blank">'+data[i].title+'</a>'+
	  	        	        '<p>'+data[i].descs+'</p>'+
	  	        	        '</li>'  
	        			 );
	        		}
	        	} 
	       	}
	     });
}