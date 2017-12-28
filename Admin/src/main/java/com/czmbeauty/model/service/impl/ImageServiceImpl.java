/*
 * CaiZiMei
 * File: ImageServiceImpl.java
 * Author: 詹晟
 * Date: 2017/12/28
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.common.constants.ServiceConstants;
import com.czmbeauty.model.dao.ImageDao;
import com.czmbeauty.model.entity.ImageBean;
import com.czmbeauty.model.service.ImageService;

/**
 * image service implement
 * 
 * @author 詹晟
 */
@Service(value = "imageService")
public class ImageServiceImpl implements ImageService, ServiceConstants {

	/**
	 * 注入 ImageDao
	 */
	@Autowired
	private ImageDao imageDao;

	/**
	 * 搜尋特定類別的所有圖片 (分頁)
	 * 
	 * @param im_ca_id
	 *            Integer --> 類別流水號
	 * @param page
	 *            Integer --> 當前頁碼
	 * @param max
	 *            int --> 每頁最大筆數
	 * @return Map<String, Object>
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> selectPagination(Integer im_ca_id, Integer page, int max) {

		// 取得當頁起始筆數
		int first = (page - 1) * max;

		return imageDao.selectPagination(im_ca_id, first, max);
	}

	/**
	 * 圖片流水號搜尋
	 * 
	 * @param im_id
	 *            Integer --> 圖片流水號
	 * @throws IllegalArgumentException
	 * @return ImageBean
	 */
	@Override
	@Transactional(readOnly = true)
	public ImageBean selectByIm_id(Integer im_id) throws IllegalArgumentException {

		return imageDao.selectByIm_id(im_id);
	}

	/**
	 * 新增圖片
	 * 
	 * @param imageBean
	 *            ImageBean
	 * @return ImageBean
	 */
	@Override
	@Transactional
	public ImageBean insert(ImageBean imageBean) {

		imageBean.setIm_name(imageBean.getIm_name().trim());
		imageBean.setIm_status(IMAGE_STATUS_OPEN);
		imageBean.setIm_update_time(new java.util.Date());

		return imageDao.insert(imageBean);
	}

	/**
	 * 修改資料
	 * 
	 * @param imageBean
	 *            ImageBean
	 * @return ImageBean
	 */
	@Override
	@Transactional
	public ImageBean update(ImageBean imageBean) {

		imageBean.setIm_name(imageBean.getIm_name().trim());
		imageBean.setIm_update_time(new java.util.Date());

		return imageDao.update(imageBean);
	}

	/**
	 * 切換狀態
	 * 
	 * @param im_id
	 *            Integer --> 圖片流水號
	 * @return ImageBean
	 */
	@Override
	@Transactional
	public ImageBean updateIm_status(Integer im_id) {

		// 在同一個 Session 中利用 get() 取出資料為持久化狀態 (Persistent)，物件的內容更新將直接反應至資料庫
		ImageBean imageBean = imageDao.selectByIm_id(im_id);

		if (imageBean.getIm_status() == IMAGE_STATUS_OPEN) {

			// 不顯示
			imageBean.setIm_status(IMAGE_STATUS_CLOSE);

		} else {

			// 顯示
			imageBean.setIm_status(IMAGE_STATUS_OPEN);
		}

		return imageBean;
	}

}
