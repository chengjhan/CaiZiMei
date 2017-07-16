$(document).ready(function(){
	
	// 國家 select
	$.getJSON("../country/select-all.ajax", function(data){
		$.each(data, function(index, countryBean){
			var countryList_option = $("<option value=" + countryBean.co_id + "></option>").append(countryBean.co_name);
			$("#id-input-s-co-id").append(countryList_option);
		});
	});
});

// 區域 table
$("#id-input-s-co-id").change(function(){
	var stateList_tbody = $("table tbody");
	stateList_tbody.empty();
	var s_co_id = $("#id-input-s-co-id").val();
	$.getJSON("../state/select-by-country.ajax", {"s_co_id": s_co_id}, function(data){
		$.each(data, function(index, stateBean){
			index = index + 1;
			var count_td = $("<td></td>").append(index);
			var s_id_td = $("<td></td>").append(stateBean.s_id);
			var s_name_td = $("<td></td>").append(stateBean.s_name);
			var s_rank_td = $("<td></td>").append(stateBean.s_rank);
			var edit_a = $("<a href='../state/edit?s_id=" + stateBean.s_id + "'></a>").append("編輯");
			var edit_td = $("<td></td>").append(edit_a);
			var delete_a = $("<a href='../state/delete?s_id=" + stateBean.s_id + "'></a>").append("刪除");
			var delete_td = $("<td></td>").append(delete_a);
			var stateList_tr = $("<tr></tr>").append([count_td, s_id_td, s_name_td, s_rank_td, edit_td, delete_td]);
			stateList_tbody.append(stateList_tr);
		});
	});
});