// 顯示提示
$(".video-form-table p").on("click", function(){
	$(".youtube-help").removeAttr("style");
	document.body.style.overflow = 'hidden';
});

$(".youtube-help").on("click", function(){
	$(".youtube-help").attr("style", "display:none");
	document.body.style.overflow = 'auto';
});

// validation
$(document).ready(function(){
	
	// regex
	$.validator.addMethod("pattern", function(value, element, regex){
		return regex.test(value);
	});
	
	$("form").validate({
		rules: {
			vi_name: {
				maxlength: 50
			},
			vi_tag: {
				required: true
			},
			vi_rank: {
				max: 99
			},
		},
		messages: {
			vi_name: {
				maxlength: "名稱必須小於50個字"
			},
			vi_tag: {
				required: "這裡必須填入資料"
			},
			vi_rank: {
				max: "排序必須填入小於99的數字",
			},
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