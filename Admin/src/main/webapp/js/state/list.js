var country_select = $("#id-input-st-co-id");

$(document).ready(function(){
	
	// 國家 select
	$.getJSON("../country/select-all.ajax", function(data){
		$.each(data, function(index, countryBean){
			var country_option = $("<option value=" + countryBean.co_id + "></option>").append(countryBean.co_name);
			country_select.append(country_option);
		});
	});
});

// 區域 table
country_select.change(function(){
	var stateList_tbody = $("table tbody");
	stateList_tbody.empty();
	var st_co_id = country_select.val();
	$.getJSON("../state/select-by-country.ajax", {"st_co_id": st_co_id}, function(data){
		$.each(data, function(index, stateBean){
			index = index + 1;
			var count_td = $("<td></td>").append(index);
			var st_id_td = $("<td></td>").append(stateBean.st_id);
			var st_name_td = $("<td></td>").append(stateBean.st_name);
			var st_rank_td = $("<td></td>").append(stateBean.st_rank);
			var edit_a = $("<a href='../state/edit?st_id=" + stateBean.st_id + "'></a>").append("編輯");
			var edit_td = $("<td></td>").append(edit_a);
			var delete_a = $("<a href='../state/delete?st_id=" + stateBean.st_id + "'></a>").append("刪除");
			var delete_td = $("<td></td>").append(delete_a);
			var stateList_tr = $("<tr></tr>").append([count_td, st_id_td, st_name_td, st_rank_td, edit_td, delete_td]);
			stateList_tbody.append(stateList_tr);
		});
	});
});