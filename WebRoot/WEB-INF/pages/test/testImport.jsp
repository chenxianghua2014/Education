<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base target="_self">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="css/skins/default/main.css" />
<link rel="stylesheet" href="js/validator-0.7.3/jquery.validator.css"
	type="text/css"></link>
<link rel="stylesheet" type="text/css"
	href="js/jBox/Skins/Blue/jbox.css"></link>
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="js/jquery/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="js/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="js/jBox/jquery.jBox-zh-CN.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src=""></script>
<script type="text/javascript"
	src="js/validator-0.7.3/jquery.validator.js"></script>
<script type="text/javascript" src="js/validator-0.7.3/local/zh_CN.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('form').validator({
			//自定义用于当前实例的消息
			messages : {
				required : "请选择{0}"
			},
			//待验证字段集合
			fields : {
				excel : 'required'
			},
			valid : function(form) {
				//表单验证通过，提交表单到服务器
				$.jBox.tip("正在批量导入试题，请耐心等待一下！", 'loading');
				document.ff.action = "testDoImport";
				document.ff.method = "post";
				document.ff.submit();
			}
		});
	});
</script>
</head>

<body>
	<div class="title">当前位置:考试管理 > 试题导入</div>
	<div class="editBlock">
		<form enctype="multipart/form-data" name="ff" id="ff">
			<input type="hidden" id="subjectType" name="subjectType"
				value="${subject.subjectType }" /> <input type="hidden"
				id="courseId" name="courseId" value="${subject.courseId }" />
			<table>
				<tr>
					<th><span class="warning">*</span>文本选择：</th>
					<td><input type="file" id="excel" name="excel"
						accept="application/vnd.ms-excel" style="width:450px; height:24px"></td>
				</tr>
				<tr>
					<th>具体要求：</th>
					<td><span style="color: red;"> <c:choose>
								<c:when test="${subject.subjectType eq '1_4'}">选择题(四个选项)
								</c:when>
								<c:when test="${subject.subjectType eq '1_3'}">选择题(三个选项)
								</c:when>
								<c:when test="${subject.subjectType eq '1_5m'}">多选题(五个选项)
								</c:when>
								<c:when test="${subject.subjectType eq '1_m'}">多选题(六个选项)
								</c:when>
								<c:when test="${subject.subjectType eq '2'}">判断题
								</c:when>
								<c:when test="${subject.subjectType eq '3'}">填空题
								</c:when>
								<c:when test="${subject.subjectType eq '4'}">主观题
								</c:when>
								<c:otherwise>选择题(四个选项)
								</c:otherwise>
							</c:choose> 试题导入！
					</span>
						<p>
							1、请按模板要求填写。<br /> 2、Excel文件中不能存在公式。<br />
							3、每次最多导入999行，文件大小限制在1M以内。<br /> 4、导入速度和每次导入的数据量有关，请耐心等待。
						</p>
						<p class="excelModel">
							5、
							<c:choose>
								<c:when test="${subject.subjectType eq '1_4'}">
									<a href="excelModel/FourChooseTest.xls">选择题(四个选项)导入格式.xls</a>
								</c:when>
								<c:when test="${subject.subjectType eq '1_3'}">
									<a href="excelModel/ThreeChooseTest.xls">选择题(三个选项)导入格式.xls</a>
								</c:when>
								<c:when test="${subject.subjectType eq '1_m'}">
									<a href="excelModel/ThreeChooseTestD.xls">多选题(六个选项)导入格式.xls</a>
								</c:when>
								<c:when test="${subject.subjectType eq '1_5m'}">
									<a href="excelModel/ThreeChooseTestD5.xls">多选题(五个选项)导入格式.xls</a>
								</c:when>
								<c:when test="${subject.subjectType eq '2'}">
									<a href="excelModel/JudgeTest.xls">判断题导入格式.xls</a>
								</c:when>
								<c:when test="${subject.subjectType eq '3'}">
									<a href="excelModel/SubjectiveTest.xls">填空题导入格式.xls</a>
								</c:when>
								<c:when test="${subject.subjectType eq '4'}">
									<a href="excelModel/SubjectiveTest.xls">主观题导入格式.xls</a>
								</c:when>
								<c:otherwise>
									<a href="excelModel/FourChooseTest.xls">选择题(四个选项)导入格式.xls</a>
								</c:otherwise>
							</c:choose>
						</p></td>
				</tr>
				<tr>
					<th></th>
					<td><input class="inputButton" type="submit" value="导入">
						<input class="inputButton" type="button" value="返回"
						onclick="window.location.href = document.referrer;"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
