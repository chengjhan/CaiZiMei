/*
 * CaiZiMei/User
 * File: UserPathServiceImpl.java
 * Author: 詹晟
 * Date: 2017/11/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.UserPathDao;
import com.czmbeauty.model.entity.CategoryPathBean;
import com.czmbeauty.model.entity.UserPathBean;
import com.czmbeauty.model.service.UserPathService;

/**
 * user_path service implement
 * 
 * @author 詹晟
 */
@Service(value = "userPathService")
public class UserPathServiceImpl implements UserPathService {

	/**
	 * 注入 UserPathDao
	 */
	@Autowired
	private UserPathDao userPathDao;

	/**
	 * path 類別及 path 搜尋
	 * 
	 * @param up_CategoryPathBean
	 *            CategoryPathBean --> path 類別
	 * @param up_path
	 *            String --> path
	 * @return UserPathBean
	 * @return null
	 */
	@Override
	@Transactional(readOnly = true)
	public UserPathBean selectByUp_path(CategoryPathBean up_CategoryPathBean, String up_path) {

		return userPathDao.selectByUp_path(up_CategoryPathBean, up_path);
	}

}
