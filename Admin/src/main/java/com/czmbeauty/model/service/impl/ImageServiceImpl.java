/*
 * CaiZiMei
 * File: ImageServiceImpl.java
 * Author: 詹晟
 * Date: 2017/8/22
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.ImageDao;
import com.czmbeauty.model.entity.CategoryBean;
import com.czmbeauty.model.entity.ImageBean;
import com.czmbeauty.model.service.ImageService;

/**
 * image service implement
 * 
 * @author 詹晟
 */
@Service(value = "imageService")
public class ImageServiceImpl implements ImageService {

	/**
	 * 注入 ImageDao
	 */
	@Autowired
	private ImageDao imageDao;

	/**
	 * 搜尋特定類別的所有圖片 (分頁)
	 * 
	 * @param hql
	 *            String
	 * @param first
	 *            int --> 起始筆數
	 * @param max
	 *            int --> 最大筆數
	 * @return List<ImageBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ImageBean> selectPagination(String hql, int first, int max) {

		return imageDao.selectPagination(hql, first, max);
	}

	/**
	 * 搜尋特定類別的所有圖片筆數 (分頁)
	 * 
	 * @param im_CategoryBean
	 *            CategoryBean
	 * @return int
	 */
	@Override
	@Transactional(readOnly = true)
	public int selectCountByIm_Ca(CategoryBean im_CategoryBean) {

		return imageDao.selectCountByIm_Ca(im_CategoryBean);
	}

	/**
	 * 圖片流水號搜尋
	 * 
	 * @param im_id
	 *            Integer --> 圖片流水號
	 * @return ImageBean
	 */
	@Override
	@Transactional(readOnly = true)
	public ImageBean selectByIm_id(Integer im_id) {

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

		ImageBean result = null;

		if (imageBean != null) {

			result = imageDao.insert(imageBean);
		}
		return result;
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

		if (imageBean.getIm_status() == 1) {

			// 不顯示
			imageBean.setIm_status(0);
		} else {

			// 顯示
			imageBean.setIm_status(1);
		}
		return imageBean;
	}

}
