$(document).ready(function() {
	loadLatestArticles();
	
	addListener();
});

//最新文章
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
        	          '<h3 class="blogtitle"><a href="/" target="_blank">'+data[i].title+'</a></h3>'+
        	          '<span class="blogpic imgscale"><i><a href="/">原创</a></i><a href="/" title=""><img src="v2/images/b01.jpg" alt=""/></a></span>'+
        	          '<p class="blogtext">'+data[i].descs+'</p>'+
        	          '<p class="bloginfo"><i class="avatar"><img src="v2/images/avatar.png"/></i><span>顺顺郭</span><span>'+data[i].updatetime+'</span></p>'+
        	          '<a href="/article_dt/'+data[i].id+'" target="_blank" class="viewmore">阅读更多</a> </li>'
        	          
        	         /* <!--多图模式 置顶设计-->
        	        <li>
        	          <h3 class="blogtitle"><a href="/" target="_blank"><b>【顶】</b>别让这些闹心的套路，毁了你的网页设计!</a></h3>
        	          <span class="bplist"><a href="/"> <img src="v2/images/b02.jpg" alt=""/></a> <a href="/"><img src="v2/images/b03.jpg" alt=""/></a> <a href="/"><img src="v2/images/b04.jpg" alt=""/> </a><a href="/"><img src="v2/images/b05.jpg" alt=""/> </a></span>
        	          <p class="blogtext">如图，要实现上图效果，我采用如下方法：1、首先在数据库模型，增加字段，分别是图片2，图片3。2、增加标签模板，用if，else if 来判断，输出。思路已打开，样式调用就可以多样化啦！... </p>
        	          <p class="bloginfo"><i class="avatar"><img src="v2/images/avatar.png"/></i><span>顺顺郭</span><span>2018-10-28</span></p>
        	        </li>
        	        <!--单图-->
        	        <li>
        	          <h3 class="blogtitle"><a href="/" target="_blank">【个人博客网站制作】自己不会个人博客网站制作，你会选择用什么博客程序源码？</a></h3>
        	          <span class="blogpic imgscale"><i><a href="/">原创模板</a></i><a href="/" title=""><img src="v2/images/b01.jpg" alt=""/></a></span>
        	          <p class="blogtext">这些开源的博客程序源码，都是经过很多次版本测试的，都有固定的使用人群。我所知道的主流的博客程序有，Z-blog，Emlog，WordPress，Typecho等，免费的cms系统有，织梦cms（dedecms），phpcms，帝国cms（EmpireCMS）！... </p>
        	          <p class="bloginfo"><i class="avatar"><img src="v2/images/avatar.png"/></i><span>顺顺郭</span><span>2018-10-28</span></p>
        	          <a href="/info" target="_blank" class="viewmore">阅读更多</a> </li>*/
        			 );
        		}
        	} 
       	}
     });
}

//监听器
function addListener(){
	
}