/*
 * CaiZiMei
 * File: StateService.java
 * Author: 詹晟
 * Date: 2017/8/12
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.List;

import com.czmbeauty.model.entity.StateBean;

/**
 * state service interface
 * 
 * @author 詹晟
 */
public interface StateService {

	/**
	 * @see com.czmbeauty.model.service.impl.StateServiceImpl#selectBySt_id(Integer)
	 */
	StateBean selectBySt_id(Integer st_id);

	/**
	 * @see com.czmbeauty.model.service.impl.StateServiceImpl#selectBySt_co_id(Integer)
	 */
	List<StateBean> selectBySt_co_id(Integer st_co_id);

	/**
	 * @see com.czmbeauty.model.service.impl.StateServiceImpl#insert(StateBean)
	 */
	StateBean insert(StateBean stateBean);

	/**
	 * @see com.czmbeauty.model.service.impl.StateServiceImpl#update(StateBean)
	 */
	StateBean update(StateBean stateBean);

	/**
	 * @see com.czmbeauty.model.service.impl.StateServiceImpl#updateSt_status(Integer)
	 */
	StateBean updateSt_status(Integer st_id);

}
