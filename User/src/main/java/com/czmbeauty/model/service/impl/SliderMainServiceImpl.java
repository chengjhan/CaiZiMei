/*
 * CaiZiMei/User
 * File: SliderMainServiceImpl.java
 * Author: 詹晟
 * Date: 2017/7/27
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.SliderMainDao;
import com.czmbeauty.model.entity.SliderMainBean;
import com.czmbeauty.model.service.SliderMainService;

/**
 * slider_main service implement
 * 
 * @author 詹晟
 */
@Service(value = "sliderMainService")
public class SliderMainServiceImpl implements SliderMainService {

	/**
	 * 注入 SliderMainDao
	 */
	@Autowired
	private SliderMainDao sliderMainDao;

	/**
	 * 狀態搜尋
	 * 
	 * @return List<SliderMainBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<SliderMainBean> selectBySm_status() {

		return sliderMainDao.selectBySm_status();
	}

}
