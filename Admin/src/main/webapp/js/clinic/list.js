var createClinicListTable = function(data){
	var clinicList_tbody = $("table tbody");
	clinicList_tbody.empty();
	$.each(data, function(index, clinicBean){
		index = index + 1;
		var count_td = $("<td></td>").append(index);
		var cl_id_td = $("<td></td>").append(clinicBean.cl_id);
		var cl_name_td = $("<td></td>").append(clinicBean.cl_name);
//		var cl_eng_name_td = $("<td></td>").append(clinicBean.cl_eng_name);
		var cl_localphone_td = $("<td></td>").append(clinicBean.cl_localphone);
		var co_name_td = $("<td></td>").append(clinicBean.cl_CityBean.ci_StateBean.st_CountryBean.co_name);
		var st_name_td = $("<td></td>").append(clinicBean.cl_CityBean.ci_StateBean.st_name);
		var ci_name_td = $("<td></td>").append(clinicBean.cl_CityBean.ci_name);
		var	cl_address_td = $("<td></td>").append(clinicBean.cl_address);
		var	cl_latitude_td = $("<td></td>").append(clinicBean.cl_latitude);
		var	cl_longitude_td = $("<td></td>").append(clinicBean.cl_longitude);
		var cl_url_a = $("<a href='" + clinicBean.cl_url + "'></a>").append(clinicBean.cl_url);
		var	cl_url_td = $("<td></td>").append(cl_url_a);	
//		var	cl_insert_time_td = $("<td></td>").append(clinicBean.cl_insert_time);
		var	cl_update_time_td = $("<td></td>").append(clinicBean.cl_update_time);
//		var	cl_status_time_td = $("<td></td>").append(clinicBean.cl_status_time);
		var edit_a = $("<a href='../clinic/edit?cl_id=" + clinicBean.cl_id + "'></a>").append("編輯");
		var edit_td = $("<td></td>").append(edit_a);
		var switch_img;
		if(clinicBean.cl_status == 1){
			switch_img = $("<img src='../images/true.svg' data-cl-status='1' style='width:100%'>");
		}else{
			switch_img = $("<img src='../images/false.svg' data-cl-status='0' style='width:100%'>");
		}
		var switch_div = $("<div class='cl-status-switch' data-cl-id='" + clinicBean.cl_id + "' style='width:20px;cursor:pointer'></div>").append(switch_img);
		var switch_td = $("<td></td>").append(switch_div);
//		var clinicList_tr = $("<tr></tr>").append([count_td, cl_id_td, cl_name_td, cl_eng_name_td, cl_localphone_td, co_name_td, st_name_td, ci_name_td, cl_address_td, cl_latitude_td, cl_longitude_td, cl_url_td, cl_insert_time_td, cl_update_time_td, cl_status_time_td, edit_td, switch_td]);
		var clinicList_tr = $("<tr></tr>").append([count_td, cl_id_td, cl_name_td, cl_localphone_td, co_name_td, st_name_td, ci_name_td, cl_address_td, cl_latitude_td, cl_longitude_td, cl_url_td, cl_update_time_td, edit_td, switch_td]);
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

// 診所開關
$(document).on("click", ".cl-status-switch", function(){
	var $this = $(this);
	var cl_id = $this.attr("data-cl-id");
	var cl_status = $this.children("img").attr("data-cl-status");				
	$.get("../clinic/switch.ajax", {"cl_id": cl_id}, function(data){
		if(cl_status == "1"){
//			alert("將關閉 「" + data + "」。");
			$this.children("img").attr("src", "../images/false.svg");
		}else{
//			alert("將開啟 「" + data + "」。");
			$this.children("img").attr("src", "../images/true.svg");
		}
	});
});