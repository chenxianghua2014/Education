<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="<%=path%>/css/skins/default/main.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/js/TreeTable/css/treetable.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/TreeTable/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/js/TreeTable/jquery-ui.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/TreeTable/jquery.treetable.js"></script>
<title></title>
<script type="text/javascript">
	function Addaccount() {
		window.location = "AccountskipID?id=0";
	}
	function Addnew() {
		window.location = "OrganizationskipID?id=0";
	}
	function AccountOnes(id) {
		window.location = "accountOnes?accountCatalog="+id;
	}
	function mergerOrganization() {
		window.location = "organizationMerger";
	}
	
	function sortOrganization() {
		window.location = "organizationSort";
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
<base target="mainFrame" />
<body>
<div class="title">当前位置:系统管理>组织结构管理</div>
<div class="editBlock">
		
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">组织结构管理</td>
				</tr>
				<tr>
					<td style="text-align: left">
					  <c:if test="${sessionScope.sessionUser.accountType==1}">
					<input name="button" type="button"
						class="inputButton" value="创建组织"  onclick="Addnew()"/>
				   <input name="button" type="button"
						class="inputButton" value="组织合并"  onclick="mergerOrganization()"/>
					<input name="button" type="button"
						class="inputButton" value="组织排序"  onclick="sortOrganization()"/>
					</c:if>
				   
				   	<input name="button" type="button"
						class="inputButton" value="创建账号"  onclick="Addaccount()"/>
						</td>
						
					
						
				</tr>
			</tbody>
		</table>
	    
	</div>
	<table id="example-advanced" class="treetable dataGrid">
		<thead>
			<tr>
				<th>单位名称
			<a href="#"onclick="jQuery('#example-advanced').treetable('expandAll'); return false;">【展开】</a> 
			<a href="#"onclick="jQuery('#example-advanced').treetable('collapseAll'); return false;">【收起】</a>
		</th>
				<th>单位代码</th>
				<th>单位负责人</th>
				<th>联系电话</th>
				<th>邮箱</th>
				<th>编辑</th>
				<th>账号管理</th>
				<c:if test="${sessionScope.sessionUser.accountType==1||sessionScope.sessionUser.accountType==5}">
				<th>调整</th>
				</c:if>
			</tr>
		</thead>
		<tbody>
			<tr data-tt-id="1"  class="ui-droppable branch expanded"
				style="display: table-row;">
				<td><span class="folder ui-draggable">${oneid.organizationDwmc}</span></td>
				<td>${oneid.organizationDwdm}</td>
				<td>${oneid.organizationDwfzr}</td>
				<td>${oneid.organizationLxrdh}</td>
				<td>${oneid.organizationLxremail}</td>
				<td>
					<input name='button'type='button' onclick="editNew('${oneid.organizationId}')" class='inputButton' value='编辑' />
				</td>
				<td>
					<input name='button'type='button' onclick="AccountOnes('${oneid.organizationId}')" class='inputButton' value='账号管理' />
				</td>
			</tr>
			<c:forEach items="${organization}" var="fmap" varStatus="fmapStatus">
			<c:if test="${fmap.organizationSjdw == oneid.organizationId }">
				<tr data-tt-id="1-${fmapStatus.index +1}" id="1-${fmapStatus.index +1}" data-tt-parent-id="1"
					class="branch ui-droppable expanded" style="display: none;">
					<td><span class="folder ui-draggable">${fmap.organizationDwmc}</span>
					</td>
					<td>${fmap.organizationDwdm}</td>
					<td>${fmap.organizationDwfzr}</td>
					<td>${fmap.organizationLxrdh}</td>
					<td>${fmap.organizationLxremail}</td>
					<td>
					<input name='button'type='button' onclick="editNew('${fmap.organizationId}')" class='inputButton' value='编辑' />
					</td>
					<td>
					<input name='button'type='button' onclick="AccountOnes('${fmap.organizationId}')" class='inputButton' value='账号管理' />
				</td>
				</tr>
				<c:forEach items="${organization}" var="map" varStatus="mapStatus">
					<c:if test="${fmap.organizationId eq map.organizationSjdw}">
						<tr data-tt-id="1-${fmapStatus.index +1}-${mapStatus.index +1}"
							data-tt-parent-id="1-${fmapStatus.index +1}"
							class="leaf collapsed" style="display: none;">
						<td><span class="folder ui-draggable">${map.organizationDwmc}</span>
					</td>
					<td>${map.organizationDwdm}</td>
					<td>${map.organizationDwfzr}</td>
					<td>${map.organizationLxrdh}</td>
					<td>${map.organizationLxremail}</td>
					
					
					
					<td>
					<input name='button'type='button' onclick="editNew('${map.organizationId}')" class='inputButton' value='编辑' />
					</td>
					<td>
					<input name='button'type='button' onclick="AccountOnes('${map.organizationId}')" class='inputButton' value='账号管理' />
					</td>
					<c:if test="${sessionScope.sessionUser.accountType==1||sessionScope.sessionUser.accountType==5}">
					<td>
					<c:if test="${map.organizationSjdw != 'test001' && map.organizationId != 'test001'}">
					<input name='button'type='button' onclick="tisheng('${map.organizationId}')" class='inputButton' value='提升' />
					</c:if>
					</td>
					</c:if>
					
					
						</tr>
					</c:if>
				</c:forEach>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
	

</body>
<script>
	$("#example-basic").treetable({
		expandable : true
	});

	$("#example-basic-static").treetable();

	$("#example-basic-expandable").treetable({
		expandable : true
	});

	$("#example-advanced").treetable({
		expandable : true
	});

	// Highlight selected row
	$("#example-advanced tbody").on("mousedown", "tr", function() {
		$(".selected").not(this).removeClass("selected");
		$(this).toggleClass("selected");
	});

	// Drag & Drop Example Code
	//$("#example-advanced .file, #example-advanced .folder").draggable({
	//	helper : "clone",
	//	opacity : .75,
	//	refreshPositions : true, // Performance?
	//	revert : "invalid",
	//	revertDuration : 300,
	//	scroll : true
	//});

	//$("#example-advanced .folder").each(
	//		function() {
	//			$(this).parents("#example-advanced tr")
	//					.droppable(
	//							{
	//								accept : ".file, .folder",
	//								drop : function(e, ui) {
	//									var droppedEl = ui.draggable
	//											.parents("tr");
	//									$("#example-advanced").treetable(
	//											"move", droppedEl.data("ttId"),
	//											$(this).data("ttId"));
	//								},
	//								hoverClass : "accept",
	//								over : function(e, ui) {
	//									var droppedEl = ui.draggable
	//											.parents("tr");
	//									if (this != droppedEl[0]
	//											&& !$(this).is(".expanded")) {
	//										$("#example-advanced").treetable(
	//												"expandNode",
	//												$(this).data("ttId"));
	//									}
	//								}
	//							});
	//		});

	$("form#reveal").submit(function() {
		var nodeId = $("#revealNodeId").val()

		try {
			$("#example-advanced").treetable("reveal", nodeId);
		} catch (error) {
			alert(error.message);
		}

		return false;
	});
	
	
	
</script>
</html>
