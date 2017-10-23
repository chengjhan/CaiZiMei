// 分頁
$(document).ready(function(){
	var href = document.location.href; // 取得當前 url
	var param = href.split("?"); // String
	var currentPage = param[1].split("="); // String
	var id_li_page = "#id-li-page-" + currentPage[1];
	$(id_li_page).addClass("active").children("a").removeAttr("href");
});

// 影片開關
$(document).on("click", ".vi-status-switch img[data-vi-status='0']", function(){
	var $this = $(this);
	var vi_id = $this.parent("div").attr("data-vi-id");
	var vi_status_1 = $(".vi-status-switch img[data-vi-status='1']");				
	$.get("../video/switch.ajax", {"vi_id": vi_id}, function(data){
		vi_status_1.attr("src", "../images/icon_false.svg");
		vi_status_1.attr("data-vi-status", "0");
		$this.attr("src", "../images/icon_true.svg");
		$this.attr("data-vi-status", "1");
	});
});