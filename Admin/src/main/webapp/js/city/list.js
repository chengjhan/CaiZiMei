var country_select = $("#ci_CountryBean");
var state_select = $("#ci_StateBean");

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
//			var ci_id_td = $("<td></td>").append(cityBean.ci_id);
			var ci_name_td = $("<td></td>").append(cityBean.ci_name);
			var ci_rank_td = $("<td></td>").append(cityBean.ci_rank);
			var edit_img = $("<img src='../images/icon_edit.svg'>");
			var edit_a = $("<a href='../city/edit?ci_id=" + cityBean.ci_id + "'></a>").append(edit_img);
			var edit_div = $("<div class='edit-button'></div>").append(edit_a);
			var edit_td = $("<td></td>").append(edit_div);
			var switch_img;
			if(cityBean.ci_status == 1){
				switch_img = $("<img src='../images/true.svg' data-ci-status='1'>");
			}else{
				switch_img = $("<img src='../images/false.svg' data-ci-status='0'>");
			}
			var switch_div = $("<div class='ci-status-switch' data-ci-id='" + cityBean.ci_id + "'></div>").append(switch_img);
			var switch_td = $("<td></td>").append(switch_div);
//			var cityList_tr = $("<tr></tr>").append([count_td, ci_id_td, ci_name_td, ci_rank_td, edit_td, switch_td]);
			var cityList_tr = $("<tr></tr>").append([count_td, ci_name_td, ci_rank_td, edit_td, switch_td]);
			cityList_tbody.append(cityList_tr);
		});
	});
});

// 城市開關
$(document).on("click", ".ci-status-switch", function(){
	var $this = $(this);
	var ci_id = $this.attr("data-ci-id");
	var ci_status = $this.children("img").attr("data-ci-status");				
	$.get("../city/switch.ajax", {"ci_id": ci_id}, function(data){
		if(ci_status == "1"){
//			alert("將關閉 「" + data + "」。");
			$this.children("img").attr("src", "../images/false.svg");
			$this.children("img").attr("data-ci-status", "0");
		}else{
//			alert("將開啟 「" + data + "」。");
			$this.children("img").attr("src", "../images/true.svg");
			$this.children("img").attr("data-ci-status", "1");
		}
	});
});