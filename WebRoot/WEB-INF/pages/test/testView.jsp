<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>考试</title>
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

<!-- 禁用右键: -->

<script>
function stop(){

return false;

}

document.oncontextmenu=stop;

</script>

<script>
 document.onkeydown   =   function(){    
     if(window.event && window.event.keyCode == 116) {
	  return false;
 	}
      if(window.event   &&   window.event.keyCode   ==   123)   {    
        return false;
      }    
     
  }

</script>
<SCRIPT LANGUAGE=javascript>

if (top.location != self.location)top.location=self.location;

</SCRIPT>


</head>
<body onselectstart="return false">
	<c:choose>
		<c:when test="${Student eq null}">
			<script type="text/javascript">
				$.jBox.open("iframe:loginFrm", "用户登录", 260, 200, {
					buttons : {}
				});
			</script>
		</c:when>
		<c:otherwise>
			<div id="fixd">
				<div id="top"></div>
				<div class="conTop">
					<p>考生姓名：&nbsp;&nbsp;&nbsp; ${sessionScope.Student.studentName}</p>
					<div class="time">
						<img src="images/time.jpg" />考试剩余时间 ：<span id="timer"></span>
					</div>
				</div>
			</div>
			<div class="conRight">
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
									<td colspan="2"><c:set
											value="${ fn:split(map.subject.subjectTopic, '$()$') }" var="topics" />
										<c:forEach items="${ topics }" var="topic"  varStatus="stat">
										${ topic }
										<c:if test="${!stat.last}">
										<input type="text" name="${map.subject.subjectId }" dataType="Require" msg="请答题" />
										</c:if>  
										</c:forEach></td>
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
						<a href="javascript:void(0);" class="btn"
							onclick="checkValidate();">提交答卷</a>
					</form>
				</div>
			</div>
			<script type="text/javascript" language="javascript">
				/*------判断radio是否有选中，获取选中的值--------*/
				var maxtime = '${ruld.ruldTime }' * 60;//半个小时，按秒计算，自己调整!
				function CountDown() {
					if (maxtime >= 0) {
						minutes = Math.floor(maxtime / 60);
						seconds = Math.floor(maxtime % 60);
						msg = minutes + "分" + seconds + "秒";
						document.all["timer"].innerHTML = msg;
						if (maxtime == 5 * 60)
							$.jBox
									.messager(
											"您的考试时间还剩余：<span style='color:red;'>5分钟</span>！请抓紧时间",
											"剩余考试时间");
						--maxtime;
					} else {
						clearInterval(timer);
						$.jBox.messager('时间到，考试结束', '剩余考试时间');
						$.jBox.tip("正在提交试卷，请耐心等待一下！", 'loading');
						document.ff.action = "testSubmit";
						document.ff.method = "post";
						document.ff.submit();
						window.setTimeout(function() {
							$.jBox.tip('已成功提交试卷。', 'success');
						}, 10000);
					}
				}
				timer = setInterval("CountDown()", 1000);
				$(function() {
					$("#btnSubmit").click(function() {
						alert("提交成功");
					});
				});
				function checkValidate() {
					if (Validator.Validate(document.getElementById('ff'), 3)) {
						if (confirm("您确定要提交试卷么?")) {
							if (confirm("您确定要提交试卷么?")) {
								if (confirm("您确定要提交试卷么?")) {
									$.jBox.tip("正在提交试卷，请耐心等待一下！", 'loading');
									document.ff.action = "testSubmit";
									document.ff.method = "post";
									document.ff.submit();
									window.setTimeout(function() {
										$.jBox.tip('已成功提交试卷。', 'success');
									}, 10000);
								}
							}
						}
					}
				}
				function rmd() {
					return new Date();
				}
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>