<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=7">
<title></title>
<link href="css/reset.css" rel="stylesheet" type="text/css">
<link href="css/zh-cn-system.css" rel="stylesheet" type="text/css">
<link href="css/table_form.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="css/style/zh-cn-styles1.css" title="styles1" media="screen">
<link rel="alternate stylesheet" type="text/css" href="css/style/zh-cn-styles2.css" title="styles2" media="screen">
<link rel="alternate stylesheet" type="text/css" href="css/style/zh-cn-styles3.css" title="styles3" media="screen">
<link rel="alternate stylesheet" type="text/css" href="css/style/zh-cn-styles4.css" title="styles4" media="screen">
<script language="javascript" type="text/javascript" src="js/jquery.min.js"></script>
<script language="javascript" type="text/javascript" src="js/admin_common.js"></script>
<script language="javascript" type="text/javascript" src="js/styleswitch.js"></script>
<style type="text/css">
	html{_overflow-y:scroll}
</style>
<link rel="stylesheet" href="css/jquery.treeview.css" type="text/css">
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="js/jquery.treeview.js"></script>
<script language="JavaScript">

$(document).ready(function(){
    $("#category_tree").treeview({
			control: "#treecontrol",
			persist: "cookie",
			cookieId: "treeview-black"
	});
});
function open_list(n,y) {
	if(n=='1'){
		window.parent.frames["rightMain"].src="accountAll?page=1&mark=1";
	}
	if(n=='2'){
		window.parent.frames["rightMain"].src="accountTree";
	//window.parent.frames["rightMain"].src="organizationAll?page=1&mark=1";
	}
}

</script>
<style type="text/css">
	.filetree *{white-space:nowrap;}
	.filetree span.folder, .filetree span.file{display:auto;padding:1px 0 1px 16px;}
</style>
</head>
<body>


<!-- <ul class="filetree  treeview">
	<li class="collapsable">
	<div class="hitarea collapsable-hitarea"></div>
	<span><img src="images/icon/box-exclaim.gif" width="15" height="14">&nbsp;<a href="" target="right">审核内容</a></span>
	</li>
</ul> -->

<ul id="category_tree" class="filetree  treeview">
	<li id="1" class="collapsable">
		<div class="hitarea collapsable-hitarea"></div>
		<span class="folder">系统管理</span>
		<ul>
			<li id="2"><span class="file"><a href="javascript:void(0);" target="right" onclick="open_list(1);">账户管理</a></span></li>
			<c:if test="${sessionScope.sessionUser.accountType==1 || sessionScope.sessionUser.accountType==2 || sessionScope.sessionUser.accountType==5}">
			<li id="2"><span class="file"><a href="javascript:void(0);" target="right" onclick="open_list(2);">组织架构</a></span></li>
			</c:if>
		</ul>
	</li>
</ul>
</body>
</html>