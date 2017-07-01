$(document).ready(function(){
		
	// facebook icon mouseover
	$("#id-svg-facebook").hover(function(){
		$("#id-svg-facebook circle").attr("stroke", "#FFFFFF");
		$("#id-svg-facebook path").attr("fill", "#FFFFFF");
	}, function(){
		$("#id-svg-facebook circle").attr("stroke", "#CCCCCC");
		$("#id-svg-facebook path").attr("fill", "#CCCCCC");
	});
	
	// youtube icon mouseover
	$("#id-svg-youtube").hover(function(){
		$("#id-svg-youtube g").attr("fill", "#FFFFFF");
	}, function(){
		$("#id-svg-youtube g").attr("fill", "#CCCCCC");
	});
});