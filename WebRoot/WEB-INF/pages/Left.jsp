<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  

<div class="my_synopsis_2">
	<div class="course_lmenu">
	<div class="tree">
    <div class="tree_box" onmouseover="CUrl(1)" onmouseout="CUrl(2)" >
        <h3 >
            <a title="中国航天科工集团"  href="noticeInfoAll?mark=${mark}&page=1&sort=1&oid=test001" style="color: #666666;">
					中国航天科工集团
		     </a>
        </h3>
        <ul class="tree_one" id="tree_one" style="display: none;" onmouseover="CUrl1(1)" onmouseout="CUrl1(2)" >
            <li>
            <c:choose>
            <c:when test="${sessionScope.Student.organization.organizationFwqx == '2'}">
             <h4>
                      <a title="${oneid.organizationDwmc}"  href="noticeInfoAll?mark=${mark}&page=1&sort=1&oid=${oneid.organizationId}" style="color: #666666;">
					${oneid.organizationDwmc}
		     </a>
                </h4>
            </c:when>
             <c:when test="${sessionScope.Student.organization.organizationFwqx == '3'}">
             <h4>
                      <a title="${oneid1.organizationDwmc}"  href="noticeInfoAll?mark=${mark}&page=1&sort=1&oid=${oneid1.organizationId}" style="color: #666666;">
					${oneid1.organizationDwmc}
		     </a>
                </h4>
                  <ul class="tree_two" style="display: none;" id="tree_two">
                    <li>
                           <a title="${oneid.organizationDwmc}"  href="noticeInfoAll?mark=${mark}&page=1&sort=1&oid=${oneid.organizationId}" style="color: #666666;">
					${oneid.organizationDwmc}
		     </a>
                   </li>
                </ul>
            </c:when>
            </c:choose>
           
            </li>
          
        </ul>
    </div>
    
   </div>
</div>
<script type="text/javascript">

function CUrl(n) {

	if(n==1){

			document.getElementById("tree_one").style.display="block";
			
	}else{
		document.getElementById("tree_one").style.display="none";
	}
} 	

function CUrl1(n) {

	if(n==1){
			document.getElementById("tree_one").style.display="block";
			document.getElementById("tree_two").style.display="block";
			
	}else{
		document.getElementById("tree_one").style.display="none";
			document.getElementById("tree_two").style.display="none";
	}
}
  
</script>
</div>
	
	
		
		