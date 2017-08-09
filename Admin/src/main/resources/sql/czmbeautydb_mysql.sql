-- MySQL

drop database if exists czmbeautydb;

create database if not exists czmbeautydb;

use czmbeautydb;

-- create
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
	primary key (co_id)
);

create table state (
	st_id					int auto_increment not null,
	st_co_id				int not null,
	st_name					nvarchar(50) not null,
	st_rank					tinyint,
	primary key (st_id),
	foreign key (st_co_id) references country (co_id)
);

create table city (
	ci_id					int auto_increment not null,
	ci_co_id				int not null,
	ci_st_id				int not null,
	ci_name					nvarchar(50) not null,
	ci_rank					tinyint,
	primary key (ci_id),
	foreign key (ci_co_id) references country (co_id),
	foreign key (ci_st_id) references state (st_id)
);

create table base_kind (
	bk_id					int auto_increment not null,
	bk_name					nvarchar(20) not null,
	bk_eng_name				varchar(50) not null,
	primary key (bk_id)
);

create table base (
	ba_id					int auto_increment not null,
	ba_bk_id				int not null,
	ba_name					nvarchar(20) not null,
	ba_eng_name				varchar(50),
	ba_localphone			varchar(20),
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
	foreign key (ba_bk_id) references base_kind (bk_id),
	foreign key (ba_co_id) references country (co_id),
	foreign key (ba_st_id) references state (st_id),
	foreign key (ba_ci_id) references city (ci_id)
);

create table image_item (
	ii_id					int auto_increment not null,
	ii_name					nvarchar(20) not null,
	ii_eng_name				varchar(50) not null,
	primary key (ii_id)
);

create table image (
	im_id					int auto_increment not null,
	im_ii_id				int not null,
	im_name					nvarchar(20) not null,
	im_path					varchar(500) not null,
	im_filename				varchar(50) not null,
	im_url					varchar(100),
	im_rank					tinyint not null,
	im_status				tinyint not null,
	im_update_time			datetime not null,
	primary key (im_id),
	foreign key (im_ii_id) references image_item (ii_id)
);

create table slider_main (
	sm_id					int auto_increment not null,
	sm_name					nvarchar(20) not null,
	sm_path					varchar(500) not null,
	sm_filename				varchar(50) not null,
	sm_url					varchar(100),
	sm_rank					tinyint not null,
	sm_status				tinyint not null,
	sm_update_time			datetime not null,
	primary key (sm_id)
);

-- alter
alter table state auto_increment = 11;
alter table city auto_increment = 101;

-- insert
-- admin
insert into admin (ad_id, ad_username, ad_password, ad_salt, ad_lastname, ad_firstname, ad_email, ad_signup_time, ad_signin_number, ad_signin_ip, ad_signin_time, ad_update_info_time, ad_update_pwd_time, ad_status, ad_status_time) values (100, 'admin', '538f10610b9eda06f83d7d73332d2ed0', '37f3d447-fad6-4ec0-b5fb-6a8f05b60c77', 'czmbeauty', 'admin', 'admin@czmbeauty.com', now(), 0, null, null, now(), now(), 1, now());

-- country
insert into country (co_iso, co_name, co_phonecode, co_rank) values ('TW', '台灣', '886', 1);
insert into country (co_iso, co_name, co_phonecode, co_rank) values ('CN', '中國', '86', 2);

-- state
insert into state (st_co_id, st_name, st_rank) values (1, '台灣', 1);
insert into state (st_co_id, st_name, st_rank) values (1, '澎湖', 2);
insert into state (st_co_id, st_name, st_rank) values (1, '金門', 3);
insert into state (st_co_id, st_name, st_rank) values (1, '馬祖', 4);
insert into state (st_co_id, st_name, st_rank) values (2, '福建省', 1);
insert into state (st_co_id, st_name, st_rank) values (2, '廣東省', 2);
insert into state (st_co_id, st_name, st_rank) values (2, '甘肅省', 3);

-- city
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 11, '基隆市', 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 11, '臺北市', 2);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 11, '新北市', 3);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 11, '桃園市', 4);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 11, '新竹市', 5);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 11, '新竹縣', 6);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 11, '苗栗縣', 7);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 11, '臺中市', 8);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 11, '彰化縣', 9);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 11, '南投縣', 10);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 11, '雲林縣', 11);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 11, '嘉義市', 12);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 11, '嘉義縣', 13);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 11, '臺南市', 14);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 11, '高雄市', 15);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 11, '屏東縣', 16);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 11, '臺東縣', 17);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 11, '花蓮縣', 18);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 11, '宜蘭縣', 19);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 12, '澎湖縣', 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 13, '金門縣', 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 14, '連江縣', 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (2, 17, '天水市', 1);

-- base_kind
insert into base_kind (bk_name, bk_eng_name) values ('辦事處', 'office');
insert into base_kind (bk_name, bk_eng_name) values ('加盟店', 'franchisee');
insert into base_kind (bk_name, bk_eng_name) values ('診所', 'clinic');

-- base
insert into base (ba_bk_id, ba_name, ba_eng_name, ba_localphone, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (1, '采姿美台北辦事處', 'CaiZiMei', '02-27079333', 1, 11, 102, '大安區信義路四段58號3F-2', 25.033072, 121.545437, 'http://localhost:8080/User/', now(), now(), 1, now());
insert into base (ba_bk_id, ba_name, ba_eng_name, ba_localphone, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (2, '甘肅天水采姿美加盟旗艦館', 'CaiZiMei', '', 2, 17, 123, '秦城區陽光麗景灣商鋪19號', 34.576471, 105.721828, 'http://localhost:8080/User/', now(), now(), 1, now());
insert into base (ba_bk_id, ba_name, ba_eng_name, ba_localphone, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '曼星整形醫美診所', 'Twinkle Clinic', '02-27079333', 1, 11, 102, '大安區信義路四段58號3F-2', 25.033072, 121.545437, 'http://twinkle-clinic.tw/', now(), now(), 1, now());
insert into base (ba_bk_id, ba_name, ba_eng_name, ba_localphone, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '晶鑽時尚診所-台北', 'Diamond Cosmetic', '02-27665066', 1, 11, 102, '信義區忠孝東路4段563號3F', 25.041589, 121.564050, 'http://diamondcosmetic.com.tw/', now(), now(), 1, now());
insert into base (ba_bk_id, ba_name, ba_eng_name, ba_localphone, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '晶鑽時尚診所-台中', 'Diamond Cosmetic', '04-23195800', 1, 11, 108, '西區公益路130號B1', 24.151141, 120.660487, 'http://diamondcosmetic.com.tw/', now(), now(), 1, now());
insert into base (ba_bk_id, ba_name, ba_eng_name, ba_localphone, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '晶鑽時尚診所-高雄', 'Diamond Cosmetic', '07-2367811', 1, 11, 115, '新興區中正三路156號', 22.631456, 120.304368, 'http://diamondcosmetic.com.tw/', now(), now(), 1, now());
insert into base (ba_bk_id, ba_name, ba_eng_name, ba_localphone, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '法泊時尚診所-台北', 'Fabulous Clinic', '02-27199010', 1, 11, 102, '松山區復興北路311號3樓', 25.058785, 121.544431, 'http://www.fabulous-clinic.com', now(), now(), 1, now());
insert into base (ba_bk_id, ba_name, ba_eng_name, ba_localphone, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '法泊時尚診所-高雄', 'Fabulous Clinic', '07-5585838', 1, 11, 115, '左營區博愛二路656號', 22.669675, 120.303546, 'http://www.fabulous-clinic.com', now(), now(), 1, now());
insert into base (ba_bk_id, ba_name, ba_eng_name, ba_localphone, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '喬雅時尚診所', 'Joya Clinical Beauty', '02-27715833', 1, 11, 102, '大安區忠孝東路四段147巷1號10樓', 25.041839, 121.544056, 'http://www.joya-beauty.com.tw', now(), now(), 1, now());
insert into base (ba_bk_id, ba_name, ba_eng_name, ba_localphone, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '好萊塢診所-台北仁愛', 'Hollywood', '02-27717771', 1, 11, 102, '大安區仁愛路四段105巷5號', 25.038633, 121.550189, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_bk_id, ba_name, ba_eng_name, ba_localphone, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '好萊塢診所-台北忠孝', 'Hollywood', '02-27315900', 1, 11, 102, '大安區忠孝東路四段94號2樓之1', 25.041701, 121.545949, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_bk_id, ba_name, ba_eng_name, ba_localphone, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '好萊塢診所-台北復興', 'Hollywood', '02-27519600', 1, 11, 102, '大安區忠孝東路三段283號2樓', 25.042171, 121.542878, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_bk_id, ba_name, ba_eng_name, ba_localphone, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '好萊塢診所-台北南西', 'Hollywood', '02-25622701', 1, 11, 102, '中山區南京東路一段24號3樓', 25.052305, 121.523649, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_bk_id, ba_name, ba_eng_name, ba_localphone, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '好萊塢診所-台北光復', 'Hollywood', '02-27786800', 1, 11, 102, '大安區光復南路260巷23號3樓', 25.040441, 121.556414, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_bk_id, ba_name, ba_eng_name, ba_localphone, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '好萊塢診所-台北南港', 'Hollywood', '02-27828200', 1, 11, 102, '南港區三重路26號1樓', 25.056361, 121.614177, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_bk_id, ba_name, ba_eng_name, ba_localphone, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '好萊塢診所-桃園', 'Hollywood', '03-3263123', 1, 11, 104, '桃園區新埔六街38號1樓', 25.014528, 121.302752, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_bk_id, ba_name, ba_eng_name, ba_localphone, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '好萊塢診所-台中', 'Hollywood', '04-22595825', 1, 11, 108, '西屯區市政北一路1號2樓', 24.157116, 120.646442, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_bk_id, ba_name, ba_eng_name, ba_localphone, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '璀璨精品牙醫診所', 'Dazzling Dental Clinic', '02-28721239', 1, 11, 102, '士林區天玉街41號', 25.121355, 121.529410, 'http://www.dazzlingdental.com.tw/', now(), now(), 1, now());
insert into base (ba_bk_id, ba_name, ba_eng_name, ba_localphone, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '何彬彬牙醫診所', 'Ho Bing Bing Dental Clinic', '07-2270748', 1, 11, 115, '新興區民生一路56號', 22.628011, 120.310796, 'http://1637.tw/07-2270748/', now(), now(), 1, now());

-- image_item
insert into image_item (ii_name, ii_eng_name) values ('主輪播', 'slider_main');

-- image
insert into image (im_ii_id, im_name, im_path, im_filename, im_url, im_rank, im_status, im_update_time) values (1, '曼星整形醫美診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_twinkle_clinic_1000x380.jpg', 'http://twinkle-clinic.tw', 1, 1, now());
insert into image (im_ii_id, im_name, im_path, im_filename, im_url, im_rank, im_status, im_update_time) values (1, '晶鑽時尚診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_diamondcosmetic_1000x380.jpg', 'http://diamondcosmetic.com.tw/', 2, 1, now());
insert into image (im_ii_id, im_name, im_path, im_filename, im_url, im_rank, im_status, im_update_time) values (1, '法泊時尚診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_fabulous_clinic_1000x380.png', 'http://www.fabulous-clinic.com', 3, 1, now());
insert into image (im_ii_id, im_name, im_path, im_filename, im_url, im_rank, im_status, im_update_time) values (1, '喬雅時尚診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_joya_beauty_1000x380.png', 'http://www.joya-beauty.com.tw', 4, 1, now());
insert into image (im_ii_id, im_name, im_path, im_filename, im_url, im_rank, im_status, im_update_time) values (1, '好萊塢診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_hollywood_1000x380.jpg', 'http://hollywood.tw/', 5, 1, now());
insert into image (im_ii_id, im_name, im_path, im_filename, im_url, im_rank, im_status, im_update_time) values (1, '何彬彬牙醫診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_1637_1000x380.jpg', 'http://1637.tw/07-2270748/', 6, 1, now());

-- slider_main
insert into slider_main (sm_name, sm_path, sm_filename, sm_url, sm_rank, sm_status, sm_update_time) values ('曼星整形醫美診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_twinkle_clinic_1000x380.jpg', 'http://twinkle-clinic.tw', 1, 1, now());
insert into slider_main (sm_name, sm_path, sm_filename, sm_url, sm_rank, sm_status, sm_update_time) values ('晶鑽時尚診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_diamondcosmetic_1000x380.jpg', 'http://diamondcosmetic.com.tw/', 2, 1, now());
insert into slider_main (sm_name, sm_path, sm_filename, sm_url, sm_rank, sm_status, sm_update_time) values ('法泊時尚診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_fabulous_clinic_1000x380.png', 'http://www.fabulous-clinic.com', 3, 1, now());
insert into slider_main (sm_name, sm_path, sm_filename, sm_url, sm_rank, sm_status, sm_update_time) values ('喬雅時尚診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_joya_beauty_1000x380.png', 'http://www.joya-beauty.com.tw', 4, 1, now());
insert into slider_main (sm_name, sm_path, sm_filename, sm_url, sm_rank, sm_status, sm_update_time) values ('好萊塢診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_hollywood_1000x380.jpg', 'http://hollywood.tw/', 5, 1, now());
insert into slider_main (sm_name, sm_path, sm_filename, sm_url, sm_rank, sm_status, sm_update_time) values ('何彬彬牙醫診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_1637_1000x380.jpg', 'http://1637.tw/07-2270748/', 6, 1, now());