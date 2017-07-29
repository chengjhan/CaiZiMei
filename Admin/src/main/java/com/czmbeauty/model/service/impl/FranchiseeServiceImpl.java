/*
 * CaiZiMei
 * File: FranchiseeServiceImpl.java
 * Author: 詹晟
 * Date: 2017/7/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.common.util.Geocoder;
import com.czmbeauty.model.dao.CityDao;
import com.czmbeauty.model.dao.FranchiseeDao;
import com.czmbeauty.model.entity.FranchiseeBean;
import com.czmbeauty.model.service.FranchiseeService;

/**
 * franchisee service implement
 * 
 * @author 詹晟
 */
@Service(value = "franchiseeService")
public class FranchiseeServiceImpl implements FranchiseeService {

	/**
	 * 注入 CityDao
	 */
	@Autowired
	private CityDao cityDao;

	/**
	 * 注入 FranchiseeDao
	 */
	@Autowired
	private FranchiseeDao franchiseeDao;

	/**
	 * 搜尋所有加盟店
	 * 
	 * @return List<FranchiseeBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<FranchiseeBean> selectAll() {

		return franchiseeDao.selectAll();
	}

	/**
	 * 加盟店流水號搜尋
	 * 
	 * @param fr_id
	 *            Integer --> 加盟店流水號
	 * @return FranchiseeBean
	 */
	@Override
	@Transactional(readOnly = true)
	public FranchiseeBean selectByFr_id(Integer fr_id) {

		return franchiseeDao.selectByFr_id(fr_id);
	}

	/**
	 * 狀態搜尋
	 * 
	 * @return List<FranchiseeBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<FranchiseeBean> selectByFr_status() {

		return franchiseeDao.selectByFr_status();
	}

	/**
	 * 新增加盟店
	 * 
	 * @param franchiseeBean
	 *            FranchiseeBean
	 * @return FranchiseeBean
	 */
	@Override
	@Transactional
	public FranchiseeBean insert(FranchiseeBean franchiseeBean) {

		FranchiseeBean result = null;

		if (franchiseeBean != null) {

			// 地址轉換經緯度
			String ci_name = cityDao.selectByCi_id(franchiseeBean.getFr_CityBean().getCi_id()).getCi_name();
			String fr_address = franchiseeBean.getFr_address();
			Double[] LatLng = new Double[2];
			try {
				LatLng = Geocoder.addressToLatLng(ci_name + fr_address);
			} catch (Exception e) {
				e.printStackTrace();
			}

			franchiseeBean.setFr_latitude(LatLng[0]);
			franchiseeBean.setFr_longitude(LatLng[1]);
			franchiseeBean.setFr_insert_time(new java.util.Date());
			franchiseeBean.setFr_update_time(new java.util.Date());
			franchiseeBean.setFr_status(1);
			franchiseeBean.setFr_status_time(new java.util.Date());

			result = franchiseeDao.insert(franchiseeBean);
		}
		return result;
	}

	/**
	 * 修改資料
	 * 
	 * @param franchiseeBean
	 *            FranchiseeBean
	 * @return FranchiseeBean
	 */
	@Override
	@Transactional
	public FranchiseeBean update(FranchiseeBean franchiseeBean) {

		// 地址轉換經緯度
		String ci_name = cityDao.selectByCi_id(franchiseeBean.getFr_CityBean().getCi_id()).getCi_name();
		String fr_address = franchiseeBean.getFr_address();
		Double[] LatLng = new Double[2];
		try {
			LatLng = Geocoder.addressToLatLng(ci_name + fr_address);
		} catch (Exception e) {
			e.printStackTrace();
		}

		franchiseeBean.setFr_latitude(LatLng[0]);
		franchiseeBean.setFr_longitude(LatLng[1]);
		franchiseeBean.setFr_update_time(new java.util.Date());

		return franchiseeDao.update(franchiseeBean);
	}

	/**
	 * 切換狀態
	 * 
	 * @param fr_id
	 *            Integer --> 加盟店流水號
	 * @return FranchiseeBean
	 */
	@Override
	@Transactional
	public FranchiseeBean updateFr_status(Integer fr_id) {

		// 在同一個 Session 中利用 get() 取出資料為持久化狀態 (Persistent)，物件的內容更新將直接反應至資料庫
		FranchiseeBean franchiseeBean = franchiseeDao.selectByFr_id(fr_id);

		if (franchiseeBean.getFr_status() == 1) {

			// 不顯示
			franchiseeBean.setFr_status(0);
			franchiseeBean.setFr_status_time(new java.util.Date());
		} else {

			// 顯示
			franchiseeBean.setFr_status(1);
			franchiseeBean.setFr_status_time(new java.util.Date());
		}
		return franchiseeBean;
	}

}
