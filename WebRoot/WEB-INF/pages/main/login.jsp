<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>中国航天科工集团公司</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" href="css/style.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<style type="text/css">
html {
	overflow: hidden;
	height: 200px;
	width: 223px;
}

body {
	height: 200px;
	width: 233px;
	min-width: 223px;
}

.loginLength {
	width: 153px;
}
</style>
<script type="text/javascript">
	//登录功能提交
	function Login() {
		if ($("#studentSeq").val() == "") {
			$("#studentSeq").focus();
			$("#result").html("请输入用户名!");
			return;
		}
		if ($("#studentPassword").val() == "") {
			$("#studentPassword").focus();
			$("#result").html("请输入密码!");
			return;
		}
		document.forms[0].submit();
	}

	function userUpd(userId) {
		window.parent.location = "register?userId=" + userId;
	}
</script>
</head>
<body>
	<form action="StudentCheckLogin" method="post">
		<div id="loginForm" class="loginForm">
			<c:choose>
				<c:when test="${Student eq null}">
					<table style=" width:230px; margin-left:30px;">
						<tr>
							<td>用户名：</td>
							<td><input type="text" name="studentSeq" id="studentSeq"
								class="loginLength" /></td>
						</tr>
						<tr>
							<td>密&nbsp;码：</td>
							<td><input type="password" name="studentPassword"
								id="studentPassword" class="loginLength" /></td>
						</tr>
						<tr>
							<td>验证码：</td>
							<td><input type="text" id="code" name="code"
								style="width:50px;"></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><img id='code_img'
								onclick='this.src=this.src+"?"+Math.random()' src='image.jsp' /><br/><a href="javascript:;">看不清？换一个</a>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="font-size: 14px;color: red;" id="result">${result.message}</td>
						</tr>
						<tr>
							<td colspan="2"><a href="javascript:void(0);"
								onclick="Login();"><span>登录</span> </a></td>
						</tr>
					</table>
					<div colspan="2"
						style=" width:280px; height:25px; background-color:#eee; border-top:1px solid #ccc;"></div>
				</c:when>
				<c:otherwise>
					<script type="text/javascript">
						if (window.parent.window.$.jBox != undefined) {
							window.parent.window.$.jBox.close();
							window.parent.window.location.reload();
						}
					</script>
				</c:otherwise>
			</c:choose>
		</div>
	</form>
</body>
</html>