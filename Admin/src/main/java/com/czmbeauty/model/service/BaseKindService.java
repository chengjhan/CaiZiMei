/*
 * CaiZiMei
 * File: BaseKindService.java
 * Author: 詹晟
 * Date: 2017/8/9
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import com.czmbeauty.model.entity.BaseKindBean;

/**
 * base_kind service interface
 * 
 * @author 詹晟
 */
public interface BaseKindService {

	/**
	 * @see com.czmbeauty.model.service.impl.BaseKindServiceImpl#selectByBk_id(Integer)
	 */
	BaseKindBean selectByBk_id(Integer bk_id);

}
