// validation
$(document).ready(function(){
	
	// regex
	$.validator.addMethod("pattern", function(value, element, regex){
		return regex.test(value);
	});
	
	$("form").validate({
		rules: {
			co_iso: {
				pattern: /^$|^[a-zA-Z]{2}$/,
			},
			co_name: {
				required: true,
				maxlength: 20
			},
			co_phonecode: {
				digits: true,
				maxlength: 5
			},
			co_rank: {
				max: 99
			},
		},
		messages: {
			co_iso: {
				pattern: "代碼必須填入2個英文字",
			},
			co_name: {
				required: "這裡必須填入資料",
				maxlength: "名稱必須小於20個字"
			},
			co_phonecode: {
				digits: "電話碼必須為數字",
				maxlength: "電話碼必須小於5個字"
			},
			co_rank: {
				max: "排序必須填入小於99的數字"
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