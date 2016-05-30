<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="<%=path%>/css/skins/default/main.css" rel="stylesheet" type="text/css" />
<!-- <link rel="stylesheet" href="resources/pagination/pagination.css" type="text/css"></link> -->
<script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.9.1.min.js"></script>
<!-- <script type="text/javascript" src="js/common.js"></script> -->

<!-- 日期 -->
<script type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
<!-- 分页 -->
<script type="text/javascript" src="<%=path%>/css/pager/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/css/pager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/pager/kkpager_blue.css" />
<script type="text/javascript"> 
	function Addnew() {
		window.location = "AccountskipID?id=0";
	}
	
 	 function deleteProgramme(id) {
	if (confirm("你确定要删除该条记录吗?")) {
	$.ajax({
			url : "deleteAccount",
			type : 'GET',
			data : {
				id: id
			},
			success : function(data){
				if(data=="ok"){
					alert("删除成功");
					window.location.reload();
				}else{
					alert("删除失败");
					window.location.reload();
				}
			}
		  });
}  
 	 }
function editNew(newsId,id) {
	window.location = "AccountskipID?id=" + newsId+"&oid="+id;
}
function newsOnes(newsId) {
	window.location = "queryProgramme?id=" + newsId;
}
function release(id){
	if (confirm("重置密码为：‘123456’ ，你确定要重置吗?")) {
	$.ajax({
			url : "passwordAccount",
			type : 'GET',
			data : {
				id: id
			},
			success : function(data){
				if(data=="ok"){
					alert("重置成功");
					window.location.reload();
				}else{
					alert("重置失败");
					window.location.reload();
				}
			}
		  });
}  
 } 
function loadData() {
	$.ajax({
		type : 'POST',
		url : "newsDataCount",
		data : {
			/* userName : $("#userName").val(), */
			newsCatalog : $("#newsCatalog").val(),
			newsRank : $("#newsRank").val(),
			zhuanNewsTime : $("#zhuanNewsTime").val()
		},
		dataType : "json",
		success : function(count) {
			dataCount = count;
			var optInit = getOptionsFromForm();
			$("#Pagination").pagination(dataCount, optInit);
			$("#setoptions").click(function() {
				var opt = getOptionsFromForm();
				$("#Pagination").pagination(dataCount, opt);
			});
		}
	});
}

/*  	function saveWeddingphoto() {
		document.ff.action = "saveWeddingphoto";
		document.ff.method = "post";
		document.ff.submit();
	}  */
</script>
</head>
<body>
	<div class="title">当前位置:系统管理>账号管理</div>
	<div class="editBlock">
		
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">账号管理</td>
				</tr>
			</tbody>
		</table>
	    
	</div>
	<div class="dataGrid">
		<table>
			<caption>本单位账号查询结果</caption>
			<thead>
				<tr>
				    <!-- <th>序      号</th> -->
					<th>账号</th>
					<th>所属目录</th>
					<th>管理级别</th>
					<th>联系人</th>
					<th>身份证</th>
					<th>联系电话</th>
					<th width="60" class="alignCenter">密码重置</th>
					<th width="60" class="alignCenter">修改</th>
					 <th width="60" class="alignCenter">删除</th>  
				</tr>
			</thead>
			<tbody id="">
			    <c:forEach items="${list}" var="maps" varStatus="status">
			    <c:if test="${maps.accountType!=4}">
				<tr>
					<td>${maps.accountLoginid}</td>
					<td>${maps.organization.organizationDwmc}</td>
					<td>
					<c:if test="${maps.accountType==1}">集团级</c:if>
					<c:if test="${maps.accountType==2}">院一级</c:if>
					<c:if test="${maps.accountType==3}">院二级</c:if>
					<c:if test="${maps.accountType==4}">培训机构</c:if>
					<c:if test="${maps.accountType==5}">系统管理</c:if>
					
					</td>
					<td>${maps.accountName}</td>
					<td>${maps.accountSfzh}</td>
					<td>${maps.accountPhone}</td>
						
					<td class='alignCenter'><input name='button'type='button' onclick="release('${maps.accountId}')" class='inputButton' value='密码重置' /></td>	
					<td class='alignCenter'><input name='button'type='button' onclick="editNew('${maps.accountId}','${id}')" class='inputButton' value='编辑' /></td>
					<c:if test="${sessionScope.sessionUser.accountType==1 && maps.accountCatalog != 'test001'}">
					 <td class='alignCenter'><input name='button'type='button' onclick="deleteProgramme('${maps.accountId}')" class='inputButton' value='删除' /></td>
					</c:if>
					
					 
				</tr>
				</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="dataGrid">
		<table>
			<caption>下属单位账号查询结果</caption>
			<thead>
				<tr>
				    <!-- <th>序      号</th> -->
					<th>账号</th>
					<th>所属目录</th>
					<th>管理级别</th>
					<th>联系人</th>
					<th>身份证</th>
					<th>联系电话</th>
					<th width="60" class="alignCenter">密码重置</th>
					<th width="60" class="alignCenter">修改</th>
					 <th width="60" class="alignCenter">删除</th>  
				</tr>
			</thead>
			<tbody id="">
			    <c:forEach items="${sjlist}" var="maps" varStatus="status">
				<tr>
					<td>${maps.accountLoginid}</td>
					<td>${maps.organization.organizationDwmc}</td>
					<td>
					<c:if test="${maps.accountType==1}">集团级</c:if>
					<c:if test="${maps.accountType==2}">院一级</c:if>
					<c:if test="${maps.accountType==3}">院二级</c:if>
					<c:if test="${maps.accountType==4}">培训机构</c:if>
					<c:if test="${maps.accountType==5}">系统管理</c:if>
					
					</td>
					<td>${maps.accountName}</td>
					<td>${maps.accountSfzh}</td>
					<td>${maps.accountPhone}</td>
						
					<td class='alignCenter'><input name='button'type='button' onclick="release('${maps.accountId}')" class='inputButton' value='密码重置' /></td>	
					<td class='alignCenter'><input name='button'type='button' onclick="editNew('${maps.accountId}','${id}')" class='inputButton' value='编辑' /></td>
					<c:if test="${sessionScope.sessionUser.accountType==1}">
					 <td class='alignCenter'><input name='button'type='button' onclick="deleteProgramme('${maps.accountId}')" class='inputButton' value='删除' /></td>
					</c:if>
					
					 
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="dataGrid">
		<table>
			<caption>本单位项目专员账号查询结果</caption>
			<thead>
				<tr>
				    <!-- <th>序      号</th> -->
					<th>账号</th>
					<th>所属目录</th>
					<th>管理级别</th>
					<th>联系人</th>
					<th>身份证</th>
					<th>联系电话</th>
					<th width="60" class="alignCenter">密码重置</th>
					<th width="60" class="alignCenter">修改</th>
					 <th width="60" class="alignCenter">删除</th>  
				</tr>
			</thead>
			<tbody id="">
			    <c:forEach items="${list}" var="maps" varStatus="status">
			    <c:if test="${maps.accountType==4}">
				<tr>
					<td>${maps.accountLoginid}</td>
					<td>${maps.organization.organizationDwmc}</td>
					<td>
					<c:if test="${maps.accountType==1}">集团级</c:if>
					<c:if test="${maps.accountType==2}">院一级</c:if>
					<c:if test="${maps.accountType==3}">院二级</c:if>
					<c:if test="${maps.accountType==4}">培训机构</c:if>
					<c:if test="${maps.accountType==5}">系统管理</c:if>
					
					</td>
					<td>${maps.accountName}</td>
					<td>${maps.accountSfzh}</td>
					<td>${maps.accountPhone}</td>
						
					<td class='alignCenter'><input name='button'type='button' onclick="release('${maps.accountId}')" class='inputButton' value='密码重置' /></td>	
					<td class='alignCenter'><input name='button'type='button' onclick="editNew('${maps.accountId}','${id}')" class='inputButton' value='编辑' /></td>
					 <td class='alignCenter'><input name='button'type='button' onclick="deleteProgramme('${maps.accountId}')" class='inputButton' value='删除' /></td>
					
					 
				</tr>
				</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	
</body>

</html>
