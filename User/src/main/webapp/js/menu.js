var href = document.location.href; // 取得當前 url
var hrefSplit = href.split("/"); // Array

$(document).ready(function(){
	
	// 取得當前頁面
	var pageName = hrefSplit[hrefSplit.length - 1];
		
	// list mouseover
	$(".li-menu-left").hover(function(){
		$(this).addClass("li-menu-mouseover");
	}, function(){
		$(this).removeClass("li-menu-mouseover");
	});
	
	// list active
	var id_li = "#id-li-" + pageName;
	var id_li_a_span = "#id-li-" + pageName + " span";
	$(id_li).addClass("li-menu-active");
	$(id_li_a_span).attr("style", "color:#AA0000");
});