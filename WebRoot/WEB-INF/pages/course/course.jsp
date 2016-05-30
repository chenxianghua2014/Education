<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="css/pager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/pager/kkpager_blue.css" />
<script type="text/javascript">
	function Add() {
		window.location = "courseskipID?id=0";
	}
	function update(id) {
		window.location = "courseskipID?id="+id;
	}
	function deletes(id) {
	if (confirm("你确定要删除该条记录吗?")) {
	$.ajax({
			url : "deleteCourse",
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
	
	
	function Courseresource(id) {
		window.location = "courseresourcePage?mark=1&page=1&courseId="+id;
	}
	//------------------------课程的推送--------------------------
	function coursePush(courseId,organizationId,cj) {
		window.location = "studentxyht?courseId=" + courseId+"&mark=1&page=1&organizationId="+organizationId+"&cj="+cj;
	}


</script>
</head>
<body>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="3" class="subtitle">课程</td>
				</tr>
				<tr>
				<td style="text-align: left"><input name="button" type="button"
						class="inputButton" value="添加"  onclick="Add()"/>
						
					</td>
					<td style="text-align: left"></td>
					<td style="text-align: right;">
					<form action="course?syllabusId=${course.syllabusId}&mark=1&page=1" method="post"  >
					查找:<select id="courseCatalog" name="courseCatalog">
							<option value="1" ${course.courseCatalog==1?"selected":""}>课程名称</option>
							<option value="2" ${course.courseCatalog==2?"selected":""}>发布人</option>
							<option value="3" ${course.courseCatalog==3?"selected":""}>发布单位</option>
					</select>
					<input type="text" id=coursePubman name="coursePubman" value="${course.coursePubman}" />
					&nbsp;
					<input  type="submit"  class="inputButton" value="查询" />
					</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>课程查询结果</caption>
			<thead>
				<tr>
				    <th>SN</th>
					<th>课程名称</th>
					<th>课程类型</th>
					<th>状态</th>
					<th>发布人</th>
					<th>发布单位</th>
					<c:if test="${ch==1}">
					<th width="70" class="alignCenter">上传课程</th>
					<th width="50" class="alignCenter">推送</th>
					<th width="50" class="alignCenter">修改</th>
					<th width="50" class="alignCenter">删除</th>
					</c:if>
				</tr>
			</thead>
			<tbody id="dataBody">
			 <c:forEach items="${pageBean.list}" var="maps" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${maps.courseName }</td>
					<td>${maps.courseType }</td>
					<td>
					<c:if test="${maps.courseStatus ==1 }">
					可用
					</c:if>
					<c:if test="${maps.courseStatus !=1 }">
					不可用
					</c:if>
					</td>
					<td>${maps.coursePubman }</td>
					<td>${maps.organization.organizationDwmc }</td>
					<c:if test="${ch==1}">
					<td class='alignCenter'><input name='button'type='button' onclick="Courseresource('${maps.courseId}')" class='inputButton' value='上传课程' /></td>
					<c:if test="${maps.courseType=='选修课' || maps.courseType=='必修课'  || maps.courseType=='党校课' }">
					<td class='alignCenter'><input name='button'type='button' onclick="coursePush('${maps.courseId}','${maps.courseCatalog}','${sessionScope.sessionUser.accountType}')" class='inputButton' value='推送' /></td>
					</c:if>
					<c:if test="${maps.courseType!='选修课' && maps.courseType!='必修课' && maps.courseType!='党校课' }">
					<td class='alignCenter'><input name='button'type='button'  class='inputButton' value='推送' style="color:#AFB8C0"/></td>
					</c:if>
					<td class='alignCenter'><input name='button'type='button' onclick="update('${maps.courseId}')" class='inputButton' value='修改' /></td>
					<td class='alignCenter'><input name='button'type='button' onclick="deletes('${maps.courseId}')" class='inputButton' value='删除' /></td>
					</c:if>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="kkpager"></div>
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