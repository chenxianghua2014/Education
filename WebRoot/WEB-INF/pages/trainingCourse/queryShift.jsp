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
	function AddShift(sjid,rank,ciasslb) {
		window.location = "addEditShiftPage?pageShiftId="+sjid+"&tranclassRank="+rank+"&tranclassCategroy="+ciasslb;
	}
	function editShift(classresourceId,rank) {
		window.location = "addEditShiftPage?id=" + classresourceId+"&tranclassRank="+rank;
	}
	
 	 function deleteShift(tranclassId) {
	if (confirm("你确定要删除该条记录吗?")) {
	$.ajax({
			url : "deleteShift",
			type : 'GET',
			data : {
				tranclassId : tranclassId
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

function shiftOnes(tranclassId) {
	window.location = "shiftOnes?tranclassId=" + tranclassId;
}
function release(tranclassId,tranclassSetuptime){
      $.ajax({          
            url:"publishShift",  
            type : "post",
            data:{
            	tranclassId : tranclassId,
            	tranclassSetuptime : tranclassSetuptime
            	},  
            error:function(){  
               alert("error occured!!!");  
            },  
            success:function(data){
            if(data=="ok")
            {
              alert('发布成功');
              window.location.reload();
            }
            else if(data=="")
            {
               alert('发布失败');
               history.back(-1);
            }else if(data=="1"){
            	alert('发布失败，开设时间已经过期，请从新设置开设时间');
            	 var tranclassRank = $("#tranclassRank").val();
            	window.location = "addEditShiftPage?id=" + tranclassId+"&tranclassRank="+tranclassRank;
            }else if(data=="2"){
            	alert('至少添加一门课程再发布');
           	 var tranclassRank = $("#tranclassRank").val();
           	 window.location.reload();
           }
      
          }  
      }); 
 } 
function queryStudent(tranclassId,cj,classlb) {
	
	window.location = "queryStudentOne?tranclassId=" + tranclassId+"&mark=1&page=1&type=0&cj="+cj+"&classlb="+classlb;
}
function queryCourse(organizationId,cj,tranclassId,classlb) {
	window.location = "queryClassCourse?organizationId=" + organizationId+"&mark=1&page=1&type=0&cj="+cj+"&tranclassId=" + tranclassId+"&classlb="+classlb;
}
function studentThrough(tranclassId,cj,classlb) {
	window.location = "queryStudentOne?tranclassId=" + tranclassId+"&mark=1&page=1&type=1&cj="+cj+"&classlb="+classlb;
}
function queryJoinCourse(tranclassId,cj,classlb) {
	window.location = "queryJoinCourse?tranclassId=" + tranclassId+"&mark=1&page=1&type=1&cj="+cj+"&classlb="+classlb;
}
function fanhui(organizationId,cj,classlb) {
	window.location = "queryClassCatalog?organizationId=" + organizationId+"&mark=1&page=1&type=1&cj="+cj+"&classresourceUpname="+classlb;
}
/*  	function saveWeddingphoto() {
		document.ff.action = "saveWeddingphoto";
		document.ff.method = "post";
		document.ff.submit();
	}  */
</script>
</head>
<body>
	<div class="title">当前位置:培训班详情>培训班详情</div>
	<div class="editBlock">
		<form action="queryShift?classresourceId=${tranclas.classresourceId}&mark=1&page=1&cj=${cj}&classlb=${tranclas.classlb}" method="post" name="userForm" id="userForm">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">培训班详情管理</td>
				</tr>
			 <tr>
			    <c:if test="${cj!=4}">
			      <c:if test="${zzjg.organizationId==sessionScope.sessionUser.accountCatalog}">
				<td style="text-align: left"><input name="button" type="button"
					class="inputButton" value="添加"  onclick="AddShift('${tranclas.classresourceId}','${cj}','${tranclas.classlb}')"/>
					<%--  <c:if test="${tranclas.classresourceUpname!=0}"> --%>
					 <c:if test="${tranclas.classlb!=null&&tranclas.classlb!=''}">
					<input name="button" type="button"
					class="inputButton" value="返回"   onclick="fanhui('${zzjg.organizationId}','${cj}','${tranclas.classlb}')"/>
					 </c:if>
					</td>
				 </c:if>
					<c:if test="${zzjg.organizationId!=sessionScope.sessionUser.accountCatalog}">
					<td style="text-align: left"><input style="color:#AFB8C0" name="button" type="button"
					class="inputButton" value="添加" />
					<input name="button" type="button"
					class="inputButton" value="返回"  onclick="fanhui('${zzjg.organizationId}','${cj}','${tranclas.classlb}')"/>
					</td>	
					</c:if>
					</c:if>
					
					
					<td style="text-align: right;">
				 	<input type="hidden" id="classresourceId" name="classresourceId" value="${tranclas.classresourceId}" />
					<input type="hidden" id="tranclassRank" name="tranclassRank" value="${cj}" /> 
					
					
					
					
					&nbsp;
					         班名称:<input type="text" name="tranclassName" class="inputText" id="tranclassName" value="${tranclas.tranclassName}"/> &nbsp;
						 &nbsp;
						<input name="button" type="submit"  class="inputButton" value="查询" /> 
						
					</td>
				</tr> 
			</tbody>
		</table>
	    </form>
	</div>
	<div class="dataGrid">
		<table>
			<caption>培训班详情查询结果</caption>
			<thead>
				<tr>
				     <th>序      号</th> 
				    <th>班名称</th>
					<th>人数</th>
					<th>班主任</th>
					<th>开设时间</th>
					<th>结束时间</th>
					<th>面授信息</th>
					<th>报名日期</th>
					<th width="60" class="alignCenter">发布</th> 
					<th width="60" class="alignCenter">查看报名学员</th> 
					<th width="60" class="alignCenter">添加课程</th> 
					<th width="60" class="alignCenter">查看课程</th>
				    <c:if test="${cj!=4}"> 
					<th width="60" class="alignCenter">修改</th>
					 <th width="60" class="alignCenter">删除</th> 
					 </c:if>
				</tr>
			</thead>
			<tbody id="">
			    <c:forEach items="${pageBean.list}" var="maps" varStatus="status">
				<tr>
					<td>${status.count}</td> 
					<td><a href="#" onclick="shiftOnes('${maps.tranclassId}')">${maps.tranclassName }</a></td>
					<td><a href="#" onclick="studentThrough('${maps.tranclassId}','${cj}','${tranclas.classlb}')">${maps.tranclassNumber }</a></td>
					<td>${maps.tranclassTeacher }</td>
					<td>${maps.tranclassSetuptime }</td>
					<td>${maps.tranclassEndtime }</td>
					<td>${maps.tranclassFace }</td>
					<td>${maps.tranclassApplytime }</td>
					<c:if test="${cj!=4}">
					<c:if test="${zzjg.organizationId==sessionScope.sessionUser.accountCatalog}">
					<td class='alignCenter'><input name='button'type='button' onclick="release('${maps.tranclassId}','${maps.tranclassSetuptime }')" class='inputButton' value='发布' /></td>
					<td class='alignCenter'><input name='button'type='button' onclick="queryStudent('${maps.tranclassId}','${cj}','${tranclas.classlb}')" class='inputButton' value='报名' /></td>
					<td class='alignCenter'><input name='button'type='button' onclick="queryCourse('${zzjg.organizationId}','${cj}','${maps.tranclassId}','${tranclas.classlb}')" class='inputButton' value='加课程' /></td>
					<td class='alignCenter'><input name='button'type='button' onclick="queryJoinCourse('${maps.tranclassId}','${cj}','${tranclas.classlb}')" class='inputButton' value='课程' /></td>
					<td class='alignCenter'><input name='button'type='button' onclick="editShift('${maps.tranclassId}','${cj}')" class='inputButton' value='编辑' /></td>
					<td class='alignCenter'><input name='button'type='button' onclick="deleteShift('${maps.tranclassId}')" class='inputButton' value='删除' /></td> 
				    </c:if>
					<c:if test="${zzjg.organizationId!=sessionScope.sessionUser.accountCatalog}">
					<td class='alignCenter'><input style="color:#AFB8C0" name='button'type='button'  class='inputButton' value='发布' /></td>
					<td class='alignCenter'><input style="color:#AFB8C0" name='button'type='button' class='inputButton' value='报名' /></td>
					<td class='alignCenter'><input style="color:#AFB8C0" name='button'type='button'  class='inputButton' value='加课程' /></td>
					<td class='alignCenter'><input style="color:#AFB8C0" name='button'type='button' class='inputButton' value='课程' /></td>
					<td class='alignCenter'><input style="color:#AFB8C0" name='button'type='button'  class='inputButton' value='编辑' /></td>
					<td class='alignCenter'><input style="color:#AFB8C0" name='button'type='button' class='inputButton' value='删除' /></td> 
				    </c:if>
				    </c:if>
				    <c:if test="${cj==4}">
					<td class='alignCenter'><input name='button'type='button' onclick="release('${maps.tranclassId}','${maps.tranclassSetuptime }')" class='inputButton' value='发布' /></td>
					<td class='alignCenter'><input name='button'type='button' onclick="queryStudent('${maps.tranclassId}','${cj}','${tranclas.classlb}')" class='inputButton' value='报名' /></td>
					<td class='alignCenter'><input name='button'type='button' onclick="queryCourse('${zzjg.organizationId}','${cj}','${maps.tranclassId}','${tranclas.classlb}')" class='inputButton' value='加课程' /></td>
					<td class='alignCenter'><input name='button'type='button' onclick="queryJoinCourse('${maps.tranclassId}','${cj}','${tranclas.classlb}')" class='inputButton' value='课程' /></td>
	<!-- 				<td class='alignCenter'><input style="color:#AFB8C0" name='button'type='button'  class='inputButton' value='编辑' /></td>
					<td class='alignCenter'><input style="color:#AFB8C0" name='button'type='button' class='inputButton' value='删除' /></td>  -->
				    </c:if>
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
