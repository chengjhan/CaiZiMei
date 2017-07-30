var createClinicListTable = function(data){
	var clinicList_tbody = $("table tbody");
	clinicList_tbody.empty();
	$.each(data, function(index, baseBean){
		index = index + 1;
		var count_td = $("<td></td>").append(index);
		var ba_id_td = $("<td></td>").append(baseBean.ba_id);
		var ba_name_td = $("<td></td>").append(baseBean.ba_name);
		var ba_eng_name_td = $("<td></td>").append(baseBean.ba_eng_name);
		var ba_localphone_td = $("<td></td>").append(baseBean.ba_localphone);
		var co_name_td = $("<td></td>").append(baseBean.ba_CityBean.ci_StateBean.st_CountryBean.co_name);
		var st_name_td = $("<td></td>").append(baseBean.ba_CityBean.ci_StateBean.st_name);
		var ci_name_td = $("<td></td>").append(baseBean.ba_CityBean.ci_name);
		var	ba_address_td = $("<td></td>").append(baseBean.ba_address);
		var	ba_latitude_td = $("<td></td>").append(baseBean.ba_latitude);
		var	ba_longitude_td = $("<td></td>").append(baseBean.ba_longitude);
		var ba_url_a = $("<a href='" + baseBean.ba_url + "'></a>").append(baseBean.ba_url);
		var	ba_url_td = $("<td></td>").append(ba_url_a);	
//		var	ba_insert_time_td = $("<td></td>").append(baseBean.ba_insert_time);
//		var	ba_update_time_td = $("<td></td>").append(baseBean.ba_update_time);
//		var	ba_status_time_td = $("<td></td>").append(baseBean.ba_status_time);
		var edit_img = $("<img src='../images/edit.svg' style='width:100%'>");
		var edit_a = $("<a href='../clinic/edit?ba_id=" + baseBean.ba_id + "'></a>").append(edit_img);
		var edit_div = $("<div style='width:30px'></div>").append(edit_a);
		var edit_td = $("<td></td>").append(edit_div);
		var switch_img;
		if(baseBean.ba_status == 1){
			switch_img = $("<img src='../images/true.svg' data-ba-status='1' style='width:100%'>");
		}else{
			switch_img = $("<img src='../images/false.svg' data-ba-status='0' style='width:100%'>");
		}
		var switch_div = $("<div class='ba-status-switch' data-ba-id='" + baseBean.ba_id + "' style='width:30px;cursor:pointer'></div>").append(switch_img);
		var switch_td = $("<td></td>").append(switch_div);
//		var clinicList_tr = $("<tr></tr>").append([count_td, ba_id_td, ba_name_td, ba_eng_name_td, ba_localphone_td, co_name_td, st_name_td, ci_name_td, ba_address_td, ba_latitude_td, ba_longitude_td, ba_url_td, ba_insert_time_td, ba_update_time_td, ba_status_time_td, edit_td, switch_td]);
		var clinicList_tr = $("<tr></tr>").append([count_td, ba_id_td, ba_name_td, ba_eng_name_td, ba_localphone_td, co_name_td, st_name_td, ci_name_td, ba_address_td, ba_latitude_td, ba_longitude_td, ba_url_td, edit_td, switch_td]);
		clinicList_tbody.append(clinicList_tr);
	});
}

// 產生診所 table
$("#id-input-switch").change(function(){
	if(this.checked){
		
		// 開啟的診所列表
		$.getJSON("../clinic/open-clinic-list.ajax", createClinicListTable);
	}else{
	
		// 所有診所列表
		$.getJSON("../clinic/all-clinic-list.ajax", createClinicListTable);
	}
});