/*
 * CaiZiMei/User
 * File: FranchiseeServiceImpl.java
 * Author: 詹晟
 * Date: 2017/7/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.FranchiseeDao;
import com.czmbeauty.model.entity.FranchiseeBean;
import com.czmbeauty.model.service.FranchiseeService;

/**
 * franchisee service implement
 * 
 * @author 詹晟
 */
@Service(value = "franchiseeService")
public class FranchiseeServiceImpl implements FranchiseeService {

	/**
	 * 注入 FranchiseeDao
	 */
	@Autowired
	private FranchiseeDao franchiseeDao;

	/**
	 * 狀態搜尋
	 * 
	 * @return List<FranchiseeBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<FranchiseeBean> selectByFr_status() {

		return franchiseeDao.selectByFr_status();
	}

}
