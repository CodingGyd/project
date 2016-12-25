<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>注册</title>
<link rel="stylesheet" type="text/css" href="css/register.css" />
<script src="support/jquery-1.11.0.js"></script>
<script src="js/checkRegister.js"></script>
<script src="js/login-main.js">

</script>

</head>

<body>
	<div id="left">
		<a id="alogo" href="index.jsp"> <img id="imglogo"
			src="images/logo.png" />
		</a>
		<div id="topword">在线书城会员注册</div>
	</div>

	<div id="right">
		<form action="Register" method="post">
			<br /><br /><br /><br /><br /><br /><br /><br />
			<table >
				<tr>
					<td class="label">用&nbsp;&nbsp;&nbsp;&nbsp;户:&nbsp;</td>
					<td><input class="textin" id="username" name="username"
						type="text" /></td>
					<td><div class="worning" id="usernameworning"></div></td>
				</tr>

				<tr>
					<td class="label">密&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;</td>
					<td><input class="textin" id="password" name="password"
						type="password" /></td>
					<td><div class="worning" id="passwordworning"></div></td>
				</tr>

				<tr>
					<td class="label">确认密码:&nbsp;</td>
					<td><input class="textin" id="repassword" name="repassword"
						type="password" /></td>
					<td><div class="worning" id="repasswordworning"></div></td>
				</tr>

				<tr id="remove">
					<td class="label">Tel&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;</td>
					<td><input class="textin" id="phone" name="tel" type="text" /></td>
					<td><div class="worning" id="phoneworning"></div></td>
				</tr>

				<tr>
					<td class="label">地&nbsp;&nbsp;&nbsp;&nbsp;址:&nbsp;</td>
					<td><input class="textin" id="address" name="address" type="text" /></td>
					<td><div class="worning" id="qqworning"></div></td>
				</tr>

				<tr>
					<td></td>
					<td align="center"><input id="submit" name="imgcode"
						type="submit" value="注册" /></td>
					<td></td>
				</tr>
			</table>
		</form>
		<!--版权区-->
		<!--div id="bottom">------版权信息&copy CopyRight------</div-->
	</div>

</body>
</html>

