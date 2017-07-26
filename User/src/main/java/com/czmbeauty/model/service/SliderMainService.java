/*
 * CaiZiMei/User
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

	List<SliderMainBean> selectBySm_status();

}
