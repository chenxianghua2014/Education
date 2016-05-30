<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link rel="stylesheet" href="js/zTree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<style type="text/css">
.ztree li span.button.add {
	margin-left: 2px;
	margin-right: -1px;
	background-position: -144px 0;
	vertical-align: top;
	*vertical-align: middle
}
</style>
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="js/jquery/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript"
	src="js/zTree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="js/ajaxCommon.js"></script>
<script type="text/javascript">
	var zNodes = [];
	var setting = {};
	$(function() {
		setting = {
			data : {
				simpleData : {
					enable : true
				}
			}
		};
		var i = getUrlParam("i");
		var c = getUrlParam("c");
		if ('${sessionScope.sessionUser.accountCatalog}' == c && i == 0) {
		} else {
			setting.view = {};
			setting.edit = {};
			setting.callback = {};
		}
		ajaxPost("tree", "", backData);
	});
	//加载课程目录树
	function backData(msg) {
		if (msg.status != "success")
			return;
		for (var i = 0; i < msg.msgBody.length; i++) {
			zNodes.push({
				id : msg.msgBody[i].syllabusId,
				pId : msg.msgBody[i].syllabusUpperId,
				name : msg.msgBody[i].syllabusName,
				open : true,
				icon : "images/file.gif",
				iconOpen : "images/folder-closed.gif",
				iconClose : "images/folder-closed.gif",
				click : "open_list('" + msg.msgBody[i].syllabusId + "');"
			});
		}
		//console.log(msg);
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	}
	function open_list(syllabusId) {
		window.parent.document.getElementById('rightMain').src = "courseManage?syllabusId="
				+ syllabusId;
	}
</script>
</head>
<body>
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
</body>
</html>
