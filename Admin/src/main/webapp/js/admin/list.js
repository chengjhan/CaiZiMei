// 管理員開關
$(document).on("click", ".ad-status-switch", function(){
	var $this = $(this);
	var ad_id = $this.attr("data-ad-id");
	var ad_status = $this.children("img").attr("data-ad-status");				
	$.getJSON("../admin/switch.ajax", {"ad_id": ad_id}, function(data){
		if(ad_status == "1"){
//			alert("將關閉 「" + data.ad_username + "」。");
			$this.children("img").attr("src", "../images/icon_false.svg");
			$this.children("img").attr("data-ad-status", "0");
			$this.parent("td").prev().text(data.ad_status_time);
		}else{
//			alert("將開啟 「" + data.ad_username + "」。");
			$this.children("img").attr("src", "../images/icon_true.svg");
			$this.children("img").attr("data-ad-status", "1");
			$this.parent("td").prev().text(data.ad_status_time);
		}
	});
});