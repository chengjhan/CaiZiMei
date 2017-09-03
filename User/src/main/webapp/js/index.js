$(document).ready(function(){
	
	// 相關影音
	$.getJSON("video/open-video-main.ajax", function(data){
		$(".video-main iframe").attr("src", data.vi_tag);
	});
	
});