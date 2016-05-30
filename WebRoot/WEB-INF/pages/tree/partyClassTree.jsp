<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link rel="stylesheet" href="js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<style type="text/css">
.ztree li span.button.add {
	margin-left:2px;
	margin-right: -1px;
	background-position:-144px 0;
	vertical-align:top;
	*vertical-align:middle
}
</style>
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="js/zTree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="js/ajaxCommon.js"></script>
<script type="text/javascript" src="js/partySchoolTree.js"></script>
<script type="text/javascript">
$(function(){
	var i = getUrlParam("i");
	var c = getUrlParam("c");
	if('${sessionScope.sessionUser.accountCatalog}'==c&&i==0){}
	else{
		setting.view={};
		setting.edit={};
		setting.callback={};
	}
	ajaxPost("partyTrainTree", "", backData);
});
</script>
</head>
<body>
<div class="zTreeDemoBackground left">
	<ul id="treeDemo" class="ztree"></ul>
</div>
</body>
</html>
