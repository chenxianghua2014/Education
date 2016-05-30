$(document).ready(function() {
	var type = getQueryString("type");
	var order = getQueryString("order");
	if (order == null)
		$("#time").addClass("cur");
	else
		$("#" + order).addClass("cur");
	if (type == null)
		$("#hc").css({
			color : "#f75a87"
		});
	else
		$("#" + type).css({
			color : "#f75a87"
		});
});

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
	window.location.href = changeURLArg(window.location.href, "order", order);
}
function setKeyword(keyword) {
	var url = encodeURI(changeURLArg(window.location.href, "keyword", keyword));
	window.location.href = url;
}
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}
