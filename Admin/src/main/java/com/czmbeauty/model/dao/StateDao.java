/*
 * CaiZiMei
 * File: StateDao.java
 * Author: 詹晟
 * Date: 2017/8/12
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

import com.czmbeauty.model.entity.StateBean;

/**
 * state DAO interface
 * 
 * @author 詹晟
 */
public interface StateDao {

	/**
	 * @see com.czmbeauty.model.dao.impl.StateDaoImpl#selectBySt_id(Integer)
	 */
	StateBean selectBySt_id(Integer st_id);

	/**
	 * @see com.czmbeauty.model.dao.impl.StateDaoImpl#selectBySt_co_id(Integer)
	 */
	List<StateBean> selectBySt_co_id(Integer st_co_id);

	/**
	 * @see com.czmbeauty.model.dao.impl.StateDaoImpl#insert(StateBean)
	 */
	StateBean insert(StateBean stateBean);

	/**
	 * @see com.czmbeauty.model.dao.impl.StateDaoImpl#update(StateBean)
	 */
	StateBean update(StateBean stateBean);

}
