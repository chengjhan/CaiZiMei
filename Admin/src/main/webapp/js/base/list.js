// 分頁
$(document).ready(function(){
	var href = document.location.href; // 取得當前 url
	var param = href.split("?"); // String
	var currentPage = param[1].split("="); // String
	var id_li_page = "#id-li-page-" + currentPage[1];
	$(id_li_page).addClass("active").children("a").removeAttr("href");
});

// 據點開關
$(document).on("click", ".ba-status-switch", function(){
	var $this = $(this);
	var ba_id = $this.attr("data-ba-id");
	var ba_status = $this.children("img").attr("data-ba-status");				
	$.get("../base/switch.ajax", {"ba_id": ba_id}, function(data){
		if(ba_status == "1"){
//			alert("將關閉 「" + data + "」。");
			$this.children("img").attr("src", "../images/icon_false.svg");
			$this.children("img").attr("data-ba-status", "0");
		}else{
//			alert("將開啟 「" + data + "」。");
			$this.children("img").attr("src", "../images/icon_true.svg");
			$this.children("img").attr("data-ba-status", "1");
		}
	});
});