/*
 * CaiZiMei
 * File: BaseKindDao.java
 * Author: 詹晟
 * Date: 2017/8/9
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import com.czmbeauty.model.entity.BaseKindBean;

/**
 * base_kind DAO interface
 * 
 * @author 詹晟
 */
public interface BaseKindDao {

	/**
	 * @see com.czmbeauty.model.dao.impl.BaseKindDaoImpl#selectByBk_id(Integer)
	 */
	BaseKindBean selectByBk_id(Integer bk_id);

}
