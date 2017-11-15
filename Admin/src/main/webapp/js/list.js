// admin //////////
// switch
$(document).on("click", ".ad-status-switch", function(){
	var $this = $(this);
	var ad_id = $this.attr("data-ad-id");
	var ad_status = $this.children("img").attr("data-ad-status");				
	$.getJSON("../admin/switch.ajax", {"ad_id": ad_id}, function(data){
		if(ad_status == "1"){
//			alert("將關閉 「" + data.ad_username + "」。");
			$this.children("img").attr("src", "../images/icon_false.svg");
			$this.children("img").attr("data-ad-status", "0");
			$this.parent("td").prev().text(data.ad_status_time);
		}else{
//			alert("將開啟 「" + data.ad_username + "」。");
			$this.children("img").attr("src", "../images/icon_true.svg");
			$this.children("img").attr("data-ad-status", "1");
			$this.parent("td").prev().text(data.ad_status_time);
		}
	});
});

// area-country //////////
// switch
$(document).on("click", ".co-status-switch", function(){
	var $this = $(this);
	var co_id = $this.attr("data-co-id");
	var co_status = $this.children("img").attr("data-co-status");				
	$.get("../area-country/switch.ajax", {"co_id": co_id}, function(data){
		if(co_status == "1"){
//			alert("將關閉 「" + data + "」。");
			$this.children("img").attr("src", "../images/icon_false.svg");
			$this.children("img").attr("data-co-status", "0");
		}else{
//			alert("將開啟 「" + data + "」。");
			$this.children("img").attr("src", "../images/icon_true.svg");
			$this.children("img").attr("data-co-status", "1");
		}
	});
});

// area-state //////////
// table
$("#st_CountryBean").change(function(){
	var stateList_tbody = $("table tbody");
	stateList_tbody.empty();
	var st_co_id = $("#st_CountryBean").val();
	$.getJSON("../area-state/choice-country-state-list.ajax", {"st_co_id": st_co_id}, function(data){
		$.each(data, function(index, stateBean){
			index = index + 1;
			var count_td = $("<td></td>").append(index);
			var st_name_td = $("<td></td>").append(stateBean.st_name);
			var st_rank_td = $("<td></td>").append(stateBean.st_rank);
			var edit_img = $("<img src='../images/icon_edit.svg'>");
			var edit_a = $("<a href='../area-state/edit?st_id=" + stateBean.st_id + "' title='編輯'></a>").append(edit_img);
			var edit_div = $("<div class='edit-button'></div>").append(edit_a);
			var edit_td = $("<td></td>").append(edit_div);
			var switch_img;
			if(stateBean.st_status == 1){
				switch_img = $("<img src='../images/icon_true.svg' data-st-status='1'>");
			}else{
				switch_img = $("<img src='../images/icon_false.svg' data-st-status='0'>");
			}
			var switch_div = $("<div class='st-status-switch' data-st-id='" + stateBean.st_id + "' title='切換'></div>").append(switch_img);
			var switch_td = $("<td></td>").append(switch_div);
			var stateList_tr = $("<tr></tr>").append([count_td, st_name_td, st_rank_td, edit_td, switch_td]);
			stateList_tbody.append(stateList_tr);
		});
	});
});

// switch
$(document).on("click", ".st-status-switch", function(){
	var $this = $(this);
	var st_id = $this.attr("data-st-id");
	var st_status = $this.children("img").attr("data-st-status");				
	$.get("../area-state/switch.ajax", {"st_id": st_id}, function(data){
		if(st_status == "1"){
//			alert("將關閉 「" + data + "」。");
			$this.children("img").attr("src", "../images/icon_false.svg");
			$this.children("img").attr("data-st-status", "0");
		}else{
//			alert("將開啟 「" + data + "」。");
			$this.children("img").attr("src", "../images/icon_true.svg");
			$this.children("img").attr("data-st-status", "1");
		}
	});
});

// area-city //////////
var country_select = $("#ci_CountryBean");
var state_select = $("#ci_StateBean");

// select
country_select.change(function(){
	state_select.empty();
	state_select.append("<option value='0'>請選擇區域</option>");
	var cityList_tbody = $("table tbody");
	cityList_tbody.empty();
	var st_co_id = country_select.val();
	$.getJSON("../area-state/choice-country-state-list.ajax", {"st_co_id": st_co_id}, function(data){
		$.each(data, function(index, stateBean){
			var state_option = $("<option value=" + stateBean.st_id + "></option>").append(stateBean.st_name);
			state_select.append(state_option);
		});
	});
});

// table
state_select.change(function(){
	var cityList_tbody = $("table tbody");
	cityList_tbody.empty();
	var ci_st_id = state_select.val();
	$.getJSON("../area-city/choice-state-city-list.ajax", {"ci_st_id": ci_st_id}, function(data){
		$.each(data, function(index, cityBean){
			index = index + 1;
			var count_td = $("<td></td>").append(index);
			var ci_name_td = $("<td></td>").append(cityBean.ci_name);
			var ci_rank_td = $("<td></td>").append(cityBean.ci_rank);
			var edit_img = $("<img src='../images/icon_edit.svg'>");
			var edit_a = $("<a href='../area-city/edit?ci_id=" + cityBean.ci_id + "' title='編輯'></a>").append(edit_img);
			var edit_div = $("<div class='edit-button'></div>").append(edit_a);
			var edit_td = $("<td></td>").append(edit_div);
			var switch_img;
			if(cityBean.ci_status == 1){
				switch_img = $("<img src='../images/icon_true.svg' data-ci-status='1'>");
			}else{
				switch_img = $("<img src='../images/icon_false.svg' data-ci-status='0'>");
			}
			var switch_div = $("<div class='ci-status-switch' data-ci-id='" + cityBean.ci_id + "' title='切換'></div>").append(switch_img);
			var switch_td = $("<td></td>").append(switch_div);
			var cityList_tr = $("<tr></tr>").append([count_td, ci_name_td, ci_rank_td, edit_td, switch_td]);
			cityList_tbody.append(cityList_tr);
		});
	});
});

// switch
$(document).on("click", ".ci-status-switch", function(){
	var $this = $(this);
	var ci_id = $this.attr("data-ci-id");
	var ci_status = $this.children("img").attr("data-ci-status");				
	$.get("../area-city/switch.ajax", {"ci_id": ci_id}, function(data){
		if(ci_status == "1"){
//			alert("將關閉 「" + data + "」。");
			$this.children("img").attr("src", "../images/icon_false.svg");
			$this.children("img").attr("data-ci-status", "0");
		}else{
//			alert("將開啟 「" + data + "」。");
			$this.children("img").attr("src", "../images/icon_true.svg");
			$this.children("img").attr("data-ci-status", "1");
		}
	});
});

// base //////////
// switch
$(document).on("click", ".ba-status-switch", function(){
	var $this = $(this);
	var ba_id = $this.attr("data-ba-id");
	var ba_status = $this.children("img").attr("data-ba-status");				
	$.get("../base/switch.ajax", {"ba_id": ba_id}, function(data){
		if(ba_status == "1"){
//			alert("將關閉 「" + data + "」。");
			$this.children("img").attr("src", "../images/icon_false.svg");
			$this.children("img").attr("data-ba-status", "0");
		}else{
//			alert("將開啟 「" + data + "」。");
			$this.children("img").attr("src", "../images/icon_true.svg");
			$this.children("img").attr("data-ba-status", "1");
		}
	});
});

// image //////////
// zoom in
$(".image-list-table>tbody>tr>td>img").on("click", function(){
	$(".image-zoom").removeAttr("style");
	document.body.style.overflow = 'hidden';
	$(".image-zoom img").attr("src", $(this).attr("src"));
});

$(".image-zoom").on("click", function(){
	$(".image-zoom").attr("style", "display:none");
	document.body.style.overflow = 'auto';
});

// switch
$(document).on("click", ".im-status-switch", function(){
	var $this = $(this);
	var im_id = $this.attr("data-im-id");
	var im_status = $this.children("img").attr("data-im-status");				
	$.get("../image/switch.ajax", {"im_id": im_id}, function(data){
		if(im_status == "1"){
//			alert("將關閉 「" + data + "」。");
			$this.children("img").attr("src", "../images/icon_false.svg");
			$this.children("img").attr("data-im-status", "0");
		}else{
//			alert("將開啟 「" + data + "」。");
			$this.children("img").attr("src", "../images/icon_true.svg");
			$this.children("img").attr("data-im-status", "1");
		}
	});
});

// video //////////
// switch
$(document).on("click", ".vi-status-switch img[data-vi-status='0']", function(){
	var $this = $(this);
	var vi_id = $this.parent("div").attr("data-vi-id");
	var vi_status_1 = $(".vi-status-switch img[data-vi-status='1']");				
	$.get("../video/switch.ajax", {"vi_id": vi_id}, function(data){
		vi_status_1.attr("src", "../images/icon_false.svg");
		vi_status_1.attr("data-vi-status", "0");
		vi_status_1.attr("title", "開啟");
		$this.attr("src", "../images/icon_true.svg");
		$this.attr("data-vi-status", "1");
		$this.removeAttr("title");
	});
});

// html //////////
// switch
$(document).on("click", ".ht-status-switch img[data-ht-status='0']", function(){
	var $this = $(this);
	var ht_id = $this.parent("div").attr("data-ht-id");
	var ht_status_1 = $(".ht-status-switch img[data-ht-status='1']");
	$.get("../html/switch.ajax", {"ht_id": ht_id}, function(data){
		ht_status_1.attr("src", "../images/icon_false.svg");
		ht_status_1.attr("data-ht-status", "0");
		ht_status_1.attr("title", "開啟");
		$this.attr("src", "../images/icon_true.svg");
		$this.attr("data-ht-status", "1");
		$this.removeAttr("title");
	});
});