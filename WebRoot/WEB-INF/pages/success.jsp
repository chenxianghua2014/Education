<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
	function msgRedirect() {
		alert("${message.message}");
		window.location.href = "${message.uri}";
		return;
	}
</script>
</head>
<body onload="javascript:msgRedirect();">
</body>
</html>