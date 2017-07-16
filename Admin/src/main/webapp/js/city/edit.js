// 區域 select
$("#id-input-ci-co-id").change(function(){
	var stateList_select = $("#id-input-ci-s-id");
	stateList_select.empty();
	stateList_select.append("<option value='0'>請選擇區域</option>");
	var s_co_id = $("#id-input-ci-co-id").val();
	$.getJSON("../state/select-by-country.ajax", {"s_co_id": s_co_id}, function(data){
		$.each(data, function(index, stateBean){
			var stateList_option = $("<option value=" + stateBean.s_id + "></option>").append(stateBean.s_name);
			stateList_select.append(stateList_option);
		});
	});
});