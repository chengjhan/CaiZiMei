/*
 * CaiZiMei
 * File: SliderMainServiceImpl.java
 * Author: 詹晟
 * Date: 2017/7/28
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
	 * 搜尋所有圖片
	 * 
	 * @return List<SliderMainBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<SliderMainBean> selectAll() {

		return sliderMainDao.selectAll();
	}

	/**
	 * 圖片流水號搜尋
	 * 
	 * @param sm_id
	 *            Integer --> 圖片流水號
	 * @return SliderMainBean
	 */
	@Override
	@Transactional(readOnly = true)
	public SliderMainBean selectBySm_id(Integer sm_id) {

		return sliderMainDao.selectBySm_id(sm_id);
	}

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

	/**
	 * 新增圖片
	 * 
	 * @param sliderMainBean
	 *            SliderMainBean
	 * @return SliderMainBean
	 */
	@Override
	@Transactional
	public SliderMainBean insert(SliderMainBean sliderMainBean) {

		SliderMainBean result = null;

		if (sliderMainBean != null) {

			result = sliderMainDao.insert(sliderMainBean);
		}
		return result;
	}

	/**
	 * 修改資料
	 * 
	 * @param sliderMainBean
	 *            SliderMainBean
	 * @return SliderMainBean
	 */
	@Override
	@Transactional
	public SliderMainBean update(SliderMainBean sliderMainBean) {

		return sliderMainDao.update(sliderMainBean);
	}

	/**
	 * 切換狀態
	 * 
	 * @param sliderMainBean_sm_id
	 *            SliderMainBean --> sm_id
	 * @return SliderMainBean
	 */
	@Override
	@Transactional
	public SliderMainBean updateSm_status(SliderMainBean sliderMainBean_sm_id) {

		// 在同一個 Session 中利用 get() 取出資料為持久化狀態 (Persistent)，物件的內容更新將直接反應至資料庫
		SliderMainBean sliderMainBean = sliderMainDao.selectBySm_id(sliderMainBean_sm_id.getSm_id());

		if (sliderMainBean.getSm_status() == 1) {

			// 不顯示
			sliderMainBean.setSm_status(0);
		} else {

			// 顯示
			sliderMainBean.setSm_status(1);
		}
		return sliderMainBean;
	}

}
