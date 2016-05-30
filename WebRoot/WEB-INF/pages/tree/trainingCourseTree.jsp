<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=7">
<title>phpcms V9 - 后台管理中心</title>
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
function open_list(n,type,i) {
if(type == '常规培训班'){
		window.parent.document.getElementById('rightMain').src="queryClassCatalog?mark=1&organizationId="+n+"&page=1&cj="+i+"&classresourceUpname=1";
	}
	if(type == '专题培训班'){
		window.parent.document.getElementById('rightMain').src="queryClassCatalog?mark=1&organizationId="+n+"&page=1&cj="+i+"&classresourceUpname=2";
	}
	if(type == '党校培训班'){
		window.parent.document.getElementById('rightMain').src="queryClassCatalog?mark=1&organizationId="+n+"&page=1&cj="+i+"&classresourceUpname=3";
	}
	if(type == '培训班'){
		window.parent.document.getElementById('rightMain').src="queryShift?classresourceId="+n+"&mark=1&page=1&cj="+i;
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
<c:choose>
 <c:when test="${account.accountType== 1}"> 
	<ul id="category_tree" class="filetree  treeview">
		 <li id="1" class="expandable">
			<div class="hitarea expandable-hitarea"></div>
			<span class="folder" onclick="open_list('test001','${type}','1');">航天科工集团</span>
				<ul>
					<c:forEach items="${organization}" var="o">
					     <c:choose>
							<c:when test="${o.organizationSjdw == 'test001' }">
								<li id="2" class="expandable">
									<div class="hitarea expandable-hitarea"></div>
									<span class="folder" onclick="open_list('${o.organizationId}','${type}','2');">${o.organizationDwmc}</span>
										<ul>	
											<c:forEach items="${organization}" var="o1">
												<c:choose>
													<c:when test="${o1.organizationSjdw == o.organizationId }">
														<li id="3"><span class="file"><a href="javascript:void(0);" target="right" onclick="open_list('${o1.organizationId}','${type}','3');">${o1.organizationDwmc}</a></span></li>
													</c:when>
												</c:choose>
											</c:forEach>
										</ul>
								</li>
							</c:when>
						</c:choose>
					</c:forEach>
	 			</ul>
			</li>
	</ul>
  </c:when> 
</c:choose>

	<c:choose>
		<c:when test="${account.accountType== 2}">
			<ul id="category_tree" class="filetree  treeview">
				<li id="1" class="expandable">
				<%-- 	<div class="hitarea expandable-hitarea"></div> <span class="folder"
					onclick="open_list('test001','${type}','1');">航天科工集团</span> --%>
						<div class="hitarea expandable-hitarea"></div> <span class="folder">航天科工集团</span>
					<ul>
						<c:forEach items="${organization}" var="o">

							<c:choose>
								<c:when
									test="${o.organizationSjdw == 'test001' && o.organizationId == account.accountCatalog}">
									<li id="2" class="expandable">
										<div class="hitarea expandable-hitarea"></div> <span
										class="folder"
										onclick="open_list('${o.organizationId}','${type}','2');">${o.organizationDwmc}</span>
										<ul>
											<c:forEach items="${organization}" var="o1">
												<c:choose>
													<c:when test="${o1.organizationSjdw == o.organizationId }">
														<li id="3"><span class="file"><a
																href="javascript:void(0);" target="right"
																onclick="open_list('${o1.organizationId}','${type}','3');">${o1.organizationDwmc}</a></span></li>
													</c:when>
												</c:choose>
											</c:forEach>
										</ul>
									</li>
								</c:when>
							</c:choose>
						</c:forEach>
					</ul>
				</li>
			</ul>
		</c:when>
	</c:choose>
	
	<c:choose>
		<c:when test="${account.accountType== 3}">
			<ul id="category_tree" class="filetree  treeview">
				<li id="1" class="expandable">
				<%-- 	<div class="hitarea expandable-hitarea"></div> <span class="folder"
					onclick="open_list('test001','${type}','1');">航天科工集团</span> --%>
					<div class="hitarea expandable-hitarea"></div> <span class="folder">航天科工集团</span>
					<ul>
						<c:forEach items="${organization}" var="o">
							<c:choose>
								<c:when
									test="${o.organizationSjdw == 'test001' && o.organizationId == oneid.organizationSjdw}">
									<li id="2" class="expandable">
										<%-- <div class="hitarea expandable-hitarea"></div> <span
										class="folder"
										onclick="open_list('${o.organizationId}','${type}','2');">${o.organizationDwmc}</span> --%>
										<div class="hitarea expandable-hitarea"></div> <span
										class="folder" >${o.organizationDwmc}</span>
										<ul>
											<c:forEach items="${organization}" var="o1">
												<c:choose>
													<c:when
														test="${o1.organizationSjdw == o.organizationId && o1.organizationId == account.accountCatalog}">
														<li id="3"><span class="file"><a
																href="javascript:void(0);" target="right"
																onclick="open_list('${o1.organizationId}','${type}','3');">${o1.organizationDwmc}</a></span></li>
													</c:when>
												</c:choose>
											</c:forEach>
										</ul>
									</li>
								</c:when>
							</c:choose>
						</c:forEach>
					</ul>
				</li>
			</ul>
		</c:when>
	</c:choose>

	<c:choose>
		<c:when test="${account.accountType== 4}">
			<ul id="category_tree" class="filetree  treeview">
				<li id="1" class="expandable">
					<div class="hitarea expandable-hitarea"></div> <span class="folder"
					onclick="open_list('${ids}','${type}','4');">${oneid.organizationDwmc}</span>
				</li>
			</ul>
		</c:when>
	</c:choose>






</body>
</html>