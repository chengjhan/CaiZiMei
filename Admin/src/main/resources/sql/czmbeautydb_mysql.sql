-- MySQL

drop database if exists czmbeautydb;

create database if not exists czmbeautydb;

use czmbeautydb;

-- CREATE
create table admin (
	ad_id					int auto_increment not null,
	ad_username				varchar(50) not null,
	ad_password				varchar(50) not null,
	ad_salt					varchar(50) not null,
	ad_lastname				nvarchar(20),
	ad_firstname			nvarchar(20),
	ad_email				varchar(50) not null,
	ad_signup_time			datetime not null,
	ad_signin_number		int,
	ad_signin_ip			varchar(20),
	ad_signin_time			datetime,
	ad_update_info_time		datetime not null,
	ad_update_pwd_time		datetime not null,
	ad_status				tinyint not null,
	ad_status_time			datetime not null,
	primary key (ad_id)
);

create table admin_log (
	al_id					int auto_increment not null,
	al_insert_time			timestamp default current_timestamp not null,
	al_ad_id				int not null,
	al_operation			nvarchar(50),
	al_ip					varchar(20),
	primary key (al_id),
	foreign key (al_ad_id) references admin (ad_id)
);

create table country (
	co_id					int auto_increment not null,
	co_iso					varchar(2),
	co_name					nvarchar(50) not null,
	co_phonecode			varchar(5),
	co_rank					tinyint,
	co_status				tinyint,
	primary key (co_id)
);

create table state (
	st_id					int auto_increment not null,
	st_co_id				int not null,
	st_name					nvarchar(50) not null,
	st_rank					tinyint,
	st_status				tinyint,
	primary key (st_id),
	foreign key (st_co_id) references country (co_id)
);

create table city (
	ci_id					int auto_increment not null,
	ci_co_id				int not null,
	ci_st_id				int not null,
	ci_name					nvarchar(50) not null,
	ci_rank					tinyint,
	ci_status				tinyint,
	primary key (ci_id),
	foreign key (ci_co_id) references country (co_id),
	foreign key (ci_st_id) references state (st_id)
);

create table category (
	ca_id					int auto_increment not null,
	ca_name					nvarchar(20) not null,
	ca_directory			varchar(50) not null,
	primary key (ca_id)
);

create table base (
	ba_id					int auto_increment not null,
	ba_ca_id				int not null,
	ba_name					nvarchar(20) not null,
	ba_eng_name				varchar(50),
	ba_tel_code				varchar(5),
	ba_tel					varchar(20),
	ba_co_id				int not null,
	ba_st_id				int not null,
	ba_ci_id				int not null,
	ba_address				nvarchar(50) not null,
	ba_latitude				decimal(10,6),
	ba_longitude			decimal(10,6),
	ba_url					varchar(100),
	ba_insert_time			datetime not null,
	ba_update_time			datetime,
	ba_status				tinyint,
	ba_status_time			datetime,
	primary key (ba_id),
	foreign key (ba_ca_id) references category (ca_id),
	foreign key (ba_co_id) references country (co_id),
	foreign key (ba_st_id) references state (st_id),
	foreign key (ba_ci_id) references city (ci_id)
);

create table image (
	im_id					int auto_increment not null,
	im_ca_id				int not null,
	im_name					nvarchar(20) not null,
	im_path					varchar(500) not null,
	im_filename				varchar(50) not null,
	im_url					varchar(100),
	im_rank					tinyint not null,
	im_status				tinyint not null,
	im_update_time			datetime not null,
	primary key (im_id),
	foreign key (im_ca_id) references category (ca_id)
);

create table video (
	vi_id					int auto_increment not null,
	vi_ca_id				int not null,
	vi_name					nvarchar(50),
	vi_tag					varchar(500) not null,
	vi_rank					tinyint not null,
	vi_status				tinyint not null,
	vi_update_time			datetime not null,
	primary key (vi_id),
	foreign key (vi_ca_id) references category (ca_id)
);

create table admin_view (
	av_id					int auto_increment not null,
	av_name					nvarchar(20) not null,
	av_page_name			varchar(50) not null,
	primary key (av_id)
);

create table admin_action (
	aa_id					int auto_increment not null,
	aa_name					nvarchar(20) not null,
	aa_page_name			varchar(50) not null,
	primary key (aa_id)
);

-- ALTER
alter table state auto_increment = 11;
alter table city auto_increment = 101;

-- INSERT
-- admin
insert into admin (ad_id, ad_username, ad_password, ad_salt, ad_lastname, ad_firstname, ad_email, ad_signup_time, ad_signin_number, ad_signin_ip, ad_signin_time, ad_update_info_time, ad_update_pwd_time, ad_status, ad_status_time) values (100, 'admin', '538f10610b9eda06f83d7d73332d2ed0', '37f3d447-fad6-4ec0-b5fb-6a8f05b60c77', 'czmbeauty', 'admin', 'admin@czmbeauty.com', now(), 0, null, null, now(), now(), 1, now());

-- country
insert into country (co_iso, co_name, co_phonecode, co_rank, co_status) values ('TW', '台灣', '886', 1, 1);
insert into country (co_iso, co_name, co_phonecode, co_rank, co_status) values ('CN', '中國', '86', 2, 1);

-- state
insert into state (st_co_id, st_name, st_rank, st_status) values (1, '台灣', 1, 1);
insert into state (st_co_id, st_name, st_rank, st_status) values (1, '澎湖', 2, 1);
insert into state (st_co_id, st_name, st_rank, st_status) values (1, '金門', 3, 1);
insert into state (st_co_id, st_name, st_rank, st_status) values (1, '馬祖', 4, 1);
insert into state (st_co_id, st_name, st_rank, st_status) values (2, '福建省', 1, 1);
insert into state (st_co_id, st_name, st_rank, st_status) values (2, '廣東省', 2, 1);
insert into state (st_co_id, st_name, st_rank, st_status) values (2, '甘肅省', 3, 1);

-- city
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (1, 11, '基隆市', 1, 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (1, 11, '臺北市', 2, 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (1, 11, '新北市', 3, 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (1, 11, '桃園市', 4, 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (1, 11, '新竹市', 5, 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (1, 11, '新竹縣', 6, 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (1, 11, '苗栗縣', 7, 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (1, 11, '臺中市', 8, 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (1, 11, '彰化縣', 9, 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (1, 11, '南投縣', 10, 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (1, 11, '雲林縣', 11, 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (1, 11, '嘉義市', 12, 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (1, 11, '嘉義縣', 13, 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (1, 11, '臺南市', 14, 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (1, 11, '高雄市', 15, 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (1, 11, '屏東縣', 16, 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (1, 11, '臺東縣', 17, 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (1, 11, '花蓮縣', 18, 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (1, 11, '宜蘭縣', 19, 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (1, 12, '澎湖縣', 1, 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (1, 13, '金門縣', 1, 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (1, 14, '連江縣', 1, 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank, ci_status) values (2, 17, '天水市', 1, 1);

-- category
insert into category (ca_name, ca_directory) values ('辦事處', 'office');
insert into category (ca_name, ca_directory) values ('加盟店', 'franchisee');
insert into category (ca_name, ca_directory) values ('診所', 'clinic');
insert into category (ca_name, ca_directory) values ('主輪播圖片', 'slider-main');
insert into category (ca_name, ca_directory) values ('相關影音', 'video-related');
insert into category (ca_name, ca_directory) values ('加盟店資訊輪播圖片', 'slider-franchisee');
insert into category (ca_name, ca_directory) values ('近期活動輪播圖片', 'slider-recent');
insert into category (ca_name, ca_directory) values ('優惠活動輪播圖片', 'slider-sale');
insert into category (ca_name, ca_directory) values ('醫療新知輪播圖片', 'slider-knowledge');
insert into category (ca_name, ca_directory) values ('醫療團隊輪播圖片', 'slider-team');

-- base
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (1, '采姿美台北辦事處', 'CaiZiMei', '02', '27079333', 1, 11, 102, '大安區信義路四段58號3F-2', 25.033072, 121.545437, 'http://localhost:8080/User/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (2, '甘肅天水采姿美加盟旗艦館', 'CaiZiMei', null, null, 2, 17, 123, '秦城區陽光麗景灣商鋪19號', 34.576471, 105.721828, 'http://localhost:8080/User/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '曼星整形醫美診所', 'Twinkle Clinic', '02', '27079333', 1, 11, 102, '大安區信義路四段58號3F-2', 25.033072, 121.545437, 'http://twinkle-clinic.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '晶鑽時尚診所-台北', 'Diamond Cosmetic', '02', '27665066', 1, 11, 102, '信義區忠孝東路4段563號3F', 25.041589, 121.564050, 'http://diamondcosmetic.com.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '晶鑽時尚診所-台中', 'Diamond Cosmetic', '02', '23195800', 1, 11, 108, '西區公益路130號B1', 24.151141, 120.660487, 'http://diamondcosmetic.com.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '晶鑽時尚診所-高雄', 'Diamond Cosmetic', '02', '2367811', 1, 11, 115, '新興區中正三路156號', 22.631456, 120.304368, 'http://diamondcosmetic.com.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '法泊時尚診所-台北', 'Fabulous Clinic', '02', '27199010', 1, 11, 102, '松山區復興北路311號3樓', 25.058785, 121.544431, 'http://www.fabulous-clinic.com', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '法泊時尚診所-高雄', 'Fabulous Clinic', '02', '5585838', 1, 11, 115, '左營區博愛二路656號', 22.669675, 120.303546, 'http://www.fabulous-clinic.com', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '喬雅時尚診所', 'Joya Clinical Beauty', '02', '27715833', 1, 11, 102, '大安區忠孝東路四段147巷1號10樓', 25.041839, 121.544056, 'http://www.joya-beauty.com.tw', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '好萊塢診所-台北仁愛', 'Hollywood', '02', '27717771', 1, 11, 102, '大安區仁愛路四段105巷5號', 25.038633, 121.550189, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '好萊塢診所-台北忠孝', 'Hollywood', '02', '27315900', 1, 11, 102, '大安區忠孝東路四段94號2樓之1', 25.041701, 121.545949, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '好萊塢診所-台北復興', 'Hollywood', '02', '27519600', 1, 11, 102, '大安區忠孝東路三段283號2樓', 25.042171, 121.542878, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '好萊塢診所-台北南西', 'Hollywood', '02', '25622701', 1, 11, 102, '中山區南京東路一段24號3樓', 25.052305, 121.523649, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '好萊塢診所-台北光復', 'Hollywood', '02', '27786800', 1, 11, 102, '大安區光復南路260巷23號3樓', 25.040441, 121.556414, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '好萊塢診所-台北南港', 'Hollywood', '02', '27828200', 1, 11, 102, '南港區三重路26號1樓', 25.056361, 121.614177, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '好萊塢診所-桃園', 'Hollywood', '03', '3263123', 1, 11, 104, '桃園區新埔六街38號1樓', 25.014528, 121.302752, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '好萊塢診所-台中', 'Hollywood', '04', '22595825', 1, 11, 108, '西屯區市政北一路1號2樓', 24.157116, 120.646442, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '璀璨精品牙醫診所', 'Dazzling Dental Clinic', '02', '28721239', 1, 11, 102, '士林區天玉街41號', 25.121355, 121.529410, 'http://www.dazzlingdental.com.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '何彬彬牙醫診所', 'Ho Bing Bing Dental Clinic', '07', '2270748', 1, 11, 115, '新興區民生一路56號', 22.628011, 120.310796, 'http://1637.tw/07-2270748/', now(), now(), 1, now());

-- image
insert into image (im_ca_id, im_name, im_path, im_filename, im_url, im_rank, im_status, im_update_time) values (4, '曼星整形醫美診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_twinkle_clinic_1000x380.jpg', 'http://twinkle-clinic.tw', 1, 1, now());
insert into image (im_ca_id, im_name, im_path, im_filename, im_url, im_rank, im_status, im_update_time) values (4, '晶鑽時尚診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_diamondcosmetic_1000x380.jpg', 'http://diamondcosmetic.com.tw/', 2, 1, now());
insert into image (im_ca_id, im_name, im_path, im_filename, im_url, im_rank, im_status, im_update_time) values (4, '法泊時尚診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_fabulous_clinic_1000x380.png', 'http://www.fabulous-clinic.com', 3, 1, now());
insert into image (im_ca_id, im_name, im_path, im_filename, im_url, im_rank, im_status, im_update_time) values (4, '喬雅時尚診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_joya_beauty_1000x380.png', 'http://www.joya-beauty.com.tw', 4, 1, now());
insert into image (im_ca_id, im_name, im_path, im_filename, im_url, im_rank, im_status, im_update_time) values (4, '好萊塢診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_hollywood_1000x380.jpg', 'http://hollywood.tw/', 5, 1, now());
insert into image (im_ca_id, im_name, im_path, im_filename, im_url, im_rank, im_status, im_update_time) values (4, '何彬彬牙醫診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_1637_1000x380.jpg', 'http://1637.tw/07-2270748/', 6, 1, now());

-- video
insert into video (vi_ca_id, vi_name, vi_tag, vi_rank, vi_status, vi_update_time) values (5, '', '<iframe width="560" height="315" src="https://www.youtube.com/embed/C589vlQLQEA" frameborder="0" allowfullscreen></iframe>', 1, 1, now());
insert into video (vi_ca_id, vi_name, vi_tag, vi_rank, vi_status, vi_update_time) values (5, '', '<iframe width="560" height="315" src="https://www.youtube.com/embed/zXvg00_5OpM" frameborder="0" allowfullscreen></iframe>', 2, 0, now());
insert into video (vi_ca_id, vi_name, vi_tag, vi_rank, vi_status, vi_update_time) values (5, '', '<iframe width="560" height="315" src="https://www.youtube.com/embed/R82z1DfsKWk" frameborder="0" allowfullscreen></iframe>', 3, 0, now());

-- admin_view
insert into admin_view (av_name, av_page_name) values ('首頁', 'index');
insert into admin_view (av_name, av_page_name) values ('變更密碼', 'admin/change-password');
insert into admin_view (av_name, av_page_name) values ('編輯個人資訊', 'admin/edit');
insert into admin_view (av_name, av_page_name) values ('管理員一覽', 'admin/list');
insert into admin_view (av_name, av_page_name) values ('個人資訊', 'admin/profile');
insert into admin_view (av_name, av_page_name) values ('註冊', 'admin/sign-up');
insert into admin_view (av_name, av_page_name) values ('新增城市', 'city/add');
insert into admin_view (av_name, av_page_name) values ('編輯城市資訊', 'city/edit');
insert into admin_view (av_name, av_page_name) values ('城市一覽', 'city/list');
insert into admin_view (av_name, av_page_name) values ('新增診所', 'clinic/add');
insert into admin_view (av_name, av_page_name) values ('編輯診所資訊', 'clinic/edit');
insert into admin_view (av_name, av_page_name) values ('診所一覽', 'clinic/list');
insert into admin_view (av_name, av_page_name) values ('新增國家', 'country/add');
insert into admin_view (av_name, av_page_name) values ('編輯國家資訊', 'country/edit');
insert into admin_view (av_name, av_page_name) values ('國家一覽', 'country/list');
insert into admin_view (av_name, av_page_name) values ('找不到網頁', 'error/page-not-found');
insert into admin_view (av_name, av_page_name) values ('新增加盟店', 'franchisee/add');
insert into admin_view (av_name, av_page_name) values ('編輯加盟店資訊', 'franchisee/edit');
insert into admin_view (av_name, av_page_name) values ('加盟店一覽', 'franchisee/list');
insert into admin_view (av_name, av_page_name) values ('新增辦事處', 'office/add');
insert into admin_view (av_name, av_page_name) values ('編輯辦事處資訊', 'office/edit');
insert into admin_view (av_name, av_page_name) values ('辦事處一覽', 'office/list');
insert into admin_view (av_name, av_page_name) values ('忘記密碼', 'secure/forget-password');
insert into admin_view (av_name, av_page_name) values ('重設密碼', 'secure/reset-password');
insert into admin_view (av_name, av_page_name) values ('登入', 'secure/sign-in');
insert into admin_view (av_name, av_page_name) values ('新增加盟店資訊輪播圖片', 'slider-franchisee/add');
insert into admin_view (av_name, av_page_name) values ('編輯加盟店資訊輪播圖片資訊', 'slider-franchisee/edit');
insert into admin_view (av_name, av_page_name) values ('加盟店資訊輪播圖片一覽', 'slider-franchisee/list');
insert into admin_view (av_name, av_page_name) values ('新增醫療新知輪播圖片', 'slider-knowledge/add');
insert into admin_view (av_name, av_page_name) values ('編輯醫療新知輪播圖片資訊', 'slider-knowledge/edit');
insert into admin_view (av_name, av_page_name) values ('醫療新知輪播圖片一覽', 'slider-knowledge/list');
insert into admin_view (av_name, av_page_name) values ('新增主輪播圖片', 'slider-main/add');
insert into admin_view (av_name, av_page_name) values ('編輯主輪播圖片資訊', 'slider-main/edit');
insert into admin_view (av_name, av_page_name) values ('主輪播圖片一覽', 'slider-main/list');
insert into admin_view (av_name, av_page_name) values ('新增近期活動輪播圖片', 'slider-recent/add');
insert into admin_view (av_name, av_page_name) values ('編輯近期活動輪播圖片資訊', 'slider-recent/edit');
insert into admin_view (av_name, av_page_name) values ('近期活動輪播圖片一覽', 'slider-recent/list');
insert into admin_view (av_name, av_page_name) values ('新增優惠活動輪播圖片', 'slider-sale/add');
insert into admin_view (av_name, av_page_name) values ('編輯優惠活動輪播圖片資訊', 'slider-sale/edit');
insert into admin_view (av_name, av_page_name) values ('優惠活動輪播圖片一覽', 'slider-sale/list');
insert into admin_view (av_name, av_page_name) values ('新增醫療團隊輪播圖片', 'slider-team/add');
insert into admin_view (av_name, av_page_name) values ('編輯醫療團隊輪播圖片資訊', 'slider-team/edit');
insert into admin_view (av_name, av_page_name) values ('醫療團隊輪播圖片一覽', 'slider-team/list');
insert into admin_view (av_name, av_page_name) values ('新增區域', 'state/add');
insert into admin_view (av_name, av_page_name) values ('編輯區域資訊', 'state/edit');
insert into admin_view (av_name, av_page_name) values ('區域一覽', 'state/list');
insert into admin_view (av_name, av_page_name) values ('新增相關影音', 'video-related/add');
insert into admin_view (av_name, av_page_name) values ('編輯相關影音資訊', 'video-related/edit');
insert into admin_view (av_name, av_page_name) values ('相關影音一覽', 'video-related/list');

-- admin_action
insert into admin_action (aa_name, aa_page_name) values ('變更密碼', 'admin/change-password.do');
insert into admin_action (aa_name, aa_page_name) values ('編輯個人資訊', 'admin/edit.do');
insert into admin_action (aa_name, aa_page_name) values ('註冊', 'admin/sign-up.do');
insert into admin_action (aa_name, aa_page_name) values ('新增城市', 'city/add.do');
insert into admin_action (aa_name, aa_page_name) values ('編輯城市資訊', 'city/edit.do');
insert into admin_action (aa_name, aa_page_name) values ('新增診所', 'clinic/add.do');
insert into admin_action (aa_name, aa_page_name) values ('編輯診所資訊', 'clinic/edit.do');
insert into admin_action (aa_name, aa_page_name) values ('新增國家', 'country/add.do');
insert into admin_action (aa_name, aa_page_name) values ('編輯國家資訊', 'country/edit.do');
insert into admin_action (aa_name, aa_page_name) values ('新增加盟店', 'franchisee/add.do');
insert into admin_action (aa_name, aa_page_name) values ('編輯加盟店資訊', 'franchisee/edit.do');
insert into admin_action (aa_name, aa_page_name) values ('新增辦事處', 'office/add.do');
insert into admin_action (aa_name, aa_page_name) values ('編輯辦事處資訊', 'office/edit.do');
insert into admin_action (aa_name, aa_page_name) values ('忘記密碼', 'secure/forget-password.do');
insert into admin_action (aa_name, aa_page_name) values ('重設密碼', 'secure/reset-password.do');
insert into admin_action (aa_name, aa_page_name) values ('登入', 'secure/sign-in.do');
insert into admin_action (aa_name, aa_page_name) values ('登出', 'secure/sign-out.do');
insert into admin_action (aa_name, aa_page_name) values ('新增加盟店資訊輪播圖片', 'slider-franchisee/add.do');
insert into admin_action (aa_name, aa_page_name) values ('編輯加盟店資訊輪播圖片資訊', 'slider-franchisee/edit.do');
insert into admin_action (aa_name, aa_page_name) values ('新增醫療新知輪播圖片', 'slider-knowledge/add.do');
insert into admin_action (aa_name, aa_page_name) values ('編輯醫療新知輪播圖片資訊', 'slider-knowledge/edit.do');
insert into admin_action (aa_name, aa_page_name) values ('新增主輪播圖片', 'slider-main/add.do');
insert into admin_action (aa_name, aa_page_name) values ('編輯主輪播圖片資訊', 'slider-main/edit.do');
insert into admin_action (aa_name, aa_page_name) values ('新增近期活動輪播圖片', 'slider-recent/add.do');
insert into admin_action (aa_name, aa_page_name) values ('編輯近期活動輪播圖片資訊', 'slider-recent/edit.do');
insert into admin_action (aa_name, aa_page_name) values ('新增優惠活動輪播圖片', 'slider-sale/add.do');
insert into admin_action (aa_name, aa_page_name) values ('編輯優惠活動輪播圖片資訊', 'slider-sale/edit.do');
insert into admin_action (aa_name, aa_page_name) values ('新增醫療團隊輪播圖片', 'slider-team/add.do');
insert into admin_action (aa_name, aa_page_name) values ('編輯醫療團隊輪播圖片資訊', 'slider-team/edit.do');
insert into admin_action (aa_name, aa_page_name) values ('新增區域', 'state/add.do');
insert into admin_action (aa_name, aa_page_name) values ('編輯區域資訊', 'state/edit.do');
insert into admin_action (aa_name, aa_page_name) values ('新增相關影音', 'video-related/add.do');
insert into admin_action (aa_name, aa_page_name) values ('編輯相關影音資訊', 'video-related/edit.do');