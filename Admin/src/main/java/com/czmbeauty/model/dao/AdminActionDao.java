/*
 * CaiZiMei
 * File: AdminActionDao.java
 * Author: 詹晟
 * Date: 2017/10/2
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import com.czmbeauty.common.constants.HqlConstants;
import com.czmbeauty.model.entity.AdminActionBean;

/**
 * admin_action DAO interface
 *
 * @author 詹晟
 */
public interface AdminActionDao extends HqlConstants {

	/**
	 * @see com.czmbeauty.model.dao.impl.AdminActionDaoImpl#selectByAa_action_name(String)
	 */
	AdminActionBean selectByAa_action_name(String aa_action_name);

}
