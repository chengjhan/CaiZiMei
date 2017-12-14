// 分頁
$(document).ready(function(){
	var href = document.location.href; // 取得當前 url
	var query = href.split("?")[1]; // String
	var parameters = query.split("&"); // Array
	var currentPage = parameters[parameters.length - 1].split("=")[1]; // String
	var id_li_page = "#id-li-page-" + currentPage;
	$(id_li_page).addClass("active").children("a").removeAttr("href");
});