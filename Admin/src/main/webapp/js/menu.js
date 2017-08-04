var href = document.location.href; // 取得當前 url
var hrefSplit = href.split("/");
var hrefArray = [];
var pageName;

// 取得當前頁面
function getPageName(hrefSplit){
	for(var i = 0; i < hrefSplit.length; i++){
		hrefArray.push(hrefSplit.slice(i, i + 1));
	}
	if(hrefArray[hrefSplit.length - 2] == "admin"){
		pageName = hrefArray[hrefSplit.length - 2] + "-" + hrefArray[hrefSplit.length - 1];
	}else{
		pageName = hrefArray[hrefSplit.length - 2] + "-list"
	}
};

getPageName(hrefSplit);

$(document).ready(function(){
	
	// list active
	var id_li = "#id-li-" + pageName;
	$(id_li).addClass("active");
});