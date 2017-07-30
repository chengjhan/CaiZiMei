var country_select = $("#id-input-ba-co-id");
var state_select = $("#id-input-ba-st-id");
var city_select = $("#id-input-ba-ci-id");

// 區域 select
country_select.change(function(){
	state_select.empty();
	state_select.append("<option value='0'>請選擇區域</option>");
	city_select.empty();
	city_select.append("<option value='0'>請選擇城市</option>");
	var st_co_id = country_select.val();
	$.getJSON("../state/choice-country-state-list.ajax", {"st_co_id": st_co_id}, function(data){
		$.each(data, function(index, stateBean){
			var state_option = $("<option value=" + stateBean.st_id + "></option>").append(stateBean.st_name);
			state_select.append(state_option);
		});
	});
});

// 城市 select
state_select.change(function(){
	var city_select = $("#id-input-ba-ci-id");
	city_select.empty();
	city_select.append("<option value='0'>請選擇城市</option>");
	var ci_st_id = state_select.val();
	$.getJSON("../city/choice-state-city-list.ajax", {"ci_st_id": ci_st_id}, function(data){
		$.each(data, function(index, cityBean){
			var city_option = $("<option value=" + cityBean.ci_id + "></option>").append(cityBean.ci_name);
			city_select.append(city_option);
		});
	});
});