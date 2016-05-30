<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>考试预览</title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="all">
<link href="css/skins/default/cpstyle.css" rel="stylesheet"
	type="text/css" />
<link href="js/jBox/Skins/Blue/jbox.css" rel="stylesheet"
	type="text/css"></link>
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="js/jquery/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="js/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="js/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="js/validator.js"></script>
<style>
td {
	border-bottom: 1px dashed #ccc;
	height: 40px;
	line-height: 30px;
	font-size: 14px;
}
</style>



</head>
<body onselectstart="return false" style="background: white;">
			
			<div class="conRight" style="margin-top: 0px;width:95%">
				<div class="questions">
					<form name="ff" id="ff">
						<input type="hidden" name="clickNum" id="clickNum" value="1" />
						<input type="hidden" name="ruldId" id="ruldId" value="${ruld.ruldId }" />
						<table width="100%">
							<tr>
								<th colspan="2"><h1>${ruld.ruldName }</h1></th>
							</tr>
							<tr>
								<th colspan="2">
									<p style="text-align: left; font-size: 12px;">答题说明:</p>
									<p style="text-align: left; font-size: 12px;">1、试卷总分${ruld.ruldTotalscore }</p>
									<p style="text-align: left; font-size: 12px;">2、${ruld.ruldNote }</p>
							</tr>
							<c:if test="${ ! empty  map['1_3'] }">
							<tr>
								<td colspan="2"><h3>${ruld.ruldName13 }(每题${ruld.ruldScore13 }分)</h3></td>
							</tr>
							</c:if>
						</table>
						<c:set value="0" var="sum" />
						<table width="100%">
							<c:forEach items="${ map['1_3'] }" var="map" varStatus="status">
								<c:set value="${status.index+1}" var="sum" />
								<tr>
									<td style="width: 60px;">${sum}、</td>
									<td colspan="2">
									${map.subject.subjectTopic}
									<c:if test="${ruld.ruldAnswers==1 }">
									<input type="button" value="查看答案" onclick="alert('${map.subject.subjectAnswer}')">
									</c:if>
									</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="radio"
										name="${map.subject.subjectId}" value="A" /></td>
									<td style="width: 60px;">A.</td>
									<td>${map.subject.subjectA}</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="radio"
										name="${map.subject.subjectId}" value="B" /></td>
									<td style="width: 60px;">B.</td>
									<td>${map.subject.subjectB}</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="radio"
										name="${map.subject.subjectId}" value="C" dataType="Group" msg="请答题" /></td>
									<td style="width: 60px;">C.</td>
									<td>${map.subject.subjectC}</td>
								</tr>
							</c:forEach>
						</table>
						<table width="100%">
							<c:if test="${!empty map['1_4'] }">
							<tr>
								<td colspan="2"><h3>${ruld.ruldName14 }(每题${ruld.ruldScore14 }分)</h3></td>
							</tr>
								</c:if>
						</table>
						<table width="100%">
							<c:forEach items="${ map['1_4'] }" var="map" varStatus="status">
								<c:set value="${sum }" var="sum" />
								<tr>
									<td style="width: 60px;">${sum+status.index+1}、</td>
									<td colspan="2">
									${map.subject.subjectTopic}
									<c:if test="${ruld.ruldAnswers==1 }">
									<input type="button" value="查看答案" onclick="alert('${map.subject.subjectAnswer}')">
									</c:if>
									</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="radio"
										name="${map.subject.subjectId}" value="A" /></td>
									<td style="width: 60px;">A.</td>
									<td>${map.subject.subjectA}</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="radio"
										name="${map.subject.subjectId}" value="B" /></td>
									<td style="width: 60px;">B.</td>
									<td>${map.subject.subjectB}</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="radio"
										name="${map.subject.subjectId}" value="C" /></td>
									<td style="width: 60px;">C.</td>
									<td>${map.subject.subjectC}</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="radio"
										name="${map.subject.subjectId}" value="D" dataType="Group" msg="请答题" /></td>
									<td style="width: 60px;">D.</td>
									<td>${map.subject.subjectD}</td>
								</tr>
							</c:forEach>
						</table>
							<table width="100%">
							<c:if test="${!empty map['1_5m'] }">
							<tr>
								<td colspan="2"><h3>${ruld.ruldName15M }(每题${ruld.ruldScore15M }分)</h3></td>
							</tr>
								</c:if>
						</table>
						<table width="100%">
							<c:forEach items="${ map['1_5m'] }" var="map" varStatus="status">
								<tr>
									<td style="width: 60px;">${status.index+1}、</td>
										<td colspan="2">
									${map.subject.subjectTopic}
									<c:if test="${ruld.ruldAnswers==1 }">
									<input type="button" value="查看答案" onclick="alert('${map.subject.subjectAnswer}')">
									</c:if>
									</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="checkbox"
										name="${map.subject.subjectId}" value="A" /></td>
									<td style="width: 60px;">A.</td>
									<td>${map.subject.subjectA}</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="checkbox"
										name="${map.subject.subjectId}" value="B" /></td>
									<td style="width: 60px;">B.</td>
									<td>${map.subject.subjectB}</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="checkbox"
										name="${map.subject.subjectId}" value="C" /></td>
									<td style="width: 60px;">C.</td>
									<td>${map.subject.subjectC}</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="checkbox"
										name="${map.subject.subjectId}" value="D"  /></td>
									<td style="width: 60px;">D.</td>
									<td>${map.subject.subjectD}</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="checkbox"
										name="${map.subject.subjectId}" value="E" dataType="Group" msg="请答题" /></td>
									<td style="width: 60px;">E.</td>
									<td>${map.subject.subjectE}</td>
								</tr>
							</c:forEach>
						</table>
						<table width="100%">
							<c:if test="${!empty map['1_m'] }">
							<tr>
								<td colspan="2"><h3>${ruld.ruldName1M }(每题${ruld.ruldScore1M }分)</h3></td>
							</tr>
								</c:if>
						</table>
						<table width="100%">
							<c:forEach items="${ map['1_m'] }" var="map" varStatus="status">
								<tr>
									<td style="width: 60px;">${status.index+1}、</td>
									<td colspan="2">
									${map.subject.subjectTopic}
									<c:if test="${ruld.ruldAnswers==1 }">
									<input type="button" value="查看答案" onclick="alert('${map.subject.subjectAnswer}')">
									</c:if>
									</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="checkbox"
										name="${map.subject.subjectId}" value="A" /></td>
									<td style="width: 60px;">A.</td>
									<td>${map.subject.subjectA}</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="checkbox"
										name="${map.subject.subjectId}" value="B" /></td>
									<td style="width: 60px;">B.</td>
									<td>${map.subject.subjectB}</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="checkbox"
										name="${map.subject.subjectId}" value="C" /></td>
									<td style="width: 60px;">C.</td>
									<td>${map.subject.subjectC}</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="checkbox"
										name="${map.subject.subjectId}" value="D"  /></td>
									<td style="width: 60px;">D.</td>
									<td>${map.subject.subjectD}</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="checkbox"
										name="${map.subject.subjectId}" value="E" /></td>
									<td style="width: 60px;">E.</td>
									<td>${map.subject.subjectE}</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="checkbox"
										name="${map.subject.subjectId}" value="F" dataType="Group" msg="请答题" /></td>
									<td style="width: 60px;">F.</td>
									<td>${map.subject.subjectF}</td>
								</tr>
							</c:forEach>
						</table>
						<table width="100%">
						<c:if test="${!empty map['2'] }">
							<tr>
								<td colspan="2"><h3>${ruld.ruldName2 }(每题${ruld.ruldScore2 }分)</h3></td>
							</tr>
							</c:if>
						</table>
						<table width="100%">
							<c:forEach items="${ map['2'] }" var="map" varStatus="status">
								<tr>
									<td style="width: 60px;">${status.index+1}、</td>
									<td colspan="2">
									${map.subject.subjectTopic}
									<c:if test="${ruld.ruldAnswers==1 }">
									<input type="button" value="查看答案" onclick="alert('${map.subject.subjectAnswer}')">
									</c:if>
									</td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="radio"
										name="${map.subject.subjectId}" value="YES" /></td>
									<td>&#10004</td>
									<td></td>
								</tr>
								<tr>
									<td style="text-align:right;"><input type="radio"
										name="${map.subject.subjectId}" value="NO" dataType="Group" msg="请答题" /></td>
									<td>&#10006</td>
									<td></td>
								</tr>
							</c:forEach>
						</table>
						<table width="100%">
						<c:if test="${!empty map['3'] }">
							<tr>
								<td colspan="2"><h3>${ruld.ruldName3 }(每题${ruld.ruldScore3 }分)</h3></td>
							</tr>
							</c:if>
						</table>
						<table width="100%">
							<c:forEach items="${ map['3'] }" var="map" varStatus="status">
								<tr>
									<td>${status.index+1}、</td>
									<td colspan="2">
									<c:set value="${fn:split(map.subject.subjectTopic, '$()$') }" var="topics" />
										<c:forEach items="${topics}" var="topic"  varStatus="stat">
										${topic} 
										<c:if test="${!stat.last}">
										<input type="text" name="${map.subject.subjectId }" dataType="Require" msg="请答题" />
										</c:if>  
										</c:forEach>
										</td>
								</tr>
							</c:forEach>
						</table>
						<table width="100%">
						<c:if test="${!empty map['4'] }">
							<tr>
								<td colspan="2"><h3>${ruld.ruldName4 }(每题${ruld.ruldScore4 }分)</h3></td>
							</tr>
							</c:if>
						</table>
						<table width="100%">
							<c:forEach items="${ map['4'] }" var="map" varStatus="status">
								<tr>
									<td style="width:35px;">${status.index+1}、</td>
									<td colspan="2">${map.subject.subjectTopic }</td>
								</tr>
								<tr>
									<td colspan="3"><textarea style="width: 99%;height:80px;"
											name="${map.subject.subjectId }" dataType="Require" msg="请答题"></textarea></td>
								</tr>
							</c:forEach>
						</table>
					</form>
				</div>
			</div>
</body>
</html>