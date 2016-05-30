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
<script type="text/javascript" src="js/test.js"></script>
</head>
<body>
	<div class="title">当前位置:考试管理 > 试题制作</div>
	<div class="editBlock" id="showWork">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">试题制作</td>
				</tr>
				<tr>
					<td style="text-align: left;"><input name="button"
						type="button" class="inputButton" value="添加试题"
						onclick="showAddTest()" /> <input name="button" type="button"
						class="inputButton" value="导入试题" onclick="showImportTest()" />
						<input name="button" type="button"
						onclick="window.location.href='courseTable?syllabusId=${syllabusId}';"
						class='inputButton' value='返回' />
						</td>
					 <td style="text-align: right;">
					 <form action="test?mark=1&courseId=${subject.courseId}" method="post"  >
					 <input type="hidden" name="syllabusId" class="inputText"
						id="syllabusId" value="${syllabusId}"/> 
					 	类型：<select name="subjectType" id="subjectType" class="inputText"> 
					 					<option value="">全部</option>
					 					<option value="1_3" ${subject.subjectType=="1_3"?"selected":"" }>单选3选1</option>
					 					<option value="1_4" ${subject.subjectType=="1_4"?"selected":"" }>单选4选1</option>
					 					<option value="1_5m" ${subject.subjectType=="1_5m"?"selected":"" }>多选题(五选)</option>
					 					<option value="1_m" ${subject.subjectType=="1_m"?"selected":"" }>多选题(六选)</option>
					 					<option value="2" ${subject.subjectType=="2"?"selected":"" }>判断题</option>
					 					<option value="3" ${subject.subjectType=="3"?"selected":"" }>填空题</option>
					 					<option value="4" ${subject.subjectType=="4"?"selected":"" }>主观题</option>
					 			   </select>
						试题资源名称：<input type="text" name="subjectName" class="inputText"
						id="subjectName" value="${subject.subjectName }"/> 
						<input name="button" type="submit" class="inputButton" value="查询"  />
						</form>
					</td> 
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>查询结果</caption>
			<thead>
				<tr>
					<th width="95px;">试题资源名称</th>
					<th width="100px;">试题类型</th>
					<th>试题题干</th>
					<th width="60px;">试题答案</th>
					<th width="60px;" class="alignCenter">编辑</th>
 					<th width="60px;" class="alignCenter">删除</th> 
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageBean.list}" var="maps" varStatus="status">
					<tr>
						<td>${maps.subjectName }</td>
						<td><c:choose>
								<c:when test="${maps.subjectType eq '1_4'}">选择题(四个选项)</c:when>
								<c:when test="${maps.subjectType eq '1_3'}">选择题(三个选项)</c:when>
								<c:when test="${maps.subjectType eq '1_5m'}">多选题(五个选项)</c:when>
								<c:when test="${maps.subjectType eq '1_m'}">多选题(六个选项)</c:when>
								<c:when test="${maps.subjectType eq '2'}">判断题</c:when>
								<c:when test="${maps.subjectType eq '3'}">填空题</c:when>
								<c:when test="${maps.subjectType eq '4'}">主观题</c:when>
								<c:otherwise>选择题(四个选项)</c:otherwise>
							</c:choose></td>
						<td>${maps.subjectTopic }</td>
						<td><c:choose>
								<c:when test="${maps.subjectAnswer eq 'YES'}">&#10004</c:when>
								<c:when test="${maps.subjectAnswer eq 'NO'}">&#10006</c:when>
								<c:otherwise>${maps.subjectAnswer}</c:otherwise>
							</c:choose></td>
						<td class="alignCenter"><input name="button" type="button"
							onclick="window.location.href='testAdd?subjectId=${maps.subjectId}&subjectType=${maps.subjectType}';"
							class='inputButton' value='编辑' /></td>
 						<td class="alignCenter"><input name="button" type="button"
							onclick="delTest('${maps.subjectId} ')" class='inputButton'
							value='删除' /></td> 
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