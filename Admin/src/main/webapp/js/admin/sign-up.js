// 帳號重複驗證
$("#ad_username").blur(function(){
	var ad_username = $(this).val();
	var ad_username_span = $("#id-span-ad-username");
	ad_username_span.empty();
	$.get("../admin/username-repeat.ajax", {"ad_username": ad_username}, function(data){
		if(data == "1"){
			ad_username_span.text("已使用");					
		}
	});
});