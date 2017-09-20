/*
 * CaiZiMei
 * File: VideoDaoImpl.java
 * Author: 詹晟
 * Date: 2017/9/20
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.VideoDao;
import com.czmbeauty.model.entity.CategoryBean;
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
	 * @param hql
	 *            String
	 * @param first
	 *            int --> 起始筆數
	 * @param max
	 *            int --> 最大筆數
	 * @return List<ImageBean>
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VideoBean> selectPagination(String hql, int first, int max) {

		// outer method
		List<VideoBean> result = (List<VideoBean>) hibernateTemplate.execute(

				// inner class
				new HibernateCallback() {

					// inner method
					public Object doInHibernate(Session session) throws HibernateException {
						List<VideoBean> list = session.createQuery(hql).setFirstResult(first).setMaxResults(max)
								.getResultList();
						return list;
					}
				});
		return result;
	}

	/**
	 * 搜尋特定類別的所有影片筆數 (分頁)
	 * 
	 * @param vi_CategoryBean
	 *            CategoryBean
	 * @return int
	 */
	@Override
	public int selectCountByVi_Ca(CategoryBean vi_CategoryBean) {

		DetachedCriteria criteria = DetachedCriteria.forClass(VideoBean.class);

		criteria.add(Restrictions.eq("vi_CategoryBean", vi_CategoryBean));

		return hibernateTemplate.findByCriteria(criteria).size();
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

		return (!list.isEmpty()) ? list : null;
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
