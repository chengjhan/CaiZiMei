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