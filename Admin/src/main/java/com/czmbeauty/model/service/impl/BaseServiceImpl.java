/*
 * CaiZiMei
 * File: BaseServiceImpl.java
 * Author: 詹晟
 * Date: 2017/8/7
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.common.util.Geocoder;
import com.czmbeauty.model.dao.BaseDao;
import com.czmbeauty.model.dao.CityDao;
import com.czmbeauty.model.entity.BaseBean;
import com.czmbeauty.model.service.BaseService;

/**
 * base service implement
 * 
 * @author 詹晟
 */
@Service(value = "baseService")
public class BaseServiceImpl implements BaseService {

	/**
	 * 注入 CityDao
	 */
	@Autowired
	private CityDao cityDao;

	/**
	 * 注入 BaseDao
	 */
	@Autowired
	private BaseDao baseDao;

	/**
	 * 搜尋所有辦事處
	 * 
	 * @return List<BaseBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<BaseBean> selectAllOffice() {

		return baseDao.selectAllOffice();
	}

	/**
	 * 搜尋所有加盟店
	 * 
	 * @return List<BaseBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<BaseBean> selectAllFranchisee() {

		return baseDao.selectAllFranchisee();
	}

	/**
	 * 搜尋所有診所
	 * 
	 * @return List<BaseBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<BaseBean> selectAllClinic() {

		return baseDao.selectAllClinic();
	}

	/**
	 * 搜尋所有診所 (分頁)
	 * 
	 * @return List<BaseBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<BaseBean> selectAllClinic(Integer first, Integer max) {

		return baseDao.selectAllClinic(first, max);
	}

	/**
	 * 搜尋開啟的診所
	 * 
	 * @return List<BaseBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<BaseBean> selectOpenClinic() {

		return baseDao.selectOpenClinic();
	}

	/**
	 * 據點流水號搜尋
	 * 
	 * @param ba_id
	 *            Integer --> 據點流水號
	 * @return BaseBean
	 */
	@Override
	@Transactional(readOnly = true)
	public BaseBean selectByBa_id(Integer ba_id) {

		return baseDao.selectByBa_id(ba_id);
	}

	/**
	 * 新增據點
	 * 
	 * @param baseBean
	 *            BaseBean
	 * @return BaseBean
	 */
	@Override
	@Transactional
	public BaseBean insert(BaseBean baseBean) {

		BaseBean result = null;

		if (baseBean != null) {

			// 地址轉換經緯度
			String ci_name = cityDao.selectByCi_id(baseBean.getBa_CityBean().getCi_id()).getCi_name();
			String ba_address = baseBean.getBa_address();
			Double[] LatLng = new Double[2];
			try {
				LatLng = Geocoder.addressToLatLng(ci_name + ba_address);
			} catch (Exception e) {
				e.printStackTrace();
			}

			baseBean.setBa_latitude(LatLng[0]);
			baseBean.setBa_longitude(LatLng[1]);
			baseBean.setBa_insert_time(new java.util.Date());
			baseBean.setBa_update_time(new java.util.Date());
			baseBean.setBa_status(1);
			baseBean.setBa_status_time(new java.util.Date());

			result = baseDao.insert(baseBean);
		}
		return result;
	}

	/**
	 * 修改資料
	 * 
	 * @param baseBean
	 *            BaseBean
	 * @return BaseBean
	 */
	@Override
	@Transactional
	public BaseBean update(BaseBean baseBean) {

		// 地址轉換經緯度
		String ci_name = cityDao.selectByCi_id(baseBean.getBa_CityBean().getCi_id()).getCi_name();
		String ba_address = baseBean.getBa_address();
		Double[] LatLng = new Double[2];
		try {
			LatLng = Geocoder.addressToLatLng(ci_name + ba_address);
		} catch (Exception e) {
			e.printStackTrace();
		}

		baseBean.setBa_latitude(LatLng[0]);
		baseBean.setBa_longitude(LatLng[1]);
		baseBean.setBa_update_time(new java.util.Date());

		return baseDao.update(baseBean);
	}

	/**
	 * 切換狀態
	 * 
	 * @param ba_id
	 *            Integer --> 據點流水號
	 * @return BaseBean
	 */
	@Override
	@Transactional
	public BaseBean updateBa_status(Integer ba_id) {

		// 在同一個 Session 中利用 get() 取出資料為持久化狀態 (Persistent)，物件的內容更新將直接反應至資料庫
		BaseBean baseBean = baseDao.selectByBa_id(ba_id);

		if (baseBean.getBa_status() == 1) {

			// 不顯示
			baseBean.setBa_status(0);
			baseBean.setBa_status_time(new java.util.Date());
		} else {

			// 顯示
			baseBean.setBa_status(1);
			baseBean.setBa_status_time(new java.util.Date());
		}
		return baseBean;
	}

}
