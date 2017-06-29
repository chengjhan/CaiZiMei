var map;
var clinicArray = [];
var markerArray = [];

function initMap(){
	
	var centerLocation = {
		lat: 23.583234,
		lng: 117
	};
	
	map = new google.maps.Map(document.getElementById("id-map"), {
		center: centerLocation,
		zoom: 7,
		scrollwheel: false // 禁用滾輪縮放
	});
	
	$.getJSON("json/clinic.json", function(data){
		
		$.each(data, function(index, clinic){
			// 加入診所陣列
			var aClinic = [clinic.c_id, clinic.c_name, clinic.c_eng_name, clinic.c_localphone, clinic.c_r_id, clinic.c_address, clinic.c_latitude, clinic.c_longitude, clinic.c_url];
			clinicArray.push(aClinic);
		});
	
		// 取出診所陣列
		for(var i = 0; i < clinicArray.length; i++){
			
			var name = clinicArray[i][1];
			var lat = clinicArray[i][6];
			var lng = clinicArray[i][7];
			var url = clinicArray[i][8];
			
			var clinicLatLng = new google.maps.LatLng(lat, lng);
			
			// 建立標記
			var marker = new google.maps.Marker({
				title: name,
				position: clinicLatLng,
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
	});
};