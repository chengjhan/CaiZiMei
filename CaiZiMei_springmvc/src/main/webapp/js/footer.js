$(document).ready(function(){
		
	// facebook icon mouseover
	$("#id-img-facebook").hover(function(){
		$(this).removeAttr("src").attr("src", "images/icon_facebook_white_512x512.svg");
	}, function(){
		$(this).removeAttr("src").attr("src", "images/icon_facebook_512x512.svg");
	});
		
});