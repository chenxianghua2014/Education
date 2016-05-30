/**
 *  董再兴
 *	ajax 统一调用方法
 */

function ajaxPost(url, jsonObject, callback){
	$.ajax({
			type : 'POST',
			url : url,
			data : jsonObject,
			dataType : "json",
			success : function(msg) {
				callback(msg);
			}
	});
}

/**
 *  董再兴
 *	获取url中的参数
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}
