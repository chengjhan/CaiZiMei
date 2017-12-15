/*
 * CaiZiMei
 * File: VideoServiceImpl.java
 * Author: 詹晟
 * Date: 2017/12/15
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.VideoDao;
import com.czmbeauty.model.entity.VideoBean;
import com.czmbeauty.model.service.VideoService;

/**
 * video service implement
 * 
 * @author 詹晟
 */
@Service(value = "videoService")
public class VideoServiceImpl implements VideoService {

	/**
	 * 注入 VideoDao
	 */
	@Autowired
	private VideoDao videoDao;

	/**
	 * 搜尋特定類別的所有影片 (分頁)
	 * 
	 * @param vi_ca_id
	 *            Integer --> 類別流水號
	 * @param page
	 *            Integer --> 當前頁碼
	 * @param max
	 *            int --> 每頁最大筆數
	 * @return Map<String, Object>
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> selectPagination(Integer vi_ca_id, Integer page, int max) {

		// 取得當頁起始筆數
		int first = (page - 1) * max;

		return videoDao.selectPagination(vi_ca_id, first, max);
	}

	/**
	 * 影片流水號搜尋
	 * 
	 * @param vi_id
	 *            Integer --> 影片流水號
	 * @throws IllegalArgumentException
	 * @return VideoBean
	 */
	@Override
	@Transactional(readOnly = true)
	public VideoBean selectByVi_id(Integer vi_id) throws IllegalArgumentException {

		return videoDao.selectByVi_id(vi_id);
	}

	/**
	 * 新增影片
	 * 
	 * @param videoBean
	 *            VideoBean
	 * @return VideoBean
	 */
	@Override
	@Transactional
	public VideoBean insert(VideoBean videoBean) {

		videoBean.setVi_name(videoBean.getVi_name().trim());
		videoBean.setVi_status(0);
		videoBean.setVi_update_time(new java.util.Date());

		return videoDao.insert(videoBean);
	}

	/**
	 * 修改資料
	 * 
	 * @param videoBean
	 *            VideoBean
	 * @return VideoBean
	 */
	@Override
	@Transactional
	public VideoBean update(VideoBean videoBean) {

		videoBean.setVi_name(videoBean.getVi_name().trim());
		videoBean.setVi_status(videoDao.selectByVi_id(videoBean.getVi_id()).getVi_status());
		videoBean.setVi_update_time(new java.util.Date());

		return videoDao.update(videoBean);
	}

	/**
	 * 切換狀態
	 * 
	 * @param vi_id
	 *            Integer --> 影片流水號
	 * @return VideoBean
	 */
	@Override
	@Transactional
	public VideoBean updateVi_status(Integer vi_id) {

		// 在同一個 Session 中利用 get() 取出資料為持久化狀態 (Persistent)，物件的內容更新將直接反應至資料庫
		VideoBean videoBean = videoDao.selectByVi_id(vi_id);

		List<VideoBean> list = videoDao.selectByVi_status(videoBean.getVi_CategoryBean().getCa_id());

		if (list != null) {

			for (VideoBean bean : list) {
				VideoBean other = videoDao.selectByVi_id(bean.getVi_id());
				other.setVi_status(0);
			}
		}

		videoBean.setVi_status(1);

		return videoBean;
	}

}
