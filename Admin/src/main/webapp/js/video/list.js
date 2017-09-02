// 分頁
$(document).ready(function(){
	var href = document.location.href; // 取得當前 url
	var param = href.split("?"); // String
	var currentPage = param[1].split("="); // String
	var id_li_page = "#id-li-page-" + currentPage[1];
	$(id_li_page).addClass("active").children("a").removeAttr("href");
});

// 影片開關
$(document).on("click", ".vi-status-switch", function(){
	var $this = $(this);
	var vi_id = $this.attr("data-vi-id");
	var vi_status = $this.children("img").attr("data-vi-status");				
	$.get("../video/switch.ajax", {"vi_id": vi_id}, function(data){
		$("img[data-vi-status='1']").attr("src", "../images/false.svg");
		$("img[data-vi-status='1']").attr("data-vi-status", "0");
		$this.children("img").attr("src", "../images/true.svg");
		$this.children("img").attr("data-vi-status", "1");
	});
});