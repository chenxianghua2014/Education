<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>视频播放</title>
<link rel="stylesheet" type="text/css" href="css/bcss/index_other.css">
<link rel="stylesheet" type="text/css" href="css/bcss/other.css">
<script src="js/jquery/jquery-1.9.1.min.js"></script>
		<style type="text/css" media="screen">
		#flashContent { width:100%; height:100%; }
		</style>
</head>

<body>
<div class="header">
		<div class="wrap">
			<%@ include file="../top.jsp"%>
        </div>
    </div>
<div class="main">
	<div class="concainer">
<div class="bbs_home">
	<div class="wrap">
		<div class="gkkcym">

			<div id="flashContent">
			<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" width="1000px" height="480px" id="videoPlayer" align="middle">
				<param name="movie" value="images/videoPlayer.swf" />
				<param name="quality" value="high" />
				<param name="bgcolor" value="#cccccc" />
				<param name="play" value="true" />
				<param name="loop" value="true" />
				<param name="wmode" value="window" />
				<param name="scale" value="showall" />
				<param name="menu" value="true" />
				<param name="devicefont" value="false" />
				<param name="FlashVars" value="videoURL=../${knowledge.knowledgePath}" />
				<param name="salign" value="" />
				<param name="allowScriptAccess" value="sameDomain" />
				<param name="allowFullScreen" value="true" />
				<!--[if !IE]>-->
				<object type="application/x-shockwave-flash" data="images/videoPlayer.swf" width="1200px" height="480px">
					<param name="movie" value="images/videoPlayer.swf" />
					<param name="quality" value="high" />
					<param name="bgcolor" value="#cccccc" />
					<param name="play" value="true" />
					<param name="loop" value="true" />
					<param name="wmode" value="window" />
					<param name="scale" value="showall" />
					<param name="FlashVars" value="videoURL=../${knowledge.knowledgePath}" />
					<param name="menu" value="true" />
					<param name="devicefont" value="false" />
					<param name="salign" value="" />
					<param name="allowScriptAccess" value="sameDomain" />
					<param name="allowFullScreen" value="true" />
				<!--<![endif]-->
					<a href="http://www.adobe.com/go/getflash">
						<img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="获得 Adobe Flash Player" />
					</a>
				<!--[if !IE]>-->
				</object>
				<!--<![endif]-->
			</object>
		</div>
		<div class="clear"></div>

			<div class="clear"></div>
		</div>
	</div>
</div>
	</div>
</div>

<!-- 底部 版权信息 -->
<%@ include file="../footer.jsp"%>
</body>
</html>
