<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
	
			
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="bootstrap/icon/favicon.ico">

<title>欢迎来到顺顺郭的地盘</title>

<!-- Bootstrap core CSS -->
<link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="bootstrap/main.css" rel="stylesheet">
<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="bootstrap/assets/js/ie-emulation-modes-warning.js"></script>
<script src="support/jquery-1.11.0.js"></script>
<script type="text/javascript" language="javascript"  charset="utf-8"  src="js/main.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
  <div class="container">

      <div class="header">
        <ul class="nav nav-pills pull-right" role="tablist">
          <li role="presentation" class="active" id="li-main-page"><a href="#" id="alink-main-page">主页</a></li>
          <li role="presentation" id="li-desktop-programe-developer"><a href="#" id="alink-desktop-programe-developer">桌面程序</a></li>
          <li role="presentation" id="li-website-developer"><a href="#" id="alink-website-developer">网站开发</a></li>
          <li role="presentation" id="li-mobile-developer"><a href="#" id="alink-mobile-developer">移动开发</a></li>
          <li role="presentation" id="li-contact-me"><a href="#" id="alink-contact-me">联系我</a></li>
        </ul>
        <h3 class="text-muted">定个小目标,先挣一个亿</h3>
<!--         <input type="button" value="测试跨域访问1" name="testBt" id="testBt"/> -->
      </div>
  	<div id="div-main-page">
      <div class="jumbotron">
       
        <p>
        	<img src="image/logo.JPG" alt="我" class="img-circle">
        </p>
         <h1>代码改变世界!</h1>
      <!--   <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
        <p><a class="btn btn-lg btn-success" href="#" role="button">Sign up today</a></p> -->
      </div>

      <div class="row marketing">
        <div class="col-lg-6">
          <h4>我是谁</h4>
          <p>大学毕业已经一年, 目前在一家互联网金融公司就职, 担任产品技术开发工程师.</p>
          
          <h4>网站开发</h4>
          <p>
          	熟悉html、javascript、jquery、css、div、ajax等网页开发技术.<br/>
 		            熟悉servlet、jsp、javabean等服务器端开发技术.<br/>
 		            熟悉Struts、Spring、Hibernate等框架技术.<br/>
		            熟悉Apache Ignite、Apache Cassandra、ElasticSearch等前沿技术.<br/>
          </p>
          
          <h4>数据库开发</h4>
          <p>熟悉MySql、Sqlserver等主流数据库开发技术.</p>
        </div>

       <div class="col-lg-6">
          <h4>我的编程语言</h4>
          <p>JAVA编程语言.</p>
          
          <h4>移动开发</h4>
          <p>
          	熟悉Android API,四大组件、五大布局、网络编程、消息机制等Android开发技术.<br/>
			了解各种开源框架的使用(用过slidingmenu、volley、androidpn、androidannotation、imageloader等第三方框架).<br/>
          </p>
 
          <h4>开发工具</h4>
          <p>熟悉Eclipse、MyEclipse、Android Studio等各种主流开发工具以及SVN、GIT团队协作工具.</p>
       </div> 
      </div>
</div>
    <div id="div-desktop-programe-developer">
        	<img src="image/project-ui/javase/tankewar.png" class="img-thumbnail" >
        	<img src="image/project-ui/javase/plane.png" class="img-thumbnail" >
        	<img src="image/project-ui/javase/mine.png" class="img-thumbnail" >
        	<img src="image/project-ui/javase/lianliankan.png" class="img-thumbnail" >
        	<img src="image/project-ui/javase/lianliankan2.png" class="img-thumbnail" >
     
    </div>
    <div id="div-website-developer">
        	<img src="image/project-ui/javaee/website_project_list.png" class="img-thumbnail" >
        	<img src="image/project-ui/javaee/login.png" class="img-rounded" >
        	<img src="image/project-ui/javaee/register.png" class="img-thumbnail" >
        	<img src="image/project-ui/javaee/mainpage.png" class="img-thumbnail" >
        	<img src="image/project-ui/javaee/selectclass.png" class="img-thumbnail" >
        	<img src="image/project-ui/javaee/hrms.png" class="img-thumbnail" >
 
    </div>
        <div id="div-mobile-developer">
        <p>
        	<img src="image/project-ui/android/books-main.png" class="img-thumbnail" >&nbsp;&nbsp;&nbsp;
        	<img src="image/project-ui/android/books-main1.png" class="img-thumbnail" >&nbsp;&nbsp;&nbsp;
           	<img src="image/project-ui/android/books-detail.png" class="img-thumbnail" >&nbsp;&nbsp;&nbsp;
            <img src="image/project-ui/android/books-buy.png" class="img-thumbnail" >&nbsp;&nbsp;&nbsp;
   		    <img src="image/project-ui/android/books-cart.png" class="img-thumbnail" >&nbsp;&nbsp;&nbsp;
   		    <img src="image/project-ui/android/books-my.png" class="img-thumbnail" >&nbsp;&nbsp;&nbsp;
   		</p>
   		      <p>
        	<img src="image/project-ui/android/music-main.png" class="img-thumbnail" >&nbsp;&nbsp;&nbsp;
        	<img src="image/project-ui/android/music-list.png" class="img-thumbnail" >&nbsp;&nbsp;&nbsp;
           	<img src="image/project-ui/android/music-online.png" class="img-thumbnail" >&nbsp;&nbsp;&nbsp;
            <img src="image/project-ui/android/music-online.png" class="img-thumbnail" >&nbsp;&nbsp;&nbsp;
   		    <img src="image/project-ui/android/music-online2.png" class="img-thumbnail" >&nbsp;&nbsp;&nbsp;
   		    <img src="image/project-ui/android/music-online3.png" class="img-thumbnail" >&nbsp;&nbsp;&nbsp;
   			<img src="image/project-ui/android/music-online4.png" class="img-thumbnail" >&nbsp;&nbsp;&nbsp;
   			<img src="image/project-ui/android/music-usermenu.png" class="img-thumbnail" >&nbsp;&nbsp;&nbsp;
   			
   		</p>
    </div>
    <div id="div-contact-me">
   
    	  <h3 class="text-muted">联系我</h3>
    	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<p>QQ: 964781872</p>
         &nbsp;&nbsp; &nbsp;<p>email: 964781872@qq.com</p><br/>
    </div>
    
    </div> <!-- /container -->
    
<!--    
footer固定在底部的实现方法
 html{height:100%;}
body{min-height:100%;margin:0;padding:0;position:relative;}

header{background-color: #ffe4c4;}
main{padding-bottom:100px;background-color: #bdb76b;}/* main的padding-bottom值要等于或大于footer的height值 */
footer{position:absolute;bottom:0;width:100%;height:100px;background-color: #ffc0cb;}
首先，设置body的高度至少充满整个屏幕，并且body作为footer绝对定位的参考节点；
其次，设置main（footer前一个兄弟元素）的padding-bottom值大于等于footer的height值，以保证main的内容能够全部显示出来而不被footer遮盖；
最后，设置footer绝对定位，并设置height为固定高度值。 -->
  <div class="footer">
        <p>&copy; Copyright 2017 by guoyading</p>
        <p>QQ: 964781872</p>
        <p>email: 964781872@qq.com</p>
      </div>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="bootstrap/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
