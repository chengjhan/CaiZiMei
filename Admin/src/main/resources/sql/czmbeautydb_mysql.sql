-- MySQL

drop database if exists czmbeautydb;

create database if not exists czmbeautydb;

use czmbeautydb;

create table admin (
	a_id					int auto_increment not null,
	a_username				varchar(50) not null,
	a_password				varchar(50) not null,
	a_salt					varchar(50) not null,
	a_lastname				nvarchar(20),
	a_firstname				nvarchar(20),
	a_email					varchar(50) not null,
	a_signup_time			datetime not null,
	a_signin_number			int,
	a_signin_ip				varchar(20),
	a_signin_time			datetime,
	a_update_info_time		datetime not null,
	a_update_pwd_time		datetime not null,
	a_status				tinyint not null,
	a_status_time			datetime not null,
	primary key (a_id)
);

create table admin_log (
	al_id					int auto_increment not null,
	al_insert_time			timestamp default current_timestamp not null,
	al_a_id					int not null,
	al_operation			nvarchar(50),
	al_ip					varchar(20),
	primary key (al_id),
	foreign key (al_a_id) references admin (a_id)
);

create table country (
	co_id					int auto_increment not null,
	co_iso					varchar(2),
	co_name					nvarchar(10) not null,
	co_phonecode			varchar(5),
	co_rank					tinyint,
	primary key (co_id)
);

insert into admin (a_id, a_username, a_password, a_salt, a_lastname, a_firstname, a_email, a_signup_time, a_signin_number, a_signin_ip, a_signin_time, a_update_info_time, a_update_pwd_time, a_status, a_status_time) values (100, 'admin', '538f10610b9eda06f83d7d73332d2ed0', '37f3d447-fad6-4ec0-b5fb-6a8f05b60c77', null, null, 'admin@czmbeauty.com', now(), null, null, null, now(), now(), 1, now());