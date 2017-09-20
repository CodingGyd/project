// JavaScript Document
var editor;

$(document).ready(function(e) {
	//初始化文章编辑器
	initEditor();
	addListener();
});

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
	           path    : "editormd/lib/",
	           imageUpload: false,//关闭图片上传功能
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

//控件添加今监听器
function addListener(){
	$('#btn_submit').click(function() {
		
		  //获取文章标题
		  var title=$("#article_title").val();
		  //获取文章概述
		  var descs=$("#article_descs").val();
		  //获取第二个textarea的值，即生成的HTML代码
		  var htmlContent=$("#editorhtml").val();
		  //获取第一个textarea的值，即md值
		   var content=$("#editormd").val();
		  
			 $.ajax({
		         type: "POST",
		         url: "/update",
		         data:{"title":title,"descs" :descs,"content":content,"htmlContent":htmlContent},
		         async:true,
		         success: function(data){
		        	   	alert("发表成功");
			      	 //重置
		      		  $("#editorhtml").val("");
		      		  //重置
		      		  $("#editormd").val("");
		        	 }
		         }); 
		});
}

//滚动到顶部
function pageScroll() { 
	$('html,body').animate({scrollTop:0},'slow');
} 
