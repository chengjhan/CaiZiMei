var image_upload = $(".image-upload");

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
function preview(input){
	if (input.files && input.files[0]){
		image_upload.removeAttr("style");
		image_upload.siblings("p[class='error']").remove();
		if(!isImage(input.files[0].name.split(".").pop().toLowerCase())){
			image_upload.find("img").attr("src", "../images/image/upload.png");
			image_upload.attr("style", "border-color:red");
			image_upload.after("<p class='error'>請上傳副檔名為 jpg 或 png 的圖片</p>");
		}else{
			var reader = new FileReader();
			reader.onload = function(e){
				image_upload.find("img").attr("src", e.target.result);
			}
			reader.readAsDataURL(input.files[0]);			
		}
	}
}

// 預覽上傳圖片
$(document).on("change", "#image-uploader", function(){
    preview(this);
    $("#image-reset").removeAttr("style");
});

// image validation
$(".btn-success").on("click", function(){
	image_upload.removeAttr("style");
	image_upload.siblings("p[class='error']").remove();
	if(image_upload.find("img").attr("src").indexOf("/images/image/upload.png") != -1){
		image_upload.attr("style", "border-color:red");
		image_upload.after("<p class='error'>請上傳副檔名為 jpg 或 png 的圖片</p>");
		return false;
	}
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
				max: "排序必須填入小於99的數字"
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