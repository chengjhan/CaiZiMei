$(document).ready(function(){
	
	$.getJSON("../country/select-all.ajax", function(data){
		$.each(data, function(index, country){
			var country_option = $("<option value=" + country.co_id + "></option>").append(country.co_name);
			$("#id-input-ci-co-id").append(country_option);
		});
	});
});

$("#id-input-ci-co-id").change(function(){
	var s_co_id = $("#id-input-ci-co-id").val();
	$.getJSON("../state/select-by-country.ajax", {"s_co_id": s_co_id}, function(data){
		var state_select = $("#id-input-ci-s-id");
		state_select.empty();
		state_select.append("<option value='0'>請選擇區域</option>");
		$.each(data, function(index, state){
			var state_option = $("<option value=" + state.s_id + "></option>").append(state.s_name);
			$("#id-input-ci-s-id").append(state_option);
		});
	});
});