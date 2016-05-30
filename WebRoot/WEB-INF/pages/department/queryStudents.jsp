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
	function Addnew(id,uid) {
		window.location = "departmentSkipID?id=0&organizationId="+id+"&epartmentRank=5&epartmentUpid="+uid;
	}


</script>

</head>
<body>
	<div class="title">当前位置:学员管理>科室人员详情</div>
	<div class="editBlock">
			<table>
			<tbody>
				<tr>
					<td style="text-align: left"><input name="button" type="button"
						class="inputButton" value="添加科室"  onclick="Addnew('${department.studentCompanyid}','${department.studentDepartment}')"/>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<div>
			<div style=" overflow:auto; width:1000px; ">
				<table>
					<caption>查询结果</caption>
					<thead>
						<tr>
							<!-- 学员信息表 -->
							
							
							<!-- 功能按钮 -->
							<th style="width:70px">序 号</th>
							
							<!-- 整理后的22个字段 -->
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
					
						</tr>
					</thead>
					<tbody id="">
						<c:forEach items="${pageBean.list}" var="maps" varStatus="status">
							<tr>
								<!-- 学员信息表 -->
								<td style="width:70px">${status.count}</td>
								<td style="width:70px">${maps.studentSeq }</td>
								<td style="width:70px">${maps.studentName }</td>
								 <td style="width:70px">${maps.studentCategory }</td>
								<td style="width:70px">${maps.studentRank }111</td>
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
