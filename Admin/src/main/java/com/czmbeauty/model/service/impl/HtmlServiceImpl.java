/*
 * CaiZiMei
 * File: HtmlServiceImpl.java
 * Author: 詹晟
 * Date: 2017/10/23
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.HtmlDao;
import com.czmbeauty.model.entity.CategoryBean;
import com.czmbeauty.model.entity.HtmlBean;
import com.czmbeauty.model.service.HtmlService;

/**
 * html service implement
 * 
 * @author 詹晟
 */
@Service(value = "htmlService")
public class HtmlServiceImpl implements HtmlService {

	/**
	 * 注入 HtmlDao
	 */
	@Autowired
	private HtmlDao htmlDao;

	/**
	 * 搜尋特定類別的所有 html (分頁)
	 * 
	 * @param ht_ca_id
	 *            Integer --> 類別流水號
	 * @param page
	 *            Integer --> 當前頁碼
	 * @param max
	 *            int --> 每頁最大筆數
	 * @return List<HtmlBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<HtmlBean> selectPagination(Integer ht_ca_id, Integer page, int max) {

		// 取得當頁起始筆數
		int first = (page - 1) * max;

		return htmlDao.selectPagination(ht_ca_id, first, max);
	}

	/**
	 * 搜尋特定類別的所有 html 筆數 (分頁)
	 * 
	 * @param ht_CategoryBean
	 *            CategoryBean
	 * @return int
	 */
	@Override
	@Transactional(readOnly = true)
	public int selectCountByHt_Ca(CategoryBean ht_CategoryBean) {

		return htmlDao.selectCountByHt_Ca(ht_CategoryBean);
	}

	/**
	 * html 流水號搜尋
	 * 
	 * @param ht_id
	 *            Integer --> html 流水號
	 * @throws IllegalArgumentException
	 * @return HtmlBean
	 */
	@Override
	@Transactional(readOnly = true)
	public HtmlBean selectByHt_id(Integer ht_id) throws IllegalArgumentException {

		return htmlDao.selectByHt_id(ht_id);
	}

	/**
	 * 新增 html
	 * 
	 * @param htmlBean
	 *            HtmlBean
	 * @return HtmlBean
	 */
	@Override
	@Transactional
	public HtmlBean insert(HtmlBean htmlBean) {

		return htmlDao.insert(htmlBean);
	}

	/**
	 * 修改資料
	 * 
	 * @param htmlBean
	 *            HtmlBean
	 * @return HtmlBean
	 */
	@Override
	@Transactional
	public HtmlBean update(HtmlBean htmlBean) {

		return htmlDao.update(htmlBean);
	}

	/**
	 * 切換狀態
	 * 
	 * @param ht_id
	 *            Integer --> html 流水號
	 * @return HtmlBean
	 */
	@Override
	@Transactional
	public HtmlBean updateHt_status(Integer ht_id) {

		// 在同一個 Session 中利用 get() 取出資料為持久化狀態 (Persistent)，物件的內容更新將直接反應至資料庫
		HtmlBean htmlBean = htmlDao.selectByHt_id(ht_id);

		List<HtmlBean> list = htmlDao.selectByHt_status(htmlBean.getHt_CategoryBean().getCa_id());

		if (list != null) {

			for (HtmlBean bean : list) {
				HtmlBean other = htmlDao.selectByHt_id(bean.getHt_id());
				other.setHt_status(0);
			}
		}
		htmlBean.setHt_status(1);

		return htmlBean;
	}

}
