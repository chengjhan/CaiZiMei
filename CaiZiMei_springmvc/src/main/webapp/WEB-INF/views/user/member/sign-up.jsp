<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<c:url value="/" var="root" />
	<form action="<c:url value='/user/member/sign-up.do' />" method="post">
		<div>
			<label for="id-m-username">信箱</label>
			<input type="text" id="id-m-username" name="m_username">
			<span id="id-m-username-span" style="color:red"></span>
		</div>
		<div>
			<label for="id-m-password">密碼</label>
			<input type="password" id="id-m-password" name="m_password">
		</div>
		<div>
			<label for="id-m-password-again">確認密碼</label>
			<input type="password" id="id-m-password-again">
		</div>
		<div>
			<label for="id-m-lastname">姓氏</label>
			<input type="text" id="id-m-lastname" name="m_lastname">
		</div>
		<div>
			<label for="id-m-firstname">名字</label>
			<input type="text" id="id-m-firstname" name="m_firstname">
		</div>
		<div>
			<label for="id-m-birth">生日</label>
			<div id="id-m-birth">
				<select id="id-m-birth-year" name="m_birth_year">
					<option></option>
				</select>年
				<select id="id-m-birth-month" name="m_birth_month">
					<option></option>
				</select>月
				<select id="id-m-birth-date" name="m_birth_date">
					<option></option>
				</select>日
			</div>
		</div>
		<div>
			<label for="id-m-sex">性別</label>
			<select id="id-m-sex" name="m_sex">
				<option value="0">--</option>
				<option value="1">男</option>
				<option value="2">女</option>
			</select>
		</div>
		<div>
			<label for="id-m-height">身高</label>
			<input type="text" id="id-m-height" name="m_height">
		</div>
		<div>
			<label for="id-m-weight">體重</label>
			<input type="text" id="id-m-weight" name="m_weight">
		</div>
		<div>
			<label for="id-m-localphone">電話</label>
			<input type="text" id="id-m-localphone" name="m_localphone_front">
			<input type="text" id="id-m-localphone" name="m_localphone_back">
		</div>
		<div>
			<label for="id-m-mobilephone">手機</label>
			<input type="text" id="id-m-mobilephone" name="m_mobilephone_front">
			<input type="text" id="id-m-mobilephone" name="m_mobilephone_back">
		</div>
		<div>
			<label for="id-m-zipcode">郵遞區號</label>
			<input type="text" id="id-m-zipcode" name="m_zipcode" readonly>
		</div>
		<div>
			<label for="id-m-country">國家</label>
			<select id="id-m-country" name="m_country" style="width:150px">
				<option value="0">請選擇國家</option>
			</select>
		</div>
		<div>
			<label for="id-m-city">城市</label>
			<select id="id-m-city" name="m_city" style="width:150px">
				<option value="0">請選擇城市</option>
			</select>
		</div>
		<div>
			<label for="id-m-region">區域</label>
			<select id="id-m-region" name="m_region" style="width:150px">
				<option value="0">請選擇區域</option>
			</select>
		</div>
		<div>
			<label for="id-m-address">地址</label>
			<input type="text" id="id-m-address" name="m_address">
		</div>
		<div>
			<input type="submit" id="id-submit" value="註冊">
		</div>
	</form>
	<script>
		// 帳號驗證
		$("#id-m-username").blur(function(){
			var m_username = $("#id-m-username").val();
			var m_username_span = $("#id-m-username-span");
			m_username_span.empty();
			$.get("${root}user/member/select-by-username.ajax", { "m_username": m_username }, function(data){
				if(data == "1"){
					m_username_span.text("已使用");					
				}
			});
		});
		
		// 日期選單
		var theYear;
		var theMonth;
		var maxDate;

		window.onload = function() {
			var year_select = document.getElementById("id-m-birth-year");
			var today = new Date();
			var currentYear = today.getFullYear();
			for (i = 0; i <= 100; i++) {
				var opt = window.document.createElement("option");
				opt.value = currentYear - i;
				opt.innerHTML = currentYear - i;
				year_select.appendChild(opt);
			}
			var month_select = document.getElementById("id-m-birth-month");
			for (i = 1; i <= 12; i++) {
				var opt = window.document.createElement("option");
				var optName = document.createTextNode(i);
				opt.appendChild(optName);
				month_select.appendChild(opt);
			}
			year_select.onchange = changeYear;
			month_select.onchange = changeMonth;
		};

		function changeYear() {
			theYear = document.getElementById("id-m-birth-year").value;
			theDate = new Date(theYear, theMonth, 0);
			maxDate = theDate.getDate();
			changeDate();
		}

		function changeMonth() {
			theMonth = document.getElementById("id-m-birth-month").value;
			var theDate = new Date(theYear, theMonth, 0);
			maxDate = theDate.getDate();
			changeDate();
		}

		function changeDate() {
			document.getElementById("id-m-birth-date").innerHTML = "";
			for (i = 1; i <= maxDate; i++) {
				var opt = window.document.createElement("option");
				var optName = document.createTextNode(i);
				opt.appendChild(optName);
				document.getElementById("id-m-birth-date").appendChild(opt);
			}
		}
		
		// 地區選單
		$(document).ready(function(){
			$.getJSON("${root}admin/country/select.ajax", function(data){
				$.each(data, function(index, country){
					var country_option = $("<option value=" + country.co_id + "></option>").append(country.co_name);
					$("#id-m-country").append(country_option);
				});
			});
		});
	
		$("#id-m-country").change(function(){
			var co_id = $("#id-m-country").val();
			$.ajax({
				url: '${root}admin/city/select-by-country.ajax?ci_co_id=' + co_id,
				type: 'get',
				dataType: 'json',
				success: function(data){
					var city_select = $("#id-m-city");
					city_select.empty();
					city_select.append("<option value='0'>請選擇城市</option>");
					$.each(data, function(index, city){
						var city_option = $("<option value=" + city.ci_id + "></option>").append(city.ci_name);
						city_select.append(city_option);
					});
				}
			});
		});
		
		$("#id-m-city").change(function(){
			var ci_id = $("#id-m-city").val();
			$.getJSON("${root}admin/region/select-by-city.ajax", {"r_ci_id": ci_id}, function(data){
				var region_select = $("#id-m-region");
				region_select.empty();
				region_select.append("<option value='0'>請選擇區域</option>");
				$.each(data, function(index, region){
					var region_option = $("<option value=" + region.r_id + "></option>").append(region.r_name);
					region_select.append(region_option);
				});
			});
		});
		
		$("#id-m-region").change(function(){
			var r_id = $("#id-m-region").val();
			$.get("${root}admin/region/select-by-id.ajax", {"r_id": r_id}, function(data){
				$("#id-m-zipcode").val(data);
			});
		});
	</script>
</body>
</html>