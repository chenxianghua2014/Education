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
	function Addnew() {
		window.location = "OrganizationskipID?id=0";
	}
	
	 	 function tisheng(id) {
	if (confirm("提升之后不可还原和下调，你确定要提升吗？")) {
	$.ajax({
			url : "promotionOrganization",
			type : 'GET',
			data : {
				id: id
			},
			success : function(data){
				if(data=="ok"){
					alert("提升成功");
					window.location.reload();
				}else{
					alert("提升失败");
					window.location.reload();
				}
			}
		  });
}  
 	 }

function editNew(newsId) {
	window.location = "OrganizationskipID?id=" + newsId;
}
function newsOnes(newsId) {
	window.location = "queryProgramme?id=" + newsId;
}
function release(id,type1){
      $.ajax({          
            url:"publishProgramme",  
            type : "post",
            data:{programmeId : id,programmeType:type1},  
            error:function(){  
               alert("error occured!!!");  
            },  
            success:function(data){
            if(data!="")
            {
            if(type1=="可用"){
            	alert('发布成功');
            }else{
            	alert('取消发布成功');
            }
              
              window.location.reload();
            }
            else
            {
               alert('发布失败');
               history.back(-1);
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
	<div class="title">当前位置:系统管理>组织结构管理</div>
	<div class="editBlock">
		
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">组织结构管理</td>
				</tr>
				<tr>
					<td style="text-align: left"><input name="button" type="button"
						class="inputButton" value="添加"  onclick="Addnew()"/>
				   </td>
						
					<td style="text-align: right;">
					<form action="organizationAll?mark=1&page=1" method="post" id="form1" name = "form1" >
					<c:if test="${sessionScope.sessionUser.accountType==1||sessionScope.sessionUser.accountType==5}">
					上级单位:<select id="organizationSjdw" name="organizationSjdw" onchange="document.form1.submit();">
						     <option value="" >全部</option>
							<option value="test001" ${organization.organizationSjdw=='test001' ? "selected":""}>集团公司总部 </option>
							<c:forEach items="${listtype}" var="listtype" varStatus="status">
								<option value="${listtype.organizationId}" ${organization.organizationSjdw==listtype.organizationId  ? "selected":""}>${listtype.organizationDwmc}</option>
							</c:forEach>
							
					</select>
					</c:if>
					&nbsp;
						部门名称:<input type="text" name="organizationDwmc" class="inputText" id="organizationDwmc"  value="${organization.organizationDwmc}" /> &nbsp;
						<input  type="submit"  class="inputButton" value="查询" />
					</form>
						</td>
						
				</tr>
			</tbody>
		</table>
	    
	</div>
	<div class="dataGrid">
		<table>
			<caption>组织结构管理查询结果</caption>
			<thead>
				<tr>
				    <!-- <th>序      号</th> -->
				    <th>单位代码</th>
					<th>部门名称</th>
					<th>上级单位</th>
					<th>单位负责人</th>
					<th>联系电话</th>
					<th>邮箱</th>

					<th width="60" class="alignCenter">修改</th>
					<c:if test="${sessionScope.sessionUser.accountType==1||sessionScope.sessionUser.accountType==5}">
					<th width="60" class="alignCenter">层级调整</th>
					</c:if>
				</tr>
			</thead>
			<tbody id="">
			    <c:forEach items="${pageBean.list}" var="maps" varStatus="status">
				<tr>
					<td>${maps.organizationDwdm}</td>
					<td>${maps.organizationDwmc}</td>
					
					<td>
					 <c:forEach items="${listtype}" var="listtype" varStatus="status">
					 <c:if test="${maps.organizationSjdw==listtype.organizationId}">
						 ${listtype.organizationDwmc}
					 </c:if>
					 </c:forEach>
					 <c:if test="${maps.organizationSjdw=='test001'}">
						 集团公司总部
					 </c:if>
					
					</td>
					<td>${maps.organizationDwfzr}</td>
					<td>${maps.organizationLxrdh}</td>
					<td>${maps.organizationLxremail}</td>
			
					<td class='alignCenter'><input name='button'type='button' onclick="editNew('${maps.organizationId}')" class='inputButton' value='编辑' /></td>
					<c:if test="${sessionScope.sessionUser.accountType==1||sessionScope.sessionUser.accountType==5}">
					<c:if test="${maps.organizationSjdw != 'test001' && maps.organizationId != 'test001'}">
					<td class='alignCenter'><input name='button'type='button' onclick="tisheng('${maps.organizationId}')" class='inputButton' value='提升' /></td>
					</c:if>
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
