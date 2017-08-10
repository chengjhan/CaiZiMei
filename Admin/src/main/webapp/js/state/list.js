var country_select = $("#id-input-st-co-id");

// 區域 table
country_select.change(function(){
	var stateList_tbody = $("table tbody");
	stateList_tbody.empty();
	var st_co_id = country_select.val();
	$.getJSON("../state/choice-country-state-list.ajax", {"st_co_id": st_co_id}, function(data){
		$.each(data, function(index, stateBean){
			index = index + 1;
			var count_td = $("<td></td>").append(index);
//			var st_id_td = $("<td></td>").append(stateBean.st_id);
			var st_name_td = $("<td></td>").append(stateBean.st_name);
			var st_rank_td = $("<td></td>").append(stateBean.st_rank);
			var edit_img = $("<img src='../images/icon_edit.svg'>");
			var edit_a = $("<a href='../state/edit?st_id=" + stateBean.st_id + "'></a>").append(edit_img);
			var edit_div = $("<div class='edit-button'></div>").append(edit_a);
			var edit_td = $("<td></td>").append(edit_div);
			var delete_img = $("<img src='../images/icon_delete.svg'>");
			var delete_a = $("<a href='../state/delete?st_id=" + stateBean.st_id + "'></a>").append(delete_img);
			var delete_div = $("<div class='delete-button'></div>").append(delete_a);
			var delete_td = $("<td></td>").append(delete_div);
//			var stateList_tr = $("<tr></tr>").append([count_td, st_id_td, st_name_td, st_rank_td, edit_td, delete_td]);
			var stateList_tr = $("<tr></tr>").append([count_td, st_name_td, st_rank_td, edit_td, delete_td]);
			stateList_tbody.append(stateList_tr);
		});
	});
});