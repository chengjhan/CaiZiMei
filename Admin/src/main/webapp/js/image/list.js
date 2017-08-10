// 分頁
$(document).ready(function(){
	var href = document.location.href; // 取得當前 url
	var param = href.split("?"); // String
	var currentPage = param[1].split("="); // String
	var id_li_page = "#id-li-page-" + currentPage[1];
	$(id_li_page).addClass("active").children("a").removeAttr("href");
});

// 圖片開關
$(document).on("click", ".im-status-switch", function(){
	var $this = $(this);
	var im_id = $this.attr("data-im-id");
	var im_status = $this.children("img").attr("data-im-status");				
	$.get("../image/switch.ajax", {"im_id": im_id}, function(data){
		if(im_status == "1"){
//			alert("將關閉 「" + data + "」。");
			$this.children("img").attr("src", "../images/false.svg");
			$this.children("img").attr("data-im-status", "0");
		}else{
//			alert("將開啟 「" + data + "」。");
			$this.children("img").attr("src", "../images/true.svg");
			$this.children("img").attr("data-im-status", "1");
		}
	});
});