var href = document.location.href; // 取得當前 url
var hrefSplit = href.split("/"); // Array

$(document).ready(function(){
	
	// 取得當前資料夾名
	var directoryName = hrefSplit[hrefSplit.length - 2];

	// list mouseover
	$(".ul-menu-left li").hover(function(){
		$(this).addClass("mouseover-menu");
	}, function(){
		$(this).removeClass("mouseover-menu");
	});
	
	// list active
	var id_li = "#id-li-" + directoryName;
	$(id_li).addClass("active-menu");
});