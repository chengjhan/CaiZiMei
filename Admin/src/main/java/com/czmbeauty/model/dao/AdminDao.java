/*
 * CaiZiMei
 * File: AdminDao.java
 * Author: 詹晟
 * Date: 2017/7/21
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

import com.czmbeauty.model.entity.AdminBean;

/**
 * admin DAO interface
 *
 * @author 詹晟
 */
public interface AdminDao {

	List<AdminBean> selectAll();

	AdminBean selectByAd_id(Integer ad_id);

	AdminBean selectByAd_username(String ad_username);

	AdminBean selectByAd_email(String ad_email);

	AdminBean insert(AdminBean adminBean);

	AdminBean update(AdminBean adminBean);

	AdminBean updateAd_password(Integer ad_id, String ad_password_new_hashed);

	AdminBean updateAd_status(Integer ad_id);

}
