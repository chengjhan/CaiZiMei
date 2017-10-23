/*
 * CaiZiMei/User
 * File: UserViewServiceImpl.java
 * Author: 詹晟
 * Date: 2017/10/23
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.UserViewDao;
import com.czmbeauty.model.entity.UserViewBean;
import com.czmbeauty.model.service.UserViewService;

/**
 * user_view service implement
 * 
 * @author 詹晟
 */
@Service(value = "userViewService")
public class UserViewServiceImpl implements UserViewService {

	/**
	 * 注入 UserViewDao
	 */
	@Autowired
	private UserViewDao userViewDao;

	/**
	 * 視圖名搜尋
	 * 
	 * @param uv_view_name
	 *            String --> 視圖名
	 * @return UserViewBean
	 */
	@Override
	@Transactional(readOnly = true)
	public UserViewBean selectByUv_view_name(String uv_view_name) {

		return userViewDao.selectByUv_view_name(uv_view_name);
	}

}
