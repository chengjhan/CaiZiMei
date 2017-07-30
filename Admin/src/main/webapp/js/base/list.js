// 據點開關
$(document).on("click", ".ba-status-switch", function(){
	var $this = $(this);
	var ba_id = $this.attr("data-ba-id");
	var ba_status = $this.children("img").attr("data-ba-status");				
	$.get("../base/switch.ajax", {"ba_id": ba_id}, function(data){
		if(ba_status == "1"){
//			alert("將關閉 「" + data + "」。");
			$this.children("img").attr("src", "../images/false.svg");
			$this.children("img").attr("data-ba-status", "0");
		}else{
//			alert("將開啟 「" + data + "」。");
			$this.children("img").attr("src", "../images/true.svg");
			$this.children("img").attr("data-ba-status", "1");
		}
	});
});