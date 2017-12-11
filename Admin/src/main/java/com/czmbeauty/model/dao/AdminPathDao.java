/*
 * CaiZiMei
 * File: AdminPathDao.java
 * Author: 詹晟
 * Date: 2017/12/11
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

import com.czmbeauty.common.constants.HqlConstants;
import com.czmbeauty.model.entity.AdminPathBean;
import com.czmbeauty.model.entity.CategoryPathBean;

/**
 * admin_path DAO interface
 *
 * @author 詹晟
 */
public interface AdminPathDao extends HqlConstants {

	/**
	 * @see com.czmbeauty.model.dao.impl.AdminPathDaoImpl#selectByAp_id(Integer)
	 */
	AdminPathBean selectByAp_id(Integer ap_id);

	/**
	 * @see com.czmbeauty.model.dao.impl.AdminPathDaoImpl#selectByAp_cp_id(Integer)
	 */
	List<AdminPathBean> selectByAp_cp_id(Integer ap_cp_id);

	/**
	 * @see com.czmbeauty.model.dao.impl.AdminPathDaoImpl#selectByAp_path(CategoryPathBean,
	 *      String)
	 */
	AdminPathBean selectByAp_path(CategoryPathBean ap_CategoryPathBean, String ap_path);

}
