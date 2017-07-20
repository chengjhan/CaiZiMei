/*
 * CaiZiMei
 * File: StateServiceImpl.java
 * Author: 詹晟
 * Date: 2017/7/20
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.StateDao;
import com.czmbeauty.model.entity.StateBean;
import com.czmbeauty.model.service.StateService;

/**
 * state service implement
 * 
 * @author 詹晟
 */
@Service(value = "stateService")
public class StateServiceImpl implements StateService {

	/**
	 * 注入 StateDao
	 */
	@Autowired
	private StateDao stateDao;

	/**
	 * 區域流水號搜尋
	 * 
	 * @param st_id-->區域流水號
	 * @return result-->StateBean
	 */
	@Override
	@Transactional(readOnly = true)
	public StateBean selectBySt_id(Integer st_id) {

		StateBean result = null;

		if (st_id != 0) {

			result = stateDao.selectBySt_id(st_id);
		}
		return result;
	}

	/**
	 * 國家流水號搜尋
	 * 
	 * @param st_co_id-->國家流水號
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
	 * @param stateBean-->StateBean
	 * @return result-->StateBean
	 */
	@Override
	@Transactional
	public StateBean insert(StateBean stateBean) {

		StateBean result = null;

		if (stateBean != null) {

			result = stateDao.insert(stateBean);
		}
		return result;
	}

	/**
	 * 修改資料
	 * 
	 * @param stateBean-->StateBean
	 * @return StateBean
	 */
	@Override
	@Transactional
	public StateBean update(StateBean stateBean) {

		return stateDao.update(stateBean);
	}

	/**
	 * 刪除區域
	 * 
	 * @param st_id-->城市流水號
	 * @return true-->成功
	 */
	@Override
	@Transactional
	public Boolean delete(Integer st_id) {

		return stateDao.delete(st_id);
	}

}
