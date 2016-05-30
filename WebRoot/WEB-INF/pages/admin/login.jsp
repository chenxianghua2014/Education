<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>管理员登录</title>
<style type="text/css">
div {
	overflow: hidden;
	*display: inline-block;
}

div {
	*display: block;
}

.login_box {
	background: url(images/login_bg.jpg) no-repeat;
	width: 602px;
	height: 416px;
	overflow: hidden;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -301px;
	margin-top: -208px;
}

.login_iptbox {
	bottom: 90px;
	_bottom: 72px;
	color: #FFFFFF;
	font-size: 12px;
	height: 30px;
	left: 50%;
	margin-left: -280px;
	position: absolute;
	width: 560px;
	overflow: visible;
}

.login_iptbox .ipt {
	height: 24px;
	width: 110px;
	margin-right: 22px;
	color: #fff;
	background: url(images/ipt_bg.jpg) repeat-x;
	*line-height: 24px;
	border: none;
	color: #000;
	overflow: hidden;
}

.login_iptbox label {
	*position: relative;
	*top: -6px;
}

.login_iptbox .ipt_reg {
	width: 45px;
	background: url(images/ipt_bg.jpg) repeat-x;
	*overflow: hidden;
	text-align: left;
	padding: 2px 0 2px 5px;
	font-size: 16px;
	font-weight: bold;
}

.login_tj_btn {
	background: url(images/login_dl_btn.jpg) no-repeat 0px 0px;
	width: 52px;
	height: 24px;
	margin-left: 16px;
	border: none;
	cursor: pointer;
	padding: 0px;
	float: right;
}

.yzm {
	position: absolute;
	background: url(images/login_ts140x89.gif) no-repeat;
	width: 140px;
	height: 89px;
	right: 56px;
	top: -96px;
	text-align: center;
	font-size: 12px;
	display: none;
}

.yzm a:link,.yzm a:visited {
	color: #036;
	text-decoration: none;
}

.yzm a:hover {
	color: #C30;
}

.yzm img {
	cursor: pointer;
	margin: 4px auto 7px;
	width: 130px;
	height: 50px;
	border: 1px solid #fff;
}

.cr {
	font-size: 12px;
	font-style: inherit;
	text-align: center;
	color: #ccc;
	width: 100%;
	position: absolute;
	bottom: 58px;
}

.cr a {
	color: #ccc;
	text-decoration: none;
}
</style>
<script language="javascript" type="text/javascript"
	src="js/jquery/jquery-1.9.1.min.js"></script>
<script language="JavaScript">
	if (top != self)
		if (self != top)
			top.location = self.location;
</script>
<script language="javascript">
	document.onkeydown = function(event) {
		e = event ? event : (window.event ? window.event : null);
		if (e.keyCode == 13) {
			checkLogin();
		}
	};
	function checkLogin() {
		var accountLoginid = $('#accountLoginid').val();
		var accountPassword = $('#accountPassword').val();
		var code = $('#code').val();
		if (accountLoginid != " " && accountLoginid != "") {
			if (accountPassword != " " && accountPassword != "") {
				$.ajax({
					url : "loginForm",
					type : "post",
					data : {
						accountLoginid : accountLoginid,
						accountPassword : accountPassword,
						code : code
					},
					success : function(msg) {
						//alert(msg.status);
						if ("error" == msg.status) {
							alert(msg.msgBody);
							location.reload();
						} else {
							window.location.href = "index";
						}
					}
				});
			} else {
				alert("密码不能为空！");
			}
		} else {
			alert("用户名不能为空！");
		}

	}
</script>
</head>

<body onload="javascript:document.myform.accountLoginid.focus();">
	<div id="login_bg" class="login_box">
		<div class="login_iptbox">
			<form action="loginForm" method="post" name="myform">
				<input name="dosubmit" value="" type="button"
					onclick="checkLogin();" class="login_tj_btn" /><label>用户名：</label>
				<input id="accountLoginid" name="accountLoginid" type="text"
					class="ipt" value="" /><label>密码：</label> <input
					id="accountPassword" name="accountPassword" type="password"
					class="ipt" value="" /><label>验证码：</label> <input id="code"
					name="code" type="text" class="ipt ipt_reg"
					onfocus="document.getElementById('yzm').style.display='block'" />
				<div id="yzm" class="yzm">
					<img id='code_img' onclick='this.src=this.src+"?"+Math.random()'
						src='image.jsp' /> <span style="color:black;">单击更换验证码</span>
				</div>
			</form>
		</div>
		<div class="cr">中国航天科工集团公司 地址：北京市海淀区阜成路甲8号航天科工大厦 邮编：100048</div>
	</div>
</body>
</html>