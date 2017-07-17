// 區域 select
$("#id-input-cl-co-id").change(function(){
	var stateList_select = $("#id-input-cl-st-id");
	stateList_select.empty();
	stateList_select.append("<option value='0'>請選擇區域</option>");
	var st_co_id = $("#id-input-cl-co-id").val();
	$.getJSON("../state/select-by-country.ajax", {"st_co_id": st_co_id}, function(data){
		$.each(data, function(index, stateBean){
			var stateList_option = $("<option value=" + stateBean.st_id + "></option>").append(stateBean.st_name);
			stateList_select.append(stateList_option);
		});
	});
});

// 城市 select
$("#id-input-cl-st-id").change(function(){
	var cityList_select = $("#id-input-cl-ci-id");
	cityList_select.empty();
	cityList_select.append("<option value='0'>請選擇城市</option>");
	var ci_st_id = $("#id-input-cl-st-id").val();
	$.getJSON("../city/select-by-state.ajax", {"ci_st_id": ci_st_id}, function(data){
		$.each(data, function(index, cityBean){
			var cityList_option = $("<option value=" + cityBean.ci_id + "></option>").append(cityBean.ci_name);
			cityList_select.append(cityList_option);
		});
	});
});