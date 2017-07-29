/*
 * CaiZiMei
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
	 * @see com.czmbeauty.model.dao.impl.FranchiseeDaoImpl#selectAll()
	 */
	List<FranchiseeBean> selectAll();

	/**
	 * @see com.czmbeauty.model.dao.impl.FranchiseeDaoImpl#selectByFr_id(Integer)
	 */
	FranchiseeBean selectByFr_id(Integer fr_id);

	/**
	 * @see com.czmbeauty.model.dao.impl.FranchiseeDaoImpl#selectByFr_status()
	 */
	List<FranchiseeBean> selectByFr_status();

	/**
	 * @see com.czmbeauty.model.dao.impl.FranchiseeDaoImpl#insert(FranchiseeBean)
	 */
	FranchiseeBean insert(FranchiseeBean franchiseeBean);

	/**
	 * @see com.czmbeauty.model.dao.impl.FranchiseeDaoImpl#update(FranchiseeBean)
	 */
	FranchiseeBean update(FranchiseeBean franchiseeBean);

}
