// 分頁
$(document).ready(function(){
	var href = document.location.href; // 取得當前 url
	var query;
	var parameters;
	var currentPage;
	if(href.indexOf("?") == -1){
		query = "";
	}else{
		query = href.split("?")[1]; // String
		if(query.indexOf("&") == -1){
			parameters = query; // String
			currentPage = parameters.split("=")[1]; // String
		}else{
			parameters = query.split("&"); // Array
			currentPage = parameters[parameters.length - 1].split("=")[1]; // String
		}
	}
	var id_li_page = "#id-li-page-" + currentPage;
	$(id_li_page).addClass("active").children("a").removeAttr("href");
});