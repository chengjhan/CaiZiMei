-- MySQL

drop database if exists czmbeautydb;

create database if not exists czmbeautydb;

use czmbeautydb;

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

alter table state auto_increment = 11;

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

alter table city auto_increment = 101;

create table clinic (
	cl_id					int auto_increment not null,
	cl_name					nvarchar(20) not null,
	cl_eng_name				varchar(50),
	cl_localphone			varchar(20),
	cl_co_id				int not null,
	cl_st_id				int not null,
	cl_ci_id				int not null,
	cl_address				nvarchar(50) not null,
	cl_latitude				decimal(10,6),
	cl_longitude			decimal(10,6),
	cl_url					varchar(100),
	cl_insert_time			datetime not null,
	cl_update_time			datetime,
	cl_status				tinyint,
	cl_status_time			datetime,
	primary key (cl_id),
	foreign key (cl_co_id) references country (co_id),
	foreign key (cl_st_id) references state (st_id),
	foreign key (cl_ci_id) references city (ci_id)
);

create table franchisee (
	fr_id					int auto_increment not null,
	fr_name					nvarchar(20) not null,
	fr_eng_name				varchar(50),
	fr_localphone			varchar(20),
	fr_co_id				int not null,
	fr_st_id				int not null,
	fr_ci_id				int not null,
	fr_address				nvarchar(50) not null,
	fr_latitude				decimal(10,6),
	fr_longitude			decimal(10,6),
	fr_url					varchar(100),
	fr_insert_time			datetime not null,
	fr_update_time			datetime,
	fr_status				tinyint,
	fr_status_time			datetime,
	primary key (fr_id),
	foreign key (fr_co_id) references country (co_id),
	foreign key (fr_st_id) references state (st_id),
	foreign key (fr_ci_id) references city (ci_id)
);

-- admin
insert into admin (ad_id, ad_username, ad_password, ad_salt, ad_lastname, ad_firstname, ad_email, ad_signup_time, ad_signin_number, ad_signin_ip, ad_signin_time, ad_update_info_time, ad_update_pwd_time, ad_status, ad_status_time) values (100, 'admin', '538f10610b9eda06f83d7d73332d2ed0', '37f3d447-fad6-4ec0-b5fb-6a8f05b60c77', null, null, 'admin@czmbeauty.com', now(), null, null, null, now(), now(), 1, now());

-- country
insert into country (co_iso, co_name, co_phonecode, co_rank) values ('TW', '台灣', '886', 1);
insert into country (co_iso, co_name, co_phonecode, co_rank) values ('CN', '中國', '86', 2);

-- state
insert into state (st_co_id, st_name, st_rank) values (1, '台灣', 1);
insert into state (st_co_id, st_name, st_rank) values (1, '澎湖', 2);
insert into state (st_co_id, st_name, st_rank) values (2, '福建省', 1);
insert into state (st_co_id, st_name, st_rank) values (2, '廣東省', 2);

-- city
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 11, '台北市', 1);
insert into city (ci_co_id, ci_st_id, ci_name, ci_rank) values (1, 11, '新北市', 2);

-- clinic
insert into clinic (cl_name, cl_eng_name, cl_localphone, cl_co_id, cl_st_id, cl_ci_id, cl_address, cl_latitude, cl_longitude, cl_url, cl_insert_time, cl_update_time, cl_status, cl_status_time) values ('曼星整形醫美診所', 'Twinkle Clinic', '02-27079333', 1, 11, 101, '大安區信義路四段58號3F-2', 25.033072, 121.545437, 'http://twinkle-clinic.tw/', now(), now(), '1', now());