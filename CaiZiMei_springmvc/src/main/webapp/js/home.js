var map;
var aClinic = ["曼星整形醫美珍所", "Twinkle Clinic", "02-27079333", "台北市大安區信義路四段58號3F-2", 25.033073, 121.545438, "http://twinkle-clinic.tw"];

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
	
	var clinicLatLng = new google.maps.LatLng(aClinic[4], aClinic[5]);
	
	// 建立標記
	var marker = new google.maps.Marker({
		title: aClinic[0],
		position: clinicLatLng,
		url: aClinic[6],
		map: map
	});
	
	// 標記超連結
	google.maps.event.addListener(marker, 'click', function(){
	    window.location.href = this.url;
	});
	
};