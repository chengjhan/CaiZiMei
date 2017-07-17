/*
 * CaiZiMei
 * File: StateDao.java
 * Author: 詹晟
 * Date: 2017/7/17
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

	List<StateBean> selectAll();

	StateBean selectByS_id(Integer s_id);

	List<StateBean> selectByS_co_id(Integer s_co_id);

	StateBean insert(StateBean stateBean);

	StateBean update(StateBean stateBean);

	Boolean delete(Integer s_id);

}
