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