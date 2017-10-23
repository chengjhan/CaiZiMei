// 分頁
$(document).ready(function(){
	var href = document.location.href; // 取得當前 url
	var param = href.split("?"); // String
	var currentPage = param[1].split("="); // String
	var id_li_page = "#id-li-page-" + currentPage[1];
	$(id_li_page).addClass("active").children("a").removeAttr("href");
});

// html 開關
$(document).on("click", ".ht-status-switch img[data-ht-status='0']", function(){
	var $this = $(this);
	var ht_id = $this.parent("div").attr("data-ht-id");
	var ht_status_1 = $(".ht-status-switch img[data-ht-status='1']");
	$.get("../html/switch.ajax", {"ht_id": ht_id}, function(data){
		ht_status_1.attr("src", "../images/icon_false.svg");
		ht_status_1.attr("data-ht-status", "0");
		$this.attr("src", "../images/icon_true.svg");
		$this.attr("data-ht-status", "1");
	});
});