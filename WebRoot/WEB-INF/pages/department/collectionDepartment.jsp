<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="<%=path%>/css/skins/default/main.css" rel="stylesheet" type="text/css" />
<!-- <link rel="stylesheet" href="resources/pagination/pagination.css" type="text/css"></link> -->
<script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.9.1.min.js"></script>
<!-- <script type="text/javascript" src="js/common.js"></script> -->

<!-- 日期 -->
<script type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
<!-- 分页 -->
<script type="text/javascript" src="<%=path%>/css/pager/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/css/pager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/pager/kkpager_blue.css" />
<script type="text/javascript">
	
 	 function deleteProgramme(id) {
	if (confirm("你确定要删除该条记录吗?")) {
	$.ajax({
			url : "deleteDepartment",
			type : 'GET',
			data : {
				id: id
			},
			success : function(data){
				if(data=="ok"){
					alert("删除成功");
					window.location.reload();
				}else if(data=="sno"){
					alert("删除失败,部门中有学员");
					window.location.reload();
				}else if(data=="dno"){
					alert("删除失败,部门中有科室");
					window.location.reload();
				}else{
					alert("删除失败");
					window.location.reload();
				}
			}
		  });
}  
 	 }
function editNew(newsId) {
	window.location = "departmentSkipID?id=" + newsId+"&mark=0";
}


function collection(id){
      $.ajax({          
            url:"collectionDelDepartment",  
            type : "post",
            data:{departmentId : id},  
            error:function(){  
               alert("error occured!!!");  
            },  
            success:function(data){
            if(data!="")
            {
            
            	alert('取消收藏成功');
            
              
              window.location.reload();
            }
            else
            {
               alert('取消失败');
                window.location.reload();
            }
      
          }  
      }); 
 } 
function loadData() {
	$.ajax({
		type : 'POST',
		url : "newsDataCount",
		data : {
			/* userName : $("#userName").val(), */
			newsCatalog : $("#newsCatalog").val(),
			newsRank : $("#newsRank").val(),
			zhuanNewsTime : $("#zhuanNewsTime").val()
		},
		dataType : "json",
		success : function(count) {
			dataCount = count;
			var optInit = getOptionsFromForm();
			$("#Pagination").pagination(dataCount, optInit);
			$("#setoptions").click(function() {
				var opt = getOptionsFromForm();
				$("#Pagination").pagination(dataCount, opt);
			});
		}
	});
}



/*  	function saveWeddingphoto() {
		document.ff.action = "saveWeddingphoto";
		document.ff.method = "post";
		document.ff.submit();
	}  */
</script>
</head>
<body>
	<div class="title">当前位置:部门管理>部门收藏</div>
	<div class="editBlock">
		
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">部门收藏</td>
				</tr>
			</tbody>
		</table>
	    
	</div>
	<div class="dataGrid">
		<table>
			<caption>部门查询结果</caption>
			<thead>
				<tr>
				    <!-- <th>序      号</th> -->
				    <th>部门编号</th>
					<th>部门名称</th>
					<th>所在单位</th>
					<th>层级</th>
					<th>所属部门</th>
					<th width="60" class="alignCenter">收藏</th>
					<th width="60" class="alignCenter">修改</th>
					 <th width="60" class="alignCenter">删除</th>  
				</tr>
			</thead>
			<tbody id="">
			    <c:forEach items="${pageBean.list}" var="maps" varStatus="status">
				<tr>
				<%-- 	<td>${status.count}</td> --%>
					<td>${maps.departmentId }</td>
					<td>${maps.epartmentName }</td>
					<td>${maps.organization.organizationDwmc }</td>
					<td>
					<c:if test="${maps.epartmentRank == 4 }">部门</c:if>
					<c:if test="${maps.epartmentRank == 5 }">科室</c:if>
					</td>
					<td>${maps.epartmentUpid }</td>
				
					
					<c:if test="${maps.organizationId==sessionScope.sessionUser.accountCatalog}">
					<td class='alignCenter'><input name='button'type='button' onclick="collection('${maps.departmentId}')" class='inputButton' value='取消' /></td>
					<td class='alignCenter'><input name='button'type='button' onclick="editNew('${maps.departmentId}')" class='inputButton' value='编辑' /></td>
					 <td class='alignCenter'><input name='button'type='button' onclick="deleteProgramme('${maps.departmentId}')" class='inputButton' value='删除' /></td>
					 </c:if> 
					 <c:if test="${maps.organizationId!=sessionScope.sessionUser.accountCatalog}">
					 	<td class='alignCenter'><input name='button'type='button' style="color:#AFB8C0" class='inputButton' value='取消' /></td>
					<td class='alignCenter'><input name='button'type='button' style="color:#AFB8C0" class='inputButton' value='编辑' /></td>
					 <td class='alignCenter'><input name='button'type='button' style="color:#AFB8C0"class='inputButton' value='删除' /></td>
					 </c:if> 
					 
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="kkpager"></div>
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
