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

	


 
	
	function querySee(studentId,organizationId,cj) {
		window.location = "studentHistoryClass?studentId=" + studentId+"&mark=1&page=1&organizationId="+organizationId+"&cj="+cj ;
	}
</script>
</head>
<body>
	<div class="title">
	当前位置:报告管理>培训报告
	</div>
	<div class="editBlock">
		<form action="#" method="post">
	
		
		 	 <c:if test="${student.courseId!=null}">
			 <input type="hidden" id="courseId" name="courseId" value="${student.courseId}" /> 
			 </c:if>
			&nbsp; 所属部门:<select style="width:158px;" id="studentDepartment"
				name="studentDepartment" onchange="getData()">
				<option value="" selected="selected">全部</option>
				<c:forEach items="${bm}" var="maps" varStatus="status">
					<option value="${maps.departmentId}">${maps.epartmentName}</option>
				</c:forEach>
			</select> 
			<div id="departmentIds" name="departmentIds"></div>
			<input name="button" type="submit" class="inputButton" value="查询" />
		
		</form>
	</div>
	<div class="dataGrid">
		<div>
			<div >
				<table>
					<caption>查询结果</caption>
					<thead>
						<tr>
							<!-- 学员信息表 -->
							<th style="width:30px">序 号</th>
							<th style="width:70px">学员编号</th>
							<th style="width:70px">学员姓名</th>
							<th style="width:70px">学员人员类别</th>
							<th style="width:70px">学员级别</th>
							<th style="width:70px">学员状态</th>
							<!-- <th style="width:70px">学员所在单位(现在放的是学员等级)</th> -->
							<th style="width:70px">学员所属单位编号</th>
							<th style="width:70px">学员所在部门</th>
							
					<!-- 		<th style="width:70px">学员本年度学时</th>
							
							<th style="width:70px">学习币</th>
							<th style="width:70px">学员手机号码</th>
							<th style="width:70px">学员电子邮箱</th>
							<th style="width:70px">学员密码</th>
							<th style="width:70px">必修课应修学时</th>
							<th style="width:70px">必修课学习进度</th>
							<th style="width:70px">已选选修课学时</th>
							<th style="width:70px">选修课学习进度</th> -->


                            <th style="width:70px">查看培训记录</th>
                            
						</tr>
					</thead>
					<tbody id="">
						<c:forEach items="${pageBean.list}" var="maps" varStatus="status">
							<tr>
								<!-- 学员信息表 -->
								<td style="width:30px">${status.count}</td>
								<td style="width:70px">${maps.studentSeq }</td>
								<td style="width:70px">${maps.studentName }</td>
								<td style="width:70px">${maps.studentCategory }</td>
								<td style="width:70px">${maps.studentRank }</td>
								<td style="width:70px">${maps.studentType }</td>
								<%-- <td style="width:70px">${maps.studentCompany }</td> --%>
								<td style="width:70px">${maps.studentCompanyid }</td>
								<td style="width:70px">${maps.studentDepartment }</td>
								
							<%-- 	<td style="width:70px">${maps.studentYeartime }</td>
								
								<td style="width:70px">${maps.studentCoin }</td>
								<td style="width:70px">${maps.studentTelephone }</td>
								<td style="width:70px">${maps.studentEmail }</td>
								<td style="width:70px">${maps.studentPassword }</td>
								<td style="width:70px">${maps.studentBxyxtime }</td>
								<td style="width:70px">${maps.studentBxxxtime }</td>
								<td style="width:70px">${maps.studentYxxxtime }</td>
								<td style="width:70px">${maps.studentXxxxtime }</td> --%>

<td class='alignCenter'><input name='button'type='button' onclick="querySee('${maps.studentId}','${student.organizationId}','${cj}')" class='inputButton' value='查看' /></td>

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
