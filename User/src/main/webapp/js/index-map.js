function initMap(){
	
	var beanArray = [];
	var markerArray = [];
	
	var centerLocation = {
		lat: 23.583234,
		lng: 117
	};
	
	var map = new google.maps.Map(document.getElementById("id-map"), {
		center: centerLocation,
		zoom: 7,
		scrollwheel: false // 禁用滾輪縮放
	});
	
	var addMarker = function(){

		// 取出陣列資料
		for(var i = 0; i < beanArray.length; i++){

			var name = beanArray[i][1];
			var lat = beanArray[i][2];
			var lng = beanArray[i][3];
			var url = beanArray[i][4];
			var latLng = new google.maps.LatLng(lat, lng);
			
			// 建立標記
			var marker = new google.maps.Marker({
				title: name,
				position: latLng,
				url: url,
				map: map
			});
			
			// 建立標記超連結
			google.maps.event.addListener(marker, 'click', function(){
				window.open(this.url, '_blank');
			});
			
			// 加入標記陣列
			markerArray.push(marker);
		}
	}
	
	$.getJSON("franchisee/open-franchisee-list.ajax", function(data){
		$.each(data, function(index, franchiseeBean){
			
			// 加入加盟店陣列
			var aFranchiseeBean = [franchiseeBean.fr_id, franchiseeBean.fr_name, franchiseeBean.fr_latitude, franchiseeBean.fr_longitude, franchiseeBean.fr_url];
			beanArray.push(aFranchiseeBean);
		});
	});
	
	$.getJSON("clinic/open-clinic-list.ajax", function(data){
		$.each(data, function(index, clinicBean){
			
			// 加入診所陣列
			var aClinicBean = [clinicBean.cl_id, clinicBean.cl_name, clinicBean.cl_latitude, clinicBean.cl_longitude, clinicBean.cl_url];
			beanArray.push(aClinicBean);
		});
		
		// 執行標記
		addMarker();
	});			
};