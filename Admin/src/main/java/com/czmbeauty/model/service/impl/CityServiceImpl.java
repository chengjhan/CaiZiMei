/*
 * CaiZiMei
 * File: CityServiceImpl.java
 * Author: 詹晟
 * Date: 2017/12/28
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.common.constants.ServiceConstants;
import com.czmbeauty.model.dao.CityDao;
import com.czmbeauty.model.entity.BaseBean;
import com.czmbeauty.model.entity.CityBean;
import com.czmbeauty.model.service.CityService;

/**
 * city service implement
 * 
 * @author 詹晟
 */
@Service(value = "cityService")
public class CityServiceImpl implements CityService, ServiceConstants {

	/**
	 * 注入 CityDao
	 */
	@Autowired
	private CityDao cityDao;

	/**
	 * 城市流水號搜尋
	 * 
	 * @param ci_id
	 *            Integer --> 城市流水號
	 * @return CityBean
	 */
	@Override
	@Transactional(readOnly = true)
	public CityBean selectByCi_id(Integer ci_id) {

		return cityDao.selectByCi_id(ci_id);
	}

	/**
	 * 區域流水號搜尋
	 * 
	 * @param ci_st_id
	 *            Integer --> 區域流水號
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
	 * @param cityBean
	 *            CityBean
	 * @return CityBean
	 */
	@Override
	@Transactional
	public CityBean insert(CityBean cityBean) {

		cityBean.setCi_name(cityBean.getCi_name().trim());
		cityBean.setCi_status(CITY_STATUS_OPEN);

		return cityDao.insert(cityBean);
	}

	/**
	 * 修改資料
	 * 
	 * @param cityBean
	 *            CityBean
	 * @return CityBean
	 */
	@Override
	@Transactional
	public CityBean update(CityBean cityBean) {

		cityBean.setCi_name(cityBean.getCi_name().trim());

		return cityDao.update(cityBean);
	}

	/**
	 * 切換狀態
	 * 
	 * @param ci_id
	 *            Integer
	 * @return CityBean
	 */
	@Override
	@Transactional
	public CityBean updateCi_status(Integer ci_id) {

		// 在同一個 Session 中利用 get() 取出資料為持久化狀態 (Persistent)，物件的內容更新將直接反應至資料庫
		CityBean cityBean = cityDao.selectByCi_id(ci_id);

		if (cityBean.getCi_status() == CITY_STATUS_OPEN) {

			// 不顯示
			cityBean.setCi_status(CITY_STATUS_CLOSE);
			Set<BaseBean> baseSet = cityBean.getCi_BaseBean();
			for (BaseBean baseBean : baseSet) {
				baseBean.setBa_status(BASE_STATUS_CLOSE);
				baseBean.setBa_status_time(new java.util.Date());
			}
		} else {

			// 顯示
			cityBean.setCi_status(CITY_STATUS_OPEN);
			Set<BaseBean> baseSet = cityBean.getCi_BaseBean();
			for (BaseBean baseBean : baseSet) {
				baseBean.setBa_status(BASE_STATUS_OPEN);
				baseBean.setBa_status_time(new java.util.Date());
			}
		}

		return cityBean;
	}

}
