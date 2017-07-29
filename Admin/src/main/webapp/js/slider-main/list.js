// 圖片開關
$(document).on("click", ".sm-status-switch", function(){
	var $this = $(this);
	var sm_id = $this.attr("data-sm-id");
	var sm_status = $this.children("img").attr("data-sm-status");				
	$.get("../slider-main/switch.ajax", {"sm_id": sm_id}, function(data){
		if(sm_status == "1"){
//			alert("將關閉 「" + data + "」。");
			$this.children("img").attr("src", "../images/false.svg");
			$this.children("img").attr("data-sm-status", "0");
		}else{
//			alert("將開啟 「" + data + "」。");
			$this.children("img").attr("src", "../images/true.svg");
			$this.children("img").attr("data-sm-status", "1");
		}
	});
});