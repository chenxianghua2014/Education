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

<!-- 下拉日期 -->
<script type="text/javascript" charset="utf-8" src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function Addclass(sjid,rank,type) {
		window.location = "addEditPage?pageClassId="+sjid+"&classresourceRank="+rank+"&classresourceUpname="+type;
	}
	
 	 function deleteClass(classresourceId) {
	if (confirm("你确定要删除该条记录吗?")) {
	$.ajax({
			url : "deleteClass",
			type : 'GET',
			data : {
				classresourceId : classresourceId
			},
			success : function(data){
				if(data=="ok"){
					alert("删除成功");
					window.location.reload();
				}else{
					alert("删除失败");
					window.location.reload();
				}
			}
		  });
}  
 	 }
function editClass(classresourceId,rank) {
	window.location = "addEditPage?id=" + classresourceId+"&classresourceRank="+rank;
}
//src="queryClassCatalog?mark=1&organizationId="+n+"&page=1&cj="+i;
function classOnes(classresourceId,cj) {
	/* alert(classresourceId)
	alert(cj) */
	window.location = "queryShift?classresourceId=" + classresourceId+"&mark=1&page=1&cj="+cj;
}
 
function examination(clastudentId,tranclassId,studentId){
    $.ajax({          
          url:"StudentExamination",  
          type : "post",
          data:{clastudentId : clastudentId,
        	  tranclassId : tranclassId,
        	  studentId : studentId
          },  
          error:function(){  
             alert("error occured!!!");  
          },  
          success:function(data){
          if(data!="")
          {
            alert('审核通过成功');
             window.location.reload(); 
          /*   window.location = "queryShift?classresourceId=" + classresourceId +"&mark=1&page=1&cj="+cj+"&classlb="+classlb;   */
          }
          else
          {
             alert('审核通过失败');
             history.back(-1);
          }
    
        }  
    }); 
}
function studentOnes(studentId) {

	window.location = "queryInformation?studentId=" + studentId;
}

/*  	function saveWeddingphoto() {
		document.ff.action = "saveWeddingphoto";
		document.ff.method = "post";
		document.ff.submit();
	}  */
	function fanhui(classresourceId,cj,classlb) {
  		/* alert(classresourceId)
		alert(cj) */
		window.location.href = "queryShift?classresourceId=" + classresourceId +"&mark=1&page=1&cj="+cj+"&classlb="+classlb+"&num="+Math.random();
	}
</script>
</head>
<body>
	<div class="title">当前位置:培训班管理>学员</div>
	<div class="editBlock">
		<form action="journalism" method="get" name="userForm" id="userForm">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">学员</td>
				</tr>
				 <tr>
				 	<td style="text-align: left"><input name="button" type="button"
						class="inputButton" value="返回"   onclick="fanhui('${tranc.classresourceId}','${Clastudents.cj}','${Clastudents.classlb}')"/></td>
					<td style="text-align: right;">
					<input type="hidden" id="organizationId" name="organizationId" value="${classCatalog.organizationId}" />
					<input type="hidden" id="classresourceRank" name="classresourceRank" value="${cj}" />
					<input type="hidden" id="classresourceUpname" name="classresourceUpname" value="${classCatalog.classresourceUpname}" />
					&nbsp;
					<%--           培训班名称:<input type="text" name="newsCatalog" class="inputText" id="newsCatalog" value="${informationPage.newsCatalog}"/> &nbsp;
						<input name="button" type="submit"  class="inputButton" value="查询" /> --%>
						
					</td> 
				</tr>
			</tbody>
		</table>
	    </form>
	</div>
	<div class="dataGrid">
		<table>
			<caption>培训班查询结果</caption>
			<thead>
				<tr>
				     <th>序      号</th> 
				    <th>培训班编号</th>
					<th>学员名称</th>
					 <th width="60" class="alignCenter">学员详情</th>
					 <c:if test="${Clastudents.type!=1}">
					<th width="60" class="alignCenter">学员审核</th>
					</c:if> 
				</tr>
			</thead>
			<tbody id="">
			    <c:forEach items="${pageBean.list}" var="maps" varStatus="status">
				<tr>
					<td>${status.count}</td> 
					<td>${maps.tranclassId }</td>
					<td>${maps.clastudentName }</td>
					<td class='alignCenter'><input name='button'type='button' onclick="studentOnes('${maps.studentId}','${cj}')" class='inputButton' value='详情' /></td>
					 <c:if test="${Clastudents.type!=1}">
					<td class='alignCenter'><input name='button'type='button' onclick="examination('${maps.clastudentId}','${maps.tranclassId}','${maps.studentId}')" class='inputButton' value='通过' /></td>
					 </c:if> 
					<%-- <td class='alignCenter'><input name='button'type='button' onclick="deleteClass('${maps.classresourceId}')" class='inputButton' value='删除' /></td> --%> 
				</tr>
				</c:forEach> 
			</tbody>
		</table>
		<div id="kkpager"></div><br/>
	</div>
</body>
<script type="text/javascript">

var cfg = /\?currentPage=\d{0,5}/;
var cfg2 = /\&currentPage=\d{0,5}/;
var href = window.location.href;

 if(cfg.test(href)){
	 href = href.replace(cfg,'?')+'&';
	}else if(cfg2.test(href)){
	href = href.replace(cfg2,'')+'&';
	
}else{
	if(href.indexOf('?') > -1){
		href = href + '&';
	}else{
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
    getLink : function(n){
        if(n == 1){
            return this.hrefFormer + this.hrefLatter;
        }
        return this.hrefFormer + n;
    }

});
 
</script>
</html>
