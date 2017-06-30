var href = document.location.href; // 取得當前url
var hrefSplit = href.split("/");
var hrefArray = [];
var pageName;

function getPageName(hrefSplit){
    for (var i = 0; i < hrefSplit.length; i++) {
    	hrefArray.push(hrefSplit.slice(i, i + 1));
    }
    pageName = hrefArray[hrefSplit.length - 1];
};

getPageName(hrefSplit);

$(document).ready(function(){
		
	// list mouseover
	$(".li-menu").hover(function(){
		$(this).addClass("li-menu-mouseover");
	}, function(){
		$(this).removeClass("li-menu-mouseover");
	});
	
	// list active
	var id = "#id-li-" + pageName;
	$(id).addClass("li-menu-active");
});