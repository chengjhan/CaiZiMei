$(document).ready(function(){
	
	$.getJSON("../../admin/country/select.ajax", function(data){
		$.each(data, function(index, country){
			var country_option = $("<option value=" + country.co_id + "></option>").append(country.co_name);
			$("#id-co-id").append(country_option);
		});
	});
});

$("#id-co-id").change(function(){
	var co_id = $("#id-co-id").val();
	$.ajax({
		url: '../../admin/city/select-by-country.ajax?ci_co_id=' + co_id,
		type: 'get',
		dataType: 'json',
		success: function(data){
			var city_select = $("#id-ci-id");
			city_select.empty();
			city_select.append("<option value='0'>請選擇城市</option>");
			var region_select = $("#id-c-r-id");
			region_select.empty();
			region_select.append("<option value='0'>請選擇區域</option>");
			$.each(data, function(index, city){
				var city_option = $("<option value=" + city.ci_id + "></option>").append(city.ci_name);
				city_select.append(city_option);
			});
		}
	});
});

$("#id-ci-id").change(function(){
	var ci_id = $("#id-ci-id").val();
	$.getJSON("../../admin/region/select-by-city.ajax", {"r_ci_id": ci_id}, function(data){
		var region_select = $("#id-c-r-id");
		region_select.empty();
		region_select.append("<option value='0'>請選擇區域</option>");
		$.each(data, function(index, region){
			var region_option = $("<option value=" + region.r_id + "></option>").append(region.r_name);
			region_select.append(region_option);
		});
	});
});