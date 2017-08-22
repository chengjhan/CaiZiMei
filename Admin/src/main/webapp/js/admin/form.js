// 帳號重複驗證
$("#ad_username").blur(function(){
	var ad_username = $(this).val();
	var ad_username_span = $("#id-span-ad-username");
	ad_username_span.empty();
	$.get("../admin/username-repeat.ajax", {"ad_username": ad_username}, function(data){
		if(data == "1"){
			ad_username_span.text("已使用");					
		}
	});
});

// validation
$(document).ready(function(){
	
	// regex
	$.validator.addMethod("pattern", function(value, element, regex){
		return regex.test(value);
	});
	
	// repeat
	$.validator.addMethod("repeat", function(value, element, regex){
		return regex.test(value);
	});
	
	$("form").validate({
		rules: {
			ad_username: {
				required: true,
				pattern: /^[a-zA-Z0-9_]+$/,
				minlength: 3,
				maxlength: 20
			},
			ad_password: {
				required: true,
				pattern: /^(?=.*([a-z]|[A-Z]))(?=.*[0-9])(?=\S+$).+$/,
				minlength: 8,
				maxlength: 32
			},
			ad_password_again: {
				required: true,
				equalTo : "#ad_password"
			},
			ad_lastname: {
				maxlength: 20
			},
			ad_firstname: {
				maxlength: 20
			},
			ad_email: {
				required: true,
				email: true,
				maxlength: 50
			},
			// change-password.jsp
			ad_password_old: {
				required: true
			},
			ad_password_new: {
				required: true,
				pattern: /^(?=.*([a-z]|[A-Z]))(?=.*[0-9])(?=\S+$).+$/,
				minlength: 8,
				maxlength: 32
			},
			ad_password_new_again: {
				required: true,
				equalTo : "#ad_password_new"
			},
		},
		messages: {
			ad_username: {
				required: "這裡必須填入資料",
				pattern: "帳號只接受英文大小寫、數字及底線符號",
				minlength: "帳號必須大於3個字",
				maxlength: "帳號必須小於20個字"
			},
			ad_password: {
				required: "這裡必須填入資料",
				pattern: "密碼必須包含英文及數字，不可填入空白",
				minlength: "密碼必須大於8個字",
				maxlength: "密碼必須小於32個字"
			},
			ad_password_again: {
				required: "這裡必須填入資料",
				equalTo: "密碼重複錯誤"
			},
			ad_lastname: {
				maxlength: "姓氏必須小於20個字"
			},
			ad_firstname: {
				maxlength: "名字必須小於20個字"
			},
			ad_email: {
				required: "這裡必須填入資料",
				email: "信箱必須填入正確的格式",
				maxlength: "信箱必須小於50個字"
			},
			// change-password.jsp
			ad_password_old: {
				required: "這裡必須填入資料"
			},
			ad_password_new: {
				required: "這裡必須填入資料",
				pattern: "密碼必須包含英文及數字，不可填入空白",
				minlength: "密碼必須大於8個字",
				maxlength: "密碼必須小於32個字"
			},
			ad_password_new_again: {
				required: "這裡必須填入資料",
				equalTo: "密碼重複錯誤"
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