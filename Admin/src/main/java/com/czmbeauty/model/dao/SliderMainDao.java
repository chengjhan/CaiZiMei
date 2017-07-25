/*
 * CaiZiMei
 * File: SliderMainDao.java
 * Author: 詹晟
 * Date: 2017/7/25
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

	List<SliderMainBean> selectAll();

	SliderMainBean selectBySm_id(Integer sm_id);

	List<SliderMainBean> selectBySm_status();

	SliderMainBean insert(SliderMainBean sliderMainBean);

	SliderMainBean update(SliderMainBean sliderMainBean);

}
