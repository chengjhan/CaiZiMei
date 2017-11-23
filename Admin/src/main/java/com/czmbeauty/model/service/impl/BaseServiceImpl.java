/*
 * CaiZiMei
 * File: BaseServiceImpl.java
 * Author: 詹晟
 * Date: 2017/11/23
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
import com.czmbeauty.model.entity.CategoryBean;
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
	 * 搜尋特定類別的所有據點 (分頁)
	 * 
	 * @param ba_ca_id
	 *            Integer --> 類別流水號
	 * @param page
	 *            Integer --> 當前頁碼
	 * @param max
	 *            int --> 每頁最大筆數
	 * @return List<BaseBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<BaseBean> selectPagination(Integer ba_ca_id, Integer page, int max) {

		// 取得當頁起始筆數
		int first = (page - 1) * max;

		return baseDao.selectPagination(ba_ca_id, first, max);
	}

	/**
	 * 搜尋特定類別的所有據點筆數 (分頁)
	 * 
	 * @param ba_CategoryBean
	 *            CategoryBean
	 * @return int
	 */
	@Override
	@Transactional(readOnly = true)
	public int selectCountByBa_Ca(CategoryBean ba_CategoryBean) {

		return baseDao.selectCountByBa_Ca(ba_CategoryBean);
	}

	/**
	 * 據點流水號搜尋
	 * 
	 * @param ba_id
	 *            Integer --> 據點流水號
	 * @throws IllegalArgumentException
	 * @return BaseBean
	 */
	@Override
	@Transactional(readOnly = true)
	public BaseBean selectByBa_id(Integer ba_id) throws IllegalArgumentException {

		return baseDao.selectByBa_id(ba_id);
	}

	/**
	 * 新增據點
	 * 
	 * @param baseBean
	 *            BaseBean
	 * @throws Exception
	 * @return BaseBean
	 */
	@Override
	@Transactional
	public BaseBean insert(BaseBean baseBean) throws Exception {

		// 地址轉換經緯度
		String ci_name = cityDao.selectByCi_id(baseBean.getBa_CityBean().getCi_id()).getCi_name();
		String ba_address = baseBean.getBa_address();
		Double[] LatLng = Geocoder.addressToLatLng(ci_name + ba_address);

		baseBean.setBa_latitude(LatLng[0]);
		baseBean.setBa_longitude(LatLng[1]);
		baseBean.setBa_insert_time(new java.util.Date());
		baseBean.setBa_update_time(new java.util.Date());
		baseBean.setBa_status(1);
		baseBean.setBa_status_time(new java.util.Date());

		return baseDao.insert(baseBean);
	}

	/**
	 * 修改資料
	 * 
	 * @param baseBean
	 *            BaseBean
	 * @throws Exception
	 * @return BaseBean
	 */
	@Override
	@Transactional
	public BaseBean update(BaseBean baseBean) throws Exception {

		// 地址轉換經緯度
		String ci_name = cityDao.selectByCi_id(baseBean.getBa_CityBean().getCi_id()).getCi_name();
		String ba_address = baseBean.getBa_address();
		Double[] LatLng = Geocoder.addressToLatLng(ci_name + ba_address);

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
