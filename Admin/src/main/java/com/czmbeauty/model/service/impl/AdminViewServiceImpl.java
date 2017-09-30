/*
 * CaiZiMei
 * File: AdminViewServiceImpl.java
 * Author: 詹晟
 * Date: 2017/10/1
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.czmbeauty.model.dao.AdminViewDao;
import com.czmbeauty.model.entity.AdminViewBean;
import com.czmbeauty.model.service.AdminViewService;

/**
 * admin_view service implement
 * 
 * @author 詹晟
 */
@Service(value = "adminViewService")
public class AdminViewServiceImpl implements AdminViewService {

	/**
	 * 注入 AdminViewDao
	 */
	@Autowired
	private AdminViewDao adminViewDao;

	/**
	 * 頁面名搜尋
	 * 
	 * @param av_page_name
	 *            String --> 頁面名
	 * @return AdminViewBean
	 */
	@Override
	public AdminViewBean selectByAv_page_name(String av_page_name) {

		AdminViewBean adminViewBean = adminViewDao.selectByAv_page_name(av_page_name);

		return adminViewBean;
	}

}
