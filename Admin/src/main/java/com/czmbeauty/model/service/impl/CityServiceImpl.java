/*
 * CaiZiMei
 * File: CityServiceImpl.java
 * Author: 詹晟
 * Date: 2017/7/17
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.CityDao;
import com.czmbeauty.model.entity.CityBean;
import com.czmbeauty.model.service.CityService;

/**
 * city service implement
 * 
 * @author 詹晟
 */
@Service(value = "cityService")
public class CityServiceImpl implements CityService {

	/**
	 * 注入 CityDao
	 */
	@Autowired
	private CityDao cityDao;

	/**
	 * 搜尋所有城市
	 * 
	 * @return List<CityBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CityBean> selectAll() {

		return cityDao.selectAll();
	}

	/**
	 * 城市流水號搜尋
	 * 
	 * @param ci_id-->城市流水號
	 * @return result-->CityBean
	 */
	@Override
	@Transactional(readOnly = true)
	public CityBean selectByCi_id(Integer ci_id) {

		CityBean result = null;

		if (ci_id != 0) {

			result = cityDao.selectByCi_id(ci_id);
		}
		return result;
	}

	/**
	 * 區域流水號搜尋
	 * 
	 * @param ci_st_id-->區域流水號
	 * @return List<CityBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CityBean> selectByCi_st_id(Integer ci_st_id) {

		return cityDao.selectByCi_st_id(ci_st_id);
	}

	/**
	 * 新增城市
	 * 
	 * @param cityBean-->CityBean
	 * @return result-->CityBean
	 */
	@Override
	@Transactional
	public CityBean insert(CityBean cityBean) {

		CityBean result = null;

		if (cityBean != null) {

			result = cityDao.insert(cityBean);
		}
		return result;
	}

	/**
	 * 修改資料
	 * 
	 * @param cityBean-->CityBean
	 * @return CityBean
	 */
	@Override
	@Transactional
	public CityBean update(CityBean cityBean) {

		return cityDao.update(cityBean);
	}

	/**
	 * 刪除城市
	 * 
	 * @param ci_id-->城市流水號
	 * @return true-->成功
	 */
	@Override
	@Transactional
	public Boolean delete(Integer ci_id) {

		return cityDao.delete(ci_id);
	}

}
