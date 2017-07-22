// 加盟店 table
$("#id-input-switch").change(function(){
	
	var franchiseeList_tbody = $("table tbody");
	
	if(this.checked){
		
		// 開啟的加盟店列表
		franchiseeList_tbody.empty();
		$.getJSON("../franchisee/open-franchisee-list.ajax", function(data){
			$.each(data, function(index, franchiseeBean){
				index = index + 1;
				var count_td = $("<td></td>").append(index);
				var fr_id_td = $("<td></td>").append(franchiseeBean.fr_id);
				var fr_name_td = $("<td></td>").append(franchiseeBean.fr_name);
				var fr_eng_name_td = $("<td></td>").append(franchiseeBean.fr_eng_name);
				var fr_localphone_td = $("<td></td>").append(franchiseeBean.fr_localphone);
				var co_name_td = $("<td></td>").append(franchiseeBean.fr_CityBean.ci_StateBean.st_CountryBean.co_name);
				var st_name_td = $("<td></td>").append(franchiseeBean.fr_CityBean.ci_StateBean.st_name);
				var ci_name_td = $("<td></td>").append(franchiseeBean.fr_CityBean.ci_name);
				var	fr_address_td = $("<td></td>").append(franchiseeBean.fr_address);
				var	fr_latitude_td = $("<td></td>").append(franchiseeBean.fr_latitude);
				var	fr_longitude_td = $("<td></td>").append(franchiseeBean.fr_longitude);
				var fr_url_a = $("<a href='" + franchiseeBean.fr_url + "'></a>").append(franchiseeBean.fr_url);
				var	fr_url_td = $("<td></td>").append(fr_url_a);
				var	fr_insert_time_td = $("<td></td>").append(franchiseeBean.fr_insert_time);
				var	fr_update_time_td = $("<td></td>").append(franchiseeBean.fr_update_time);
				var	fr_status_td = $("<td></td>").append(franchiseeBean.fr_status);
				var	fr_status_time_td = $("<td></td>").append(franchiseeBean.fr_status_time);
				var edit_a = $("<a href='../franchisee/edit?fr_id=" + franchiseeBean.fr_id + "'></a>").append("編輯");
				var edit_td = $("<td></td>").append(edit_a);
				var switch_a = $("<a href='../franchisee/switch?fr_id=" + franchiseeBean.fr_id + "'></a>").append("變更");
				var switch_td = $("<td></td>").append(switch_a);
				var franchiseeList_tr = $("<tr></tr>").append([count_td, fr_id_td, fr_name_td, fr_eng_name_td, fr_localphone_td, co_name_td, st_name_td, ci_name_td, fr_address_td, fr_latitude_td, fr_longitude_td, fr_url_td, fr_insert_time_td, fr_update_time_td, fr_status_td, fr_status_time_td, edit_td, switch_td]);
				franchiseeList_tbody.append(franchiseeList_tr);
			});
		});
	}else{
	
		// 所有加盟店列表
		franchiseeList_tbody.empty();
		$.getJSON("../franchisee/all-franchisee-list.ajax", function(data){
			$.each(data, function(index, franchiseeBean){
				index = index + 1;
				var count_td = $("<td></td>").append(index);
				var fr_id_td = $("<td></td>").append(franchiseeBean.fr_id);
				var fr_name_td = $("<td></td>").append(franchiseeBean.fr_name);
				var fr_eng_name_td = $("<td></td>").append(franchiseeBean.fr_eng_name);
				var fr_localphone_td = $("<td></td>").append(franchiseeBean.fr_localphone);
				var co_name_td = $("<td></td>").append(franchiseeBean.fr_CityBean.ci_StateBean.st_CountryBean.co_name);
				var st_name_td = $("<td></td>").append(franchiseeBean.fr_CityBean.ci_StateBean.st_name);
				var ci_name_td = $("<td></td>").append(franchiseeBean.fr_CityBean.ci_name);
				var	fr_address_td = $("<td></td>").append(franchiseeBean.fr_address);
				var	fr_latitude_td = $("<td></td>").append(franchiseeBean.fr_latitude);
				var	fr_longitude_td = $("<td></td>").append(franchiseeBean.fr_longitude);
				var fr_url_a = $("<a href='" + franchiseeBean.fr_url + "'></a>").append(franchiseeBean.fr_url);
				var	fr_url_td = $("<td></td>").append(fr_url_a);	
				var	fr_insert_time_td = $("<td></td>").append(franchiseeBean.fr_insert_time);
				var	fr_update_time_td = $("<td></td>").append(franchiseeBean.fr_update_time);
				var	fr_status_td = $("<td></td>").append(franchiseeBean.fr_status);
				var	fr_status_time_td = $("<td></td>").append(franchiseeBean.fr_status_time);
				var edit_a = $("<a href='../franchisee/edit?fr_id=" + franchiseeBean.fr_id + "'></a>").append("編輯");
				var edit_td = $("<td></td>").append(edit_a);
				var switch_a = $("<a href='../franchisee/switch?fr_id=" + franchiseeBean.fr_id + "'></a>").append("變更");
				var switch_td = $("<td></td>").append(switch_a);
				var franchiseeList_tr = $("<tr></tr>").append([count_td, fr_id_td, fr_name_td, fr_eng_name_td, fr_localphone_td, co_name_td, st_name_td, ci_name_td, fr_address_td, fr_latitude_td, fr_longitude_td, fr_url_td, fr_insert_time_td, fr_update_time_td, fr_status_td, fr_status_time_td, edit_td, switch_td]);
				franchiseeList_tbody.append(franchiseeList_tr);
			});
		});
	}
});