$(document).ready(function(){
	
	function reinitSwiper(slider_main, slider_franchisee){
		setTimeout(function(){
			slider_main.reInit();
			slider_franchisee.reInit();
		}, 500);
	}
	
	// 主輪播
	var slider_main = new Swiper('.slider-main', {
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
	
	$.getJSON("image/open-slider-main-list.ajax", function(data){
		$.each(data, function(index, imageBean){
			var slider_main_img = $("<img src='/Admin/images/slider-main/" + imageBean.im_filename + "' title='" + imageBean.im_name + "' alt='" + imageBean.im_name + "' class='img-slider'>");
			var slider_main_a = $("<a href=" + imageBean.im_url + " target='_blank' class='a-silder'></a>").append(slider_main_img);
			var slider_main_div = $("<div class='swiper-slide'></div>").append(slider_main_a);
			slider_main.appendSlide(slider_main_div);
		});
	});
	
	// 相關影音
	$.getJSON("video/open-video-related-list.ajax", function(data){
		$(".video-related iframe").attr("src", data.vi_tag);
	});
	
	// 加盟店資訊
	var slider_franchisee = new Swiper('.slider-franchisee', {
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
	
	$.getJSON("image/open-slider-main-list.ajax", function(data){
		$.each(data, function(index, imageBean){
			var slider_main_img = $("<img src='/Admin/images/slider-main/" + imageBean.im_filename + "' title='" + imageBean.im_name + "' alt='" + imageBean.im_name + "' class='img-slider'>");
			var slider_main_a = $("<a href=" + imageBean.im_url + " target='_blank' class='a-silder'></a>").append(slider_main_img);
			var slider_main_div = $("<div class='swiper-slide'></div>").append(slider_main_a);
			slider_franchisee.appendSlide(slider_main_div);
		});
	});
	
});