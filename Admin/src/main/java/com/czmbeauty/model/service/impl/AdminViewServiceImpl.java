/*
 * CaiZiMei
 * File: AdminViewServiceImpl.java
 * Author: 詹晟
 * Date: 2017/10/2
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
	 * 視圖名搜尋
	 * 
	 * @param av_view_name
	 *            String --> 視圖名
	 * @return AdminViewBean
	 */
	@Override
	public AdminViewBean selectByAv_view_name(String av_view_name) {

		AdminViewBean adminViewBean = adminViewDao.selectByAv_view_name(av_view_name);

		return adminViewBean;
	}

}
