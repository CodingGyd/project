// JavaScript Document
var editor;

$(document).ready(function(e) {
  
	addListener();
     //加载文章原始数据
 	loadArticleInfo();
});
//初始化文章原始数据
function loadArticleInfo() {
	var id = getUrlVars()["id"];  
	$("#article_id").val(id);
	 $.ajax({
	       type: "Post",
	       url: "/sys/article_byid",
	       data:{"id":id},
	       async:true,
	       success: function(data){
		        	$("#editormd").val(data.content);
		        	$("#article_title").val(data.title);
		        	$("#article_descs").val(data.descs);
		        	
		        	var selectedType = data.type;
		        	//加载文章分类
		        	loadArticleType(selectedType);
		        	//初始化文章编辑器
		        	initEditor();
		        	
	       },
	       error:function(){
	    	   alert("接口調用出錯!");
	       }
	 });
}

//初始化文章编辑器
function initEditor(){
	
	editor=$(function() {
	      editormd("test-editormd", {
	           width   : "90%",
	           height  : 640,
	           //markdown : md,
	           codeFold : true,
	           syncScrolling : "single",
	           //你的lib目录的路径
	           path    : "/editormd/lib/",
	           imageUpload: true,//打开图片上传功能
	           imageUploadURL : "/sys/imgupload",
	          /*  theme: "dark",//工具栏主题
	           previewTheme: "dark",//预览主题
	           editorTheme: "pastel-on-dark",//编辑主题 */
	           emoji: true,
	           taskList: true, 
	           tocm: true,         // Using [TOCM]
	           tex: true,                   // 开启科学公式TeX语言支持，默认关闭
	           flowChart: true,             // 开启流程图支持，默认关闭
	           sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭,
	          //这个配置在simple.html中并没有，但是为了能够提交表单，使用这个配置可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单。
	           saveHTMLToTextarea : true            
	      });
	  });
	
}
function loadArticleType(selectedType) {
	 $.ajax({
       type: "Post",
       url: "/sys/article_types",
       async:false,
       success: function(data){
	        	 //遍历生成select
				$("#article_type").append("<option value='-1'>选择文章分类</option>"); //为Select追加一个Option(下拉项)

       		$(data).each(function (index, r) {
       			$("#article_type").append("<option value='"+r.dm+"'>"+r.ms+"</option>"); //为Select追加一个Option(下拉项)
       			$("#article_type").find("option[value='"+selectedType+"']").attr("selected",true);//设置默认选中
       		});
       },
       error:function(){
    	   alert("接口調用出錯!");
       }
	 });
}
//控件添加今监听器
function addListener(){
	$('#btn_submit').click(function() {
		 //获取文章ID
		  var id=$("#article_id").val();
		  //获取文章标题
		  var title=$("#article_title").val();
		  //获取文章概述
		  var descs=$("#article_descs").val();
		  //获取文章分类$
		  var type =$('#article_type option:selected').val();
		  //获取第二个textarea的值，即生成的HTML代码
		  var htmlContent=$("#editorhtml").val();
		  //获取第一个textarea的值，即md值
		   var content=$("#editormd").val();
			 $.ajax({
		         type: "POST",
		         url: "/sys/update_withcontent",
		         data:{"id":id,"title":title,"descs" :descs,"content":content,"htmlContent":htmlContent,"type":type},
		         async:true,
		         success: function(data){
		        	   	alert("更新成功");
		        	 },
		        error:function(){
		   	    	   alert("接口調用出錯!");
		   	       }
		         }); 
		});
}

//滚动到顶部
function pageScroll() { 
	$('html,body').animate({scrollTop:0},'slow');
} 

//获取request的参数
function getUrlVars() {
    var vars = [],
        hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for (var i = 0; i < hashes.length; i++) {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}