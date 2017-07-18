var country_select = $("#id-input-ci-co-id");
var state_select = $("#id-input-ci-st-id");

$(document).ready(function(){
	
	// 國家 select
	$.getJSON("../country/all-country-list.ajax", function(data){
		$.each(data, function(index, countryBean){
			var country_option = $("<option value=" + countryBean.co_id + "></option>").append(countryBean.co_name);
			country_select.append(country_option);
		});
	});
});

// 區域 select
country_select.change(function(){
	state_select.empty();
	state_select.append("<option value='0'>請選擇區域</option>");
	var cityList_tbody = $("table tbody");
	cityList_tbody.empty();
	var st_co_id = country_select.val();
	$.getJSON("../state/choice-country-state-list.ajax", {"st_co_id": st_co_id}, function(data){
		$.each(data, function(index, stateBean){
			var state_option = $("<option value=" + stateBean.st_id + "></option>").append(stateBean.st_name);
			state_select.append(state_option);
		});
	});
});

// 城市 table
state_select.change(function(){
	var cityList_tbody = $("table tbody");
	cityList_tbody.empty();
	var ci_st_id = state_select.val();
	$.getJSON("../city/choice-state-city-list.ajax", {"ci_st_id": ci_st_id}, function(data){
		$.each(data, function(index, cityBean){
			index = index + 1;
			var count_td = $("<td></td>").append(index);
			var ci_id_td = $("<td></td>").append(cityBean.ci_id);
			var ci_name_td = $("<td></td>").append(cityBean.ci_name);
			var ci_rank_td = $("<td></td>").append(cityBean.ci_rank);
			var edit_a = $("<a href='../city/edit?ci_id=" + cityBean.ci_id + "'></a>").append("編輯");
			var edit_td = $("<td></td>").append(edit_a);
			var delete_a = $("<a href='../city/delete?ci_id=" + cityBean.ci_id + "'></a>").append("刪除");
			var delete_td = $("<td></td>").append(delete_a);
			var cityList_tr = $("<tr></tr>").append([count_td, ci_id_td, ci_name_td, ci_rank_td, edit_td, delete_td]);
			cityList_tbody.append(cityList_tr);
		});
	});
});