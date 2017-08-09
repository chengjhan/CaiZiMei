/*
 * CaiZiMei
 * File: BaseKindServiceImpl.java
 * Author: 詹晟
 * Date: 2017/8/9
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.BaseKindDao;
import com.czmbeauty.model.entity.BaseKindBean;
import com.czmbeauty.model.service.BaseKindService;

/**
 * base_kind service implement
 * 
 * @author 詹晟
 */
@Service(value = "baseKindService")
public class BaseKindServiceImpl implements BaseKindService {

	/**
	 * 注入 BaseKindDao
	 */
	@Autowired
	private BaseKindDao baseKindDao;

	/**
	 * 據點種類流水號搜尋
	 * 
	 * @param bk_id
	 *            Integer --> 據點類別流水號
	 * @return BaseKindBean
	 */
	@Override
	@Transactional(readOnly = true)
	public BaseKindBean selectByBk_id(Integer bk_id) {

		BaseKindBean result = null;

		if (bk_id != 0) {

			result = baseKindDao.selectByBk_id(bk_id);
		}
		return result;
	}

}
