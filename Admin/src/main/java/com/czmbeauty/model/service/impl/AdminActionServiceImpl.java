/*
 * CaiZiMei
 * File: AdminActionServiceImpl.java
 * Author: 詹晟
 * Date: 2017/10/2
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.czmbeauty.model.dao.AdminActionDao;
import com.czmbeauty.model.entity.AdminActionBean;
import com.czmbeauty.model.service.AdminActionService;

/**
 * admin_action service implement
 * 
 * @author 詹晟
 */
@Service(value = "adminActionService")
public class AdminActionServiceImpl implements AdminActionService {

	/**
	 * 注入 AdminActionDao
	 */
	@Autowired
	private AdminActionDao adminActionDao;

	/**
	 * 頁面名搜尋
	 * 
	 * @param aa_page_name
	 *            String --> 頁面名
	 * @return AdminActionBean
	 */
	@Override
	public AdminActionBean selectByAa_page_name(String aa_page_name) {

		AdminActionBean adminActionBean = adminActionDao.selectByAa_page_name(aa_page_name);

		return adminActionBean;
	}

}
