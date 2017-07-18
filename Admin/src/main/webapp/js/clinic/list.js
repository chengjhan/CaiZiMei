// 診所 table
$("#id-input-switch").change(function(){
	
	var clinicList_tbody = $("table tbody");
	
	if(this.checked){
		
		// 開啟的診所
		clinicList_tbody.empty();
		$.getJSON("../clinic/open-clinic-list.ajax", function(data){
			$.each(data, function(index, clinicBean){
				index = index + 1;
				var count_td = $("<td></td>").append(index);
				var cl_id_td = $("<td></td>").append(clinicBean.cl_id);
				var cl_name_td = $("<td></td>").append(clinicBean.cl_name);
				var cl_eng_name_td = $("<td></td>").append(clinicBean.cl_eng_name);
				var cl_localphone_td = $("<td></td>").append(clinicBean.cl_localphone);
				var co_name_td = $("<td></td>").append(clinicBean.cl_CityBean.ci_StateBean.st_CountryBean.co_name);
				var st_name_td = $("<td></td>").append(clinicBean.cl_CityBean.ci_StateBean.st_name);
				var ci_name_td = $("<td></td>").append(clinicBean.cl_CityBean.ci_name);
				var	cl_address_td = $("<td></td>").append(clinicBean.cl_address);
				var	cl_latitude_td = $("<td></td>").append(clinicBean.cl_latitude);
				var	cl_longitude_td = $("<td></td>").append(clinicBean.cl_longitude);
				var	cl_url_td = $("<td></td>").append(clinicBean.cl_url);
				var	cl_insert_time_td = $("<td></td>").append(clinicBean.cl_insert_time);
				var	cl_update_time_td = $("<td></td>").append(clinicBean.cl_update_time);
				var	cl_status_td = $("<td></td>").append(clinicBean.cl_status);
				var	cl_status_time_td = $("<td></td>").append(clinicBean.cl_status_time);
				var edit_a = $("<a href='../clinic/edit?cl_id=" + clinicBean.cl_id + "'></a>").append("編輯");
				var edit_td = $("<td></td>").append(edit_a);
				var switch_a = $("<a href='../clinic/switch?cl_id=" + clinicBean.cl_id + "'></a>").append("變更");
				var switch_td = $("<td></td>").append(switch_a);
				var clinicList_tr = $("<tr></tr>").append([count_td, cl_id_td, cl_name_td, cl_eng_name_td, cl_localphone_td, co_name_td, st_name_td, ci_name_td, cl_address_td, cl_latitude_td, cl_longitude_td, cl_url_td, cl_insert_time_td, cl_update_time_td, cl_status_td, cl_status_time_td, edit_td, switch_td]);
				clinicList_tbody.append(clinicList_tr);
			});
		});
	}else{
	
		// 所有診所
		clinicList_tbody.empty();
		$.getJSON("../clinic/all-clinic-list.ajax", function(data){
			$.each(data, function(index, clinicBean){
				index = index + 1;
				var count_td = $("<td></td>").append(index);
				var cl_id_td = $("<td></td>").append(clinicBean.cl_id);
				var cl_name_td = $("<td></td>").append(clinicBean.cl_name);
				var cl_eng_name_td = $("<td></td>").append(clinicBean.cl_eng_name);
				var cl_localphone_td = $("<td></td>").append(clinicBean.cl_localphone);
				var co_name_td = $("<td></td>").append(clinicBean.cl_CityBean.ci_StateBean.st_CountryBean.co_name);
				var st_name_td = $("<td></td>").append(clinicBean.cl_CityBean.ci_StateBean.st_name);
				var ci_name_td = $("<td></td>").append(clinicBean.cl_CityBean.ci_name);
				var	cl_address_td = $("<td></td>").append(clinicBean.cl_address);
				var	cl_latitude_td = $("<td></td>").append(clinicBean.cl_latitude);
				var	cl_longitude_td = $("<td></td>").append(clinicBean.cl_longitude);
				var	cl_url_td = $("<td></td>").append(clinicBean.cl_url);
				var	cl_insert_time_td = $("<td></td>").append(clinicBean.cl_insert_time);
				var	cl_update_time_td = $("<td></td>").append(clinicBean.cl_update_time);
				var	cl_status_td = $("<td></td>").append(clinicBean.cl_status);
				var	cl_status_time_td = $("<td></td>").append(clinicBean.cl_status_time);
				var edit_a = $("<a href='../clinic/edit?cl_id=" + clinicBean.cl_id + "'></a>").append("編輯");
				var edit_td = $("<td></td>").append(edit_a);
				var switch_a = $("<a href='../clinic/switch?cl_id=" + clinicBean.cl_id + "'></a>").append("變更");
				var switch_td = $("<td></td>").append(switch_a);
				var clinicList_tr = $("<tr></tr>").append([count_td, cl_id_td, cl_name_td, cl_eng_name_td, cl_localphone_td, co_name_td, st_name_td, ci_name_td, cl_address_td, cl_latitude_td, cl_longitude_td, cl_url_td, cl_insert_time_td, cl_update_time_td, cl_status_td, cl_status_time_td, edit_td, switch_td]);
				clinicList_tbody.append(clinicList_tr);
			});
		});
	}
});