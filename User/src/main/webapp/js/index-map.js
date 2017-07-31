function initMap(){
	
	var baseArray = [];
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
		for(var i = 0; i < baseArray.length; i++){

			var name = baseArray[i][1];
			var lat = baseArray[i][2];
			var lng = baseArray[i][3];
			var url = baseArray[i][4];
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
	
	$.getJSON("base/open-base-list.ajax", function(data){
		$.each(data, function(index, baseBean){
			
			// 加入據點陣列
			var aBaseBean = [baseBean.ba_id, baseBean.ba_name, baseBean.ba_latitude, baseBean.ba_longitude, baseBean.ba_url];
			baseArray.push(aBaseBean);
		});
		
		// 執行標記
		addMarker();
	});			
};