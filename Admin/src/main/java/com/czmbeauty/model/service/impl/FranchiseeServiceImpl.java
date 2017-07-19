/*
 * CaiZiMei
 * File: FranchiseeServiceImpl.java
 * Author: 詹晟
 * Date: 2017/7/20
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
	 * 搜尋所有加盟店
	 * 
	 * @return List<FranchiseeBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<FranchiseeBean> selectAll() {

		return franchiseeDao.selectAll();
	}

	/**
	 * 加盟店流水號搜尋
	 * 
	 * @param fr_id-->加盟店流水號
	 * @return FranchiseeBean
	 */
	@Override
	@Transactional(readOnly = true)
	public FranchiseeBean selectByFr_id(Integer fr_id) {

		return franchiseeDao.selectByFr_id(fr_id);
	}

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

	/**
	 * 新增加盟店
	 * 
	 * @param franchiseeBean-->FranchiseeBean
	 * @return result-->FranchiseeBean
	 */
	@Override
	@Transactional
	public FranchiseeBean insert(FranchiseeBean franchiseeBean) {

		FranchiseeBean result = null;

		if (franchiseeBean != null) {

			result = franchiseeDao.insert(franchiseeBean);
		}
		return result;
	}

	/**
	 * 修改資料
	 * 
	 * @param franchiseeBean-->FranchiseeBean
	 * @return FranchiseeBean
	 */
	@Override
	@Transactional
	public FranchiseeBean update(FranchiseeBean franchiseeBean) {

		return franchiseeDao.update(franchiseeBean);
	}

	/**
	 * 切換狀態
	 * 
	 * @param fr_id-->加盟店流水號
	 * @return FranchiseeBean
	 */
	@Override
	@Transactional
	public FranchiseeBean updateFr_status(Integer fr_id) {

		return franchiseeDao.updateFr_status(fr_id);
	}

}
