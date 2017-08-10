/*
 * CaiZiMei/User
 * File: BaseServiceImpl.java
 * Author: 詹晟
 * Date: 2017/8/10
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.BaseDao;
import com.czmbeauty.model.entity.BaseBean;
import com.czmbeauty.model.service.BaseService;

/**
 * base service implement
 * 
 * @author 詹晟
 */
@Service(value = "baseService")
public class BaseServiceImpl implements BaseService {

	/**
	 * 注入 BaseDao
	 */
	@Autowired
	private BaseDao baseDao;

	/**
	 * 搜尋開啟的據點
	 * 
	 * @return List<BaseBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<BaseBean> selectOpenBase() {

		return baseDao.selectOpenBase();
	}

}
