// 預覽上傳圖片
function preview(input){
	if (input.files && input.files[0]) {
		var div_image_upload = $(".image-upload");
		div_image_upload.removeAttr("style");
		div_image_upload.siblings("p").remove();
		if(!isImage(input.files[0].name.split(".").pop().toLowerCase())){
			div_image_upload.find("img").attr("src", "../images/image/upload.png");
			div_image_upload.attr("style", "border-color:red");
			div_image_upload.after("<p class='error'>請上傳副檔名為 jpg 或 png 的圖片</p>");
		}else{
			var reader = new FileReader();
			reader.onload = function(e) {
				div_image_upload.find("img").attr("src", e.target.result);
			}
			reader.readAsDataURL(input.files[0]);			
		}
	}
}

// 判斷圖片副檔名
function isImage(extension){
	if(extension == "jpg"){
		return true;
	}
	if(extension == "png"){
		return true;
	}
	return false;
}

// 預覽上傳圖片
$(document).on("change", "#image-uploader", function(){
    preview(this);
    $("#image-reset").removeAttr("style");
});

// validation
$(document).ready(function(){
	$("form").validate({
		rules: {
			im_name: {
				required: true,
				maxlength: 20
			},
			im_url: {
				url: true,
				maxlength: 100
			},
			im_rank: {
				max: 99
			}
		},
		messages: {
			im_name: {
				required: "這裡必須填入資料",
				maxlength: "名稱必須小於20個字"
			},
			im_url: {
				url: "網址必須填入正確的格式",
				maxlength: "網址必須小於100個字"
			},
			im_rank: {
				max: "排序必須填入小於99的數字",
			}
		},
		highlight: function(element){
			$(element).addClass("form-error");
		},
		unhighlight: function(element){
			$(element).removeClass("form-error");
		},
		submitHandler: function(form){
			form.submit();
	    }
	});
});