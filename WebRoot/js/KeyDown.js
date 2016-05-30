document.oncontextmenu = function() {
	$.jBox.messager("<span style='color:red;'>鼠标右键不可用</span>", "提醒");
	return false;
};
if(navigator.appName == "Microsoft Internet Explorer"){
	document.onkeydown=keypress;
}else{
	document.onkeypress=keypress;
}
if(navigator.userAgent.indexOf("360SE")>0){
	$.jBox.messager("<span style='color:red;'>本系统不支持360浏览器，请改用ie（或firefox）等再行访问本站，谢谢合作！。</span>", "提醒");
	document.execCommand("stop");
}

var mouseCur = 0;

function mouseMove(ev) {
	ev = ev || window.event;
	var mousePos = mouseCoords(ev);
	// 鼠标y轴的坐标
	mouseCur = mousePos.y;
}

function mouseCoords(ev) {
	if (ev.pageX || ev.pageY) {
		return {
			x : ev.pageX,
			y : ev.pageY
		};
	}
	return {
		x : ev.clientX + document.body.scrollLeft - document.body.clientLeft,
		y : ev.clientY + document.body.scrollTop - document.body.clientTop
	};
}

var winWidth = 0;
var winHeight = 0;
// 函数：获取尺寸
function findDimensions() {
	// 获取窗口宽度
	if (window.innerWidth)
		winWidth = window.innerWidth;
	else if ((document.body) && (document.body.clientWidth))
		winWidth = document.body.clientWidth;
	// 获取窗口高度
	if (window.innerHeight)
		winHeight = window.innerHeight;
	else if ((document.body) && (document.body.clientHeight))
		winHeight = document.body.clientHeight;
	/* nasty hack to deal with doctype swith in IE */
	// 通过深入Document内部对body进行检测，获取窗口大小
	if (document.documentElement && document.documentElement.clientHeight
			&& document.documentElement.clientWidth) {
		winHeight = document.documentElement.clientHeight;
		winWidth = document.documentElement.clientWidth;
	}
}

window.onblur = function(e) {
	e = e || window.event;
	if (window.ActiveXObject && /MSIE/.test(navigator.userAgent)) { // IE
		// 如果 blur 事件是窗口内部的点击所产生，返回 false, 也就是说这是一个假的 blur
		var x = e.clientX;
		var y = e.clientY;
		var w = document.body.clientWidth;
		var h = document.body.clientHeight;

		if (x >= 0 && x <= w && y >= 0 && y <= h) {
			window.focus();
			return false;
		}
	}
	// 获取鼠标位置
	findDimensions();
	// 如果失去焦点, 并且焦点不在document里面, 在工具栏或者其他窗口
	if (!document.hasFocus() && mouseCur < winHeight) {
		window.focus();
	}
};
// 注册鼠标移动事件
document.onmousemove = mouseMove;
function keypress(ev){
	e = ev || window.event;
    var kcode = e.which || e.keyCode;
    if(kcode == 0){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }
    if(kcode == 1){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }
    if(kcode == 2){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }
    if(kcode == 3){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }
    if(kcode == 4){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }
    if(kcode == 5){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 6){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 7){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 8){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 9){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 10){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 11){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 12){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 13){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 14){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 15){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 16){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 17){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 18){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 19){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 20){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 21){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 22){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 23){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 24){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 1){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 25){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 26){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 27){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 28){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 29){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 30){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 31){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 32){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 33){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 34){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 35){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 36){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 37){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 38){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 39){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 40){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 41){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 42){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 43){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 44){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 45){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 46){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 47){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 48){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 49){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 50){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 51){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 52){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 53){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 54){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 55){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 56){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 57){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 58){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 59){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 60){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 61){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 62){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 63){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 64){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 65){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 66){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 67){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 68){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 69){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 70){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 71){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 72){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 73){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 74){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 75){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 76){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 77){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 78){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 79){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 80){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 81){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 82){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 83){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 84){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 85){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 86){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 87){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 88){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 89){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 90){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 91){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 92){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 93){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 94){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 95){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 96){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 97){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 98){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 99){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 100){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 101){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 102){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 103){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 104){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 105){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 106){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 107){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 108){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 109){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 110){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 111){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 112){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 113){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 114){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 115){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 116){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 117){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 118){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 119){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 120){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 121){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 122){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 123){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 124){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 125){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 126){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }if(kcode == 127){
    	$.jBox.messager("<span style='color:red;'>请勿进行键盘操作。</span>", "提醒");
        return false;
    }
}


