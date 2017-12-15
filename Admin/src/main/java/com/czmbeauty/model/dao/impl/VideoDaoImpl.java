/*
 * CaiZiMei
 * File: VideoDaoImpl.java
 * Author: 詹晟
 * Date: 2017/12/15
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.VideoDao;
import com.czmbeauty.model.entity.VideoBean;

/**
 * video DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "videoDao")
public class VideoDaoImpl implements VideoDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋特定類別的所有影片 (分頁)
	 * 
	 * @param vi_ca_id
	 *            Integer --> 類別流水號
	 * @param first
	 *            int --> 當頁起始筆數
	 * @param max
	 *            int --> 每頁最大筆數
	 * @return Map<String, Object>
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> selectPagination(Integer vi_ca_id, int first, int max) {

		Map<String, Object> map = new HashMap<String, Object>();

		// outer method
		List<VideoBean> result = (List<VideoBean>) hibernateTemplate.execute(

				// inner class
				new HibernateCallback() {

					// inner method
					public Object doInHibernate(Session session) throws HibernateException {

						// count
						map.put("count", session.createQuery(HQL_SELECT_VIDEO_BY_CATEGORY)
								.setParameter("vi_ca_id", vi_ca_id).getResultList().size());

						// list
						List<VideoBean> list = session.createQuery(HQL_SELECT_VIDEO_BY_CATEGORY)
								.setParameter("vi_ca_id", vi_ca_id).setFirstResult(first).setMaxResults(max)
								.getResultList();

						return list;
					}
				});
		map.put("list", result);

		return map;
	}

	/**
	 * 影片流水號搜尋
	 * 
	 * @param vi_id
	 *            Integer --> 影片流水號
	 * @return VideoBean
	 */
	@Override
	public VideoBean selectByVi_id(Integer vi_id) {

		return hibernateTemplate.get(VideoBean.class, vi_id);
	}

	/**
	 * 影片狀態搜尋
	 * 
	 * @param vi_ca_id
	 *            Integer --> 類別流水號
	 * @return List<VideoBean>
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<VideoBean> selectByVi_status(Integer vi_ca_id) {

		List<VideoBean> list = (List<VideoBean>) hibernateTemplate.findByNamedParam(HQL_SELECT_OPEN_VIDEO, "vi_ca_id",
				vi_ca_id);

		return !list.isEmpty() ? list : null;
	}

	/**
	 * 新增影片
	 * 
	 * @param videoBean
	 *            VideoBean
	 * @return VideoBean
	 */
	@Override
	public VideoBean insert(VideoBean videoBean) {

		hibernateTemplate.save(videoBean);

		return videoBean;
	}

	/**
	 * 修改資料
	 * 
	 * @param videoBean
	 *            VideoBean
	 * @return VideoBean
	 */
	@Override
	public VideoBean update(VideoBean videoBean) {

		hibernateTemplate.clear();
		hibernateTemplate.update(videoBean);

		return videoBean;
	}

}
