$(function(){		
	//设计案例切换
	$('.title-list li').mouseover(function(){
		var liindex = $('.title-list li').index(this);
		$(this).addClass('on').siblings().removeClass('on');
		$('.product-wrap div.product').eq(liindex).fadeIn(150).siblings('div.product').hide();
		var liWidth = $('.title-list li').width();
		$('.ranking_list .title-list p').stop(false,true).animate({'left' : liindex * liWidth + 'px'},300);
	});
	//设计案例hover效果
	$('.product-wrap .product li').hover(function(){
		$(this).css("border-color","#0099e0");
		$(this).css('color','#0099e0');
	},function(){
		$(this).find('p > a').css("border-color","#fafafa");
		$(this).find('p > a').css('color','#666666');
	});
	});