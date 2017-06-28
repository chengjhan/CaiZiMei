$(document).ready(function(){
		
	// facebook icon mouseover
	$("#id-svg-facebook").hover(function(){
		$("#id-svg-facebook g path").attr("fill", "#FFFFFF");
	}, function(){
		$("#id-svg-facebook g path").attr("fill", "#CCCCCC");
	});
	
});