// 圖片放大
$(".image-list-table>tbody>tr>td>img").on("click", function(){
	$(".image-zoom").removeAttr("style");
	document.body.style.overflow = 'hidden';
	$(".image-zoom img").attr("src", $(this).attr("src"));
});

$(".image-zoom").on("click", function(){
	$(".image-zoom").attr("style", "display:none");
	document.body.style.overflow = 'auto';
});

// 圖片開關
$(document).on("click", ".im-status-switch", function(){
	var $this = $(this);
	var im_id = $this.attr("data-im-id");
	var im_status = $this.children("img").attr("data-im-status");				
	$.get("../image/switch.ajax", {"im_id": im_id}, function(data){
		if(im_status == "1"){
//			alert("將關閉 「" + data + "」。");
			$this.children("img").attr("src", "../images/icon_false.svg");
			$this.children("img").attr("data-im-status", "0");
		}else{
//			alert("將開啟 「" + data + "」。");
			$this.children("img").attr("src", "../images/icon_true.svg");
			$this.children("img").attr("data-im-status", "1");
		}
	});
});