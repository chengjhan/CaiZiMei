/*
 * CaiZiMei/User
 * File: UserUrlServiceImpl.java
 * Author: 詹晟
 * Date: 2017/11/29
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.UserUrlDao;
import com.czmbeauty.model.entity.CategoryUrlBean;
import com.czmbeauty.model.entity.UserUrlBean;
import com.czmbeauty.model.service.UserUrlService;

/**
 * user_url service implement
 * 
 * @author 詹晟
 */
@Service(value = "userUrlService")
public class UserUrlServiceImpl implements UserUrlService {

	/**
	 * 注入 UserUrlDao
	 */
	@Autowired
	private UserUrlDao userUrlDao;

	/**
	 * URL 類別及 URL 搜尋
	 * 
	 * @param uu_CategoryUrlBean
	 *            CategoryUrlBean --> URL 類別
	 * @param uu_url
	 *            String --> URL
	 * @return UserUrlBean
	 * @return null
	 */
	@Override
	@Transactional(readOnly = true)
	public UserUrlBean selectByUu_url(CategoryUrlBean uu_CategoryUrlBean, String uu_url) {

		return userUrlDao.selectByUu_url(uu_CategoryUrlBean, uu_url);
	}

}
