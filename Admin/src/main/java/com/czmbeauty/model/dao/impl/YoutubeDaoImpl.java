/*
 * CaiZiMei
 * File: YoutubeDaoImpl.java
 * Author: 詹晟
 * Date: 2017/8/23
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

import com.czmbeauty.model.dao.YoutubeDao;
import com.czmbeauty.model.entity.CategoryBean;
import com.czmbeauty.model.entity.YoutubeBean;

/**
 * youtube DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "youtubeDao")
public class YoutubeDaoImpl implements YoutubeDao {

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
	public List<YoutubeBean> selectPagination(String hql, int first, int max) {

		// outer method
		List<YoutubeBean> result = (List<YoutubeBean>) hibernateTemplate.execute(

				// inner class
				new HibernateCallback() {

					// inner method
					public Object doInHibernate(Session session) throws HibernateException {
						List<YoutubeBean> list = session.createQuery(hql).setFirstResult(first).setMaxResults(max)
								.getResultList();
						return list;
					}
				});
		return result;
	}

	/**
	 * 搜尋特定類別的所有影片筆數 (分頁)
	 * 
	 * @param yo_CategoryBean
	 *            CategoryBean
	 * @return int
	 */
	@Override
	public int selectCountByYo_Ca(CategoryBean yo_CategoryBean) {

		DetachedCriteria criteria = DetachedCriteria.forClass(YoutubeBean.class);

		criteria.add(Restrictions.eq("yo_CategoryBean", yo_CategoryBean));

		return hibernateTemplate.findByCriteria(criteria).size();
	}

	/**
	 * 影片流水號搜尋
	 * 
	 * @param yo_id
	 *            Integer --> 影片流水號
	 * @return YoutubeBean
	 */
	@Override
	public YoutubeBean selectByYo_id(Integer yo_id) {

		return hibernateTemplate.get(YoutubeBean.class, yo_id);
	}

	/**
	 * 新增影片
	 * 
	 * @param youtubeBean
	 *            YoutubeBean
	 * @return YoutubeBean
	 */
	@Override
	public YoutubeBean insert(YoutubeBean youtubeBean) {

		hibernateTemplate.save(youtubeBean);

		return youtubeBean;
	}

	/**
	 * 修改資料
	 * 
	 * @param youtubeBean
	 *            YoutubeBean
	 * @return YoutubeBean
	 */
	@Override
	public YoutubeBean update(YoutubeBean youtubeBean) {

		hibernateTemplate.clear();
		hibernateTemplate.update(youtubeBean);

		return youtubeBean;
	}

}
