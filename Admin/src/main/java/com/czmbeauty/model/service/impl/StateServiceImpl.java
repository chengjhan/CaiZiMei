/*
 * CaiZiMei
 * File: StateServiceImpl.java
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
import com.czmbeauty.model.dao.StateDao;
import com.czmbeauty.model.entity.BaseBean;
import com.czmbeauty.model.entity.CityBean;
import com.czmbeauty.model.entity.StateBean;
import com.czmbeauty.model.service.StateService;

/**
 * state service implement
 * 
 * @author 詹晟
 */
@Service(value = "stateService")
public class StateServiceImpl implements StateService, ServiceConstants {

	/**
	 * 注入 StateDao
	 */
	@Autowired
	private StateDao stateDao;

	/**
	 * 區域流水號搜尋
	 * 
	 * @param st_id
	 *            Integer --> 區域流水號
	 * @return StateBean
	 */
	@Override
	@Transactional(readOnly = true)
	public StateBean selectBySt_id(Integer st_id) {

		return stateDao.selectBySt_id(st_id);
	}

	/**
	 * 國家流水號搜尋
	 * 
	 * @param st_co_id
	 *            Integer --> 國家流水號
	 * @return List<StateBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<StateBean> selectBySt_co_id(Integer st_co_id) {

		return stateDao.selectBySt_co_id(st_co_id);
	}

	/**
	 * 新增區域
	 * 
	 * @param stateBean
	 *            StateBean
	 * @return StateBean
	 */
	@Override
	@Transactional
	public StateBean insert(StateBean stateBean) {

		stateBean.setSt_name(stateBean.getSt_name().trim());
		stateBean.setSt_status(STATE_STATUS_OPEN);

		return stateDao.insert(stateBean);
	}

	/**
	 * 修改資料
	 * 
	 * @param stateBean
	 *            StateBean
	 * @return StateBean
	 */
	@Override
	@Transactional
	public StateBean update(StateBean stateBean) {

		stateBean.setSt_name(stateBean.getSt_name().trim());

		return stateDao.update(stateBean);
	}

	/**
	 * 切換狀態
	 * 
	 * @param st_id
	 *            Integer
	 * @return StateBean
	 */
	@Override
	@Transactional
	public StateBean updateSt_status(Integer st_id) {

		// 在同一個 Session 中利用 get() 取出資料為持久化狀態 (Persistent)，物件的內容更新將直接反應至資料庫
		StateBean stateBean = stateDao.selectBySt_id(st_id);

		if (stateBean.getSt_status() == STATE_STATUS_OPEN) {

			// 不顯示
			stateBean.setSt_status(STATE_STATUS_CLOSE);
			Set<CityBean> citySet = stateBean.getSt_CityBean();
			for (CityBean cityBean : citySet) {
				cityBean.setCi_status(CITY_STATUS_CLOSE);
			}
			Set<BaseBean> baseSet = stateBean.getSt_BaseBean();
			for (BaseBean baseBean : baseSet) {
				baseBean.setBa_status(BASE_STATUS_CLOSE);
				baseBean.setBa_status_time(new java.util.Date());
			}
		} else {

			// 顯示
			stateBean.setSt_status(STATE_STATUS_OPEN);
			Set<CityBean> citySet = stateBean.getSt_CityBean();
			for (CityBean cityBean : citySet) {
				cityBean.setCi_status(CITY_STATUS_OPEN);
			}
			Set<BaseBean> baseSet = stateBean.getSt_BaseBean();
			for (BaseBean baseBean : baseSet) {
				baseBean.setBa_status(BASE_STATUS_OPEN);
				baseBean.setBa_status_time(new java.util.Date());
			}
		}

		return stateBean;
	}

}
