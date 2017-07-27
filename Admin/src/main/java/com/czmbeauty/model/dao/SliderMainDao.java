/*
 * CaiZiMei
 * File: SliderMainDao.java
 * Author: 詹晟
 * Date: 2017/7/27
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

import com.czmbeauty.model.entity.SliderMainBean;

/**
 * slider_main DAO interface
 * 
 * @author 詹晟
 */
public interface SliderMainDao {

	/**
	 * @see com.czmbeauty.model.dao.impl.SliderMainDaoImpl#selectAll()
	 */
	List<SliderMainBean> selectAll();

	/**
	 * @see com.czmbeauty.model.dao.impl.SliderMainDaoImpl#selectBySm_id(Integer)
	 */
	SliderMainBean selectBySm_id(Integer sm_id);

	/**
	 * @see com.czmbeauty.model.dao.impl.SliderMainDaoImpl#selectBySm_status()
	 */
	List<SliderMainBean> selectBySm_status();

	/**
	 * @see com.czmbeauty.model.dao.impl.SliderMainDaoImpl#insert(SliderMainBean)
	 */
	SliderMainBean insert(SliderMainBean sliderMainBean);

	/**
	 * @see com.czmbeauty.model.dao.impl.SliderMainDaoImpl#update(SliderMainBean)
	 */
	SliderMainBean update(SliderMainBean sliderMainBean);

}
