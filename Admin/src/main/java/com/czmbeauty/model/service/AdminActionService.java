/*
 * CaiZiMei
 * File: AdminActionService.java
 * Author: 詹晟
 * Date: 2017/10/2
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import com.czmbeauty.model.entity.AdminActionBean;

/**
 * admin_action service interface
 * 
 * @author 詹晟
 */
public interface AdminActionService {

	/**
	 * @see com.czmbeauty.model.service.impl.AdminActionServiceImpl#selectByAa_action_name(String)
	 */
	AdminActionBean selectByAa_action_name(String aa_action_name);

}
