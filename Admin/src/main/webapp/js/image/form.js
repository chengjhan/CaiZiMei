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

// 預覽上傳圖片
$(document).on("change", "#image-uploader", function(){
    preview(this);
    $("#image-reset img").removeAttr("style");
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
				required: "必須填入資料",
				maxlength: "必須小於20個字"
			},
			im_url: {
				url: "必須填入正確的網址格式",
				maxlength: "必須小於100個字"
			},
			im_rank: {
				max: "必須填入小於99的數字",
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