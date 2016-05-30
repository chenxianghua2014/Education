<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/skins/default/main.css" rel="stylesheet" type="text/css" />
<!-- <link rel="stylesheet" href="resources/pagination/pagination.css" type="text/css"></link> -->
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<!-- <script type="text/javascript" src="js/common.js"></script> -->

<script type="text/javascript" src="css/pager/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="css/pager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/pager/kkpager_blue.css" />

<!-- 下拉日期 -->
<script type="text/javascript" charset="utf-8" src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function teacherAdd(cj) {
		
		window.location = "teacherJump?teacherId=0&cj="+cj;
	}
	function updateTeacher(teacherId) {
	
		window.location = "teacherJump?teacherId=" + teacherId+"&mark=1&cj="+${cj};
	}
	 function deleteTeacher(teacherId) {
	if (confirm("你确定要删除该条记录吗?")) {	
	$.ajax({
			url : "deleteTeacher",
			type : 'GET',
			data : {
				teacherId : teacherId
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
 
function queryTeacherById(teacherId) {
	window.location = "queryTeacherById?teacherId=" + teacherId;
}
	
</script>
</head>
<body>
	<div class="title">当前位置:师资管理>师资列表</div>
	<div class="editBlock">
		
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">师资列表</td>
				</tr>
				<tr>
					<td style="text-align: left"><input name="button" type="button"
						class="inputButton" value="添加"  onclick="teacherAdd(${cj})"/>
						</td>
					<td style="text-align: right;">
					<form action="queryTeacherList?mark=1&page=1&cj=${cj}" method="post"  >
					<input type="hidden" id="organizationId" name="organizationId" value="${teacher.organizationId}" />&nbsp;
					     教师姓名:<input type="text" name="teacherName" class="inputText" id="teacherName" value="${teacher.teacherName}" /> &nbsp;
					
					    <input  type="submit"  class="inputButton" value="查询" />
						</form>
						</td>
				</tr>
			</tbody>
		</table>

	</div>
	<div class="dataGrid">
		<table>
			<caption>师资列表</caption>
			<thead>
				<tr>
				    <!-- <th>序      号</th> -->
				    <th>教师编号</th>
					<th>教师姓名</th>
					
					<th>教师年龄</th>
					<th>教师管理人</th>
					<th>教师管理级别</th>
					
					<th width="60" class="alignCenter">修改</th>
					 <th width="60" class="alignCenter">删除</th>  
				</tr>
			</thead>
			<tbody id="">
			    <c:forEach items="${pageBean.list}" var="maps" varStatus="status">
				<tr>
				<%-- 	<td>${status.count}</td> --%>
					<td>${maps.teacherId}</td>
					<td><a href="#" onclick="queryTeacherById('${maps.teacherId}')">${maps.teacherName }</a></td>
					
					<td>${maps.teacherAge }</td>
					<td>${maps.teacherAdmin }</td>
					<td>${maps.teacherRank }</td>
					
					
					<c:if test="${maps.teacherCatalog==sessionScope.sessionUser.accountCatalog}">
					<td class='alignCenter'><input name='button'type='button' onclick="updateTeacher('${maps.teacherId}')" class='inputButton' value='编辑' /></td>
					 <td class='alignCenter'><input name='button'type='button' onclick="deleteTeacher('${maps.teacherId}')" class='inputButton' value='删除' /></td>
					 
					 </c:if> 
					 <c:if test="${maps.teacherCatalog!=sessionScope.sessionUser.accountCatalog}">
					
					<td class='alignCenter'><input name='button'type='button' style="color:#AFB8C0" class='inputButton' value='编辑' /></td>
					 <td class='alignCenter'><input name='button'type='button' style="color:#AFB8C0"class='inputButton' value='删除' /></td>
					 </c:if>  
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="kkpager"></div><br/>
	</div>
</body>
<script type="text/javascript">

var cfg = /\?currentPage=\d{0,5}/;
var cfg2 = /\&currentPage=\d{0,5}/;
var href = window.location.href;

 if(cfg.test(href)){
	 href = href.replace(cfg,'?')+'&';
	}else if(cfg2.test(href)){
	href = href.replace(cfg2,'')+'&';
	
}else{
	if(href.indexOf('?') > -1){
		href = href + '&';
	}else{
		href = href + '?';
	}
} 

       //生成分页控件  
         kkpager.generPageHtml({
            pno : '${pageBean.currentPage}',
            mode : 'link', //可选，默认就是link
            //总页码  
            total : '${pageBean.totalPage}',  
            //总数据条数  
            totalRecords : '${pageBean.allRow}',  
            //链接前部  
            hrefFormer : '${pageBean.url}&page=',
            //链接尾部  
            hrefLatter : '1',
            //链接算法
            getLink : function(n){
                if(n == 1){
                    return this.hrefFormer + this.hrefLatter;
                }
                return this.hrefFormer + n;
            }

        });
 
</script>
</html>
