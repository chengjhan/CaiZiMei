// 影片開關
$(document).on("click", ".vi-status-switch img[data-vi-status='0']", function(){
	var $this = $(this);
	var vi_id = $this.parent("div").attr("data-vi-id");
	var vi_status_1 = $(".vi-status-switch img[data-vi-status='1']");				
	$.get("../video/switch.ajax", {"vi_id": vi_id}, function(data){
		vi_status_1.attr("src", "../images/icon_false.svg");
		vi_status_1.attr("data-vi-status", "0");
		vi_status_1.attr("title", "開啟");
		$this.attr("src", "../images/icon_true.svg");
		$this.attr("data-vi-status", "1");
		$this.removeAttr("title");
	});
});