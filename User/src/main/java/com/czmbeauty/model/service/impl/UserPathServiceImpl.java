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

import com.czmbeauty.model.dao.CategoryPathDao;
import com.czmbeauty.model.dao.UserPathDao;
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
	 * 注入 CategoryPathDao
	 */
	@Autowired
	private CategoryPathDao categoryPathDao;

	/**
	 * 注入 UserPathDao
	 */
	@Autowired
	private UserPathDao userPathDao;

	/**
	 * extension 及 path 搜尋
	 * 
	 * @param cp_extension
	 *            String --> extension
	 * @param up_path
	 *            String --> path
	 * @return UserPathBean
	 * @return null
	 */
	@Override
	@Transactional(readOnly = true)
	public UserPathBean selectByUp_path(String cp_extension, String up_path) {

		return userPathDao.selectByUp_path(categoryPathDao.selectByCp_extension(cp_extension), up_path);
	}

}
