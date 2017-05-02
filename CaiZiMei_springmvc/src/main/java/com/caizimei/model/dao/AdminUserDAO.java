/*
 * CaiZiMei
 * File: AdminUserDAO.java
 * Author: 詹晟
 * Date: 2017/5/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.entity.AdminUserBean;

/**
 * admin_user DAO interface
 *
 * @author 詹晟
 */
public interface AdminUserDAO {

	List<AdminUserBean> select();

	AdminUserBean selectByAdu_id(Integer adu_id);

	AdminUserBean selectByAdu_username(String adu_username);

	AdminUserBean insert(AdminUserBean adminUserBean);

	AdminUserBean update(AdminUserBean adminUserBean);

	AdminUserBean updateAdu_password(Integer adu_id, String adu_password_new_hashed);

	AdminUserBean updateAdu_signin_number(Integer adu_id);

	AdminUserBean updateAdu_signin_ip(Integer adu_id, String adu_signin_ip);

	AdminUserBean updateAdu_signin_time(Integer adu_id);

}
