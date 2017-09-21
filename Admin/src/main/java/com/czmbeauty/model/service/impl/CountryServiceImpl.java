/*
 * CaiZiMei
 * File: CountryServiceImpl.java
 * Author: 詹晟
 * Date: 2017/9/21
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.common.exception.PageNotFoundException;
import com.czmbeauty.model.dao.CountryDao;
import com.czmbeauty.model.entity.BaseBean;
import com.czmbeauty.model.entity.CityBean;
import com.czmbeauty.model.entity.CountryBean;
import com.czmbeauty.model.entity.StateBean;
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
	 * @throws PageNotFoundException
	 * @throws IllegalArgumentException
	 * @return CountryBean
	 */
	@Override
	@Transactional(readOnly = true)
	public CountryBean selectByCo_id(Integer co_id) throws PageNotFoundException, IllegalArgumentException {

		CountryBean countryBean = countryDao.selectByCo_id(co_id);

		if (countryBean == null) {

			throw new PageNotFoundException();
		}
		return countryBean;
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

			countryBean.setCo_status(1);

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
	 * 切換狀態
	 * 
	 * @param co_id
	 *            Integer
	 * @return CountryBean
	 */
	@Override
	@Transactional
	public CountryBean updateCo_status(Integer co_id) {

		// 在同一個 Session 中利用 get() 取出資料為持久化狀態 (Persistent)，物件的內容更新將直接反應至資料庫
		CountryBean countryBean = countryDao.selectByCo_id(co_id);

		if (countryBean.getCo_status() == 1) {

			// 不顯示
			countryBean.setCo_status(0);
			Set<StateBean> stateSet = countryBean.getCo_StateBean();
			for (StateBean stateBean : stateSet) {
				stateBean.setSt_status(0);
			}
			Set<CityBean> citySet = countryBean.getCo_CityBean();
			for (CityBean cityBean : citySet) {
				cityBean.setCi_status(0);
			}
			Set<BaseBean> baseSet = countryBean.getCo_BaseBean();
			for (BaseBean baseBean : baseSet) {
				baseBean.setBa_status(0);
				baseBean.setBa_status_time(new java.util.Date());
			}
		} else {

			// 顯示
			countryBean.setCo_status(1);
			Set<StateBean> stateSet = countryBean.getCo_StateBean();
			for (StateBean stateBean : stateSet) {
				stateBean.setSt_status(1);
			}
			Set<CityBean> citySet = countryBean.getCo_CityBean();
			for (CityBean cityBean : citySet) {
				cityBean.setCi_status(1);
			}
			Set<BaseBean> baseSet = countryBean.getCo_BaseBean();
			for (BaseBean baseBean : baseSet) {
				baseBean.setBa_status(1);
				baseBean.setBa_status_time(new java.util.Date());
			}
		}
		return countryBean;
	}

}
