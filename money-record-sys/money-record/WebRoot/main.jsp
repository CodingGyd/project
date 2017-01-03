<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<link rel="icon" href="../../favicon.ico">

<title>日常开销简易登记平台</title>

<!-- Bootstrap core CSS -->
<link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="bootstrap/dashboard.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="bootstrap/signin/signin.css" rel="stylesheet">
<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="bootstrap/assets/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="javascript:void(0)">流水账登记</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="javascript:void(0)">备用链接1</a></li>
				<li><a href="javascript:void(0)">设置</a></li>
				<li><a href="javascript:void(0)">备用链接2</a></li>
				<li><a href="javascript:void(0)">帮助</a></li>
			</ul>
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="Search...">
			</form>
		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar" style="visibility: hidden;">
				<ul class="nav nav-sidebar">
					<li class="active"><a href="#">Overview</a></li>
					<li><a href="#">Reports</a></li>
					<li><a href="#">Analytics</a></li>
					<li><a href="#">Export</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="">Nav item</a></li>
					<li><a href="">Nav item again</a></li>
					<li><a href="">One more nav</a></li>
					<li><a href="">Another nav item</a></li>
					<li><a href="">More navigation</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="">Nav item again</a></li>
					<li><a href="">One more nav</a></li>
					<li><a href="">Another nav item</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" >
				
				<h2 class="sub-header">今天你记账了么!!!</h2>
				<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
				<form action="Record" method="post">
					<input name="action" type="hidden" value="queryRecord">
					<font color=red>开始日期:</font>&nbsp;&nbsp;&nbsp;<input class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" name="startDate" id="startDate"/> &nbsp;&nbsp;
					<font color=red>截止日期:</font>&nbsp;&nbsp;&nbsp;<input class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" name="endDate" id="endDate"/> 
					<input type="submit" value="查询"/>
				</form>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
<!-- 								<th>编号</th>
 -->								<th>名称</th>
								<th>价格</th>
								<th>备注</th>
								<th>日期</th>
							</tr>
						</thead>
						<tbody>
				
						<c:choose>
							<c:when test="${empty records}">
								<tr>
									<td colspan="4" align="center">没有查询到记录~</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${records}" var="record">
	
									<tr>
<%-- 										<td>${record.id}</td>
 --%>										<td>${record.name}</td>
										<td>${record.price}</td>
										<td>${record.remark}</td>
										<td>${record.spendtime}</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						

						</tbody>
					</table>
				
				</div>
			<!-- 	<div class="row placeholders" >
					<div class="col-xs-6 col-sm-3 placeholder">
						<img data-src="holder.js/200x200/auto/sky" class="img-responsive"
							alt="Generic placeholder thumbnail">
						<h4>Label</h4>
						<span class="text-muted">Something else</span>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder">
						<img data-src="holder.js/200x200/auto/vine" class="img-responsive"
							alt="Generic placeholder thumbnail">
						<h4>Label</h4>
						<span class="text-muted">Something else</span>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder">
						<img data-src="holder.js/200x200/auto/sky" class="img-responsive"
							alt="Generic placeholder thumbnail">
						<h4>Label</h4>
						<span class="text-muted">Something else</span>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder">
						<img data-src="holder.js/200x200/auto/vine" class="img-responsive"
							alt="Generic placeholder thumbnail">
						<h4>Label</h4>
						<span class="text-muted">Something else</span>
					</div>
				</div> -->
			</div>
		</div>
		 <div class="container">
					<form class="form-signin" role="form" action="Record" method="post">
					<input name="action" type="hidden" value="addRecord">
						<h2 class="form-signin-heading">新增开销</h2>
						<input name="name" type="text" class="form-control"
							placeholder="开销名称" required autofocus> <input
							name="price" type="text" class="form-control" placeholder="价格"
							required> <input name="remark" type="text"
							class="form-control" placeholder="备注" required>
						<button class="btn btn-lg btn-primary btn-block" type="submit">确认添加</button>
					</form>
		</div>
	</div>
 	<br/>
 	<br/>
 	 	<br/>
	<p align="center">
		Copyright 2016-2017 by guoyading
	</p>
	<!-- <!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="bootstrap/assets/js/docs.min.js"></script>
	IE10 viewport hack for Surface/desktop Windows 8 bug
	<script src="bootstrap/assets/js/ie10-viewport-bug-workaround.js"></script> --> 
</body>
</html>
