<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>论坛详细</title>  
<!-- 表格风格样式  -->
<link href="css/skins/default/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="title">当前位置:学习圈子>论坛详细</div>
	<div class="editBlock">
		
			
			<table>
				<tr>
					<td colspan="2" class="subtitle">论坛详细</td>
				</tr>
				<tbody>

					<tr >
						<th width="150">标题:</th>
						<td>
						${bbs.bbsTitle}
						</td>
					</tr>
					<tr>
						<th width="150">内容:</th>
						<td>
						${bbs.bbsContent}
						</td>
					</tr>
					
					<tr>
					     <th width="150">添加时间:</th>
						<td>
							<fmt:formatDate value="${bbs.bbsAt }" type="both"
							pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
					</tr>
					
				</tbody>
				<tr>
					<input type="button" class="inputButton" value="返回" onclick="history.back()" /></td>
				</tr>
			</table>
		
	</div>
</body>
</html>
