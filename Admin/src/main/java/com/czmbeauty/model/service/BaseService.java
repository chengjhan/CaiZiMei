/*
 * CaiZiMei
 * File: BaseService.java
 * Author: 詹晟
 * Date: 2017/7/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.List;

import com.czmbeauty.model.entity.BaseBean;

/**
 * base service interface
 * 
 * @author 詹晟
 */
public interface BaseService {

	/**
	 * @see com.czmbeauty.model.service.impl.BaseServiceImpl#selectAllOffice()
	 */
	List<BaseBean> selectAllOffice();

	/**
	 * @see com.czmbeauty.model.service.impl.BaseServiceImpl#selectAllFranchisee()
	 */
	List<BaseBean> selectAllFranchisee();

	/**
	 * @see com.czmbeauty.model.service.impl.BaseServiceImpl#selectAllClinic()
	 */
	List<BaseBean> selectAllClinic();

	/**
	 * @see com.czmbeauty.model.service.impl.BaseServiceImpl#selectOpenClinic()
	 */
	List<BaseBean> selectOpenClinic();

	/**
	 * @see com.czmbeauty.model.service.impl.BaseServiceImpl#selectByBa_id(Integer)
	 */
	BaseBean selectByBa_id(Integer ba_id);

	/**
	 * @see com.czmbeauty.model.service.impl.BaseServiceImpl#insert(BaseBean)
	 */
	BaseBean insert(BaseBean baseBean);

	/**
	 * @see com.czmbeauty.model.service.impl.BaseServiceImpl#update(BaseBean)
	 */
	BaseBean update(BaseBean baseBean);

	/**
	 * @see com.czmbeauty.model.service.impl.BaseServiceImpl#updateBa_status(Integer)
	 */
	BaseBean updateBa_status(Integer ba_id);

}
