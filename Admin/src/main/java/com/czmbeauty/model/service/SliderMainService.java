/*
 * CaiZiMei
 * File: SliderMainService.java
 * Author: 詹晟
 * Date: 2017/7/27
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.List;

import com.czmbeauty.model.entity.SliderMainBean;

/**
 * slider_main service interface
 * 
 * @author 詹晟
 */
public interface SliderMainService {

	/**
	 * @see com.czmbeauty.model.service.impl.SliderMainServiceImpl#selectAll()
	 */
	List<SliderMainBean> selectAll();

	/**
	 * @see com.czmbeauty.model.service.impl.SliderMainServiceImpl#selectBySm_id(Integer)
	 */
	SliderMainBean selectBySm_id(Integer sm_id);

	/**
	 * @see com.czmbeauty.model.service.impl.SliderMainServiceImpl#selectBySm_status()
	 */
	List<SliderMainBean> selectBySm_status();

	/**
	 * @see com.czmbeauty.model.service.impl.SliderMainServiceImpl#insert(SliderMainBean)
	 */
	SliderMainBean insert(SliderMainBean sliderMainBean);

	/**
	 * @see com.czmbeauty.model.service.impl.SliderMainServiceImpl#update(SliderMainBean)
	 */
	SliderMainBean update(SliderMainBean sliderMainBean);

	/**
	 * @see com.czmbeauty.model.service.impl.SliderMainServiceImpl#updateSm_status(SliderMainBean)
	 */
	SliderMainBean updateSm_status(SliderMainBean sliderMainBean_sm_id);

}
