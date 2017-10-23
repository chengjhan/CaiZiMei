// 分頁
$(document).ready(function(){
	var href = document.location.href; // 取得當前 url
	var param = href.split("?"); // String
	var currentPage = param[1].split("="); // String
	var id_li_page = "#id-li-page-" + currentPage[1];
	$(id_li_page).addClass("active").children("a").removeAttr("href");
});

// 影片開關
$(document).on("click", ".ht-status-switch", function(){
	var $this = $(this);
	var ht_id = $this.attr("data-ht-id");
	var ht_status = $this.children("img").attr("data-ht-status");				
	$.get("../html/switch.ajax", {"ht_id": ht_id}, function(data){
		$("img[data-ht-status='1']").attr("src", "../images/icon_false.svg");
		$("img[data-ht-status='1']").attr("data-ht-status", "0");
		$this.children("img").attr("src", "../images/icon_true.svg");
		$this.children("img").attr("data-ht-status", "1");
	});
});