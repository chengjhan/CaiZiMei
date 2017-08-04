var href = document.location.href; // 取得當前 url
var hrefSplit = href.split("/");
var hrefArray = [];
var pageName;

// 取得當前頁面
function getPageName(hrefSplit){
	for(var i = 0; i < hrefSplit.length; i++){
		hrefArray.push(hrefSplit.slice(i, i + 1));
	}
	pageName = hrefArray[hrefSplit.length - 1];
};

getPageName(hrefSplit);

$(document).ready(function(){
		
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