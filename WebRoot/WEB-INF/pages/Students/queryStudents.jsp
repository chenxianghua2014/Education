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
	function Addclass(sjid, rank, type) {
		window.location = "addEditPage?pageClassId=" + sjid
				+ "&classresourceRank=" + rank + "&classresourceUpname=" + type;
	}
	
		function StudentsImport() {
		window.location = "StudentsImport";
	}

	function deleteClass(classresourceId) {
		if (confirm("你确定要删除该条记录吗?")) {
			$.ajax({
				url : "deleteClass",
				type : 'GET',
				data : {
					classresourceId : classresourceId
				},
				success : function(data) {
					if (data == "ok") {
						alert("删除成功");
						window.location.reload();
					} else {
						alert("删除失败");
						window.location.reload();
					}
				}
			});
		}
	}
	function editClass(classresourceId, rank) {
		window.location = "addEditPage?id=" + classresourceId
				+ "&classresourceRank=" + rank;
	}
	//src="queryClassCatalog?mark=1&organizationId="+n+"&page=1&cj="+i;
	function classOnes(classresourceId, cj) {
		/* alert(classresourceId)
		alert(cj) */
		window.location = "queryShift?classresourceId=" + classresourceId
				+ "&mark=1&page=1&cj=" + cj;
	}

	/*  	function saveWeddingphoto() {
	 document.ff.action = "saveWeddingphoto";
	 document.ff.method = "post";
	 document.ff.submit();
	 }  */
	 /* N级联动查询 */
		function getDataCompany() {
		    document.getElementById("deparCompanyids").innerHTML = "";
		    document.getElementById("deparCompanyBmids").innerHTML = "";
		    document.getElementById("deparCompanyKsids").innerHTML = "";
		   
			var studentCompanyid = $("#studentCompanyid").val();
			$
					.ajax({
						url : "twoCompany",
						data : {
							organizationSjdw : studentCompanyid
						},
						type : "post",
						error : function() {
							alert("error occured!!!");
						},
						success : function(data) {
							if (data.length > 0) {
								$("#deparCompanyids").empty();
								var html = " <select id='studentCompanyids'  name='studentCompanyids' onchange='getDataTwoCompany()' >"
										+ "<option value='' selected=\"selected\">---请选择---</option>";
								if(studentCompanyid=='test001'){
									html += "<option value='test001' >航天科工集团</option>";
								}
								for ( var i = 0; i < data.length; i++) {
									var xValue = data[i].id;
									var xText = data[i].name;
									html += "<option value='"+xValue+"' >" + xText
											+ "</option>";
								}
								html += " </select>";
								$("#deparCompanyids").append(html);
							} else {
								/* var html = "<input type='hidden' id='studentCompanyids' name='studentCompanyid' value='9' />";
								$("#deparCompanyids").append(html); */
								 document.getElementById("deparCompanyids").innerHTML = ""; 
							}
						}
					});
		}
	 
	 
	 /* 二级联动查询 */
		function getDataTwoCompany() {
		 
			 document.getElementById("deparCompanyBmids").innerHTML = "";
			    document.getElementById("deparCompanyKsids").innerHTML = "";
			var studentCompanyids = $("#studentCompanyids").val();
			$
					.ajax({
						url : "twoBm",
						data : {
							organizationId : studentCompanyids
						},
						type : "post",
						error : function() {
							alert("error occured!!!");
						},
						success : function(data) {
							if (data.length > 0) {
								$("#deparCompanyBmids").empty();
								var html = " <select id='studentDepartmentBm'  name='studentDepartment'  onchange='getDataks()'>"
										+ "<option value='' selected=\"selected\">---请选择---</option>";

								for ( var i = 0; i < data.length; i++) {
									var xValue = data[i].id;
									var xText = data[i].name;
									html += "<option value='"+xValue+"' >" + xText
											+ "</option>";
								}
								html += " </select>";
								$("#deparCompanyBmids").append(html);
							} else {
								/* var html = "<input type='hidden' id='studentDepartmentBm' name='studentDepartment' value='9' />";
								$("#deparCompanyBmids").append(html); */
								 document.getElementById("deparCompanyBmids").innerHTML = ""; 
							}
						}
					});
		}
		/* 二级联动查询 */
		function getDataks() {
			  document.getElementById("deparCompanyKsids").innerHTML = "";
			var studentDepartmentBm = $("#studentDepartmentBm").val();
			$
					.ajax({
						url : "twoLevels",
						data : {
							departmentId : studentDepartmentBm
						},
						type : "post",
						error : function() {
							alert("error occured!!!");
						},
						success : function(data) {
							if (data.length > 0) {
								$("#deparCompanyKsids").empty();
								var html = " <select id='studentDepartments'  name='studentDepartments'  >"
										+ "<option value='' selected=\"selected\">---请选择---</option>";

								for ( var i = 0; i < data.length; i++) {
									var xValue = data[i].id;
									var xText = data[i].name;
									html += "<option value='"+xValue+"' >" + xText
											+ "</option>";
								}
								html += " </select>";
								$("#deparCompanyKsids").append(html);
							} else {
							/* 	var html = "<input type='hidden' id='studentDepartments' name='studentDepartment' value='9' />";
								$("#deparCompanyKsids").append(html); */
								 document.getElementById("deparCompanyKsids").innerHTML = ""; 
							}
						}
					});
		}
	 
		
	/*单一所属部门  二级联动查询 */
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
							$("#departmentIdss").empty();
							$("#departmentIdsFrom").empty();
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
						 	/* var html1 = " <select id='ww'  name='ww'  >"
								+ "<option value='' selected=\"selected\">---此部门下无科室---</option>";
								+ " </select>"; */
								var html1 = "<input type='hidden' id='studentDepartments' name='studentDepartments' value='9' />";
						 		 $("#departmentIdss").append(html1);
								/*  document.getElementById("departmentIdss").innerHTML = "";  */
						 		$("#departmentIds").append(html); 
							  document.getElementById("departmentIds").innerHTML = ""; 
							 
						}
					}
				});
	}

	/* 二级联动查询      工种名称（子集） */
	function getDataJA() {
		var studenttwoGzmcSx = $("#studenttwoGzmcSx").val();
		$
				.ajax({
					url : "twoDataJA",
					data : {
						studenttwoGzmcSx : studenttwoGzmcSx
					},
					type : "post",
					error : function() {
						alert("error occured!!!");
					},
					success : function(data) {
						if (data.length > 0) {
							$("#studenttwoGzmcId").empty();
							var html = " <select  style='width:243px;' id='studenttwoGzmc'  name='studenttwo.studenttwoGzmc'  >"
									+ "<option value='' selected=\"selected\">---请选择---</option>";

							for ( var i = 0; i < data.length; i++) {
								var xValue = data[i].id;
								var xText = data[i].name;
								html += "<option value='"+xValue+"' >" + xText
										+ "</option>";
							}
							html += " </select>";
							$("#studenttwoGzmcId").append(html);
						} else {
							var html = "";
							document.getElementById("studenttwoGzmcId").innerHTML = "";
						}
					}
				});
	}

	//按钮的点击事件
	function showAll(n) {
		if (n == 1) {
			var div = document.getElementById("more1");
			if (div.style.display == "block") {
				div.style.display = "none";
				document.getElementById("tOne").value = 0;
			} else {
				div.style.display = "block";
				document.getElementById("tOne").value = 1;
			}
		} else if (n == 2) {
			var div = document.getElementById("more2");
			if (div.style.display == "block") {
				div.style.display = "none";
				document.getElementById("tTwo").value = 0;
			} else {
				div.style.display = "block";
				document.getElementById("tTwo").value = 1;
			}
		} else if (n == 3) {
			var div = document.getElementById("more3");
			if (div.style.display == "block") {
				div.style.display = "none";
				document.getElementById("tThree").value = 0;
			} else {
				div.style.display = "block";
				document.getElementById("tThree").value = 1;
			}
		} else if (n == 4) {
			var div = document.getElementById("more4");
			if (div.style.display == "block") {
				div.style.display = "none";
				document.getElementById("tFour").value = 0;
			} else {
				div.style.display = "block";
				document.getElementById("tFour").value = 1;
			}
		}

	}

	var cfg = false;
	function select_all() { //全选     
		if (cfg == false) {
			var inputs = document.getElementsByTagName("input");
			for ( var i = 0; i < inputs.length; i++) {
				if (inputs[i].getAttribute("type") == "checkbox") {
					inputs[i].checked = true;
				}
			}
			cfg = true;
		} else {
			var inputs = document.getElementsByTagName("input");
			for ( var i = 0; i < inputs.length; i++) {
				if (inputs[i].getAttribute("type") == "checkbox") {
					inputs[i].checked = false;
				}
			}
			cfg = false;
		}
	}
	
	function querySee(studentId,organizationId,cj) {
		window.location = "studentHistoryClass?studentId=" + studentId+"&mark=1&page=1&qb=1&organizationId="+organizationId+"&cj="+cj ;
	}
	
	function addStudents(companyId, cj) {
		window.location = "addStudents?companyId="
				+ companyId + "&cj=" + cj;
	}
	function updateStudent(id,companyId, cj) {
		window.location = "updateStudent?id="+id+"&companyId="
				+ companyId + "&pan=2&cj=" + cj;
	}
	function detailedStudent(id,organizationId, cj) {
		window.location = "updateStudent?id="+id+"&organizationId="
				+ organizationId + "&pan=1&cj=" + cj;
	}
	function queryFree(zhid) {
		window.location = "studentxyht?mark=1&page=1&cj=6&organizationId="+zhid;
		
	}
	function queryJop(organizationId, cj) {
		window.location = "studentxyht?mark=1&page=1&organizationId="
				+ organizationId + "&cj=" + cj;
	}
	function diaochu(studentId, organizationId, cj) {
		window.location = "studentout?studentId=" + studentId
				+ "&organizationId=" + organizationId + "&cj=" + cj;
	}
	function diaoru(studentId) {
		var cj = 6;
		var ids = document.getElementById('studentDepartment').value;
		if (ids != "") {
			var idss = document.getElementById('studentDepartments').value;
		} 
		if (ids == "") {
			alert("请选择所属部门");
			return false;
		} else if (idss == "" && ids != "") {
			window.location = "studentin?studentId=" + studentId
					+ "&studentDepartment=" + ids + "&cj=" + cj;
		} else if (idss != "" && ids != ""&& idss != "9" ) {
			window.location = "studentin?studentId=" + studentId
					+ "&studentDepartment=" + idss + "&cj=" + cj;
		}else if (idss == "9" && ids != "") {
			window.location = "studentin?studentId=" + studentId
			+ "&studentDepartment=" + ids + "&cj=" + cj;
}

	}
	function push(mark) {
		
	if(mark==3){
		//推送
		 var flag = false;
		  var deId = document.getElementsByName("checkboxids");
		  for (var i = 0; i < deId.length; i++) {
	            if (deId[i].checked) {
	             flag = true;
	            } 
	          
	}
		  if (!flag) {
              alert("请选择需要推送的人员！");
              return ;
          }else{
             if (window.confirm("确认要推送么？")) {
            	document.form1.action = "pushStudent";
         		document.form1.method = "post";
         		document.form1.submit();
          	 /*   window.location = "pushStudent?courseId="+courseId+"&checkboxids="+deId; */
            
             }return false;
          }
	}else if(mark==1){
		//查询
		document.form1.action = "studentxyht?mark=1&page=1";
 		document.form1.method = "post";
 		document.form1.submit();
	}else if(mark==2){
		//调入
		
		 var flag = false;
		  var deId = document.getElementsByName("checkboxids");
		  for (var i = 0; i < deId.length; i++) {
	            if (deId[i].checked) {
	             flag = true;
	            } 
	          
	}
		  if (!flag) {
             alert("请选择需要调入的人员！");
             return ;
         }else{
            if (window.confirm("确认要调入么？")) {
            	
            	var cj = 6;
        		var ids = document.getElementById('studentDepartment').value;
        		if (ids != "") {
        			var idss = document.getElementById('studentDepartments').value;
        		} 
        		if (ids == "") {
        			alert("请选择所属部门");
        			return false;
        		} 
        		else if (idss == "" && ids != "") {
        			document.form1.action = "studentins";
             		document.form1.method = "post";
             		document.form1.submit();
        			/* alert("请选择部门下的所属科室");
        			return false; */
        			/* window.location = "studentin?studentId=" + studentId
        					+ "&studentDepartment=" + ids + "&cj=" + cj; */
        		}
        		else if (idss != "" && ids != ""&& idss != "9" ) {
        			document.form1.action = "studentins";
             		document.form1.method = "post";
             		document.form1.submit(); 
        			/* window.location = "studentin?studentId=" + studentId
        					+ "&studentDepartment=" + idss + "&cj=" + cj; */
        		}else if (idss == "9" && ids != "") {
        			document.form1.action = "studentins";
             		document.form1.method = "post";
             		document.form1.submit();
        			/* alert("请选择部门下的所属科室"); */
        			return false;
        			/* window.location = "studentin?studentId=" + studentId
        			+ "&studentDepartment=" + ids + "&cj=" + cj; */
        }

         	 /*   $('#form1').submit(); */
         	 /*   window.location = "pushStudent?courseId="+courseId+"&checkboxids="+deId; */
           
            }return false;
         }
		
	}
	}
	
	function diaoru() {
		
		
		 var flag = false;
		  var deId = document.getElementsByName("checkboxids");
		  for (var i = 0; i < deId.length; i++) {
	            if (deId[i].checked) {
	             flag = true;
	            } 
	          
	}
		  if (!flag) {
             alert("请选择需要调入的人员！");
             return ;
         }else{
            if (window.confirm("确认要调入么？")) {
            	
            	var cj = 6;
        		var ids = document.getElementById('studentDepartment').value;
        		if (ids != "") {
        			var idss = document.getElementById('studentDepartments').value;
        		} 
        		if (ids == "") {
        			alert("请选择所属部门");
        			return false;
        		} else if (idss == "" && ids != "") {
        			window.location = "studentin?studentId=" + studentId
        					+ "&studentDepartment=" + ids + "&cj=" + cj;
        		} else if (idss != "" && ids != ""&& idss != "9" ) {
        			window.location = "studentin?studentId=" + studentId
        					+ "&studentDepartment=" + idss + "&cj=" + cj;
        		}else if (idss == "9" && ids != "") {
        			window.location = "studentin?studentId=" + studentId
        			+ "&studentDepartment=" + ids + "&cj=" + cj;
        }

            	
         	   $('#form1').submit();
         	 /*   window.location = "pushStudent?courseId="+courseId+"&checkboxids="+deId; */
           
            }return false;
         }
	}
	
	
</script>
</head>
<body>
	<div class="title">当前位置:学员管理>人员详情</div>
	<form id="form1" name = "form1">
	<div class="editBlock">
	 <c:if test="${student.courseId==null||student.courseId!=null&&sessionScope.sessionUser.accountCatalog!='test001'}">
	 	<input type="hidden" name="organizationId" value="${student.studentCompanyid}"/>
	 </c:if>
	 <input type="hidden" name="cj" value="${cj}"/>
	<%--  <c:if test="${student.courseId!=null&&sessionScope.sessionUser.accountCatalog=='test001'}">
	 <form action="studentxyht?mark=1&page=1&cj=${cj}" method="post">
	 </c:if> --%>
			<input type="hidden" name="tOne" id="tOne" value="0" /> <input
				type="hidden" name="tTwo" id="tTwo" value="0" /> <input
				type="hidden" name="tThree" id="tThree" value="0" /> <input
				type="hidden" name="tFour" id="tFour" value="1" />
			<div class="ssbox">
				<div class="ssbox_one">
					<div class="ssbox_h">
						<a href="#" class="ssbox_h_q" onclick="showAll(1)">筛选条件1</a>
					</div>
					<div class="ssbox_con" id="more1" style="display:none;">

						<div class="clear"></div>
						<ul>
							<!-- 表1 -->
							<div class="ryxq_screening">
							<li style="margin-top:9px">性 &nbsp;&nbsp;别: &nbsp;&nbsp;<select style="width:158px;height:22px;"
								id="studentoneXb" name="studentone.studentoneXb">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${ax}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studentone.studentoneXb==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
							<li>身份证: <input name="studentone.studentoneSfzh"
								id="studentoneSfzh" value="${student.studentone.studentoneSfzh}"
								type="text" class="inputText" />
							</li>
							<li>年&nbsp;&nbsp; 龄:&nbsp;<input name="studentone.studentoneAge"
								id="studentoneAge" value="${student.studentone.studentoneAge}"
								type="text" class="inputText" />
							</li>
							<li style="margin-top:9px">学&nbsp;&nbsp; 历:&nbsp;&nbsp;&nbsp;<select style="width:158px;"
								id="studentoneXl" name="studentone.studentoneXl">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${am}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studentone.studentoneXl==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
							<li style="margin-top:9px">学 &nbsp;&nbsp;位:&nbsp;&nbsp;&nbsp;<select style="width:158px;"
								id="studentoneXw" name="studentone.studentoneXw">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${an}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studentone.studentoneXw==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
							<li style="margin-top:9px">政治面貌:<select style="width:158px;"
								id="studentoneZzmm" name="studentone.studentoneZzmm">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${AT}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studentone.studentoneZzmm==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
							<li style="margin-top:9px">用工形式:<select style="width:158px;"
								id="studentoneYgxs" name="studentone.studentoneYgxs">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${XA}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studentone.studentoneYgxs==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
							<li>行政级别:<input name="studentone.studentoneXzjb"
								id="studentoneXzjb" value="${student.studentone.studentoneXzjb}"
								type="text" class="inputText" />
							</li>
							</div>
							<div class="ryxq_screening">
							<li>专业技术职务:<input name="studentone.studentoneZyjszw"
								id="studentoneZyjszw"
								value="${student.studentone.studentoneZyjszw}" type="text"
								class="inputText" />
							</li>
							<li>工人技术职务:<input name="studentone.studentoneGrjszw"
								id="studentoneGrjszw"
								value="${student.studentone.studentoneGrjszw}" type="text"
								class="inputText" />
							</li>
							<li>所&nbsp;&nbsp;学&nbsp;专&nbsp;&nbsp;业: <input name="studentone.studentoneSxzy"
								id="studentoneSxzy" value="${student.studentone.studentoneSxzy}"
								type="text" class="inputText" />
							</li>
								<li>院&nbsp;&nbsp;&nbsp;&nbsp;（系）&nbsp;&nbsp;: <input name="studentone.studentoneYx"
								id="studentoneYx" value="${student.studentone.studentoneYx}"
								type="text" class="inputText" />
							</li>
							<li>学位授予单位: <input name="studentone.studentoneXwsydw"
								id="studentoneXwsydw"
								value="${student.studentone.studentoneXwsydw}" type="text"
								class="inputText" />
							</li>
							<li style="margin-top:9px">毕&nbsp;&nbsp;&nbsp;&nbsp;肄&nbsp;&nbsp;&nbsp;&nbsp;业&nbsp;&nbsp;:&nbsp;&nbsp;<select style="width:158px;"
								id="studentoneByy" name="studentone.studentoneByy">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${HY}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studentone.studentoneByy==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
							<li>毕（肄）业学校或单位:<input name="studentone.studentoneByyxx"
								id="studentoneByyxx"  style="width:110px;"
								value="${student.studentone.studentoneByyxx}" type="text"
								class="inputText" />
							</li>
							</div>
							<div class="ryxq_screening">
									<li style="margin-top:9px">全日制&nbsp;&nbsp;或在职：&nbsp;<select
								style="width:158px;" id="studentoneQrzhzz"
								name="studentone.studentoneQrzhzz">
									<option value="">全部</option>
									<option value=1
										${student.studentone.studentoneQrzhzz==1?"selected":""}>全日制</option>
									<option value=2
										${student.studentone.studentoneQrzhzz==2?"selected":""}>在
										职</option>
							</select>
							</li>
							<li style="margin-top:9px">是否&nbsp;当前&nbsp;学历：&nbsp;<select
								style="width:158px;" id="studentoneSfdqxl"
								name="studentone.studentoneSfdqxl">
									<option value="">全部</option>
									<option value=1
										${student.studentone.studentoneSfdqxl==1?"selected":""}>是</option>
									<option value=2
										${student.studentone.studentoneSfdqxl==2?"selected":""}>否</option>
							</select>
							</li>
							<li style="margin-top:9px">是否&nbsp;当前&nbsp;学位：&nbsp;<select
								style="width:158px;" id="studentoneSfdqxw"
								name="studentone.studentoneSfdqxw">
									<option value="">全部</option>
									<option value=1
										${student.studentone.studentoneSfdqxw==1?"selected":""}>是</option>
									<option value=2
										${student.studentone.studentoneSfdqxw==2?"selected":""}>否</option>
							</select>
							</li>
							<li>出&nbsp;&nbsp;&nbsp;生&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;期:<input name="studentone.studentoneCsri"
								id="studentoneCsri" value="${student.studentone.studentoneCsri}"
								type="text" class="inputText" onclick='WdatePicker()' />
							</li>
							<li>参加&nbsp;工作&nbsp;时间:<input name="studentone.studentoneCjgzsj"
								id="studentoneCjgzsj"
								value="${student.studentone.studentoneCjgzsj}" type="text"
								class="inputText" onclick='WdatePicker()' />
							</li>
							<li>现单位工作时间:<input name="studentone.studentoneXdwgzsj"
								id="studentoneXdwgzsj"
								value="${student.studentone.studentoneXdwgzsj}" type="text"
								class="inputText" onclick='WdatePicker()' />
							</li>
							<li>入&nbsp;&nbsp;&nbsp;学&nbsp;&nbsp;&nbsp;时&nbsp;&nbsp;&nbsp;间:<input name="studentone.studentoneRxsj"
								id="studentoneRxsj" value="${student.studentone.studentoneRxsj}"
								type="text" class="inputText" onclick='WdatePicker()' />
							</li>
							<li>毕（肄）业时间:<input name="studentone.studentoneByysj"
								id="studentoneByysj"
								value="${student.studentone.studentoneByysj}" type="text"
								class="inputText" onclick='WdatePicker()' />
							</li>
</div>
						</ul>
						<div class="clear"></div>
					</div>
				</div>
				<div class="ssbox_two">
					<div class="ssbox_h">
						<a href="#" class="ssbox_h_q" onclick="showAll(2)">筛选条件2</a>
					</div>
					<div class="ssbox_con" id="more2" style="display:none;">

						<div class="clear"></div>
						<ul>
							<!-- 表2 -->
							<div class="ryxq_screening">
							<li style="margin-top:9px">职务&nbsp;类别:&nbsp;<select style="width:158px;"
								id="studenttwoZwlb" name="studenttwo.studenttwoZwlb">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${BQ}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studenttwo.studenttwoZwlb==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
								<li>聘任时间:<input name="studenttwo.studenttwoPrsj"
								id="studenttwoPrsj" value="${student.studenttwo.studenttwoPrsj}"
								type="text" class="inputText" onclick='WdatePicker()' />
							</li>
							<li>取得时间:<input name="studenttwo.studenttwoQdsj"
								id="studenttwoQdsj" value="${student.studenttwo.studenttwoQdsj}"
								type="text" class="inputText" onclick='WdatePicker()' />
							</li>
							<li style="margin-top:9px">待遇&nbsp;级别:&nbsp;<select style="width:158px;"
								id="studenttwoDyjb" name="studenttwo.studenttwoDyjb">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${OZ}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studenttwo.studenttwoDyjb==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
							<li style="margin-top:9px">职务&nbsp;属性:&nbsp;<select style="width:158px;"
								id="studenttwoZwsx" name="studenttwo.studenttwoZwsx">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${IR}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studenttwo.studenttwoZwsx==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
						<li style="margin-top:9px">异常&nbsp;类别:&nbsp;<select style="width:158px;"
								id="studenttwoYclb" name="studenttwo.studenttwoYclb">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${CA}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studenttwo.studenttwoYclb==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
							<li>异常原因:<input name="studenttwo.studenttwoYcyy"
								id="studenttwoYcyy" value="${student.studenttwo.studenttwoYcyy}"
								type="text" class="inputText" />
							</li>
							<li>任职时间:<input name="studenttwo.studenttwoRzsj"
								id="studenttwoRzsj" value="${student.studenttwo.studenttwoRzsj}"
								type="text" class="inputText" onclick='WdatePicker()' />
							</li>
							<li>免职时间:<input name="studenttwo.studenttwoMzsj"
								id="studenttwoMzsj" value="${student.studenttwo.studenttwoMzsj}"
								type="text" class="inputText" onclick='WdatePicker()' />
							</li>
							
							<li style="margin-top:9px">专业&nbsp;类别:&nbsp;<select style="width:158px;"
								id="studenttwoZylb" name="studenttwo.studenttwoZylb">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${YC}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studenttwo.studenttwoZylb==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
							</div>
							<div class="ryxq_screening">
							<li>行政职务名称:<input name="studenttwo.studenttwoXzzwmc"
								id="studenttwoXzzwmc"
								value="${student.studenttwo.studenttwoXzzwmc}" type="text"
								class="inputText" />
							</li>
							<li>党内职务名称:<input name="studenttwo.studenttwoDnzwmc"
								id="studenttwoDnzwmc"
								value="${student.studenttwo.studenttwoDnzwmc}" type="text"
								class="inputText" />
							</li>
						
							<li>聘任起始时间:<input name="studenttwo.studenttwoPrqssj"
								id="studenttwoPrqssj"
								value="${student.studenttwo.studenttwoPrqssj}" type="text"
								class="inputText" onclick='WdatePicker()' />
							</li>
							<li>聘任终止时间:<input name="studenttwo.studenttwoPrzzsj"
								id="studenttwoPrzzsj"
								value="${student.studenttwo.studenttwoPrzzsj}" type="text"
								class="inputText" onclick='WdatePicker()' />
							</li>
							<li>取得资格时间:<input name="studenttwo.studenttwoQdzgsj"
								id="studenttwoQdzgsj"
								value="${student.studenttwo.studenttwoQdzgsj}" type="text"
								class="inputText" onclick='WdatePicker()' />
							</li>
						
							<li style="margin-top:9px">工人技术资格 : <select
								style="width:158px;" id="studenttwoGrjszg"
								name="studenttwo.studenttwoGrjszg">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${XJ}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studenttwo.studenttwoGrjszg==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
									<li style="margin-top:9px">是否当前职务：<select
								style="width:158px" id="studenttwoSfdqzw"
								name="studenttwo.studenttwoSfdqzw">
									<option value="">全部</option>
									<option value=1
										${student.studenttwo.studenttwoSfdqzw==1?"selected":""}>是</option>
									<option value=2
										${student.studenttwo.studenttwoSfdqzw==2?"selected":""}>否</option>
							</select>
							</li>
							<li>参加党派时间:<input name="studenttwo.studenttwoCjdpsj"
								id="studenttwoCjdpsj"
								value="${student.studenttwo.studenttwoCjdpsj}" type="text"
								class="inputText" onclick='WdatePicker()' />
							</li>

							<li>工&nbsp;&nbsp;种&nbsp;&nbsp;名&nbsp;&nbsp;称:&nbsp;&nbsp;<select style="width:158px;" id="studenttwoGzmcSx"
								name="studenttwo.studenttwoGzmcSx" onchange="getDataJA()">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${JATop}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studenttwo.studenttwoGzmc==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
								<div id="studenttwoGzmcId"></div>
							</li>
							</div>
							<div class="ryxq_screening">
							<li>聘任专业技术职务名称:<input style="width:130px;"
								name="studenttwo.studenttwoPrzyjszwmc" id="studenttwoPrzyjszwmc"
								value="${student.studenttwo.studenttwoPrzyjszwmc}" type="text"
								class="inputText" />
							</li>
							<li style="margin-top:9px">是否当前专业技术职务：<select
								style="width:135px" id="studenttwoSfdqzyjszw"
								name="studenttwo.studenttwoSfdqzyjszw">
									<option value="">全部</option>
									<option value=1
										${student.studenttwo.studenttwoSfdqzyjszw==1?"selected":""}>是</option>
									<option value=2
										${student.studenttwo.studenttwoSfdqzyjszw==2?"selected":""}>否</option>
							</select>
							</li>
							<li>个人简历起始时间:<input name="studenttwo.studenttwoGrjlqssj"
								id="studenttwoGrjlqssj"
								value="${student.studenttwo.studenttwoGrjlqssj}" type="text"
								class="inputText" onclick='WdatePicker()' />
							</li>
							<li>个人简历结束时间:<input name="studenttwo.studenttwoGrjljssj"
								id="studenttwoGrjljssj"
								value="${student.studenttwo.studenttwoGrjljssj}" type="text"
								class="inputText" onclick='WdatePicker()' />
							</li>
							<li>聘任工人技术职务:<input name="studenttwo.studenttwoPrgrjszw"
								id="studenttwoPrgrjszw"
								value="${student.studenttwo.studenttwoPrgrjszw}" type="text"
								class="inputText" />
							</li>
							<li>从事工作或担任职务:<input name="studenttwo.studenttwoCsgzhdrzw"
								id="studenttwoCsgzhdrzw" style="width:140px;"
								value="${student.studenttwo.studenttwoCsgzhdrzw}" type="text"
								class="inputText" />
							</li>
							
						
							<li style="margin-top:9px">是否最初任同职级：<select
								style="width:158px" id="studenttwoSfzcrtzj"
								name="studenttwo.studenttwoSfzcrtzj">
									<option value="">全部</option>
									<option value=1
										${student.studenttwo.studenttwoSfzcrtzj==1?"selected":""}>是</option>
									<option value=2
										${student.studenttwo.studenttwoSfzcrtzj==2?"selected":""}>否</option>
							</select>
							</li>
							<li>专业技术资格名称:<input name="studenttwo.studenttwoZyjszgmc"
								id="studenttwoZyjszgmc"
								value="${student.studenttwo.studenttwoZyjszgmc}" type="text"
								class="inputText" />
							</li>
							<li>专业技术任职资格:<input name="studenttwo.studenttwoZyjsrzzg"
								id="studenttwoZyjsrzzg"
								value="${student.studenttwo.studenttwoZyjsrzzg}" type="text"
								class="inputText" />
							</li>
								
							</div>


						</ul>
						<div class="clear"></div>
					</div>
				</div>
				<div class="ssbox_three">
					<div class="ssbox_h">
						<a href="#" class="ssbox_h_q" onclick="showAll(3)">筛选条件3</a>
					</div>
					<div class="ssbox_con" id="more3" style="display:none;">
						<div class="clear"></div>
						<ul>
							<!--Studenthree 表3  -->
<div class="ryxq_screening">
							<li style="margin-top:9px">留&nbsp;学&nbsp;状&nbsp;态:&nbsp;<select style="width:158px;"
								id="studenthreeLxzt" name="studenthree.studenthreeLxzt">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${XR}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studenthree.studenthreeLxzt==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
							<li>派出&nbsp;&nbsp;单位:<input name="studenthree.studenthreePcdw"
								id="studenthreePcdw"
								value="${student.studenthree.studenthreePcdw}" type="text"
								class="inputText" />
							</li>
							<li>专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业:<input name="studenthree.studenthreeZy"
								id="studenthreeZy" value="${student.studenthree.studenthreeZy}"
								type="text" class="inputText" />
							</li>
							<li style="margin-top:9px">留&nbsp;学&nbsp;身&nbsp;份:&nbsp;<select style="width:158px;"
								id="studenthreeLxsf" name="studenthree.studenthreeLxsf">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${XS}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studenthree.studenthreeLxsf==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
							<li style="margin-top:9px">留&nbsp;学&nbsp;国&nbsp;别:&nbsp;<select style="width:158px;"
								id="studenthreeLxgb" name="studenthree.studenthreeLxgb">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${AD}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studenthree.studenthreeLxgb==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
							
							<li>出国&nbsp;&nbsp;时间:<input name="studenthree.studenthreeCgsj"
								id="studenthreeCgsj"
								value="${student.studenthree.studenthreeCgsj}" type="text"
								class="inputText" onclick='WdatePicker()' />
							</li>
							<li>培训&nbsp;&nbsp;天数:<input name="studenthree.studenthreePxts"
								id="studenthreePxts"
								value="${student.studenthree.studenthreePxts}" type="text"
								class="inputText" />
							</li>
							<li style="margin-top:9px">培&nbsp;训&nbsp;类&nbsp;型:&nbsp;<select style="width:158px;"
								id="studenthreePxlx" name="studenthree.studenthreePxlx">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${YG}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studenthree.studenthreePxlx==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
							</div>
							<div class="ryxq_screening">
							<li style="margin-top:9px">培&nbsp;训&nbsp;&nbsp;&nbsp;渠&nbsp;道:&nbsp;<select style="width:158px;"
								id="studenthreePxqd" name="studenthree.studenthreePxqd">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${YH}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studenthree.studenthreePxqd==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
							<li style="margin-top:9px">离&nbsp;退&nbsp;&nbsp;&nbsp;类&nbsp;别:&nbsp;<select style="width:158px;"
								id="studenthreeLtlb" name="studenthree.studenthreeLtlb">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${HD}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studenthree.studenthreeLtlb==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
							<li style="margin-top:9px">国别&nbsp;&nbsp;及&nbsp;地区:&nbsp;<select
								style="width:158px;" id="studenthreeGbjdq"
								name="studenthree.studenthreeGbjdq">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${AD}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studenthree.studenthreeGbjdq==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
							<li>留&nbsp;学&nbsp;时&nbsp;间: <input name="studenthree.studenthreeLxsj"
								id="studenthreeLxsj"
								value="${student.studenthree.studenthreeLxsj}" type="text"
								class="inputText" onclick='WdatePicker()' />
							</li>
						
								<li style="margin-top:9px">董监事&nbsp;&nbsp;&nbsp;标识:&nbsp;<select
								style="width:158px;" id="studenthreeDjsbz"
								name="studenthree.studenthreeDjsbz">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${OG}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studenthree.studenthreeDjsbz==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
								<li style="margin-top:9px">型号干部标识:&nbsp;<select
								style="width:158px;" id="studenthreeXhgbbz"
								name="studenthree.studenthreeXhgbbz">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${OE}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studenthree.studenthreeXhgbbz==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
						
							<li>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:&nbsp;<input name="studenthree.studenthreeBj"
								id="studenthreeBj" value="${student.studenthree.studenthreeBj}"
								type="text" class="inputText" />
							</li>
							<li style="margin-top:9px">领导干部标识:&nbsp;<select
								style="width:158px;" id="studenthreeLdgbbz"
								name="studenthree.studenthreeLdgbbz">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${OC}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studenthree.studenthreeLdgbbz==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
							</div>
							<div class="ryxq_screening">
							<li style="margin-top:9px">领导&nbsp;&nbsp;后备&nbsp;标识:&nbsp;&nbsp;<select
								style="width:158px;" id="studenthreeLdhbbz"
								name="studenthree.studenthreeLdhbbz">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${OD}" var="maps" varStatus="status">
										<option value="${maps.dictionaryCode}"
											${student.studenthree.studenthreeLdhbbz==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
									</c:forEach>
							</select>
							</li>
							<li>培训&nbsp;&nbsp;项目&nbsp;名称:<input name="studenthree.studenthreePxxmmc"
								id="studenthreePxxmmc"
								value="${student.studenthree.studenthreePxxmmc}" type="text"
								class="inputText" />
							</li>
							<li>离（退）休时间:<input name="studenthree.studenthreeLtxsj"
								id="studenthreeLtxsj"
								value="${student.studenthree.studenthreeLtxsj}" type="text"
								class="inputText" onclick='WdatePicker()' />
							</li>
							<li>出国（境）事由:<input name="studenthree.studenthreeCgsy"
								id="studenthreeCgsy"
								value="${student.studenthree.studenthreeCgsy}" type="text"
								class="inputText" />
							</li>
							<li>出国&nbsp;&nbsp;所去&nbsp;单位:<input name="studenthree.studenthreeCgsqdw"
								id="studenthreeCgsqdw"
								value="${student.studenthree.studenthreeCgsqdw}" type="text"
								class="inputText" />
							</li>
							<li>出国&nbsp;&nbsp;派出&nbsp;单位:<input name="studenthree.studenthreeCgpcdw"
								id="studenthreeCgpcdw"
								value="${student.studenthree.studenthreeCgpcdw}" type="text"
								class="inputText" />
							</li>
							
							<li>计划&nbsp;&nbsp;回国&nbsp;时间: <input name="studenthree.studenthreeJhhgsj"
								id="studenthreeJhhgsj"
								value="${student.studenthree.studenthreeJhhgsj}" type="text"
								class="inputText" onclick='WdatePicker()' />
							</li>
							<li>实际&nbsp;&nbsp;回国&nbsp;时间: <input name="studenthree.studenthreeSjhgsj"
								id="studenthreeSjhgsj"
								value="${student.studenthree.studenthreeSjhgsj}" type="text"
								class="inputText" onclick='WdatePicker()' />
							</li>
</div>


						</ul>
						<div class="clear"></div>
					</div>
				</div>
				<div class="ssbox_four">
					<div class="ssbox_h">
						<a href="#" class="ssbox_h_q" onclick="showAll(4)">筛选条件4</a>
					</div>
					<div class="ssbox_con" id="more4" style="display:block;">

						<div class="clear"></div>
						<ul>
							<li style="margin-top:7px"></li>
							<li>人员姓名:<input type="text" class="inputText"
								name="studentName" id="studentName"
								value="${student.studentName}" />
							</li>
							<li>电话号码:<input type="text" class="inputText"
								name="studentTelephone" id="studentTelephone"
								value="${student.studentTelephone}" />
							</li>
							<li>邮 箱: <input name="studentEmail" id="studentEmail"
								value="${student.studentEmail}" type="text" class="inputText" />
							</li>
							<%-- <li style="margin-top:9px">
				学员状态：<select style="width:145px"
						id="studentType" name="studentType">
							<option value="" >全部</option>
							<option value=1 ${student.studentType==1?"selected":""}>在职</option>
							<option value=2 ${student.studentType==2?"selected":""}>游离</option>
					</select>   
					</li> --%>
							<li style="margin-top:9px">人员类别：<select style="width:145px"
								id="studentCategory" name="studentCategory">
									<option value="">全部</option>
									<option value="管理类"
										${student.studentCategory=="管理类"?"selected":""}>管理类</option>
									<option value="专业技术类"
										${student.studentCategory=="专业技术类"?"selected":""}>专业技术类</option>
									<option value="技能类"
										${student.studentCategory=="技能类"?"selected":""}>技能类</option>
									<option value="班组长"
										${student.studentCategory=="班组长"?"selected":""}>班组长</option>
							</select>
							</li>



						</ul>
						<div class="clear"></div>
					</div>
				</div>
			</div>
			 <c:if test="${student.courseId!=null}">
			 <input type="hidden" id="courseId" name="courseId" value="${student.courseId}" /> 
			 </c:if>
			<%-- <input type="hidden" id="organizationId" name="organizationId" value="${classCatalog.organizationId}" />
					<input type="hidden" id="classresourceRank" name="classresourceRank" value="${cj}" />
					<input type="hidden" id="classresourceUpname" name="classresourceUpname" value="${classCatalog.classresourceUpname}" /> --%>
			 <c:if test="${student.courseId==null||student.courseId!=null&&sessionScope.sessionUser.accountCatalog!='test001'}">
			<br/>
			&nbsp; 所属部门:<select style="width:158px;" id="studentDepartment"
				name="studentDepartment" onchange="getData()">
				<option value="" selected="selected">全部</option>
				<c:forEach items="${bm}" var="maps" varStatus="status">
					<option value="${maps.departmentId}">${maps.epartmentName}</option>
				</c:forEach>
			</select>
			<div id="departmentIds" name="departmentIds"></div>
			<div id="departmentIdss" name="departmentIdss"></div>
			</c:if>
			
			<c:if test="${student.courseId!=null&&sessionScope.sessionUser.accountCatalog=='test001'}">
			 &nbsp; 单位或部门:<select style="width:158px;" id="studentCompanyid"
				name="studentCompanyid" onchange="getDataCompany()">
				<option value="" selected="selected">全部</option>
				<c:forEach items="${listOrke}" var="maps" varStatus="status">
					<option value="${maps.organizationId}">${maps.organizationDwmc}</option>
				</c:forEach>
			</select> 
			<div id="deparCompanyids" name="studentCompanyid"></div>
			<div id="deparCompanyBmids" name="studentDepartment"></div>
			<div id="deparCompanyKsids" name="studentDepartment"></div>
			 </c:if>
		
			
			
			<input name="button" type="button" class="inputButton" onclick="push(1)" value="查询" />
			<%-- <input name="text" type="text"  value="${student.jop==6}" />  --%>
		<%-- 	<c:if
				test="${student.studentCompanyid==sessionScope.sessionUser.accountCatalog}"> --%>
				 <c:if test="${student.courseId!=null}">
				<input name="button" type="button" class="inputButton" value="推送"
					onclick="push(3)" />
					</c:if>
				<c:if test="${student.courseId==null}">
				<c:if test="${student.studentCompanyid==sessionScope.sessionUser.accountCatalog}">
				<%--<input name="button" type="button" class="inputButton" value="导出"
					onclick="Export()" />
				--%><input name="button" type="button" class="inputButton" value="导入"
					onclick="StudentsImport()" />
					<input name="button" type="button" class="inputButton"
						value="添加" onclick="addStudents('${companyId}','${sessionScope.sessionUser.accountType}')" />
				<c:if test="${student.jop!=6}">
					<input name="button" type="button" class="inputButton"
						value="查看游离职员" onclick="queryFree('${student.studentCompanyid}')" />
						
				</c:if>
				</c:if>
				<c:if test="${student.jop==6}">
			<input name="button" type="button" class="inputButton" value="调入"
					onclick="push(2)" />
				<input name="button" type="button" class="inputButton"
					value="查看在职职员"
					onclick="queryJop('${sessionScope.sessionUser.accountCatalog}','${sessionScope.sessionUser.accountType}')" />
			</c:if>
				
			
				</c:if>
				
				
			<%-- </c:if> --%>
			
			
			
			

			<%-- <c:if test="${student.studentCompanyid!=sessionScope.sessionUser.accountCatalog}">
			  <c:if test="${student.courseId!=null}">
				 <input name="button" type="button" style="color:#AFB8C0"
					class="inputButton" value="推送" /> 
					<input name="button" type="button" class="inputButton" value="推送"
					onclick="push()" />
				</c:if>	 --%>
					

					
					
					
				<%-- 	<c:if test="${student.jop==6}">
					<input name="button" type="button" style="color:#AFB8C0" class="inputButton" value="查看在职职员" />
					</c:if>
					<c:if test="${student.jop!=6}">
					<input name="button" type="button" style="color:#AFB8C0" class="inputButton" value="查看游离职员"  />
					</c:if> --%>
			<%-- </c:if> --%>

			<!-- <input type="checkbox" onclick="select_all();"><a style="color:#000" href="javascript:select_all();">全选</a></input> -->
			


		<!-- </form> -->
	</div>
	<div class="dataGrid">
		<div>
			<div style=" overflow:auto; width:950px; ">
				<table>
					<caption>查询结果</caption>
					<thead>
						<tr>
							<!-- 学员信息表 -->
							<%-- <c:if test="${student.courseId==null}">
							<c:if test="${student.jop!=6}">
								<th style="width:70px">修改</th>
							</c:if>
							</c:if>
                          <c:if test="${student.courseId!=null}">
							<th style="width:30px"><input type="checkbox"
								onclick="select_all();" value="0" /></th>
						 </c:if>		
							<!--   <th style="width:70px">导出</th> -->
							<c:if test="${student.jop==6}">
								<!-- <th style="width:70px">调入</th> -->
								<th style="width:30px"><input type="checkbox"
								onclick="select_all();" value="0" /></th>
							</c:if>
								<c:if test="${student.courseId==null}">
							<c:if test="${student.jop!=6}">
								<th style="width:70px">调出</th>
							</c:if>
							</c:if>
							<!-- 功能按钮 -->
							
							
							<th style="width:70px">学员详细</th> --%>
							<c:if test="${student.jop==6}">
								<!-- <th style="width:70px">调入</th> -->
								<th style="width:30px"><input type="checkbox"
								onclick="select_all();" value="0" /></th>
							</c:if>
							    <c:if test="${student.courseId!=null}">
							<th style="width:30px"><input type="checkbox"
								onclick="select_all();" value="0" /></th>
						 </c:if>	 
							<th style="width:70px">课程查看</th>
							<th style="width:70px">序 号</th>
							
							<!-- 整理后的22个字段 -->
							<th style="width:70px">学员部门</th>
							<th style="width:70px">学员科室</th>
							<th style="width:70px">学员编号</th>
							<th style="width:70px">学员姓名</th>
							<th style="width:70px">学员人员类别</th>
							<th style="width:70px">学员级别</th>
							<th style="width:70px">学员状态</th>
							<th style="width:70px">学习币</th>
							<th style="width:70px">学员密码</th>
							<th style="width:70px">身份证号</th>
							<th style="width:70px">性别</th>
							<th style="width:70px">年龄</th>
							<th style="width:70px">学历</th>
							<th style="width:70px">学位</th>
							<th style="width:70px">政治面貌</th>
							<th style="width:70px">行政级别</th>
							<th style="width:70px">专业技术职务</th>
							<th style="width:70px">工人技术职务</th>
							<th style="width:70px">毕（肄）业学校或单位</th>
							<th style="width:70px">出生日期</th>
							<th style="width:70px">参加工作时间</th>
							<th style="width:70px">行政职务名称</th>
							<th style="width:70px">党内职务名称</th>
							<th style="width:70px">从事工作或担任职务</th>
							<c:if test="${student.courseId==null}">
							<c:if test="${student.jop!=6}">
								<th style="width:70px">修改</th>
							</c:if>
							</c:if>
							<!--   <th style="width:70px">导出</th> -->
					<%-- 		<c:if test="${student.jop==6}">
								<!-- <th style="width:70px">调入</th> -->
								<th style="width:30px"><input type="checkbox"
								onclick="select_all();" value="0" /></th>
							</c:if> --%>
								<c:if test="${student.courseId==null}">
							<c:if test="${student.jop!=6}">
								<th style="width:70px">调出</th>
							</c:if>
							</c:if>
							<!-- 功能按钮 -->
							
							
							<th style="width:70px">学员详细</th>
							 
							
							
							
							
							
							<!-- 1<th style="width:70px">学员编号</th>
							1<th style="width:70px">学员姓名</th>
							1<th style="width:70px">学员人员类别</th>
							1<th style="width:70px">学员级别</th>
							1<th style="width:70px">学员状态</th>
							<th style="width:70px">学员所在单位(现在放的是学员等级)</th>
							<th style="width:70px">学员所属单位编号</th>
							<th style="width:70px">学员所在部门</th>
							1<th style="width:70px">学习币</th>
							<th style="width:70px">学员本年度学时</th>

							<th style="width:70px">学员手机号码</th>
							<th style="width:70px">学员电子邮箱</th>
							1<th style="width:70px">学员密码</th>
							<th style="width:70px">必修课应修学时</th>
							<th style="width:70px">必修课学习进度</th>
							<th style="width:70px">已选选修课学时</th>
							<th style="width:70px">选修课学习进度</th>

							人员详细信息表1
							1<th style="width:70px">身份证号</th>
							1<th style="width:70px">性别</th>
							1<th style="width:70px">年龄</th>
							<th style="width:70px">人员类别</th>
							1<th style="width:70px">学历</th>
							1<th style="width:70px">学位</th>
							1<th style="width:70px">政治面貌</th>
							<th style="width:70px">用工形式</th>
							1<th style="width:70px">行政级别</th>
							1<th style="width:70px">专业技术职务</th>
							1<th style="width:70px">工人技术职务</th>


							<th style="width:70px">所学专业</th>
							<th style="width:70px">毕肄业</th>
							1<th style="width:70px">毕（肄）业学校或单位</th>
							<th style="width:70px">院（系）</th>
							<th style="width:70px">学位授予单位</th>
							<th style="width:70px">全日制或在职</th>
							<th style="width:70px">是否当前学历</th>
							<th style="width:70px">是否当前学位</th>
							1<th style="width:70px">出生日期</th>
							1<th style="width:70px">参加工作时间</th>
							<th style="width:70px">现单位工作时间</th>
							<th style="width:70px">入学时间</th>
							<th style="width:70px">毕（肄）业时间</th>

							人员详细信息表2
							<th style="width:70px">职务类别</th>
							1<th style="width:70px">行政职务名称</th>
							1<th style="width:70px">党内职务名称</th>
							<th style="width:70px">待遇级别</th>
							<th style="width:70px">职务属性</th>
							<th style="width:70px">是否当前职务</th>
							<th style="width:70px">是否最初任同职级</th>
							<th style="width:70px">专业技术资格名称</th>
							<th style="width:70px">专业技术任职资格</th>
							<th style="width:70px">专业类别</th>
							<th style="width:70px">聘任专业技术职务名称</th>
							<th style="width:70px">是否当前专业技术职务</th>

							<th style="width:70px">工种名称</th>
							<th style="width:70px">工人技术资格</th>
							<th style="width:70px">聘任工人技术职务</th>
							<th style="width:70px">所在单位</th>
							1<th style="width:70px">从事工作或担任职务</th>
							<th style="width:70px">异常类别</th>
							<th style="width:70px">异常原因</th>

							<th style="width:70px">任职时间</th>
							<th style="width:70px">免职时间</th>
							<th style="width:70px">取得时间</th>
							<th style="width:70px">聘任起始时间</th>
							<th style="width:70px">聘任终止时间</th>
							<th style="width:70px">取得资格时间</th>
							<th style="width:70px">聘任时间</th>
							<th style="width:70px">个人简历起始时间</th>
							<th style="width:70px">个人简历结束时间</th>
							<th style="width:70px">参加党派时间</th>

							人员详细信息表3
							<th style="width:70px">留学状态</th>
							<th style="width:70px">派出单位</th>
							<th style="width:70px">专业</th>
							<th style="width:70px">留学身份</th>
							<th style="width:70px">留学国别</th>
							<th style="width:70px">培训项目名称</th>
							<th style="width:70px">培训天数</th>
							<th style="width:70px">培训类型</th>
							<th style="width:70px">培训渠道</th>
							<th style="width:70px">离退类别</th>
							<th style="width:70px">国别及地区</th>
							<th style="width:70px">出国（境）事由</th>
							<th style="width:70px">出国所去单位</th>
							<th style="width:70px">出国派出单位</th>

							<th style="width:70px">备注</th>
							<th style="width:70px">领导干部标识</th>
							<th style="width:70px">领导后备标识</th>
							<th style="width:70px">型号干部标识</th>
							<th style="width:70px">董监事标识</th>
							<th style="width:70px">留学时间</th>
							<th style="width:70px">离（退）休时间</th>
							<th style="width:70px">出国时间</th>
							<th style="width:70px">计划回国时间</th>
							<th style="width:70px">实际回国时间</th> -->

						</tr>
					</thead>
					<tbody id="">
					<%-- <input type="hidden" name="courseId" value="${student.courseId}"/> --%>
					<%-- <c:if test="${student.jop!=6}">
					<form name="form1" id="form1" action="pushStudent?courseId=${student.courseId}" method="post">
							</c:if>
							<c:if test="${student.jop==6}">
					<form name="form1" id="form1" action="pushStudent?courseId=${student.courseId}" method="post">
							</c:if> --%>
						<c:forEach items="${pageBean.list}" var="maps" varStatus="status">
							<tr>
								<!-- 学员信息表 -->
							<%-- 	<c:if test="${student.courseId==null}">
								<c:if test="${sessionScope.sessionUser.accountCatalog==maps.studentCompanyid &&student.jop!=6}">
									<td class='alignCenter'><input name="button" type="button" class="inputButton" value="修改"
									onclick="updateStudent('${maps.studentId}','${companyId}','${sessionScope.sessionUser.accountType}')" />
								</td>
								</c:if>	
								<c:if test="${sessionScope.sessionUser.accountCatalog!=maps.studentCompanyid &&student.jop!=6}">
									<td class='alignCenter'><input name="button" type="button" class="inputButton" value="修改" style="color:#AFB8C0"/>
								</td>
								</c:if>	
								</c:if>
								
								
								 <c:if test="${student.courseId!=null}">
								<td style="width:30px"><input type="checkbox" id="checkboxids"
									name="checkboxids" value="${maps.studentId}" /></td>
									</c:if>
								<c:if test="${student.jop==6}">
								<td style="width:30px"><input type="checkbox" id="checkboxids"
									name="checkboxids" value="${maps.studentId}" /></td>
									<td class='alignCenter'><input name='button' type='button'
										onclick="diaoru('${maps.studentId}')" class='inputButton'
										value='调入' /></td>
								</c:if>
								<c:if test="${student.courseId==null}">
								<c:if test="${student.jop!=6}">
								
								<c:if test="${sessionScope.sessionUser.accountCatalog==maps.studentCompanyid}">
									<td class='alignCenter'><input name='button' type='button'
										onclick="diaochu('${maps.studentId}','${sessionScope.sessionUser.accountCatalog}','${sessionScope.sessionUser.accountType}')"
										class='inputButton' value='调出' /></td>
								</c:if>	
								<c:if test="${sessionScope.sessionUser.accountCatalog!=maps.studentCompanyid}">
									<td class='alignCenter'><input name='button' type='button'
										 style="color:#AFB8C0"
										class='inputButton' value='调出' /></td>
								</c:if>	
										
								</c:if>
								</c:if>
								
									<td style="width:70px">
								<input name="button" type="button" class="inputButton" value="详请"
									onclick="detailedStudent('${maps.studentId}','${sessionScope.sessionUser.accountCatalog}','${sessionScope.sessionUser.accountType}')" />
								</td> --%>
									<c:if test="${student.jop==6}">
								<td style="width:30px"><input type="checkbox" id="checkboxids"
									name="checkboxids" value="${maps.studentId}" /></td>
									<%-- <td class='alignCenter'><input name='button' type='button'
										onclick="diaoru('${maps.studentId}')" class='inputButton'
										value='调入' /></td> --%>
								</c:if>
								 <c:if test="${student.courseId!=null}">
								<td style="width:30px"><input type="checkbox" id="checkboxids"
									name="checkboxids" value="${maps.studentId}" /></td>
									</c:if>
									<td style="width:70px">
								<input name="button" type="button" class="inputButton" value="课程查看"
									onclick="querySee('${maps.studentId}','${sessionScope.sessionUser.accountCatalog}','${sessionScope.sessionUser.accountType}')" />
								</td>
								<td style="width:70px">${status.count}</td>
								  <td style="width:70px">${maps.studentUpcompanyid }</td>
								 <td style="width:70px">${maps.department.epartmentName }</td>
								<td style="width:70px">${maps.studentSeq }</td>
								<td style="width:70px">${maps.studentName }</td>
								 <td style="width:70px">${maps.studentCategory }</td>
								 <td style="width:70px">${maps.studentRank }</td>
								<td style="width:70px">${maps.studentType }</td>
								<td style="width:70px">${maps.studentCoin }</td>
								<td style="width:70px">${maps.studentPassword }</td>
								<td style="width:70px">${maps.studentone.studentoneSfzh }</td>
								<td style="width:70px">${maps.studentone.studentoneXb }</td>
								<td style="width:70px">${maps.studentone.studentoneAge }</td>
								<td style="width:70px">${maps.studentone.studentoneXl }</td>
								<td style="width:70px">${maps.studentone.studentoneXw }</td>
								<td style="width:70px">${maps.studentone.studentoneZzmm }</td>
								<td style="width:70px">${maps.studentone.studentoneXzjb }</td>
								<td style="width:70px">${maps.studentone.studentoneZyjszw }</td>
								<td style="width:70px">${maps.studentone.studentoneGrjszw }</td>
								<td style="width:70px">${maps.studentone.studentoneByyxx }</td>
								<td style="width:70px">${maps.studentone.studentoneCsri }</td>
								<td style="width:70px">${maps.studentone.studentoneCjgzsj }</td>
								<td style="width:70px">${maps.studenttwo.studenttwoXzzwmc }</td>
								<td style="width:70px">${maps.studenttwo.studenttwoDnzwmc }</td>
								<td style="width:70px">${maps.studenttwo.studenttwoCsgzhdrzw }</td> 
									
								
								
									<c:if test="${student.courseId==null}">
								<c:if test="${sessionScope.sessionUser.accountCatalog==maps.studentCompanyid &&student.jop!=6}">
									<td class='alignCenter'><input name="button" type="button" class="inputButton" value="修改"
									onclick="updateStudent('${maps.studentId}','${companyId}','${sessionScope.sessionUser.accountType}')" />
								</td>
								</c:if>	
								<c:if test="${sessionScope.sessionUser.accountCatalog!=maps.studentCompanyid &&student.jop!=6}">
									<td class='alignCenter'><input name="button" type="button" class="inputButton" value="修改" style="color:#AFB8C0"/>
								</td>
								</c:if>	
								</c:if>
								
								
								
							<%-- 	<c:if test="${student.jop==6}">
								<td style="width:30px"><input type="checkbox" id="checkboxids"
									name="checkboxids" value="${maps.studentId}" /></td>
									<td class='alignCenter'><input name='button' type='button'
										onclick="diaoru('${maps.studentId}')" class='inputButton'
										value='调入' /></td>
								</c:if> --%>
								<c:if test="${student.courseId==null}">
								<c:if test="${student.jop!=6}">
								
								<c:if test="${sessionScope.sessionUser.accountCatalog==maps.studentCompanyid}">
									<td class='alignCenter'><input name='button' type='button'
										onclick="diaochu('${maps.studentId}','${sessionScope.sessionUser.accountCatalog}','${sessionScope.sessionUser.accountType}')"
										class='inputButton' value='调出' /></td>
								</c:if>	
								<c:if test="${sessionScope.sessionUser.accountCatalog!=maps.studentCompanyid}">
									<td class='alignCenter'><input name='button' type='button'
										 style="color:#AFB8C0"
										class='inputButton' value='调出' /></td>
								</c:if>	
										
								</c:if>
								</c:if>
								
									<td style="width:70px">
								<input name="button" type="button" class="inputButton" value="详请"
									onclick="detailedStudent('${maps.studentId}','${sessionScope.sessionUser.accountCatalog}','${sessionScope.sessionUser.accountType}')" />
								</td>
								
								<%-- <td style="width:70px">${maps.studentSeq }</td>
								<td style="width:70px">${maps.studentName }</td>
								<td style="width:70px">${maps.studentCategory }</td>
								<td style="width:70px">${maps.studentRank }</td>
								<td style="width:70px">${maps.studentType }</td>
								<td style="width:70px">${maps.studentCompany }</td>
								<td style="width:70px">${maps.studentCompanyid }</td>
								<td style="width:70px">${maps.studentDepartment }</td>
								<td style="width:70px">${maps.studentCoin }</td>
								<td style="width:70px">${maps.studentYeartime }</td>


								<td style="width:70px">${maps.studentTelephone }</td>
								<td style="width:70px">${maps.studentEmail }</td>
								<td style="width:70px">${maps.studentPassword }</td>
								<td style="width:70px">${maps.studentBxyxtime }</td>
								<td style="width:70px">${maps.studentBxxxtime }</td>
								<td style="width:70px">${maps.studentYxxxtime }</td>
								<td style="width:70px">${maps.studentXxxxtime }</td>

								<!-- 人员详细信息表1 -->
								<td style="width:70px">${maps.studentone.studentoneSfzh }</td>
								<td style="width:70px">${maps.studentone.studentoneXb }</td>
								<td style="width:70px">${maps.studentone.studentoneAge }</td>
								<td style="width:70px">${maps.studentone.studentoneRylb }</td>
								<td style="width:70px">${maps.studentone.studentoneXl }</td>
								<td style="width:70px">${maps.studentone.studentoneXw }</td>
								<td style="width:70px">${maps.studentone.studentoneZzmm }</td>
								<td style="width:70px">${maps.studentone.studentoneYgxs }</td>
								<td style="width:70px">${maps.studentone.studentoneXzjb }</td>
								<td style="width:70px">${maps.studentone.studentoneZyjszw }</td>
								<td style="width:70px">${maps.studentone.studentoneGrjszw }</td>


								<td style="width:70px">${maps.studentone.studentoneSxzy }</td>
								<td style="width:70px">${maps.studentone.studentoneByy }</td>
								<td style="width:70px">${maps.studentone.studentoneByyxx }</td>
								<td style="width:70px">${maps.studentone.studentoneYx }</td>
								<td style="width:70px">${maps.studentone.studentoneXwsydw }</td>
								<td style="width:70px">${maps.studentone.studentoneQrzhzz }</td>
								<td style="width:70px">${maps.studentone.studentoneSfdqxl }</td>
								<td style="width:70px">${maps.studentone.studentoneSfdqxw }</td>
								<td style="width:70px">${maps.studentone.studentoneCsri }</td>
								<td style="width:70px">${maps.studentone.studentoneCjgzsj }</td>
								<td style="width:70px">${maps.studentone.studentoneXdwgzsj
									}</td>
								<td style="width:70px">${maps.studentone.studentoneRxsj }</td>
								<td style="width:70px">${maps.studentone.studentoneByysj }</td>

								<!-- 人员详细信息表2 -->
								<td style="width:70px">${maps.studenttwo.studenttwoZwlb }</td>
								<td style="width:70px">${maps.studenttwo.studenttwoXzzwmc }</td>
								<td style="width:70px">${maps.studenttwo.studenttwoDnzwmc }</td>
								<td style="width:70px">${maps.studenttwo.studenttwoDyjb }</td>
								<td style="width:70px">${maps.studenttwo.studenttwoZwsx }</td>
								<td style="width:70px">${maps.studenttwo.studenttwoSfdqzw }</td>
								<td style="width:70px">${maps.studenttwo.studenttwoSfzcrtzj
									}</td>
								<td style="width:70px">${maps.studenttwo.studenttwoZyjszgmc
									}</td>
								<td style="width:70px">${maps.studenttwo.studenttwoZyjsrzzg
									}</td>
								<td style="width:70px">${maps.studenttwo.studenttwoZylb }</td>
								<td style="width:70px">${maps.studenttwo.studenttwoPrzyjszwmc
									}</td>
								<td style="width:70px">${maps.studenttwo.studenttwoSfdqzyjszw
									}</td>

								<td style="width:70px">${maps.studenttwo.studenttwoGzmc }</td>
								<td style="width:70px">${maps.studenttwo.studenttwoGrjszg }</td>
								<td style="width:70px">${maps.studenttwo.studenttwoPrgrjszw
									}</td>
								<td style="width:70px">${maps.studenttwo.studenttwoSzdw }</td>
								<td style="width:70px">${maps.studenttwo.studenttwoCsgzhdrzw
									}</td>
								<td style="width:70px">${maps.studenttwo.studenttwoYclb }</td>
								<td style="width:70px">${maps.studenttwo.studenttwoYcyy }</td>

								<td style="width:70px">${maps.studenttwo.studenttwoRzsj }</td>
								<td style="width:70px">${maps.studenttwo.studenttwoMzsj }</td>
								<td style="width:70px">${maps.studenttwo.studenttwoQdsj }</td>
								<td style="width:70px">${maps.studenttwo.studenttwoPrqssj }</td>
								<td style="width:70px">${maps.studenttwo.studenttwoPrzzsj }</td>
								<td style="width:70px">${maps.studenttwo.studenttwoQdzgsj }</td>
								<td style="width:70px">${maps.studenttwo.studenttwoPrsj }</td>
								<td style="width:70px">${maps.studenttwo.studenttwoGrjlqssj
									}</td>
								<td style="width:70px">${maps.studenttwo.studenttwoGrjljssj
									}</td>
								<td style="width:70px">${maps.studenttwo.studenttwoCjdpsj }</td>

								<!-- 人员详细信息表3 -->
								<td style="width:70px">${maps.studenthree.studenthreeLxzt }</td>
								<td style="width:70px">${maps.studenthree.studenthreePcdw }</td>
								<td style="width:70px">${maps.studenthree.studenthreeZy }</td>
								<td style="width:70px">${maps.studenthree.studenthreeLxsf }</td>
								<td style="width:70px">${maps.studenthree.studenthreeLxgb }</td>
								<td style="width:70px">${maps.studenthree.studenthreePxxmmc
									}</td>
								<td style="width:70px">${maps.studenthree.studenthreePxts }</td>
								<td style="width:70px">${maps.studenthree.studenthreePxlx }</td>
								<td style="width:70px">${maps.studenthree.studenthreePxqd }</td>
								<td style="width:70px">${maps.studenthree.studenthreeLtlb }</td>
								<td style="width:70px">${maps.studenthree.studenthreeGbjdq
									}</td>
								<td style="width:70px">${maps.studenthree.studenthreeCgsy }</td>
								<td style="width:70px">${maps.studenthree.studenthreeCgsqdw
									}</td>
								<td style="width:70px">${maps.studenthree.studenthreeCgpcdw
									}</td>


								<td style="width:70px">${maps.studenthree.studenthreeBj }</td>
								<td style="width:70px">${maps.studenthree.studenthreeLdgbbz
									}</td>
								<td style="width:70px">${maps.studenthree.studenthreeLdhbbz
									}</td>
								<td style="width:70px">${maps.studenthree.studenthreeXhgbbz
									}</td>
								<td style="width:70px">${maps.studenthree.studenthreeDjsbz
									}</td>
								<td style="width:70px">${maps.studenthree.studenthreeLxsj }</td>
								<td style="width:70px">${maps.studenthree.studenthreeLtxsj
									}</td>
								<td style="width:70px">${maps.studenthree.studenthreeCgsj }</td>
								<td style="width:70px">${maps.studenthree.studenthreeJhhgsj
									}</td>
								<td style="width:70px">${maps.studenthree.studenthreeSjhgsj
									}</td> --%>

							</tr>
						</c:forEach>
						
					</tbody>
				</table>
				</form>
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
