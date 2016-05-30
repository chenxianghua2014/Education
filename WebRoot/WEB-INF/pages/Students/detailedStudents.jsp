<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>查看学员信息</title>
<!-- 表格风格样式  -->
<link href="css/skins/default/main.css" rel="stylesheet" type="text/css" />
<!-- 上传图片插件     -->
<link rel="stylesheet" href="js/uploadify/css/uploadify.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/uploadify/jquery.uploadify-3.1.min.js"></script>
<script type="text/javascript" src="js/uploadPlan_pic.js"></script>

<!-- 日期 -->
<script type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>

<!-- 校验 -->
<script type="text/javascript" src="<%=path%>/js/Validform_v5.3.2_min.js"></script>
	

	<script type="text/javascript">
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
							var html = " <select  style='width:200px;' id='studenttwoGzmc'  name='studenttwoGzmc'  >"
									+ "<option value='' selected=\"selected\">请选择</option>";

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
	</script>
</head>
<body>
<input type="hidden" id="purl" name="newsSort" value="<%=request.getSession().getServletContext().getRealPath("/UploadFile")%>" /> 
<input type="hidden" id="purl1" name="newsSort" value="<%=request.getSession().getServletContext().getRealPath("/")%>" /> 
	<div class="title">当前位置:学员管理>学员详细信息</div>
	<div class="editBlock">
		<form action="updateStudentSava" method="post" id="fff" class="registerform"  name="fff">
			<input type="hidden" id="cj" name="cj" value="${cj}" /> 
			<input type="hidden" id="organizationId" name="organizationId" value="${organizationId}" /> 
			<input type="hidden" id="pageId" name="pageId" value="${pageId}" /> 
			<input id="studentId" name="studentId" type="hidden" class="inputText" style="width:300px;"  value="${student.studentId}" />
			<input id="studentoneId" name="studentoneId" type="hidden" class="inputText" style="width:300px;"  value="${studentone.studentoneId}" />
			<input id="studenttwoId" name="studenttwoId" type="hidden" class="inputText" style="width:300px;"  value="${studenttwo.studenttwoId}" />
			<input id="studenthreeId" name="studenthreeId" type="hidden" class="inputText" style="width:300px;"  value="${studenthree.studenthreeId}" />
			<input id="studentAt" name="studentAt" type="hidden" class="inputText" style="width:300px;"  value="${student.studentAt}" />
			<input id="studentDel" name="studentDel" type="hidden" class="inputText" style="width:300px;"  value="${student.studentDel}" />
			<input id="studentoneAt" name="studentoneAt" type="hidden" class="inputText" style="width:300px;"  value="${studentone.studentoneAt}" />
			<input id="studentoneDel" name="studentoneDel" type="hidden" class="inputText" style="width:300px;"  value="${studentone.studentoneDel}" />
			<input id="studenttwoAt2" name="studenttwoAt2" type="hidden" class="inputText" style="width:300px;"  value="${studenttwo.studenttwoAt2}" />
			<input id="studenttwoDel2" name="studenttwoDel2" type="hidden" class="inputText" style="width:300px;"  value="${studenttwo.studenttwoDel2}" />
			<input id="studenthreeAt" name="studenthreeAt" type="hidden" class="inputText" style="width:300px;"  value="${studenthree.studenthreeAt}" />
			<input id="studenthreeDel" name="studenthreeDel" type="hidden" class="inputText" style="width:300px;"  value="${studenthree.studenthreeDel}" />
			<table>
				<tr>
					<td colspan="4" class="subtitle">查看学员详细信息</td>
				</tr>
				<tbody>

					<tr >
						<th width="150">学员编号:</th>
						<td>${student.studentSeq }</td>
						<th width="150">学员密码:</th>
						<td>${student.studentPassword }</td>
					</tr>
					<tr >
						<th width="150">学员姓名:</th>
						<td>${student.studentName }</td>
						<th width="150">学员人员类别:</th>
						<td>${student.studentCategory }</td>
					</tr>
					<tr >
						<th width="150">学员级别:</th>
						<td>${student.studentRank }</td>
						<th width="150">手机号码:</th>
						<td>${student.studentTelephone }</td>
					</tr>
					<tr >
						<th width="150">电子邮箱:</th>
						<td>${student.studentTelephone }</td>
						<th width="150">人员类别:</th>
						<td>${studentone.studentoneRylb }</td>
					</tr>
					<tr >
						<th width="150">身份证号:</th>
						<td>${studentone.studentoneSfzh }</td>
						<th width="150">性别:</th>
						<td>
								<c:forEach items="${ax}" var="maps" varStatus="status">
								<c:if test="${studentone.studentoneXb==maps.dictionaryCode}">
								${maps.dictionaryDescribe}
								</c:if>
								</c:forEach>
						</td>
					</tr>
					<tr >
						<th width="150">年龄 :</th>
						<td>${studentone.studentoneAge}</td>
						<th width="150">学历:</th>
						<td>
								<c:forEach items="${am}" var="maps" varStatus="status">
								<c:if test="${studentone.studentoneXl==maps.dictionaryCode}">
								${maps.dictionaryDescribe}
								</c:if>
								</c:forEach>
						</td>
					</tr>
					<tr >
						<th width="150">学位 :</th>
						<td>
								<c:forEach items="${an}" var="maps" varStatus="status">
									<c:if test="${studentone.studentoneXw==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</td>
						<th width="150">政治面貌:</th>
						<td>
								<c:forEach items="${AT}" var="maps" varStatus="status">
								<c:if test="${studentone.studentoneZzmm==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</td>
					</tr>
					<tr >
						<th width="150">用工形式 :</th>
						<td>
								<c:forEach items="${XA}" var="maps" varStatus="status">
								<c:if test="${studentone.studentoneYgxs==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</td>
						<th width="150">行政级别:</th>
						<td>${studentone.studentoneXzjb}</td>
					</tr>
					<tr >
						<th width="150">专业技术职务:</th>
						<td>${studentone.studentoneZyjszw}</td>
						<th width="150">工人技术职务:</th>
						<td>${studentone.studentoneGrjszw}</td>
					</tr>
					<tr >
						<th width="150">所学专业:</th>
						<td>${studentone.studentoneSxzy}</td>
						<th width="150">毕（肄）业:</th>
						<td>
								<c:forEach items="${HY}" var="maps" varStatus="status">
								<c:if test="${studentone.studentoneByy==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</td>
					</tr>
					<tr >
						<th width="150">毕（肄）业学校或单位:</th>
						<td>${studentone.studentoneByyxx}</td>
						<th width="150">院（系）:</th>
						<td>${studentone.studentoneYx}</td>
					</tr>
					<tr >
						<th width="150">学位授予单位:</th>
						<td>${studentone.studentoneXwsydw}</td>
						<th width="150">全日制或在职:</th>
						<td>
							<c:if test="${studentone.studentoneQrzhzz==1}">
							全日制
							</c:if>
							<c:if test="${studentone.studentoneQrzhzz==2}">
							在职
							</c:if>
						</td>
					</tr>
					<tr >
						<th width="150">是否当前学历:</th>
						<td>
						<c:if test="${studentone.studentoneSfdqxl==1}">
							是
							</c:if>
							<c:if test="${studentone.studentoneSfdqxl==2}">
							否
							</c:if>
						</td>
						<th width="150">是否当前学位:</th>
						<td>
						<c:if test="${studentone.studentoneSfdqxw==1}">
							是
							</c:if>
							<c:if test="${studentone.studentoneSfdqxw==2}">
							否
							</c:if>
						</td>
					</tr>
					<tr >
						<th width="150">出生日期:</th>
						<td>
						${studentone.studentoneCsri}
						</td>
						<th width="150">参加工作时间:</th>
						<td>
						${studentone.studentoneCjgzsj}
						</td>
					</tr>
					<tr >
						<th width="150">现单位工作时间:</th>
						<td>
						${studentone.studentoneXdwgzsj}
						</td>
						<th width="150">入学时间:</th>
						<td>
						${studentone.studentoneRxsj}
						</td>
					</tr>
					<tr >
						<th width="150">毕（肄）业时间:</th>
						<td>
						${studentone.studentoneByysj}</td>
						<th width="150">职务类别:</th>
						<td>
								<c:forEach items="${BQ}" var="maps" varStatus="status">
								<c:if test="${studenttwo.studenttwoZwlb==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</td>
						
					</tr>
					<tr >
						<th width="150">行政职务名称:</th>
						<td>${studenttwo.studenttwoXzzwmc}</td>
						<th width="150">党内职务名称:</th>
						<td>${studenttwo.studenttwoDnzwmc}</td>
					</tr>
					<tr >
						<th width="150">待遇级别:</th>
						<td>
								<c:forEach items="${OZ}" var="maps" varStatus="status">
								<c:if test="${studenttwo.studenttwoDyjb==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</td>
						<th width="150">职务属性:</th>
						<td>
								<c:forEach items="${IR}" var="maps" varStatus="status">
								<c:if test="${studenttwo.studenttwoZwsx==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</td>
					</tr>
					<tr >
						<th width="150">是否当前职务:</th>
						<td>
						<c:if test="${studenttwo.studenttwoSfdqzw==1}">
							是
							</c:if>
							<c:if test="${studenttwo.studenttwoSfdqzw==2}">
							否
							</c:if>
						</td>
						<th width="150">是否最初任同职级:</th>
						<td>
							<c:if test="${studenttwo.studenttwoSfzcrtzj==1}">
							是
							</c:if>
							<c:if test="${studenttwo.studenttwoSfzcrtzj==2}">
							否
							</c:if>
						</td>
					</tr>
					<tr >
						<th width="150">专业技术资格名称:</th>
						<td>${studenttwo.studenttwoZyjszgmc}</td>
						<th width="150">专业技术任职资格:</th>
						<td>${studenttwo.studenttwoZyjsrzzg}</td>
					</tr>
					<tr >
						<th width="150">专业类别:</th>
						<td>
								<c:forEach items="${YC}" var="maps" varStatus="status">
								<c:if test="${studenttwo.studenttwoZylb==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</td>
						<th width="150">聘任专业技术职务名称:</th>
						<td>${studenttwo.studenttwoPrzyjszwmc}</td>
					</tr>
					<tr >
						<th width="150">是否当前专业技术职务:</th>
						<td>
						<c:if test="${studenttwo.studenttwoSfdqzyjszw==1}">
							是
							</c:if>
							<c:if test="${studenttwo.studenttwoSfdqzyjszw==2}">
							否
							</c:if>
						</td>
						<th width="150">参加党派时间:</th>
						<td>
						${studenttwo.studenttwoCjdpsj}
						</td>
						
					</tr>
					<tr >
						<th width="150">工人技术资格:</th>
						<td>
								<c:forEach items="${XJ}" var="maps" varStatus="status">
								<c:if test="${studenttwo.studenttwoGrjszg==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</td>
						<th width="150">聘任工人技术职务:</th>
						<td>${studenttwo.studenttwoPrgrjszw}</td>
					</tr>
					<tr >
						<th width="150">所在单位:</th>
						<td>${studenttwo.studenttwoSzdw}</td>
						<th width="150">从事工作或担任职务:</th>
						<td>${studenttwo.studenttwoCsgzhdrzw}</td>
					</tr>
					<tr >
						<th width="150">异常类别:</th>
						<td>
								<c:forEach items="${CA}" var="maps" varStatus="status">
									<c:if test="${studenttwo.studenttwoYclb==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</td>
						<th width="150">异常原因:</th>
						<td>${studenttwo.studenttwoYcyy}</td>
					</tr>
					<tr >
						<th width="150">任职时间:</th>
						<td>
						${studenttwo.studenttwoRzsj}
						</td>
						<th width="150">免职时间:</th>
						<td>
					    ${studenttwo.studenttwoMzsj}
						</td>
					</tr>
					<tr >
						<th width="150">聘任起始时间:</th>
						<td>
						${studenttwo.studenttwoPrqssj}
						</td>
						<th width="150">聘任终止时间:</th>
						<td>
						${studenttwo.studenttwoPrzzsj}
						</td>
					</tr>
					<tr >
						<th width="150">取得资格时间:</th>
						<td>
						${studenttwo.studenttwoQdzgsj}
						</td>
						<th width="150">聘任时间:</th>
						<td>
						${studenttwo.studenttwoPrsj}
						</td>
					</tr>
					<tr >
						<th width="150">个人简历起始时间:</th>
						<td>
						${studenttwo.studenttwoGrjlqssj}
						</td>
						<th width="150">个人简历结束时间:</th>
						<td>
						${studenttwo.studenttwoGrjljssj}
						</td>
					</tr>
					<tr >
						<th width="150">工种名称:</th>
						<td>
								<c:forEach items="${JATop}" var="maps" varStatus="status">
								<c:if test="${studenttwo.studenttwoGzmcSx==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</td>
						
						<th width="150">工种名称筛选（子集）:</th>
						<td>
						<div id="studenttwoGzmcId">
								<c:forEach items="${JA}" var="maps" varStatus="status">
								<c:if test="${studenttwo.studenttwoGzmc==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</div>
						</td>
					</tr>
					<tr >
						<th width="150">留学状态:</th>
						<td>
								<c:forEach items="${XR}" var="maps" varStatus="status">
								<c:if test="${studenthree.studenthreeLxzt==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</td>
						<th width="150">派出单位:</th>
						<td>${studenthree.studenthreePcdw}</td>
					</tr>
					<tr >
						<th width="150">专业:</th>
						<td>${studenthree.studenthreeZy}</td>
						<th width="150">留学身份:</th>
						<td>
								<c:forEach items="${XS}" var="maps" varStatus="status">
								<c:if test="${studenthree.studenthreeLxsf==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</td>
					</tr>
					<tr >
						<th width="150">留学国别:</th>
						<td>
								<c:forEach items="${AD}" var="maps" varStatus="status">
								<c:if test="${studenthree.studenthreeLxgb==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</td>
						<th width="150">培训项目名称:</th>
						<td>${studenthree.studenthreePxxmmc}</td>
					</tr>
					<tr >
						<th width="150">培训天数:</th>
						<td>${studenthree.studenthreePxts}</td>
						<th width="150">培训类型:</th>
						<td>
								<c:forEach items="${YG}" var="maps" varStatus="status">
								<c:if test="${studenthree.studenthreePxlx==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</td>
					</tr>
					<tr >
						<th width="150">培训渠道:</th>
						<td>
								<c:forEach items="${YH}" var="maps" varStatus="status">
								<c:if test="${studenthree.studenthreePxqd==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</td>
						<th width="150">离退类别:</th>
						<td>
								<c:forEach items="${HD}" var="maps" varStatus="status">
									<c:if test="${studenthree.studenthreeLtlb==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</td>
					</tr>
					<tr >
						<th width="150">国别及地区:</th>
						<td>
								<c:forEach items="${AD}" var="maps" varStatus="status">
								<c:if test="${studenthree.studenthreeGbjdq==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</td>
						<th width="150">出国（境）事由:</th>
						<td>${studenthree.studenthreeCgsy}</td>
					</tr>
					<tr >
						<th width="150">出国所去单位:</th>
						<td>${studenthree.studenthreeCgsqdw}</td>
						<th width="150">出国派出单位:</th>
						<td>${studenthree.studenthreeCgpcdw}</td>
					</tr>
					<tr >
						<th width="150">备注:</th>
						<td>${studenthree.studenthreeBj}</td>
						<th width="150">领导干部标识:</th>
						<td>
								<c:forEach items="${OC}" var="maps" varStatus="status">
								<c:if test="${studenthree.studenthreeLdgbbz==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</td>
					</tr>
					<tr >
						<th width="150">领导后备标识:</th>
						<td>
								<c:forEach items="${OD}" var="maps" varStatus="status">
									<c:if test="${studenthree.studenthreeLdhbbz==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</td>
						<th width="150">型号干部标识:</th>
						<td>
								<c:forEach items="${OE}" var="maps" varStatus="status">
								<c:if test="${studenthree.studenthreeXhgbbz==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</td>
					</tr>
					<tr >
						<th width="150">董监事标识:</th>
						<td>
								<c:forEach items="${OG}" var="maps" varStatus="status">
								<c:if test="${studenthree.studenthreeDjsbz==maps.dictionaryCode}">
									${maps.dictionaryDescribe}
									</c:if>
								</c:forEach>
						</td>
						<th width="150">学员状态:</th>
						<td>
						<c:if test="${student.studentType==1}">
							在职
							</c:if>
							<c:if test="${student.studentType==2}">
							游离
							</c:if>
						</td>
					</tr>
					<tr >
						<th width="150">留学时间:</th>
						<td>
						${studenthree.studenthreeLxsj}
						</td>
						<th width="150">离（退）休时间:</th>
						<td>
						${studenthree.studenthreeLtxsj}
						</td>
					</tr>
					<tr >
						<th width="150">出国时间:</th>
						<td>
						${studenthree.studenthreeCgsj}
						</td>
						<th width="150">计划回国时间:</th>
						<td>
						${studenthree.studenthreeJhhgsj}
						</td>
					</tr>
					<tr >
						<th width="150">实际回国时间:</th>
						<td>
						${studenthree.studenthreeSjhgsj}
						</td>
						<th width="150">学习币:</th>
						<td>${student.studentCoin}</td>
					</tr>
					<tr >
						<th width="150">取得时间:</th>
						<td>
						${studenttwo.studenttwoQdsj}
						</td>
						
					</tr>
				</tbody>
				<tr>
					
						<td colspan="4" class="toolbar">
						&nbsp;&nbsp; <input
						type="button" class="inputButton" value="返回"
						onclick="history.back()" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
		 $(function(){
	//$(".registerform").Validform();  //就这一行代码！;
	$.Tipmsg.tit="提示信息";
	var demo=$(".registerform").Validform();
	demo.tipmsg.s="请填写内容";
});

	function saveNew(){
		
		var studentPassword = document.getElementById("studentPassword");
		var studentName = document.getElementById("studentName");
		var studentCategory = document.getElementById("studentCategory");
		
		if(studentPassword==""){
			alert("请输入密码！");
			return;
		}
		if(studentName==""){
			alert("请输入学员姓名！");
			return;
		}
		if(studentCategory==""){
			alert("请输入学员人员类别！");
			return;
		}
		$("#fff").submit();
		
	} 
	
	function radioType(){
		   var chk = 0;

                var chkObjs = document.getElementsByName('newsTop');
                for(var i=0;i<chkObjs.length;i++){
                    if(chkObjs[i].checked){
                        chk = i;
                        break;
                    }
                }
		  if(chk==1){
			  document.getElementById("toptype").style.display="";
			  
		  }else{
			  document.getElementById("toptype").style.display="none";
		  }
	 }
	

</script>
</html>
