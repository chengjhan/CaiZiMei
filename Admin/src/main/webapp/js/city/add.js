// 區域 select
$("#id-input-ci-co-id").change(function(){
	var stateList_select = $("#id-input-ci-st-id");
	stateList_select.empty();
	stateList_select.append("<option value='0'>請選擇區域</option>");
	var st_co_id = $("#id-input-ci-co-id").val();
	$.getJSON("../state/select-by-country.ajax", {"st_co_id": st_co_id}, function(data){
		$.each(data, function(index, stateBean){
			var stateList_option = $("<option value=" + stateBean.st_id + "></option>").append(stateBean.st_name);
			stateList_select.append(stateList_option);
		});
	});
});