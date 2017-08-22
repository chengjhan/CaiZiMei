/*
 * CaiZiMei
 * File: YoutubeDaoImpl.java
 * Author: 詹晟
 * Date: 2017/8/22
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
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

	@Override
	public int selectCountByYo_Ca(CategoryBean yo_CategoryBean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public YoutubeBean selectByYo_id(Integer yo_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public YoutubeBean insert(YoutubeBean youtubeBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public YoutubeBean update(YoutubeBean youtubeBean) {
		// TODO Auto-generated method stub
		return null;
	}

}
