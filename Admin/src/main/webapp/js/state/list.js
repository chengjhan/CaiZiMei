var country_select = $("#st_CountryBean");

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
			var switch_img;
			if(stateBean.st_status == 1){
				switch_img = $("<img src='../images/true.svg' data-st-status='1'>");
			}else{
				switch_img = $("<img src='../images/false.svg' data-st-status='0'>");
			}
			var switch_div = $("<div class='st-status-switch' data-st-id='" + stateBean.st_id + "'></div>").append(switch_img);
			var switch_td = $("<td></td>").append(switch_div);
//			var stateList_tr = $("<tr></tr>").append([count_td, st_id_td, st_name_td, st_rank_td, edit_td, switch_td]);
			var stateList_tr = $("<tr></tr>").append([count_td, st_name_td, st_rank_td, edit_td, switch_td]);
			stateList_tbody.append(stateList_tr);
		});
	});
});

// 區域開關
$(document).on("click", ".st-status-switch", function(){
	var $this = $(this);
	var st_id = $this.attr("data-st-id");
	var st_status = $this.children("img").attr("data-st-status");				
	$.get("../state/switch.ajax", {"st_id": st_id}, function(data){
		if(st_status == "1"){
//			alert("將關閉 「" + data + "」。");
			$this.children("img").attr("src", "../images/false.svg");
			$this.children("img").attr("data-st-status", "0");
		}else{
//			alert("將開啟 「" + data + "」。");
			$this.children("img").attr("src", "../images/true.svg");
			$this.children("img").attr("data-st-status", "1");
		}
	});
});