<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<title>中国航天科工在线培训平台</title>
<%--<link type="text/css" rel="stylesheet" href="../css/pageCss/style.css" />
<link type="text/css" href="../js/jBox/Skins/Blue/jbox.css"
	rel="stylesheet"></link>
--%><script src="js/jquery/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="js/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="js/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" charset="utf-8" src="js/Effects.js"></script>

<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/bcss/index_other.css">
<link rel="stylesheet" type="text/css" href="css/bbs.css">
<link rel="stylesheet" type="text/css" href="css/other.css">
<script type="text/javascript" src="js/jquery.superslide.2.1.1.js"></script>
<script type="text/javascript" src="js/zzsc.js"></script>

<script type="text/javascript" src="css/pager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/pager/kkpager_blue.css" />
</head>
<body>
<div class="header">
	<%@ include file="../top.jsp"%>
</div>

<div class="main">
	<div class="concainer">
    	<div class="training_dynamic">
        	<div class="luntan_title">论坛 </div>  
        </div>
        <div class="forum_news" style="border-top:none;">
            <div class="luntan_left">
               
                <div class="luntan_neirong">
                		
                        <c:forEach items="${pageBean.list}" var="bbs" varStatus="status">
                        	<div class="luntan_a01" id="myTab0_Content0">
                        		<div class="neirong_list">
	                        		<p><a href="#"><img src="images/f/head_img.jpg"></a></p>
	                        		<ul class="ul_list">
	                                    <li class="ul_list_title"><a href="bbsdetails?bbsId=${bbs.bbsId }">${bbs.bbsTitle }</a></li>
	                                    <li class="ul_list_main">
	                                    	<a href="javascript:;">${bbs.studentName } </a><time>${bbs.friendlyAddtime }</time>
	                                    </li>
	                                </ul>
	                                <ul class="luntan_look">
	                                	<%--<li>浏览：<span><a href="javascript:;">${bbs.viewCount }</a></span></li>
	                                    <li>攒：<span><a href="javascript:;">${bbs.praise }</a></span></li>--%>
	                                    <li>回复：<span><a href="javascript:;">${bbs.replyCount }</a></span></li>
	                                </ul>
                                </div>
							</div>
						</c:forEach>
               </div>  
               <div id="kkpager"></div><br/>
            </div>
            <div class="luntan_right">
            	<a href="writebbs?boardId=${boardId}" ><img src="./images/f/button_img_01.jpg"></a>
                <a href="mybbs"><img src="./images/f/button_img_02.jpg"></a>
            </div>
            
        </div>
	</div>
</div>
<%@ include file="../footer.jsp"%>
</body>
<script type="text/javascript">
	function changeURLArg(url, arg, arg_val) {
		var pattern = arg + '=([^&]*)';
		var replaceText = arg + '=' + arg_val;
		if (url.match(pattern)) {
			var tmp = '/(' + arg + '=)([^&]*)/gi';
			tmp = url.replace(eval(tmp), replaceText);
			return tmp;
		} else {
			if (url.match('[\?]')) {
				return url + '&' + replaceText;
			} else {
				return url + '?' + replaceText;
			}
		}
		return url + '\n' + arg + '\n' + arg_val;
	}
	function setType(type) {
		window.location.href = changeURLArg(window.location.href, "type", type);
	}
	function setOrder(order) {
		window.location.href = changeURLArg(window.location.href, "order",
				order);
	}
	function setKeyword(keyword) {
		var url = encodeURI(changeURLArg(window.location.href, "keyword",
				keyword));
		window.location.href = url;
	}
</script>
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

