$(document).ready(function(){
	
	// slick
	$(".slider").slick({
		dots: true,
		autoplay: true,
		autoplaySpeed: 5000,
		infinite: true,
		lazyLoad: 'ondemand',
		lazyLoadBuffer: 1000
	});
});