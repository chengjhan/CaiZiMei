var href = document.location.href; // 取得當前 url
var hrefSplit = href.split("/"); // Array

$(document).ready(function(){
	
	// 取得當前頁面
	var pageName = hrefSplit[hrefSplit.length - 1];
	
	// list active
	var id_li = "#id-li-about-" + pageName;
	$(id_li).addClass("active");
});