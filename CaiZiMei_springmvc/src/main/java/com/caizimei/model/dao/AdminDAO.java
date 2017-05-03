/*
 * CaiZiMei
 * File: AdminDAO.java
 * Author: 詹晟
 * Date: 2017/5/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.entity.AdminBean;

/**
 * admin DAO interface
 *
 * @author 詹晟
 */
public interface AdminDAO {

	List<AdminBean> select();

	AdminBean insert(AdminBean adminBean);

}
