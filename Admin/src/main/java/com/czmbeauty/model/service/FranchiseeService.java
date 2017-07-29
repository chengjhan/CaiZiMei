/*
 * CaiZiMei
 * File: FranchiseeService.java
 * Author: 詹晟
 * Date: 2017/7/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.List;

import com.czmbeauty.model.entity.FranchiseeBean;

/**
 * franchisee service interface
 * 
 * @author 詹晟
 */
public interface FranchiseeService {

	/**
	 * @see com.czmbeauty.model.service.impl.FranchiseeServiceImpl#selectAll()
	 */
	List<FranchiseeBean> selectAll();

	/**
	 * @see com.czmbeauty.model.service.impl.FranchiseeServiceImpl#selectByFr_id(Integer)
	 */
	FranchiseeBean selectByFr_id(Integer fr_id);

	/**
	 * @see com.czmbeauty.model.service.impl.FranchiseeServiceImpl#selectByFr_status()
	 */
	List<FranchiseeBean> selectByFr_status();

	/**
	 * @see com.czmbeauty.model.service.impl.FranchiseeServiceImpl#insert(FranchiseeBean)
	 */
	FranchiseeBean insert(FranchiseeBean franchiseeBean);

	/**
	 * @see com.czmbeauty.model.service.impl.FranchiseeServiceImpl#update(FranchiseeBean)
	 */
	FranchiseeBean update(FranchiseeBean franchiseeBean);

	/**
	 * @see com.czmbeauty.model.service.impl.FranchiseeServiceImpl#updateFr_status(Integer)
	 */
	FranchiseeBean updateFr_status(Integer fr_id);

}
