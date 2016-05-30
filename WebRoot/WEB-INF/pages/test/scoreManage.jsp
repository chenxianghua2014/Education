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
		<!-- <table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">成绩管理</td>
				</tr>
				<tr>
					<td style="text-align: left;"></td>
					<td style="text-align: right;">编号：<input type="text"
						name="tkglStbh" class="inputText" id="tkglStbh" /> 类型：<input
						type="text" name="tkglStlx" class="inputText" id="tkglStlx" />
						题干：<input type="text" name="tkglSttg" class="inputText"
						id="tkglSttg" /> <input name="button" type="button"
						class="inputButton" value="查询" onclick="loadData()" />
					</td>
				</tr>
			</tbody>
		</table> -->
	</div>
	<div class="dataGrid">
		<table>
			<caption>查询结果</caption>
			<thead>
				<tr>
					<th width="50px;">类型</th>
					<th>题干</th>
					<th>答案</th>
					<th width="60px;">最终得分</th>
					<th width="60px;" class="alignCenter">评分</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageBean.list}" var="maps" varStatus="status">
					<tr>
						<td><c:choose>
								<c:when test="${maps.subject.subjectType eq '3'}">填空题</c:when>
								<c:when test="${maps.subject.subjectType eq '4'}">主观题</c:when>
							</c:choose></td>
						<td>${maps.subject.subjectTopic }</td>
						<td>${maps.testAnswer }</td>
						<td><c:choose>
								<c:when test="${maps.testScore eq -1}">未评分</c:when>
								<c:otherwise>${maps.testScore }</c:otherwise>
							</c:choose></td>
						<td class="alignCenter"><c:choose>
								<c:when test="${maps.testScore eq -1}">
									<input name="button" type="button"
										onclick="editScore('${maps.testId }','${maps.subject.subjectScore }');"
										class='inputButton' value='评分' />
								</c:when>
								<c:otherwise>不可用</c:otherwise>
							</c:choose></td>
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

	function editScore(id, maxScore) {
		var strHtml = "<div class='editBlock'><input type='hidden' id='testId' name='testId' value='" + id + "'/><table>"
				+ "<tr><th>评分:</th><td><input type='text' style='width:80px;' id='testScore'/></td></tr>"
				+ "<tr><th></th><td><input type='button' class='inputButton' value='确定' onclick=\"updScore('"
				+ id
				+ "','"
				+ maxScore
				+ "')\"/>&nbsp;&nbsp;"
				+ "<input name='button' onclick='$.jBox.close();' type='button' class='inputButton' value='返回' /></td></tr></table></div>";
		$.jBox.open(strHtml, '评分', 220, 50, {
			buttons : {
				'关闭' : true
			}
		});
	}

	function updScore(id, maxScore) {
		ajaxPost("updScore", {
			testId : id,
			testScore : $("#testScore").val()
		}, function(msg) {
			if (msg == 0) {
				alert("评分成功!");
				$.jBox.close();
				window.location.reload();
			}else{
				alert("该题分数为 " + msg + " 分,得分不能超过该分数!");
			}
		});
	}
</script>
</html>