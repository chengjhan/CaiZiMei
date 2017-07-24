/*
 * CaiZiMei
 * File: FranchiseeService.java
 * Author: 詹晟
 * Date: 2017/7/24
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

	List<FranchiseeBean> selectAll();

	FranchiseeBean selectByFr_id(Integer fr_id);

	List<FranchiseeBean> selectByFr_status();

	FranchiseeBean insert(FranchiseeBean franchiseeBean);

	FranchiseeBean update(FranchiseeBean franchiseeBean);

	FranchiseeBean updateFr_status(FranchiseeBean franchiseeBean_fr_id);

}
