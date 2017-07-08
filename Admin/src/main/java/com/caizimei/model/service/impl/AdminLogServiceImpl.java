/*
 * CaiZiMei
 * File: AdminLogServiceImpl.java
 * Author: 詹晟
 * Date: 2017/5/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caizimei.model.dao.AdminLogDao;
import com.caizimei.model.entity.AdminLogBean;
import com.caizimei.model.service.AdminLogService;

/**
 * admin_user_log service implement
 * 
 * @author 詹晟
 */
@Service(value = "adminLogService")
public class AdminLogServiceImpl implements AdminLogService {

	/**
	 * 注入 AdminLogDao
	 */
	@Autowired
	private AdminLogDao adminLogDao;

	/**
	 * 搜尋全部管理員日誌
	 * 
	 * @return List<AdminLogBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AdminLogBean> selectAll() {

		return adminLogDao.selectAll();
	}

	/**
	 * 管理員流水號搜尋
	 * 
	 * @param al_a_id-->管理員流水號
	 * @return List<AdminLogBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AdminLogBean> selectByAl_a_id(Integer al_a_id) {

		return adminLogDao.selectByAl_a_id(al_a_id);
	}

	/**
	 * 新增管理員日誌
	 * 
	 * @param adminLogBean-->AdminLogBean
	 * @return result-->AdminLogBean
	 */
	@Override
	@Transactional
	public AdminLogBean insert(AdminLogBean adminLogBean) {

		AdminLogBean result = null;

		if (adminLogBean != null) {

			result = adminLogDao.insert(adminLogBean);
		}
		return result;
	}

}
