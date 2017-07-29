var createClinicListTable = function(data){
	var franchiseeList_tbody = $("table tbody");
	franchiseeList_tbody.empty();
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
//		var	fr_insert_time_td = $("<td></td>").append(franchiseeBean.fr_insert_time);
//		var	fr_update_time_td = $("<td></td>").append(franchiseeBean.fr_update_time);
//		var	fr_status_time_td = $("<td></td>").append(franchiseeBean.fr_status_time);
		var edit_img = $("<img src='../images/edit.svg' style='width:100%'>");
		var edit_a = $("<a href='../franchisee/edit?fr_id=" + franchiseeBean.fr_id + "'></a>").append(edit_img);
		var edit_div = $("<div style='width:30px'></div>").append(edit_a);
		var edit_td = $("<td></td>").append(edit_div);
		var switch_img;
		if(franchiseeBean.fr_status == 1){
			switch_img = $("<img src='../images/true.svg' data-fr-status='1' style='width:100%'>");
		}else{
			switch_img = $("<img src='../images/false.svg' data-fr-status='0' style='width:100%'>");
		}
		var switch_div = $("<div class='fr-status-switch' data-fr-id='" + franchiseeBean.fr_id + "' style='width:30px;cursor:pointer'></div>").append(switch_img);
		var switch_td = $("<td></td>").append(switch_div);
//		var franchiseeList_tr = $("<tr></tr>").append([count_td, fr_id_td, fr_name_td, fr_eng_name_td, fr_localphone_td, co_name_td, st_name_td, ci_name_td, fr_address_td, fr_latitude_td, fr_longitude_td, fr_url_td, fr_insert_time_td, fr_update_time_td, fr_status_time_td, edit_td, switch_td]);
		var franchiseeList_tr = $("<tr></tr>").append([count_td, fr_id_td, fr_name_td, fr_eng_name_td, fr_localphone_td, co_name_td, st_name_td, ci_name_td, fr_address_td, fr_latitude_td, fr_longitude_td, fr_url_td, edit_td, switch_td]);
		franchiseeList_tbody.append(franchiseeList_tr);
	});
}

// 產生加盟店 table
$("#id-input-switch").change(function(){
	if(this.checked){
		
		// 開啟的加盟店列表
		$.getJSON("../franchisee/open-franchisee-list.ajax", createClinicListTable);
	}else{
	
		// 所有加盟店列表
		$.getJSON("../franchisee/all-franchisee-list.ajax", createClinicListTable);
	}
});

// 加盟店開關
$(document).on("click", ".fr-status-switch", function(){
	var $this = $(this);
	var fr_id = $this.attr("data-fr-id");
	var fr_status = $this.children("img").attr("data-fr-status");				
	$.get("../franchisee/switch.ajax", {"fr_id": fr_id}, function(data){
		if(fr_status == "1"){
//			alert("將關閉 「" + data + "」。");
			$this.children("img").attr("src", "../images/false.svg");
			$this.children("img").attr("data-fr-status", "0");
		}else{
//			alert("將開啟 「" + data + "」。");
			$this.children("img").attr("src", "../images/true.svg");
			$this.children("img").attr("data-fr-status", "1");
		}
	});
});