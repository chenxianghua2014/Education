<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/skins/default/main.css" />
<link rel="stylesheet" type="text/css"
	href="js/jBox/Skins/Blue/jbox.css"></link>
<link rel="stylesheet" type="text/css" href="css/pager/kkpager_blue.css">
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="js/jquery/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="js/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="js/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="css/pager/kkpager.min.js"></script>
<script type="text/javascript" src="js/ajaxCommon.js"></script>
</head>
<body>
	<div class="title">当前位置:考试管理 > 成绩管理</div>
	<div class="editBlock" id="showWork">
		<table>
			
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>学员成绩</caption>
			<thead>
				<tr>
					<th width="50px;">姓名</th>
					<th width="50px;">考试名称</th>
					<th width="60px;">成绩</th>
					<th width="60px;">需人工评分题数</th>
					<th width="60px;" class="alignCenter">查看详情</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageBean.list}" var="maps" varStatus="status">
					<tr>
						<td>${maps.studentName }</td>
						<td>${ruld.ruldName }</td>
						<td>
						<c:if test="${maps.size == -1}">等待评分</c:if>
						<c:if test="${maps.size != -1}">${maps.size}</c:if>
						</td>
						<td>${maps.studentNoscore }</td>
						<td class="alignCenter"><input name="button" type="button"
							onclick="window.location.href='scoreManage?studentId=${maps.studentId }&courseId=${ss.courseId }'"
							class='inputButton' value='查看详情' /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="kkpager"></div>
		<br />
	</div>
</body>
<script type="text/javascript">
	$(function() {
		// 生成分页控件
		kkpager.generPageHtml({
			pno : '${pageBean.currentPage}',
			mode : 'link', // 可选，默认就是link
			// 总页码
			total : '${pageBean.totalPage}',
			// 总数据条数
			totalRecords : '${pageBean.allRow}',
			// 链接前部
			hrefFormer : '${pageBean.url}&page=',
			// 链接尾部
			hrefLatter : '1',
			// 链接算法
			getLink : function(n) {
				if (n == 1) {
					return this.hrefFormer + this.hrefLatter;
				}
				return this.hrefFormer + n;
			}
		});
	});
</script>
</html>