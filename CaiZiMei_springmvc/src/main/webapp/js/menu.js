$(document).ready(function(){
		
	// list mouseover
	$(".li-menu a span").hover(function(){
		$(this).addClass("span-menu-mouseover");
	}, function(){
		$(this).removeClass("span-menu-mouseover");
	});
		
});