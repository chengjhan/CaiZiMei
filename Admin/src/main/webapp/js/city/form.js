var country_select = $("#id-input-ci-co-id");
var state_select = $("#id-input-ci-st-id");

// 區域 select
country_select.change(function(){
	state_select.empty();
	state_select.append("<option value='0'>請選擇區域</option>");
	var st_co_id = country_select.val();
	$.getJSON("../state/choice-country-state-list.ajax", {"st_co_id": st_co_id}, function(data){
		$.each(data, function(index, stateBean){
			var stateList_option = $("<option value=" + stateBean.st_id + "></option>").append(stateBean.st_name);
			state_select.append(stateList_option);
		});
	});
});