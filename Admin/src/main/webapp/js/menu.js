var href = document.location.href; // 取得當前 url
var hrefSplitByQuestion = href.split("?", 1); // String
var hrefSplitBySlash = hrefSplitByQuestion[0].split("/"); // Array
var pageName = "";

// 取得當前頁面
function getPageName(hrefSplitBySlash){
	if(hrefSplitBySlash[hrefSplitBySlash.length-1] != ""){
		if(hrefSplitBySlash[hrefSplitBySlash.length-2] == "admin"){
			pageName = hrefSplitBySlash[hrefSplitBySlash.length - 2] + "-" + hrefSplitBySlash[hrefSplitBySlash.length - 1];
		}else{
			pageName = hrefSplitBySlash[hrefSplitBySlash.length - 2] + "-list";
		}
	}
};

$(document).ready(function(){
	
	getPageName(hrefSplitBySlash);
	
	// list active
	var id_li = "#id-li-" + pageName;
	$(id_li).addClass("active");
});