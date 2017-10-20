$(document).ready(function(){
	
	$.getJSON("../video/video-related.ajax", function(data){
		$.each(data, function(index, videoBean){
			var video_related = "#video-related-" + index + " iframe"
			$(video_related).attr("src", videoBean.vi_tag);
		});
	});
});