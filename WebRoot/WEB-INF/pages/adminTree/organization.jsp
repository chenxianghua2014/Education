<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/skins/default/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>

<script type="text/javascript" src="css/pager/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="css/pager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/pager/kkpager_blue.css" />

</head>
<body>
	<div class="title">当前位置:系统管理>组织结构部门管理</div>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">组织结构部门管理</td>
				</tr>
				<tr>
					<td style="text-align: left"><input name="button" type="button"
						class="inputButton" value="添加" onclick="AddXcxx()" /></td>
					<td style="text-align: right;">审核状态:<select
						id="" name="">
							<option value="">全部</option>
							<option value="待审核">待审核</option>
							<option value="已发布">已发布</option>
							<option value="不通过">不通过</option>
					</select>&nbsp;宣传内容:<input type="text" name="keywords" class="inputText"
						id="keywords" /> &nbsp;<input name="button" type="button"
						onclick="loadData()" class="inputButton" value="查询" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>组织结构部门查询结果</caption>
			<thead>
				<tr>
				    <th>序      号</th>
					<th>部门名称</th>
					<th>单位代码</th>
					<th>所在单位</th>
					<th width="60" class="alignCenter">修改</th>
					<!-- <th width="60" class="alignCenter">删除</th> -->
				</tr>
			</thead>
			<tbody id="">
			    <c:forEach items="${organizationList}" var="maps" varStatus="status">
				<tr>
					<td> ${status.count}</td>
					<td>${maps.organizationDwmc }</td>
					<td>${maps.organizationDwdm }</td>
					<td>${maps.organizationDwjc }</td>
					<td class='alignCenter'><input name='button'type='button' onclick="editXcxx('${maps.organizationId}')" class='inputButton' value='编辑' /></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="kkpager"></div><br/>
	</div>
</body>
<script type="text/javascript">
        //生成分页控件  
        kkpager.generPageHtml({
            pno : '${coursePage.currentPage}',
            mode : 'link', //可选，默认就是link
            //总页码  
            total : '${coursePage.totalPage}',  
            //总数据条数  
            totalRecords : '${coursePage.rows}',  
            //链接前部  
            hrefFormer : 'OrganizationalStructure?currentPage=',
            //链接尾部  
            hrefLatter : '1',
            //链接算法
            getLink : function(n){
                if(n == 1){
                    return this.hrefFormer + this.hrefLatter;
                }
                return this.hrefFormer + n;
            }

        });
 
</script>
</html>
