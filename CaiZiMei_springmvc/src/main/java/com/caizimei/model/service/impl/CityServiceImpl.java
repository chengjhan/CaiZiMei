/*
 * CaiZiMei
 * File: CityServiceImpl.java
 * Author: 詹晟
 * Date: 2017/4/10
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caizimei.model.dao.CityDAO;
import com.caizimei.model.dao.CountryDAO;
import com.caizimei.model.entity.CityBean;
import com.caizimei.model.service.CityService;

/**
 * city service implement
 * 
 * @author 詹晟
 */
@Service(value = "cityService")
public class CityServiceImpl implements CityService {

	/**
	 * 注入 CityDAO
	 */
	@Autowired
	private CityDAO cityDAO;

	/**
	 * 注入 CountryDAO
	 */
	@Autowired
	private CountryDAO countryDAO;

	/**
	 * 搜尋全部城市
	 * 
	 * @return List<CityBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CityBean> select() {

		return cityDAO.select();
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

			result = cityDAO.selectByCi_id(ci_id);
		}
		return result;
	}

	/**
	 * 國家流水號搜尋
	 * 
	 * @param ci_co_id-->國家流水號
	 * @return List<CityBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CityBean> selectByCi_co_id(Integer ci_co_id) {

		return cityDAO.selectByCi_co_id(ci_co_id);
	}

	/**
	 * 國家名搜尋
	 * 
	 * @param co_name-->國家名
	 * @return List<CityBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CityBean> selectByCo_name(String co_name) {

		return cityDAO.selectByCi_co_id(countryDAO.selectByCo_name(co_name).get(0).getCo_id());
	}

	/**
	 * 城市名搜尋
	 * 
	 * @param ci_name-->城市名
	 * @return result-->CityBean
	 */
	@Override
	@Transactional(readOnly = true)
	public CityBean selectByCi_name(String ci_name) {

		CityBean result = null;

		if (ci_name != null) {

			result = cityDAO.selectByCi_name(ci_name).get(0);
		}
		return result;
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

			result = cityDAO.insert(cityBean);
		}
		return result;
	}

	/**
	 * 修改城市資料
	 * 
	 * @param cityBean-->CityBean
	 * @return CityBean
	 */
	@Override
	@Transactional
	public CityBean update(CityBean cityBean) {

		return cityDAO.update(cityBean);
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

		return cityDAO.delete(ci_id);
	}

}
