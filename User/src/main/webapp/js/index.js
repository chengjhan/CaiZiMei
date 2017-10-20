$(document).ready(function(){
	
	function reinitSwiper(slider_main, slider_franchisee, slider_recent, slider_sale, slider_knowledge, slider_doctor){
		setTimeout(function(){
			slider_main.reInit();
			slider_franchisee.reInit();
			slider_recent.reInit();
			slider_sale.reInit();
			slider_knowledge.reInit();
			slider_doctor.reInit();
		}, 500);
	}
	
	// 主輪播
	var slider_main = new Swiper('#slider-main', {
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
	
	$.getJSON("image/slider-main.ajax", function(data){
		$.each(data, function(index, imageBean){
			var slider_img = $("<img src='/Admin/images/slider-main/" + imageBean.im_filename + "' title='" + imageBean.im_name + "' alt='" + imageBean.im_name + "' class='img-slider'>");
			var slider_a = $("<a href=" + imageBean.im_url + " target='_blank' class='a-silder'></a>").append(slider_img);
			var slider_div = $("<div class='swiper-slide'></div>").append(slider_a);
			slider_main.appendSlide(slider_div);
		});
	});
	
	// 相關影音
	$.getJSON("video/video-main.ajax", function(data){
		$("#video-main iframe").attr("src", data.vi_tag);
	});
	
	// 加盟店資訊輪播
	var slider_franchisee = new Swiper('#slider-franchisee', {
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
	
	$.getJSON("image/slider-franchisee.ajax", function(data){
		$.each(data, function(index, imageBean){
			var slider_img = $("<img src='/Admin/images/slider-franchisee/" + imageBean.im_filename + "' title='" + imageBean.im_name + "' alt='" + imageBean.im_name + "' class='img-slider'>");
			var slider_a = $("<a href=" + imageBean.im_url + " target='_blank' class='a-silder'></a>").append(slider_img);
			var slider_div = $("<div class='swiper-slide'></div>").append(slider_a);
			slider_franchisee.appendSlide(slider_div);
		});
	});
	
	// 近期活動輪播
	var slider_recent = new Swiper('#slider-recent', {
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
	
	$.getJSON("image/slider-recent.ajax", function(data){
		$.each(data, function(index, imageBean){
			var slider_img = $("<img src='/Admin/images/slider-recent/" + imageBean.im_filename + "' title='" + imageBean.im_name + "' alt='" + imageBean.im_name + "' class='img-slider'>");
			var slider_a = $("<a href=" + imageBean.im_url + " target='_blank' class='a-silder'></a>").append(slider_img);
			var slider_div = $("<div class='swiper-slide'></div>").append(slider_a);
			slider_recent.appendSlide(slider_div);
		});
	});
	
	// 優惠活動輪播
	var slider_sale = new Swiper('#slider-sale', {
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
	
	$.getJSON("image/slider-sale.ajax", function(data){
		$.each(data, function(index, imageBean){
			var slider_img = $("<img src='/Admin/images/slider-sale/" + imageBean.im_filename + "' title='" + imageBean.im_name + "' alt='" + imageBean.im_name + "' class='img-slider'>");
			var slider_a = $("<a href=" + imageBean.im_url + " target='_blank' class='a-silder'></a>").append(slider_img);
			var slider_div = $("<div class='swiper-slide'></div>").append(slider_a);
			slider_sale.appendSlide(slider_div);
		});
	});
	
	// 醫療新知輪播
	var slider_knowledge = new Swiper('#slider-knowledge', {
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
	
	$.getJSON("image/slider-knowledge.ajax", function(data){
		$.each(data, function(index, imageBean){
			var slider_img = $("<img src='/Admin/images/slider-knowledge/" + imageBean.im_filename + "' title='" + imageBean.im_name + "' alt='" + imageBean.im_name + "' class='img-slider'>");
			var slider_a = $("<a href=" + imageBean.im_url + " target='_blank' class='a-silder'></a>").append(slider_img);
			var slider_div = $("<div class='swiper-slide'></div>").append(slider_a);
			slider_knowledge.appendSlide(slider_div);
		});
	});
	
	// 醫療團隊輪播
	var slider_doctor = new Swiper('#slider-doctor', {
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
	
	$.getJSON("image/slider-doctor.ajax", function(data){
		$.each(data, function(index, imageBean){
			var slider_img = $("<img src='/Admin/images/slider-doctor/" + imageBean.im_filename + "' title='" + imageBean.im_name + "' alt='" + imageBean.im_name + "' class='img-slider'>");
			var slider_a = $("<a href=" + imageBean.im_url + " target='_blank' class='a-silder'></a>").append(slider_img);
			var slider_div = $("<div class='swiper-slide'></div>").append(slider_a);
			slider_doctor.appendSlide(slider_div);
		});
	});
	
});