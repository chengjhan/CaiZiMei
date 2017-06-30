$(document).ready(function(){
		
	// facebook icon mouseover
	$("#id-svg-facebook").hover(function(){
		$("#id-svg-facebook g circle").attr("stroke", "#FFFFFF");
		$("#id-svg-facebook g path").attr("fill", "#FFFFFF");
	}, function(){
		$("#id-svg-facebook g circle").attr("stroke", "#CCCCCC");
		$("#id-svg-facebook g path").attr("fill", "#CCCCCC");
	});
	
	// youtube icon mouseover
	$("#id-svg-youtube").hover(function(){
		$("#id-svg-youtube g g").attr("fill", "#FFFFFF");
	}, function(){
		$("#id-svg-youtube g g").attr("fill", "#CCCCCC");
	});
	
});