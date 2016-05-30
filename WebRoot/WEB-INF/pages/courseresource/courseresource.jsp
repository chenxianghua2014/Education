<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();%>
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
	function Add(cid) {
		window.location = "courseresourceskipID?id=0&cid="+cid;
	}
	
		function update(id,cid) {
		window.location = "courseresourceskipID?id="+id+"&cid="+cid;
	}
	function deletes(id) {
	if (confirm("你确定要删除该条记录吗?")) {
	$.ajax({
			url : "deleteCourseresource",
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

</script>
</head>
<body>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="3" class="subtitle">资源</td>
				</tr>
				<tr>
				<td style="text-align: left"><input name="button" type="button"
						class="inputButton" value="添加"  onclick="Add('${courseresource.courseId}')"/>
				</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>资源查询结果</caption>
			<thead>
				<tr>
				    <th>序号</th>
					<th>资源名称</th>
					<th>资源类型</th>
					<th>文件类型</th>
					<th>文件下载</th>
					<th width="50" class="alignCenter">修改</th>
					<th width="50" class="alignCenter">删除</th>
				</tr>
			</thead>
			<tbody id="dataBody">
			 <c:forEach items="${pageBean.list}" var="maps" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${maps.courseresourceName }</td>
					<td>
					<c:if test="${maps.courseresourceType==1 }">培训师讲义</c:if>
					<c:if test="${maps.courseresourceType==2 }">讲师手册</c:if>
					<c:if test="${maps.courseresourceType==3 }">学员手册</c:if>
					<c:if test="${maps.courseresourceType==4 }">案例库</c:if>
					<c:if test="${maps.courseresourceType==5 }">电子学习课件</c:if>
					<c:if test="${maps.courseresourceType==6 }">scorm学习课件</c:if>
					<c:if test="${maps.courseresourceType==7 }">文档课件</c:if>
					</td>
					<td>${maps.courseresourceFiletype }</td>
					<td>
					<%-- <a href="${maps.courseresourceFileaddr }">下载</a> --%>
					<!-- modified by jiahua   start-->
						<c:choose>
							<c:when test="${maps.courseresourceFiletype=='scorm'}">
								<a href="#"  style="color:gray; text-decoration:none;">下载</a>
							</c:when>   
							<c:otherwise>
							   	<a href="${maps.courseresourceFileaddr }">下载</a>
							</c:otherwise> 
						</c:choose>
						<!-- modified by jiahua    start-->
					
					</td>
					<%-- <td class='alignCenter'><input name='button'type='button' onclick="update('${maps.courseresourceId}','${courseresource.courseId}')" class='inputButton' value='修改' /></td> --%>
					<td class='alignCenter'>
						<!-- modified by jiahua   start-->
						<c:choose>
						   <c:when test="${maps.courseresourceFiletype=='scorm'}">
						   	<input name='button'type='button' disabled="disabled"  value='修改' />
						   </c:when>   
						   <c:otherwise>
						   	<input name='button'type='button' onclick="update('${maps.courseresourceId}','${courseresource.courseId}')" class='inputButton' value='修改' />
						   </c:otherwise>  
						</c:choose>
						<!-- modified by jiahua    start-->
					</td>
					<td class='alignCenter'><input name='button'type='button' onclick="deletes('${maps.courseresourceId}')" class='inputButton' value='删除' /></td>
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