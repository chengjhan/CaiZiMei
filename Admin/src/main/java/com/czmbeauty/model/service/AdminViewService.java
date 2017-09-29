/*
 * CaiZiMei
 * File: AdminViewService.java
 * Author: 詹晟
 * Date: 2017/9/29
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import com.czmbeauty.common.exception.PageNotFoundException;
import com.czmbeauty.model.entity.AdminViewBean;

/**
 * admin_view service interface
 * 
 * @author 詹晟
 */
public interface AdminViewService {

	/**
	 * @see com.czmbeauty.model.service.impl.AdminViewServiceImpl#selectByAv_Page_name(String)
	 */
	AdminViewBean selectByAv_Page_name(String av_page_name) throws PageNotFoundException;

}
