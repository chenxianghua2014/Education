    var em = [	{'id':0,'phrase':'这里放文字','url':'这里放路径'},
                {'id':1,'phrase':'[微笑]','url':'../images/face/1.gif'},{'id':2,'phrase':'[撇嘴]','url':'../images/face/2.gif'},
                {'id':3,'phrase':'[色]','url':'../images/face/3.gif'},{'id':4,'phrase':'[发呆]','url':'../images/face/4.gif'},
                {'id':5,'phrase':'[流泪]','url':'../images/face/5.gif'},{'id':6,'phrase':'[害羞]','url':'../images/face/6.gif'},
                {'id':7,'phrase':'[闭嘴]','url':'../images/face/7.gif'},{'id':8,'phrase':'[睡]','url':'../images/face/8.gif'},
                {'id':9,'phrase':'[大哭]','url':'../images/face/9.gif'},{'id':10,'phrase':'[尴尬]','url':'../images/face/10.gif'},
                {'id':11,'phrase':'[愤怒]','url':'../images/face/11.gif'},{'id':12,'phrase':'[调皮]','url':'../images/face/12.gif'},
                {'id':13,'phrase':'[呲牙]','url':'../images/face/13.gif'},{'id':14,'phrase':'[惊讶]','url':'../images/face/14.gif'},
                {'id':15,'phrase':'[难过]','url':'../images/face/15.gif'},{'id':16,'phrase':'[冷汗]','url':'../images/face/16.gif'},
                {'id':17,'phrase':'[抓狂]','url':'../images/face/17.gif'},{'id':18,'phrase':'[吐]','url':'../images/face/18.gif'},
                {'id':19,'phrase':'[偷笑]','url':'../images/face/19.gif'},{'id':20,'phrase':'[可爱]','url':'../images/face/20.gif'},
                {'id':21,'phrase':'[白眼]','url':'../images/face/21.gif'},{'id':22,'phrase':'[傲慢]','url':'../images/face/22.gif'},
                {'id':23,'phrase':'[饥饿]','url':'../images/face/23.gif'},{'id':24,'phrase':'[困]','url':'../images/face/24.gif'},
                {'id':25,'phrase':'[惊恐]','url':'../images/face/25.gif'},{'id':26,'phrase':'[流汗]','url':'../images/face/26.gif'},
                {'id':27,'phrase':'[憨笑]','url':'../images/face/27.gif'},{'id':28,'phrase':'[大兵]','url':'../images/face/28.gif'},
                {'id':29,'phrase':'[奋斗]','url':'../images/face/29.gif'},{'id':30,'phrase':'[咒骂]','url':'../images/face/30.gif'},
                {'id':31,'phrase':'[疑问]','url':'../images/face/31.gif'},{'id':32,'phrase':'[嘘]','url':'../images/face/32.gif'},
                {'id':33,'phrase':'[晕]','url':'../images/face/33.gif'},{'id':34,'phrase':'[折磨]','url':'../images/face/34.gif'},
                {'id':35,'phrase':'[衰]','url':'../images/face/35.gif'},{'id':36,'phrase':'[敲打]','url':'../images/face/36.gif'},
                {'id':37,'phrase':'[再见]','url':'../images/face/37.gif'},{'id':38,'phrase':'[擦汗]','url':'../images/face/38.gif'},
                {'id':39,'phrase':'[抠鼻]','url':'../images/face/39.gif'},{'id':40,'phrase':'[糗大了]','url':'../images/face/40.gif'},
                {'id':41,'phrase':'[坏笑]','url':'../images/face/41.gif'},{'id':42,'phrase':'[右哼哼]','url':'../images/face/42.gif'},
                {'id':43,'phrase':'[左哼哼]','url':'../images/face/43.gif'},{'id':44,'phrase':'[哈欠]','url':'../images/face/44.gif'},
                {'id':45,'phrase':'[鄙视]','url':'../images/face/45.gif'},{'id':46,'phrase':'[委屈]','url':'../images/face/46.gif'},
                {'id':47,'phrase':'[快哭了]','url':'../images/face/47.gif'},{'id':48,'phrase':'[阴险]','url':'../images/face/48.gif'},
                {'id':49,'phrase':'[亲亲]','url':'../images/face/49.gif'},{'id':50,'phrase':'[吓]','url':'../images/face/50.gif'},
                {'id':51,'phrase':'[可怜]','url':'../images/face/51.gif'},{'id':52,'phrase':'[拥抱]','url':'../images/face/52.gif'},
                {'id':53,'phrase':'[月亮]','url':'../images/face/53.gif'},{'id':54,'phrase':'[太阳]','url':'../images/face/54.gif'},
                {'id':55,'phrase':'[炸弹]','url':'../images/face/55.gif'},{'id':56,'phrase':'[骷髅]','url':'../images/face/56.gif'},
                {'id':57,'phrase':'[菜刀]','url':'../images/face/57.gif'},{'id':58,'phrase':'[猪头]','url':'../images/face/58.gif'},
                {'id':59,'phrase':'[西瓜]','url':'../images/face/59.gif'},{'id':60,'phrase':'[咖啡]','url':'../images/face/60.gif'},
                {'id':61,'phrase':'[米饭]','url':'../images/face/61.gif'},{'id':62,'phrase':'[爱心]','url':'../images/face/62.gif'},
                {'id':63,'phrase':'[强]','url':'../images/face/63.gif'},{'id':64,'phrase':'[弱]','url':'../images/face/64.gif'},
                {'id':65,'phrase':'[握手]','url':'../images/face/65.gif'},{'id':66,'phrase':'[胜利]','url':'../images/face/66.gif'},
                {'id':67,'phrase':'[抱拳]','url':'../images/face/67.gif'},{'id':68,'phrase':'[勾引]','url':'../images/face/68.gif'},
                {'id':69,'phrase':'[OK]','url':'../images/face/69.gif'},{'id':70,'phrase':'[NO]','url':'../images/face/70.gif'},
                {'id':71,'phrase':'[玫瑰]','url':'../images/face/71.gif'},{'id':72,'phrase':'[凋谢]','url':'../images/face/72.gif'},
                {'id':73,'phrase':'[示爱]','url':'../images/face/73.gif'},{'id':74,'phrase':'[爱情]','url':'../images/face/74.gif'},
                {'id':75,'phrase':'[飞吻]','url':'../images/face/75.gif'}
            ];

//查看结果
function replace_em(str){
	var regx = /(\[[\u4e00-\u9fa5]*\w*\]){1}/g;
	//正则查找“[]”格式
	var rs = str.match(regx);
	if(rs) {
		for( i = 0; i < rs.length; i++) {
			for( n = 0; n < em.length; n++) {
				if(em[n].phrase == rs[i]) {
					var t = "<img src='"  + em[n].url + "' />";
					str = str.replace(rs[i], t);
					break;
				}
			}
		}
	}
                
	return str;
}
// QQ表情插件
(function($){
	
	$.fn.qqFace = function(options){
		var defaults = {
			id : 'facebox',
			path : 'face/',
			assign : 'content',
			emotions : options.em
		};
		var option = $.extend(defaults, options);
		var assign = $('#'+option.assign);
		var id = option.id;
		
		if(assign.length<=0){
			alert('缺少表情赋值对象。');
			return false;
		}
		
		$(this).click(function(e){
			var strFace, labFace;
			if($('#'+id).length<=0){
				strFace = '<div id="'+id+'" style="position:absolute;display:none;z-index:1000;" class="qqFace">' +
							  '<table border="0" cellspacing="0" cellpadding="0"><tr>';
				for(var i=1; i<options.emotions.length; i++){
					labFace = option.emotions[i].phrase;
					strFace += '<td><img src="'+option.emotions[i].url+'" onclick="$(\'#'+option.assign+'\').setCaret();$(\'#'+option.assign+'\').insertAtCaret(\'' + labFace + '\');" /></td>';
					if( i % 15 == 0 ) strFace += '</tr><tr>';
				}
				strFace += '</tr></table></div>';
			}
			$(this).parent().append(strFace);
			var offset = $(this).position();
			var top = offset.top + $(this).outerHeight();
			$('#'+id).css('top',top);
			$('#'+id).css('left',offset.left);
			$('#'+id).show();
			e.stopPropagation();
		});

		$(document).click(function(){
			$('#'+id).hide();
			$('#'+id).remove();
		});
	};

})(jQuery);

jQuery.extend({ 
unselectContents: function(){ 
	if(window.getSelection) 
		window.getSelection().removeAllRanges(); 
	else if(document.selection) 
		document.selection.empty(); 
	} 
}); 
jQuery.fn.extend({ 
	selectContents: function(){ 
		$(this).each(function(i){ 
			var node = this; 
			var selection, range, doc, win; 
			if ((doc = node.ownerDocument) && (win = doc.defaultView) && typeof win.getSelection != 'undefined' && typeof doc.createRange != 'undefined' && (selection = window.getSelection()) && typeof selection.removeAllRanges != 'undefined'){ 
				range = doc.createRange(); 
				range.selectNode(node); 
				if(i == 0){ 
					selection.removeAllRanges(); 
				} 
				selection.addRange(range); 
			} else if (document.body && typeof document.body.createTextRange != 'undefined' && (range = document.body.createTextRange())){ 
				range.moveToElementText(node); 
				range.select(); 
			} 
		}); 
	}, 

	setCaret: function(){ 
		if(!$.browser.msie) return; 
		var initSetCaret = function(){ 
			var textObj = $(this).get(0); 
			textObj.caretPos = document.selection.createRange().duplicate(); 
		}; 
		$(this).click(initSetCaret).select(initSetCaret).keyup(initSetCaret); 
	}, 

	insertAtCaret: function(textFeildValue){ 
		var textObj = $(this).get(0); 
		if(document.all && textObj.createTextRange && textObj.caretPos){ 
			var caretPos=textObj.caretPos; 
			caretPos.text = caretPos.text.charAt(caretPos.text.length-1) == '' ? 
			textFeildValue+'' : textFeildValue; 
		} else if(textObj.setSelectionRange){ 
			var rangeStart=textObj.selectionStart; 
			var rangeEnd=textObj.selectionEnd; 
			var tempStr1=textObj.value.substring(0,rangeStart); 
			var tempStr2=textObj.value.substring(rangeEnd); 
			textObj.value=tempStr1+textFeildValue+tempStr2; 
			textObj.focus(); 
			var len=textFeildValue.length; 
			textObj.setSelectionRange(rangeStart+len,rangeStart+len); 
			//textObj.blur(); 
		}else{ 
			textObj.value+=textFeildValue; 
		} 
	} 
});