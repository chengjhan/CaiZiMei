// load
document.write("<scr" + "ipt src=../js/validation/jquery.validate.min.js type='text/javascript' charset='utf-8'><\/scr" + "ipt>");

$(document).ready(function(){
	
	// regex
	$.validator.addMethod("pattern", function(value, element, regex){
		return regex.test(value);
	});
	
	// not equal
	$.validator.addMethod("notEqual", function(value, element, param){
		return this.optional(element) || value != $(param).val();
	});
	
	$("form").validate({
		rules: {
			// admin
			ad_username: {
				required: true,
				pattern: /^[a-zA-Z0-9_]+$/,
				minlength: 3,
				maxlength: 20,
				remote: { // 帳號重複驗證 (AJAX)
					url: "../admin/username-repeat.ajax", // 後台處理程序
					type: "post", // 數據發送方式
					dataType: "text", // 接受數據格式
					data: { // 要傳遞的數據
						ad_username: function(){
							return $("#ad_username").val();
						}
					}
				}
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
				maxlength: 50,
				remote: { // 信箱重複驗證 (AJAX)
					url: "../admin/email-repeat.ajax", // 後台處理程序
					type: "post", // 數據發送方式
					dataType: "text", // 接受數據格式
					data: { // 要傳遞的數據
						ad_email: function(){
							return $("#ad_email").val();
						}
					}
				}
			},
			// change-password.jsp
			ad_password_old: {
				required: true
			},
			ad_password_new: {
				required: true,
				pattern: /^(?=.*([a-z]|[A-Z]))(?=.*[0-9])(?=\S+$).+$/,
				minlength: 8,
				maxlength: 32,
				notEqual: "#ad_password_old"
			},
			ad_password_new_again: {
				required: true,
				equalTo : "#ad_password_new"
			},
			// area-country
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
			// area-state
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
			// area-city
			ci_CountryBean: {
				min: 1
			},
			ci_StateBean: {
				min: 1
			},
			ci_name: {
				required: true,
				maxlength: 20
			},
			ci_rank: {
				max: 99
			},
			// base
			ba_name: {
				required: true,
				maxlength: 20
			},
			ba_eng_name: {
				pattern: /^$|^[a-zA-Z0-9 ,.'-]+$/,
				maxlength: 50
			},
			ba_tel_code: {
				digits: true,
				maxlength: 5
			},
			ba_tel: {
				digits: true,
				maxlength: 20
			},
			ba_CountryBean: {
				min: 1
			},
			ba_StateBean: {
				min: 1
			},
			ba_CityBean: {
				min: 1
			},
			ba_address: {
				required: true,
				maxlength: 20
			},
			ba_url: {
				url: true,
				maxlength: 100
			},
			// image
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
			},
			// video
			vi_name: {
				maxlength: 50
			},
			vi_tag: {
				required: true
			},
			vi_rank: {
				max: 99
			},
			// html
			ht_name: {
				maxlength: 50
			},
			ht_tag: {
				required: true
			},
			ht_rank: {
				max: 99
			}
		},
		messages: {
			// admin
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
				maxlength: "密碼必須小於32個字",
				notEqual: "新密碼不可與舊密碼相同"
			},
			ad_password_new_again: {
				required: "這裡必須填入資料",
				equalTo: "密碼重複錯誤"
			},
			// area-country
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
			// area-state
			st_CountryBean: {
				min: "這裡必須選擇"
			},
			st_name: {
				required: "這裡必須填入資料",
				maxlength: "名稱必須小於20個字"
			},
			st_rank: {
				max: "排序必須填入小於99的數字"
			},
			// area-city
			ci_CountryBean: {
				min: "這裡必須選擇"
			},
			ci_StateBean: {
				min: "這裡必須選擇"
			},
			ci_name: {
				required: "這裡必須填入資料",
				maxlength: "名稱必須小於20個字"
			},
			ci_rank: {
				max: "排序必須填入小於99的數字"
			},
			// base
			ba_name: {
				required: "這裡必須填入資料",
				maxlength: "名稱必須小於20個字"
			},
			ba_eng_name: {
				pattern: "英文名稱只接受英文大小寫、數字、一般符號及空白",
				maxlength: "英文名稱必須小於50個字"
			},
			ba_tel_code: {
				digits: "區碼必須為數字",
				maxlength: "區碼必須小於5個字"
			},
			ba_tel: {
				digits: "電話必須為數字",
				maxlength: "電話必須小於20個字"
			},
			ba_CountryBean: {
				min: "這裡必須選擇"
			},
			ba_StateBean: {
				min: "這裡必須選擇"
			},
			ba_CityBean: {
				min: "這裡必須選擇"
			},
			ba_address: {
				required: "這裡必須填入資料",
				maxlength: "地址必須小於20個字"
			},
			ba_url: {
				url: "網址必須填入正確的格式",
				maxlength: "網址必須小於100個字"
			},
			// image
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
			},
			// video
			vi_name: {
				maxlength: "名稱必須小於50個字"
			},
			vi_tag: {
				required: "這裡必須填入資料"
			},
			vi_rank: {
				max: "排序必須填入小於99的數字"
			},
			// html
			ht_name: {
				maxlength: "名稱必須小於50個字"
			},
			ht_tag: {
				required: "這裡必須填入資料"
			},
			ht_rank: {
				max: "排序必須填入小於99的數字"
			}
		},
		highlight: function(element){
			$(element).addClass("form-error");
		},
		unhighlight: function(element){
			$(element).removeClass("form-error");
		},
		errorPlacement: function(error, element){
			// error <label>
			// element <input>
			if(element.attr("name") == "ba_tel_code"){
				error.appendTo(element.closest("td").find("#ba_tel_code_error"));
			}else if(element.attr("name") == "ba_tel"){
				error.appendTo(element.closest("td").find("#ba_tel_error"));
			}else{
				error.insertAfter(element); // default
			}
		},
		submitHandler: function(form){
			form.submit();
	    }
	});
});