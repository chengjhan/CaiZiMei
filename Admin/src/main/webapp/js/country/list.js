// 國家開關
$(document).on("click", ".co-status-switch", function(){
	var $this = $(this);
	var co_id = $this.attr("data-co-id");
	var co_status = $this.children("img").attr("data-co-status");				
	$.get("../country/switch.ajax", {"co_id": co_id}, function(data){
		if(co_status == "1"){
//			alert("將關閉 「" + data + "」。");
			$this.children("img").attr("src", "../images/false.svg");
			$this.children("img").attr("data-co-status", "0");
		}else{
//			alert("將開啟 「" + data + "」。");
			$this.children("img").attr("src", "../images/true.svg");
			$this.children("img").attr("data-co-status", "1");
		}
	});
});