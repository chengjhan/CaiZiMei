var href = document.location.href; // 取得當前 url
var hrefSplit = href.split("/"); // Array

$(document).ready(function(){
	
	// 取得當前資料夾名
	var directoryName = hrefSplit[hrefSplit.length - 2];

	// list mouseover
	$(".menu li").hover(function(){
		$(this).addClass("menu-mouseover");
	}, function(){
		$(this).removeClass("menu-mouseover");
	});
	
	// list active
	var id_li = "#id-li-" + directoryName;
	$(id_li).addClass("menu-active");
});