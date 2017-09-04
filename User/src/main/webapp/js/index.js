$(document).ready(function(){	
	
	// 主輪播
	var swiper = new Swiper('.swiper-container', {
		pagination: '.swiper-pagination',
		paginationClickable: true,
		nextButton: '.swiper-button-next',
		prevButton: '.swiper-button-prev',
		spaceBetween: 30,
		centeredSlides: true,
		autoplay: 2500,
		autoplayDisableOnInteraction: false,
		loop: true
	});
		
	function reinitSwiper(swiper){
		setTimeout(function(){
			swiper.reInit();
		}, 500);
	};
	
	$.getJSON("image/open-slider-main-list.ajax", function(data){
		$.each(data, function(index, imageBean){
			var slider_main_img = $("<img src='/Admin/images/slider-main/" + imageBean.im_filename + "' title='" + imageBean.im_name + "' alt='" + imageBean.im_name + "' class='img-slider'>");
			var slider_main_a = $("<a href=" + imageBean.im_url + " target='_blank' class='a-silder'></a>").append(slider_main_img);
			var slider_main_div = $("<div class='swiper-slide'></div>").append(slider_main_a);
			swiper.appendSlide(slider_main_div);
		});
	});
	
	// 相關影音
	$.getJSON("video/open-video-main-list.ajax", function(data){
		$(".video-main iframe").attr("src", data.vi_tag);
	});
	
});