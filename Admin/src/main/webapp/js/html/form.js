// validation
$(document).ready(function(){
	
	// regex
	$.validator.addMethod("pattern", function(value, element, regex){
		return regex.test(value);
	});
	
	$("form").validate({
		rules: {
			ht_name: {
				maxlength: 50
			},
			ht_tag: {
				required: true
			},
			ht_rank: {
				max: 99
			},
		},
		messages: {
			ht_name: {
				maxlength: "名稱必須小於50個字"
			},
			ht_tag: {
				required: "這裡必須填入資料"
			},
			ht_rank: {
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