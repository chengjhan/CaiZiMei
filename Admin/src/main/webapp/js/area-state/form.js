// validation
$(document).ready(function(){
	$("form").validate({
		rules: {
			st_CountryBean: {
				min: 1
			},
			st_name: {
				required: true,
				maxlength: 20
			},
			st_rank: {
				max: 99
			},
		},
		messages: {
			st_CountryBean: {
				min: "這裡必須選擇"
			},
			st_name: {
				required: "這裡必須填入資料",
				maxlength: "名稱必須小於20個字"
			},
			st_rank: {
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