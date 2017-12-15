/*
 * CaiZiMei
 * File: ImageDaoImpl.java
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

import com.czmbeauty.model.dao.ImageDao;
import com.czmbeauty.model.entity.ImageBean;

/**
 * image DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "imageDao")
public class ImageDaoImpl implements ImageDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋特定類別的所有圖片 (分頁)
	 * 
	 * @param im_ca_id
	 *            Integer --> 類別流水號
	 * @param first
	 *            int --> 當頁起始筆數
	 * @param max
	 *            int --> 每頁最大筆數
	 * @return Map<String, Object>
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> selectPagination(Integer im_ca_id, int first, int max) {

		Map<String, Object> map = new HashMap<String, Object>();

		// outer method
		List<ImageBean> result = (List<ImageBean>) hibernateTemplate.execute(

				// inner class
				new HibernateCallback() {

					// inner method
					public Object doInHibernate(Session session) throws HibernateException {

						// count
						map.put("count", session.createQuery(HQL_SELECT_IMAGE_BY_CATEGORY)
								.setParameter("im_ca_id", im_ca_id).getResultList().size());

						// list
						List<ImageBean> list = session.createQuery(HQL_SELECT_IMAGE_BY_CATEGORY)
								.setParameter("im_ca_id", im_ca_id).setFirstResult(first).setMaxResults(max)
								.getResultList();

						return list;
					}
				});
		map.put("list", result);

		return map;
	}

	/**
	 * 圖片流水號搜尋
	 * 
	 * @param im_id
	 *            Integer --> 圖片流水號
	 * @return ImageBean
	 */
	@Override
	public ImageBean selectByIm_id(Integer im_id) {

		return hibernateTemplate.get(ImageBean.class, im_id);
	}

	/**
	 * 新增圖片
	 * 
	 * @param imageBean
	 *            ImageBean
	 * @return ImageBean
	 */
	@Override
	public ImageBean insert(ImageBean imageBean) {

		hibernateTemplate.save(imageBean);

		return imageBean;
	}

	/**
	 * 修改資料
	 * 
	 * @param imageBean
	 *            ImageBean
	 * @return ImageBean
	 */
	@Override
	public ImageBean update(ImageBean imageBean) {

		hibernateTemplate.clear();
		hibernateTemplate.update(imageBean);

		return imageBean;
	}

}
