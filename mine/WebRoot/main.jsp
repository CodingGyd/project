<%@ page language="java" import="java.util.*,java.io.*"
	pageEncoding="utf-8"%>
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
<script type="text/javascript" language="javascript" charset="utf-8"
	src="js/main.js"></script>
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
				<li role="presentation" class="active" id="li-util-function"><a href="#"
					id="alink-util-function">工具函数</a></li>
				<li role="presentation" id="li-it-technology"><a href="#"
					id="alink-it-technology">IT学习</a></li>
			</ul>
			<h3 class="text-muted">定个小目标,先挣一个亿</h3>
 		</div>
		<div id="div-util-function">
			<div class="util-function-list-group">
			</div>
		</div>
		<div id="div-it-technology"  style="display: none;">
			<div class="it-technology-list-group">
			</div>
		</div>
	</div>
	<!-- /container -->

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
