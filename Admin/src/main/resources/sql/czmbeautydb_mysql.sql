-- MySQL

drop database if exists czmbeautydb;

create database if not exists czmbeautydb;

use czmbeautydb;

-- CREATE
create table category_path (
    cp_id                   int auto_increment not null,
    cp_name                 varchar(10) not null,
    cp_extension            varchar(10) not null,
    primary key (cp_id)
);

create table admin_path (
    ap_id                   int auto_increment not null,
    ap_cp_id                int not null,
    ap_path                 varchar(50) not null,
    ap_name                 nvarchar(20) not null,
    ap_authority            tinyint not null,
    primary key (ap_id),
    foreign key (ap_cp_id) references category_path (cp_id)
);

create table user_path (
    up_id                   int auto_increment not null,
    up_cp_id                int not null,
    up_path                 varchar(50) not null,
    up_name                 nvarchar(20) not null,
    primary key (up_id),
    foreign key (up_cp_id) references category_path (cp_id)
);

create table category (
    ca_id                   int auto_increment not null,
    ca_name                 nvarchar(20) not null,
    ca_directory            varchar(50) not null,
    primary key (ca_id)
);

create table admin (
    ad_id                   int auto_increment not null,
    ad_username             varchar(50) not null,
    ad_password             varchar(50) not null,
    ad_salt                 varchar(50) not null,
    ad_lastname             nvarchar(20),
    ad_firstname            nvarchar(20),
    ad_email                varchar(50) not null,
    ad_signup_time          datetime not null,
    ad_signin_number        int,
    ad_signin_ip            varchar(20),
    ad_signin_time          datetime,
    ad_update_info_time     datetime not null,
    ad_update_pwd_time      datetime not null,
    ad_status               tinyint not null,
    ad_status_time          datetime not null,
    ad_authority            tinyint not null,
    primary key (ad_id)
);

create table admin_log (
    al_id                   int auto_increment not null,
    al_insert_time          timestamp default current_timestamp not null,
    al_ad_id                int not null,
    al_ap_id                int not null,
    al_ip                   varchar(20),
    primary key (al_id),
    foreign key (al_ad_id) references admin (ad_id),
    foreign key (al_ap_id) references admin_path (ap_id)
);

create table country (
    co_id                   int auto_increment not null,
    co_iso                  varchar(2),
    co_name                 nvarchar(50) not null,
    co_phonecode            varchar(5),
    co_rank                 tinyint,
    co_status               tinyint,
    primary key (co_id)
);

create table state (
    st_id                   int auto_increment not null,
    st_co_id                int not null,
    st_name                 nvarchar(50) not null,
    st_rank                 tinyint,
    st_status               tinyint,
    primary key (st_id),
    foreign key (st_co_id) references country (co_id)
);

create table city (
    ci_id                   int auto_increment not null,
    ci_co_id                int not null,
    ci_st_id                int not null,
    ci_name                 nvarchar(50) not null,
    ci_rank                 tinyint,
    ci_status               tinyint,
    primary key (ci_id),
    foreign key (ci_co_id) references country (co_id),
    foreign key (ci_st_id) references state (st_id)
);

create table base (
    ba_id                   int auto_increment not null,
    ba_ca_id                int not null,
    ba_name                 nvarchar(20) not null,
    ba_eng_name             varchar(50),
    ba_tel_code             varchar(5),
    ba_tel                  varchar(20),
    ba_co_id                int not null,
    ba_st_id                int not null,
    ba_ci_id                int not null,
    ba_address              nvarchar(50) not null,
    ba_latitude             decimal(10,6),
    ba_longitude            decimal(10,6),
    ba_url                  varchar(100),
    ba_insert_time          datetime not null,
    ba_update_time          datetime,
    ba_status               tinyint,
    ba_status_time          datetime,
    primary key (ba_id),
    foreign key (ba_ca_id) references category (ca_id),
    foreign key (ba_co_id) references country (co_id),
    foreign key (ba_st_id) references state (st_id),
    foreign key (ba_ci_id) references city (ci_id)
);

create table image (
    im_id                   int auto_increment not null,
    im_ca_id                int not null,
    im_name                 nvarchar(20) not null,
    im_path                 varchar(500) not null,
    im_filename             varchar(50) not null,
    im_url                  varchar(100),
    im_rank                 tinyint not null,
    im_status               tinyint not null,
    im_update_time          datetime not null,
    primary key (im_id),
    foreign key (im_ca_id) references category (ca_id)
);

create table video (
    vi_id                   int auto_increment not null,
    vi_ca_id                int not null,
    vi_name                 nvarchar(50),
    vi_tag                  varchar(500) not null,
    vi_rank                 tinyint not null,
    vi_status               tinyint not null,
    vi_update_time          datetime not null,
    primary key (vi_id),
    foreign key (vi_ca_id) references category (ca_id)
);

create table html (
    ht_id                   int auto_increment not null,
    ht_ca_id                int not null,
    ht_name                 nvarchar(50),
    ht_tag                  nvarchar(20000) not null,
    ht_rank                 tinyint not null,
    ht_status               tinyint not null,
    ht_update_time          datetime not null,
    primary key (ht_id),
    foreign key (ht_ca_id) references category (ca_id)
);

-- ALTER
alter table state auto_increment = 11;
alter table city auto_increment = 101;

-- INSERT
-- category_path
insert into category_path (cp_name, cp_extension) values ('view', '');
insert into category_path (cp_name, cp_extension) values ('action', 'do');
insert into category_path (cp_name, cp_extension) values ('ajax', 'ajax');

-- admin_path
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'index', '首頁', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'about-franchisee/add', '新增加盟店頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'about-franchisee/add.do', '新增加盟店頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'about-franchisee/edit', '編輯加盟店頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'about-franchisee/edit.do', '編輯加盟店頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'about-franchisee/list', '加盟店頁面一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'about-idea/add', '新增經營理念頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'about-idea/add.do', '新增經營理念頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'about-idea/edit', '編輯經營理念頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'about-idea/edit.do', '編輯經營理念頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'about-idea/list', '經營理念頁面一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'about-introduction/add', '新增采姿美介紹頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'about-introduction/add.do', '新增采姿美介紹頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'about-introduction/edit', '編輯采姿美介紹頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'about-introduction/edit.do', '編輯采姿美介紹頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'about-introduction/list', '采姿美介紹頁面一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'about-mission/add', '新增公司使命頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'about-mission/add.do', '新增公司使命頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'about-mission/edit', '編輯公司使命頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'about-mission/edit.do', '編輯公司使命頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'about-mission/list', '公司使命頁面一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'about-territory/add', '新增事業版圖頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'about-territory/add.do', '新增事業版圖頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'about-territory/edit', '編輯事業版圖頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'about-territory/edit.do', '編輯事業版圖頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'about-territory/list', '事業版圖頁面一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'about-vision/add', '新增公司願景頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'about-vision/add.do', '新增公司願景頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'about-vision/edit', '編輯公司願景頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'about-vision/edit.do', '編輯公司願景頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'about-vision/list', '公司願景頁面一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'admin/change-password', '變更密碼', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'admin/change-password.do', '變更密碼', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'admin/edit', '編輯個人資訊', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'admin/edit.do', '編輯個人資訊', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'admin/list', '管理員一覽', 1);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'admin/profile', '個人資訊', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'admin/sign-up', '註冊', 1);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'admin/sign-up.do', '註冊', 1);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'admin-log/list', '管理員日誌一覽', 1);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'admin-log/list.do', '管理員日誌一覽', 1);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'area-city/add', '新增城市', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'area-city/add.do', '新增城市', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'area-city/edit', '編輯城市', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'area-city/edit.do', '編輯城市', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'area-city/list', '城市一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'area-country/add', '新增國家', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'area-country/add.do', '新增國家', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'area-country/edit', '編輯國家', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'area-country/edit.do', '編輯國家', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'area-country/list', '國家一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'area-state/add', '新增區域', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'area-state/add.do', '新增區域', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'area-state/edit', '編輯區域', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'area-state/edit.do', '編輯區域', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'area-state/list', '區域一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'base-clinic/add', '新增診所', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'base-clinic/add.do', '新增診所', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'base-clinic/edit', '編輯診所', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'base-clinic/edit.do', '編輯診所', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'base-clinic/list', '診所一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'base-franchisee/add', '新增加盟店', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'base-franchisee/add.do', '新增加盟店', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'base-franchisee/edit', '編輯加盟店', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'base-franchisee/edit.do', '編輯加盟店', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'base-franchisee/list', '加盟店一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'base-office/add', '新增辦事處', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'base-office/add.do', '新增辦事處', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'base-office/edit', '編輯辦事處', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'base-office/edit.do', '編輯辦事處', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'base-office/list', '辦事處一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'error/page-not-found', '找不到網頁', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'image/add', '新增圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'image/add.do', '新增圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'image/edit', '編輯圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'image/edit.do', '編輯圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'image/list', '圖片一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'info-knowleage/add', '新增醫療新知頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'info-knowleage/add.do', '新增醫療新知頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'info-knowleage/edit', '編輯醫療新知頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'info-knowleage/edit.do', '編輯醫療新知頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'info-knowleage/list', '醫療新知頁面一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'info-video-related/add', '新增相關影音頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'info-video-related/add.do', '新增相關影音頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'info-video-related/edit', '編輯相關影音頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'info-video-related/edit.do', '編輯相關影音頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'info-video-related/list', '相關影音頁面一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'news-recent/add', '新增近期活動頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'news-recent/add.do', '新增近期活動頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'news-recent/edit', '編輯近期活動頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'news-recent/edit.do', '編輯近期活動頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'news-recent/list', '近期活動頁面一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'news-sale/add', '新增優惠活動頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'news-sale/add.do', '新增優惠活動頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'news-sale/edit', '編輯優惠活動頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'news-sale/edit.do', '編輯優惠活動頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'news-sale/list', '優惠活動頁面一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'secure/forget-password', '忘記密碼', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'secure/forget-password.do', '忘記密碼', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'secure/reset-password', '重設密碼', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'secure/reset-password.do', '重設密碼', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'secure/sign-in', '登入', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'secure/sign-in.do', '登入', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'secure/sign-out.do', '登出', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'slider-doctor/add', '新增醫療團隊輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'slider-doctor/add.do', '新增醫療團隊輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'slider-doctor/edit', '編輯醫療團隊輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'slider-doctor/edit.do', '編輯醫療團隊輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'slider-doctor/list', '醫療團隊輪播圖片一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'slider-franchisee/add', '新增加盟店資訊輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'slider-franchisee/add.do', '新增加盟店資訊輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'slider-franchisee/edit', '編輯加盟店資訊輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'slider-franchisee/edit.do', '編輯加盟店資訊輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'slider-franchisee/list', '加盟店資訊輪播圖片一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'slider-knowledge/add', '新增醫療新知輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'slider-knowledge/add.do', '新增醫療新知輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'slider-knowledge/edit', '編輯醫療新知輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'slider-knowledge/edit.do', '編輯醫療新知輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'slider-knowledge/list', '醫療新知輪播圖片一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'slider-main/add', '新增主輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'slider-main/add.do', '新增主輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'slider-main/edit', '編輯主輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'slider-main/edit.do', '編輯主輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'slider-main/list', '主輪播圖片一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'slider-recent/add', '新增近期活動輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'slider-recent/add.do', '新增近期活動輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'slider-recent/edit', '編輯近期活動輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'slider-recent/edit.do', '編輯近期活動輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'slider-recent/list', '近期活動輪播圖片一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'slider-sale/add', '新增優惠活動輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'slider-sale/add.do', '新增優惠活動輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'slider-sale/edit', '編輯優惠活動輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'slider-sale/edit.do', '編輯優惠活動輪播圖片', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'slider-sale/list', '優惠活動輪播圖片一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'team-doctor/add', '新增醫療團隊頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'team-doctor/add.do', '新增醫療團隊頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'team-doctor/edit', '編輯醫療團隊頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'team-doctor/edit.do', '編輯醫療團隊頁面', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'team-doctor/list', '醫療團隊頁面一覽', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'video-main/add', '新增相關影音', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'video-main/add.do', '新增相關影音', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'video-main/edit', '編輯相關影音', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (2, 'video-main/edit.do', '編輯相關影音', 0);
insert into admin_path (ap_cp_id, ap_path, ap_name, ap_authority) values (1, 'video-main/list', '相關影音一覽', 0);

-- user_path
insert into user_path (up_cp_id, up_path, up_name) values (1, 'index', '首頁');
insert into user_path (up_cp_id, up_path, up_name) values (1, 'about/franchisee', '加盟店');
insert into user_path (up_cp_id, up_path, up_name) values (1, 'about/idea', '經營理念');
insert into user_path (up_cp_id, up_path, up_name) values (1, 'about/introduction', '采姿美介紹');
insert into user_path (up_cp_id, up_path, up_name) values (1, 'about/mission', '公司使命');
insert into user_path (up_cp_id, up_path, up_name) values (1, 'about/territory', '事業版圖');
insert into user_path (up_cp_id, up_path, up_name) values (1, 'about/vision', '公司願景');
insert into user_path (up_cp_id, up_path, up_name) values (1, 'error/page-not-found', '找不到網頁');
insert into user_path (up_cp_id, up_path, up_name) values (1, 'info/knowleage', '醫療新知');
insert into user_path (up_cp_id, up_path, up_name) values (1, 'info/video-related', '相關影音');
insert into user_path (up_cp_id, up_path, up_name) values (1, 'news/recent', '近期活動');
insert into user_path (up_cp_id, up_path, up_name) values (1, 'news/sale', '優惠活動');
insert into user_path (up_cp_id, up_path, up_name) values (1, 'team/doctor', '醫療團隊');
insert into user_path (up_cp_id, up_path, up_name) values (3, 'base/list.ajax', '據點一覽');
insert into user_path (up_cp_id, up_path, up_name) values (3, 'slider/doctor.ajax', '醫療團隊輪播');
insert into user_path (up_cp_id, up_path, up_name) values (3, 'slider/franchisee.ajax', '加盟店資訊輪播');
insert into user_path (up_cp_id, up_path, up_name) values (3, 'slider/knowledge.ajax', '醫療新知輪播');
insert into user_path (up_cp_id, up_path, up_name) values (3, 'slider/main.ajax', '主輪播');
insert into user_path (up_cp_id, up_path, up_name) values (3, 'slider/recent.ajax', '近期活動輪播');
insert into user_path (up_cp_id, up_path, up_name) values (3, 'slider/sale.ajax', '優惠活動輪播');
insert into user_path (up_cp_id, up_path, up_name) values (3, 'video/main.ajax', '相關影音');

-- category
insert into category (ca_name, ca_directory) values ('管理員', 'admin');
insert into category (ca_name, ca_directory) values ('管理員日誌', 'admin-log');
insert into category (ca_name, ca_directory) values ('辦事處', 'base-office');
insert into category (ca_name, ca_directory) values ('加盟店', 'base-franchisee');
insert into category (ca_name, ca_directory) values ('診所', 'base-clinic');
insert into category (ca_name, ca_directory) values ('主輪播圖片', 'slider-main');
insert into category (ca_name, ca_directory) values ('相關影音', 'video-main');
insert into category (ca_name, ca_directory) values ('加盟店資訊輪播圖片', 'slider-franchisee');
insert into category (ca_name, ca_directory) values ('近期活動輪播圖片', 'slider-recent');
insert into category (ca_name, ca_directory) values ('優惠活動輪播圖片', 'slider-sale');
insert into category (ca_name, ca_directory) values ('醫療新知輪播圖片', 'slider-knowledge');
insert into category (ca_name, ca_directory) values ('醫療團隊輪播圖片', 'slider-doctor');
insert into category (ca_name, ca_directory) values ('采姿美介紹頁面', 'about-introduction');
insert into category (ca_name, ca_directory) values ('經營理念頁面', 'about-idea');
insert into category (ca_name, ca_directory) values ('公司願景頁面', 'about-vision');
insert into category (ca_name, ca_directory) values ('公司使命頁面', 'about-mission');
insert into category (ca_name, ca_directory) values ('事業版圖頁面', 'about-territory');
insert into category (ca_name, ca_directory) values ('加盟店頁面', 'about-franchisee');
insert into category (ca_name, ca_directory) values ('醫療團隊頁面', 'team-doctor');
insert into category (ca_name, ca_directory) values ('近期活動頁面', 'news-recent');
insert into category (ca_name, ca_directory) values ('優惠活動頁面', 'news-sale');
insert into category (ca_name, ca_directory) values ('醫療新知頁面', 'info-knowleage');
insert into category (ca_name, ca_directory) values ('相關影音頁面', 'info-video-related');
insert into category (ca_name, ca_directory) values ('圖片', 'image');

-- admin
insert into admin (ad_id, ad_username, ad_password, ad_salt, ad_lastname, ad_firstname, ad_email, ad_signup_time, ad_signin_number, ad_signin_ip, ad_signin_time, ad_update_info_time, ad_update_pwd_time, ad_status, ad_status_time, ad_authority) values (100, 'admin', '538f10610b9eda06f83d7d73332d2ed0', '37f3d447-fad6-4ec0-b5fb-6a8f05b60c77', 'czmbeauty', 'admin', 'admin@czmbeauty.com', now(), 0, null, null, now(), now(), 1, now(), 1);

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

-- base
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (3, '采姿美台北辦事處', 'CaiZiMei', '02', '27079333', 1, 11, 102, '大安區信義路四段58號3F-2', 25.033072, 121.545437, 'http://localhost:8080/User/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (4, '甘肅天水采姿美加盟旗艦館', 'CaiZiMei', null, null, 2, 17, 123, '秦城區陽光麗景灣商鋪19號', 34.576471, 105.721828, 'http://localhost:8080/User/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (5, '曼星整形醫美診所', 'Twinkle Clinic', '02', '27079333', 1, 11, 102, '大安區信義路四段58號3F-2', 25.033072, 121.545437, 'http://twinkle-clinic.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (5, '晶鑽時尚診所-台北', 'Diamond Cosmetic', '02', '27665066', 1, 11, 102, '信義區忠孝東路4段563號3F', 25.041589, 121.564050, 'http://diamondcosmetic.com.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (5, '晶鑽時尚診所-台中', 'Diamond Cosmetic', '02', '23195800', 1, 11, 108, '西區公益路130號B1', 24.151141, 120.660487, 'http://diamondcosmetic.com.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (5, '晶鑽時尚診所-高雄', 'Diamond Cosmetic', '02', '2367811', 1, 11, 115, '新興區中正三路156號', 22.631456, 120.304368, 'http://diamondcosmetic.com.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (5, '法泊時尚診所-台北', 'Fabulous Clinic', '02', '27199010', 1, 11, 102, '松山區復興北路311號3樓', 25.058785, 121.544431, 'http://www.fabulous-clinic.com', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (5, '法泊時尚診所-高雄', 'Fabulous Clinic', '02', '5585838', 1, 11, 115, '左營區博愛二路656號', 22.669675, 120.303546, 'http://www.fabulous-clinic.com', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (5, '喬雅時尚診所', 'Joya Clinical Beauty', '02', '27715833', 1, 11, 102, '大安區忠孝東路四段147巷1號10樓', 25.041839, 121.544056, 'http://www.joya-beauty.com.tw', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (5, '好萊塢診所-台北仁愛', 'Hollywood', '02', '27717771', 1, 11, 102, '大安區仁愛路四段105巷5號', 25.038633, 121.550189, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (5, '好萊塢診所-台北忠孝', 'Hollywood', '02', '27315900', 1, 11, 102, '大安區忠孝東路四段94號2樓之1', 25.041701, 121.545949, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (5, '好萊塢診所-台北復興', 'Hollywood', '02', '27519600', 1, 11, 102, '大安區忠孝東路三段283號2樓', 25.042171, 121.542878, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (5, '好萊塢診所-台北南西', 'Hollywood', '02', '25622701', 1, 11, 102, '中山區南京東路一段24號3樓', 25.052305, 121.523649, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (5, '好萊塢診所-台北光復', 'Hollywood', '02', '27786800', 1, 11, 102, '大安區光復南路260巷23號3樓', 25.040441, 121.556414, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (5, '好萊塢診所-台北南港', 'Hollywood', '02', '27828200', 1, 11, 102, '南港區三重路26號1樓', 25.056361, 121.614177, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (5, '好萊塢診所-桃園', 'Hollywood', '03', '3263123', 1, 11, 104, '桃園區新埔六街38號1樓', 25.014528, 121.302752, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (5, '好萊塢診所-台中', 'Hollywood', '04', '22595825', 1, 11, 108, '西屯區市政北一路1號2樓', 24.157116, 120.646442, 'http://hollywood.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (5, '璀璨精品牙醫診所', 'Dazzling Dental Clinic', '02', '28721239', 1, 11, 102, '士林區天玉街41號', 25.121355, 121.529410, 'http://www.dazzlingdental.com.tw/', now(), now(), 1, now());
insert into base (ba_ca_id, ba_name, ba_eng_name, ba_tel_code, ba_tel, ba_co_id, ba_st_id, ba_ci_id, ba_address, ba_latitude, ba_longitude, ba_url, ba_insert_time, ba_update_time, ba_status, ba_status_time) values (5, '何彬彬牙醫診所', 'Ho Bing Bing Dental Clinic', '07', '2270748', 1, 11, 115, '新興區民生一路56號', 22.628011, 120.310796, 'http://1637.tw/07-2270748/', now(), now(), 1, now());

-- image
insert into image (im_ca_id, im_name, im_path, im_filename, im_url, im_rank, im_status, im_update_time) values (6, '曼星整形醫美診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_twinkle_clinic.jpg', 'http://twinkle-clinic.tw', 1, 1, now());
insert into image (im_ca_id, im_name, im_path, im_filename, im_url, im_rank, im_status, im_update_time) values (6, '晶鑽時尚診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_diamondcosmetic.jpg', 'http://diamondcosmetic.com.tw/', 2, 1, now());
insert into image (im_ca_id, im_name, im_path, im_filename, im_url, im_rank, im_status, im_update_time) values (6, '法泊時尚診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_fabulous_clinic.png', 'http://www.fabulous-clinic.com', 3, 1, now());
insert into image (im_ca_id, im_name, im_path, im_filename, im_url, im_rank, im_status, im_update_time) values (6, '喬雅時尚診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_joya_beauty.png', 'http://www.joya-beauty.com.tw', 4, 1, now());
insert into image (im_ca_id, im_name, im_path, im_filename, im_url, im_rank, im_status, im_update_time) values (6, '好萊塢診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_hollywood.jpg', 'http://hollywood.tw/', 5, 1, now());
insert into image (im_ca_id, im_name, im_path, im_filename, im_url, im_rank, im_status, im_update_time) values (6, '何彬彬牙醫診所', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/slider-main/', 'clinic_1637.jpg', 'http://1637.tw/07-2270748/', 6, 1, now());
insert into image (im_ca_id, im_name, im_path, im_filename, im_url, im_rank, im_status, im_update_time) values (24, '事業版圖', '/Users/chengjhan/Desktop/Case/CaiZiMei/apache-tomcat-8.0.41/wtpwebapps/Admin/images/image/', 'about_territory.jpg', '', 0, 1, now());

-- video
insert into video (vi_ca_id, vi_name, vi_tag, vi_rank, vi_status, vi_update_time) values (7, 'aaa', '<iframe width="560" height="315" src="https://www.youtube.com/embed/C589vlQLQEA" frameborder="0" allowfullscreen></iframe>', 1, 1, now());
insert into video (vi_ca_id, vi_name, vi_tag, vi_rank, vi_status, vi_update_time) values (7, 'bbb', '<iframe width="560" height="315" src="https://www.youtube.com/embed/zXvg00_5OpM" frameborder="0" allowfullscreen></iframe>', 2, 0, now());
insert into video (vi_ca_id, vi_name, vi_tag, vi_rank, vi_status, vi_update_time) values (7, 'ccc', '<iframe width="560" height="315" src="https://www.youtube.com/embed/R82z1DfsKWk" frameborder="0" allowfullscreen></iframe>', 3, 0, now());
insert into video (vi_ca_id, vi_name, vi_tag, vi_rank, vi_status, vi_update_time) values (23, 'a', '<iframe width="560" height="315" src="https://www.youtube.com/embed/C589vlQLQEA" frameborder="0" allowfullscreen></iframe>', 1, 1, now());
insert into video (vi_ca_id, vi_name, vi_tag, vi_rank, vi_status, vi_update_time) values (23, 'b', '<iframe width="560" height="315" src="https://www.youtube.com/embed/zXvg00_5OpM" frameborder="0" allowfullscreen></iframe>', 2, 1, now());
insert into video (vi_ca_id, vi_name, vi_tag, vi_rank, vi_status, vi_update_time) values (23, 'c', '<iframe width="560" height="315" src="https://www.youtube.com/embed/R82z1DfsKWk" frameborder="0" allowfullscreen></iframe>', 3, 1, now());
insert into video (vi_ca_id, vi_name, vi_tag, vi_rank, vi_status, vi_update_time) values (23, 'd', '<iframe width="560" height="315" src="https://www.youtube.com/embed/C589vlQLQEA" frameborder="0" allowfullscreen></iframe>', 4, 1, now());
insert into video (vi_ca_id, vi_name, vi_tag, vi_rank, vi_status, vi_update_time) values (23, 'e', '<iframe width="560" height="315" src="https://www.youtube.com/embed/zXvg00_5OpM" frameborder="0" allowfullscreen></iframe>', 5, 1, now());
insert into video (vi_ca_id, vi_name, vi_tag, vi_rank, vi_status, vi_update_time) values (23, 'f', '<iframe width="560" height="315" src="https://www.youtube.com/embed/R82z1DfsKWk" frameborder="0" allowfullscreen></iframe>', 6, 1, now());
insert into video (vi_ca_id, vi_name, vi_tag, vi_rank, vi_status, vi_update_time) values (23, 'g', '<iframe width="560" height="315" src="https://www.youtube.com/embed/C589vlQLQEA" frameborder="0" allowfullscreen></iframe>', 7, 1, now());
insert into video (vi_ca_id, vi_name, vi_tag, vi_rank, vi_status, vi_update_time) values (23, 'h', '<iframe width="560" height="315" src="https://www.youtube.com/embed/zXvg00_5OpM" frameborder="0" allowfullscreen></iframe>', 8, 1, now());
insert into video (vi_ca_id, vi_name, vi_tag, vi_rank, vi_status, vi_update_time) values (23, 'i', '<iframe width="560" height="315" src="https://www.youtube.com/embed/R82z1DfsKWk" frameborder="0" allowfullscreen></iframe>', 9, 1, now());