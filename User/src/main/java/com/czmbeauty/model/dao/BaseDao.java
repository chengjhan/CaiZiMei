/*
 * CaiZiMei/User
 * File: BaseDao.java
 * Author: 詹晟
 * Date: 2017/9/20
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

import com.czmbeauty.common.constants.HqlConstants;
import com.czmbeauty.model.entity.BaseBean;

/**
 * base DAO interface
 * 
 * @author 詹晟
 */
public interface BaseDao extends HqlConstants {

	/**
	 * @see com.czmbeauty.model.dao.impl.BaseDaoImpl#selectOpenBase()
	 */
	List<BaseBean> selectOpenBase();

}
