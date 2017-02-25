<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>登录</title>
<link href="site.css" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body>
	<p>用户登录</p>
	<p style="color:red">${LOGIN_ERROR}</p>
	<form action="login.mvc" method="post" name="form1" id="form1">
		<table border="1">
			<tr>
				<td>用戶名</td>
				<td><input type="text" name="username" id="username" />
				</td>
			</tr>
			<tr>
				<td>密碼</td>
				<td><input type="password" name="password" id="passwd" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="登录"
					name="btnSubmit" id="btnSubmit" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
