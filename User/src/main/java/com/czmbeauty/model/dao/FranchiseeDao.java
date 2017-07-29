/*
 * CaiZiMei/User
 * File: FranchiseeDao.java
 * Author: 詹晟
 * Date: 2017/7/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

import com.czmbeauty.model.entity.FranchiseeBean;

/**
 * franchisee DAO interface
 * 
 * @author 詹晟
 */
public interface FranchiseeDao {

	/**
	 * @see com.czmbeauty.model.dao.impl.FranchiseeDaoImpl#selectByFr_status()
	 */
	List<FranchiseeBean> selectByFr_status();

}
