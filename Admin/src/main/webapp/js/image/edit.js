// 預覽上傳圖片
function preview(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$(".image-preview img").attr("src", e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}