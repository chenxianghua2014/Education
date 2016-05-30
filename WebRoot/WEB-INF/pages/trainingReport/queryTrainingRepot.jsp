  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/skins/default/main.css" rel="stylesheet" type="text/css" />
<!-- <link rel="stylesheet" href="resources/pagination/pagination.css" type="text/css"></link> -->

<!-- <script type="text/javascript" src="js/common.js"></script> -->

<script type="text/javascript" src="css/pager/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="css/pager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/pager/kkpager_blue.css" />

<!-- 查询下拉 -->
<link href="css/index.css" rel="stylesheet" type="text/css" />

<!-- 下拉日期 -->
<script type="text/javascript" charset="utf-8"
	src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">


	/*  	function saveWeddingphoto() {
	 document.ff.action = "saveWeddingphoto";
	 document.ff.method = "post";
	 document.ff.submit();
	 }  */

	/* 二级联动查询 */
	function getData() {
		var studentDepartment = $("#studentDepartment").val();
		$
				.ajax({
					url : "twoLevels",
					data : {
						departmentId : studentDepartment
					},
					type : "post",
					error : function() {
						alert("error occured!!!");
					},
					success : function(data) {
						if (data.length > 0) {
							$("#departmentIds").empty();
							var html = " <select id='studentDepartments'  name='studentDepartments'  >"
									+ "<option value='' selected=\"selected\">---请选择---</option>";

							for ( var i = 0; i < data.length; i++) {
								var xValue = data[i].id;
								var xText = data[i].name;
								html += "<option value='"+xValue+"' >" + xText
										+ "</option>";
							}
							html += " </select>";
							$("#departmentIds").append(html);
						} else {
							var html = "";
							document.getElementById("departmentIds").innerHTML = "";
						}
					}
				});
	}

	  // 返回
		function fanhui(organizationId, cj) {
			window.location = "studentHistory?mark=1&organizationId="+organizationId+"&page=1&cj="+cj;
		}
		  // 返回学员页
	/* 	function fanhuis(organizationId, cj) {
			window.location = "studentHistory?mark=1&organizationId="+organizationId+"&page=1&cj="+cj;
		} */
		function fanhuis(organizationId, cj) {
			window.location = "studentxyht?mark=1&page=1&organizationId="
					+ organizationId + "&cj=" + cj;
		}
	function querySee(studentId) {
		window.location = "studentHistoryClass?studentId=" + studentId+"&mark=1&page=1" ;
	}
</script>
</head>
<body>
	<div class="title">
	<%--  <input type="text" id="qb" name="qb" value="${coursestudy.qb}" />  --%>
	 <c:if test="${coursestudy.qb!=1}">
	当前位置:报告管理>培训报告
	</c:if>
	 <c:if test="${coursestudy.qb==1}">
	当前位置:课程详细信息
	</c:if>
	</div>
	<div class="editBlock">
		<form action="studentHistoryClass?&studentId=${coursestudy.studentId}&mark=1&page=1" method="post">
			<td style="text-align: right;">
				&nbsp;
				         课程名称:<input type="text" name="courseName" class="inputText" id="courseName" value="${coursestudy.courseName}"/> &nbsp;
				&nbsp;
			 	是否是推送的课程:<select
				id="coursestudyType" name="coursestudyType">
					<option value="" >全部</option>
					<option value="1" ${coursestudy.coursestudyType==1?"selected":""}>是</option>
					<option value="2" ${coursestudy.coursestudyType==2?"selected":""}>否</option>
			</select>
					 
					<input name="button" type="submit"  class="inputButton" value="查询" /> 
	<%-- 				 <c:if test="${coursestudy.qb!=1}">
	<input name="button" type="button"
						class="inputButton" value="返回"   onclick="fanhui('${coursestudy.organizationId}','${coursestudy.cj}')"/>
	</c:if>
	 <c:if test="${coursestudy.qb==1}">
	<input name="button" type="button"
						class="inputButton" value="返回"   onclick="fanhuis('${coursestudy.organizationId}','${coursestudy.cj}')"/>
	</c:if> --%>
					
			</td>
		</form>
	</div>
	<div class="dataGrid">
		<div>
			<div style=" overflow:auto; width:1000px; ">
				<table>
					<caption>查询结果</caption>
					<thead>
						<tr>
							<!-- 学员信息表 -->
							<th style="width:70px">序 号</th>
							<th style="width:70px">课程名称</th>
							<th style="width:70px">是否是推送的课程</th>
							<th style="width:70px">课程归属的培训班</th>
							<th style="width:70px">总学时</th>
							<th style="width:70px">现学时</th>
							<th style="width:70px">学习进度</th>
							<th style="width:70px">成绩</th>

                            
						</tr>
					</thead>
					<tbody id="">
						<c:forEach items="${pageBean.list}" var="maps" varStatus="status">
							<tr>
								<!-- 学员信息表 -->
								<td style="width:70px">${status.count}</td>
								<td style="width:70px">${maps.courseName }</td>
								<c:if test="${maps.coursestudyType=='1' }">
								<td style="width:70px">是</td>
								<td style="width:70px">无</td>
								</c:if>
								<c:if test="${maps.coursestudyType!='1' }">
								<td style="width:70px">否</td>
								<td style="width:70px">${maps.tranclassName }</td>
							</c:if>
								<td style="width:70px">${maps.coursestudyAlltime }</td>
								<td style="width:70px">${maps.coursestudyNowtime }</td>
								<td style="width:70px">${maps.coursestudyPlan }</td>
								<td style="width:70px">${maps.sum}</td>
							

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div id="kkpager"></div>
		<br />
	</div>
</body>
<script type="text/javascript">
	var cfg = /\?currentPage=\d{0,5}/;
	var cfg2 = /\&currentPage=\d{0,5}/;
	var href = window.location.href;

	if (cfg.test(href)) {
		href = href.replace(cfg, '?') + '&';
	} else if (cfg2.test(href)) {
		href = href.replace(cfg2, '') + '&';

	} else {
		if (href.indexOf('?') > -1) {
			href = href + '&';
		} else {
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
		getLink : function(n) {
			if (n == 1) {
				return this.hrefFormer + this.hrefLatter;
			}
			return this.hrefFormer + n;
		}

	});
</script>
</html>
