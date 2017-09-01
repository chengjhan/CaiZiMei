/*
 * CaiZiMei
 * File: YoutubeServiceImpl.java
 * Author: 詹晟
 * Date: 2017/8/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.YoutubeDao;
import com.czmbeauty.model.entity.CategoryBean;
import com.czmbeauty.model.entity.YoutubeBean;
import com.czmbeauty.model.service.YoutubeService;

/**
 * youtube service implement
 * 
 * @author 詹晟
 */
@Service(value = "youtubeService")
public class YoutubeServiceImpl implements YoutubeService {

	/**
	 * 注入 YoutubeDao
	 */
	@Autowired
	private YoutubeDao youtubeDao;

	/**
	 * 搜尋特定類別的所有影片 (分頁)
	 * 
	 * @param hql
	 *            String
	 * @param first
	 *            int --> 起始筆數
	 * @param max
	 *            int --> 最大筆數
	 * @return List<YoutubeBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<YoutubeBean> selectPagination(String hql, int first, int max) {

		return youtubeDao.selectPagination(hql, first, max);
	}

	/**
	 * 搜尋特定類別的所有影片筆數 (分頁)
	 * 
	 * @param yo_CategoryBean
	 *            CategoryBean
	 * @return int
	 */
	@Override
	@Transactional(readOnly = true)
	public int selectCountByYo_Ca(CategoryBean yo_CategoryBean) {

		return youtubeDao.selectCountByYo_Ca(yo_CategoryBean);
	}

	/**
	 * 影片流水號搜尋
	 * 
	 * @param yo_id
	 *            Integer --> 影片流水號
	 * @return YoutubeBean
	 */
	@Override
	@Transactional(readOnly = true)
	public YoutubeBean selectByYo_id(Integer yo_id) {

		return youtubeDao.selectByYo_id(yo_id);
	}

	/**
	 * 新增影片
	 * 
	 * @param youtubeBean
	 *            YoutubeBean
	 * @return YoutubeBean
	 */
	@Override
	@Transactional
	public YoutubeBean insert(YoutubeBean youtubeBean) {

		YoutubeBean result = null;

		if (youtubeBean != null) {

			result = youtubeDao.insert(youtubeBean);
		}
		return result;
	}

	/**
	 * 修改資料
	 * 
	 * @param youtubeBean
	 *            YoutubeBean
	 * @return YoutubeBean
	 */
	@Override
	@Transactional
	public YoutubeBean update(YoutubeBean youtubeBean) {

		return youtubeDao.update(youtubeBean);
	}

}
