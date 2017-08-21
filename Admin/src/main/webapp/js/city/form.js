var country_select = $("#ci_CountryBean");
var state_select = $("#ci_StateBean");

// 區域 select
country_select.change(function(){
	state_select.empty();
	state_select.append("<option value='0'>請選擇區域</option>");
	var st_co_id = country_select.val();
	$.getJSON("../state/choice-country-state-list.ajax", {"st_co_id": st_co_id}, function(data){
		$.each(data, function(index, stateBean){
			var stateList_option = $("<option value=" + stateBean.st_id + "></option>").append(stateBean.st_name);
			state_select.append(stateList_option);
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
		},
		messages: {
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