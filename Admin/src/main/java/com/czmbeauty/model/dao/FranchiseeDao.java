/*
 * CaiZiMei
 * File: FranchiseeDao.java
 * Author: 詹晟
 * Date: 2017/7/22
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

	List<FranchiseeBean> selectAll();

	FranchiseeBean selectByFr_id(Integer fr_id);

	List<FranchiseeBean> selectByFr_status();

	FranchiseeBean insert(FranchiseeBean franchiseeBean);

	FranchiseeBean update(FranchiseeBean franchiseeBean);

}
