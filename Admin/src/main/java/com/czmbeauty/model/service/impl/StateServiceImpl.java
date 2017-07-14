/*
 * CaiZiMei
 * File: StateServiceImpl.java
 * Author: 詹晟
 * Date: 2017/7/14
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
	 * 搜尋全部區域
	 * 
	 * @return List<StateBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<StateBean> selectAll() {

		return stateDao.selectAll();
	}

	/**
	 * 區域流水號搜尋
	 * 
	 * @param s_id-->區域流水號
	 * @return result-->StateBean
	 */
	@Override
	@Transactional(readOnly = true)
	public StateBean selectByS_id(Integer s_id) {

		StateBean result = null;

		if (s_id != 0) {

			result = stateDao.selectByS_id(s_id);
		}
		return result;
	}

	/**
	 * 國家流水號搜尋
	 * 
	 * @param s_co_id-->國家流水號
	 * @return List<StateBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<StateBean> selectByS_co_id(Integer s_co_id) {

		return stateDao.selectByS_co_id(s_co_id);
	}

	/**
	 * 區域名搜尋
	 * 
	 * @param s_name-->區域名
	 * @return result-->StateBean
	 */
	@Override
	@Transactional(readOnly = true)
	public StateBean selectByS_name(String s_name) {

		StateBean result = null;

		if (s_name != null) {

			result = stateDao.selectByS_name(s_name).get(0);
		}
		return result;
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
	 * @param s_id-->城市流水號
	 * @return true-->成功
	 */
	@Override
	@Transactional
	public Boolean delete(Integer s_id) {

		return stateDao.delete(s_id);
	}

}
