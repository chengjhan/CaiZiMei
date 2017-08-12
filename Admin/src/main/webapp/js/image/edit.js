function preview(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$(".image-preview img").attr("src", e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

$(document).ready(function(){
	
	// 圖片上傳預覽
	$(document).on("change", "#image-upload", function(){
        preview(this);
    })
});