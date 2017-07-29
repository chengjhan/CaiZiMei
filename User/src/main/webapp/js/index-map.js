var map;
var clinicArray = [];
var franchiseeArray = [];
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
	
	$.getJSON("franchisee/open-franchisee-list.ajax", function(data){
		
		$.each(data, function(index, franchiseeBean){
			
			// 加入加盟店陣列
			var aFranchiseeBean = [franchiseeBean.cl_id, franchiseeBean.fr_name, franchiseeBean.fr_latitude, franchiseeBean.fr_longitude, franchiseeBean.fr_url];
			franchiseeArray.push(aFranchiseeBean);
		});
		
		// 取出加盟店陣列
		for(var i = 0; i < franchiseeArray.length; i++){
			
			var name = franchiseeArray[i][1];
			var lat = franchiseeArray[i][2];
			var lng = franchiseeArray[i][3];
			var url = franchiseeArray[i][4];
			
			var franchiseeLatLng = new google.maps.LatLng(lat, lng);
			
			// 建立標記
			var marker = new google.maps.Marker({
				title: name,
				position: franchiseeLatLng,
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
	
	// database 接口
	$.getJSON("clinic/open-clinic-list.ajax", function(data){
		
		$.each(data, function(index, clinicBean){
			
			// 加入診所陣列
			var aClinicBean = [clinicBean.cl_id, clinicBean.cl_name, clinicBean.cl_latitude, clinicBean.cl_longitude, clinicBean.cl_url];
			clinicArray.push(aClinicBean);
		});
	
		// 取出診所陣列
		for(var i = 0; i < clinicArray.length; i++){
			
			var name = clinicArray[i][1];
			var lat = clinicArray[i][2];
			var lng = clinicArray[i][3];
			var url = clinicArray[i][4];
			
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