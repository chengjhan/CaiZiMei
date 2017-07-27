$(document).ready(function(){	
	
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
	
	$.getJSON("slider-main/open-slide-list.ajax", function(data){
		$.each(data, function(index, sliderMainBean){
			var slide_img = $("<img src='/Admin/images/slider-main/" + sliderMainBean.sm_filename + "' title='" + sliderMainBean.sm_name + "' alt='" + sliderMainBean.sm_name + "' class='img-slider'>");
			var slide_a = $("<a href=" + sliderMainBean.sm_url + " target='_blank' class='a-silder'></a>").append(slide_img);
			var slide_div = $("<div class='swiper-slide'></div>").append(slide_a);
			swiper.appendSlide(slide_div);
		});
	});
	
});