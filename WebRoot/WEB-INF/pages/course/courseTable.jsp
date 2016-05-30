<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/skins/default/main.css" />
<link rel="stylesheet" type="text/css" href="css/pager/kkpager_blue.css">
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="css/pager/kkpager.min.js"></script>
</head>
<body>
	<div class="title">当前位置:考试管理 > 试题制作</div>
	<div class="editBlock" id="showWork">
		
	</div>
	<div class="dataGrid">
		<table>
			<caption>查询结果</caption>
			<thead>
				<tr>
					<th width="120px;">课程名称</th>
					<th width="80px;">课程类型</th>
					<th width="50px;">课程状态</th>
					<th width="78px;">课程发布人</th>
					<c:if test="${ch==1}">
					<th width="80px;" class="alignCenter">查看试题</th>
					<th width="80px;" class="alignCenter">试题规则</th>
					<th width="80px;" class="alignCenter">试卷生成</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageBean.list}" var="maps" varStatus="status">
					<tr>
						<td>${maps.courseName }</td>
						<td>${maps.courseType }</td>
						<td><c:if test="${maps.courseStatus ==1 }">
					可用
					</c:if>
					<c:if test="${maps.courseStatus !=1 }">
					不可用
					</c:if></td>
						<td>${maps.coursePubman }</td>
						
						<c:if test="${ch==1}">
						<td class="alignCenter">
						<input name="button" type="button"
							onclick="window.location.href='test?mark=1&courseId=${maps.courseId}&syllabusId=${zhuan}';"
							class='inputButton' value='查看试题' />
							</td>
						<td class="alignCenter">
						<input name="button" type="button"
							onclick="window.location.href='seeRuld?courseId=${maps.courseId}&syllabusId=${zhuan}';"
							class='inputButton' value='试题规则' />
							</td>
							<td class="alignCenter">
						<input name="button" type="button"
							onclick="window.location.href='queryPapers?courseId=${maps.courseId}&syllabusId=${zhuan}';"
							class='inputButton' value='试卷生成' />
							</td>
							</c:if>
							
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