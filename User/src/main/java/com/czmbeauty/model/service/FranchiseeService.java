/*
 * CaiZiMei/User
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
	 * @see com.czmbeauty.model.service.impl.FranchiseeServiceImpl#selectByFr_status()
	 */
	List<FranchiseeBean> selectByFr_status();

}
