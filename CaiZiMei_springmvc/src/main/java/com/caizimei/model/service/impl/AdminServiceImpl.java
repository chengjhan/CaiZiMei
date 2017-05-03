/*
 * CaiZiMei
 * File: AdminServiceImpl.java
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

import com.caizimei.model.dao.AdminDAO;
import com.caizimei.model.entity.AdminBean;
import com.caizimei.model.service.AdminService;

/**
 * admin service implement
 * 
 * @author 詹晟
 */
@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {

	/**
	 * 注入 AdminDAO
	 */
	@Autowired
	private AdminDAO adminDAO;

	/**
	 * 搜尋全部總公司
	 * 
	 * @return List<AdminBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AdminBean> select() {

		return adminDAO.select();
	}

	/**
	 * 新增總公司
	 * 
	 * @param adminBean-->AdminBean
	 * @return result-->AdminBean
	 */
	@Override
	@Transactional
	public AdminBean insert(AdminBean adminBean) {

		AdminBean result = null;

		if (adminBean != null) {

			result = adminDAO.insert(adminBean);
		}
		return result;
	}

}
