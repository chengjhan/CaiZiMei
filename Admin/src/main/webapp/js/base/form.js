var country_select = $("#ba_CountryBean");
var state_select = $("#ba_StateBean");
var city_select = $("#ba_CityBean");

// 區域 select
country_select.change(function(){
	state_select.empty();
	state_select.append("<option value='0'>請選擇區域</option>");
	city_select.empty();
	city_select.append("<option value='0'>請選擇城市</option>");
	var st_co_id = country_select.val();
	$.getJSON("../area-state/choice-country-state-list.ajax", {"st_co_id": st_co_id}, function(data){
		$.each(data, function(index, stateBean){
			var state_option = $("<option value=" + stateBean.st_id + "></option>").append(stateBean.st_name);
			state_select.append(state_option);
		});
	});
});

// 城市 select
state_select.change(function(){
	city_select.empty();
	city_select.append("<option value='0'>請選擇城市</option>");
	var ci_st_id = state_select.val();
	$.getJSON("../area-city/choice-state-city-list.ajax", {"ci_st_id": ci_st_id}, function(data){
		$.each(data, function(index, cityBean){
			var city_option = $("<option value=" + cityBean.ci_id + "></option>").append(cityBean.ci_name);
			city_select.append(city_option);
		});
	});
});

// validation
$(document).ready(function(){
	
	// regex
	$.validator.addMethod("pattern", function(value, element, regex){
		return regex.test(value);
	});
	
	$("form").validate({
		rules: {
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
			}
		},
		messages: {
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
		},
		highlight: function(element){
			$(element).addClass("form-error");
		},
		unhighlight: function(element){
			$(element).removeClass("form-error");
		},
		errorPlacement: function(error, element){
			if(element.attr("name") == "ba_tel_code"){
				error.appendTo(element.closest("td").find("#ba_tel_code_error"));
			}else if(element.attr("name") == "ba_tel"){
				error.appendTo(element.closest("td").find("#ba_tel_error"));
			}else{
				$(element).closest("td").find(".error").append(error);
			}
		},
		submitHandler: function(form){
			form.submit();
	    }
	});
});