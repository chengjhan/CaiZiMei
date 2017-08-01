/*
 * CaiZiMei
 * File: CountryServiceImpl.java
 * Author: 詹晟
 * Date: 2017/8/1
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.CountryDao;
import com.czmbeauty.model.entity.CountryBean;
import com.czmbeauty.model.service.CountryService;

/**
 * country service implement
 * 
 * @author 詹晟
 */
@Service(value = "countryService")
public class CountryServiceImpl implements CountryService {

	/**
	 * 注入 CountryDao
	 */
	@Autowired
	private CountryDao countryDao;

	/**
	 * 搜尋所有國家
	 * 
	 * @return List<CountryBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CountryBean> selectAll() {

		return countryDao.selectAll();
	}

	/**
	 * 國家流水號搜尋
	 * 
	 * @param co_id
	 *            Integer --> 國家流水號
	 * @return CountryBean
	 */
	@Override
	@Transactional(readOnly = true)
	public CountryBean selectByCo_id(Integer co_id) {

		CountryBean result = null;

		if (co_id != 0) {

			result = countryDao.selectByCo_id(co_id);
		}
		return result;
	}

	/**
	 * 新增國家
	 * 
	 * @param countryBean
	 *            CountryBean
	 * @return CountryBean
	 */
	@Override
	@Transactional
	public CountryBean insert(CountryBean countryBean) {

		CountryBean result = null;

		if (countryBean != null) {

			result = countryDao.insert(countryBean);
		}
		return result;
	}

	/**
	 * 修改資料
	 * 
	 * @param countryBean
	 *            CountryBean
	 * @return CountryBean
	 */
	@Override
	@Transactional
	public CountryBean update(CountryBean countryBean) {

		return countryDao.update(countryBean);
	}

	/**
	 * 刪除國家
	 * 
	 * @param co_id
	 *            Integer --> 國家流水號
	 * @return true
	 */
	@Override
	@Transactional
	public Boolean delete(Integer co_id) {

		return countryDao.delete(co_id);
	}

}
