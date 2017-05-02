/*
 * CaiZiMei
 * File: AdminUserLogServiceImpl.java
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

import com.caizimei.model.dao.AdminUserLogDAO;
import com.caizimei.model.entity.AdminUserLogBean;
import com.caizimei.model.service.AdminUserLogService;

/**
 * admin_user_log service implement
 * 
 * @author 詹晟
 */
@Service(value = "adminUserLogService")
public class AdminUserLogServiceImpl implements AdminUserLogService {

	/**
	 * 注入 AdminUserLogDAO
	 */
	@Autowired
	private AdminUserLogDAO adminUserLogDAO;

	/**
	 * 搜尋全部管理員日誌
	 * 
	 * @return List<AdminUserLogBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AdminUserLogBean> select() {

		return adminUserLogDAO.select();
	}

	/**
	 * 管理員流水號搜尋
	 * 
	 * @return List<AdminUserLogBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AdminUserLogBean> selectByAdul_adu_id(Integer adul_adu_id) {

		return adminUserLogDAO.selectByAdul_adu_id(adul_adu_id);
	}

	/**
	 * 新增管理員日誌
	 * 
	 * @return adminUserLogBean
	 */
	@Override
	@Transactional
	public AdminUserLogBean insert(AdminUserLogBean adminUserLogBean) {

		AdminUserLogBean result = null;

		if (adminUserLogBean != null) {

			result = adminUserLogDAO.insert(adminUserLogBean);
		}
		return result;
	}

}
