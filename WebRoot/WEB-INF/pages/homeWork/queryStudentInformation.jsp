<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/skins/default/main.css" rel="stylesheet" type="text/css" />
<!-- <link rel="stylesheet" href="resources/pagination/pagination.css" type="text/css"></link> -->
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<!-- <script type="text/javascript" src="js/common.js"></script> -->

<script type="text/javascript" src="css/pager/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="css/pager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/pager/kkpager_blue.css" />

<!-- 查询下拉 -->
<link href="css/index.css" rel="stylesheet" type="text/css" />

<!-- 下拉日期 -->
<script type="text/javascript" charset="utf-8" src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">




	</script>
</head>
<body>
	<div class="title">当前位置:学员管理>人员详情</div>
		<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">学员</td>
				</tr>
				 <tr>
				 	<td style="text-align: left"><input name="button" type="button"
						class="inputButton" value="返回"  onclick="history.back()"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid" >
	 <div > 
	 <div style=" overflow:auto; width:1000px; "> 
		<table >
			<caption>学员详细信息</caption>
			<thead>
				<tr>
				<!-- 学员信息表 -->
				    <th style="width:70px">学员编号</th> 
				    <th style="width:70px">学员姓名</th>
				    <th style="width:70px">学员人员类别</th>
				    <th style="width:70px">学员级别</th>
				    <th style="width:70px">学员状态</th>
				    <th style="width:70px">学员所在单位(现在放的是学员等级)</th>
				    <th style="width:70px">学员所属单位编号</th>
				    <th style="width:70px">学员所在部门</th>
				    <th style="width:70px">学习币</th>
				    <th style="width:70px">学员本年度学时</th>
				    
					 <th style="width:70px">学员手机号码</th>
					 <th style="width:70px">学员电子邮箱</th>
					 <th style="width:70px">学员密码</th>
					 <th style="width:70px">必修课应修学时</th>
				     <th style="width:70px">必修课学习进度</th>
				     <th style="width:70px">已选选修课学时</th>
			         <th style="width:70px">选修课学习进度</th>
			         
			    <!-- 人员详细信息表1 -->     
					<th style="width:70px">身份证号</th>
					<th style="width:70px">性别</th>
					<th style="width:70px">年龄</th>
					<th style="width:70px">人员类别</th>
					<th style="width:70px">学历</th>
					<th style="width:70px">学位</th>
					<th style="width:70px">政治面貌</th>
					<th style="width:70px">用工形式</th>
					<th style="width:70px">行政级别</th>
					<th style="width:70px">专业技术职务</th>
					<th style="width:70px">工人技术职务</th>
					
					
					<th style="width:70px">所学专业</th>
					<th style="width:70px">毕肄业</th>
					<th style="width:70px">毕（肄）业学校或单位</th>
					<th style="width:70px">院（系）</th>
					<th style="width:70px">学位授予单位</th>
					<th style="width:70px">全日制或在职</th>
					<th style="width:70px">是否当前学历</th>
					<th style="width:70px">是否当前学位</th>
					<th style="width:70px">出生日期</th>
					<th style="width:70px">参加工作时间</th>
					<th style="width:70px">现单位工作时间</th>
					<th style="width:70px">入学时间</th>
					<th style="width:70px">毕（肄）业时间</th>
					
				<!-- 人员详细信息表2 -->     
					<th style="width:70px">职务类别</th>
					<th style="width:70px">行政职务名称</th>
					<th style="width:70px">党内职务名称</th>
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
					<th style="width:70px">从事工作或担任职务</th>
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
					
					<!-- 人员详细信息表3 -->     
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
					<th style="width:70px">实际回国时间</th>
					
				</tr>
			</thead>
			<tbody id="">
				<tr>
				<!-- 学员信息表 -->
					<td style="width:70px">${maps.studentSeq }</td>
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
					<td style="width:70px">${maps.studentone.studentoneXdwgzsj }</td>
					<td style="width:70px">${maps.studentone.studentoneRxsj }</td>
					<td style="width:70px">${maps.studentone.studentoneByysj }</td>
					
				<!-- 人员详细信息表2 --> 
					<td style="width:70px">${maps.studenttwo.studenttwoZwlb }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoXzzwmc }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoDnzwmc }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoDyjb }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoZwsx }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoSfdqzw }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoSfzcrtzj }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoZyjszgmc }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoZyjsrzzg }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoZylb }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoPrzyjszwmc }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoSfdqzyjszw }</td>
					
					<td style="width:70px">${maps.studenttwo.studenttwoGzmc }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoGrjszg }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoPrgrjszw }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoSzdw }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoCsgzhdrzw }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoYclb }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoYcyy }</td>
					
					<td style="width:70px">${maps.studenttwo.studenttwoRzsj }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoMzsj }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoQdsj }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoPrqssj }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoPrzzsj }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoQdzgsj }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoPrsj }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoGrjlqssj }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoGrjljssj }</td>
					<td style="width:70px">${maps.studenttwo.studenttwoCjdpsj }</td>
					
					 <!-- 人员详细信息表3 --> 
				    <td style="width:70px">${maps.studenthree.studenthreeLxzt }</td>
				    <td style="width:70px">${maps.studenthree.studenthreePcdw }</td>
				    <td style="width:70px">${maps.studenthree.studenthreeZy }</td>
				    <td style="width:70px">${maps.studenthree.studenthreeLxsf }</td>
				    <td style="width:70px">${maps.studenthree.studenthreeLxgb }</td>
				    <td style="width:70px">${maps.studenthree.studenthreePxxmmc }</td>
				    <td style="width:70px">${maps.studenthree.studenthreePxts }</td>
				    <td style="width:70px">${maps.studenthree.studenthreePxlx }</td>
				    <td style="width:70px">${maps.studenthree.studenthreePxqd }</td>
				    <td style="width:70px">${maps.studenthree.studenthreeLtlb }</td>
		            <td style="width:70px">${maps.studenthree.studenthreeGbjdq }</td>
		            <td style="width:70px">${maps.studenthree.studenthreeCgsy }</td>
	                <td style="width:70px">${maps.studenthree.studenthreeCgsqdw }</td>
	                <td style="width:70px">${maps.studenthree.studenthreeCgpcdw }</td>
	               
	               
	                <td style="width:70px">${maps.studenthree.studenthreeBj }</td>
                    <td style="width:70px">${maps.studenthree.studenthreeLdgbbz }</td>
                    <td style="width:70px">${maps.studenthree.studenthreeLdhbbz }</td>
                    <td style="width:70px">${maps.studenthree.studenthreeXhgbbz }</td>
                    <td style="width:70px">${maps.studenthree.studenthreeDjsbz }</td>
                    <td style="width:70px">${maps.studenthree.studenthreeLxsj }</td>
                    <td style="width:70px">${maps.studenthree.studenthreeLtxsj }</td>
                    <td style="width:70px">${maps.studenthree.studenthreeCgsj }</td>
                    <td style="width:70px">${maps.studenthree.studenthreeJhhgsj }</td>
                    <td style="width:70px">${maps.studenthree.studenthreeSjhgsj }</td>
				
				</tr>
			</tbody>
		</table>
	</div>
	</div>
	<div id="kkpager"></div><br/>
	</div>
</body>

</html>
